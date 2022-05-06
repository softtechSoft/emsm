package com.softtech.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.ContractInfoEntity;
import com.softtech.mappers.ContractInfoMapper;
/**
 * 概要：給料情報取得インタフェース
 *
 * @author テー@ソフトテク
 *
 */
@Service
public class ContractInfoServiceImpl implements ContractInfoService{

	/**
	 * 機能：給料情報リスト取得
	 *
	 * @author テー@ソフトテク
	 */
	@Autowired
	ContractInfoMapper contractinfoMapper;
	@Override
	public List<ContractInfoEntity> queryContractInfoList(String employeeID) {
		List<ContractInfoEntity> contractinfolist = contractinfoMapper.getContractInfoList(employeeID);
		return  contractinfolist;
	}

}
