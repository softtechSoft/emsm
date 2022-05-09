package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.softtech.entity.BaseSalaryInfoEntity;
import com.softtech.entity.ContractInfoEntity;
import com.softtech.mappers.BaseSalaryInfoMapper;
import com.softtech.mappers.ContractInfoMapper;

public class BaseSalaryInfoServiceImpl implements BaseSalaryInfoService {


	@Autowired
	BaseSalaryInfoMapper baseSalaryInfoMapper;

	@Override
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfoList(String employeeID) {
		List<BaseSalaryInfoEntity> baseSalaryInfoList = baseSalaryInfoMapper.getBaseSalaryInfoList(employeeID);
		return  baseSalaryInfoList;
	}


}
