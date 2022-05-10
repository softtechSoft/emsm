package com.softtech.controller;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.ContractInfoFormBean;
import com.softtech.actionForm.ExpensesBean;
import com.softtech.common.CompanyIDName;
import com.softtech.common.EmployeeIDName;
import com.softtech.entity.ContractInfoEntity;
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
		String employeeID = contractInfoBean.getEmployeeID();
		List<ContractInfoEntity> sList= contractInfoService.queryContractInfoList(employeeID);

		//社員IDリスト候補生成
		List<EmployeeIDName> contractList = loginService.getEmployeeList();
		//社員IDリスト候補を画面へ渡す
		model.addAttribute("contractList",contractList);
		model.addAttribute("contractInfoBean",contractInfoBean);
		model.addAttribute("list",sList);

		return "contractInfoList";

	}
	/*
	 * 機能概要：契約情報更新画面初期表示
	 * @paramater contractInfoBean リスト画面データ
	 * @param  model　画面へデータ渡す用
	 * @return  contractInfoEdit画面
	 * @exception　なし
	 * @author テー@it-softtech.com
	 */
	@RequestMapping("/toInitContractInfo")
	public String initContractInfoList(@ModelAttribute("contractInfoBean") ContractInfoFormBean contractInfoBean,
			Model model) {
		//　選択された契約の内容を取得する
		String contractID = contractInfoBean.getContractID();
		List<ContractInfoEntity> sList= contractInfoService.queryContractInfo(contractID);
		model.addAttribute("list",sList);

		//社員IDリスト候補生成
		List<EmployeeIDName> employeeList = loginService.getEmployeeList();
		model.addAttribute("employeeList",employeeList);

		//社員項目IDを任意設定
		contractInfoBean.setEmployeeID("1");

		//会社IDリスト候補生成
		List<CompanyIDName> companyList = loginService.getCompanyList();
		model.addAttribute("companyList",companyList);

		//会社項目IDを任意設定
		contractInfoBean.setCompanyID("1");

		model.addAttribute("contractInfoBean",contractInfoBean);
		return "contractInfoEdit";
	}
	/*
	 * 機能：契約情報登録(登録ボタン）
	 *
	 * @paramater contractInfoBean 画面データ
	 * @paramater　result　バリエーションチェック結果
	 * @paramater　session　セッション
	 * @paramater　model　画面へデータ渡す用
	 *
	 * @return  contractInfoEdit画面
	 * @exception　なし
	 *
	 * @author テー@ソフトテク
	 */
	@RequestMapping(value ="/contractInfoEdit", method = RequestMethod.POST)
	public String registContractInfo(@Valid @ModelAttribute("contractInfoBean") ExpensesBean expensesBean,
			BindingResult result,HttpSession session,Model model) {



		return "contractInfoEdit";
}
}


