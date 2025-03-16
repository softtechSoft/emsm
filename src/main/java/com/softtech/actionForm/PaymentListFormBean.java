package com.softtech.actionForm;
public class PaymentListFormBean {
    private String paymentID; // 支払ID
    private String paymentMonth; // 対象月
    private String companyName; // 会社名
    public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	private String paymentEmployeeName; // 社員名
    private String basicAmount; // 基本金額
    private String overtimeAmount; // 残業額
    private String paymentDate; // 支払（予定）日
    private int totalAmount; // 総支払額
    private String insertFlg;
	public String getInsertFlg() {
		return insertFlg;
	}
	public void setInsertFlg(String insertFlg) {
		this.insertFlg = insertFlg;
	}
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
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}
}