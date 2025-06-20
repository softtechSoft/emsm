package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softtech.actionForm.WelfarefeeInfoFormBean;
import com.softtech.common.ListIDName;
import com.softtech.common.WelfarefeeIDName;
import com.softtech.entity.WelfarefeeInfoEntity;

/**
 * @program @概要: @作成者:孫曄 @作成日:2022-05-25
 * @return:
 */
@Service
public interface WelfarefeeInfoService {
    /**
     * 概要:画面表示用のquery,入力の収入により、検索する、保険料を納付したいとき、検索用
     *
     * @param:[enterSalary]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
   // List<WelfarefeeInfoEntity> getWelfarefeeInfoByEnterSalary(String enterSalary);

    /**
     * 概要:画面表示用のquery方法２：年度により、検索する
     *
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    List<WelfarefeeInfoEntity> getWelfarefeeInfoByYear(String year);

    /**
     * 概要:同时检索年度和收入
     *
     * @param:[enterSalary, year]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/06/01
     */
    //List<WelfarefeeInfoEntity> getWelfarefeeInfoByYearAndEnterSalary(String enterSalary,
    //                                                                 String year);

    /**
     * 概要:更新画面への検索,表示される画面の厚生保険料IDにより、検索する
     *
     * @param:[welfarefeeID]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    List<WelfarefeeInfoEntity> getUpdateWelfarefeeInfoList(String welfarefeeID);

    /**
     * 概要:DB Entityからui　formへ変更
     *
     * @param:[wList]
     * @return:com.softtech.actionForm.WelfarefeeInfoFormBean
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    WelfarefeeInfoFormBean transforEntityToUI(List<WelfarefeeInfoEntity> wList);

    /**
     * 概要:welfarefeeID最大値+1を取得
     *
     * @param:[]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    String getNextWelfarefeeID();

    /**
     * 概要:update
     *
     * @param:[welfarefeeInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    boolean updateWelfarefeeInfo(WelfarefeeInfoFormBean welfarefeeInfoFormBean);

    /**
     * 概要:insert
     *
     * @param:[welfarefeeInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    boolean insertWelfarefeeInfo(WelfarefeeInfoFormBean welfarefeeInfoFormBean);

	/**
	 * 概要:更新画面の年度を表示する用
	 *
	 * @param:[]
	 * @return:java.util.List<com.softtech.entity.EmplyinsrateInfoEntity>
	 * @author:孫曄@SOFTTECH
	 * @date:2022/08/08
	 */
    List<WelfarefeeIDName> getYear();
    /*
     * 機能：指定数の過去年度リストを生成する
	 *
	 * @param 過去年度数
	 * @return 過去年度リスト
	 * @exception なし
     */
   // ArrayList<WelfarefeeIDName> getOldYears(int oldYear);
    ArrayList<ListIDName> getOldYears(int oldYear);
    
    /**
     * 全てのデータを取得
     */
    List<WelfarefeeInfoEntity> getAllWelfarefeeInfo();
}
