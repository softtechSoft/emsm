package com.softtech.mappers;

import com.softtech.common.IncomeTaxIDName;
import com.softtech.entity.IncomeTaxInfoEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-10
 * @return:
 */
@Mapper
public interface IncomeTaxInfoMapper {

    //画面表示用のquery,入力の年度により、検索する
    List<IncomeTaxInfoEntity> getIncomeTaxByYear(@Param("year") String year);

    //更新画面への検索,表示される画面の所得税IDにより、検索する
    List<IncomeTaxInfoEntity> getUpdateIncomeTaxByIncomeTaxID(@Param("incomeTaxID") String incomeTaxID);

    //最大のIncomeTaxIDを取得、+1用
    String getMaxIncomeTaxID();

    //更新の時、更新画面の年度を表示する用
    List<IncomeTaxIDName> getYear(@Param("year") String year);

    void updateIncomeTax(IncomeTaxInfoEntity incomeTaxInfoEntity);

    void insertIncomeTaxInfo(IncomeTaxInfoEntity incomeTaxInfoEntity);


}
