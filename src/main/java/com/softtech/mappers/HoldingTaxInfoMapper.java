package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.Employee;
import com.softtech.entity.HoldingTaxInfoEntity;

/**
 * @program
 * @概要:
 * @作成者:黄
 * @作成日:2025-11-05
 * @return:
 */
@Mapper
public interface HoldingTaxInfoMapper {

    //検索用のquery,社員IDにより、検索する
    List<HoldingTaxInfoEntity> getHoldingTaxByEmployeeID(@Param("employeeID") String employeeID);

    //更新画面への検索,表示される画面の所得税IDにより、検索する
    List<HoldingTaxInfoEntity> getUpdateHoldingTaxByHoldingTaxID(@Param("holdingTaxID") String holdingTaxID);

    //最大のIncomeTaxIDを取得、+1用
    String getMaxHoldingTaxID();

    void updateHoldingTax(HoldingTaxInfoEntity holdingTaxInfoEntity);

    void insertHoldingTaxInfo(HoldingTaxInfoEntity holdingTaxInfoEntity);

    //DBから社員リスト生成
    List<Employee> getEmployee();

    List<HoldingTaxInfoEntity> getHoldingTaxByCondition(@Param("employeeID") String employeeID,
                                                        @Param("year") String year);

    List<String> getDistinctYears();

}