package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.ContractInfoEntity;



@Mapper
 public interface ContractInfoMapper {
	List<ContractInfoEntity> getContractInfoList(@Param("employeeID")String employeeID);
	List<ContractInfoEntity> getContractInfo(@Param("contractID")String contractID);

}

