package com.softtech.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.softtech.actionForm.WelfareBabyInfoFormBean;
import com.softtech.common.ListIDName;
import com.softtech.common.RateIDName;
import com.softtech.entity.WelfareBabyInfoEntity;
import com.softtech.mappers.WelfareBabyInfoMapper;
import com.softtech.util.DataUtil;
import com.softtech.util.DateUtil;

@Service
public class WelfareBabyInfoServiceImpl implements WelfareBabyInfoService {

	   // IOC Mapper
    @Resource
    private WelfareBabyInfoMapper welfareBabyInfoMapper;

    /**
     * 概要:画面表示用のquery方法２：年度により、検索する
     *
     * @param:[year]
     * @return:java.util.List<com.softtech.entity.WelfareBabyInfoEntity>
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public List<WelfareBabyInfoEntity> getWelfareBabyInfoByYear(String year) {
        List<WelfareBabyInfoEntity> welfareBabyInfoByYear =
        		welfareBabyInfoMapper.getWelfareBabyInfoByYear(year);
        return welfareBabyInfoByYear;
    }
    /**
     * 概要:更新画面への検索,表示される画面の厚生保険料IDにより、検索する
     *
     * @param:[rateID]
     * @return:java.util.List<com.softtech.entity.welfareBabyInfoEntity>
     * @author:@SOFTTECH
     * @date:2023/09/13
     */
    @Override
    public List<WelfareBabyInfoEntity> getUpdateWelfareBabyInfoList(String rateID) {
        List<WelfareBabyInfoEntity> updateWelfareBabyInfoList =
        		welfareBabyInfoMapper.getUpdateWelfareBabyInfoList(rateID);
        return updateWelfareBabyInfoList;
    }

