package com.softtech.entity;

public class Employee {
	/**
	 *
	 */

	 String employeeID;
	 String employeeName;
	 String password;
	 String status;
	 String sex;
	 String epType;
	 String birthday;
	 String age;
	 String joinedDate;
	 String joinedTime;
	 String postCode;
	 String address;
	 String phoneNumber;
	 String authority;
	 String mailAdress;
	 String insertDate;
	 String updateDate;
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
	 * @return employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName セットする employeeName
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
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
	/**
	 * @return sex
	 */
	public String getSex() {
		return sex;
	}
	/**
	 * @param sex セットする sex
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}
	/**
	 * @return birthday
	 */
	public String getBirthday() {
		return birthday;
	}
	/**
	 * @param birthday セットする birthday
	 */
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}
	/**
	 * @return age
	 */
	public String getAge() {
		return age;
	}
	/**
	 * @param age セットする age
	 */
	public void setAge(String age) {
		this.age = age;
	}
	/**
	 * @return joinedDate
	 */
	public String getJoinedDate() {
		return joinedDate;
	}
	/**
	 * @param joinedDate セットする joinedDate
	 */
	public void setJoinedDate(String joinedDate) {
		this.joinedDate = joinedDate;
	}
	/**
	 * @return joinedTime
	 */
	public String getJoinedTime() {
		return joinedTime;
	}
	/**
	 * @param joinedTime セットする joinedTime
	 */
	public void setJoinedTime(String joinedTime) {
		this.joinedTime = joinedTime;
	}
	/**
	 * @return postCode
	 */
	public String getPostCode() {
		return postCode;
	}
	/**
	 * @param postCode セットする postCode
	 */
	public void setPostCode(String postCode) {
		this.postCode = postCode;
	}
	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address セットする address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}
	/**
	 * @param phoneNumber セットする phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	/**
	 * @return authority
	 */
	public String getAuthority() {
		return authority;
	}
	/**
	 * @param authority セットする authority
	 */
	public void setAuthority(String authority) {
		this.authority = authority;
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
	 * @return remark
	 */
	public String getEpType() {
		return epType;
	}
	/**
	 * @param remark セットする remark
	 */
	public void setEpType(String epType) {
		this.epType = epType;
	}
	/**
	 * @return insertDate
	 */
	public String getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate セットする insertDate
	 */
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	/**
	 * @return updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate セットする updateDate
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	@Override
	public String toString() {
		return "Employee [employeeID=" + employeeID + ", employeeName=" + employeeName + ", password=" + password
				+ ", status=" + status + ", sex=" + sex + ", birthday=" + birthday + ", age=" + age + ", joinedDate="
				+ joinedDate + ", joinedTime=" + joinedTime + ", postCode=" + postCode + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", authority=" + authority + ", mailAdress=" + mailAdress
				+ ", epType=" + epType + ", insertDate=" + insertDate + ", updateDate=" + updateDate + "]";
	}


}