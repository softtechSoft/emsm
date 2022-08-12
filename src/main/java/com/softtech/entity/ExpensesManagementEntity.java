package com.softtech.entity;

public class ExpensesManagementEntity {
	private String employeeID;
	 //発生日
	private String accrualDate ;
	//担当者
	private String tantouName ;
	//金額
	private String cost ;
	//経費種別
	private String expensesType ;
	//場所
	private String happenAddress;
	//承認ステータス
	private String confirmStaus;
	//精算タイプ
	//private String stmtlType;
	//精算日
	private String stmtlDate ;
	//精算ステータス
	private String stmtlStaus;
	//備考
	private String remark;
    //経費種別明細
	private String expensesTypeDetail;
    //出金タイプ
	private String paymentType;

	public String getAccrualDate() {
		return accrualDate;
	}
	public void setAccrualDate(String accrualDate) {
		this.accrualDate = accrualDate;
	}
	public String getTantouName() {
		return tantouName;
	}
	public void setTantouName(String tantouName) {
		this.tantouName = tantouName;
	}
	public String getCost() {
		return cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getHappenAddress() {
		return happenAddress;
	}
	public void setHappenAddress(String happenAddress) {
		this.happenAddress = happenAddress;
	}

	/*public String getStmtlType() {
		return stmtlType;
	}
	public void setStmtlType(String stmtlType) {
		this.stmtlType = stmtlType;
	}*/
	public String getStmtlDate() {
		return stmtlDate;
	}
	public void setStmtlDate(String stmtlDate) {
		this.stmtlDate = stmtlDate;
	}

	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return employeeID
	 */
	public String getEmployeeID() {
		return employeeID;
	}
	/**
	 * @param employeeID セットする employeeID
	 */
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	/**
	 * @return confirmStaus
	 */
	public String getConfirmStaus() {
		return confirmStaus;
	}
	/**
	 * @param confirmStaus セットする confirmStaus
	 */
	public void setConfirmStaus(String confirmStaus) {
		this.confirmStaus = confirmStaus;
	}
	/**
	 * @return stmtlStaus
	 */
	public String getStmtlStaus() {
		return stmtlStaus;
	}
	/**
	 * @param stmtlStaus セットする stmtlStaus
	 */
	public void setStmtlStaus(String stmtlStaus) {
		this.stmtlStaus = stmtlStaus;
	}
	/**
	 * @return expensesType
	 */
	public String getExpensesType() {
		return expensesType;
	}
	/**
	 * @param expensesType セットする expensesType
	 */
	public void setExpensesType(String expensesType) {
		this.expensesType = expensesType;
	}
	/**
	 * @return expensesTypeDetail
	 */
	public String getExpensesTypeDetail() {
		return expensesTypeDetail;
	}
	/**
	 * @param expensesTypeDetail セットする expensesTypeDetail
	 */
	public void setExpensesTypeDetail(String expensesTypeDetail) {
		this.expensesTypeDetail = expensesTypeDetail;
	}
	/**
	 * @return paymentType
	 */
	public String getPaymentType() {
		return paymentType;
	}
	/**
	 * @param paymentType セットする paymentType
	 */
	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
	}


}
