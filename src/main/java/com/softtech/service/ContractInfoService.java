package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.ContractInfoFormBean;
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
	//更新処理
	public boolean updateContractInfoDetail(ContractInfoFormBean contractInfoBean) ;
//	//数字チェック
//	public List<FieldError> chkNumberData(ContractInfoBean contractInfoBean);
//	//日付チェック
//	public List<FieldError> chkDate(ContractInfoFormBean ContractInfoFormBean);
	//DB Entityからui　formへ変更
	public ContractInfoFormBean trasferEntityToUI(List<ContractInfoEntity> sList);
	// contrctidの最大値を取得
	public String getNextContractID();
	//新規処理
	public boolean insertContractInfoDetail(ContractInfoFormBean contractInfoBean);


}