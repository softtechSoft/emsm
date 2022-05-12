package com.softtech.service;

import java.util.List;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.BaseSalaryInfoBean;
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
	//更新画面
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfo(String baseSalaryID);

	public boolean updateBaseSalaryInfoList(BaseSalaryInfoBean baseSalaryInfoBean);
}
