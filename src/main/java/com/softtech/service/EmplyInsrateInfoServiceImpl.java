package com.softtech.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.EmplyinsrateInfoFormBean;
import com.softtech.common.ListIDName;
import com.softtech.entity.EmplyinsrateInfoEntity;
import com.softtech.mappers.EmplyinsrateMapper;
import com.softtech.util.DataUtil;
import com.softtech.util.DateUtil;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-08
 * @return:
 */
@Service
public class EmplyInsrateInfoServiceImpl implements EmplyInsrateInfoService {
    //IOCMapper
    @Autowired
    private EmplyinsrateMapper emplyinsrateMapper;


    /**
     * 概要:画面表示用のquery,入力の年度により、検索する
     *
     * @param year
     * @param:[]
     * @return:java.util.List<com.softtech.entity.EmplyinsrateInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @Override
    public List<EmplyinsrateInfoEntity> getEmplyinsrateInfoByYear(String year) {
        List<EmplyinsrateInfoEntity> emplyinsrateInfoByYear =
                emplyinsrateMapper.getEmplyinsrateInfoByYear(year);
        return emplyinsrateInfoByYear;
    }

    /**
     * 概要:更新画面への検索,表示される画面の厚生保険料IDにより、検索する
     *
     * @param emplyinsrateID
     * @param:[]
     * @return:java.util.List<com.softtech.entity.EmplyinsrateInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @Override
    public List<EmplyinsrateInfoEntity> getUpdateEmplyinsrateInfoList(String emplyinsrateID) {
        List<EmplyinsrateInfoEntity> updateEmplyinsrateInfoList =
                emplyinsrateMapper.getUpdateEmplyinsrateInfoList(emplyinsrateID);
        return updateEmplyinsrateInfoList;
    }

    /**
     * 概要:DB Entityからui使用のformへ変更,更新画面の生成の時使用
     *
     * @param bList
     * @param:[eList]
     * @return:com.softtech.actionForm.EmplyinsrateInfoFormBean
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @Override
    public EmplyinsrateInfoFormBean transforEntityToUI(List<EmplyinsrateInfoEntity> bList) {
        EmplyinsrateInfoFormBean emplyinsrateInfoFormBean = new EmplyinsrateInfoFormBean();
        for (EmplyinsrateInfoEntity emplyinsrateInfoEntity : bList) {
            emplyinsrateInfoFormBean.setEmplyinsrateID(emplyinsrateInfoEntity.getEmplyinsrateID());
            emplyinsrateInfoFormBean.setYear(emplyinsrateInfoEntity.getYear());
            emplyinsrateInfoFormBean.setLaborBurdenRate(emplyinsrateInfoEntity.getLaborBurdenRate());
            emplyinsrateInfoFormBean.setEmployerBurdenRate(emplyinsrateInfoEntity.getEmployerBurdenRate());
            emplyinsrateInfoFormBean.setIndustrialAccidentInsuranceRate(emplyinsrateInfoEntity.getIndustrialAccidentInsuranceRate());
            emplyinsrateInfoFormBean.setContributionRate(emplyinsrateInfoEntity.getContributionRate());
            emplyinsrateInfoFormBean.setStatus(emplyinsrateInfoEntity.getStatus());
            emplyinsrateInfoFormBean.setUpdateDate(emplyinsrateInfoEntity.getUpdateDate());
            emplyinsrateInfoFormBean.setInsertDate(emplyinsrateInfoEntity.getInsertDate());
        }
        return emplyinsrateInfoFormBean;
    }

    /**
     * 概要:ID最大値+1を取得
     *
     * @param:[]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @Override
    public String getNextEmplyinsrateID() {
        String maxEmplyinsrateID = emplyinsrateMapper.getMaxEmplyinsrateID();
        String nextEmplyinsrateID = DataUtil.getNextID(maxEmplyinsrateID, 1);
        return nextEmplyinsrateID;
    }
    /**
     * 概要:更新用BeanToEntity
     *
     * @param:[emplyinsrateInfoBean]
     * @return:com.softtech.entity.EmplyinsrateInfoEntity
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    private EmplyinsrateInfoEntity transforBeanToEntityByUpdate(EmplyinsrateInfoFormBean emplyinsrateInfoFormBean) {
        EmplyinsrateInfoEntity emplyinsrateInfoEntity = new EmplyinsrateInfoEntity();

        emplyinsrateInfoEntity.setEmplyinsrateID(emplyinsrateInfoFormBean.getEmplyinsrateID());
        //年度
        //String year = emplyinsrateInfoFormBean.getYear();
        emplyinsrateInfoEntity.setYear(emplyinsrateInfoFormBean.getYear());

        //雇用保険労働者負担料率‰
        //String laborBurdenRate = emplyinsrateInfoFormBean.getLaborBurdenRate();
       // BigDecimal thousand = new BigDecimal(1000);
        //String laborBurdenRate1 = new BigDecimal(laborBurdenRate).divide(thousand).toString();
        emplyinsrateInfoEntity.setLaborBurdenRate(emplyinsrateInfoFormBean.getLaborBurdenRate());

        //雇用保険事業主負担料率‰
       // String employerBurdenRate = emplyinsrateInfoFormBean.getEmployerBurdenRate();
       // String employerBurdenRate1 = new BigDecimal(employerBurdenRate).divide(thousand).toString();
        emplyinsrateInfoEntity.setEmployerBurdenRate(emplyinsrateInfoFormBean.getEmployerBurdenRate());

        //労災保険料率(全額事業主)‰
        //String industrialAccidentInsuranceRate =
                //emplyinsrateInfoFormBean.getIndustrialAccidentInsuranceRate();
        //String industrialAccidentInsuranceRate1 =
               // new BigDecimal(industrialAccidentInsuranceRate).divide(thousand).toString();
        emplyinsrateInfoEntity.setIndustrialAccidentInsuranceRate(emplyinsrateInfoFormBean.getIndustrialAccidentInsuranceRate());

        //一般拠出金料率(全額事業主)‰
        //String contributionRate = emplyinsrateInfoFormBean.getContributionRate();
       // String contributionRate1 = new BigDecimal(contributionRate).divide(thousand).toString();
        emplyinsrateInfoEntity.setContributionRate(emplyinsrateInfoFormBean.getContributionRate());

        //利用ステータス
        //String status = emplyinsrateInfoFormBean.getStatus();
        emplyinsrateInfoEntity.setStatus(emplyinsrateInfoFormBean.getStatus());

        //更新日
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = formatter.format(now);
        emplyinsrateInfoEntity.setUpdateDate(format);

        return emplyinsrateInfoEntity;
    }

    /**
     * 概要:update
     *
     * @param emplyinsrateInfoFormBean
     * @param:[emplyinsrateInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @Override
    public boolean updateEmplyinsrateInfo(EmplyinsrateInfoFormBean emplyinsrateInfoFormBean) {
    	//画面データをEntityに設定する。
        EmplyinsrateInfoEntity emplyinsrateInfoEntity =
                transforBeanToEntityByUpdate(emplyinsrateInfoFormBean);
     // DB登録
        emplyinsrateMapper.updateEmplyinsrate(emplyinsrateInfoEntity);
        return false;
    }


    /**概要:新規追加用BeanToEntity
    *@param:[emplyinsrateInfoFormBean]
    *@return:com.softtech.entity.EmplyinsrateInfoEntity
    *@author:孫曄@SOFTTECH
    *@date:2022/08/09
    */
    private EmplyinsrateInfoEntity transforBeanToEntityByInsert(EmplyinsrateInfoFormBean emplyinsrateInfoFormBean) {
        EmplyinsrateInfoEntity emplyinsrateInfoEntity = new EmplyinsrateInfoEntity();


        //新規追加用　ID+1を取得
        String maxEmplyinsrateID = emplyinsrateMapper.getMaxEmplyinsrateID();
        String nextEmplyinsrateID = DataUtil.getNextID(maxEmplyinsrateID, 1);
        emplyinsrateInfoEntity.setEmplyinsrateID(nextEmplyinsrateID);
        //年度
        String year = emplyinsrateInfoFormBean.getYear();
        emplyinsrateInfoEntity.setYear(year);

        //雇用保険労働者負担料率‰
        String laborBurdenRate = emplyinsrateInfoFormBean.getLaborBurdenRate();
        //BigDecimal thousand = new BigDecimal(1000);
        //String laborBurdenRate1 = new BigDecimal(laborBurdenRate).divide(thousand).toString();
        emplyinsrateInfoEntity.setLaborBurdenRate(laborBurdenRate);

        //雇用保険事業主負担料率‰
        String employerBurdenRate = emplyinsrateInfoFormBean.getEmployerBurdenRate();
       // String employerBurdenRate1 = new BigDecimal(employerBurdenRate).divide(thousand).toString();
        emplyinsrateInfoEntity.setEmployerBurdenRate(employerBurdenRate);

        //労災保険料率(全額事業主)‰
        String industrialAccidentInsuranceRate =
                emplyinsrateInfoFormBean.getIndustrialAccidentInsuranceRate();
       // String industrialAccidentInsuranceRate1 =
           //     new BigDecimal(industrialAccidentInsuranceRate).divide(thousand).toString();
        emplyinsrateInfoEntity.setIndustrialAccidentInsuranceRate(industrialAccidentInsuranceRate);

        //一般拠出金料率(全額事業主)‰
        String contributionRate = emplyinsrateInfoFormBean.getContributionRate();
       // String contributionRate1 = new BigDecimal(contributionRate).divide(thousand).toString();
        emplyinsrateInfoEntity.setContributionRate(contributionRate);

        //利用ステータス
        String status = emplyinsrateInfoFormBean.getStatus();
        emplyinsrateInfoEntity.setStatus(status);

        //更新日
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = formatter.format(now);
        emplyinsrateInfoEntity.setUpdateDate(format);

        //作成日
        emplyinsrateInfoEntity.setInsertDate(format);

        return emplyinsrateInfoEntity;

    }

