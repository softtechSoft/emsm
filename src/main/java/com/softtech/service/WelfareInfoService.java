package com.softtech.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.WelfareBean;
import com.softtech.entity.WelfareInfo;
import com.softtech.mappers.WelfareInfoMapper;
import com.softtech.util.DateUtil;
/**
 * 概要：福祉情報作成のservice
 *
 * 作成者：馬＠ソフトテク
 * 作成日：2021/06/30
 */
@Service
public class WelfareInfoService {
	@Autowired
	WelfareInfoMapper welfareInfoMapper;
	/*
	 * 機能概要： 作成用社員情報を取得
	 *			条件：社員ID
	 *
	 * @param  employeeID 検索パラメータ
	 * @return 作成用社員情報
	 */
	public WelfareBean queryEmployee(String employeeID) {
		// DBから給作成用社員情報を取得する
		WelfareInfo welfareInfoEmployee= welfareInfoMapper.getEmployeeDetail(employeeID);
		//DBから取得したデータを画面データへ変換する
		WelfareBean welfareBean = new WelfareBean();
		//社員ID
		welfareBean.setEmployeeID(welfareInfoEmployee.getEmployeeID());
		//社員氏名
		welfareBean.setEmployeeName(welfareInfoEmployee.getEmployeeName());
		return welfareBean;
	}
	/*
	 * 機能概要： 福祉情報を登録
	 *
	 * @param  画面からのデータ
	 * @return 変更後の福祉情報
	 */
	public WelfareInfo changeWelfare(WelfareBean welfareBean,HttpSession session) {
		//ログインの社員ID
		String loginUserID=String.valueOf(session.getAttribute("loginUserID"));
		WelfareInfo welfareInfo= new WelfareInfo();
		//社員ID
		welfareInfo.setEmployeeID(welfareBean.getEmployeeID());
		//控除開始日
		welfareInfo.setStartDate(DateUtil.chgMonthToYM(welfareBean.getStartDate()));
		//基本給
		welfareInfo.setBase(Integer.parseInt(DateUtil.chgMonthToYM1(welfareBean.getBase())));
		//厚生年金控除個人
		welfareInfo.setWelfarePensionSelf(Integer.parseInt(welfareBean.getWelfarePensionSelf()));
		//厚生年金控除会社
		welfareInfo.setWelfarePensionComp(Integer.parseInt(welfareBean.getWelfarePensionComp()));
		//厚生健康控除会社
		welfareInfo.setWelfareHealthComp(Integer.parseInt(welfareBean.getWelfareHealthComp()));
		//厚生健康控除個人
		welfareInfo.setWelfareHealthSelf(Integer.parseInt(welfareBean.getWelfareHealthSelf()));
		//厚生控除子育(会社)
		welfareInfo.setWelfareBaby(Integer.parseInt(welfareBean.getWelfareBaby()));
		//雇用保険個人負担
		welfareInfo.setEplyInsSelf(Integer.parseInt(welfareBean.getEplyInsSelf()));
		//雇用保険会社負担
		welfareInfo.setEplyInsComp(Integer.parseInt(welfareBean.getEplyInsComp()));
		//雇用保拠出金（会社)
		welfareInfo.setEplyInsWithdraw(Integer.parseInt(welfareBean.getEplyInsWithdraw()));
		//労災保険（会社負担のみ）
		welfareInfo.setWkAcccpsIns(Integer.parseInt(welfareBean.getWkAcccpsIns()));
		//源泉控除
		welfareInfo.setWithholdingTax(Integer.parseInt(welfareBean.getWithholdingTax()));
		//住民税控除
		welfareInfo.setMunicipalTax(Integer.parseInt(welfareBean.getMunicipalTax()));
		//社宅家賃控除
		welfareInfo.setRental(Integer.parseInt(welfareBean.getRental()));
		//社宅管理費控除
		welfareInfo.setRentalMgmtFee(Integer.parseInt(welfareBean.getRentalMgmtFee()));
		//控除ステータス
		welfareInfo.setStatus(welfareBean.getStatus());
		//作成日
		welfareInfo.setInsertDate(DateUtil.chgMonthToYM(welfareBean.getInsertDate()));
		//作成者
		welfareInfo.setInsertEmployee(loginUserID);
		//更新日
		welfareInfo.setUpdateDate(DateUtil.chgMonthToYM(welfareBean.getUpdateDate()));
		//更新者
		welfareInfo.setUpdateEmployee(loginUserID);
		return welfareInfo;
	}
	/*
	 * 機能概要： DBに福祉情報を作成と更新
	 *
	 * @param  変更後の福祉情報
	 * @return なし
	 */
	public void makeWelfare(WelfareInfo welfareInfo) {
		//DBに福祉情報を作成と更新
		welfareInfoMapper.makeWelfare(welfareInfo);
	}
}
