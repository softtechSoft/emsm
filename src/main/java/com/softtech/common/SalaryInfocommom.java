package com.softtech.common;
/**
 * 概要：給料作成パラメータ用クラス
 *
 * 作成者：ソフトテク
 * 作成日：2021/5/15
 */
public class SalaryInfocommom {
	private static final long serialVersionUID = 1L;
	private String employeeID;
	private String month;
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
	 * @return month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month セットする month
	 */
	public void setMonth(String month) {
		this.month = month;
	}

}
