package com.softtech.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.softtech.entity.ExpenseListEntity;
import com.softtech.service.ExpenseListService;

/**
 * 経費リストの管理を行うコントローラクラス。
 * 経費の表示、検索、削除、および更新機能を提供する。
 */
@Controller
@RequestMapping("/expenseList")
public class ExpenseListController {

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
	 * 指定された経費IDの経費を論理削除する。
	 *
	 * @param expensesID 削除対象の経費ID
	 * @return 経費リストのリダイレクトURL
	 */
	@GetMapping("/delete/{expensesID}")
	public String delete(@PathVariable("expensesID") String expensesID) {
		expenseListService.deleteExpense(expensesID);
		return "redirect:/expenseList";
	}

	/**
	 * 経費データを更新するためのインライン編集機能。
	 * AJAXリクエストを受け取り、経費データを更新する。
	 *
	 * @param expense 更新対象の経費エンティティ
	 * @return 更新結果のメッセージ
	 */
	@PostMapping("/update")
	@ResponseBody
	public ResponseEntity<String> updateExpense(@RequestBody ExpenseListEntity expense) {
		try {
			// 更新対象の経費情報をログに出力
			System.out.println("===== updateExpense =====");
			System.out.println("expensesID       = " + expense.getExpensesID());
			System.out.println("accrualDate      = " + expense.getAccrualDate());
			System.out.println("cost             = " + expense.getCost());
			System.out.println("happenAddress    = " + expense.getHappenAddress());
			System.out.println("tantouName       = " + expense.getTantouName());
			System.out.println("settlementDate   = " + expense.getSettlementDate());
			System.out.println("settlementType   = " + expense.getSettlementType());
			System.out.println("expensesType     = " + expense.getExpensesType());

			// 経費データを更新
			expenseListService.updateExpense(expense);

			return ResponseEntity.ok("更新成功");
		} catch (Exception e) {
			// エラー発生時にスタックトレースを出力し、エラーメッセージを返す
			e.printStackTrace();
			return ResponseEntity.badRequest().body("更新失敗: " + e.getMessage());
		}
	}
}
