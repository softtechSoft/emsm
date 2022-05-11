package com.softtech.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.ContractInfoBean;
import com.softtech.entity.ContractInfoEntity;
import com.softtech.mappers.ContractInfoMapper;
	/**
	 * 概要：給料情報取得インタフェース
	 *
	 * @author テー@ソフトテク
	 *
	 */
	@Service
	public class ContractInfoServiceImpl implements ContractInfoService{

	/**
	 * 機能：給料情報リスト取得
	 *
	 * @author テー@ソフトテク
	 */
	@Autowired
	ContractInfoMapper contractinfoMapper;
	@Override
	public List<ContractInfoEntity> queryContractInfoList(String employeeID) {
		List<ContractInfoEntity> contractinfolist = contractinfoMapper.getContractInfoList(employeeID);
		return  contractinfolist;
	}
	/**
	 * 機能：更新処理の契約情報取得
	 *
	 * @author テー@ソフトテク
	 */
	public List<ContractInfoEntity> queryContractInfo(String contractID) {
		List<ContractInfoEntity> contractinfo = contractinfoMapper.getContractInfo(contractID);
		return  contractinfo;
	}

	/*
	 * 機能概要：画面データをDBに登録
	 *
	 * @param ContractInfoBean 画面データ
	 * @return true:成功、false:失敗
	 */
	public boolean updateContractInfoDetail(ContractInfoBean contractInfoBean) {
	//画面データをEntityに設定する。
	ContractInfoEntity contractInfoEntity = tranferBeanToEntity(contractInfoBean);

	// DB登録
	contractinfoMapper.updateContractInfo(contractInfoEntity);

	return true;
	}
	/*
	 * 機能概要：画面データをEntityクラスに設定する
	 *
	 * @param contractInfoBean 画面データ
	 * @return Entityクラス
	 */
	private ContractInfoEntity tranferBeanToEntity(ContractInfoBean contractInfoBean) {
		ContractInfoEntity contractInfoEntity = new ContractInfoEntity();
		//契約ID
		String contractID=contractInfoBean.getContractID();
		contractInfoEntity.setContractID(contractID);
		//契約名称
		String contractName=contractInfoBean.getContractName();
		contractInfoEntity.setContractName(contractName);
		//取引先ID
		String companyID=contractInfoBean.getCompanyID();
		contractInfoEntity.setCompanyID(companyID);
		//社員ID
		String employeeID=contractInfoBean.getEmployeeID();
		contractInfoEntity.setEmployeeID(employeeID);
		//単位
		String price=contractInfoBean.getPrice();
		contractInfoEntity.setPrice(price);
		//精算タイプ
		String payOff=contractInfoBean.getPayOff();
		contractInfoEntity.setPayOff(payOff);
		//精契約下限
		String lowerTime=contractInfoBean.getLowerTime();
		contractInfoEntity.setLowerTime(lowerTime);
		//控除単価
		String lowerPrice=contractInfoBean.getLowerPrice();
		contractInfoEntity.setLowerPrice(lowerPrice);
		//契約上限
		String upperTime=contractInfoBean.getUpperTime();
		contractInfoEntity.setUpperTime(upperTime);
		//契約上限
		String upperPrice=contractInfoBean.getUpperPrice();
		contractInfoEntity.setUpperPrice(upperPrice);
		//契約開始日
		String contractBeginDate=contractInfoBean.getContractBeginDate();
		contractInfoEntity.setContractBeginDate(contractBeginDate);
		//契約終了日
		String contractEndDate=contractInfoBean.getContractEndDate();
		contractInfoEntity.setContractEndDate(contractEndDate);
		//原本郵送フラグ
		String postNeed=contractInfoBean.getPostNeed();
		contractInfoEntity.setPostNeed(postNeed);
		//タイムレポートパス
		String timeReportPath=contractInfoBean.getTimeReportPath();
		contractInfoEntity.setTimeReportPath(timeReportPath);
		//支払サイト
		String paymentTerm=contractInfoBean.getPaymentTerm();
		contractInfoEntity.setPaymentTerm(paymentTerm);
		//請求書名称
		String invoice=contractInfoBean.getInvoice();
		contractInfoEntity.setInvoice(invoice);
		//進行ステータス
		String status=contractInfoBean.getStatus();
		contractInfoEntity.setStatus(status);
		return contractInfoEntity;
	}
}
