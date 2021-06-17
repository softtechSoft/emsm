package com.softtech.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.common.SalaryInfoRecord;
import com.softtech.entity.SalaryInfo;

@Mapper
public interface SalaryInfoMapper {
	SalaryInfo getSalaryInfoDetail(SalaryInfoRecord em);
	SalaryInfoRecord setSalaryInfoDetail(SalaryInfoRecord am);
	SalaryInfoRecord setSalaryInfoDetail1(SalaryInfoRecord lm);

}
