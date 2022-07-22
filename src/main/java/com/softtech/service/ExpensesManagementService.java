package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.FieldError;

import com.softtech.actionForm.ExpensesManagementBean;
import com.softtech.entity.ExpensesManagementEntity;
import com.softtech.mappers.ExpensesManagementMapper;
import com.softtech.util.DataUtil;
import com.softtech.util.DateUtil;

public class ExpensesManagementService {
	@Autowired
	ExpensesManagementMapper expensesManagementMapper;

	/*
	 * 機能概要：数字チェック
	 *
	 * @param expensesManagementBean　チェック対象
	 * @return エーラリスト
	 */
	public List<FieldError> chkNumberData(ExpensesManagementBean expensesManagementBean) {

		// エラーチェック用
		List<FieldError> errorlst = new ArrayList<FieldError>();

		// 金額は数字ではない場合、エラーメッセージ戻す
		String amount=expensesManagementBean.getCost();
		if(DataUtil.isNumeric(amount)) {
			//
		} else {
			FieldError err1 = new FieldError("", "", "金額に数字を入力してください。");
			errorlst.add(err1);
		}

		return errorlst;

	}
	/*
	 * 機能概要：日付チェック
	 *
	 * @param expensesManagementBean　チェック対象
	 * @return エラーリスト
	 */
	public List<FieldError> chkDate(ExpensesManagementBean expensesManagementBean) {

		// エラーチェック用
		List<FieldError> errorlst = new ArrayList<FieldError>();

		// 発生日ではない場合、エラーメッセージ戻す
		String accrualDate=expensesManagementBean.getAccrualDate();
		if(!DateUtil.isDate(accrualDate)) {

			FieldError err1 = new FieldError("", "", "発生日に通常の日付を入力してください	。");
			errorlst.add(err1);
		}

		// 精算日ではない場合、エラーメッセージ戻す
				String stmtlDay=expensesManagementBean.getStmtlDay();
				if(!DateUtil.isDate(stmtlDay)) {

					FieldError err1 = new FieldError("", "", "精算日に通常の日付を入力してください。");
					errorlst.add(err1);
				}

				return errorlst;

			}
			/*
			 * 機能概要：画面データをDBに登録
			 *
			 * @param expensesManagementBean 画面データ
			 * @return true:成功、false:失敗
			 */
			public boolean insertExpensesManagement(ExpensesManagementBean expensesManagementBean) {
				//画面データをEntityに設定する。
				ExpensesManagementEntity expensesManagementEntity = tranferBeanToEntity(expensesManagementBean);

				// DB登録
				expensesManagementMapper.insertExpensesManagement(expensesManagementEntity);

				return true;
			}


	private ExpensesManagementEntity tranferBeanToEntity(ExpensesManagementBean expensesManagementBean) {
				// TODO 自動生成されたメソッド・スタブ
				return null;
			}
	/*
	 * 機能概要：画面データをEntityクラスに設定する
	 *
	 * @param expensesManagementBean 画面データ
	 * @return Entityクラス
	 */
	private ExpensesManagementEntity queryEmployeeInfo(ExpensesManagementBean expensesManagementBean) {
		ExpensesManagementEntity expensesManagementEntity = new ExpensesManagementEntity();

		//日付YYYY/MM/DD→YYYYMMDD変換
		//発生日YYYY/MM/DD→YYYYMMDD変換
		String accrualDate=expensesManagementBean.getAccrualDate();
		expensesManagementEntity.setAccrualDate(DateUtil.chgMonthToYM(accrualDate));

		//金額のComa(,)を除くDataUtil.deleteComma(accrualDate)
		String cost=expensesManagementBean.getCost();
		expensesManagementEntity.setCost(DataUtil.deleteComma(cost));

		//担当者
		String tantouName =expensesManagementBean.getTantouName();
		expensesManagementEntity.setTantouName(tantouName);

		//経費種別
		String expensesType =expensesManagementBean.getExpensesType();
		expensesManagementEntity.setExpensesType(expensesType);

		//場所
		String happenAddress =expensesManagementBean.getHappenAdderss();
		expensesManagementEntity.setHappenAdderss(happenAddress);

		//承認ステータス
		String confirmStauts =expensesManagementBean.getConfirmStatus();
		expensesManagementEntity.setConfirmStatus(confirmStauts);

		//精算タイプ
		String stmtlType=expensesManagementBean.getStmtlType();
		expensesManagementEntity.setStmtlType(stmtlType);

		//精算ステータス
		String stmtlStatus=expensesManagementBean.getStmtlStatus();
		expensesManagementEntity.setStmtlStatus(stmtlStatus);

		//精算日YYYY/MM/DD→YYYYMMDD変換
	    String stmtlDay=expensesManagementBean.getStmtlDay();
		expensesManagementEntity.setStmtlDay(DateUtil.chgMonthToYM(stmtlDay));


		//備考
		String remark=expensesManagementBean.getRemark();
		expensesManagementEntity.setRemark(remark);

		return expensesManagementEntity;

	}


}
