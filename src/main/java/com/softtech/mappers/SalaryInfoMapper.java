package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.common.SalaryInfocommom;
import com.softtech.common.SalaryInfocommom2;
import com.softtech.entity.SalaryInfo;

@Mapper
public interface SalaryInfoMapper {
	List<SalaryInfo> getSalaryInfoDetail(SalaryInfocommom em);
	List<SalaryInfo> getSalaryInfoDetail1(String sting);
	SalaryInfocommom2 setSalaryInfoDetail(SalaryInfocommom2 am);
	SalaryInfocommom2 setSalaryInfoDetail1(SalaryInfocommom2 lm);

}
