package com.softtech.controller;



import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.BaseSalaryInfoFormBean;
import com.softtech.actionForm.ContractInfoFormBean;
import com.softtech.common.EmployeeIDName;
import com.softtech.entity.BaseSalaryInfoEntity;
import com.softtech.entity.ContractInfoEntity;
import com.softtech.service.BaseSalaryInfoService;
import com.softtech.service.LoginService;


/**
 * 概要：契約情報リスト初期表示
 *
 * 作成者：孫曄@ソフトテク
 * 作成日：2022/05/05
 *
 */
@Controller
public class BaseSalaryControl {
	// ログインサービス
	@Autowired
	LoginService loginService;

    // 基本給サービス
	@Autowired
	BaseSalaryInfoService baseSalaryInfoService;

	/*
	 * 機能概要：契約情報リストの初期表示
	 *
	 * @param  model
	 */
	//データ画面の設定
	@RequestMapping("/initBaseSalaryList")
	public String toinitBaseSalaryList(Model model) {
		//社員IDリスト候補生成
		List<EmployeeIDName> baseSalaryList = loginService.getEmployeeList();

		BaseSalaryInfoFormBean baseSalaryInfoBean = new BaseSalaryInfoFormBean();
		//社員項目IDを任意設定
		baseSalaryInfoBean.setEmployeeID("1");

		model.addAttribute("baseSalaryInfoBean",baseSalaryInfoBean);
		//社員IDリスト候補を画面へ渡す
		model.addAttribute("baseSalaryList",baseSalaryList);

		return "baseSalaryInfoList";


	}

	//データ画面検索
	@RequestMapping("/baseSalaryInfoList")
	public String baseSalaryInfo(@ModelAttribute("baseSalaryInfoBean") BaseSalaryInfoFormBean baseSalaryInfoBean,
			Model model) {

		String employeeID = baseSalaryInfoBean.getEmployeeID();

		List<BaseSalaryInfoEntity> bList= baseSalaryInfoService.queryBaseSalaryInfoList(employeeID);

		//社員IDリスト候補生成
		List<EmployeeIDName> baseSalaryList = loginService.getEmployeeList();
		//社員IDリスト候補を画面へ渡す
		model.addAttribute("baseSalaryList",baseSalaryList);
		model.addAttribute("baseSalaryInfoBean",baseSalaryInfoBean);
		model.addAttribute("list",bList);

				return "baseSalaryInfoList";

	}

	//更新画面の検索

	@RequestMapping("/toinitBaseSalaryInfo")
	public String initBaseSalaryInfoList(@ModelAttribute("baseSalaryInfoBean") BaseSalaryInfoFormBean baseSalaryInfoBean,
			Model model) {
		String baseSalaryID = baseSalaryInfoBean.getBaseSalaryID();
		List<BaseSalaryInfoEntity> bList= baseSalaryInfoService.queryBaseSalaryInfo(baseSalaryID);
		model.addAttribute("list",bList);
		model.addAttribute("baseSalaryInfoBean",baseSalaryInfoBean);
		return "baseSalaryInfoEdit";
}
}