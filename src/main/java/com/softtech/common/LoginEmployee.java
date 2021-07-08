package com.softtech.common;
/**
 * 概要：ログインパラメータ用クラス
 *
 * 作成者：ソフトテク
 * 作成日：2021/5/15
 */
public class LoginEmployee {
	private static final long serialVersionUID = 1L;
	//パスワード
	private String password;
	//メールアドレス
	private String mailAdress;
	//ユーザID
	private String employeeID;
	//ステータス
	private String status;

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
	/**
	 * @return mailAdress
	 */
	public String getMailAdress() {
		return mailAdress;
	}
	/**
	 * @param mailAdress セットする mailAdress
	 */
	public void setMailAdress(String mailAdress) {
		this.mailAdress = mailAdress;
	}
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
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status セットする status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
