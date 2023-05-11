package com.softtech.actionForm;

/**
 * 概要：基本給情報リストの区別用
 *
 * 作成者：孫曄@ソフトテク
 * 作成日：2022/5/5
 */

@SuppressWarnings("unused")
public class BaseSalaryInfoBean {
	//社員ID
	private String employeeID;
	//基本給
	private String baseSalary;
	//残業不足時間
	private String minusHour;
	//残業時間
	private String plusHour;
	//稼働期間From
	private String wkPeriodFrom;
	//稼働期間To
	private String wkPeriodTo;
	//残業単価
	private String overtimePay;
	//控除単価
	private String insufficienttimePay;
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

	public String getBaseSalary() {
		return baseSalary;
	}

	public void setBaseSalary(String baseSalary) {
		this.baseSalary = baseSalary;
	}

	public String getMinusHour() {
		return minusHour;
	}

	public void setMinusHour(String minusHour) {
		this.minusHour = minusHour;
	}

	public String getPlusHour() {
		return plusHour;
	}

	public void setPlusHour(String plusHour) {
		this.plusHour = plusHour;
	}

	public String getWkPeriodFrom() {
		return wkPeriodFrom;
	}

	public void setWkPeriodFrom(String wkPeriodFrom) {
		this.wkPeriodFrom = wkPeriodFrom;
	}

	public String getWkPeriodTo() {
		return wkPeriodTo;
	}

	public void setWkPeriodTo(String wkPeriodTo) {
		this.wkPeriodTo = wkPeriodTo;
	}

	public String overtimePay() {
		return overtimePay;
	}

	public void setovertimePay(String overtimePay) {
		this.overtimePay = overtimePay;

		public String insufficienttimePay() {
			return insufficienttimePay;
		}

		public void setinsufficienttimePay(String insufficienttimePay) {
			this.insufficienttimePay = insufficienttimePay;



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

	public String getOvertimePay() {
		return overtimePay;
	}

	public void setOvertimePay(String overtimePay) {
		this.overtimePay = overtimePay;
	}

	public String getInsufficienttimePay() {
		return insufficienttimePay;
	}

	public void setInsufficienttimePay(String insufficienttimePay) {
		this.insufficienttimePay = insufficienttimePay;
	}
}
