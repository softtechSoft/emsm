package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softtech.actionForm.EmplyinsrateInfoFormBean;
import com.softtech.common.ListIDName;
import com.softtech.entity.EmplyinsrateInfoEntity;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-08
 * @return:
 */
@Service
public interface EmplyInsrateInfoService {

    /**
     * 概要:画面表示用のquery,入力の年度により、検索する
     *
     * @param:[]
     * @return:java.util.List<com.softtech.entity.EmplyinsrateInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    List<EmplyinsrateInfoEntity> getEmplyinsrateInfoByYear(String year);

    /**
     * 概要:更新画面への検索,表示される画面の厚生保険料IDにより、検索する
     *
     * @param:[]
     * @return:java.util.List<com.softtech.entity.EmplyinsrateInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    List<EmplyinsrateInfoEntity> getUpdateEmplyinsrateInfoList(String emplyinsrateID);

    /**
     * 概要:DB Entityからui使用のformへ変更
     *
     * @param:[eList]
     * @return:com.softtech.actionForm.EmplyinsrateInfoFormBean
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    EmplyinsrateInfoFormBean transforEntityToUI(List<EmplyinsrateInfoEntity> eList);

    /**
     * 概要:ID最大値+1を取得
     *
     * @param:[]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    String getNextEmplyinsrateID();

    /**
     * 概要:update
     *
     * @param:[emplyinsrateInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    boolean updateEmplyinsrateInfo(EmplyinsrateInfoFormBean emplyinsrateInfoFormBean);

    /**
     * 概要:insert
     *
     * @param:[emplyinsrateInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    boolean insertEmplyinsrateInfo(EmplyinsrateInfoFormBean emplyinsrateInfoFormBean);

    /**
     * 概要:更新画面の年度を表示する用
     *
     * @param:[]
     * @return:java.util.List<com.softtech.entity.EmplyinsrateInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    List<ListIDName> getYear();

    /*
     * 機能：指定数の過去年度リストを生成する
	 *
	 * @param 過去年度数
	 * @return 過去年度リスト
	 * @exception なし
     */
    //ArrayList<EmplyinsrateIDName> getOldYears(int oldYear);
    ArrayList<ListIDName> getOldYears(int oldYear);
    
    /**
     * 全てのデータを取得
     */
    List<EmplyinsrateInfoEntity> getAllEmplyinsrateInfo();

}
