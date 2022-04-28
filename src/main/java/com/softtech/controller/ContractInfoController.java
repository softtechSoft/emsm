package com.softtech.controller;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.ContractInfoBean;
import com.softtech.actionForm.ContractInfoFormBean;
import com.softtech.common.EmployeeIDName;
import com.softtech.service.ContractInfoService;

/**
 * 概要：契約情報リスト初期表示
 *
 * 作成者：テー@ソフトテク
 * 作成日：2022/4/25
 *
 */
@Controller
public class ContractInfoController {

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
		List<EmployeeIDName> contractList = new ArrayList<EmployeeIDName>();
		EmployeeIDName employeeIDName1 = new EmployeeIDName();
		employeeIDName1.setId(1);
		employeeIDName1.setUserID("E001");
		contractList.add(employeeIDName1);

		EmployeeIDName employeeIDName2 = new EmployeeIDName();
		employeeIDName2.setId(2);
		employeeIDName2.setUserID("E002");
		contractList.add(employeeIDName2);

		ContractInfoFormBean contractInfoBean = new ContractInfoFormBean();
		contractInfoBean.setId(1);
		//contractInfoBean.setEmployeeIDNameList(contractList);
		model.addAttribute("contractInfoBean",contractInfoBean);
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

	@RequestMapping("/ContractInfoList")
	public String contractInfo(@Valid @ModelAttribute("contractInfoBean") ContractInfoFormBean contractInfoBean,
		BindingResult result,HttpSession session,Model model) {


			String employeeID = (String) session.getAttribute("userEmoplyeeID");
			List<ContractInfoBean> sList= ContractInfoService.queryContractInfoList(employeeID);
					// 入力にエラーある場合、画面にエラーを表示する。
				if (result.hasErrors()) {
					model.addAttribute("errors", result.getFieldErrors());
					return "login";
				}

		model.addAttribute("ContractInfoList",sList);
		model.addAttribute("contractInfoBean",contractInfoBean);

			return "contractInfoList";

	}




}