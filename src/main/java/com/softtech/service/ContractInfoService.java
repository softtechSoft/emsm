package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.ContractInfoBean;


/**
 * 概要：給料情報取得インタフェース
 *
 * @author テー@ソフトテク
 */
public interface ContractInfoService {

	/*
	 * 機能：給料情報リスト取得
	 *
	 * @author テー@ソフトテク
	 */
	public List<ContractInfoBean > queryContractInfoList(String employeeID);

}

