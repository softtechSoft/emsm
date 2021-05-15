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

}
