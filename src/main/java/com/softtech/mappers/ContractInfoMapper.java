package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.ContractInfoEntity;



@Mapper
 public interface ContractInfoMapper {
	//検索処理の契約リスト
	List<ContractInfoEntity> getContractInfoList(@Param("employeeID")String employeeID);
	//更新処理の契約情報
	List<ContractInfoEntity> getContractInfo(@Param("contractID")String contractID);
	//画面データをDBデータ登録
	public void updateContractInfo(ContractInfoEntity contractInfoEntity);
	// contrctidの最大値を取得
	public String getMaxContractID();
}

