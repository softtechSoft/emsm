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

import com.softtech.actionForm.SalarylistBean2;
import com.softtech.common.SalaryInfocommom;
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
public class SalarylistController {

	@Autowired
	SalarylistService salarylistService;
	@Autowired
	SalaryInfoService salaryInfoService;

	/**
	 *    ログイン画面初期処理
	 *       ログイン画面へ遷移する。
	 * @param  モデル
//	 */
	@RequestMapping(value = {"/salarylist"})
	public String home(Model model) {
		//現在年月取得
		String month = DateUtil.getNowMonth();
		model.addAttribute("month",month);
		// DBから給料情報を取得
		List<SalaryInfo> sl= salarylistService.querySalarylist(month);
		model.addAttribute("list",sl);


		//画面初期化
//		List<SalarylistBean> salarylistBean1 = new ArrayList<SalarylistBean>();
//		SalarylistBean slb = new SalarylistBean();
//		slb.setEmployeeID("01");
//		slb.setEmployeeName("晴子");
//		slb.setMonth("202104");
//		slb.setPaymentDate("20210430");
//		slb.setBase("300000");
//		slb.setSum("320000");
//		slb.setRemark("");
//		salarylistBean1.add(slb);
//
//		SalarylistBean slb2 = new SalarylistBean();
//		slb2.setEmployeeID("02");
//		slb2.setEmployeeName("夏子");
//		slb2.setMonth("202105");
//		slb2.setPaymentDate("20210530");
//		slb2.setBase("320000");
//		slb2.setSum("340000");
//		slb2.setRemark("");
//		salarylistBean1.add(slb2);
//
//		model.addAttribute("list", salarylistBean1);

		return "salarylist";
	}

	@RequestMapping(value = "salarylist", method = RequestMethod.POST)
	public String SalarylistSubmit(HttpServletResponse response, @Valid SalarylistBean2 salarylistBean2,
			BindingResult result,SalaryInfo salaryInfo, Model model) {
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
			  // 給料作成場合
		 }else if(salarylistBean2.getDownloadFlg()==3) {
			 SalaryInfocommom em = new SalaryInfocommom();
				//社員ID
				em.setEmployeeID(salarylistBean2.getEmployeeIDFlg());
				//対象年月と対象年月YYYY/MM→yyyymmに変換
				em.setMonth(DateUtil.chgMonthToYM(salarylistBean2.getMonth()));
				// DBから給料作成情報を取得
				List<SalaryInfo> ss= salaryInfoService.querySalaryInfo(em);
				String s = salarylistBean2.getMonth();
				String ret = s.substring(0,4)+"/"+s.substring(4,6);
				String cc = "作成";
				model.addAttribute("month", ret);
				model.addAttribute("salaryInfo",ss);
				model.addAttribute("button",cc);
		        return "salaryInfo";
		 }
		return "salarylist";
	}
}








