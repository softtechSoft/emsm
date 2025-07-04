package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.LoginBean;
import com.softtech.common.BaseSalaryIDName;
import com.softtech.common.CompanyIDName;
import com.softtech.common.ContractIDName;
import com.softtech.common.EmployeeIDName;
import com.softtech.common.LoginEmployee;
import com.softtech.common.WelfarefeeIDName;
import com.softtech.entity.LoginEntity;
import com.softtech.mappers.LoginMappers;

@Service
public class LoginService {
    // 社員情報Mapper
	@Autowired
	LoginMappers loginMappers;

	/**
	 * ログイン処理
	 *
	 * @param loginBean ログイン画面の入力情報
	 * @return ログイン結果。TRUE：ログイン成功、FALSE：ログイン失敗。
	 * @author Softtech
	 */
	public boolean doLogin(LoginBean loginBean) {
		// 社員情報を取得する
		LoginEmployee em = new LoginEmployee();
		// アカウント
		em.setMailAdress(loginBean.getEmployeeID());
		// パスワード
		em.setPassword(loginBean.getPassword());

		LoginEntity employeeID = new LoginEntity();
		employeeID = loginMappers.getOldPassword(em);

		//ログイン成功
	   if(employeeID !=null && "1".equals(employeeID.getAuthority())) {
           return true;
         //ログイン失敗
	   } else {
		   return false;
	   }
	}

	public LoginEntity qureyEmployee(LoginBean loginBean) {
		// 社員情報を取得する
		LoginEmployee em = new LoginEmployee();
		// アカウント
		em.setMailAdress(loginBean.getEmployeeID());
		// パスワード
		em.setPassword(loginBean.getPassword());

		LoginEntity employee = new LoginEntity();
		employee = loginMappers.getOldPassword(em);
		return employee;

	}
	/*
	 * ユーザIDを持ち、ユーザ情報を取得する
	 *
	 * @param loginBean ログイン画面の入力情報
	 *
	 * @return ユーザ情報
	 * @author Softtech
	 */
	public LoginEntity getEmployeeByID(LoginBean loginBean) {
		// 社員情報を取得する
		LoginEmployee em = new LoginEmployee();
		// アカウント
		em.setMailAdress(loginBean.getEmployeeID());

		LoginEntity employee = new LoginEntity();
		employee = loginMappers.getEmployeeByID(em);
		return employee;

	}

	/*
	 * 機能：社員情報から社員IDリストを取得
	 *
	 * @param なし
	 * @return 社員IDリスト
	 * @author
	 */
	public List<EmployeeIDName> getEmployeeList() {

		List<EmployeeIDName> contractList = new ArrayList<EmployeeIDName>();
		// 社員情報テーブルからIDリストを取得する
		contractList = loginMappers.getEmployees();

		return contractList;

	}

	public List<EmployeeIDName> getEmployeeID() {

		List<EmployeeIDName> baseSalaryList = new ArrayList<EmployeeIDName>();
		// 社員情報テーブルからIDリストを取得する
		baseSalaryList = loginMappers.getEmployees();

		return baseSalaryList;

	}

	/**概要:更新画面の年度を表示する用
	*@param:[]
	*@return:java.util.List<com.softtech.common.WelfarefeeIDName>
	*@author:孫曄@SOFTTECH
	*@date:2022/05/31
	*/
	public List<WelfarefeeIDName> getYear() {

		List<WelfarefeeIDName> welfarefeeIDNameList = new ArrayList<WelfarefeeIDName>();
		// 厚生保険料マスタテーブルから年度リストを取得する
		welfarefeeIDNameList = loginMappers.getYear();

		return welfarefeeIDNameList;

	}

	/*
	 * 機能：契約情報から契約IDリストを取得
	 *
	 * @param なし
	 * @return 契約IDリスト
	 * @author
	 */
	public List<ContractIDName> getContractList() {

		List<ContractIDName> contractList = new ArrayList<ContractIDName>();
		// 社員情報テーブルからIDリストを取得する
		contractList = loginMappers.getContracts();

		return contractList;
	}

	public List<BaseSalaryIDName> getBaseSalaryList() {

		List<BaseSalaryIDName> baseSalaryList = new ArrayList<BaseSalaryIDName>();
		// 社員情報テーブルからIDリストを取得する
		baseSalaryList = loginMappers.getBaseSalarys();

		return baseSalaryList;
	}
	/*
	 * 機能：会社情報から会社IDリストを取得
	 *
	 * @param なし
	 * @return 会社IDリスト
	 * @author
	 */
	public List<CompanyIDName> getCompanyList() {

		List<CompanyIDName> contractList = new ArrayList<CompanyIDName>();
		// 社員情報テーブルからIDリストを取得する
		contractList = loginMappers.getCompanys();

		return contractList;
	}
	
	public LoginEntity getEmployeeByMailAddress(LoginBean loginBean) {
	    LoginEmployee em = new LoginEmployee();
	    em.setMailAdress(loginBean.getEmployeeID());
	    
	    LoginEntity employee = loginMappers.getEmployeeByMailAddress(em);
	    return employee;
	}
}
