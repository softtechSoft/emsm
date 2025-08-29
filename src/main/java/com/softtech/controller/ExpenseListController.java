package com.softtech.controller;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipOutputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
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
import com.softtech.entity.ExpenseTypeEntity;
import com.softtech.entity.SaveFolder;
import com.softtech.service.ExpenseListService;
import com.softtech.service.ExpenseTypeService;
import com.softtech.service.SaveFolderService;

/**
 * 経費リストの管理を行うコントローラクラス。
 * 経費の表示、検索、削除、および更新機能を提供する。
 */
@Controller
@RequestMapping("/expenseList")
public class ExpenseListController {
	// @Value("${file.receipt.location}")
	// private String receiptFolder;

	@Autowired
	private ExpenseListService expenseListService;
	@Autowired
	private ExpenseTypeService expenseTypeService;
	@Autowired
	private SaveFolderService saveFolderService;

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
	    //double totalCost = expenseList.stream().mapToDouble(e -> e.getCost().doubleValue()).sum();
	    int totalCost = (int)expenseList.stream().mapToDouble(e -> e.getCost().doubleValue()).sum();

	    // 経費種別マスターデータを取得（追加）
	    Map<String, List<ExpenseTypeEntity>> expenseTypeGroups = expenseTypeService.getAllExpenseTypesByGroup();

	    // モデルにデータを追加
	    model.addAttribute("yearList", yearList);
	    model.addAttribute("monthList", monthList);
	    model.addAttribute("currentYear", currentYear);
	    model.addAttribute("currentMonth", currentMonth);
	    model.addAttribute("expenseList", expenseList);
	    model.addAttribute("totalCost", totalCost);
	    model.addAttribute("expenseTypeGroups", expenseTypeGroups);

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

	    // 経費種別マスターデータを取得（追加）
	    Map<String, List<ExpenseTypeEntity>> expenseTypeGroups = expenseTypeService.getAllExpenseTypesByGroup();

	    // モデルにデータを追加
	    model.addAttribute("yearList", yearList);
	    model.addAttribute("monthList", monthList);
	    model.addAttribute("currentYear", year);
	    model.addAttribute("currentMonth", month);
	    model.addAttribute("expenseList", expenseList);
	    model.addAttribute("totalCost", totalCost);
	    model.addAttribute("expenseTypeGroups", expenseTypeGroups);

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
	        dbEntity.setmexpensesId(formExpense.getmexpensesId());

