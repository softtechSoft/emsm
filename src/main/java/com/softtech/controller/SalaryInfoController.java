package com.softtech.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.SalarylistBean2;
import com.softtech.common.SalaryInfocommom;
import com.softtech.entity.SalaryInfo;
import com.softtech.service.SalaryInfoService;
import com.softtech.util.DateUtil;

/**
 * 概要：給料作成機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/5/22
 */
@Controller
public class SalaryInfoController {
	@Autowired
	SalaryInfoService salaryInfoService;

	@RequestMapping(value = "salaryInfo", method = RequestMethod.POST )
	public String toWorkDetailList(@Valid @ModelAttribute("salaryInfo2") SalarylistBean2 salarylistBean2, Model model) {
		// 作成ボタンをおした、inputが可入力。
		model.addAttribute("loadFlg", salarylistBean2.getLoadFlg());
		SalaryInfocommom em = new SalaryInfocommom();
		 	// 対象年月+1をする。
		   int a = Integer.parseInt(DateUtil.chgMonthToYM(salarylistBean2.getMonth()))+1;
		   String s=String.valueOf(a);
			//社員ID
			em.setEmployeeID(salarylistBean2.getEmployeeIDFlg());
			//対象年月
			em.setMonth(s);
			// DBから給料作成情報を取得
			List<SalaryInfo> ss= salaryInfoService.querySalaryInfo(em);
			model.addAttribute("month", s);
			if(null == ss || ss.size() ==0) {
				String cc = "作成";
				model.addAttribute("button",cc);
				//作成場合
				List<SalaryInfo> dd= salaryInfoService.querySalaryInfo1(salarylistBean2.getEmployeeIDFlg());
				model.addAttribute("salaryInfo",dd);
				String ee = "新規追加";
				model.addAttribute("cFlg",ee);
			}else {
				String bb = "変更";
				model.addAttribute("button",bb);
				//変更場合
				model.addAttribute("salaryInfo",ss);
				String ff = "更新";
				model.addAttribute("cFlg",ff);
			}
			//登録ボタンを押す時
			if(salarylistBean2.getB()=="新規追加") {
				return "hello";

			}else if(salarylistBean2.getB()=="更新") {
				return "hello2";

			}
		return "salaryInfo";
	}
}
