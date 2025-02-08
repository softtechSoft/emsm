package com.softtech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.softtech.entity.ExpenseListEntity;
import com.softtech.service.ExpenseInfoService;
import com.softtech.service.ExpenseListService;

@Controller
@RequestMapping("/expenseInfo")
public class ExpenseInfoController {

	@Autowired
	private ExpenseInfoService expenseInfoService;

	@Autowired
	private ExpenseListService expenseListService;

	/**
	 * 経費情報追加ページを表示する。
	 *
	 * @return 経費情報追加ページのビュー名
	 */
	@GetMapping
	public String showExpenseInfoPage() {
		return "expenseInfo";
	}

	/**
	 * 複数の経費記録を保存する。
	 * AJAXリクエストを受け取り、経費情報をデータベースに保存する。
	 *
	 * @param expenses 保存対象の経費エンティティリスト
	 * @return 保存結果を示すステータスとメッセージを含むレスポンスエンティティ
	 */
	@PostMapping("/addMultiple")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> addMultipleExpenses(@RequestBody List<ExpenseListEntity> expenses) {
		Map<String, Object> result = new HashMap<>();
		try {
			// 複数の経費エンティティをサービス層を通じて保存
			expenseInfoService.addMultipleExpenses(expenses);
			result.put("status", "ok");
			result.put("message", "保存成功");
			return ResponseEntity.ok(result);
		} catch (Exception e) {
			// エラー発生時にスタックトレースを出力し、エラーメッセージをレスポンスに含める
			e.printStackTrace();
			result.put("status", "fail");
			result.put("message", "保存失敗: " + e.getMessage());
			return ResponseEntity.badRequest().body(result);
		}
	}

	/**
	* 経費データの新規登録処理を実行する
	*
	* @param expenseDataJson 経費データ（JSON形式）
	* @param receiptFile 領収書ファイル（任意）
	* @return 処理結果のレスポンス
	* @details
	* - 経費データのJSONパース
	* - 領収書ファイルの保存
	* - データベースへの登録
	*/
	@PostMapping("/add")
	@ResponseBody
	public ResponseEntity<Map<String, Object>> addExpense(
	       @RequestParam("expenseData") String expenseDataJson,
	       @RequestParam(value = "receiptFile", required = false) MultipartFile receiptFile) {

	   Map<String, Object> result = new HashMap<>();
	   try {
	       // JSONパーサーの設定
	       ObjectMapper mapper = new ObjectMapper();
	       mapper.registerModule(new JavaTimeModule());
	       mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

	       // JSONデータを経費エンティティに変換
	       ExpenseListEntity expense = mapper.readValue(expenseDataJson, ExpenseListEntity.class);

	       // 論理削除フラグの初期設定
	       expense.setDeleteFlg("0");

	       // 経費データと領収書の保存
	       expenseInfoService.addExpenseWithReceipt(expense, receiptFile);

	       // 正常終了レスポンス
	       result.put("status", "ok");
	       result.put("message", "保存成功");
	       return ResponseEntity.ok(result);

	   } catch (Exception e) {
	       // エラー処理
	       e.printStackTrace();
	       result.put("status", "error");
	       result.put("message", "保存失敗: " + e.getMessage());
	       return ResponseEntity.badRequest().body(result);
	   }
	}

	/**
	 * アップロードされたファイルのバリデーションを行う
	 *
	 * @param file アップロード対象のファイル
	 * @throws IllegalArgumentException ファイルサイズや形式が不適切な場合にスローされる例外
	 * @details
	 * - ファイルサイズが5MB以下であることを確認
	 * - ファイル形式がJPG、PNG、PDFのいずれかであることを確認
	 */
	private void validateFile(MultipartFile file) {
	    // ファイルサイズの検証（上限5MB）
	    if (file.getSize() > 5 * 1024 * 1024) {
	        throw new IllegalArgumentException("ファイルサイズは5MB以下にしてください。");
	    }

	    // ファイル形式の検証（JPG、PNG、PDF のみ許可）
	    String contentType = file.getContentType();
	    if (contentType == null || !(contentType.equals("image/jpeg") ||
	            contentType.equals("image/png") ||
	            contentType.equals("application/pdf"))) {
	        throw new IllegalArgumentException("アップロード可能なファイル形式は JPG、PNG、PDF のみです。");
	    }
	}

	/**
	 * 領収書ファイルのアップロード処理を実行する
	 *
	 * @param expensesID 経費ID
	 * @param file アップロード対象の領収書ファイル
	 * @param redirectAttributes リダイレクト時に使用する属性
	 * @return 処理結果のリダイレクト先
	 * @details
	 * - アップロードされたファイルのバリデーションを実施
	 * - ファイルを保存
	 * - 成功またはエラーのメッセージを設定し、画面に通知
	 */
	@PostMapping("/uploadReceipt/{expensesID}")
	public String uploadReceipt(@PathVariable("expensesID") String expensesID,
	                            @RequestParam("receiptFile") MultipartFile file,
	                            RedirectAttributes redirectAttributes) {
	    try {
	        // ファイルのバリデーションを実施
	        validateFile(file);

	        // ファイルを保存
	        expenseListService.saveReceiptFile(expensesID, file);

	        // 処理成功時のメッセージ設定
	        redirectAttributes.addFlashAttribute("message", "ファイルのアップロードが完了しました。");
	    } catch (Exception e) {
	        e.printStackTrace();
	        // 処理失敗時のエラーメッセージ設定
	        redirectAttributes.addFlashAttribute("error", e.getMessage());
	    }
	    return "redirect:/expenseList";
	}
}
