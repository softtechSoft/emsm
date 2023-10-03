package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softtech.actionForm.WelfareBabyInfoFormBean;
import com.softtech.common.RateIDName;
import com.softtech.entity.WelfareBabyInfoEntity;
@Service
public interface WelfareBabyInfoService {
	 /**
     * 概要:画面表示用のquery方法２：年度により、検索する
     *
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    List<WelfareBabyInfoEntity> getWelfareBabyInfoByYear(String year);
    /**
     * 概要:更新画面への検索,表示される画面の厚生保険料IDにより、検索する
     *
     * @param:[welfarefeeID]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    List<WelfareBabyInfoEntity> getUpdateWelfareBabyInfoList(String rateID);
    /**
     * 概要:DB Entityからui　formへ変更
     *
     * @param:[wList]
     * @return:com.softtech.actionForm.welfareBabyInfoFormBean
     * @author:孫曄@SOFTTECH
     * @date:2023/09/13
     */
    WelfareBabyInfoFormBean transforEntityToUI(List<WelfareBabyInfoEntity> wList);
    /**
     * 概要:RateID最大値+1を取得
     *
     * @param:[]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2023/09/13
     */
     String getNextRateID();
    /**
     * 概要:update
     *
     * @param:[welfareBabyInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2023/09/13
     */
    boolean updateWelfareBabyInfo( WelfareBabyInfoFormBean welfareBabyInfoFormBean);

    /**
     * 概要:insert
     *
     * @param:[welfareBabyInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2023/09/13
     */
    boolean insertWelfareBabyInfo(WelfareBabyInfoFormBean welfareBabyInfoFormBean);
    /**
	 * 概要:更新画面の年度を表示する用
	 *
	 * @param:[]
	 * @return:java.util.List<com.softtech.entity.EmplyinsrateInfoEntity>
	 * @author:孫曄@SOFTTECH
	 * @date:2022/08/08
	 */
    List<RateIDName> getYear();
    /*
     * 機能：指定数の過去年度リストを生成する
	 *
	 * @param 過去年度数
	 * @return 過去年度リスト
	 * @exception なし
     */
	ArrayList<RateIDName> getOldYears(int i);
	//boolean updateWelfareBabyInfo(WelfareBabyInfoEntity welfareBabyInfoEntity1);
	//boolean insertWelfareBabyInfo(WelfareBabyInfoFormBean welfarebabyInfoFormBean);

}
