package com.softtech.entity;

public class SalaryInfoEntity {
	private String employeeName ;
	private String employeeID ;
	private String month ;
	private String paymentDate ;
	private String base ;
	private String overTimePlus ;
	private String shortageReduce ;
	private String transportExpense;
	private String allowancePlus ;
	private String allowanceReduce ;
	private String allowanceReason ;
	private String welfarePensionSelf ;
	private String welfareHealthSelf ;
	private String eplyInsSelf ;
	private String withholdingTax ;
	private String municipalTax ;
	private String rental ;
	private String rentalMgmtFee ;
	private String sum ;
	private String remark ;
	private String totalFee ;
	private String wkAcccpsIns ;
	private String eplyInsWithdraw ;
	private String eplyInsComp ;
	private String welfareBaby ;
	private String welfarePensionComp ;
	private String welfareHealthComp ;
	private String overTime ;
	private String shortage ;
	private String address ;
	private String welfareSelf;
	private String welfareComp;

	private String specialAddition;
	private String specialReduce;


	private String year;
	private String deleteFlg;

	private String insertDate;
	private String updateDate;



	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeID() {
		return employeeID;
	}

	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = String.format( "%s/%s",month.substring(0, 4), month.substring(4, 6));
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = String.format("%s/%s/%s", paymentDate.substring(0, 4), paymentDate.substring(4, 6),
				paymentDate.substring(6, 8));
	}

	public String getBase() {
		return base;
	}

	public void setBase(String base) {
		this.base = String.format("%,d", Integer.parseInt(base));
	}

	public String getOverTimePlus() {
		return overTimePlus;
	}

	public void setOverTimePlus(String overTimePlus) {
		this.overTimePlus = String.format("%,d", Integer.parseInt(overTimePlus));
	}

	public String getShortageReduce() {
		return shortageReduce;
	}

	public void setShortageReduce(String shortageReduce) {
		this.shortageReduce = String.format("%,d", Integer.parseInt(shortageReduce));
	}

	public String getTransportExpense() {
		return transportExpense;
	}

	public void setTransportExpense(String transportExpense) {
		this.transportExpense = String.format("%,d", Integer.parseInt(transportExpense));
	}

	public String getAllowancePlus() {
		return allowancePlus;
	}

	public void setAllowancePlus(String allowancePlus) {
		this.allowancePlus = String.format("%,d", Integer.parseInt(allowancePlus));
	}

	public String getAllowanceReduce() {
		return allowanceReduce;
	}

	public void setAllowanceReduce(String allowanceReduce) {
		this.allowanceReduce = String.format("%,d", Integer.parseInt(allowanceReduce));
	}

	public String getAllowanceReason() {
		return allowanceReason;
	}

	public void setAllowanceReason(String allowanceReason) {
		this.allowanceReason = allowanceReason;
	}
	public String getWelfarePensionSelf() {
		return  welfarePensionSelf;
	}


	public void setWelfarePensionSelf(String welfarePensionSelf) {
		this.welfarePensionSelf = String.format("%,d", Integer.parseInt(welfarePensionSelf));
	}

	public String getWelfareHealthSelf() {
		return welfareHealthSelf;
	}

	public void setWelfareHealthSelf(String welfareHealthSelf) {
		this.welfareHealthSelf = String.format("%,d", Integer.parseInt(welfareHealthSelf));


	}

	public String getEplyInsSelf() {
		return eplyInsSelf;
	}

	public void setEplyInsSelf(String eplyInsSelf) {
		this.eplyInsSelf = String.format("%,d", Integer.parseInt(eplyInsSelf));
	}

	public String getWithholdingTax() {
		return withholdingTax;
	}

	public void setWithholdingTax(String withholdingTax) {
		this.withholdingTax = String.format("%,d", Integer.parseInt(withholdingTax));
	}

	public String getMunicipalTax() {
		return municipalTax;
	}

	public void setMunicipalTax(String municipalTax) {
		this.municipalTax = String.format("%,d", Integer.parseInt(municipalTax));
	}

	public String getRental() {
		return rental;
	}

	public void setRental(String rental) {
		this.rental = String.format("%,d", Integer.parseInt(rental));
	}

	public String getRentalMgmtFee() {
		return rentalMgmtFee;
	}

	public void setRentalMgmtFee(String rentalMgmtFee) {
		this.rentalMgmtFee = String.format("%,d", Integer.parseInt(rentalMgmtFee));
	}

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = String.format("%,d", Integer.parseInt(sum));
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getTotalFee() {
		return totalFee;
	}

	public void setTotalFee(String totalFee) {
		this.totalFee = String.format("%,d", Integer.parseInt(totalFee));
	}

	public String getWkAcccpsIns() {
		return wkAcccpsIns;
	}

	public void setWkAcccpsIns(String wkAcccpsIns) {
		this.wkAcccpsIns = String.format("%,d", Integer.parseInt(wkAcccpsIns));
	}

	public String getEplyInsWithdraw() {
		return eplyInsWithdraw;
	}

	public void setEplyInsWithdraw(String eplyInsWithdraw) {
		this.eplyInsWithdraw = String.format("%,d", Integer.parseInt(eplyInsWithdraw));
	}

	public String getEplyInsComp() {
		return eplyInsComp;
	}

	public void setEplyInsComp(String eplyInsComp) {
		this.eplyInsComp = String.format("%,d", Integer.parseInt(eplyInsComp));
	}

	public String getWelfareBaby() {
		return welfareBaby;
	}

	public void setWelfareBaby(String welfareBaby) {
		this.welfareBaby = String.format("%,d", Integer.parseInt(welfareBaby));
	}

	public String getWelfarePensionComp() {
		return welfarePensionComp;
	}

	public void setWelfarePensionComp(String welfarePensionComp) {
		this.welfarePensionComp = String.format("%,d", Integer.parseInt(welfarePensionComp));
	}
	public String getWelfareHealthComp() {
		return welfareHealthComp;
	}

	public void setWelfareHealthComp(String welfareHealthComp) {
		this.welfareHealthComp = String.format("%,d", Integer.parseInt(welfareHealthComp));
	}
	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	public String getShortage() {
		return shortage;
	}

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
	 * @return specialAddition
	 */
	public String getSpecialAddition() {
		return specialAddition;
	}

	/**
	 * @param specialAddition セットする specialAddition
	 */
	public void setSpecialAddition(String specialAddition) {
		this.specialAddition = specialAddition;
	}

	public String getSpecialReduce() {
		return specialReduce;
	}

	public void setSpecialReduce(String specialReduce) {
		this.specialReduce = specialReduce;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
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

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}



}