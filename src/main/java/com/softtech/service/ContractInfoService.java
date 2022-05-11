package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.ContractInfoBean;
import com.softtech.entity.ContractInfoEntity;


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
	//検索処理の契約リスト
	public List<ContractInfoEntity > queryContractInfoList(String employeeID);
	//更新処理の契約情報
	public List<ContractInfoEntity > queryContractInfo(String contractID);

	public boolean updateContractInfoDetail(ContractInfoBean contractInfoBean) ;


}