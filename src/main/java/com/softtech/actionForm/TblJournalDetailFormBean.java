package com.softtech.actionForm;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class TblJournalDetailFormBean {

    // 行番号
    private String lineNumber;

    // 勘定科目ID（必須）
    @NotBlank(message = "借方の勘定科目IDは必須です")
    private String accountTitleID;

    // 勘定科目名
    private String accountTitleName;

    // 課税区分
    @NotBlank(message = "借方の課税区分は必須です")
    private String cdTaxationKbn;

    // 課税区分1
    @NotBlank(message = "貸方の課税区分は必須です")
    private String cdTaxationKbn1;

    // 税処理区分
    @NotBlank(message = "借方の税処理区分は必須です")
    private String cdCTaxPriceKbn;

    // 税処理区分1
    @NotBlank(message = "貸方の税処理区分は必須です")
    private String cdCTaxPriceKbn1;

    // 借方の金額（必須）
    @NotEmpty(message = "借方金額を入力してください。")

    @Pattern(message = "借方に数字のみを入力してください。",  regexp = "^[0-9]+")
    private String transValue;

    // 貸方の金額（必須）
    @NotEmpty(message = "貸方金額を入力してください。")

    @Pattern(message = "貸方に数字のみを入力してください。",  regexp = "^[0-9]+")
    private String transValue1;

    // 備考
    private String description;

    // 備考1
    private String description1;

    // UID
    private String uid;

    // 勘定科目ID1（必須）
    @NotBlank(message = "貸方の勘定科目IDは必須です")
    private String accountTitleID1;

    // 勘定科目名1
    private String accountTitleName1;

    // 会計日付
    private String bookDate;

    // ゲッターとセッター
    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getAccountTitleID() {
        return accountTitleID;
    }

    public void setAccountTitleID(String accountTitleID) {
        this.accountTitleID = accountTitleID;
    }

    public String getAccountTitleName() {
        return accountTitleName;
    }

    public void setAccountTitleName(String accountTitleName) {
        this.accountTitleName = accountTitleName;
    }

    public String getCdTaxationKbn() {
        return cdTaxationKbn;
    }

    public void setCdTaxationKbn(String cdTaxationKbn) {
        this.cdTaxationKbn = cdTaxationKbn;
    }

    public String getCdCTaxPriceKbn() {
        return cdCTaxPriceKbn;
    }

    public void setCdCTaxPriceKbn(String cdCTaxPriceKbn) {
        this.cdCTaxPriceKbn = cdCTaxPriceKbn;
    }

    public  String getTransValue() {
        return transValue;
    }

    public void setTransValue(String transValue) {
        this.transValue = transValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getAccountTitleID1() {
        return accountTitleID1;
    }

    public void setAccountTitleID1(String accountTitleID1) {
        this.accountTitleID1 = accountTitleID1;
    }

    public String getAccountTitleName1() {
        return accountTitleName1;
    }

    public void setAccountTitleName1(String accountTitleName1) {
        this.accountTitleName1 = accountTitleName1;
    }

    public String getTransValue1() {
        return transValue1;
    }

    public void setTransValue1( String transValue1) {
        this.transValue1 = transValue1;
    }

    public String getCdCTaxPriceKbn1() {
        return cdCTaxPriceKbn1;
    }

    public void setCdCTaxPriceKbn1(String cdCTaxPriceKbn1) {
        this.cdCTaxPriceKbn1 = cdCTaxPriceKbn1;
    }

    public String getCdTaxationKbn1() {
        return cdTaxationKbn1;
    }

    public void setCdTaxationKbn1(String cdTaxationKbn1) {
        this.cdTaxationKbn1 = cdTaxationKbn1;
    }

    public String getDescription1() {
        return description1;
    }

    public void setDescription1(String description1) {
        this.description1 = description1;
    }

    public String getBookDate() {
        return bookDate;
    }

    public void setBookDate(String bookDate) {
        this.bookDate = bookDate;
    }
}
