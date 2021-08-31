package com.softtech.actionForm;

import javax.validation.constraints.Size;

public class ExpensesBean {
	//発生日
	@Size(min=1, max=30,message="発生日を入力してください。")
	private String accrualDate ;
	//金額
	@Size(min=1, max=30,message="金額を入力してください。")
	private String cost ;
	//担当者
	@Size(min=1, max=30,message="担当者を入力してください。")
	private String personel ;
	//承認
	@Size(min=1, max=30,message="承認を入力してください。")
	private String confirmStaus ;
	//承認日
	private String confirmDate ;
	//承認者
	private String confirmer ;
	//精算
	@Size(min=1, max=30,message="精算を入力してください。")
	private String stmtlStaus ;
	//精算日
	private String stmtlDate ;
	//出金タイプ
	@Size(min=1, max=30,message="出金タイプを入力してください。")
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
