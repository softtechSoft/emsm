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
	//対象年度
	private String year;
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
	 * @return baseSalary
	 */
	public String getBaseSalary() {
		return baseSalary;
	}
	/**
	 * @return year
	 */
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	/**
	 * @param baseSalary セットする baseSalary
	 */
	public void setBaseSalary(String baseSalary) {
		this.baseSalary = baseSalary;
	}
	/**



	/**
	 * @return wkPeriodFrom
	 */
	public String getWkPeriodFrom() {
		return wkPeriodFrom;
	}
	/**
	 * @param wkPeriodFrom セットする wkPeriodFrom
	 */
	public void setWkPeriodFrom(String wkPeriodFrom) {
		this.wkPeriodFrom = wkPeriodFrom;
	}
	/**
	 * @return wkPeriodTo
	 */
	public String getWkPeriodTo() {
		return wkPeriodTo;
	}
	/**
	 * @param wkPeriodTo セットする wkPeriodTo
	 */
	public void setWkPeriodTo(String wkPeriodTo) {
		this.wkPeriodTo = wkPeriodTo;
	}
	/**
	 * @return overtimePay
	 */
	public String getOvertimePay() {
		return overtimePay;
	}
	/**
	 * @param overtimePay セットする overtimePay
	 */
	public void setOvertimePay(String overtimePay) {
		this.overtimePay = overtimePay;
	}
	/**
	 * @return insufficienttimePay
	 */
	public String getInsufficienttimePay() {
		return insufficienttimePay;
	}
	/**
	 * @param insufficienttimePay セットする insufficienttimePay
	 */
	public void setInsufficienttimePay(String insufficienttimePay) {
		this.insufficienttimePay = insufficienttimePay;
	}
	/**
	 * @return status
	 */
	public int getStatus() {
		return status;
	}
	/**
	 * @param status セットする status
	 */
	public void setStatus(int status) {
		this.status = status;
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
	/**
	 * @return baseSalaryID
	 */
	public String getBaseSalaryID() {
		return baseSalaryID;
	}
	/**
	 * @param baseSalaryID セットする baseSalaryID
	 */
	public void setBaseSalaryID(String baseSalaryID) {
		this.baseSalaryID = baseSalaryID;
	}

}
