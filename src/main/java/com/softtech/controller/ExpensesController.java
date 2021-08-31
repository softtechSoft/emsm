package com.softtech.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.ExpensesBean;
import com.softtech.service.ExpensesService;

/**
 * 概要：一般経費作成
 *
 * 作成者：劉
 * 作成日：2021/08/12
 */
@Controller
public class ExpensesController {
	@Autowired
	ExpensesService expensesService;

	/**
	 * 機能：初期画面を表示（一般経費ボタン）
	 *
	 * @return expenses
	 * @author 劉
	 */
	@RequestMapping("/expenses")
	public String toExpenses(Model model) {

		model.addAttribute("expensesBean", new ExpensesBean());
		return "expenses";
	}

	/*
	 * 機能：一般経費登録(登録ボタン）
	 *
	 * @paramater　expensesBean　画面データ
	 * @paramater　result　バリエーションチェック結果
	 * @paramater　session　セッション
	 * @paramater　model　画面へデータ渡す用
	 *
	 * @return  一般経費画面
	 * @exception　なし
	 *
	 * @author 劉@ソフトテク
	 */
	@RequestMapping(value ="/expensesRegit", method = RequestMethod.POST)
	public String registExpenses(@Valid @ModelAttribute("expensesBean") ExpensesBean expensesBean,
			BindingResult result,HttpSession session,Model model) {

		//必須チェック
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getFieldErrors());
			return "expenses";
		}

		//数字チェック
		 List<FieldError> errors = expensesService.chkNumberData(expensesBean);
		 // エラーがある場合
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "expenses";
		}

		//日付チェック
		 errors = expensesService.chkDate(expensesBean);
		 // エラーがある場合
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "expenses";
		}

		//DB登録
		expensesService.insertExpensesDetail(expensesBean);
		return "expenses";
	}

}
