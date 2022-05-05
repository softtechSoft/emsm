package com.softtech.mappers;

import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

/**
 * 概要：基本給
 *
 * 作成者：孫曄@ソフトテク
 * 作成日：2022/5/5
 */

@Mapper
public interface BaseSalaryInfoMapper {
	//提出
	int insertBaseSalaryInfo(Map<String, String> map);
	//検索
	String queryBaseSalaryInfo(String employeeID);
	//修正
	int updateBaseSalaryInfo(Map<String,String> map);
}
