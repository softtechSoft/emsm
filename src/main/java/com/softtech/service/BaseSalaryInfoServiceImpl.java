package com.softtech.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.softtech.actionForm.BaseSalaryInfoBean;
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
	//更新画面
	@Override
	public List<BaseSalaryInfoEntity> queryBaseSalaryInfo(String baseSalaryID) {
		List<BaseSalaryInfoEntity> baseSalaryInfo = baseSalaryInfoMapper.getBaseSalaryInfo(baseSalaryID);
		return  baseSalaryInfo;
	}

	@Override
	public boolean updateBaseSalaryInfoList(BaseSalaryInfoFormBean baseSalaryInfoBean) {

		BaseSalaryInfoEntity baseSalaryInfoEntity = tranferBeanToEntity(baseSalaryInfoBean);
		baseSalaryInfoMapper.updateBaseSalaryInfo(baseSalaryInfoEntity);
		return true;

	}
	/**
	 * @Description: 更新画面用のBeanToEntity
	 * @author 孫曄
	 * @date 2022-05-12
	 * @return:
	*/
	private BaseSalaryInfoEntity tranferBeanToEntity(BaseSalaryInfoFormBean baseSalaryInfoBean) {
		BaseSalaryInfoEntity baseSalaryInfoEntity = new BaseSalaryInfoEntity();

		//修正時間
		LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		baseSalaryInfoEntity.setUpdateDate(format);

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

		//登録日　　！入力日は修正しません
		String insertDate = baseSalaryInfoBean.getInsertDate();
		baseSalaryInfoEntity.setInsertDate(insertDate);

		//社員ID
		String employeeID = baseSalaryInfoBean.getEmployeeID();
		baseSalaryInfoEntity.setEmployeeID(employeeID);

		return baseSalaryInfoEntity;
	}
	/**
	 * @Description: 更新画面の数字チェク
	 * @author 孫曄
	 * @date 2022-05-13
	 * @return:
	*/
	@Override
	public List<FieldError> chkNumberData(BaseSalaryInfoBean baseSalaryInfoBean) {
		// TODO 自動生成されたメソッド・スタブ
		return null;
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
	 * @Description: baseSalaryIDの最大値+1を取得,登録時用のPK
	 * @author 孫曄
	 * @date 2022-05-13
	 * @return:baseSalaryIDの最大値+1,DataUtil.getNextID(); Method
	*/
	public String getNextBaseSalaryID(){
		//baseSalaryIDの最大ID+1を取得する
		String maxBaseSalaryID = getMaxBaseSalaryID();
		//採番する
		String nextBaseSalaryID = DataUtil.getNextID(maxBaseSalaryID,2);
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
	 * @Description: 新規画面用のBeanToEntity
	 * @author 孫曄
	 * @date 2022-05-13
	 * @return:Map
	*/
	private BaseSalaryInfoEntity tranferBeanToEntity1(BaseSalaryInfoFormBean baseSalaryInfoBean) {
		BaseSalaryInfoEntity baseSalaryInfoEntity = new BaseSalaryInfoEntity();

		//修正時間
		LocalDateTime now = LocalDateTime.now();
        String format = now.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		baseSalaryInfoEntity.setUpdateDate(format);

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

		//登録日：新規ので、LocalDateTime.now();から時間を入ります
		baseSalaryInfoEntity.setInsertDate(format);

		//社員ID
		String employeeID = baseSalaryInfoBean.getEmployeeID();
		baseSalaryInfoEntity.setEmployeeID(employeeID);

		return baseSalaryInfoEntity;
	}
	/**
	 * @Description: TODO
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
			baseSalaryInfoFormBean.setMinusHour(baseSalaryInfoEntity.getMinusHour());
			baseSalaryInfoFormBean.setPlusHour(baseSalaryInfoEntity.getPlusHour());
			baseSalaryInfoFormBean.setWkPeriodFrom(baseSalaryInfoEntity.getWkPeriodFrom());
			baseSalaryInfoFormBean.setWkPeriodTo(baseSalaryInfoEntity.getWkPeriodTo());
			baseSalaryInfoFormBean.setStatus(baseSalaryInfoEntity.getStatus());
			baseSalaryInfoFormBean.setEmployeeID(baseSalaryInfoEntity.getEmployeeID());
		}
		return baseSalaryInfoFormBean;
	}
}
