package com.softtech.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.BaseSalaryInfoFormBean;
import com.softtech.entity.BaseSalaryInfoEntity;
import com.softtech.mappers.BaseSalaryInfoMapper;
import com.softtech.util.DataUtil;
@Service
public class BaseSalaryInfoServiceImpl implements BaseSalaryInfoService {


	@Autowired
	BaseSalaryInfoMapper baseSalaryInfoMapper;
	//データ画面
	@Override
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfoList(String employeeID) {
		List<BaseSalaryInfoEntity> baseSalaryInfoList = baseSalaryInfoMapper.getBaseSalaryInfoList(employeeID);
		return  baseSalaryInfoList;
	}
	/**
	 * @Description: 更新画面へのquery
	 * @author 孫曄
	 * @date 2022-05-09
	 * @return:
	*/
	@Override
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfo(String baseSalaryID) {
		List<BaseSalaryInfoEntity> baseSalaryInfo = baseSalaryInfoMapper.getBaseSalaryInfo(baseSalaryID);
		return  baseSalaryInfo;
	}

	public BaseSalaryInfoMapper getBaseSalaryInfoMapper() {
		return baseSalaryInfoMapper;
	}
	public void setBaseSalaryInfoMapper(BaseSalaryInfoMapper baseSalaryInfoMapper) {
		this.baseSalaryInfoMapper = baseSalaryInfoMapper;
	}
	@Override
	public boolean updateBaseSalaryInfoList(BaseSalaryInfoFormBean baseSalaryInfoBean) {

		BaseSalaryInfoEntity baseSalaryInfoEntity = tranferBeanToEntity(baseSalaryInfoBean);
		baseSalaryInfoMapper.updateBaseSalaryInfo(baseSalaryInfoEntity);
		return true;

	}
	/**
	 * @Description: 更新用のBeanToEntity
	 * @author 孫曄
	 * @date 2022-05-12
	 * @return:
	*/
	private BaseSalaryInfoEntity tranferBeanToEntity(BaseSalaryInfoFormBean baseSalaryInfoBean) {
		BaseSalaryInfoEntity baseSalaryInfoEntity = new BaseSalaryInfoEntity();
		baseSalaryInfoEntity.setEmployeeID(baseSalaryInfoBean.getEmployeeID());
		//基本給
		String baseSalary = baseSalaryInfoBean.getBaseSalary();
		baseSalaryInfoEntity.setBaseSalary(baseSalary);

		//対象年度
		String year = baseSalaryInfoBean.getYear();
		baseSalaryInfoEntity.setYear(year);


		//稼働期間From
		String wkPeriodFrom = baseSalaryInfoBean.getWkPeriodFrom();
		baseSalaryInfoEntity.setWkPeriodFrom(wkPeriodFrom);

		//稼働期間To
		String wkPeriodTo = baseSalaryInfoBean.getWkPeriodTo();
		baseSalaryInfoEntity.setWkPeriodTo(wkPeriodTo);

		//残業単価
		baseSalaryInfoEntity.setOvertimePay(baseSalaryInfoBean.getOvertimePay());

		//控除単価
		baseSalaryInfoEntity.setInsufficienttimePay(baseSalaryInfoBean.getInsufficienttimePay());

		//利用ステータス
		int status = baseSalaryInfoBean.getStatus();
		baseSalaryInfoEntity.setStatus(status);

		//基本給ID
		String baseSalaryID = baseSalaryInfoBean.getBaseSalaryID();
		baseSalaryInfoEntity.setBaseSalaryID(baseSalaryID);
        //社員ID
		String employeeID = baseSalaryInfoBean.getEmployeeID();
		baseSalaryInfoEntity.setEmployeeID(employeeID);

		//登録日
		String insertDate = baseSalaryInfoBean.getInsertDate();
		baseSalaryInfoEntity.setInsertDate(insertDate);

		//更新日
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String format = formatter.format(now);
		baseSalaryInfoEntity.setUpdateDate(format);

		return baseSalaryInfoEntity;
	}

