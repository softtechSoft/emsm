package com.softtech.controller;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.ContractInfoFormBean;
import com.softtech.common.CompanyIDName;
import com.softtech.common.EmployeeIDName;
import com.softtech.entity.ContractInfoEntity;
import com.softtech.service.ContractInfoService;
import com.softtech.service.LoginService;
import com.softtech.util.DateUtil;
/**
 * 概要：契約情報リスト初期表示
 *
 * 作成者：テー@ソフトテク
 * 作成日：2022/4/25
 *
 */
@Controller
public class ContractInfoController {
	static protected Logger logger = LogManager.getLogger(ContractInfoController.class);
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
		  logger.info("start index()");
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

		//新規フラグを取得
		String insertFlg = contractInfoBean.getInsertFlg();
		//新規の場合
		if("0".equals(insertFlg)) {
			ContractInfoFormBean contractInfoFormBean = new ContractInfoFormBean();

			// 契約情報を採番する（既存の最大値＋１）
			String maxContractID =contractInfoService.getNextContractID();
			contractInfoFormBean.setContractID(maxContractID);
			//新規
			contractInfoFormBean.setInsertFlg(insertFlg);


			contractInfoFormBean.setContractBeginDateS(DateUtil.getNowYear());


			//contractInfoFormBean.setContractBeginDate(Date.valueOf(getNowYear()));


			model.addAttribute("contractInfoBean",contractInfoFormBean);

		//更新の場合
		} else {
			//　選択された契約の内容を取得する
			String contractID = contractInfoBean.getContractID();
			List<ContractInfoEntity> sList= contractInfoService.queryContractInfo(contractID);

			ContractInfoFormBean contractInfoFormBean=contractInfoService.trasferEntityToUI(sList);
			//更新
			contractInfoFormBean.setInsertFlg(insertFlg);

			model.addAttribute("contractInfoBean",contractInfoFormBean);
		}
		return "contractInfoEdit";
	}

	/*
	 * 機能：契約情報登録(登録ボタン）
	 *
	 * @paramater contractInfoBean 画面データ
	 * @paramater　result　バリエーションチェック結果
	 *
	 * @paramater　model　画面へデータ渡す用
	 *
	 * @return  contractInfoEdit画面
	 * @exception　なし
	 *
	 * @author テー@ソフトテク
	 */
	@RequestMapping(value ="/contractInfoEdit", method = RequestMethod.POST)
	public String updateContractInfo(@Validated@ModelAttribute("contractInfoBean") ContractInfoFormBean contractInfoBean,
			BindingResult result,Model model) {

//		//必須チェック
//		if (result.hasErrors()) {
//			//社員IDリスト候補生成
//			List<EmployeeIDName> employeeList = loginService.getEmployeeList();
//			model.addAttribute("employeeList",employeeList);
//
//			//社員項目IDを任意設定
//			contractInfoBean.setEmployeeID("1");
//
//			//会社IDリスト候補生成
//			List<CompanyIDName> companyList = loginService.getCompanyList();
//			model.addAttribute("companyList",companyList);
//
//			//会社項目IDを任意設定
//			contractInfoBean.setCompanyID("1");
//			model.addAttribute("errors", result.getAllErrors());
//			return "contractInfoEdit";
//		}
		//数字チェック
//		 List<FieldError> errors = contractInfoService.chkNumberData(contractInfoBean);
//		 // エラーがある場合
//		if (errors.size() > 0) {
//			model.addAttribute("errors", errors);
//			return "contractInfoEdit";
//		}

		//日付チェック
//
//		List<FieldError>errors = contractInfoService.chkDate(contractInfoBean);
//		 // エラーがある場合
//		if (errors.size() > 0) {
//			model.addAttribute("errors", errors);
//			return "contractInfoEdit";
//		}

		//新規フラグを取得l
		String insertFlg = contractInfoBean.getInsertFlg();
		//新規の場合
		if("0".equals(insertFlg)) {
			contractInfoService.insertContractInfoDetail(contractInfoBean);
		} else {
			//DB登録
			contractInfoService.updateContractInfoDetail(contractInfoBean);
		}


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
		return "contractInfoEdit";
	}

}



