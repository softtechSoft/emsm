package com.softtech.entity;

public class Yukyu {

	//ユーザID
	private String employeeID;

	//年度
    private String nendo;

    //総日数
    private String totalDay;

    //消化日数
    private String usedDay;

    //作成日
    private String insertDate;

    //更新日
    private String updateDate;

    //getter and setter
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getNendo() {
		return nendo;
	}
	public void setNendo(String nendo) {
		this.nendo = nendo;
	}
	public String getTotalDay() {
		return totalDay;
	}
	public void setTotalDay(String totalDay) {
		this.totalDay = totalDay;
	}
	public String getUsedDay() {
		return usedDay;
	}
	public void setUsedDay(String usedDay) {
		this.usedDay = usedDay;
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

}
