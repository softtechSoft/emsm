package com.softtech.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.service.SalaryInfoService;
import com.softtech.service.WelfareListService;

/**
 * 概要：給料作成機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/6/02
 */
@Controller
public class SalaryInfoController {
	@Autowired
	SalaryInfoService salaryInfoService;
	@Autowired
	WelfareListService welfareListService;
	/*
	 * 機能概要： 給料データの作成処理。
	 *
	 * @param  salaryInfoBean 画面入力値
	 * @throws ParseException
	 */
	@RequestMapping(value = "salaryInfo", method = RequestMethod.POST )
	public String toWorkDetailList(@Valid @ModelAttribute("SalaryInfoBean") SalaryInfoBean salaryInfoBean,
									BindingResult result, Model model) throws ParseException {

		if("1".equals(salaryInfoBean.getMake())) {
			//現在、何にもやってない。

		//登録ボタンを押す
		}else if("2".equals(salaryInfoBean.getMake())) {
			// 数値に変更
			salaryInfoService.deleteComma(salaryInfoBean);
			// 必須チェック
			if (result.hasErrors()) {
			    // エラーチェック用リスト
				List<FieldError> errorlst = new ArrayList<FieldError>();
				//エラーメッセージ。
				errorlst.addAll(result.getFieldErrors());
				//エラーメッセージ
				model.addAttribute("errors", errorlst);

				model.addAttribute("salaryInfoBean",salaryInfoBean);

				return "salaryInfo";
			 }

			//その他チェック
			List<FieldError> fieldErrors = salaryInfoService.checkData(salaryInfoBean);
			// エラーがある場合
			if(fieldErrors.size()>0) {
				model.addAttribute("errors", fieldErrors);
				model.addAttribute("salaryInfoBean",salaryInfoBean);

				return "salaryInfo";
			}

			// 給料情報更新
			salaryInfoService.updateSalaryInfo(salaryInfoService.transferToDB(salaryInfoBean));
			salaryInfoService.addComma(salaryInfoBean);
			//更新成功後、画面の表示。
			List<FieldError> fieldSuccess = salaryInfoService.getSuccessMessage();
			if(fieldSuccess.size()>0) {
				model.addAttribute("errors", fieldSuccess);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
		//計算1
		} else if("3".equals(salaryInfoBean.getMake())) {
			// 数値に変更
			salaryInfoService.deleteComma1(salaryInfoBean);
			//再計算
			salaryInfoService.calSalary1(salaryInfoBean);

			model.addAttribute("salaryInfoBean",salaryInfoBean);
		//計算2
		} else if("4".equals(salaryInfoBean.getMake())) {
			// 数値に変更
			salaryInfoService.deleteComma(salaryInfoBean);
			//再計算
			salaryInfoService.calSalary2(salaryInfoBean);

			model.addAttribute("salaryInfoBean",salaryInfoBean);
		}

		return "salaryInfo";
	}
}
