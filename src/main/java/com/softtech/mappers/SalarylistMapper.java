package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.SalaryInfo;

@Mapper
public interface SalarylistMapper {

	List<SalaryInfo> getSalaryinfolist(String month);

	//年度大値+1を取得
	String getMaxMonth();

}
