package com.softtech.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.common.SalaryInfoRecord;
import com.softtech.entity.SalaryInfo;

@Mapper
public interface SalaryInfoMapper {
	SalaryInfo getSalaryInfoDetail(SalaryInfoRecord em);
	SalaryInfoRecord insertSalaryInfo(SalaryInfoRecord am);
	SalaryInfoRecord updateSalaryInfo(SalaryInfoRecord lm);

}
