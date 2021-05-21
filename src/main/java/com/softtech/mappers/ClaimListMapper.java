package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.ClaimInfo;

@Mapper
public interface ClaimListMapper {
	List<ClaimInfo>getClaimInfolist(@Param("month")  String month,@Param("companyName")String companyName);

}
