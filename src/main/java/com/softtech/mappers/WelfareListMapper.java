package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.Employee;
import com.softtech.entity.WelfareInfo;

@Mapper
public interface WelfareListMapper {
	List<WelfareInfo> getWelfareInfoDetail(String employeeID);
	List<WelfareInfo> getWelfare();
	List<Employee> getEmployee();

}
