package com.softtech.entity;

public class ExpensesEntity {
	//発生日
	private String accrualDate ;
	//金額
	private String cost ;
	//担当者
	private String personel ;
	//承認
	private String confirmStaus ;
	//承認日
	private String confirmDate ;
	//承認者
	private String confirmer ;
	//精算
	private String stmtlStaus ;
	//精算日
	private String stmtlDate ;
	//出金タイプ
	private String paymentType ;
	/**
	 * @return accrualDate
	 */
	public String getAccrualDate() {
		return accrualDate;
	}
	/**
	 * @param accrualDate セットする accrualDate
	 */
	public void setAccrualDate(String accrualDate) {
		this.accrualDate = accrualDate;
	}
	/**
	 * @return cost
	 */
	public String getCost() {
		return cost;
	}
	/**
	 * @param cost セットする cost
	 */
	public void setCost(String cost) {
		this.cost = cost;
	}
	/**
	 * @return personel
	 */
	public String getPersonel() {
		return personel;
	}
	/**
	 * @param personel セットする personel
	 */
	public void setPersonel(String personel) {
		this.personel = personel;
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
	 * @return confirmDate
	 */
	public String getConfirmDate() {
		return confirmDate;
	}
	/**
	 * @param confirmDate セットする confirmDate
	 */
	public void setConfirmDate(String confirmDate) {
		this.confirmDate = confirmDate;
	}
	/**
	 * @return confirmer
	 */
	public String getConfirmer() {
		return confirmer;
	}
	/**
	 * @param confirmer セットする confirmer
	 */
	public void setConfirmer(String confirmer) {
		this.confirmer = confirmer;
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
	 * @return stmtlDate
	 */
	public String getStmtlDate() {
		return stmtlDate;
	}
	/**
	 * @param stmtlDate セットする stmtlDate
	 */
	public void setStmtlDate(String stmtlDate) {
		this.stmtlDate = stmtlDate;
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
