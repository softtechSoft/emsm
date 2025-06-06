package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.common.WelfarefeeIDName;
import com.softtech.entity.Employee;
import com.softtech.entity.WelfarefeeInfoEntity;

/**
 * @program @概要:厚生保険料データ画面の更新、新規追加 @作成者:孫曄 @作成日:2022-05-24
 * @return:
 */
@Mapper
public interface WelfarefeeInfoMapper {
    // 画面表示用のquery,入力の収入により、検索する、保険料を納付したいとき、検索用
   // List<WelfarefeeInfoEntity> getWelfarefeeInfoByEnterSalary(
    //        @Param("enterSalary") String enterSalary);

    // 画面表示用のquery方法２：年度により、検索する
    List<WelfarefeeInfoEntity> getWelfarefeeInfoByYear(@Param("year") String year);

    //同时检索年度和收入
    //List<WelfarefeeInfoEntity> getWelfarefeeInfoByYearAndEnterSalary(@Param("enterSalary") String enterSalary, @Param("year") String year);

    // 更新画面への検索,表示される画面の厚生保険料IDにより、検索する
    List<WelfarefeeInfoEntity> getUpdateWelfarefeeInfoList(@Param("welfarefeeID")Integer welfarefeeID);

    // update
    void updateWelfarefeeInfo(WelfarefeeInfoEntity welfarefeeInfoEntity);

    // 最大のwelfarefeeIDを探す
    Integer getMaxWelfarefeeID();
    // insert
    void insertWelfarefeeInfo(WelfarefeeInfoEntity welfarefeeInfoEntity);

    // 選択枠
    //List<WelfarefeeIDName> getWelfarefeeInfoID();
    List<WelfarefeeIDName> getYear();
  //DBから社員リスト生成
    List<Employee> getEmployee();
}
