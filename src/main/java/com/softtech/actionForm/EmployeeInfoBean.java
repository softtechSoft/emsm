package com.softtech.actionForm;
/**
 * @program
 * @概要:
 * @作成者:スッ
 * @作成日:2023-10-20
 * @return:
 */
public class EmployeeInfoBean {
	//社員ID
	private String employeeID;
	//社員氏名
	private String employeeName;
	//パスワード
	/*private String password;
	//ステータス
	private String status;*/
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
	//作成日
	private String insertDate;
	//更新日
	private String updateDate;

	public String getEmployeeID() {
		return employeeID;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	/*public String getPassword() {
		return password;
	}

	public String getStatus(){
		return status;

	}*/

	public String getSex() {
		return sex;
	}

	public String getEpType(){
		return epType;
	}

	public String getbirthday() {
		return birthday;
	}
	public String getAge() {
		return age;
	}

	public String getJoinedDate() {
		return joinedDate;
	}

	public String getJoinedTime(){
		return joinedTime;

	}

	public String getPostCode() {
		return postCode;
	}

	public String getAddress(){
		return address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public String getAuthority(){
		return authority;

	}

	public String getMailAdress() {
		return mailAdress;
	}

	public String getInsertDate(){
		return insertDate;
	}

	public String getUpdateDate() {
		return updateDate;
	}

}
