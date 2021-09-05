package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.WelfareBean;
import com.softtech.entity.Employee;
import com.softtech.entity.WelfareInfo;
import com.softtech.mappers.WelfareListMapper;
import com.softtech.util.DateUtil;

/**
 * 概要：福祉控除リストリストのservice
 *
 * 作成者：馬＠ソフトテク
 * 作成日：2021/06
 */
@Service
public class WelfareListService {
	@Autowired
	WelfareListMapper welfareListMapper;

	/*
	 * 機能概要：福祉控除情報検索
	 *			条件：社員ID
	 *
	 * @param  employeeID 検索パラメータ
	 * @return 福祉控除情報
	 */
	public  List<WelfareBean> queryWelfare(String employeeID) {
		// DBから福祉控除情報を取得する
		List<WelfareInfo> welfareInfo= welfareListMapper.getWelfareInfoDetail(employeeID);
		//DBから取得したデータを画面データへ変換する
		List<WelfareBean> rtn  = welfareer(welfareInfo);
		return  rtn;

	}
	/*
	 * 機能概要：初期画面時、福祉控除情報取得
	 *
	 * @param なし
	 * @return 福祉控除情報
	 */
	public List<WelfareBean>  queryWelfareDate() {
		// DBから福祉情報を取得する
		List<WelfareInfo> welfareInfo= welfareListMapper.getWelfare();
		// DBから社員情報を取得する
		List<Employee> employee= welfareListMapper.getEmployee();
		//福祉リストを作成
		List<WelfareBean> rtn  =new ArrayList<WelfareBean>();
		for(Employee wk : employee) {
			WelfareBean welfareBean= new WelfareBean();
			//社員ID
			welfareBean.setEmployeeID(wk.getEmployeeID());
			//社員氏名
			welfareBean.setEmployeeName(wk.getEmployeeName());
			for(WelfareInfo ww : welfareInfo) {
				if(wk.getEmployeeID().equals(ww.getEmployeeID())) {
					//控除開始日
					welfareBean.setStartDate(DateUtil.modifymonth1(ww.getStartDate()));
					//基本給
					welfareBean.setBase(DateUtil.formatTosepara(ww.getBase()));
					//厚生年金控除個人
					welfareBean.setWelfarePensionSelf(Integer.toString(ww.getWelfarePensionSelf()));
					//厚生年金控除会社
					welfareBean.setWelfarePensionComp(Integer.toString(ww.getWelfarePensionComp()));
					//厚生健康控除会社
					welfareBean.setWelfareHealthComp(Integer.toString(ww.getWelfareHealthComp()));
					//厚生健康控除個人
					welfareBean.setWelfareHealthSelf(Integer.toString(ww.getWelfareHealthSelf()));
					//厚生控除子育(会社)
					welfareBean.setWelfareBaby(Integer.toString(ww.getWelfareBaby()));
					//雇用保険個人負担
					welfareBean.setEplyInsSelf(Integer.toString(ww.getEplyInsSelf()));
					//雇用保険会社負担
					welfareBean.setEplyInsComp(Integer.toString(ww.getEplyInsComp()));
					//雇用保拠出金（会社)
					welfareBean.setEplyInsWithdraw(Integer.toString(ww.getEplyInsWithdraw()));
					//労災保険（会社負担のみ）
					welfareBean.setWkAcccpsIns(Integer.toString(ww.getWkAcccpsIns()));
					//源泉控除
					welfareBean.setWithholdingTax(Integer.toString(ww.getWithholdingTax()));
					//住民税控除一月
					welfareBean.setMunicipalTax1(Integer.toString(ww.getMunicipalTax1()));
					//住民税控除二月
					welfareBean.setMunicipalTax2(Integer.toString(ww.getMunicipalTax2()));
					//住民税控除三月
					welfareBean.setMunicipalTax3(Integer.toString(ww.getMunicipalTax3()));
					//住民税控除四月
					welfareBean.setMunicipalTax4(Integer.toString(ww.getMunicipalTax4()));
					//住民税控除五月
					welfareBean.setMunicipalTax5(Integer.toString(ww.getMunicipalTax5()));
					//住民税控除六月
					welfareBean.setMunicipalTax6(Integer.toString(ww.getMunicipalTax6()));
					//住民税控除七月
					welfareBean.setMunicipalTax7(Integer.toString(ww.getMunicipalTax7()));
					//住民税控除八月
					welfareBean.setMunicipalTax8(Integer.toString(ww.getMunicipalTax8()));
					//住民税控除九月
					welfareBean.setMunicipalTax9(Integer.toString(ww.getMunicipalTax9()));
					//住民税控除十月
					welfareBean.setMunicipalTax10(Integer.toString(ww.getMunicipalTax10()));
					//住民税控除十一月
					welfareBean.setMunicipalTax11(Integer.toString(ww.getMunicipalTax11()));
					//住民税控除十二月
					welfareBean.setMunicipalTax12(Integer.toString(ww.getMunicipalTax12()));
					//社宅家賃控除
					welfareBean.setRental(Integer.toString(ww.getRental()));
					//社宅管理費控除
					welfareBean.setRentalMgmtFee(Integer.toString(ww.getRentalMgmtFee()));
					//控除ステータス
					welfareBean.setStatus(ww.getStatus());
					//作成日
					welfareBean.setInsertDate(DateUtil.modifymonth1(ww.getInsertDate()));
					//作成者
					welfareBean.setInsertEmployee(ww.getInsertEmployee());
					//更新日
					welfareBean.setUpdateDate(DateUtil.modifymonth1(ww.getUpdateDate()));
					//更新者
					welfareBean.setUpdateEmployee(ww.getUpdateEmployee());
				}
			}
			rtn.add(welfareBean);
		}
			return rtn;
	}
	/**
	 * 機能：DBから取得したデータを福祉控除情報へ変換する。
	 *
	 * @param lst DBから取得したデータ
	 * @return 福祉控除情報リスト
	 *
	 * @author 馬@ソフトテク
	 */
	private List<WelfareBean> welfareer(List<WelfareInfo> lst){
		if(lst == null ) return new ArrayList<WelfareBean>();
		List<WelfareBean> rtn  =new ArrayList<WelfareBean>();
		for(WelfareInfo ww : lst) {
		WelfareBean welfareBean= new WelfareBean();
		//社員ID
		welfareBean.setEmployeeID(ww.getEmployeeID());
		//社員氏名
		welfareBean.setEmployeeName(ww.getEmployeeName());
		//控除開始日
		welfareBean.setStartDate(DateUtil.modifymonth1(ww.getStartDate()));
		//基本給
		welfareBean.setBase(DateUtil.formatTosepara1(ww.getBase()));
		//厚生年金控除個人
		welfareBean.setWelfarePensionSelf(Integer.toString(ww.getWelfarePensionSelf()));
		//厚生年金控除会社
		welfareBean.setWelfarePensionComp(Integer.toString(ww.getWelfarePensionComp()));
		//厚生健康控除会社
		welfareBean.setWelfareHealthComp(Integer.toString(ww.getWelfareHealthComp()));
		//厚生健康控除個人
		welfareBean.setWelfareHealthSelf(Integer.toString(ww.getWelfareHealthSelf()));
		//厚生控除子育(会社)
		welfareBean.setWelfareBaby(Integer.toString(ww.getWelfareBaby()));
		//雇用保険個人負担
		welfareBean.setEplyInsSelf(Integer.toString(ww.getEplyInsSelf()));
		//雇用保険会社負担
		welfareBean.setEplyInsComp(Integer.toString(ww.getEplyInsComp()));
		//雇用保拠出金（会社)
		welfareBean.setEplyInsWithdraw(Integer.toString(ww.getEplyInsWithdraw()));
		//労災保険（会社負担のみ）
		welfareBean.setWkAcccpsIns(Integer.toString(ww.getWkAcccpsIns()));
		//源泉控除
		welfareBean.setWithholdingTax(Integer.toString(ww.getWithholdingTax()));
		//住民税控除一月
		welfareBean.setMunicipalTax1(Integer.toString(ww.getMunicipalTax1()));
		//住民税控除二月
		welfareBean.setMunicipalTax2(Integer.toString(ww.getMunicipalTax2()));
		//住民税控除三月
		welfareBean.setMunicipalTax3(Integer.toString(ww.getMunicipalTax3()));
		//住民税控除四月
		welfareBean.setMunicipalTax4(Integer.toString(ww.getMunicipalTax4()));
		//住民税控除五月
		welfareBean.setMunicipalTax5(Integer.toString(ww.getMunicipalTax5()));
		//住民税控除六月
		welfareBean.setMunicipalTax6(Integer.toString(ww.getMunicipalTax6()));
		//住民税控除七月
		welfareBean.setMunicipalTax7(Integer.toString(ww.getMunicipalTax7()));
		//住民税控除八月
		welfareBean.setMunicipalTax8(Integer.toString(ww.getMunicipalTax8()));
		//住民税控除九月
		welfareBean.setMunicipalTax9(Integer.toString(ww.getMunicipalTax9()));
		//住民税控除十月
		welfareBean.setMunicipalTax10(Integer.toString(ww.getMunicipalTax10()));
		//住民税控除十一月
		welfareBean.setMunicipalTax11(Integer.toString(ww.getMunicipalTax11()));
		//住民税控除十二月
		welfareBean.setMunicipalTax12(Integer.toString(ww.getMunicipalTax12()));
		//社宅家賃控除
		welfareBean.setRental(Integer.toString(ww.getRental()));
		//社宅管理費控除
		welfareBean.setRentalMgmtFee(Integer.toString(ww.getRentalMgmtFee()));
		//控除ステータス
		welfareBean.setStatus(ww.getStatus());
		//作成日
		welfareBean.setInsertDate(ww.getInsertDate());
		//作成者
		welfareBean.setInsertEmployee(ww.getInsertEmployee());
		//更新日
		welfareBean.setUpdateDate(ww.getUpdateDate());
		//更新者
		welfareBean.setUpdateEmployee(ww.getUpdateEmployee());
		rtn.add(welfareBean);
		}
		return rtn;
	}
}
