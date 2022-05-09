package com.softtech.actionForm;

/**
 * 概要：基本給情報リストの区別用
 *
 * 作成者：孫曄@ソフトテク
 * 作成日：2022/5/5
 */

public class BaseSalaryInfoBean {
	//社員ID
	private String employeeID;
	//基本給
	private int baseSalary;
	//残業不足時間
	private int minusHour;
	//残業時間
	private int plusHour;
	//稼働期間From
	private int wkPeriodFrom;
	//稼働期間To
	private int wkPeriodTo;
	//利用ステータス
	private int status;
	//作成日
	private String insertDate;
	//更新日
	private String updateDate;
	//基本給ID
	private String baseSalaryID;


	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public int getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}
	public int getMinusHour() {
		return minusHour;
	}
	public void setMinusHour(int minusHour) {
		this.minusHour = minusHour;
	}
	public int getPlusHour() {
		return plusHour;
	}
	public void setPlusHour(int plusHour) {
		this.plusHour = plusHour;
	}
	public int getWkPeriodFrom() {
		return wkPeriodFrom;
	}
	public void setWkPeriodFrom(int wkPeriodFrom) {
		this.wkPeriodFrom = wkPeriodFrom;
	}
	public int getWkPeriodTo() {
		return wkPeriodTo;
	}
	public void setWkPeriodTo(int wkPeriodTo) {
		this.wkPeriodTo = wkPeriodTo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
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
	public String getBaseSalaryID() {
		return baseSalaryID;
	}
	public void setBaseSalaryID(String baseSalaryID) {
		this.baseSalaryID = baseSalaryID;
	}


}
