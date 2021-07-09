package com.softtech.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.common.SalaryInfoRecord;
import com.softtech.entity.SalaryInfo;
import com.softtech.entity.WelfareInfo;

@Mapper
public interface SalaryInfoMapper {
	SalaryInfo getSalaryInfoDetail(SalaryInfoRecord em);
	WelfareInfo getWelfareInfoDetail(String EmployeeID );
	SalaryInfoRecord insertSalaryInfo(SalaryInfoRecord am);
	SalaryInfoRecord updateSalaryInfo(SalaryInfoRecord lm);

}
