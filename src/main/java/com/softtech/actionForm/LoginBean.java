package com.softtech.actionForm;

import java.io.Serializable;

import javax.validation.constraints.Size;

public class LoginBean implements Serializable {

	// 社員ID
	@Size(min=1, max=30,message="入力してください。")
	private String employeeID;

	//パスワード
	@Size(min=2, max=30,message="パスワードを入力してください。")
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
