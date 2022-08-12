package com.softtech.controller;

import java.util.ArrayList;
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

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.ExpensesActionForm;
import com.softtech.actionForm.ExpensesManagementBean;
import com.softtech.service.ExpensesManagementService;
import com.softtech.util.DateUtil;
@Controller
public class ExpensesManagementController {
	@Autowired
	ExpensesManagementService expensesManagementService;


	@RequestMapping(value = {"/initExpensesManagement"})
	public String initExpensesManagement(Model model) {
        //画面共通データ準備
		mkInitDate( model);

		ExpensesManagementBean expensesManagementBean = new ExpensesManagementBean();
		model.addAttribute("expensesManagementBean", expensesManagementBean);

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
	@RequestMapping(value ="expensesManagementSubmit", method = RequestMethod.POST)
	public String expensesManagementSubmit(@Valid @ModelAttribute("expensesManagementBean") ExpensesManagementBean expensesManagementBean,
			BindingResult result,HttpSession session,Model model) {

		 //画面共通データ準備
		mkInitDate( model);

		model.addAttribute("expensesManagementBean", expensesManagementBean);
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

		ArrayList<FieldError> errorlst = new ArrayList<FieldError>();
		FieldError err1 = new FieldError("", "", "登録しました。");
		errorlst.add(err1);

			// 発生日ではない場合、エラーメッセージ戻す
			String accrualDate=expensesManagementBean.getAccrualDate();
			if(!DateUtil.isDate(accrualDate)) {

				FieldError err2 = new FieldError("", "", "発生日に通常の日付を入力してください	。");
				errorlst.add(err2);
			}
			//精算日ではない場合、エラーメッセージ戻す
			String stmtlDate=expensesManagementBean.getStmtlDate();
			if(!DateUtil.isDate(stmtlDate)) {

				FieldError err3 = new FieldError("", "", "精算日に通常の日付を入力してください	。");
				errorlst.add(err3);
			}



        model.addAttribute("errors",errorlst);

        return "expensesManagement";
	}

	private void mkInitDate(Model model) {

		// DBから社員情報を取得する
		List<EmployeeActionForm> employeeList = expensesManagementService.queryEmployeeInfo();
		model.addAttribute("employeeList", employeeList);



		List<ExpensesActionForm> expensesList=new ArrayList<ExpensesActionForm>();

		ExpensesActionForm expensesAcctionForm1 = new ExpensesActionForm();
		expensesAcctionForm1.setExpensesID("1");
		expensesAcctionForm1.setExpensesName("種別1");
		expensesList.add(expensesAcctionForm1);

		ExpensesActionForm expensesAcctionForm2 = new ExpensesActionForm();
		expensesAcctionForm2.setExpensesID("2");
		expensesAcctionForm2.setExpensesName("通勤交通費");
		expensesList.add(expensesAcctionForm2);

		ExpensesActionForm expensesAcctionForm3 = new ExpensesActionForm();
		expensesAcctionForm3.setExpensesID("3");
		expensesAcctionForm3.setExpensesName("出張旅費");
		expensesList.add(expensesAcctionForm3);

		ExpensesActionForm expensesAcctionForm4 = new ExpensesActionForm();
		expensesAcctionForm4.setExpensesID("4");
		expensesAcctionForm4.setExpensesName("一般経費");
		expensesList.add(expensesAcctionForm4);

		ExpensesActionForm expensesAcctionForm5 = new ExpensesActionForm();
		expensesAcctionForm5.setExpensesID("5");
		expensesAcctionForm5.setExpensesName("接待交際費");
		expensesList.add(expensesAcctionForm5);

		ExpensesActionForm expensesAcctionForm6 = new ExpensesActionForm();
		expensesAcctionForm6.setExpensesID("6");
		expensesAcctionForm6.setExpensesName("会議費用");
		expensesList.add(expensesAcctionForm6);

		ExpensesActionForm expensesAcctionForm7 = new ExpensesActionForm();
		expensesAcctionForm7.setExpensesID("7");
		expensesAcctionForm7.setExpensesName("その他");
		expensesList.add(expensesAcctionForm7);

		model.addAttribute("expensesList", expensesList);


		List<ExpensesActionForm> expensesList1=new ArrayList<ExpensesActionForm>();
		ExpensesActionForm expensesAcctionForm = new ExpensesActionForm();
		expensesAcctionForm.setExpensesID("2");
		expensesAcctionForm.setExpensesName("種別2");
		expensesList1.add(expensesAcctionForm);


		ExpensesActionForm expensesAcctionForm8 = new ExpensesActionForm();
		expensesAcctionForm8.setExpensesID("8");
		expensesAcctionForm8.setExpensesName("家賃");
		expensesList1.add(expensesAcctionForm8);

		ExpensesActionForm expensesAcctionForm10 = new ExpensesActionForm();
		expensesAcctionForm10.setExpensesID("9");
		expensesAcctionForm10.setExpensesName("水道光熱費");
		expensesList1.add(expensesAcctionForm10);

		ExpensesActionForm expensesAcctionForm11 = new ExpensesActionForm();
		expensesAcctionForm11.setExpensesID("10");
		expensesAcctionForm11.setExpensesName("電話及びインターネット");
		expensesList1.add(expensesAcctionForm11);

		ExpensesActionForm expensesAcctionForm12 = new ExpensesActionForm();
		expensesAcctionForm12.setExpensesID("11");
		expensesAcctionForm12.setExpensesName("BizStation費用");
		expensesList1.add(expensesAcctionForm12);

		ExpensesActionForm expensesAcctionForm13 = new ExpensesActionForm();
		expensesAcctionForm13.setExpensesID("12");
		expensesAcctionForm13.setExpensesName("クレジットカード年会費");
		expensesList1.add(expensesAcctionForm13);

		ExpensesActionForm expensesAcctionForm14 = new ExpensesActionForm();
		expensesAcctionForm14.setExpensesID("13");
		expensesAcctionForm14.setExpensesName("AWS費用");
		expensesList1.add(expensesAcctionForm14);



		model.addAttribute("expensesList1", expensesList1);
	}

}
