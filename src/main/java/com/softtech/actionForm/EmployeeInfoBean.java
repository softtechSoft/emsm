package com.softtech.actionForm;

import java.io.Serializable;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class EmployeeInfoBean implements Serializable{

	//社員ID
	@NotBlank(message = "社員IDを入力してください。")
	@Size(max = 6, message = "社員IDは6文字以下で入力してください。")
	private String employeeID;
	//社員氏名
	@NotBlank(message = "社員氏名を入力してください。")
	@Size(max = 12, message = "社員氏名は12文字以下で入力してください。")
	private String employeeName;
	//パスワード
	@NotBlank(message = "パスワードを入力してください。")
	@Size(max = 6, message = "パスワードは6文字以下で入力してください。")
	private String password;

	//メール
	@NotBlank(message = "メールを入力してください。")
	@Pattern(regexp = ".+@it-softtech\\.com", message = "メールは'@it-softtech.com'で終わる必要があります。")
	private String mailAdress;
	//作成日
	/* private String insertDate;
	//更新日
	private String updateDate;
	*/
	//更新と新規区別用のFlg
	//ステータス0：在籍　１:離職
	private String status;
	private String authority;
//	private String insertFlg;

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
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}


//	public String getInsertFlg() {
//		return insertFlg;
//	}
//
//	public void setInsertFlg(String insertFlg) {
//		this.insertFlg = insertFlg;
//	}

	public String getSelectFlg() {
		return selectFlg;
	}

	public void setSelectFlg(String selectFlg) {
		this.selectFlg = selectFlg;
	}

}
