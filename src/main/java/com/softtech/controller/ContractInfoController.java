package com.softtech.controller;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.ContractInfoBean;
import com.softtech.actionForm.ContractInfoFormBean;
import com.softtech.common.EmployeeIDName;
import com.softtech.service.ContractInfoService;
import com.softtech.service.LoginService;

/**
 * 概要：契約情報リスト初期表示
 *
 * 作成者：テー@ソフトテク
 * 作成日：2022/4/25
 *
 */
@Controller
public class ContractInfoController {
	// ログインサービス
	@Autowired
	LoginService loginService;

    // 契約情報サービス
	@Autowired
	ContractInfoService contractInfoService;

	/*
	 * 機能概要：契約情報リストの初期表示
	 *
	 * @param  model
	 * @author テー@it-softtech.com
	 *
	 */
	@RequestMapping("/initContractInfoList")
	public String toinitContractInfoList(Model model) {
		//社員IDリスト候補生成
		List<EmployeeIDName> contractList = loginService.getEmployeeList();

		ContractInfoFormBean contractInfoBean = new ContractInfoFormBean();
		//社員項目IDを任意設定
		contractInfoBean.setEmployeeID("1");

		model.addAttribute("contractInfoBean",contractInfoBean);
		//社員IDリスト候補を画面へ渡す
		model.addAttribute("contractList",contractList);

		return "contractInfoList";

	}
	/*
	 * 機能概要：契約情報検索
	 *
	 * @param  model
	 * @author テー@it-softtech.com
	 *
	 */
	@RequestMapping("/contractInfoList")
	public String contractInfo(@ModelAttribute("contractInfoBean") ContractInfoFormBean contractInfoBean,
								Model model) {
		// 対象社員IDを取得する
		String emplyeeID = contractInfoBean.getEmployeeID();
		List<ContractInfoBean> sList= contractInfoService.queryContractInfoList(emplyeeID);

		//社員IDリスト候補生成
		List<EmployeeIDName> contractList = loginService.getEmployeeList();
		//社員IDリスト候補を画面へ渡す
		model.addAttribute("contractList",contractList);
		model.addAttribute("contractInfoBean",contractInfoBean);

		return "contractInfoList";

	}




}