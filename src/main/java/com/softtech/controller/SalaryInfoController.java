package com.softtech.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.SalarylistBean3;
import com.softtech.common.SalaryInfocommom;
import com.softtech.common.SalaryInfocommom2;
import com.softtech.entity.SalaryInfo;
import com.softtech.service.SalaryInfoService;
import com.softtech.util.DateUtil;

/**
 * 概要：給料作成機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/6/02
 */
@Controller
public class SalaryInfoController {
	@Autowired
	SalaryInfoService salaryInfoService;

	@RequestMapping(value = "salaryInfo", method = RequestMethod.POST )
	public String toWorkDetailList(HttpServletResponse response,@Valid @ModelAttribute("SalarylistBean3") SalarylistBean3 salarylistBean3,
			BindingResult result, Model model) {
		if(salarylistBean3.getMake().equals("2")) {
		    // 作成ボタンをおした、inputが可入力。
			model.addAttribute("loadFlg", salarylistBean3.getLoadFlg());
			SalaryInfocommom em = new SalaryInfocommom();
		 	// 対象年月+1をする。
		    int a = Integer.parseInt(DateUtil.chgMonthToYM(salarylistBean3.getMonth()))+1;
		    String s=String.valueOf(a);
			//社員ID
			em.setEmployeeID(salarylistBean3.getEmployeeIDFlg());
			//対象年月
			em.setMonth(s);
			// DBから給料作成情報を取得
			List<SalaryInfo> ss= salaryInfoService.querySalaryInfo(em);
			String ret = s.substring(0,4)+"/"+s.substring(4,6);
			model.addAttribute("month", ret);
			if(null == ss || ss.size() ==0) {
				String cc = "作成";
				model.addAttribute("button",cc);
				//作成場合
				List<SalaryInfo> dd= salaryInfoService.querySalaryInfo1(salarylistBean3.getEmployeeIDFlg());
				model.addAttribute("salaryInfo",dd);
				String k="1";
				model.addAttribute("MakeDistinction",k);
			}else {
				String bb = "変更";
				model.addAttribute("button",bb);
				//変更場合
				model.addAttribute("salaryInfo",ss);
				String o="2";
				model.addAttribute("MakeDistinction",o);
			}
				//登録ボタンを押す
		}else if(salarylistBean3.getMake().equals("1")) {
			// NotNullチェック。
			if (result.hasErrors()) {
				// 作成ボタンをおした、inputが可入力。
				model.addAttribute("loadFlg", salarylistBean3.getLoadFlg());
				//エラーメッセージ。
				model.addAttribute("errors", result.getFieldErrors());
				SalaryInfocommom sm = new SalaryInfocommom();
				//社員ID
				sm.setEmployeeID(salarylistBean3.getEmployeeIDb());
				//対象年月
				sm.setMonth(DateUtil.chgMonthToYM(salarylistBean3.getMonth()));
				List<SalaryInfo> ss= salaryInfoService.querySalaryInfo(sm);
				String u=DateUtil.chgMonthToYM(salarylistBean3.getMonth());
				String ret = u.substring(0,4)+"/"+u.substring(4,6);
				model.addAttribute("month", ret);
				//エラー処理用リスト
				List<SalarylistBean3> rtn =new ArrayList<SalarylistBean3>();
				//作成場合.社員情報リスト
				List<SalaryInfo> dd= salaryInfoService.querySalaryInfo1(salarylistBean3.getEmployeeIDb());
				SalarylistBean3 salarylistBean4=new SalarylistBean3();
				for(SalaryInfo wk : dd) {
					salarylistBean4.setEmployeeID(wk.getEmployeeID());
					salarylistBean4.setMonth((wk.getMonth()));
					salarylistBean4.setAddress((wk.getAddress()));
				}
					//作成場合.画面から入力した情報
					salarylistBean4.setPaymentDate(salarylistBean3.getPaymentDate());
					salarylistBean4.setBase(salarylistBean3.getBase());
					salarylistBean4.setOverTimePlus(salarylistBean3.getOverTimePlus());
					salarylistBean4.setShortageReduce(salarylistBean3.getShortageReduce());
					salarylistBean4.setTransportExpense(salarylistBean3.getTransportExpense());
					salarylistBean4.setAllowancePlus(salarylistBean3.getAllowancePlus());
					salarylistBean4.setAllowanceReduce(salarylistBean3.getAllowanceReduce());
					salarylistBean4.setAllowanceReason(salarylistBean3.getAllowanceReason());
					salarylistBean4.setWelfarePensionSelf(salarylistBean3.getWelfarePensionSelf());
					salarylistBean4.setWelfareHealthSelf(salarylistBean3.getWelfareHealthSelf());
					salarylistBean4.setEplyInsSelf(salarylistBean3.getEplyInsSelf());
					salarylistBean4.setWithholdingTax(salarylistBean3.getWithholdingTax());
					salarylistBean4.setMunicipalTax(salarylistBean3.getMunicipalTax());
					salarylistBean4.setRental(salarylistBean3.getRental());
					salarylistBean4.setRentalMgmtFee(salarylistBean3.getRentalMgmtFee());
					salarylistBean4.setSum(salarylistBean3.getSum());
					salarylistBean4.setRemark(salarylistBean3.getRemark());
					salarylistBean4.setTotalFee(salarylistBean3.getTotalFee());
					salarylistBean4.setWkAcccpsIns(salarylistBean3.getWkAcccpsIns());
					salarylistBean4.setEplyInsWithdraw(salarylistBean3.getEplyInsWithdraw());
					salarylistBean4.setEplyInsComp(salarylistBean3.getEplyInsComp());
					salarylistBean4.setWelfareBaby(salarylistBean3.getWelfareBaby());
					salarylistBean4.setWelfarePensionComp(salarylistBean3.getWelfarePensionComp());
					salarylistBean4.setWelfareHealthComp(salarylistBean3.getWelfareHealthComp());
					salarylistBean4.setOverTime(salarylistBean3.getOverTime());
					salarylistBean4.setShortage(salarylistBean3.getShortage());
					salarylistBean4.setWelfareSelf(salarylistBean3.getWelfareSelf());
					salarylistBean4.setWelfareComp(salarylistBean3.getWelfareComp());
					rtn.add(salarylistBean4);
					//作成場合
				if(null == ss || ss.size() ==0) {
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
				return "salaryInfo";
				//登録ボタンを押す時.作成場合
			 }else if(salarylistBean3.getMakeDistinction().equals("1") ) {
				SalaryInfocommom2 am = new SalaryInfocommom2();
				am.setEmployeeID(salarylistBean3.getEmployeeIDb());
				am.setMonth(DateUtil.chgMonthToYM(salarylistBean3.getMonth()));
				am.setPaymentDate(DateUtil.chgMonthToYM(salarylistBean3.getPaymentDate()));
				am.setBase(DateUtil.chgMonthToYM1(salarylistBean3.getBase()));
				am.setOverTimePlus(salarylistBean3.getOverTimePlus());
				am.setShortageReduce(salarylistBean3.getShortageReduce());
				am.setTransportExpense(salarylistBean3.getTransportExpense());
				am.setAllowancePlus(salarylistBean3.getAllowancePlus());
				am.setAllowanceReduce(salarylistBean3.getAllowanceReduce());
				am.setAllowanceReason(salarylistBean3.getAllowanceReason());
				am.setWelfarePensionSelf(salarylistBean3.getWelfarePensionSelf());
				am.setWelfareHealthSelf(salarylistBean3.getWelfareHealthSelf());
				am.setEplyInsSelf(salarylistBean3.getEplyInsSelf());
				am.setWithholdingTax(salarylistBean3.getWithholdingTax());
				am.setMunicipalTax(salarylistBean3.getMunicipalTax());
				am.setRental(salarylistBean3.getRental());
				am.setRentalMgmtFee(salarylistBean3.getRentalMgmtFee());
				am.setSum(DateUtil.chgMonthToYM1(salarylistBean3.getSum()));
				am.setRemark(salarylistBean3.getRemark());
				am.setTotalFee(DateUtil.chgMonthToYM1(salarylistBean3.getTotalFee()));
				am.setWkAcccpsIns(salarylistBean3.getWkAcccpsIns());
				am.setEplyInsWithdraw(salarylistBean3.getEplyInsWithdraw());
				am.setEplyInsComp(salarylistBean3.getEplyInsComp());
				am.setWelfareBaby(salarylistBean3.getWelfareBaby());
				am.setWelfarePensionComp(salarylistBean3.getWelfarePensionComp());
				am.setWelfareHealthComp(salarylistBean3.getWelfareHealthComp());
				am.setOverTime(salarylistBean3.getOverTime());
				am.setShortage(salarylistBean3.getShortage());
				am.setWelfareSelf(salarylistBean3.getWelfareSelf());
				am.setWelfareComp(salarylistBean3.getWelfareComp());
				// DBまで給料作成情報を作成。
			    salaryInfoService.querySalaryInfo2(am);
			    return "hello3";
			    //登録ボタンを押す時.変更場合
			}else if(salarylistBean3.getMakeDistinction().equals("2")) {
					SalaryInfocommom2 lm = new SalaryInfocommom2();
					lm.setEmployeeID(salarylistBean3.getEmployeeIDb());
					lm.setMonth(DateUtil.chgMonthToYM(salarylistBean3.getMonth()));
					lm.setPaymentDate(DateUtil.chgMonthToYM(salarylistBean3.getPaymentDate()));
					lm.setBase(DateUtil.chgMonthToYM1(salarylistBean3.getBase()));
					lm.setOverTimePlus(salarylistBean3.getOverTimePlus());
					lm.setShortageReduce(salarylistBean3.getShortageReduce());
					lm.setTransportExpense(salarylistBean3.getTransportExpense());
					lm.setAllowancePlus(salarylistBean3.getAllowancePlus());
					lm.setAllowanceReduce(salarylistBean3.getAllowanceReduce());
					lm.setAllowanceReason(salarylistBean3.getAllowanceReason());
					lm.setWelfarePensionSelf(salarylistBean3.getWelfarePensionSelf());
					lm.setWelfareHealthSelf(salarylistBean3.getWelfareHealthSelf());
					lm.setEplyInsSelf(salarylistBean3.getEplyInsSelf());
					lm.setWithholdingTax(salarylistBean3.getWithholdingTax());
					lm.setMunicipalTax(salarylistBean3.getMunicipalTax());
					lm.setRental(salarylistBean3.getRental());
					lm.setRentalMgmtFee(salarylistBean3.getRentalMgmtFee());
					lm.setSum(DateUtil.chgMonthToYM1(salarylistBean3.getSum()));
					lm.setRemark(salarylistBean3.getRemark());
					lm.setTotalFee(DateUtil.chgMonthToYM1(salarylistBean3.getTotalFee()));
					lm.setWkAcccpsIns(salarylistBean3.getWkAcccpsIns());
					lm.setEplyInsWithdraw(salarylistBean3.getEplyInsWithdraw());
					lm.setEplyInsComp(salarylistBean3.getEplyInsComp());
					lm.setWelfareBaby(salarylistBean3.getWelfareBaby());
					lm.setWelfarePensionComp(salarylistBean3.getWelfarePensionComp());
					lm.setWelfareHealthComp(salarylistBean3.getWelfareHealthComp());
					lm.setOverTime(salarylistBean3.getOverTime());
					lm.setShortage(salarylistBean3.getShortage());
					lm.setWelfareSelf(salarylistBean3.getWelfareSelf());
					lm.setWelfareComp(salarylistBean3.getWelfareComp());
					// DBまで給料作成情報を更新。
				    salaryInfoService.querySalaryInfo3(lm);
				    return "hello2";

				}
		}

					//社員IDがクライアント側へ渡す。
					String bb =salarylistBean3.getEmployeeIDFlg();
					model.addAttribute("EmployeeIDb",bb);
					return "salaryInfo";
	}
}
