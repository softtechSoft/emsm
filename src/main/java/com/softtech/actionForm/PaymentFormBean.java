package com.softtech.actionForm;
public class PaymentFormBean {
    private String paymentID; // 支払ID
    private String paymentMonth; // 対象月
    private String companyID; // 会社名
    private String paymentEmployeeName; // 社員名
    private String basicAmount; // 基本金額
    private String overtimeAmount; // 残業額
    private String paymentDate; // 支払（予定）日

    public String getPaymentID() {
		return paymentID;
	}
	public void setPaymentID(String paymentID) {
		this.paymentID = paymentID;
	}
	public String getPaymentMonth() {
		return paymentMonth;
	}
	public void setPaymentMonth(String paymentMonth) {
		this.paymentMonth = paymentMonth;
	}
	public String getCompanyID() {
		return companyID;
	}
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	public String getPaymentEmployeeName() {
		return paymentEmployeeName;
	}
	public void setPaymentEmployeeName(String paymentEmployeeName) {
		this.paymentEmployeeName = paymentEmployeeName;
	}
	public String getBasicAmount() {
		return basicAmount;
	}
	public void setBasicAmount(String basicAmount) {
		this.basicAmount = basicAmount;
	}
	public String getOvertimeAmount() {
		return overtimeAmount;
	}
	public void setOvertimeAmount(String overtimeAmount) {
		this.overtimeAmount = overtimeAmount;
	}
	public String getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

}