package com.softtech.actionForm;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.softtech.common.EmployeeIDName;

public class BaseSalaryInfoFormBean {
	//基本給ID
	@NotEmpty(message = "基本給IDを入力してください。")
	private String employeeID;
	//基本給
	@NotEmpty(message = "基本給を入力してください。")
	@Pattern(message = "基本給に数値のみを入力してください。", regexp = "^[0-9]*$")
	private String baseSalary;
	//対象年度
	@NotEmpty(message = "対象年度を入力してください。")
	@Pattern(message = "対象年度のみを入力してください。", regexp = "^[0-9]*$")
	private String  year;
	//残業不足時間
	@NotEmpty(message = "残業不足時間を入力してください。")
	@Pattern(message = "残業不足時間に数値のみを入力してください。", regexp = "^[0-9]*$")
	private String minusHour;
	//残業時間
	@NotEmpty(message = "残業時間を入力してください。")
	@Pattern(message = "残業時間に数値のみを入力してください。", regexp = "^[0-9]*$")
	private String plusHour;
	//稼働期間From
	@NotEmpty(message = "稼働期間Fromを入力してください。")
	@Pattern(message = "稼働期間Fromに数値のみを入力してください。", regexp = "^[0-9]*$")
	private String wkPeriodFrom;
	//稼働期間To
	@NotEmpty(message = "稼働期間Toを入力してください。")
	@Pattern(message = "稼働期間Toに数値のみを入力してください。", regexp = "^[0-9]*$")
	private String wkPeriodTo;
	//残業単価
	private String overtimePay;
	//控除単価
	private String insufficienttimePay;
	//利用ステータス
	@NotNull(message = "利用ステータスを入力してください。")
	private int status;
	//作成日
	private String insertDate;
	//更新日
	private String updateDate;
	//基本給ID
	private String baseSalaryID;
	//更新と新規区別用のFlg
	private String insertFlg;

	//社員IDリスト
	private ArrayList<EmployeeIDName> employeeIDNameList;

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


	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getInsertFlg() {
		return insertFlg;
	}

	public void setInsertFlg(String insertFlg) {
		this.insertFlg = insertFlg;
	}

	public ArrayList<EmployeeIDName> getEmployeeIDNameList() {
		return employeeIDNameList;
	}

	public void setEmployeeIDNameList(ArrayList<EmployeeIDName> employeeIDNameList) {
		this.employeeIDNameList = employeeIDNameList;
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
