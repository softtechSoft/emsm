package com.softtech.mappers;

import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.BaseSalaryInfoEntity;
import com.softtech.entity.ContractInfoEntity;

/**
 * 概要：基本給の検索、更新、入力
 *
 * 作成者：孫曄@ソフトテク
 * 作成日：2022/5/5
 */

@Mapper
public interface BaseSalaryInfoMapper {

	List<BaseSalaryInfoEntity> getBaseSalaryInfoList(@Param("employeeID")String employeeID);
}