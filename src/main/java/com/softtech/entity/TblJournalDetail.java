package com.softtech.entity;

import java.sql.Timestamp;

public class TblJournalDetail {
	  // UID
    private String uid;

    // プロジェクトID
    private String projectID;

    // 会計日付
    private Timestamp bookDate;

    // 行番号
    private String lineNumber;

    // 勘定科目ID
    private String debitAccountTitleID;

    // 備考
    private String debitDescription;

    // 備考1
    private String creditDescription;

    // 課税区分
    private String debitcdTaxationKbn;

    // 課税区分1
    private String creditcdTaxationKbn;

    // 消費税額区分
    private String debitcdCTaxPriceKbn;

    // 消費税額区分1
    private String creditcdCTaxPriceKbn;

    // 金額
    private String debitTransValue;

    // 金額1
    private String creditTransValue;

    // 勘定科目名
    private String debitAccountTitleName;

    // 勘定科目ID1
    private String creditAccountTitleID;

    // 勘定科目名1
    private String creditAccountTitleName;
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}

	public String getProjectID() {
		return projectID;
	}
	public void setProjectID(String projectID) {
		this.projectID = projectID;
	}

	public Timestamp getBookDate() {
		return bookDate;
	}
	public void setBookDate(Timestamp bookDateTimestamp) {
		this.bookDate = bookDateTimestamp;
	}

	public String getLineNumber() {
		return lineNumber;
	}
	public void setLineNumber(String lineNumber) {
		this.lineNumber = lineNumber;
	}

	public String getdebitAccountTitleID() {
		return debitAccountTitleID;
	}
	public void setdebitAccountTitleID(String debitAccountTitleID) {
		this.debitAccountTitleID = debitAccountTitleID;
	}

	public String getdebitDescription() {
		return debitDescription;
	}
	public void setdebitDescription(String debitDescription) {
		this.debitDescription = debitDescription;
	}
	public String getdebitcdTaxationKbn() {
		return debitcdTaxationKbn;
	}
	public void setdebitcdTaxationKbn(String debitcdTaxationKbn) {
		this.debitcdTaxationKbn = debitcdTaxationKbn;
	}
	public String getdebitcdCTaxPriceKbn() {
		return debitcdCTaxPriceKbn;
	}
	public void setdebitcdCTaxPriceKbn(String debitcdCTaxPriceKbn) {
		this.debitcdCTaxPriceKbn = debitcdCTaxPriceKbn;
	}

	public String getdebitTransValue() {
		return debitTransValue;
	}
	public void  setdebitTransValue(String debitTransValue) {
		this.debitTransValue = debitTransValue;
	}

	public String getdebitAccountTitleName() {
		return debitAccountTitleName;
	}
	public void setdebitAccountTitleName(String debitAccountTitleName) {
		this.debitAccountTitleName = debitAccountTitleName;
	}
	public String getcreditAccountTitleID() {
		return creditAccountTitleID;
	}
	public void setcreditAccountTitleID(String creditAccountTitleID) {
		this.creditAccountTitleID = creditAccountTitleID;
	}
	public String getcreditAccountTitleName() {
		return creditAccountTitleName;
	}
	public void setcreditAccountTitleName(String creditAccountTitleName) {
		this.creditAccountTitleName = creditAccountTitleName;
	}
	public String getcreditTransValue() {
		return creditTransValue;
	}
	public void setcreditTransValue(String creditTransValue) {
		this.creditTransValue = creditTransValue;
	}
	public String getcreditDescription() {
		return creditDescription;
	}
	public void setcreditDescription(String creditDescription) {
		this.creditDescription = creditDescription;
	}
	public String getcreditcdTaxationKbn() {
		return creditcdTaxationKbn;
	}
	public void setcreditcdTaxationKbn(String creditcdTaxationKbn) {
		this.creditcdTaxationKbn = creditcdTaxationKbn;
	}
	public String getcreditcdCTaxPriceKbn() {
		return creditcdCTaxPriceKbn;
	}
	public void setcreditcdCTaxPriceKbn(String creditcdCTaxPriceKbn) {
		this.creditcdCTaxPriceKbn = creditcdCTaxPriceKbn;
	}

    // Getters and setters
}
