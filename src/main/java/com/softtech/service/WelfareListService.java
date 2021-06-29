package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.WelfareBean;
import com.softtech.entity.WelfareInfo;
import com.softtech.mappers.WelfareListMapper;

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
		// DBから福祉控除情報を取得する
		List<WelfareInfo> welfareInfo= welfareListMapper.getWelfare();
		//DBから取得したデータを画面データへ変換する
		List<WelfareBean> rtn  = welfareer(welfareInfo);
		return  rtn;
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
		welfareBean.setStartDate(ww.getStartDate());
		//厚生控除会社
		welfareBean.setWelfareComp(Integer.toString(ww.getWelfareComp()));
		//厚生控除子育(会社)
		welfareBean.setWelfareBaby(Integer.toString(ww.getWelfareBaby()));
		//雇用保険会社負担
		welfareBean.setEplyInsComp(Integer.toString(ww.getEplyInsComp()));
		//雇用保拠出金（会社)
		welfareBean.setEplyInsWithdraw(Integer.toString(ww.getEplyInsWithdraw()));
		//源泉控除
		welfareBean.setWithholdingTax(Integer.toString(ww.getWithholdingTax()));
		//住民税控除
		welfareBean.setMunicipalTax(Integer.toString(ww.getMunicipalTax()));
		//社宅家賃控除
		welfareBean.setRental(Integer.toString(ww.getRental()));
		//社宅管理費控除
		welfareBean.setRentalMgmtFee(Integer.toString(ww.getRentalMgmtFee()));
		//控除ステータス
		welfareBean.setStatus(ww.getStatus());
		//作成日
		welfareBean.setInsertDate(ww.getInsertDate());
		//更新日
		welfareBean.setUpdateDate(ww.getUpdateDate());
		rtn.add(welfareBean);
		}
		return rtn;
	}
}
