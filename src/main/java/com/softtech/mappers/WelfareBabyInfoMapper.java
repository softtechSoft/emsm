package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.common.RateIDName;
import com.softtech.entity.Employee;
import com.softtech.entity.WelfareBabyInfoEntity;
@Mapper
public interface WelfareBabyInfoMapper {
	   // 画面表示用のquery方法２：年度により、検索する
    List<WelfareBabyInfoEntity> getWelfareBabyInfoByYear(@Param("year") String year);
    // 更新画面への検索,表示される画面の厚生保険料IDにより、検索する
    List<WelfareBabyInfoEntity> getUpdateWelfareBabyInfoList(@Param("rateID")String rateID);

    // update
    void updateWelfareBabyInfo(WelfareBabyInfoEntity welfareBabyInfoEntity);

    // 最大のRateIDを探す
//    String getMaxRateID();
    // insert
    void insertWelfareBabyInfo(WelfareBabyInfoEntity welfareBabyInfoEntity);
    // 選択枠
    //List<IDName> getInfoID();
    List<RateIDName> getYear();
  //DBから社員リスト生成
    List<Employee> getEmployee();

}
