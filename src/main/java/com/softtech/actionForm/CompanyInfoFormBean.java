package com.softtech.actionForm;

import javax.validation.constraints.NotEmpty;

public class CompanyInfoFormBean {
	 // 取引先ID
//    @NotEmpty(message = "取引先IDを入力してください")
    private String companyID;

    // 取引先名称
    @NotEmpty(message = "取引先名称を入力してください")
    private String companyName;

    // 取引先種類
    @NotEmpty(message = "取引先種類を選択してください")
    private String companyType;

    // 郵便番号
    @NotEmpty(message = "郵便番号を入力してください")
    private String postCode;

    // 住所
    @NotEmpty(message = "住所を入力してください")
    private String address;

    // 基本契約日
    @NotEmpty(message = "基本契約日を入力してください")
    private String basicContractDate;

    // 電話番号
    @NotEmpty(message = "電話番号を入力してください")
    private String phoneNumber;

    // 連絡先名
    @NotEmpty(message = "連絡先名を入力してください")
    private String contactName;

    // メールアドレス
    @NotEmpty(message = "メールアドレスを入力してください")
    private String mail;

    // 取引ステータス
    @NotEmpty(message = "取引ステータスを選択してください")
    private String contractStatus;

    // 評判レベル
    @NotEmpty(message = "評判レベルを選択してください")
    private String level;

    // 作成日
    @NotEmpty(message = "作成日を入力してください")
    private String insertDate;

    // 更新日
    @NotEmpty(message = "更新日を入力してください")
    private String updateDate;
    private String insertFlg;
    private String selectFlg;
    // Getters and Setters

    public String getCompanyID() {
        return companyID;
    }

    public void setCompanyID(String companyID) {
        this.companyID = companyID;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyType() {
        return companyType;
    }

    public void setCompanyType(String companyType) {
        this.companyType = companyType;
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = postCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBasicContractDate() {
        return basicContractDate;
    }

    public void setBasicContractDate(String basicContractDate) {
        this.basicContractDate = basicContractDate;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getContractStatus() {
        return contractStatus;
    }

    public void setContractStatus(String contractStatus) {
        this.contractStatus = contractStatus;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
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

	public String getSelectFlg() {
		return selectFlg;
	}

	public void setSelectFlg(String selectFlg) {
		this.selectFlg = selectFlg;
	}
}
