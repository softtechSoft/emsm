package com.softtech.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtech.entity.ExpenseListEntity;
import com.softtech.service.ExpenseListService;

/**
 * 経費リストの管理を行うコントローラクラス。
 * 経費の表示、検索、削除、および更新機能を提供する。
 */
@Controller
@RequestMapping("/expenseList")
public class ExpenseListController {
	@Value("${file.receipt.location}")
	private String receiptFolder;

	@Autowired
	private ExpenseListService expenseListService;

	/**
	 * 現在の年と月に基づいて経費データを初期表示する。
	 *
	 * @param model モデルオブジェクト
	 * @return 経費リストのビュー名
	 */
	@GetMapping
	public String init(Model model) {
		LocalDate now = LocalDate.now();
		int currentYear = now.getYear();
		int currentMonth = now.getMonthValue();

		// 年リストを作成（現在の年から過去10年）
		List<Integer> yearList = new ArrayList<>();
		for (int y = currentYear; y > currentYear - 10; y--) {
			yearList.add(y);
		}

		// 月リストを作成（1月から12月）
		List<Integer> monthList = new ArrayList<>();
		for (int m = 1; m <= 12; m++) {
			monthList.add(m);
		}

		// 指定された年と月の経費データを取得
		List<ExpenseListEntity> expenseList = expenseListService.findExpensesByYearMonth(currentYear, currentMonth);

		// 合計金額を計算
		double totalCost = expenseList.stream().mapToDouble(e -> e.getCost().doubleValue()).sum();

		// モデルにデータを追加
		model.addAttribute("yearList", yearList);
		model.addAttribute("monthList", monthList);
		model.addAttribute("currentYear", currentYear);
		model.addAttribute("currentMonth", currentMonth);
		model.addAttribute("expenseList", expenseList);
		model.addAttribute("totalCost", totalCost);

		return "expenseList";
	}

	/**
	 * 指定された年と月に基づいて経費データを検索し表示する。
	 *
	 * @param year   検索対象の年度
	 * @param month  検索対象の月度
	 * @param model  モデルオブジェクト
	 * @return 経費リストのビュー名
	 */
	@GetMapping("/search")
	public String search(@RequestParam("year") int year,
			@RequestParam("month") int month,
			Model model) {
		// 年リストを作成（現在の年から過去10年）
		int currentYear = LocalDate.now().getYear();
		List<Integer> yearList = new ArrayList<>();
		for (int y = currentYear; y > currentYear - 10; y--) {
			yearList.add(y);
		}

		// 月リストを作成（1月から12月）
		List<Integer> monthList = new ArrayList<>();
		for (int m = 1; m <= 12; m++) {
			monthList.add(m);
		}

		// 指定された年と月の経費データを取得
		List<ExpenseListEntity> expenseList = expenseListService.findExpensesByYearMonth(year, month);

		// 合計金額を計算
		double totalCost = expenseList.stream().mapToDouble(e -> e.getCost().doubleValue()).sum();

		// モデルにデータを追加
		model.addAttribute("yearList", yearList);
		model.addAttribute("monthList", monthList);
		model.addAttribute("currentYear", year);
		model.addAttribute("currentMonth", month);
		model.addAttribute("expenseList", expenseList);
		model.addAttribute("totalCost", totalCost);

		return "expenseList";
	}

	/**
	* 指定された経費IDの経費を論理削除する
	*
	* @param expensesID 削除対象の経費ID
	* @return 経費リストのリダイレクトURL
	* @details 精算済みデータは削除不可
	*/
	@GetMapping("/delete/{expensesID}")
	public String delete(@PathVariable("expensesID") String expensesID) {
		// 対象データの取得
		ExpenseListEntity expense = expenseListService.findById(expensesID);

		// 精算済みの場合は削除不可
		if (expense != null &&
				expense.getSettlementDate() != null &&
				expense.getSettlementType() != null &&
				!expense.getSettlementType().isEmpty()) {
			return "redirect:/expenseList?error=settled";
		}

		// 論理削除の実行
		expenseListService.deleteExpense(expensesID);
		return "redirect:/expenseList";
	}

