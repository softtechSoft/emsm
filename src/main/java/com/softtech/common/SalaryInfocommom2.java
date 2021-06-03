package com.softtech.common;
/**
 * 概要：給料作成パラメータ用クラス
 *
 * 作成者：ソフトテク
 * 作成日：2021/5/15
 */
public class SalaryInfocommom2 {
	private static final long serialVersionUID = 1L;
	private String employeeID;
	private String month;
	private String employeeName = "0";
	private String paymentDate = "0";
	private String base = "0";
	private String overTimePlus = "0";
	private String shortageReduce = "0";
	private String transportExpense = "0";
	private String allowancePlus = "0";
	private String allowanceReduce = "0";
	private String allowanceReason = " ";
	private String welfarePensionSelf = "0";
	private String welfareHealthSelf = "0";
	private String eplyInsSelf = "0";
	private String withholdingTax = "0";
	private String municipalTax = "0";
	private String rental = "0";
	private String rentalMgmtFee = "0";
	private String sum = "0";
	private String remark = " ";
	private String totalFee = "0";
	private String wkAcccpsIns = "0";
	private String eplyInsWithdraw = "0";
	private String eplyInsComp = "0";
	private String welfareBaby = "0";
	private String welfarePensionComp = "0";
	private String welfareHealthComp = "0";
	private String overTime = "0";
	private String shortage = "0";
	private String address = "0";
	private String welfareSelf = "0";
	private String welfareComp = "0";
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
	 * @return month
	 */
	public String getMonth() {
		return month;
	}
	/**
	 * @param month セットする month
	 */
	public void setMonth(String month) {
		this.month = month;
	}
	/**
	 * @return employeeName
	 */
	public String getEmployeeName() {
		return employeeName;
	}
	/**
	 * @param employeeName セットする employeeName
	 */
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	/**
	 * @return paymentDate
	 */
	public String getPaymentDate() {
		return paymentDate;
	}
	/**
	 * @param paymentDate セットする paymentDate
	 */
	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}
	/**
	 * @return base
	 */
	public String getBase() {
		return base;
	}
	/**
	 * @param base セットする base
	 */
	public void setBase(String base) {
		this.base = base;
	}
	/**
	 * @return overTimePlus
	 */
	public String getOverTimePlus() {
		return overTimePlus;
	}
	/**
	 * @param overTimePlus セットする overTimePlus
	 */
	public void setOverTimePlus(String overTimePlus) {
		this.overTimePlus = overTimePlus;
	}
	/**
	 * @return shortageReduce
	 */
	public String getShortageReduce() {
		return shortageReduce;
	}
	/**
	 * @param shortageReduce セットする shortageReduce
	 */
	public void setShortageReduce(String shortageReduce) {
		this.shortageReduce = shortageReduce;
	}
	/**
	 * @return transportExpense
	 */
	public String getTransportExpense() {
		return transportExpense;
	}
	/**
	 * @param transportExpense セットする transportExpense
	 */
	public void setTransportExpense(String transportExpense) {
		this.transportExpense = transportExpense;
	}
	/**
	 * @return allowancePlus
	 */
	public String getAllowancePlus() {
		return allowancePlus;
	}
	/**
	 * @param allowancePlus セットする allowancePlus
	 */
	public void setAllowancePlus(String allowancePlus) {
		this.allowancePlus = allowancePlus;
	}
	/**
	 * @return allowanceReduce
	 */
	public String getAllowanceReduce() {
		return allowanceReduce;
	}
	/**
	 * @param allowanceReduce セットする allowanceReduce
	 */
	public void setAllowanceReduce(String allowanceReduce) {
		this.allowanceReduce = allowanceReduce;
	}
	/**
	 * @return allowanceReason
	 */
	public String getAllowanceReason() {
		return allowanceReason;
	}
	/**
	 * @param allowanceReason セットする allowanceReason
	 */
	public void setAllowanceReason(String allowanceReason) {
		this.allowanceReason = allowanceReason;
	}
	/**
	 * @return welfarePensionSelf
	 */
	public String getWelfarePensionSelf() {
		return welfarePensionSelf;
	}
	/**
	 * @param welfarePensionSelf セットする welfarePensionSelf
	 */
	public void setWelfarePensionSelf(String welfarePensionSelf) {
		this.welfarePensionSelf = welfarePensionSelf;
	}
	/**
	 * @return welfareHealthSelf
	 */
	public String getWelfareHealthSelf() {
		return welfareHealthSelf;
	}
	/**
	 * @param welfareHealthSelf セットする welfareHealthSelf
	 */
	public void setWelfareHealthSelf(String welfareHealthSelf) {
		this.welfareHealthSelf = welfareHealthSelf;
	}
	/**
	 * @return eplyInsSelf
	 */
	public String getEplyInsSelf() {
		return eplyInsSelf;
	}
	/**
	 * @param eplyInsSelf セットする eplyInsSelf
	 */
	public void setEplyInsSelf(String eplyInsSelf) {
		this.eplyInsSelf = eplyInsSelf;
	}
	/**
	 * @return withholdingTax
	 */
	public String getWithholdingTax() {
		return withholdingTax;
	}
	/**
	 * @param withholdingTax セットする withholdingTax
	 */
	public void setWithholdingTax(String withholdingTax) {
		this.withholdingTax = withholdingTax;
	}
	/**
	 * @return municipalTax
	 */
	public String getMunicipalTax() {
		return municipalTax;
	}
	/**
	 * @param municipalTax セットする municipalTax
	 */
	public void setMunicipalTax(String municipalTax) {
		this.municipalTax = municipalTax;
	}
	/**
	 * @return rental
	 */
	public String getRental() {
		return rental;
	}
	/**
	 * @param rental セットする rental
	 */
	public void setRental(String rental) {
		this.rental = rental;
	}
	/**
	 * @return rentalMgmtFee
	 */
	public String getRentalMgmtFee() {
		return rentalMgmtFee;
	}
	/**
	 * @param rentalMgmtFee セットする rentalMgmtFee
	 */
	public void setRentalMgmtFee(String rentalMgmtFee) {
		this.rentalMgmtFee = rentalMgmtFee;
	}
	/**
	 * @return sum
	 */
	public String getSum() {
		return sum;
	}
	/**
	 * @param sum セットする sum
	 */
	public void setSum(String sum) {
		this.sum = sum;
	}
	/**
	 * @return remark
	 */
	public String getRemark() {
		return remark;
	}
	/**
	 * @param remark セットする remark
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * @return totalFee
	 */
	public String getTotalFee() {
		return totalFee;
	}
	/**
	 * @param totalFee セットする totalFee
	 */
	public void setTotalFee(String totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * @return wkAcccpsIns
	 */
	public String getWkAcccpsIns() {
		return wkAcccpsIns;
	}
	/**
	 * @param wkAcccpsIns セットする wkAcccpsIns
	 */
	public void setWkAcccpsIns(String wkAcccpsIns) {
		this.wkAcccpsIns = wkAcccpsIns;
	}
	/**
	 * @return eplyInsWithdraw
	 */
	public String getEplyInsWithdraw() {
		return eplyInsWithdraw;
	}
	/**
	 * @param eplyInsWithdraw セットする eplyInsWithdraw
	 */
	public void setEplyInsWithdraw(String eplyInsWithdraw) {
		this.eplyInsWithdraw = eplyInsWithdraw;
	}
	/**
	 * @return eplyInsComp
	 */
	public String getEplyInsComp() {
		return eplyInsComp;
	}
	/**
	 * @param eplyInsComp セットする eplyInsComp
	 */
	public void setEplyInsComp(String eplyInsComp) {
		this.eplyInsComp = eplyInsComp;
	}
	/**
	 * @return welfareBaby
	 */
	public String getWelfareBaby() {
		return welfareBaby;
	}
	/**
	 * @param welfareBaby セットする welfareBaby
	 */
	public void setWelfareBaby(String welfareBaby) {
		this.welfareBaby = welfareBaby;
	}
	/**
	 * @return welfarePensionComp
	 */
	public String getWelfarePensionComp() {
		return welfarePensionComp;
	}
	/**
	 * @param welfarePensionComp セットする welfarePensionComp
	 */
	public void setWelfarePensionComp(String welfarePensionComp) {
		this.welfarePensionComp = welfarePensionComp;
	}
	/**
	 * @return welfareHealthComp
	 */
	public String getWelfareHealthComp() {
		return welfareHealthComp;
	}
	/**
	 * @param welfareHealthComp セットする welfareHealthComp
	 */
	public void setWelfareHealthComp(String welfareHealthComp) {
		this.welfareHealthComp = welfareHealthComp;
	}
	/**
	 * @return overTime
	 */
	public String getOverTime() {
		return overTime;
	}
	/**
	 * @param overTime セットする overTime
	 */
	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}
	/**
	 * @return shortage
	 */
	public String getShortage() {
		return shortage;
	}
	/**
	 * @param shortage セットする shortage
	 */
	public void setShortage(String shortage) {
		this.shortage = shortage;
	}
	/**
	 * @return address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address セットする address
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return welfareSelf
	 */
	public String getWelfareSelf() {
		return welfareSelf;
	}
	/**
	 * @param welfareSelf セットする welfareSelf
	 */
	public void setWelfareSelf(String welfareSelf) {
		this.welfareSelf = welfareSelf;
	}
	/**
	 * @return welfareComp
	 */
	public String getWelfareComp() {
		return welfareComp;
	}
	/**
	 * @param welfareComp セットする welfareComp
	 */
	public void setWelfareComp(String welfareComp) {
		this.welfareComp = welfareComp;
	}
	/**
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
