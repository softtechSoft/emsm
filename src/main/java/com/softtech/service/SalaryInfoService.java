package com.softtech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.common.SalaryInfoRecord;
import com.softtech.entity.SalaryInfo;
import com.softtech.mappers.SalaryInfoMapper;
import com.softtech.util.DateUtil;

/**
 * 概要：給料作成のservice
 *
 * 作成者：馬＠ソフトテク
 * 作成日：2021/06/15
 */
@Service
public class SalaryInfoService {
	@Autowired
	SalaryInfoMapper salaryInfoMapper;
	//給料作成情報の取得
	public SalaryInfoBean querySalaryInfo(SalaryInfoRecord em) {
		// DBから給料作成情報を取得する
		SalaryInfo salaryInfo= salaryInfoMapper.getSalaryInfoDetail(em);
		if(salaryInfo==null) {
			return null;
		}
		//DBから取得したデータを給料作成へ変換する
		SalaryInfoBean salaryInfoBean = new SalaryInfoBean();
		//社員ID
		salaryInfoBean.setEmployeeID(salaryInfo.getEmployeeID());
		//氏名
		salaryInfoBean.setEmployeeName(salaryInfo.getEmployeeName());
		//住所
		salaryInfoBean.setAddress(salaryInfo.getAddress());
		//対象月
		salaryInfoBean.setMonth(salaryInfo.getMonth());
		//基本給
		salaryInfoBean.setBase(salaryInfo.getBase());
		//支払日
		salaryInfoBean.setPaymentDate(salaryInfo.getPaymentDate());
		//残業時間
		salaryInfoBean.setOverTime(salaryInfo.getOverTime());
		//不足時間
		salaryInfoBean.setShortage(salaryInfo.getShortage());
		//残業加算
		salaryInfoBean.setOverTimePlus(salaryInfo.getOverTimePlus());
		//稼働不足減
		salaryInfoBean.setShortageReduce(salaryInfo.getShortageReduce());
		//交通費
		salaryInfoBean.setTransportExpense(salaryInfo.getTransportExpense());
		//手当加算
		salaryInfoBean.setAllowancePlus(salaryInfo.getAllowancePlus());
		//手当減算
		salaryInfoBean.setAllowanceReduce(salaryInfo.getAllowanceReduce());
		//手当理由
		salaryInfoBean.setAllowanceReason(salaryInfo.getAllowanceReason());
		//厚生控除個人
		salaryInfoBean.setWelfareSelf(salaryInfo.getWelfareSelf());
		//厚生控除会社
		salaryInfoBean.setWelfareComp(salaryInfo.getWelfareComp());
		//厚生控除子育(会社）
		salaryInfoBean.setWelfareBaby(salaryInfo.getWelfareBaby());
		//雇用保険個人負担
		salaryInfoBean.setEplyInsSelf(salaryInfo.getEplyInsSelf());
		//雇用保険会社負担
		salaryInfoBean.setEplyInsComp(salaryInfo.getEplyInsComp());
		//雇用保拠出金（会社)
		salaryInfoBean.setEplyInsWithdraw(salaryInfo.getEplyInsWithdraw());
		//労災保険（会社負担のみ）
		salaryInfoBean.setWkAcccpsIns(salaryInfo.getWkAcccpsIns());
		//源泉控除
		salaryInfoBean.setWithholdingTax(salaryInfo.getWithholdingTax());
		//住民税控除
		salaryInfoBean.setMunicipalTax(salaryInfo.getMunicipalTax());
		//社宅家賃控除
		salaryInfoBean.setRental(salaryInfo.getRental());
		//社宅共益費控除
		salaryInfoBean.setRentalMgmtFee(salaryInfo.getRentalMgmtFee());
		//総額
		salaryInfoBean.setSum(salaryInfo.getSum());
		//総費用
		salaryInfoBean.setTotalFee(salaryInfo.getTotalFee());
		//備考
		salaryInfoBean.setRemark(salaryInfo.getRemark());
		return  salaryInfoBean;
	}
	// 給料作成情報の作成
	public  void setSalaryInfo2(SalaryInfoRecord am) {
		 salaryInfoMapper.setSalaryInfoDetail(am);
	}
	// 給料作成情報の更新
	public  void setSalaryInfo3(SalaryInfoRecord lm) {
		 salaryInfoMapper.setSalaryInfoDetail1(lm);
	}
	//登録用情報
	public SalaryInfoRecord  queryRegister(SalaryInfoBean salaryInfoBean) {
		//登録用情報
		SalaryInfoRecord am = new SalaryInfoRecord();
		//社員ID
		am.setEmployeeID(salaryInfoBean.getEmployeeID());
		//対象月
		am.setMonth(DateUtil.chgMonthToYM(salaryInfoBean.getMonth()));
		//支払日
		am.setPaymentDate(DateUtil.chgMonthToYM(salaryInfoBean.getPaymentDate()));
		//基本給
		am.setBase(Integer.parseInt(DateUtil.chgMonthToYM1(salaryInfoBean.getBase())));
		if(salaryInfoBean.getOverTimePlus().length()==0) {
	     	salaryInfoBean.setOverTimePlus("0") ;
		}
		//残業加算
		am.setOverTimePlus(Integer.parseInt(salaryInfoBean.getOverTimePlus()));
		if(salaryInfoBean.getShortageReduce().length()==0) {
	     	salaryInfoBean.setShortageReduce("0") ;
		}
		//稼働不足減
		am.setShortageReduce(Integer.parseInt(salaryInfoBean.getShortageReduce()));
		if(salaryInfoBean.getTransportExpense().length()==0) {
	     	salaryInfoBean.setTransportExpense("0") ;
		}
		//交通費
		am.setTransportExpense(Integer.parseInt(salaryInfoBean.getTransportExpense()));
		if(salaryInfoBean.getAllowancePlus().length()==0) {
	     	salaryInfoBean.setAllowancePlus("0") ;
		}
		//手当加算
		am.setAllowancePlus(Integer.parseInt(salaryInfoBean.getAllowancePlus()));
		if(salaryInfoBean.getAllowanceReduce().length()==0) {
	     	salaryInfoBean.setAllowanceReduce("0") ;
		}
		//手当減算
		am.setAllowanceReduce(Integer.parseInt(salaryInfoBean.getAllowanceReduce()));
		//手当理由
		am.setAllowanceReason(salaryInfoBean.getAllowanceReason());
		if(salaryInfoBean.getEplyInsSelf().length()==0) {
	     	salaryInfoBean.setEplyInsSelf("0") ;
		}
		//雇用保険個人負担
		am.setEplyInsSelf(Integer.parseInt(salaryInfoBean.getEplyInsSelf()));
		if(salaryInfoBean.getWithholdingTax().length()==0) {
	     	salaryInfoBean.setWithholdingTax("0") ;
		}
		//源泉控除
		am.setWithholdingTax(Integer.parseInt(salaryInfoBean.getWithholdingTax()));
		if(salaryInfoBean.getMunicipalTax().length()==0) {
	     	salaryInfoBean.setMunicipalTax("0") ;
		}
		//住民税控除
		am.setMunicipalTax(Integer.parseInt(salaryInfoBean.getMunicipalTax()));
		if(salaryInfoBean.getRental().length()==0) {
	     	salaryInfoBean.setRental("0") ;
		}
		//社宅家賃控除
		am.setRental(Integer.parseInt(salaryInfoBean.getRental()));
		if(salaryInfoBean.getRentalMgmtFee().length()==0) {
	     	salaryInfoBean.setRentalMgmtFee("0") ;
		}
		//社宅共益費控除
		am.setRentalMgmtFee(Integer.parseInt(salaryInfoBean.getRentalMgmtFee()));
		//総額
		am.setSum(Integer.parseInt(DateUtil.chgMonthToYM1(salaryInfoBean.getSum())));
		//備考
		am.setRemark(salaryInfoBean.getRemark());
		//総費用
		am.setTotalFee(Integer.parseInt(DateUtil.chgMonthToYM1(salaryInfoBean.getTotalFee())));
		if(salaryInfoBean.getWkAcccpsIns().length()==0) {
	     	salaryInfoBean.setWkAcccpsIns("0") ;
		}
		//労災保険（会社負担のみ）
		am.setWkAcccpsIns(Integer.parseInt(salaryInfoBean.getWkAcccpsIns()));
		if(salaryInfoBean.getEplyInsWithdraw().length()==0) {
	     	salaryInfoBean.setEplyInsWithdraw("0") ;
		}
		//雇用保拠出金（会社)
		am.setEplyInsWithdraw(Integer.parseInt(salaryInfoBean.getEplyInsWithdraw()));
		if(salaryInfoBean.getEplyInsComp().length()==0) {
	     	salaryInfoBean.setEplyInsComp("0") ;
		}
		//雇用保険会社負担
		am.setEplyInsComp(Integer.parseInt(salaryInfoBean.getEplyInsComp()));
		if(salaryInfoBean.getWelfareBaby().length()==0) {
	     	salaryInfoBean.setWelfareBaby("0") ;
		}
		//厚生控除子育(会社）
		am.setWelfareBaby(Integer.parseInt(salaryInfoBean.getWelfareBaby()));
		if(salaryInfoBean.getOverTime().length()==0) {
	     	salaryInfoBean.setOverTime("0") ;
		}
		//残業時間
		am.setOverTime(Integer.parseInt(salaryInfoBean.getOverTime()));
		if(salaryInfoBean.getShortage().length()==0) {
	     	salaryInfoBean.setShortage("0") ;
		}
		//不足時間
		am.setShortage(Integer.parseInt(salaryInfoBean.getShortage()));
		if(salaryInfoBean.getWelfareSelf().length()==0) {
	     	salaryInfoBean.setWelfareSelf("0") ;
		}
		//厚生控除個人
		am.setWelfareSelf(Integer.parseInt(salaryInfoBean.getWelfareSelf()));
		if(salaryInfoBean.getWelfareComp().length()==0) {
	     	salaryInfoBean.setWelfareComp("0") ;
		}
		//厚生控除会社
		am.setWelfareComp(Integer.parseInt(salaryInfoBean.getWelfareComp()));
		return am;
	}
}
