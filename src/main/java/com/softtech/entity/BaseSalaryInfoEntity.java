package com.softtech.entity;

import java.util.Objects;

/**
 * 概要：基本給情報クラス
 * <p>
 * 作成者：孫@ソフトテク
 * 作成日：2022/5/4
 */

public class BaseSalaryInfoEntity {
    //社員ID
    private String employeeID;
    //社員名
    private String employeeName;
    //基本給
    private String baseSalary;
    //対象年度
    private String year;
     //不足減単価(h)
    private String minusHour;
    //残業加単価(h)
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

    //get,set,toString,hashCode,equals


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

    @Override
    public String toString() {
        return "BaseSalaryInfoEntity{" +
                "employeeID='" + employeeID + '\'' +
                ", baseSalary='" + baseSalary + '\'' +
                ", year='" + year + '\'' +
                ", minusHour='" + minusHour + '\'' +
                ", plusHour='" + plusHour + '\'' +
                ", wkPeriodFrom='" + wkPeriodFrom + '\'' +
                ", wkPeriodTo='" + wkPeriodTo + '\'' +
                ", status=" + status +
                ", insertDate='" + insertDate + '\'' +
                ", updateDate='" + updateDate + '\'' +
                ", baseSalaryID='" + baseSalaryID + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BaseSalaryInfoEntity that = (BaseSalaryInfoEntity) o;
        return status == that.status && Objects.equals(employeeID, that.employeeID) && Objects.equals(baseSalary,
                that.baseSalary) && Objects.equals(year,that.year)&& Objects.equals(minusHour, that.minusHour) && Objects.equals(plusHour,
                that.plusHour) && Objects.equals(wkPeriodFrom, that.wkPeriodFrom) && Objects.equals(wkPeriodTo,
                that.wkPeriodTo) && Objects.equals(insertDate, that.insertDate) && Objects.equals(updateDate,
                that.updateDate) && Objects.equals(baseSalaryID, that.baseSalaryID);
    }

    @Override
    public int hashCode() {
        return Objects.hash(employeeID, baseSalary,year, minusHour, plusHour, wkPeriodFrom, wkPeriodTo, status, insertDate,
                updateDate, baseSalaryID);
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
