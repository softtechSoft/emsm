package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.common.ListIDName;
import com.softtech.entity.Employee;
import com.softtech.entity.EmplyinsrateInfoEntity;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-08
 * @return:
 */
@Mapper
public interface EmplyinsrateMapper {
    //画面表示用のquery,入力の年度により、検索する
    List<EmplyinsrateInfoEntity> getEmplyinsrateInfoByYear(@Param("year") String year);

    //更新画面への検索,表示される画面の厚生保険料IDにより、検索する
    List<EmplyinsrateInfoEntity> getUpdateEmplyinsrateInfoList(@Param("emplyinsrateID") String emplyinsrateID);

    //ID最大値+1を取得
    String getMaxEmplyinsrateID();

    //更新画面の年度を表示する用
    List<ListIDName> getYear();

    //update
    void updateEmplyinsrate(EmplyinsrateInfoEntity emplyinsrateInfoEntity);

    //insert
    void insertEmplyinsrate(EmplyinsrateInfoEntity emplyinsrateInfoEntity);

  //DBから社員リスト生成
    List<Employee> getEmployee();

}
