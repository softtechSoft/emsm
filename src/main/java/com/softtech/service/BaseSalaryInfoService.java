package com.softtech.service;

import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.softtech.actionForm.BaseSalaryInfoBean;
import com.softtech.actionForm.BaseSalaryInfoFormBean;
import com.softtech.actionForm.ContractInfoBean;
import com.softtech.actionForm.ContractInfoFormBean;
import com.softtech.entity.BaseSalaryInfoEntity;
import com.softtech.entity.ContractInfoEntity;

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

	//数字チェック
	public List<FieldError> chkNumberData(BaseSalaryInfoBean baseSalaryInfoBean);

	//DB Entityからui　formへ変更
	public BaseSalaryInfoFormBean trasferEntityToUI(List<BaseSalaryInfoEntity> bList);

	//baseSalaryIDの最大値を取得
	public String getNextBaseSalaryID();
	//新規処理
	public boolean insertBaseSalaryInfoDetail(BaseSalaryInfoFormBean baseSalaryInfoBean);
}