	/**
	* 経費データの更新処理を実行する
	*
	* @param expenseDataJson 更新する経費データ（JSON形式）
	* @param file アップロードされた領収書画像（任意）
	* @return 更新処理結果のレスポンス
	* @details
	* - JSONデータのパース処理
	* - 精算済みデータの更新不可チェック
	* - 領収書画像の保存処理
	* - データベースの更新処理
	*/
	@PostMapping(value = "/update", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	@ResponseBody
	public ResponseEntity<Map<String, Object>> updateExpense(
			@RequestParam("expenseData") String expenseDataJson,
			@RequestParam(value = "receiptFile", required = false) MultipartFile file) {
		Map<String, Object> result = new HashMap<>();
		try {
			// JSONデータのパース
			ObjectMapper mapper = new ObjectMapper();
			mapper.registerModule(new JavaTimeModule());
			mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
			ExpenseListEntity formExpense = mapper.readValue(expenseDataJson, ExpenseListEntity.class);

			// 対象データの取得
			ExpenseListEntity dbEntity = expenseListService.findById(formExpense.getExpensesID());
			if (dbEntity == null) {
				result.put("status", "error");
				result.put("message", "対象データが存在しません。");
				return ResponseEntity.badRequest().body(result);
			}

			// 精算済みデータの更新チェック
			if (dbEntity.getSettlementDate() != null &&
					dbEntity.getSettlementType() != null &&
					!dbEntity.getSettlementType().isEmpty()) {
				result.put("status", "error");
				result.put("message", "精算済みのため更新できません。");
				return ResponseEntity.badRequest().body(result);
			}

			// 経費データの更新
			dbEntity.setExpensesType(formExpense.getExpensesType());
			dbEntity.setAccrualDate(formExpense.getAccrualDate());
			dbEntity.setCost(formExpense.getCost());
			dbEntity.setHappenAddress(formExpense.getHappenAddress());
			dbEntity.setTantouName(formExpense.getTantouName());
			dbEntity.setSettlementDate(formExpense.getSettlementDate());
			dbEntity.setSettlementType(formExpense.getSettlementType());

			// 領収書画像の処理
			if (file != null && !file.isEmpty()) {
				String newPath = expenseListService.saveAndReturnReceiptPath(file);
				dbEntity.setReceiptPath(newPath);
			}

			// データベースの更新
			expenseListService.updateExpense(dbEntity);

			// 正常終了レスポンス
			result.put("status", "ok");
			result.put("message", "更新成功");
			return ResponseEntity.ok(result);

		} catch (Exception e) {
			// エラー処理
			e.printStackTrace();
			result.put("status", "error");
			result.put("message", "更新失敗: " + e.getMessage());
			return ResponseEntity.badRequest().body(result);
		}
	}

	/**
	 * 領収書画像のアップロード
	 * @param expensesID 経費ID
	 * @param file フロントエンドからアップロードされたファイル
	 * @return リダイレクトURL
	 */
	@PostMapping("/uploadReceipt/{expensesID}")
	public String uploadReceipt(@PathVariable("expensesID") String expensesID,
			@RequestParam("receiptFile") MultipartFile file) {
		try {
			// サービスを呼び出してファイルを保存し、receiptPathを更新
			expenseListService.saveReceiptFile(expensesID, file);
		} catch (Exception e) {
			e.printStackTrace();
			// 必要に応じてエラーメッセージを表示
		}
		// アップロード後、リストページにリダイレクト
		return "redirect:/expenseList";
	}

	/**
	* 領収書画像のダウンロード処理を実行する
	*
	* @param expensesID 対象の経費ID
	* @return ファイルダウンロード用のレスポンスエンティティ
	* @details
	* - 経費データと領収書ファイルの存在チェック
	* - 相対パス・絶対パスの解決処理
	* - ファイルのダウンロード設定
	*/
	@GetMapping("/downloadReceipt/{expensesID}")
	@ResponseBody
	public ResponseEntity<?> downloadReceipt(@PathVariable("expensesID") String expensesID) {
		try {
			// 経費データの取得と検証
			ExpenseListEntity expense = expenseListService.findById(expensesID);
			if (expense == null || !StringUtils.hasText(expense.getReceiptPath())) {
				return ResponseEntity.notFound().build();
			}

			// ファイルパスの取得
			String pathInDb = expense.getReceiptPath();

			// パス形式の判定と解決
			Path filePath;
			if (Paths.get(pathInDb).isAbsolute()) {
				filePath = Paths.get(pathInDb);
			} else {
				// 相対パスの場合は基準ディレクトリから解決
				filePath = expenseListService.getReceiptFolderPath().resolve(pathInDb).normalize();
			}

			// ファイルの存在確認
			File file = filePath.toFile();
			if (!file.exists()) {
				return ResponseEntity.notFound().build();
			}

			// ファイルストリームの準備
			FileInputStream fis = new FileInputStream(file);
			InputStreamResource resource = new InputStreamResource(fis);

			// ダウンロードファイル名の設定
			String fileName = file.getName();
			String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name())
					.replace("+", "%20"); // 空白文字の適切な処理

			// ダウンロード用レスポンスの生成
			return ResponseEntity.ok()
					.contentType(MediaType.APPLICATION_OCTET_STREAM)
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + encodedFileName + "\"")
					.contentLength(file.length())
					.body(resource);

		} catch (IOException e) {
			// エラー処理
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("ファイルダウンロードでエラー発生: " + e.getMessage());
		}
	}

	/**
	* 領収書画像のサムネイル表示処理を実行する
	*
	* @param path 画像ファイルの絶対パス
	* @return 画像データのレスポンスエンティティ
	* @details
	* - 画像ファイルの存在チェック
	* - 拡張子に応じた適切なContent-Type設定
	* - 画像データのストリーミング
	* @example
	* <img src="expenseList/showThumbnail?path=画像の絶対パス" />
	*/
	@GetMapping("/showThumbnail")
	@ResponseBody
	public ResponseEntity<?> showThumbnail(@RequestParam("path") String path) {
		try {
			// 画像ファイルの存在確認
			File file = new File(path);
			if (!file.exists()) {
				return ResponseEntity.notFound().build();
			}

			// 画像データの読み込み
			Resource resource = new InputStreamResource(new FileInputStream(file));

			// ファイル拡張子に応じたContent-Typeの設定
			MediaType contentType = MediaType.IMAGE_JPEG; // デフォルトはJPEG
			String lowerPath = path.toLowerCase();
			if (lowerPath.endsWith(".png")) {
				contentType = MediaType.IMAGE_PNG;
			} else if (lowerPath.endsWith(".gif")) {
				contentType = MediaType.IMAGE_GIF;
			}

			// 画像データのレスポンス返却
			return ResponseEntity.ok()
					.contentType(contentType)
					.body(resource);

		} catch (Exception e) {
			// エラー処理
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("サムネイル表示中にエラーが発生しました: " + e.getMessage());
		}
	}

}
