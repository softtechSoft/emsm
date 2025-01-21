package com.softtech.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softtech.entity.ExpenseListEntity;
import com.softtech.service.ExpenseInfoService;

@Controller
@RequestMapping("/expenseInfo")
public class ExpenseInfoController {

	@Autowired
	private ExpenseInfoService expenseInfoService;

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
}