    /**
     * 概要:DB Entityからui　formへ変更
     *
     * @param:[wList]
     * @return:com.softtech.actionForm.WelfareBabyInfoFormBean
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public WelfareBabyInfoFormBean transforEntityToUI(List<WelfareBabyInfoEntity> wList) {
    	WelfareBabyInfoFormBean welfareBabyInfoFormBean = new WelfareBabyInfoFormBean();
        for (WelfareBabyInfoEntity welfareBabyInfoEntity : wList) {
        	welfareBabyInfoFormBean.setRateID(welfareBabyInfoEntity.getRateID());
        	welfareBabyInfoFormBean.setYear(welfareBabyInfoEntity.getYear());
        	welfareBabyInfoFormBean.setArea(welfareBabyInfoEntity.getArea());
        	welfareBabyInfoFormBean.setRate(welfareBabyInfoEntity.getRate());
        	welfareBabyInfoFormBean.setStatus(welfareBabyInfoEntity.getStatus());
        	welfareBabyInfoFormBean.setInsertDate(welfareBabyInfoEntity.getInsertDate());
        	welfareBabyInfoFormBean.setUpdateDate(welfareBabyInfoEntity.getUpdateDate());
        }
        return welfareBabyInfoFormBean;
    }
    /**
     * 概要:rateID最大値+1を取得
     *
     * @param:[]
     * @return:java.lang.String
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public String getNextRateID() {
        String maxRateID = welfareBabyInfoMapper.getMaxRateID();
        String nextRateID = DataUtil.getNextID(maxRateID, 1);
        return nextRateID;
    }

    /**
     * 概要:更新用BeanToEntity
     *
     * @param:[WelfareBabyInfoFormBean]
     * @return:com.softtech.entity.WelfareBabyInfoEntity
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    private WelfareBabyInfoEntity transforBeanToEntityByUpdate(
    		WelfareBabyInfoFormBean welfareBabyInfoFormBean) {
    	WelfareBabyInfoEntity welfareBabyInfoEntity = new WelfareBabyInfoEntity();
    	 welfareBabyInfoEntity.setRateID(welfareBabyInfoFormBean.getRateID());

        // 対象年度
        String year = welfareBabyInfoFormBean.getYear();
        welfareBabyInfoEntity.setYear(year);

        // 対象エリア
        String area = welfareBabyInfoFormBean.getArea();
        welfareBabyInfoEntity.setArea(area);

        // 標準報酬
        String rate = welfareBabyInfoFormBean.getRate();
        welfareBabyInfoEntity.setRate(rate);

        // 利用ステータス
        int status = welfareBabyInfoFormBean.getStatus();
        welfareBabyInfoEntity.setStatus(status);

        // 更新日
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = formatter.format(now);
        welfareBabyInfoEntity.setUpdateDate(format);

        return welfareBabyInfoEntity;

    }
    /**
     * 概要:update
     *
     * @param:[WelfareBabyInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public boolean updateWelfareBabyInfo(WelfareBabyInfoFormBean welfareBabyInfoFormBean) {
    	//画面データをEntityに設定する。
    	WelfareBabyInfoEntity welfareBabyInfoEntity =
                transforBeanToEntityByUpdate(welfareBabyInfoFormBean);
     // DB登録
    	welfareBabyInfoMapper.updateWelfareBabyInfo(welfareBabyInfoEntity);
        return false;
    }
    /**
     * 概要:新規追加用BeanToEntity
     *
     * @param:[WelfareBabyInfoFormBean]
     * @return:com.softtech.entity.WelfareBabyInfoEntity
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    private WelfareBabyInfoEntity transforBeanToEntityByInsert(
    		WelfareBabyInfoFormBean welfareBabyInfoFormBean) {
    	WelfareBabyInfoEntity welfareBabyInfoEntity = new WelfareBabyInfoEntity();

        // 厚生保険料ID+1、新規追加用
        String maxRateID = welfareBabyInfoMapper.getMaxRateID();
        String nextRateID = DataUtil.getNextID(maxRateID, 1);
        welfareBabyInfoEntity.setRateID(nextRateID);

        // 対象年度
        String year = welfareBabyInfoFormBean.getYear();
        welfareBabyInfoEntity.setYear(year);

        // 対象エリア
        String area = welfareBabyInfoFormBean.getArea();
        welfareBabyInfoEntity.setArea(area);

        // 標準報酬
        String rate = welfareBabyInfoFormBean.getRate();
        welfareBabyInfoEntity.setRate(rate);

        // 利用ステータス
        int status = welfareBabyInfoFormBean.getStatus();
        welfareBabyInfoEntity.setStatus(status);

        // 作成日
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
        String format = formatter.format(now);
        welfareBabyInfoEntity.setInsertDate(format);

        // 更新日
        welfareBabyInfoEntity.setUpdateDate(format);

        return welfareBabyInfoEntity;
    }
    /**
     * 概要:insert
     *
     * @param:[WelfareBabyInfoFormBean]
     * @return:boolean
     * @author:孫曄@SOFTTECH
     * @date:2022/05/25
     */
    @Override
    public boolean insertWelfareBabyInfo(WelfareBabyInfoFormBean welfareBabyInfoFormBean) {
        // 画面からデータをEntityに伝送
    	WelfareBabyInfoEntity welfareBabyInfoEntity1 =
                transforBeanToEntityByInsert(welfareBabyInfoFormBean);
        // DBに登録
        welfareBabyInfoMapper.insertWelfareBabyInfo(welfareBabyInfoEntity1);
        return true;
    }


	@Override
	public List<RateIDName> getYear() {
		List<RateIDName> rateIDNameList = new ArrayList<>();
		// DBから年度を取得
		rateIDNameList= welfareBabyInfoMapper.getYear();

		return rateIDNameList;
	}

	/*public ArrayList<RateIDName> getOldYears(int oldYear){
		return (ArrayList<RateIDName>) DateUtil.getYears2(oldYear);
	}*/
	public ArrayList<ListIDName> getOldYears(int oldYear){
		return (ArrayList<ListIDName>) DateUtil.getYears(oldYear);
	}


}
