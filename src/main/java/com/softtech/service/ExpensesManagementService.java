package com.softtech.service;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.ExpensesManagementBean;
import com.softtech.entity.Employee;
import com.softtech.entity.ExpensesManagementEntity;
import com.softtech.mappers.ExpensesManagementMapper;
import com.softtech.util.DataUtil;
import com.softtech.util.DateUtil;

@Service
public class ExpensesManagementService {
	@Autowired
	ExpensesManagementMapper expensesManagementMapper;
	// DBから社員情報を取得する
	public List<EmployeeActionForm> queryEmployeeInfo() {
		List<Employee> employee = expensesManagementMapper.getEmployee();
		List<EmployeeActionForm> rtn = transferDBTOUI(employee);
		return rtn;

	}
	/*
	 * 機能概要:社員情報をactionformへ変換
	 *
	 * @param employees　dbの社員情報
	 * @return 画面用actionformリスト
	 */
	public List<EmployeeActionForm> transferDBTOUI(List<Employee> employees) {

		List<EmployeeActionForm> employeeAactionForms =new ArrayList<EmployeeActionForm>();
		for(Employee employee:employees) {
			EmployeeActionForm  employeeAactionForm = new EmployeeActionForm();
			employeeAactionForm.setEmployeeID(employee.getEmployeeID());
			employeeAactionForm.setEmployeeName(employee.getEmployeeName());
			employeeAactionForms.add(employeeAactionForm);
		}
		return employeeAactionForms;
	}

	//画面からDBに変換

	public ExpensesManagementEntity  transferToDB(ExpensesManagementBean expensesManagemntBean) {
		ExpensesManagementEntity am = new ExpensesManagementEntity();


		am.setEmployeeID(expensesManagemntBean.getEmployeeID());

		am.setAccrualDate(DateUtil.chgMonthToYM(expensesManagemntBean.getAccrualDate()));
		return am;
	}

	/*
	 * 機能概要：数字チェック
	 *
	 * @param expensesManagementBean　チェック対象
	 * @return エーラリスト
	 */
	public ArrayList<FieldError> chkNumberData(ExpensesManagementBean expensesManagementBean) {

		// エラーチェック用
		ArrayList<FieldError> errorlst = new ArrayList<FieldError>();

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
	public ArrayList<FieldError> chkDate(ExpensesManagementBean expensesManagementBean) {

		// エラーチェック用
		ArrayList<FieldError> errorlst = new ArrayList<FieldError>();

		// 発生日ではない場合、エラーメッセージ戻す
		String accrualDate=expensesManagementBean.getAccrualDate();
		if(!DateUtil.isDate(accrualDate)) {

			FieldError err1 = new FieldError("", "", "発生日に通常の日付を入力してください	。");
			errorlst.add(err1);
		}

		// 精算日ではない場合、エラーメッセージ戻す
		String stmtlDate=expensesManagementBean.getStmtlDate();
        //精算日は日付チェックではない場合

		if(!DateUtil.isDate(stmtlDate)) {

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
			/*
			 * 機能概要：画面データをEntityクラスに設定する
			 *
			 * @param expensesManagementBean 画面データ
			 * @return Entityクラス
			 */

	private ExpensesManagementEntity tranferBeanToEntity(ExpensesManagementBean expensesManagementBean) {
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

				String expensesTypeDetail =expensesManagementBean.getExpensesTypeDetail();
				expensesManagementEntity.setExpensesTypeDetail(expensesTypeDetail);

				//場所
				String happenAddress =expensesManagementBean.getHappenAddress();
				expensesManagementEntity.setHappenAddress(happenAddress);

				//承認ステータス
				String confirmStauts =expensesManagementBean.getConfirmStaus();
				expensesManagementEntity.setConfirmStaus(confirmStauts);

				//精算タイプ
				String paymentType =expensesManagementBean.getStmtlType();
				expensesManagementEntity.setPaymentType(paymentType);
				//精算ステータス
				String stmtlStaus=expensesManagementBean.getStmtlStaus();
				expensesManagementEntity.setStmtlStaus(stmtlStaus);

				//精算日YYYY/MM/DD→YYYYMMDD変換
			    String stmtlDate=expensesManagementBean.getStmtlDate();
				expensesManagementEntity.setStmtlDate(DateUtil.chgMonthToYM(stmtlDate));


				//備考
				String remark=expensesManagementBean.getRemark();
				expensesManagementEntity.setRemark(remark);

				return expensesManagementEntity;

			}

}
