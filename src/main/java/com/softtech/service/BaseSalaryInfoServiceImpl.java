package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.BaseSalaryInfoEntity;
import com.softtech.mappers.BaseSalaryInfoMapper;
@Service
public class BaseSalaryInfoServiceImpl implements BaseSalaryInfoService {


	@Autowired
	BaseSalaryInfoMapper baseSalaryInfoMapper;
	//データ画面
	@Override
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfoList(String employeeID) {
		List<BaseSalaryInfoEntity> baseSalaryInfoList = baseSalaryInfoMapper.getBaseSalaryInfoList(employeeID);
		return  baseSalaryInfoList;
	}
	//更新画面
	@Override
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfo(String baseSalaryID) {
		List<BaseSalaryInfoEntity> baseSalaryInfo = baseSalaryInfoMapper.getBaseSalaryInfo(baseSalaryID);
		return  baseSalaryInfo;
	}
}
