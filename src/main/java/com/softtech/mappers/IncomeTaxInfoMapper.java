package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.common.IncomeTaxIDName;
import com.softtech.entity.Employee;
import com.softtech.entity.IncomeTaxInfoEntity;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-10
 * @return:
 */
@Mapper
public interface IncomeTaxInfoMapper {

    //検索用のquery,社員IDにより、検索する
    List<IncomeTaxInfoEntity> getIncomeTaxByEmployeeID(@Param("employeeID") String employeeID);

    //更新画面への検索,表示される画面の所得税IDにより、検索する
    List<IncomeTaxInfoEntity> getUpdateIncomeTaxByIncomeTaxID(@Param("incomeTaxID") String incomeTaxID);

    //最大のIncomeTaxIDを取得、+1用
//    String getMaxIncomeTaxID();

    //更新の時、更新画面の年度を表示する用
    List<IncomeTaxIDName> getYear();

    void updateIncomeTax(IncomeTaxInfoEntity incomeTaxInfoEntity);

    void insertIncomeTaxInfo(IncomeTaxInfoEntity incomeTaxInfoEntity);

    //DBから社員リスト生成
    List<Employee> getEmployee();


}