    /**
     * 概要:insert
     *
     * @param emplyinsrateInfoFormBean
     * @param:[emplyinsrateInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @Override
    public boolean insertEmplyinsrateInfo(EmplyinsrateInfoFormBean emplyinsrateInfoFormBean) {
        //画面からデータをEntityに伝送
        EmplyinsrateInfoEntity emplyinsrateInfoEntity1 =
                transforBeanToEntityByInsert(emplyinsrateInfoFormBean);
        //DBに登録
        emplyinsrateMapper.insertEmplyinsrate(emplyinsrateInfoEntity1);
        return true;
    }

    /**
     * 概要:更新画面の年度を表示する用
     *
     * @param:[]
     * @return:java.util.List<com.softtech.entity.EmplyinsrateInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/08/08
     */
    @Override
    public List<ListIDName> getYear() {
        List<ListIDName>listIDNameList = new ArrayList<>();
        // DBから年度を取得
        listIDNameList = emplyinsrateMapper.getYear();

        return listIDNameList;
    }
    /*
     * 機能：指定数の過去年度リストを生成する
	 *
	 * @param 過去年度数
	 * @return 過去年度リスト
	 * @exception なし
     */
	/* public ArrayList<EmplyinsrateIDName> getOldYears(int oldYear){
		return (ArrayList<EmplyinsrateIDName>) DateUtil.getYears(oldYear);
	}*/
    public ArrayList<ListIDName> getOldYears(int oldYear){
    	return (ArrayList<ListIDName>) DateUtil.getYears(oldYear);
    }
    
    /**
     * 概要:全てのデータを取得
     */
    @Override
    public List<EmplyinsrateInfoEntity> getAllEmplyinsrateInfo() {
        List<EmplyinsrateInfoEntity> allEmplyinsrateInfo = 
                emplyinsrateMapper.getAllEmplyinsrateInfo();
        return allEmplyinsrateInfo;
    }
    
}

