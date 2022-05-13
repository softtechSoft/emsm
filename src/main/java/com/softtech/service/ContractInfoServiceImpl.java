package com.softtech.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.softtech.actionForm.ContractInfoBean;
import com.softtech.actionForm.ContractInfoFormBean;
import com.softtech.entity.ContractInfoEntity;
import com.softtech.mappers.ContractInfoMapper;
import com.softtech.util.DataUtil;
import com.softtech.util.DateUtil;
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
	public boolean updateContractInfoDetail(ContractInfoFormBean contractInfoBean) {
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
	private ContractInfoEntity tranferBeanToEntity(ContractInfoFormBean contractInfoBean) {
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

	/*
	 * 機能概要：数字チェック
	 *
	 * @param contractInfoBean　チェック対象
	 * @return エーラリスト
	 */
	public List<FieldError> chkNumberData(ContractInfoBean contractInfoBean) {
		// エラーチェック用リスト
		List<FieldError> errorlst = new ArrayList<FieldError>();
		// 単価は数字ではない場合、エラーメッセージ戻す
		String price=contractInfoBean.getPrice();
			if(DataUtil.isNumeric(price)) {

			} else {
				FieldError err1 = new FieldError("", "", "単価に数値のみを入力してください。");
				errorlst.add(err1);
			}
		// 控除単価は数字ではない場合、エラーメッセージ戻す
		String lowerPrice=contractInfoBean.getLowerPrice();
			if(DataUtil.isNumeric(lowerPrice)) {

			} else {
				FieldError err2 = new FieldError("", "", "控除単価に数値のみ入力してください。");
				errorlst.add(err2);
			}
		//契約下限は数字ではない場合、エラーメッセージ戻す
		String lowerTime=contractInfoBean.getLowerTime();
			if(DataUtil.isNumeric(lowerTime)) {

			} else {
				FieldError err3 = new FieldError("", "", "契約下限に数値のみを入力してください。");
				errorlst.add(err3);
			}
		//契約上限は数字ではない場合、エラーメッセージ戻す
		String upperTime=contractInfoBean.getUpperTime();
			if(DataUtil.isNumeric(upperTime)) {

			} else {
				FieldError err4= new FieldError("", "", "契約上限に数値のみを入力してください。");
				errorlst.add(err4);
		}
		//残業単価は数字ではない場合、エラーメッセージ戻す
		String upperPrice=contractInfoBean.getUpperPrice();
			if(DataUtil.isNumeric(upperPrice)) {

			} else {
				FieldError err5= new FieldError("", "", "残業単価を入力してください。");
				errorlst.add(err5);
			}
		//支払サイトは数字ではない場合、エラーメッセージ戻す
		String paymentTerm=contractInfoBean.getPaymentTerm();
			if(DataUtil.isNumeric(paymentTerm)) {

			} else {
				FieldError err6= new FieldError("", "", "支払サイトに数値のみを入力してください。");
				errorlst.add(err6);
			}
		return errorlst;
	}
	/*
	 * 機能概要：日付チェック
	 *
	 * @param expensesBean　チェック対象
	 * @return エラーリスト
	 */
	public List<FieldError> chkDate(ContractInfoBean contractInfoBean) {

		// エラーチェック用リスト
		List<FieldError> errorlst = new ArrayList<FieldError>();

		// 契約開始日は日付ではない場合、エラーメッセージ戻す
		String contractBeginDate=contractInfoBean.getContractBeginDate();
			if(!DateUtil.isDate(contractBeginDate)) {

			FieldError err1 = new FieldError("", "", "契約開始日に日付のみを入力してください。");
			errorlst.add(err1);
		}
		return errorlst;
	}
	/*
	 * 機能概要:DBのデータを画面formへ転換する
 	 *
	 *
	 * @param sList　DBのデータ
	 * @return 画面form
	 */
	public ContractInfoFormBean trasferEntityToUI(List<ContractInfoEntity> sList) {

		// 画面form
		ContractInfoFormBean contractInfoFormBean = new ContractInfoFormBean();

		// DBのデータを画面formへ転換する
		for(ContractInfoEntity contractInfoEntity:sList){
			contractInfoFormBean.setContractID(contractInfoEntity.getContractID());
			contractInfoFormBean.setContractName(contractInfoEntity.getContractName());
			contractInfoFormBean.setEmployeeID(contractInfoEntity.getEmployeeID());
			contractInfoFormBean.setEmployeeName(contractInfoEntity.getEmployeeName());
			contractInfoFormBean.setCompanyID(contractInfoEntity.getCompanyID());
			contractInfoFormBean.setCompanyName(contractInfoEntity.getCompanyName());
			contractInfoFormBean.setPrice(contractInfoEntity.getPrice());
			contractInfoFormBean.setPayOff(contractInfoEntity.getPayOff());
			contractInfoFormBean.setLowerTime(contractInfoEntity.getLowerTime());
			contractInfoFormBean.setLowerPrice(contractInfoEntity.getLowerPrice());
			contractInfoFormBean.setUpperTime(contractInfoEntity.getUpperTime());
			contractInfoFormBean.setUpperPrice(contractInfoEntity.getUpperPrice());
			contractInfoFormBean.setContractBeginDate(contractInfoEntity.getContractBeginDate());
			contractInfoFormBean.setContractEndDate(contractInfoEntity.getContractEndDate());
			contractInfoFormBean.setPaymentTerm(contractInfoEntity.getPaymentTerm());
			contractInfoFormBean.setPostNeed(contractInfoEntity.getPostNeed());
			contractInfoFormBean.setTimeReportPath(contractInfoEntity.getTimeReportPath());
			contractInfoFormBean.setInvoice(contractInfoEntity.getInvoice());
			contractInfoFormBean.setStatus(contractInfoEntity.getStatus());
		}

		return contractInfoFormBean;
	}
	/*
	 * 機能概要:契約情報の最大IDを取得する
 	 *
	 *
	 * @param なし
	 * @return 最大ID
	 */
	private String getMaxContractID()
	{
		String maxContractID = contractinfoMapper.getMaxContractID();
		return  maxContractID;
	}

	/*
	 * 機能概要:契約情報の契約IDを採番する
	 *
	 * @param なし
	 * @return 契約ID
	 */
	public String getNextContractID()
	{
		//契約情報の最大IDを取得する
		String maxContractID = getMaxContractID();
		// 採番する
		String nextContractID = DataUtil.getNextID(maxContractID,2);
		return  nextContractID;
	}
	public boolean insertContractInfoDetail(ContractInfoFormBean contractInfoBean) {
		//画面データをEntityに設定する。
		ContractInfoEntity contractInfoEntity = tranferBeanToEntity(contractInfoBean);

		// DB登録
		contractinfoMapper.insertContractInfo(contractInfoEntity);

		return true;

}
	}