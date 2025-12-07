package com.softtech.entity;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-10
 * @return:
 */
public class HoldingTaxInfoEntity {
    //所得税ID
    private String holdingTaxID;
    //社員ID
    private String employeeID;
    //社員name
    private String employeeName;
    //年度
    private String year;

    //所得税
    private String incomeTax1;
    private String incomeTax2;
    private String incomeTax3;
    private String incomeTax4;
    private String incomeTax5;
    private String incomeTax6;
    private String incomeTax7;
    private String incomeTax8;
    private String incomeTax9;
    private String incomeTax10;
    private String incomeTax11;
    private String incomeTax12;

    //利用ステータス 0:未使用　1:使用中
    private String status;
    //作成日
    private String insertDate;
    //更新日
    private String updateDate;
    private String tax;

    public String getHoldingTaxID() {
        return holdingTaxID;
    }

    public void setHoldingTaxID(String holdingTaxID) {
        this.holdingTaxID = holdingTaxID;
    }

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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIncomeTax1() {
        return incomeTax1;
    }

    public void setIncomeTax1(String incomeTax1) {
        this.incomeTax1 = incomeTax1;
    }

    public String getIncomeTax2() {
        return incomeTax2;
    }

    public void setIncomeTax2(String incomeTax2) {
        this.incomeTax2 = incomeTax2;
    }

    public String getIncomeTax3() {
        return incomeTax3;
    }

    public void setIncomeTax3(String incomeTax3) {
        this.incomeTax3 = incomeTax3;
    }

    public String getIncomeTax4() {
        return incomeTax4;
    }

    public void setIncomeTax4(String incomeTax4) {
        this.incomeTax4 = incomeTax4;
    }

    public String getIncomeTax5() {
        return incomeTax5;
    }

    public void setIncomeTax5(String incomeTax5) {
        this.incomeTax5 = incomeTax5;
    }

    public String getIncomeTax6() {
        return incomeTax6;
    }

    public void setIncomeTax6(String incomeTax6) {
        this.incomeTax6 = incomeTax6;
    }

    public String getIncomeTax7() {
        return incomeTax7;
    }

    public void setIncomeTax7(String incomeTax7) {
        this.incomeTax7 = incomeTax7;
    }

    public String getIncomeTax8() {
        return incomeTax8;
    }

    public void setIncomeTax8(String incomeTax8) {
        this.incomeTax8 = incomeTax8;
    }

    public String getIncomeTax9() {
        return incomeTax9;
    }

    public void setIncomeTax9(String incomeTax9) {
        this.incomeTax9 = incomeTax9;
    }

    public String getIncomeTax10() {
        return incomeTax10;
    }

    public void setIncomeTax10(String incomeTax10) {
        this.incomeTax10 = incomeTax10;
    }

    public String getIncomeTax11() {
        return incomeTax11;
    }

    public void setIncomeTax11(String incomeTax11) {
        this.incomeTax11 = incomeTax11;
    }

    public String getIncomeTax12() {
        return incomeTax12;
    }

    public void setIncomeTax12(String incomeTax12) {
        this.incomeTax12 = incomeTax12;
    }


	public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

	/**
	 * @return tax
	 */
	public String getTax() {
		return tax;
	}

	/**
	 * @param tax セットする tax
	 */
	public void setTax(String tax) {
		this.tax = tax;
	}


}
