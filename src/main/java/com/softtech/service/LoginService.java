package com.softtech.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.LoginBean;
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
			//　社員情報を取得する
			LoginEntity employeeID = new LoginEntity();
			employeeID = loginMappers.getOldPassword(loginBean.getEmployeeID());


			   if(employeeID !=null) {
		           if(employeeID.getPassword() != null) {
		        	   // ログイン画面に入力したパスワードとDBに登録したパスワードと一致する場合、ログイン成功。
		        	   if( employeeID.getPassword().equals(loginBean.getPassword())) {
		        		     return true;
		        	   } else {
		        		   return false;
		        	   }
		           } else {
		        	   return false;
		           }
			   } else {
				   return false;
			   }
		}



}
