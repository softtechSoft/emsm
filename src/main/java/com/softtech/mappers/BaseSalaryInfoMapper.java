package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.BaseSalaryInfoEntity;

/**
 * 概要：基本給の検索、更新、入力
 *
 * 作成者：孫曄@ソフトテク
 * 作成日：2022/5/5
 */

@Mapper
public interface BaseSalaryInfoMapper {
	//データ画面
	List<BaseSalaryInfoEntity> getBaseSalaryInfoList(@Param("employeeID")String employeeID);
	//更新画面
	List<BaseSalaryInfoEntity> getBaseSalaryInfo(@Param("baseSalaryID")String baseSalaryID);
	//update
	public void updateBaseSalaryInfo(BaseSalaryInfoEntity baseSalaryInfoEntity);


	//ID最大値+1を取得
	public String getMaxBaseSalaryID();
	//insert
	public void insertBaseSalaryInfo(BaseSalaryInfoEntity baseSalaryInfoEntity);
}
