package com.softtech.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
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
@SuppressWarnings("unused")
@Controller
public class BaseSalaryControl {
	static protected Logger logger = LogManager.getLogger(BaseSalaryControl.class);
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
		logger.info("start index()");
		//社員IDリスト候補生成
		List<EmployeeIDName> baseSalaryList = loginService.getEmployeeList();
		
		//年度をとる
		List<String> yearList = baseSalaryInfoService.getYearList();

		BaseSalaryInfoFormBean baseSalaryInfoBean = new BaseSalaryInfoFormBean();
		//社員項目IDを任意設定
//		baseSalaryInfoBean.setEmployeeID("1");
	    baseSalaryInfoBean.setEmployeeID("");
	    baseSalaryInfoBean.setSearchYear("");

		model.addAttribute("baseSalaryInfoBean",baseSalaryInfoBean);
		//社員IDリスト候補を画面へ渡す
		model.addAttribute("baseSalaryList",baseSalaryList);
		model.addAttribute("yearList", yearList);

		return "baseSalaryInfoList";


	}

	/*
	 * 機能概要：基本給情報リスト検索
	 *
	 * @param  model
	 */
	@RequestMapping("/baseSalaryInfoList")
	public String baseSalaryInfo(@ModelAttribute("baseSalaryInfoBean") BaseSalaryInfoFormBean baseSalaryInfoBean,Model model){

		logger.debug("debug test");
		logger.info("info test");
		logger.warn("warn test");
		logger.error("error test");
		String employeeID = baseSalaryInfoBean.getEmployeeID();
		String searchYear = baseSalaryInfoBean.getSearchYear();

		List<BaseSalaryInfoEntity> bList= baseSalaryInfoService.queryBaseSalaryInfoByCondition(employeeID,searchYear);

		//社員IDリスト候補生成
//		List<EmployeeIDName> baseSalaryList = loginService.getEmployeeID();
		List<EmployeeIDName> baseSalaryList = loginService.getEmployeeList();
		//年度をとる
		List<String> yearList = baseSalaryInfoService.getYearList();
		//社員IDリスト候補を画面へ渡す
		model.addAttribute("baseSalaryList",baseSalaryList);
		model.addAttribute("yearList", yearList);
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
	public String initBaseSalaryInfoList(@ModelAttribute("baseSalaryInfoBean") BaseSalaryInfoFormBean baseSalaryInfoBean,Model model) {
		String baseSalaryID = baseSalaryInfoBean.getBaseSalaryID();

		//新規フラグを取得
		String insertFlg = baseSalaryInfoBean.getInsertFlg();
		//新規の場合
		if("0".equals(insertFlg)) {
			BaseSalaryInfoFormBean baseSalaryInfoFormBean = new BaseSalaryInfoFormBean();
			 //获取当前时间，显示在页面上
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
            String format = formatter.format(now);

			//baseSalaryIDを採番する（既存の最大値＋１）
			String maxBaseSalaryID =baseSalaryInfoService.getNextBaseSalaryID();
			baseSalaryInfoFormBean.setBaseSalaryID(maxBaseSalaryID);
			//新規
			baseSalaryInfoFormBean.setInsertFlg(insertFlg);
			baseSalaryInfoFormBean.setInsertDate(format);
			baseSalaryInfoFormBean.setUpdateDate(format);
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


		return "baseSalaryInfoEdit";
	}

	/**
	 * 基本給情報更新と新規ボタン
	 */

	@RequestMapping(value ="/baseSalaryInfoEdit", method = RequestMethod.POST)
	public String registBaseSalaryInfo(@Valid @ModelAttribute("baseSalaryInfoBean") BaseSalaryInfoFormBean baseSalaryInfoBean,BindingResult result,HttpSession session,Model model) {

		//必須チェック
		if (result.hasErrors()) {
			 // エラーチェック用リスト
			List<FieldError> errorlst = new ArrayList<FieldError>();

			//エラーメッセージ。
			errorlst.addAll(result.getFieldErrors());
			//エラーメッセージ
			model.addAttribute("errors", errorlst);
			model.addAttribute("baseSalaryInfoBean",baseSalaryInfoBean);
			//社員IDリスト候補生成
			List<EmployeeIDName> employeeList = loginService.getEmployeeList();
			model.addAttribute("employeeList",employeeList);
			return "baseSalaryInfoEdit";
		}

		String insertFlg = baseSalaryInfoBean.getInsertFlg();
		//新規の場合
		if("0".equals(insertFlg)) {
			baseSalaryInfoService.insertBaseSalaryInfoDetail(baseSalaryInfoBean);
			model.addAttribute("successMessage", "登録完了");
		} else {
			//DBに更新入力
			baseSalaryInfoService.updateBaseSalaryInfoList(baseSalaryInfoBean);
			model.addAttribute("successMessage", "更新完了");
		}

		//社員IDリスト候補生成
		List<EmployeeIDName> employeeList = loginService.getEmployeeList();
		model.addAttribute("employeeList",employeeList);

		return "redirect:/baseSalaryInfoList";
//		return "baseSalaryInfoEdit";
	}
}