	        // 領収書画像の処理
	        if (file != null && !file.isEmpty()) {
	            // 新しい領収書を保存
	            String newPath = expenseListService.saveAndReturnReceiptPath(file, dbEntity);
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
	 * 領収書ファイルのダウンロード処理を実行する
	 *
	 * @param expensesID 対象の経費ID
	 * @return ファイルダウンロード用のレスポンスエンティティ
	 * @throws IOException ファイル処理で例外が発生した場合
	 */
	@GetMapping("/downloadReceipt/{expensesID}")
	@ResponseBody
	public ResponseEntity<?> downloadReceipt(@PathVariable("expensesID") String expensesID) {
	    try {
	        // データベースから経費データを取得
	        ExpenseListEntity expense = expenseListService.findById(expensesID);
	        if (expense == null || StringUtils.isEmpty(expense.getReceiptPath())) {
	            return ResponseEntity.notFound().build();
	        }


	        // 基準フォルダ（01: 経費管理）を解決（DB優先、なければ設定値）
	        String baseFolder = null;
	        try {
	        	SaveFolder sf = saveFolderService.findFileTypeCode("01");
	        	if (sf != null) baseFolder = sf.getSaveFolder();
	        } catch (Exception ignore) {}
	        if (!StringUtils.hasText(baseFolder)) {
	        	baseFolder = System.getProperty("user.dir");
	        }

	        // データベースのパス正規化（先頭スラッシュ除去、ドライブ無しは相対扱い）
	        String dbPath = expense.getReceiptPath();
	        String normalized = dbPath != null ? dbPath.trim() : "";
	        boolean hasDrive = normalized.matches("^[A-Za-z]:.*");
	        if (!hasDrive) {
	        	while (normalized.startsWith("/") || normalized.startsWith("\\")) {
	        		normalized = normalized.substring(1);
	        	}
	        }
	        File file = new File(normalized);
	        if (!file.isAbsolute() && StringUtils.hasText(baseFolder)) {
	        	file = new File(baseFolder, normalized);
	        }
	        if (!file.exists() || !file.isFile()) {
	        	return ResponseEntity.notFound().build();
	        }

	        // ダウンロード用ファイル名
	        String fileName = expenseListService.getFileNameFromPath(file.getPath());

	        // ストリームを返却
	        FileInputStream fileInputStream = new FileInputStream(file);
	        InputStreamResource resource = new InputStreamResource(fileInputStream);

	        // Content-Dispositionヘッダーの設定
	        String encodedFileName = URLEncoder.encode(fileName, StandardCharsets.UTF_8.name())
	                .replace("+", "%20");

	        // レスポンスの返却
	        return ResponseEntity.ok()
	                .contentType(MediaType.APPLICATION_OCTET_STREAM)
	                .contentLength(file.length())
	                .header(HttpHeaders.CONTENT_DISPOSITION,
	                        "attachment; filename=\"" + encodedFileName + "\"")
	                .body(resource);

	    } catch (IOException e) {
	        e.printStackTrace();
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body("ファイルダウンロードエラー: " + e.getMessage());
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

	/**
	 * 特定の経費種別に属する経費名称リストを取得する（Ajax用）
	 *
	 * @param expensesType 経費種別コード
	 * @return 経費名称リストのJSON
	 */
	@GetMapping("/getExpenseNamesByType/{expensesType}")
	@ResponseBody
	public ResponseEntity<List<ExpenseTypeEntity>> getExpenseNamesByType(@PathVariable("expensesType") String expensesType) {
	    try {
	        // 特定種別の経費名称リストを取得
	        Map<String, List<ExpenseTypeEntity>> typeGroup = expenseTypeService.getExpenseTypesByType(expensesType);

	        if (typeGroup.containsKey(expensesType)) {
	            return ResponseEntity.ok(typeGroup.get(expensesType));
	        } else {
	            return ResponseEntity.ok(new ArrayList<>());
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	        return ResponseEntity.badRequest().body(new ArrayList<>());
	    }
	}

	/**
	 * 年月単位のダウンロード処理を実行する
	 *
	 * @param year  年
	 * @param month 月
	 * @return ファイルダウンロード用のレスポンスエンティティ
	 * @throws IOException ファイル処理で例外が発生した場合
	 */
	@GetMapping("/downloadZip")
	@ResponseBody
	public ResponseEntity<?> downloadExpensesByMonth(@RequestParam int year,
			@RequestParam int month) {
		try {

			List<ExpenseListEntity> expenses = expenseListService.findExpensesByYearMonth(year, month);
			if (expenses == null || expenses.isEmpty()) {
				return ResponseEntity.notFound().build();
			}

			String zipFileName = String.format("expenses_%04d_%02d.zip", year, month);
			String encodedZipFileName = URLEncoder.encode(zipFileName, StandardCharsets.UTF_8.name())
					.replace("+", "%20");

			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			try (ZipOutputStream zipOut = new ZipOutputStream(baos)) {
				// ①CSVファイルを追加
				expenseListService.addCsvToZip(zipOut, expenses, year, month);
				// ②証跡ファイルを年月日サブフォルダーで追加
				expenseListService.addEvidenceFilesToZip(zipOut, expenses);
			}

			ByteArrayResource resource = new ByteArrayResource(baos.toByteArray());
			return ResponseEntity.ok()
					.contentType(MediaType.parseMediaType("application/zip"))
					.contentLength(baos.size())
					.header(HttpHeaders.CONTENT_DISPOSITION,
							"attachment; filename=\"" + encodedZipFileName + "\"")
					.body(resource);

		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("ファイルダウンロードエラー: " + e.getMessage());
		}
	}

}
