package com.softtech.actionForm;

public class TblJournalDetailFormBean {

    // 行番号
    private String lineNumber;

    // 勘定科目ID（必須）

    private String debitAccountTitleID;

    // 勘定科目名
    private String debitAccountTitleName;

    // 課税区分

    private String debitcdTaxationKbn;

    // 課税区分1

    private String creditcdTaxationKbn;

    // 税処理区分

    private String debitcdCTaxPriceKbn;

    // 税処理区分1

    private String creditcdCTaxPriceKbn;

    // 借方の金額（必須）



    private String debitTransValue;

    // 貸方の金額（必須）



    private String creditTransValue;

    // 備考
    private String debitDescription;

    // 備考1
    private String creditDescription;

    // UID
    private String uid;

    // 勘定科目ID1（必須）

    private String creditAccountTitleID;

    // 勘定科目名1
    private String creditAccountTitleName;

    // 会計日付
    private String bookDate;

    // ゲッターとセッター

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

    public String getdebitAccountTitleName() {
        return debitAccountTitleName;
    }

    public void setdebitAccountTitleName(String debitAccountTitleName) {
        this.debitAccountTitleName = debitAccountTitleName;
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

    public  String getdebitTransValue() {
        return debitTransValue;
    }

    public void setdebitTransValue(String debitTransValue) {
        this.debitTransValue = debitTransValue;
    }

    public String getdebitDescription() {
        return debitDescription;
    }

    public void setdebitDescription(String debitDescription) {
        this.debitDescription = debitDescription;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
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

    public void setcreditTransValue( String creditTransValue) {
        this.creditTransValue = creditTransValue;
    }

    public String getcreditcdCTaxPriceKbn() {
        return creditcdCTaxPriceKbn;
    }

    public void setcreditcdCTaxPriceKbn(String creditcdCTaxPriceKbn) {
        this.creditcdCTaxPriceKbn = creditcdCTaxPriceKbn;
    }

    public String getcreditcdTaxationKbn() {
        return creditcdTaxationKbn;
    }

    public void setcreditcdTaxationKbn(String creditcdTaxationKbn) {
        this.creditcdTaxationKbn = creditcdTaxationKbn;
    }

    public String getcreditDescription() {
        return creditDescription;
    }

    public void setcreditDescription(String creditDescription) {
        this.creditDescription = creditDescription;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
}
