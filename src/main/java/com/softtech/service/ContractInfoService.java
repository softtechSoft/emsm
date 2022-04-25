package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.ContractInfoBean;


/**
 * 機能：給料リスト取得
 * @param
 * @param employeeID 対象社員ID
 * @return 給料情報リスト
 * @author テー@ソフトテク
 */
public interface ContractInfoService {

	List<ContractInfoBean > queryContractInfoList(String employeeID);

	}

