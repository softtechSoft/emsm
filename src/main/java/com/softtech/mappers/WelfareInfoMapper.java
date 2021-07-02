package com.softtech.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.WelfareInfo;

@Mapper
public interface WelfareInfoMapper {
	WelfareInfo getEmployeeDetail(String employeeID);
	WelfareInfo makeWelfare(WelfareInfo welfareInfo);

}
