package com.softtech.actionForm;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class EmployeeInfoFormBean {

	//社員ID
	@NotEmpty(message = "社員IDを入力してください。")
	private String employeeID;
	//社員氏名
	@NotEmpty(message = "社員氏名を入力してください。")
	private String employeeName;
	//パスワード
	private String password;
	//ステータス
	//ステータス0：在籍　１:離職
	private String status;
	//性別
	//性別 0:男　1:女
	private String sex;
	//タイプ
	//タイプ 0:しない　1:する
	private String epType;
	//生年月日
	@NotEmpty(message = "生年月日を入力してください。例：20231031")
	@Pattern(message = "生年月日に数字のみを入力してください。例：20231031",  regexp = "^[0-9]+")
	private String birthday;
	//年齢
	@NotEmpty(message = "年齢を入力してください。")
	@Pattern(message = "年齢に数字のみを入力してください。",  regexp = "^[0-9]+")
	private String age;
	//入社年月日
	
	@NotEmpty(message = "入社年月日を入力してください。例：20231031")
	@Pattern(message = "入社年月日に数字のみを入力してください。",  regexp = "^[0-9]+")
	private String joinedDate;
	//社齢
	@NotEmpty(message = "社齢を入力してください。")
	@Pattern(message = "社齢に数字のみを入力してください。",  regexp = "^[0-9]+")
	private String joinedTime;
	//郵便番号
	@NotEmpty(message = "郵便番号を入力してください。")
	@Pattern(message = "郵便番号に数字のみを入力してください。",  regexp = "^[0-9]+")
	private String postCode;
	//住所
	@NotEmpty(message = "住所を入力してください。")
	private String address;
	//電話番号
	@NotEmpty(message = "電話番号を入力してください。")
	@Pattern(message = "電話番号に数字のみを入力してください。",  regexp = "^[0-9]+")
	private String phoneNumber;
	//権限
	@NotEmpty(message = "権限を入力してください。")
	private String authority;
	//メール
	@NotEmpty(message = "メールを入力してください。")
	private String mailAdress;
	//作成日
	private String insertDate;
	//更新日
	private String updateDate;
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getEpType() {
		return epType;
	}

	public void setEpType(String epType) {
		this.epType = epType;
	}

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}

	public String getJoinedTime() {
		return joinedTime;
	}

	public void setJoinedTime(String joinedTime) {
		this.joinedTime = joinedTime;
	}

	public String getPostCode() {
		return postCode;
	}

	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAuthority() {
		return authority;
	}

	public void setAuthority(String authority) {
		this.authority = authority;
	}

	public String getMailAdress() {
		return mailAdress;
	}

	public void setMailAdress(String mailAdress) {
		this.mailAdress = mailAdress;
	}

	public String getInsertDate() {
		return insertDate;
	}

	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
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
