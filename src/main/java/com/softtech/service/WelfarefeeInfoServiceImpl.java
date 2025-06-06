package com.softtech.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.WelfarefeeInfoFormBean;
import com.softtech.common.ListIDName;
import com.softtech.common.WelfarefeeIDName;
import com.softtech.entity.WelfarefeeInfoEntity;
import com.softtech.mappers.WelfarefeeInfoMapper;
import com.softtech.util.DateUtil;

/**
 * @program @概要: @作成者:孫曄 @作成日:2022-05-25
 * @return:
 */
@Service
public class WelfarefeeInfoServiceImpl implements WelfarefeeInfoService {

    // IOC Mapper
	 @Autowired
    private WelfarefeeInfoMapper welfarefeeInfoMapper;

    /**
     * 概要:画面表示用のquery,入力の収入により、検索する、保険料を納付したいとき、検索用
     *
     * @param:[enterSalary]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
 //   @Override
//    public List<WelfarefeeInfoEntity> getWelfarefeeInfoByEnterSalary(String enterSalary) {
//        List<WelfarefeeInfoEntity> welfarefeeInfoByEnterSalary =
//                welfarefeeInfoMapper.getWelfarefeeInfoByEnterSalary(enterSalary);
//        return welfarefeeInfoByEnterSalary;
//    }

    /**
     * 概要:画面表示用のquery方法２：年度により、検索する
     *
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public List<WelfarefeeInfoEntity> getWelfarefeeInfoByYear(String year) {
        List<WelfarefeeInfoEntity> welfarefeeInfoByYear =
                welfarefeeInfoMapper.getWelfarefeeInfoByYear(year);
        return welfarefeeInfoByYear;
    }

    /**
     * 概要:同时检索年度和收入
     *
     * @param:[enterSalary, year]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/06/01
     */
//    @Override
//    public List<WelfarefeeInfoEntity> getWelfarefeeInfoByYearAndEnterSalary(String enterSalary,
//                                                                            String year) {
//        List<WelfarefeeInfoEntity> welfarefeeInfoByYearAndEnterSalary =
//                welfarefeeInfoMapper.getWelfarefeeInfoByYearAndEnterSalary(enterSalary, year);
//        return welfarefeeInfoByYearAndEnterSalary;
//    }

