package com.softtech.common;
/**
 * 概要：給料作成パラメータ用クラス
 *
 * 作成者：ソフトテク
 * 作成日：2021/5/15
 */
public class SalaryInfoRecord {
	private static final long serialVersionUID = 1L;
	private String employeeID;
	private String month;
	private String employeeName ;
	private String paymentDate ;
	private int base ;
	private int overTimePlus ;
	private int shortageReduce ;
	private int transportExpense;
	private int allowancePlus ;
	private int allowanceReduce;
	private String allowanceReason;
	private int welfarePensionSelf ;
	private int welfareHealthSelf ;
	private int eplyInsSelf ;
	private int withholdingTax ;
	private int municipalTax ;
	private int rental ;
	private int rentalMgmtFee ;
	private int sum ;
	private String remark ;
	private int totalFee ;
	private int wkAcccpsIns ;
	private int eplyInsWithdraw ;
	private int eplyInsComp ;
	private int welfareBaby ;
	private int welfarePensionComp ;
	private int welfareHealthComp ;
	private int overTime ;
	private int shortage ;
	private String address ;
	private int welfareSelf ;
	private int welfareComp ;
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
	public int getBase() {
		return base;
	}
	/**
	 * @param base セットする base
	 */
	public void setBase(int base) {
		this.base = base;
	}
	/**
	 * @return overTimePlus
	 */
	public int getOverTimePlus() {
		return overTimePlus;
	}
	/**
	 * @param overTimePlus セットする overTimePlus
	 */
	public void setOverTimePlus(int overTimePlus) {
		this.overTimePlus = overTimePlus;
	}
	/**
	 * @return shortageReduce
	 */
	public int getShortageReduce() {
		return shortageReduce;
	}
	/**
	 * @param shortageReduce セットする shortageReduce
	 */
	public void setShortageReduce(int shortageReduce) {
		this.shortageReduce = shortageReduce;
	}
	/**
	 * @return transportExpense
	 */
	public int getTransportExpense() {
		return transportExpense;
	}
	/**
	 * @param transportExpense セットする transportExpense
	 */
	public void setTransportExpense(int transportExpense) {
		this.transportExpense = transportExpense;
	}
	/**
	 * @return allowancePlus
	 */
	public int getAllowancePlus() {
		return allowancePlus;
	}
	/**
	 * @param allowancePlus セットする allowancePlus
	 */
	public void setAllowancePlus(int allowancePlus) {
		this.allowancePlus = allowancePlus;
	}
	/**
	 * @return allowanceReduce
	 */
	public int getAllowanceReduce() {
		return allowanceReduce;
	}
	/**
	 * @param allowanceReduce セットする allowanceReduce
	 */
	public void setAllowanceReduce(int allowanceReduce) {
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
	public int getWelfarePensionSelf() {
		return welfarePensionSelf;
	}
	/**
	 * @param welfarePensionSelf セットする welfarePensionSelf
	 */
	public void setWelfarePensionSelf(int welfarePensionSelf) {
		this.welfarePensionSelf = welfarePensionSelf;
	}
	/**
	 * @return welfareHealthSelf
	 */
	public int getWelfareHealthSelf() {
		return welfareHealthSelf;
	}
	/**
	 * @param welfareHealthSelf セットする welfareHealthSelf
	 */
	public void setWelfareHealthSelf(int welfareHealthSelf) {
		this.welfareHealthSelf = welfareHealthSelf;
	}
	/**
	 * @return eplyInsSelf
	 */
	public int getEplyInsSelf() {
		return eplyInsSelf;
	}
	/**
	 * @param eplyInsSelf セットする eplyInsSelf
	 */
	public void setEplyInsSelf(int eplyInsSelf) {
		this.eplyInsSelf = eplyInsSelf;
	}
	/**
	 * @return withholdingTax
	 */
	public int getWithholdingTax() {
		return withholdingTax;
	}
	/**
	 * @param withholdingTax セットする withholdingTax
	 */
	public void setWithholdingTax(int withholdingTax) {
		this.withholdingTax = withholdingTax;
	}
	/**
	 * @return municipalTax
	 */
	public int getMunicipalTax() {
		return municipalTax;
	}
	/**
	 * @param municipalTax セットする municipalTax
	 */
	public void setMunicipalTax(int municipalTax) {
		this.municipalTax = municipalTax;
	}
	/**
	 * @return rental
	 */
	public int getRental() {
		return rental;
	}
	/**
	 * @param rental セットする rental
	 */
	public void setRental(int rental) {
		this.rental = rental;
	}
	/**
	 * @return rentalMgmtFee
	 */
	public int getRentalMgmtFee() {
		return rentalMgmtFee;
	}
	/**
	 * @param rentalMgmtFee セットする rentalMgmtFee
	 */
	public void setRentalMgmtFee(int rentalMgmtFee) {
		this.rentalMgmtFee = rentalMgmtFee;
	}
	/**
	 * @return sum
	 */
	public int getSum() {
		return sum;
	}
	/**
	 * @param sum セットする sum
	 */
	public void setSum(int sum) {
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
	public int getTotalFee() {
		return totalFee;
	}
	/**
	 * @param totalFee セットする totalFee
	 */
	public void setTotalFee(int totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * @return wkAcccpsIns
	 */
	public int getWkAcccpsIns() {
		return wkAcccpsIns;
	}
	/**
	 * @param wkAcccpsIns セットする wkAcccpsIns
	 */
	public void setWkAcccpsIns(int wkAcccpsIns) {
		this.wkAcccpsIns = wkAcccpsIns;
	}
	/**
	 * @return eplyInsWithdraw
	 */
	public int getEplyInsWithdraw() {
		return eplyInsWithdraw;
	}
	/**
	 * @param eplyInsWithdraw セットする eplyInsWithdraw
	 */
	public void setEplyInsWithdraw(int eplyInsWithdraw) {
		this.eplyInsWithdraw = eplyInsWithdraw;
	}
	/**
	 * @return eplyInsComp
	 */
	public int getEplyInsComp() {
		return eplyInsComp;
	}
	/**
	 * @param eplyInsComp セットする eplyInsComp
	 */
	public void setEplyInsComp(int eplyInsComp) {
		this.eplyInsComp = eplyInsComp;
	}
	/**
	 * @return welfareBaby
	 */
	public int getWelfareBaby() {
		return welfareBaby;
	}
	/**
	 * @param welfareBaby セットする welfareBaby
	 */
	public void setWelfareBaby(int welfareBaby) {
		this.welfareBaby = welfareBaby;
	}
	/**
	 * @return welfarePensionComp
	 */
	public int getWelfarePensionComp() {
		return welfarePensionComp;
	}
	/**
	 * @param welfarePensionComp セットする welfarePensionComp
	 */
	public void setWelfarePensionComp(int welfarePensionComp) {
		this.welfarePensionComp = welfarePensionComp;
	}
	/**
	 * @return welfareHealthComp
	 */
	public int getWelfareHealthComp() {
		return welfareHealthComp;
	}
	/**
	 * @param welfareHealthComp セットする welfareHealthComp
	 */
	public void setWelfareHealthComp(int welfareHealthComp) {
		this.welfareHealthComp = welfareHealthComp;
	}
	/**
	 * @return overTime
	 */
	public int getOverTime() {
		return overTime;
	}
	/**
	 * @param overTime セットする overTime
	 */
	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}
	/**
	 * @return shortage
	 */
	public int getShortage() {
		return shortage;
	}
	/**
	 * @param shortage セットする shortage
	 */
	public void setShortage(int shortage) {
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
	public int getWelfareSelf() {
		return welfareSelf;
	}
	/**
	 * @param welfareSelf セットする welfareSelf
	 */
	public void setWelfareSelf(int welfareSelf) {
		this.welfareSelf = welfareSelf;
	}
	/**
	 * @return welfareComp
	 */
	public int getWelfareComp() {
		return welfareComp;
	}
	/**
	 * @param welfareComp セットする welfareComp
	 */
	public void setWelfareComp(int welfareComp) {
		this.welfareComp = welfareComp;
	}

}
