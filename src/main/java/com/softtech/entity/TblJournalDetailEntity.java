package com.softtech.entity;

import java.sql.Timestamp;

public class TblJournalDetailEntity {
	 // UID (Primary Key)
    private String uid;

    // 部門ID
    private String divisionID;

    // プロジェクトID
    private String projectID;

    // 共通伝票ID
    private String generalHeaderID;

    // 伝票ヘッダID
    private String journalHeaderID;

    // 会計日付 (format: yyyyMMdd)
    private Timestamp bookDate;

    // 伝票ステータス
    private Integer cdFormStatus;

    // 明細行番号
    private Integer lineNumber;

    // 明細取引日付 (format: yyyyMMdd)
    private String detailDate;

    // 明細行区分
    private Integer cdContentKbn;

    // 貸借区分 (1: 借方, 2: 貸方)
    private Integer cdDrCrKbn;

    // 勘定科目ID
    private String accountTitleID;

    // キャッシュフロー科目ID
    private String cashFlowTitleID;

    // 取引内訳区分
    private Integer cdTransactionDetailKbn;

    // 取引内訳ID
    private Long transactionDetailID;

    // 備考
    private String description;

    // 課税区分
    private Integer cdTaxationKbn;

    // 税処理区分
    private Integer cdCTaxPriceKbn;

    // 消費税率
    private Integer cTaxRate;

    // 元帳通貨取引金額
    private Integer transValue;

    // 元帳通貨本体金額
    private Integer netValue;

    // 元帳通貨消費税額
    private Integer cTaxValue;

    // 元帳通貨元帳表記金額
    private Integer displayValue;

    // 消込み状況
    private Integer cdNegationStatus;

    // 入力者ID
    private String insertID;

    // 入力日時 (format: yyyyMMdd)
    private String insertDate;

    // 最終更新者ID
    private String updateID;

    // 最終更新日時 (format: yyyyMMdd)
    private String updateDate;


    // ゲッターとセッター
    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(String divisionID) {
        this.divisionID = divisionID;
    }

    public String getProjectID() {
        return projectID;
    }

    public void setProjectID(String projectID) {
        this.projectID = projectID;
    }

    public String getGeneralHeaderID() {
        return generalHeaderID;
    }

    public void setGeneralHeaderID(String generalHeaderID) {
        this.generalHeaderID = generalHeaderID;
    }

    public String getJournalHeaderID() {
        return journalHeaderID;
    }

    public void setJournalHeaderID(String journalHeaderID) {
        this.journalHeaderID = journalHeaderID;
    }

    public Timestamp getBookDate() {
        return bookDate;
    }

    public void setBookDate(Timestamp bookDateTimestamp) {
        this.bookDate = bookDateTimestamp;
    }

    public Integer getCdFormStatus() {
        return cdFormStatus;
    }

    public void setCdFormStatus(Integer cdFormStatus) {
        this.cdFormStatus = cdFormStatus;
    }

    public Integer getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(Integer lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDetailDate() {
        return detailDate;
    }

    public void setDetailDate(String detailDate) {
        this.detailDate = detailDate;
    }

    public Integer getCdContentKbn() {
        return cdContentKbn;
    }

    public void setCdContentKbn(Integer cdContentKbn) {
        this.cdContentKbn = cdContentKbn;
    }

    public Integer getCdDrCrKbn() {
        return cdDrCrKbn;
    }

    public void setCdDrCrKbn(Integer cdDrCrKbn) {
        this.cdDrCrKbn = cdDrCrKbn;
    }

    public String getAccountTitleID() {
        return accountTitleID;
    }

    public void setAccountTitleID(String accountTitleID) {
        this.accountTitleID = accountTitleID;
    }

    public String getCashFlowTitleID() {
        return cashFlowTitleID;
    }

    public void setCashFlowTitleID(String cashFlowTitleID) {
        this.cashFlowTitleID = cashFlowTitleID;
    }

    public Integer getCdTransactionDetailKbn() {
        return cdTransactionDetailKbn;
    }

    public void setCdTransactionDetailKbn(Integer cdTransactionDetailKbn) {
        this.cdTransactionDetailKbn = cdTransactionDetailKbn;
    }

    public Long getTransactionDetailID() {
        return transactionDetailID;
    }

    public void setTransactionDetailID(Long transactionDetailID) {
        this.transactionDetailID = transactionDetailID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getCdTaxationKbn() {
        return cdTaxationKbn;
    }

    public void setCdTaxationKbn(Integer cdTaxationKbn) {
        this.cdTaxationKbn = cdTaxationKbn;
    }

    public Integer getCdCTaxPriceKbn() {
        return cdCTaxPriceKbn;
    }

    public void setCdCTaxPriceKbn(Integer cdCTaxPriceKbn) {
        this.cdCTaxPriceKbn = cdCTaxPriceKbn;
    }

    public Integer getcTaxRate() {
        return cTaxRate;
    }

    public void setcTaxRate(Integer cTaxRate) {
        this.cTaxRate = cTaxRate;
    }

    public Integer getTransValue() {
        return transValue;
    }

    public void setTransValue(Integer transValue) {
        this.transValue = transValue;
    }

    public Integer getNetValue() {
        return netValue;
    }

    public void setNetValue(Integer netValue) {
        this.netValue = netValue;
    }

    public Integer getcTaxValue() {
        return cTaxValue;
    }

    public void setcTaxValue(Integer cTaxValue) {
        this.cTaxValue = cTaxValue;
    }

    public Integer getDisplayValue() {
        return displayValue;
    }

    public void setDisplayValue(Integer displayValue) {
        this.displayValue = displayValue;
    }

    public Integer getCdNegationStatus() {
        return cdNegationStatus;
    }

    public void setCdNegationStatus(Integer cdNegationStatus) {
        this.cdNegationStatus = cdNegationStatus;
    }

    public String getInsertID() {
        return insertID;
    }

    public void setInsertID(String insertID) {
        this.insertID = insertID;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getUpdateID() {
        return updateID;
    }

    public void setUpdateID(String updateID) {
        this.updateID = updateID;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }
}