    /**
     * 概要:更新画面への検索,表示される画面の厚生保険料IDにより、検索する
     *
     * @param:[welfarefeeID]
     * @return:java.util.List<com.softtech.entity.WelfarefeeInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public List<WelfarefeeInfoEntity> getUpdateWelfarefeeInfoList(String welfarefeeID) {
//        List<WelfarefeeInfoEntity> updateWelfarefeeInfoList =
//                welfarefeeInfoMapper.getUpdateWelfarefeeInfoList(welfarefeeID);
//        return updateWelfarefeeInfoList;
    	Integer id = Integer.parseInt(welfarefeeID);
        List<WelfarefeeInfoEntity> updateWelfarefeeInfoList = welfarefeeInfoMapper.getUpdateWelfarefeeInfoList(id);
        return updateWelfarefeeInfoList;
    }

    /**
     * 概要:DB Entityからui　formへ変更
     *
     * @param:[wList]
     * @return:com.softtech.actionForm.WelfarefeeInfoFormBean
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public WelfarefeeInfoFormBean transforEntityToUI(List<WelfarefeeInfoEntity> wList) {
        WelfarefeeInfoFormBean welfarefeeInfoFormBean = new WelfarefeeInfoFormBean();
        for (WelfarefeeInfoEntity welfarefeeInfoEntity : wList) {
            welfarefeeInfoFormBean.setWelfarefeeID(welfarefeeInfoEntity.getWelfarefeeID());
            welfarefeeInfoFormBean.setYear(welfarefeeInfoEntity.getYear());
            welfarefeeInfoFormBean.setArea(welfarefeeInfoEntity.getArea());
            welfarefeeInfoFormBean.setStandSalary(welfarefeeInfoEntity.getStandSalary());
            welfarefeeInfoFormBean.setSalaryFrom(welfarefeeInfoEntity.getSalaryFrom());
            welfarefeeInfoFormBean.setSalaryTo(welfarefeeInfoEntity.getSalaryTo());
            welfarefeeInfoFormBean.setNotCareRatio(welfarefeeInfoEntity.getNotCareRatio());
            welfarefeeInfoFormBean.setCareRatio(welfarefeeInfoEntity.getCareRatio());
            welfarefeeInfoFormBean.setAnnuityRatio(welfarefeeInfoEntity.getAnnuityRatio());
            welfarefeeInfoFormBean.setStatus(welfarefeeInfoEntity.getStatus());
            welfarefeeInfoFormBean.setInsertDate(welfarefeeInfoEntity.getInsertDate());
            welfarefeeInfoFormBean.setUpdateDate(welfarefeeInfoEntity.getUpdateDate());
        }
        return welfarefeeInfoFormBean;
    }

    /**
     * 概要:welfarefeeID最大値+1を取得
     *
     * @param:[]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public String getNextWelfarefeeID() {
//        String maxWelfarefeeID = welfarefeeInfoMapper.getMaxWelfarefeeID();
//        String nextWelfarefeeID = DataUtil.getNextID(maxWelfarefeeID, 1);
//        return nextWelfarefeeID;
    	return null; 
    }

    /**
     * 概要:更新用BeanToEntity
     *
     * @param:[welfarefeeInfoFormBean]
     * @return:com.softtech.entity.WelfarefeeInfoEntity
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    private WelfarefeeInfoEntity transforBeanToEntityByUpdate(
            WelfarefeeInfoFormBean welfarefeeInfoFormBean) {
        WelfarefeeInfoEntity welfarefeeInfoEntity = new WelfarefeeInfoEntity();

        welfarefeeInfoEntity.setWelfarefeeID (welfarefeeInfoFormBean.getWelfarefeeID());


        // 対象年度
        String year = welfarefeeInfoFormBean.getYear();
        welfarefeeInfoEntity.setYear(year);

        // 対象エリア
        String area = welfarefeeInfoFormBean.getArea();
        welfarefeeInfoEntity.setArea(area);

        // 標準報酬
        String standSalary = welfarefeeInfoFormBean.getStandSalary();
        welfarefeeInfoEntity.setStandSalary(standSalary);

        // 給料From
        String salaryFrom = welfarefeeInfoFormBean.getSalaryFrom();
        welfarefeeInfoEntity.setSalaryFrom(salaryFrom);

        // 給料To
        String salaryTo = welfarefeeInfoFormBean.getSalaryTo();
        welfarefeeInfoEntity.setSalaryTo(salaryTo);

        // 介護必要ない料率
        // 前端获得百分号前面的数字，所以直接除100获得对应的小数
//        String notCareRatio = welfarefeeInfoFormBean.getNotCareRatio();
//        BigDecimal hundred = new BigDecimal(100);
//        String notCareRatio1 = new BigDecimal(notCareRatio).divide(hundred).toString();
        welfarefeeInfoEntity.setNotCareRatio(welfarefeeInfoFormBean.getNotCareRatio());

        // 介護必要料率
//        String careRatio = welfarefeeInfoFormBean.getCareRatio();
//        String careRatio1 = new BigDecimal(notCareRatio).divide(hundred).toString();
        welfarefeeInfoEntity.setCareRatio(welfarefeeInfoFormBean.getCareRatio());

        // 厚生年金保険料率
//        String annuityRatio = welfarefeeInfoFormBean.getAnnuityRatio();
//        String annuityRatio1 = new BigDecimal(annuityRatio).divide(hundred).toString();
        welfarefeeInfoEntity.setAnnuityRatio(welfarefeeInfoFormBean.getAnnuityRatio());


        // 利用ステータス
        int status = welfarefeeInfoFormBean.getStatus();
        welfarefeeInfoEntity.setStatus(status);

        // 更新日
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = formatter.format(now);
        welfarefeeInfoEntity.setUpdateDate(format);

        return welfarefeeInfoEntity;
    }

    /**
     * 概要:update
     *
     * @param:[welfarefeeInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public boolean updateWelfarefeeInfo(WelfarefeeInfoFormBean welfarefeeInfoFormBean) {
    	//画面データをEntityに設定する。
        WelfarefeeInfoEntity welfarefeeInfoEntity =
                transforBeanToEntityByUpdate(welfarefeeInfoFormBean);
     // DB登録
        welfarefeeInfoMapper.updateWelfarefeeInfo(welfarefeeInfoEntity);
        return false;
    }

    /**
     * 概要:新規追加用BeanToEntity
     *
     * @param:[welfarefeeInfoFormBean]
     * @return:com.softtech.entity.WelfarefeeInfoEntity
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    private WelfarefeeInfoEntity transforBeanToEntityByInsert(
            WelfarefeeInfoFormBean welfarefeeInfoFormBean) {
        WelfarefeeInfoEntity welfarefeeInfoEntity = new WelfarefeeInfoEntity();

        // 厚生保険料ID+1、新規追加用
//        String maxWelfarefeeID = welfarefeeInfoMapper.getMaxWelfarefeeID();
//        String nextWelfarefeeID = DataUtil.getNextID(maxWelfarefeeID, 1);
//        welfarefeeInfoEntity.setWelfarefeeID(nextWelfarefeeID);

        // 対象年度
        String year = welfarefeeInfoFormBean.getYear();
        welfarefeeInfoEntity.setYear(year);

        // 対象エリア
        String area = welfarefeeInfoFormBean.getArea();
        welfarefeeInfoEntity.setArea(area);

        // 標準報酬
        String standSalary = welfarefeeInfoFormBean.getStandSalary();
        welfarefeeInfoEntity.setStandSalary(standSalary);

        // 給料From
        String salaryFrom = welfarefeeInfoFormBean.getSalaryFrom();
        welfarefeeInfoEntity.setSalaryFrom(salaryFrom);

        // 給料To
        String salaryTo = welfarefeeInfoFormBean.getSalaryTo();
        welfarefeeInfoEntity.setSalaryTo(salaryTo);

        // 介護必要ない料率
        // 前端获得百分号前面的数字，所以直接除100获得对应的小数
       String notCareRatio = welfarefeeInfoFormBean.getNotCareRatio();
//        BigDecimal hundred = new BigDecimal(100);
//        String notCareRatio1 = new BigDecimal(notCareRatio).divide(hundred).toString();
        welfarefeeInfoEntity.setNotCareRatio(notCareRatio);

        // 介護必要料率
        String careRatio = welfarefeeInfoFormBean.getCareRatio();
//        String careRatio1 = new BigDecimal(notCareRatio).divide(hundred).toString();
        welfarefeeInfoEntity.setCareRatio(careRatio);

        // 厚生年金保険料率
        String annuityRatio = welfarefeeInfoFormBean.getAnnuityRatio();
//        String annuityRatio1 = new BigDecimal(annuityRatio).divide(hundred).toString();
        welfarefeeInfoEntity.setAnnuityRatio(annuityRatio);

        // 利用ステータス
        int status = welfarefeeInfoFormBean.getStatus();
        welfarefeeInfoEntity.setStatus(status);

        // 作成日
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = formatter.format(now);
        welfarefeeInfoEntity.setInsertDate(format);

        // 更新日
        welfarefeeInfoEntity.setUpdateDate(format);

        return welfarefeeInfoEntity;
    }

    /**
     * 概要:insert
     *
     * @param:[welfarefeeInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public boolean insertWelfarefeeInfo(WelfarefeeInfoFormBean welfarefeeInfoFormBean) {
        // 画面からデータをEntityに伝送
        WelfarefeeInfoEntity welfarefeeInfoEntity1 =
                transforBeanToEntityByInsert(welfarefeeInfoFormBean);
        // DBに登録
        welfarefeeInfoMapper.insertWelfarefeeInfo(welfarefeeInfoEntity1);
        return true;
    }


	@Override
	public List<WelfarefeeIDName> getYear() {
		List<WelfarefeeIDName> welfarefeeIDNameList = new ArrayList<>();
		// DBから年度を取得
		welfarefeeIDNameList= welfarefeeInfoMapper.getYear();

		return welfarefeeIDNameList;
	}

	/*public ArrayList<WelfarefeeIDName> getOldYears(int oldYear){
		return (ArrayList<WelfarefeeIDName>) DateUtil.getYears1(oldYear);
	}*/
	public ArrayList<ListIDName> getOldYears(int oldYear){
		return (ArrayList<ListIDName>) DateUtil.getYears(oldYear);
	}

}
