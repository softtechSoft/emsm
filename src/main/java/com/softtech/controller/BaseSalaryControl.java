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

import com.softtech.actionForm.BaseSalaryInfoFormBean;
import com.softtech.common.EmployeeIDName;
import com.softtech.entity.BaseSalaryInfoEntity;
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
	 * 機能概要：基本給情報リストの初期表示
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

	/*
	 * 機能概要：基本給情報リスト検索
	 *
	 * @param  model
	 */
	@RequestMapping("/baseSalaryInfoList")
	public String baseSalaryInfo(@ModelAttribute("baseSalaryInfoBean") BaseSalaryInfoFormBean baseSalaryInfoBean,Model model){

		String employeeID = baseSalaryInfoBean.getEmployeeID();

		List<BaseSalaryInfoEntity> bList= baseSalaryInfoService.queryBaseSalaryInfoList(employeeID);

		//社員IDリスト候補生成
		List<EmployeeIDName> baseSalaryList = loginService.getEmployeeID();
		//社員IDリスト候補を画面へ渡す
		model.addAttribute("baseSalaryList",baseSalaryList);
		model.addAttribute("baseSalaryInfoBean",baseSalaryInfoBean);
		model.addAttribute("list",bList);

		return "baseSalaryInfoList";

	}

	/*
	 * 機能概要：基本給情報更新画面の初期表示
	 *
	 * @param  model
	 */
	@RequestMapping("/toInitBaseSalaryInfo")
	public String initBaseSalaryInfoList(@ModelAttribute("baseSalaryInfoBean") BaseSalaryInfoFormBean baseSalaryInfoBean,
			Model model) {
		String baseSalaryID = baseSalaryInfoBean.getBaseSalaryID();

		//新規フラグを取得
		String insertFlg = baseSalaryInfoBean.getInsertFlg();
		//新規の場合
		if("0".equals(insertFlg)) {
			BaseSalaryInfoFormBean baseSalaryInfoFormBean = new BaseSalaryInfoFormBean();

			//baseSalaryIDを採番する（既存の最大値＋１）
			String maxBaseSalaryID =baseSalaryInfoService.getNextBaseSalaryID();
			baseSalaryInfoFormBean.setBaseSalaryID(maxBaseSalaryID);
			//新規
			baseSalaryInfoFormBean.setInsertFlg(insertFlg);
			model.addAttribute("baseSalaryInfoBean",baseSalaryInfoFormBean);

		//更新の場合
		} else {
			//選択された内容を取得する
			baseSalaryID = baseSalaryInfoBean.getBaseSalaryID();
			List<BaseSalaryInfoEntity> bList= baseSalaryInfoService.queryBaseSalaryInfo(baseSalaryID);

			BaseSalaryInfoFormBean baseSalaryInfoFormBean = baseSalaryInfoService.trasferEntityToUI(bList);
			//更新
			baseSalaryInfoFormBean.setInsertFlg(insertFlg);
			model.addAttribute("baseSalaryInfoBean",baseSalaryInfoFormBean);
		}


		//社員IDリスト候補生成
		List<EmployeeIDName> employeeList = loginService.getEmployeeList();
		model.addAttribute("employeeList",employeeList);
		//社員項目IDを任意設定
//		baseSalaryInfoBean.setEmployeeID("1");
//		//IDリスト候補生成
//		List<BaseSalaryIDName> baseSalaryList = loginService.getBaseSalaryList();
//		model.addAttribute("baseSalaryList",baseSalaryList);
//
//		//IDを任意設定
//		baseSalaryInfoBean.setBaseSalaryID("1");

		return "baseSalaryInfoEdit";
	}

	/**
	 * 基本給情報更新と新規ボタン
	 */

	@RequestMapping(value ="/baseSalaryInfoEdit", method = RequestMethod.POST)
	public String registBaseSalaryInfo(@Valid @ModelAttribute("baseSalaryInfoBean") BaseSalaryInfoFormBean baseSalaryInfoBean,
			BindingResult result,HttpSession session,Model model) {


		String insertFlg = baseSalaryInfoBean.getInsertFlg();
		//新規の場合
		if("0".equals(insertFlg)) {
			baseSalaryInfoService.insertBaseSalaryInfoDetail(baseSalaryInfoBean);
		} else {
			//DBに更新入力
			baseSalaryInfoService.updateBaseSalaryInfoList(baseSalaryInfoBean);
		}

		//社員IDリスト候補生成
		List<EmployeeIDName> employeeList = loginService.getEmployeeList();
		model.addAttribute("employeeList",employeeList);

		//社員項目IDを任意設定
		baseSalaryInfoBean.setEmployeeID("1");
		return "baseSalaryInfoEdit";
	}
}