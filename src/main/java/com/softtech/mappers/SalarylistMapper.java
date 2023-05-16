package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.SalaryInfo;

@Mapper
public interface SalarylistMapper {

	List<SalaryInfo> getSalaryinfolist(String month);

	//年度最大値を取得
	String getMaxMonth();

	/**
	 * @return
	 */
	String getBaseSalary(String employeeID,String year);

	String getPayMonth(String month);

}
