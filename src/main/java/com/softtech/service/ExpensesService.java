package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.softtech.actionForm.ExpensesBean;
import com.softtech.entity.ExpensesEntity;
import com.softtech.mappers.ExpensesMapper;
import com.softtech.util.DataUtil;
import com.softtech.util.DateUtil;
/**
 * 概要：一般経費処理用サービスクラス
 *
 * 作成者：劉
 * 作成日：2021/08/14
 */
@Service
public class ExpensesService {
	@Autowired
	ExpensesMapper expensesMapper;

	/*
	 * 機能概要：数字チェック
	 *
	 * @param expensesBean　チェック対象
	 * @return エーラリスト
	 */
	public List<FieldError> chkNumberData(ExpensesBean expensesBean) {

		// エラーチェック用リスト
		List<FieldError> errorlst = new ArrayList<FieldError>();

		// 金額は数字ではない場合、エラーメッセージ戻す
		String amount=expensesBean.getCost();
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
	 * @param expensesBean　チェック対象
	 * @return エラーリスト
	 */
	public List<FieldError> chkDate(ExpensesBean expensesBean) {

		// エラーチェック用リスト
		List<FieldError> errorlst = new ArrayList<FieldError>();

		// 発生日ではない場合、エラーメッセージ戻す
		String accrualDate=expensesBean.getAccrualDate();
		if(!DateUtil.isDate(accrualDate)) {

			FieldError err1 = new FieldError("", "", "発生日に通常の日付を入力してください	。");
			errorlst.add(err1);
		}

		// 承認日ではない場合、エラーメッセージ戻す
		String confirmDate=expensesBean.getConfirmDate();
		if(!DateUtil.isDate(confirmDate)) {

			FieldError err1 = new FieldError("", "", "承認日に通常の日付を入力してください。");
			errorlst.add(err1);
		}

		// 精算日ではない場合、エラーメッセージ戻す
		String stmtlDate=expensesBean.getStmtlDate();
		if(!DateUtil.isDate(stmtlDate)) {

			FieldError err1 = new FieldError("", "", "精算日に通常の日付を入力してください。");
			errorlst.add(err1);
		}

		return errorlst;

	}
	/*
	 * 機能概要：画面データをDBに登録
	 *
	 * @param expensesBean 画面データ
	 * @return true:成功、false:失敗
	 */
	public boolean insertExpensesDetail(ExpensesBean expensesBean) {
		//画面データをEntityに設定する。
		ExpensesEntity expensesEntity = tranferBeanToEntity(expensesBean);

		// DB登録
		expensesMapper.insertExpenses(expensesEntity);

		return true;
	}
	/*
	 * 機能概要：画面データをEntityクラスに設定する
	 *
	 * @param expensesBean 画面データ
	 * @return Entityクラス
	 */
	private ExpensesEntity tranferBeanToEntity(ExpensesBean expensesBean) {
		ExpensesEntity expensesEntity = new ExpensesEntity();

		//日付YYYY/MM/DD→YYYYMMDD変換
		//発生日YYYY/MM/DD→YYYYMMDD変換
		String accrualDate=expensesBean.getAccrualDate();
		expensesEntity.setAccrualDate(DateUtil.chgMonthToYM(accrualDate));

		//金額のComa(,)を除くDataUtil.deleteComma(accrualDate)
		String cost=expensesBean.getCost();
		expensesEntity.setCost(DataUtil.deleteComma(cost));

		//担当者
		String personel=expensesBean.getPersonel();
		expensesEntity.setPersonel(personel);

		//承認
		String confirmStaus=expensesBean.getConfirmStaus();
		expensesEntity.setConfirmStaus(confirmStaus);

		//承認日YYYY/MM/DD→YYYYMMDD変換
		String confirmDate=expensesBean.getConfirmDate();
		expensesEntity.setConfirmDate(DateUtil.chgMonthToYM(confirmDate));

		//承認者
		String confirmer=expensesBean.getConfirmer();
		expensesEntity.setConfirmer(confirmer);

		//精算
		String stmtlStaus=expensesBean.getStmtlStaus();
		expensesEntity.setStmtlStaus(stmtlStaus);

		//精算日YYYY/MM/DD→YYYYMMDD変換
		String stmtlDate=expensesBean.getStmtlDate();
		expensesEntity.setStmtlDate(DateUtil.chgMonthToYM(stmtlDate));

		//出金タイプ
		String paymentType=expensesBean.getPaymentType();
		expensesEntity.setPaymentType(paymentType);

		return expensesEntity;

	}
}

