package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.common.SalaryInfocommom;
import com.softtech.common.SalaryInfocommom2;
import com.softtech.entity.SalaryInfo;
import com.softtech.mappers.SalaryInfoMapper;

/**
 * 概要：勤怠リストのservice
 *
 * 作成者：馬＠ソフトテク
 * 作成日：2021/05/25
 */
@Service
public class SalaryInfoService {
	@Autowired
	SalaryInfoMapper salaryInfoMapper;
	public List<SalaryInfo> querySalaryInfo(SalaryInfocommom em) {
		// 給料作成情報を取得する
		List<SalaryInfo> salaryInfo= salaryInfoMapper.getSalaryInfoDetail(em);
		return  salaryInfo;
	}
	public List<SalaryInfo> querySalaryInfo1(String id) {
		// 社員情報を取得する
		List<SalaryInfo> salaryInfo1= salaryInfoMapper.getSalaryInfoDetail1( id );
		return  salaryInfo1;
	}
	public  void querySalaryInfo2(SalaryInfocommom2 am) {
		// 給料作成情報を作成する
		 salaryInfoMapper.setSalaryInfoDetail(am);
	}
	public  void querySalaryInfo3(SalaryInfocommom2 lm) {
		// 給料作成情報を更新する
		 salaryInfoMapper.setSalaryInfoDetail1(lm);
	}


}
