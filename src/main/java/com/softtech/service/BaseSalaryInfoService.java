package com.softtech.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.softtech.actionForm.BaseSalaryInfoFormBean;
import com.softtech.entity.BaseSalaryInfoEntity;

/**
 * 概要：基本給追加と変更のservice
 *
 * 作成者：孫曄＠ソフトテク
 * 作成日：2022/05/5
 */

@Service
public interface BaseSalaryInfoService {
	/*
	 * 機能概要： 作成用社員情報を取得
	 *			条件：社員ID
	 *
	 * @param  employeeID 検索パラメータ
	 */
	//データ画面
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfoList(String employeeID);
	//更新画面へ
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfo(String baseSalaryID);
	//データ更新
	public boolean updateBaseSalaryInfoList(BaseSalaryInfoFormBean baseSalaryInfoBean);

	//DB Entityからui　formへ変更
	public BaseSalaryInfoFormBean trasferEntityToUI(List<BaseSalaryInfoEntity> bList);

	//baseSalaryIDの最大値+1を取得
	public String getNextBaseSalaryID();
	//新規処理
	public boolean insertBaseSalaryInfoDetail(BaseSalaryInfoFormBean baseSalaryInfoBean);
	
	// IDと年度検索
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfoByCondition(String employeeID, String year);

	// 年度
	public List<String> getYearList();
}
