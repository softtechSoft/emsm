package com.softtech.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.actionForm.SalarylistBean2;
import com.softtech.common.SalaryInfoRecord;
import com.softtech.entity.SalaryInfo;
import com.softtech.service.SalaryInfoService;
import com.softtech.service.SalarylistService;
import com.softtech.util.DateUtil;
import com.softtech.util.FileUtil;
/**
 *  Menu処理する。
 *
 * @author Softtech
 * @since  2021/04/10
 */
@Controller
//@RequestMapping("/emsm")
public class SalaryListController {

	@Autowired
	SalarylistService salarylistService;
	@Autowired
	SalaryInfoService salaryInfoService;

	/**
	 *    給料リスト画面初期処理
	 *    給料リスト画面へ遷移する。
	 * @param  モデル
	 */
	@RequestMapping(value = {"/salarylist"})
	public String home(Model model) {
		//現在年月取得
		String month = DateUtil.getNowMonth();
		model.addAttribute("month",month);
		// DBから給料情報を取得
		List<SalaryInfo> sl= salarylistService.querySalarylist(month);
		model.addAttribute("list",sl);
		return "salarylist";
	}
	/**
	 *   給料リスト処理。
	 *   給料リスト処理。
	 * @param  モデル
	 */
	@RequestMapping(value = "salarylist", method = RequestMethod.POST)
	public String SalarylistSubmit(HttpServletResponse response, @Valid SalarylistBean2 salarylistBean2,
			BindingResult result, Model model) {
		// NotNullの入力した年月をチェック。
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getFieldErrors());
					return "salarylist";
				 }
        model.addAttribute("month",salarylistBean2.getMonth());
		// 入力した年月を持っち、DBから給料情報を取得
	     List<SalaryInfo> sl = salarylistService.querySalarylist(salarylistBean2.getMonth());

		// データダウンロード場合
		if(salarylistBean2.getDownloadFlg()==2){
			 FileUtil ft = new FileUtil();
			 boolean rtn = ft.salaryDownload(response,sl);
			 if(!rtn) {
				 // エラーメッセージを設定して、画面表示
			 }else {
				 //画面表示用データを設定する。
//				 model.addAttribute("month", salarylistBean2.getMonth());
			 }
			 // 検索する場合
		 } else if(salarylistBean2.getDownloadFlg()==1){
			     model.addAttribute("list", sl);
		 // 画面の給料リスト中の社員IDを押す時。

		 } else if(salarylistBean2.getDownloadFlg()==4) {
			 model.addAttribute("list",sl);
			 model.addAttribute("create","作成完了しました。");



		 }else if(salarylistBean2.getDownloadFlg()==3) {
			 SalaryInfoRecord em = new SalaryInfoRecord();
			//社員ID
			em.setEmployeeID(salarylistBean2.getEmployeeIDFlg());
			//対象年月と対象年月YYYY/MM→yyyymmに変換
			em.setMonth(DateUtil.chgMonthToYM(salarylistBean2.getMonth()));
			// DBから社員IDの対象年月の給料情報を取得
			SalaryInfo salaryInfoDB= salaryInfoService.querySalaryInfo(em);
			SalaryInfoBean salaryInfoBean=salaryInfoService.transferToGamen(salaryInfoDB);
			// 初期モードに設定
			salaryInfoBean.setGamenMode("0");
			model.addAttribute("salaryInfoBean",salaryInfoBean);

		    return "salaryInfo";
		 }
		return "salarylist";
	}
}