	/**
	 * @Description: baseSalaryIDの最大値を取得
	 * @author 孫曄
	 * @date 2022-05-13
	 * @return:baseSalaryIDの最大値
	*/
	public String getMaxBaseSalaryID() {
		String maxBaseSalaryID = baseSalaryInfoMapper.getMaxBaseSalaryID();
		return maxBaseSalaryID;
	}
	/**
	 *
	 * @Description: 新規用のBeanToEntity
	 * @author 孫曄
	 * @date 2022-05-17
	 * @return:
	 */
	private BaseSalaryInfoEntity tranferBeanToEntity1(BaseSalaryInfoFormBean baseSalaryInfoBean) {
		BaseSalaryInfoEntity baseSalaryInfoEntity = new BaseSalaryInfoEntity();

		//基本給
		String baseSalary = baseSalaryInfoBean.getBaseSalary();
		baseSalaryInfoEntity.setBaseSalary(baseSalary);

		//対象年度
				String year = baseSalaryInfoBean.getYear();
				baseSalaryInfoEntity.setYear(year);

		//稼働期間From
		String wkPeriodFrom = baseSalaryInfoBean.getWkPeriodFrom();
		baseSalaryInfoEntity.setWkPeriodFrom(wkPeriodFrom);

		//稼働期間To
		String wkPeriodTo = baseSalaryInfoBean.getWkPeriodTo();
		baseSalaryInfoEntity.setWkPeriodTo(wkPeriodTo);

		//残業単価
		if (wkPeriodTo != null && wkPeriodTo != "" && wkPeriodTo !="0") {
			baseSalaryInfoEntity.setOvertimePay(baseSalaryInfoBean.getOvertimePay());
		}
		//控除単価
		if (wkPeriodFrom != null && wkPeriodFrom != "" && wkPeriodFrom !="0") {
			baseSalaryInfoEntity.setInsufficienttimePay(baseSalaryInfoBean.getInsufficienttimePay());
		}


		//利用ステータス
		int status = baseSalaryInfoBean.getStatus();
		baseSalaryInfoEntity.setStatus(status);

		//基本給ID
//		String maxBaseSalaryID = getMaxBaseSalaryID();
//		String nextBaseSalaryID = DataUtil.getNextID(maxBaseSalaryID,1);
//		baseSalaryInfoEntity.setBaseSalaryID(nextBaseSalaryID);
	    String maxBaseSalaryID = getMaxBaseSalaryID();
	    String nextBaseSalaryID;
	    
	    if (maxBaseSalaryID == null || maxBaseSalaryID.isEmpty()) {
	        nextBaseSalaryID = "1";
	    } else {
	        try {
	            int maxNum = Integer.parseInt(maxBaseSalaryID);
	            nextBaseSalaryID = String.valueOf(maxNum + 1);
	        } catch (NumberFormatException e) {
	            nextBaseSalaryID = "1";
	        }
	    }
	    
	    baseSalaryInfoEntity.setBaseSalaryID(nextBaseSalaryID);

		//社員ID
		String employeeID = baseSalaryInfoBean.getEmployeeID();
		baseSalaryInfoEntity.setEmployeeID(employeeID);

		//登録日
		String insertDate = baseSalaryInfoBean.getInsertDate();
		baseSalaryInfoEntity.setInsertDate(insertDate);

		//更新日
		LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		String format = formatter.format(now);
		baseSalaryInfoEntity.setUpdateDate(format);

		return baseSalaryInfoEntity;
	}
/**
	 * @Description: baseSalaryIDの最大値+1を取得,登録時用のPK
	 * @author 孫曄
	 * @date 2022-05-13
	 * @return:baseSalaryIDの最大値+1,DataUtil.getNextID(); Method
	*/
	public String getNextBaseSalaryID(){
		//baseSalaryIDの最大ID+1を取得する
		String maxBaseSalaryID = getMaxBaseSalaryID();
		//採番する
		String nextBaseSalaryID = DataUtil.getNextID(maxBaseSalaryID,1);
		return nextBaseSalaryID;
	}

	/**
	 * @Description: 新規登録
	 * @author 孫曄
	 * @date 2022-05-13
	 * @return:Mapper
	*/
	@Override
	public boolean insertBaseSalaryInfoDetail(BaseSalaryInfoFormBean baseSalaryInfoBean) {
		//画面データをEntityに設定する。　　新規画面用のBeanToEntity
		BaseSalaryInfoEntity baseSalaryInfoEntity = tranferBeanToEntity1(baseSalaryInfoBean);

		// DB登録
		baseSalaryInfoMapper.insertBaseSalaryInfo(baseSalaryInfoEntity);

		return true;
	}
	/**
	 * @Description: 画面form,画面初期値
	 * @author 孫曄
	 * @date 2022-05-13
	 * @return:
	*/
	@Override
	public BaseSalaryInfoFormBean trasferEntityToUI(List<BaseSalaryInfoEntity> bList) {
		// 画面form
		BaseSalaryInfoFormBean baseSalaryInfoFormBean = new BaseSalaryInfoFormBean();
		// DBのデータを画面formへ転換する
		for(BaseSalaryInfoEntity baseSalaryInfoEntity:bList){
			baseSalaryInfoFormBean.setBaseSalaryID(baseSalaryInfoEntity.getBaseSalaryID());
			baseSalaryInfoFormBean.setBaseSalary(baseSalaryInfoEntity.getBaseSalary());
			baseSalaryInfoFormBean.setYear(baseSalaryInfoEntity.getYear());
			baseSalaryInfoFormBean.setWkPeriodFrom(baseSalaryInfoEntity.getWkPeriodFrom());
			baseSalaryInfoFormBean.setWkPeriodTo(baseSalaryInfoEntity.getWkPeriodTo());
			baseSalaryInfoFormBean.setOvertimePay(baseSalaryInfoEntity.getOvertimePay());
			baseSalaryInfoFormBean.setInsufficienttimePay(baseSalaryInfoEntity.getInsufficienttimePay());
			baseSalaryInfoFormBean.setStatus(baseSalaryInfoEntity.getStatus());
			baseSalaryInfoFormBean.setEmployeeID(baseSalaryInfoEntity.getEmployeeID());
			baseSalaryInfoFormBean.setInsertDate(baseSalaryInfoEntity.getInsertDate());
			baseSalaryInfoFormBean.setUpdateDate(baseSalaryInfoEntity.getUpdateDate());
		}
		return baseSalaryInfoFormBean;
	}
	
	@Override
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfoByCondition(String employeeID, String year) {
	    return baseSalaryInfoMapper.getBaseSalaryInfoByCondition(employeeID, year);
	}

	@Override
	public List<String> getYearList() {
	    return baseSalaryInfoMapper.getDistinctYears();
	}
}