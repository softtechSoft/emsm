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

import com.softtech.actionForm.ExpensesManagementBean;
import com.softtech.service.ExpensesManagementService;
@Controller
public class ExpensesManagementController {
	@Autowired
	ExpensesManagementService expensesManagementService;


	@RequestMapping(value = {"/initExpensesManagement"})
	public String initExpensesManagement(Model model) {

		model.addAttribute("expensesManagementBean", new ExpensesManagementBean());
		//社員情報テーブルから、社員IDおよび氏名を取得
		List<ExpensesManagementBean> expensesManagement = expensesManagementService.queryEmployeeInfo();
		model.addAttribute("employee",expensesManagement);

		return "expensesManagement";

	}

	/*
	 * 機能：経費登録(登録ボタン）
	 *
	 * @paramater　expensesManagementBean　画面データ
	 * @paramater　result　バリエーションチェック結果
	 * @paramater　session　セッション0
	 * @paramater　model　画面へデータ渡す用
	 *
	 * @return  経費画面
	 * @exception　なし
	 *
	 * @author @ソフトテク
	 */
	@RequestMapping(value ="/expensesManagementSubmit", method = RequestMethod.POST)
	public String expensesManagementSubmit(@Valid @ModelAttribute("expensesManagementBean") ExpensesManagementBean expensesManagementBean,
			BindingResult result,HttpSession session,Model model) {

		//必須チェック
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getFieldErrors());
			return "expensesManagement";
		}

		//数字チェック
		 List<FieldError> errors = expensesManagementService.chkNumberData(expensesManagementBean);
		 // エラーがある場合
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "expensesManagement";
		}

		//日付チェック
		 errors = expensesManagementService.chkDate(expensesManagementBean);
		 // エラーがある場合
		if (errors.size() > 0) {
			model.addAttribute("errors", errors);
			return "expensesManagement";
		}

		//DB登録
		expensesManagementService.insertExpensesManagement(expensesManagementBean);
		return "expensesManagement";
	}



}
