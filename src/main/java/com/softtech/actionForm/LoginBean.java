package com.softtech.actionForm;

import java.io.Serializable;




public class LoginBean implements Serializable {

	// 社員ID
//	@NotEmpty(message="{login.error.accountId.notEmpty}")
//    @Email(message="{login.error.accountId.isEmail}")
	private String employeeID;

	//パスワード
//	@Size(min=2, max=30,message="パスワードを入力してください。")
	private String password;

	/**
	 * @return employeeID
	 */
	public String getEmployeeID() {
		return employeeID;
	}
	/**
	 * @param employeeID セットする employeeID
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	/**
	 * @return password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password セットする password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
