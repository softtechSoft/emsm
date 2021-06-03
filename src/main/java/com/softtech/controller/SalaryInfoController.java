package com.softtech.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.SalarylistBean2;
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
	public String toWorkDetailList(@Valid @ModelAttribute("SalarylistBean2") SalarylistBean2 salarylistBean2, Model model) {
		if(salarylistBean2.getMake().equals("2")) {
		    // 作成ボタンをおした、inputが可入力。
			model.addAttribute("loadFlg", salarylistBean2.getLoadFlg());
			SalaryInfocommom em = new SalaryInfocommom();
		 	// 対象年月+1をする。
		    int a = Integer.parseInt(DateUtil.chgMonthToYM(salarylistBean2.getMonth()))+1;
		    String s=String.valueOf(a);
			//社員ID
			em.setEmployeeID(salarylistBean2.getEmployeeIDFlg());
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
				List<SalaryInfo> dd= salaryInfoService.querySalaryInfo1(salarylistBean2.getEmployeeIDFlg());
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
				//登録ボタンを押す時.作成場合
		}else if(salarylistBean2.getMake().equals("1")) {
			if(salarylistBean2.getMakeDistinction().equals("1") ) {
				SalaryInfocommom2 am = new SalaryInfocommom2();
				am.setEmployeeID(salarylistBean2.getEmployeeIDb());
				am.setMonth(DateUtil.chgMonthToYM(salarylistBean2.getMonth()));
				am.setPaymentDate(DateUtil.chgMonthToYM(salarylistBean2.getPaymentDate()));
				am.setBase(DateUtil.chgMonthToYM1(salarylistBean2.getBase()));
				am.setOverTimePlus(salarylistBean2.getOverTimePlus());
				am.setShortageReduce(salarylistBean2.getShortageReduce());
				am.setTransportExpense(salarylistBean2.getTransportExpense());
				am.setAllowancePlus(salarylistBean2.getAllowancePlus());
				am.setAllowanceReduce(salarylistBean2.getAllowanceReduce());
				am.setAllowanceReason(salarylistBean2.getAllowanceReason());
				am.setWelfarePensionSelf(salarylistBean2.getWelfarePensionSelf());
				am.setWelfareHealthSelf(salarylistBean2.getWelfareHealthSelf());
				am.setEplyInsSelf(salarylistBean2.getEplyInsSelf());
				am.setWithholdingTax(salarylistBean2.getWithholdingTax());
				am.setMunicipalTax(salarylistBean2.getMunicipalTax());
				am.setRental(salarylistBean2.getRental());
				am.setRentalMgmtFee(salarylistBean2.getRentalMgmtFee());
				am.setSum(DateUtil.chgMonthToYM1(salarylistBean2.getSum()));
				am.setRemark(salarylistBean2.getRemark());
				am.setTotalFee(DateUtil.chgMonthToYM1(salarylistBean2.getTotalFee()));
				am.setWkAcccpsIns(salarylistBean2.getWkAcccpsIns());
				am.setEplyInsWithdraw(salarylistBean2.getEplyInsWithdraw());
				am.setEplyInsComp(salarylistBean2.getEplyInsComp());
				am.setWelfareBaby(salarylistBean2.getWelfareBaby());
				am.setWelfarePensionComp(salarylistBean2.getWelfarePensionComp());
				am.setWelfareHealthComp(salarylistBean2.getWelfareHealthComp());
				am.setOverTime(salarylistBean2.getOverTime());
				am.setShortage(salarylistBean2.getShortage());
				am.setWelfareSelf(salarylistBean2.getWelfareSelf());
				am.setWelfareComp(salarylistBean2.getWelfareComp());
				// DBまで給料作成情報を作成。
			    salaryInfoService.querySalaryInfo2(am);
			    return "hello3";
			    //登録ボタンを押す時.変更場合
			}else if(salarylistBean2.getMakeDistinction().equals("2")) {
					SalaryInfocommom2 lm = new SalaryInfocommom2();
					lm.setEmployeeID(salarylistBean2.getEmployeeIDb());
					lm.setMonth(DateUtil.chgMonthToYM(salarylistBean2.getMonth()));
					lm.setPaymentDate(DateUtil.chgMonthToYM(salarylistBean2.getPaymentDate()));
					lm.setBase(DateUtil.chgMonthToYM1(salarylistBean2.getBase()));
					lm.setOverTimePlus(salarylistBean2.getOverTimePlus());
					lm.setShortageReduce(salarylistBean2.getShortageReduce());
					lm.setTransportExpense(salarylistBean2.getTransportExpense());
					lm.setAllowancePlus(salarylistBean2.getAllowancePlus());
					lm.setAllowanceReduce(salarylistBean2.getAllowanceReduce());
					lm.setAllowanceReason(salarylistBean2.getAllowanceReason());
					lm.setWelfarePensionSelf(salarylistBean2.getWelfarePensionSelf());
					lm.setWelfareHealthSelf(salarylistBean2.getWelfareHealthSelf());
					lm.setEplyInsSelf(salarylistBean2.getEplyInsSelf());
					lm.setWithholdingTax(salarylistBean2.getWithholdingTax());
					lm.setMunicipalTax(salarylistBean2.getMunicipalTax());
					lm.setRental(salarylistBean2.getRental());
					lm.setRentalMgmtFee(salarylistBean2.getRentalMgmtFee());
					lm.setSum(DateUtil.chgMonthToYM1(salarylistBean2.getSum()));
					lm.setRemark(salarylistBean2.getRemark());
					lm.setTotalFee(DateUtil.chgMonthToYM1(salarylistBean2.getTotalFee()));
					lm.setWkAcccpsIns(salarylistBean2.getWkAcccpsIns());
					lm.setEplyInsWithdraw(salarylistBean2.getEplyInsWithdraw());
					lm.setEplyInsComp(salarylistBean2.getEplyInsComp());
					lm.setWelfareBaby(salarylistBean2.getWelfareBaby());
					lm.setWelfarePensionComp(salarylistBean2.getWelfarePensionComp());
					lm.setWelfareHealthComp(salarylistBean2.getWelfareHealthComp());
					lm.setOverTime(salarylistBean2.getOverTime());
					lm.setShortage(salarylistBean2.getShortage());
					lm.setWelfareSelf(salarylistBean2.getWelfareSelf());
					lm.setWelfareComp(salarylistBean2.getWelfareComp());
					// DBまで給料作成情報を更新。
				    salaryInfoService.querySalaryInfo3(lm);
				    return "hello2";

				}
		}
		//社員IDがクライアント側へ渡す。
		String bb =salarylistBean2.getEmployeeIDFlg();
		model.addAttribute("EmployeeIDb",bb);
		return "salaryInfo";
	}
}
