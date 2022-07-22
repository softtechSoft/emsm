package com.softtech.entity;

public class ExpensesManagementEntity {
	 //発生日
	private String accrualDate ;
	//担当者
	private String tantouName ;
	//金額
	private String cost ;
	//経費種別
	private String expensesType ;
	//場所
	private String happenAdderss;
	//承認ステータス
	private String confirmStatus;
	//精算タイプ
	private String stmtlType;
	//精算日
	private String stmtlDate ;
	//精算ステータス
	private String stmtlStatus;
	//備考
	private String remark;

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
	public String getExpensesType() {
		return expensesType;
	}
	public void setExpensesType(String expensesType) {
		this.expensesType = expensesType;
	}
	public String getHappenAdderss() {
		return happenAdderss;
	}
	public void setHappenAdderss(String happenAdderss) {
		this.happenAdderss = happenAdderss;
	}
	public String getConfirmStatus() {
		return confirmStatus;
	}
	public void setConfirmStatus(String confirmStatus) {
		this.confirmStatus = confirmStatus;
	}
	public String getStmtlType() {
		return stmtlType;
	}
	public void setStmtlType(String stmtlType) {
		this.stmtlType = stmtlType;
	}
	public String getStmtlDate() {
		return stmtlDate;
	}
	public void setStmtlDate(String stmtlDate) {
		this.stmtlDate = stmtlDate;
	}
	public String getStmtlStatus() {
		return stmtlStatus;
	}
	public void setStmtlStatus(String stmtlStatus) {
		this.stmtlStatus = stmtlStatus;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}


}
