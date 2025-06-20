package com.softtech.actionForm;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-10
 * @return:
 */
public class IncomeTaxInfoFormBean {
    //社員ID
    private String employeeID;
    //社員name
    private String employeeName;
    //所得税ID
//    @NotEmpty(message = "所得税IDを入力してください。")
    private String incomeTaxID;
    //年度
    @NotEmpty(message = "年度を入力してください。")
    @Pattern(message = "年度に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String year;
    
    private String searchYear;
    
    //所得税
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax1;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax2;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax3;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax4;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax5;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax6;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax7;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax8;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax9;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax10;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax11;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax12;
    //住民税
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax1;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax2;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax3;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax4;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax5;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax6;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax7;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax8;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax9;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax10;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax11;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax12;
    //利用ステータス 0:未使用　1:使用中
    private String status;
    //作成日
    private String insertDate;
    //更新日
    private String updateDate;
    //更新と新規区別用のFlg
    private String insertFlg;

    public String getIncomeTaxID() {
        return incomeTaxID;
    }

    public void setIncomeTaxID(String incomeTaxID) {
        this.incomeTaxID = incomeTaxID;
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
    
    public String getSearchYear() {
        return searchYear;  
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
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

    public String getResidentTax1() {
        return residentTax1;
    }

    public void setResidentTax1(String residentTax1) {
        this.residentTax1 = residentTax1;
    }

    public String getResidentTax2() {
        return residentTax2;
    }

    public void setResidentTax2(String residentTax2) {
        this.residentTax2 = residentTax2;
    }

    public String getResidentTax3() {
        return residentTax3;
    }

    public void setResidentTax3(String residentTax3) {
        this.residentTax3 = residentTax3;
    }

    public String getResidentTax4() {
        return residentTax4;
    }

    public void setResidentTax4(String residentTax4) {
        this.residentTax4 = residentTax4;
    }

    public String getResidentTax5() {
        return residentTax5;
    }

    public void setResidentTax5(String residentTax5) {
        this.residentTax5 = residentTax5;
    }

    public String getResidentTax6() {
        return residentTax6;
    }

    public void setResidentTax6(String residentTax6) {
        this.residentTax6 = residentTax6;
    }

    public String getResidentTax7() {
        return residentTax7;
    }

    public void setResidentTax7(String residentTax7) {
        this.residentTax7 = residentTax7;
    }

    public String getResidentTax8() {
        return residentTax8;
    }

    public void setResidentTax8(String residentTax8) {
        this.residentTax8 = residentTax8;
    }

    public String getResidentTax9() {
        return residentTax9;
    }

    public void setResidentTax9(String residentTax9) {
        this.residentTax9 = residentTax9;
    }

    public String getResidentTax10() {
        return residentTax10;
    }

    public void setResidentTax10(String residentTax10) {
        this.residentTax10 = residentTax10;
    }

    public String getResidentTax11() {
        return residentTax11;
    }

    public void setResidentTax11(String residentTax11) {
        this.residentTax11 = residentTax11;
    }

    public String getResidentTax12() {
        return residentTax12;
    }

    public void setResidentTax12(String residentTax12) {
        this.residentTax12 = residentTax12;
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

    public String getInsertFlg() {
        return insertFlg;
    }

    public void setInsertFlg(String insertFlg) {
        this.insertFlg = insertFlg;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }
}
