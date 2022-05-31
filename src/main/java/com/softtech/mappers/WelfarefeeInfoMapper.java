package com.softtech.mappers;

import com.softtech.common.WelfarefeeIDName;
import com.softtech.entity.WelfarefeeInfo;
import com.softtech.entity.WelfarefeeInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program @概要:厚生保険料データ画面の更新、新規追加 @作成者:孫曄 @作成日:2022-05-24
 * @return:
 */
@Mapper
public interface WelfarefeeInfoMapper {
  // 画面表示用のquery,入力の収入により、検索する、保険料を納付したいとき、検索用
  List<WelfarefeeInfoEntity> getWelfarefeeInfoByEnterSalary(
      @Param("enterSalary") String enterSalary);
  // 画面表示用のquery方法２：年度により、検索する
  List<WelfarefeeInfoEntity> getWelfarefeeInfoByYear(@Param("year") String year);

  // 更新画面への検索,表示される画面の厚生保険料IDにより、検索する
  List<WelfarefeeInfoEntity> getUpdateWelfarefeeInfoList(String welfarefeeID);

  // update
  void updateWelfarefeeInfo(WelfarefeeInfoEntity welfarefeeInfoEntity);

  // 最大のwelfarefeeIDを探す
  String getMaxWelfarefeeID();

  // insert
  void insertWelfarefeeInfo(WelfarefeeInfoEntity welfarefeeInfoEntity);

  // 選択枠
  //List<WelfarefeeIDName> getWelfarefeeInfoID();
}
