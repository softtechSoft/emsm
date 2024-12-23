package com.softtech.entity;

public class EmployeeInfoEntity {

//社員ID
	private String employeeID;
	//社員氏名
	private String employeeName;
	//パスワード
	private String password;
	//ステータス
	private String status;
	//性別
	private String sex;
	//タイプ
	private String epType;
	//生年月日
	private String birthday;
	//年齢
	private String age;
	//入社年月日
	private String joinedDate;
	//社齢
	private String joinedTime;
	//郵便番号
	private String postCode;
	//住所
	private String address;
	//電話番号
	private String phoneNumber;
	//権限
	private String authority;
	//メール
	private String mailAdress;
	private String personNumber;
//	//作成日
	private String insertDate;
//	//更新日
	private String updateDate;
	//更新と新規区別用のFlg
	private String insertFlg;
	//検索と全量検索区別用のFlg
	private String selectFlg;
	private String EmployeeIDAll;
	private String department;


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
	public String getPersonNumber() {
		return personNumber;
	}

	public void setPersonNumber(String personNumber) {
		this.personNumber = personNumber;
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

	public String getEmployeeIDAll() {
		return EmployeeIDAll;
	}

	public void setEmployeeIDAll(String employeeIDAll) {
		EmployeeIDAll = employeeIDAll;
	}
//
//	public void setSex(String string) {
//		// TODO 自動生成されたメソッド・スタブ
//
//	}
//
//	public Object getSex() {
//		// TODO 自動生成されたメソッド・スタブ
//		return null;
//	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}


}
