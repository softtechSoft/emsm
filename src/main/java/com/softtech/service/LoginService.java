package com.softtech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.LoginBean;
import com.softtech.common.LoginEmployee;
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
		   if(employeeID !=null) {
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

}
