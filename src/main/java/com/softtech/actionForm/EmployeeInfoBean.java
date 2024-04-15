package com.softtech.actionForm;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

public class EmployeeInfoBean implements Serializable{

	//社員ID
	@NotEmpty(message = "社員IDを入力してください。")
	private String employeeID;
	//社員氏名
	@NotEmpty(message = "社員氏名を入力してください。")
	private String employeeName;
	//パスワード

	private String password;

	//メール
	@NotEmpty(message = "メールを入力してください。")
	private String mailAdress;
	//作成日
	/* private String insertDate;
	//更新日
	private String updateDate;
	*/
	//更新と新規区別用のFlg
	private String insertFlg;

	//検索区別用のFlg
	private String selectFlg;

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}


	public String getMailAdress() {
		return mailAdress;
	}

	public void setMailAdress(String mailAdress) {
		this.mailAdress = mailAdress;
	}


	public String getInsertFlg() {
		return insertFlg;
	}

	public void setInsertFlg(String insertFlg) {
		this.insertFlg = insertFlg;
	}

	public String getSelectFlg() {
		return selectFlg;
	}

	public void setSelectFlg(String selectFlg) {
		this.selectFlg = selectFlg;
	}

}
