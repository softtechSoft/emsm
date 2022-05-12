package com.softtech.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.softtech.actionForm.BaseSalaryInfoBean;
import com.softtech.entity.BaseSalaryInfoEntity;
import com.softtech.mappers.BaseSalaryInfoMapper;
import com.softtech.util.DateUtil;
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
	//更新画面
	@Override
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfo(String baseSalaryID) {
		List<BaseSalaryInfoEntity> baseSalaryInfo = baseSalaryInfoMapper.getBaseSalaryInfo(baseSalaryID);
		return  baseSalaryInfo;
	}

	@Override
	public boolean updateBaseSalaryInfoList(BaseSalaryInfoBean baseSalaryInfoBean) {

		BaseSalaryInfoEntity baseSalaryInfoEntity = tranferBeanToEntity(baseSalaryInfoBean);
		baseSalaryInfoMapper.updateBaseSalaryInfo(baseSalaryInfoEntity);
		return true;

	}

	private BaseSalaryInfoEntity tranferBeanToEntity(BaseSalaryInfoBean baseSalaryInfoBean) {
		BaseSalaryInfoEntity baseSalaryInfoEntity = new BaseSalaryInfoEntity();

		//修正時間
		//日付YYYY/MM/DD→YYYYMMDD変換
		//発生日YYYY/MM/DD→YYYYMMDD変換
		String updateDate = baseSalaryInfoBean.getUpdateDate();
		baseSalaryInfoEntity.setUpdateDate(DateUtil.chgMonthToYM(updateDate));

		//基本給
		int baseSalary = baseSalaryInfoBean.getBaseSalary();
		baseSalaryInfoEntity.setBaseSalary(baseSalary);

		//残業不足時間
		int minusHour = baseSalaryInfoBean.getMinusHour();
		baseSalaryInfoEntity.setMinusHour(minusHour);


		//残業時間
		int plusHour= baseSalaryInfoBean.getPlusHour();
		baseSalaryInfoEntity.setPlusHour(plusHour);

		//稼働期間From
		int wkPeriodFrom = baseSalaryInfoBean.getWkPeriodFrom();
		baseSalaryInfoEntity.setWkPeriodFrom(wkPeriodFrom);

		//稼働期間To
		int wkPeriodTo = baseSalaryInfoBean.getWkPeriodTo();
		baseSalaryInfoEntity.setWkPeriodTo(wkPeriodTo);

		//利用ステータス
		int status = baseSalaryInfoBean.getStatus();
		baseSalaryInfoEntity.setStatus(status);

		//基本給ID
		String baseSalaryID = baseSalaryInfoBean.getBaseSalaryID();
		baseSalaryInfoEntity.setBaseSalaryID(baseSalaryID);

		//登録日
		String insertDate = baseSalaryInfoBean.getInsertDate();
		baseSalaryInfoEntity.setInsertDate(insertDate);

		//社員ID
		String employeeID = baseSalaryInfoBean.getEmployeeID();
		baseSalaryInfoEntity.setEmployeeID(employeeID);

		return baseSalaryInfoEntity;
	}


}
