package com.softtech.actionForm;

import javax.validation.constraints.Size;

/**
 * 概要：給料作成用Bean
 * 		画面とコントロール層とデータ転送
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/6/02
 */
public class SalaryInfoBean {
	//対象年月
	@Size(min=2, max=30,message="対象月を入力してください。例：202104")
	private String month ;
	//作成処理の設定用変数
	private String make;
	//画面入力可に設定用変数
	private String gamenMode;
	//社員氏名
	private String employeeName ;
	//社員氏ID
	private String employeeID ;
	//社員住所
	private String address;
	//支払日
	@Size(min=1, max=30,message="支払日を入力してください。例：2021/06/15")
	private String paymentDate ;
	//基本給
	@Size(min=1, max=30,message="基本給を入力してください。例：60000")
	private String base ;
	//超過増
	private String overTimePlus ;
	//不足減
	private String shortageReduce ;
	//残業時間
	private String overTime;
	//交通費
	private String transportExpense ;
	//不足時間
	private String shortage;
	//手当加算
	private String allowancePlus ;
	//手当減
	private String allowanceReduce;
	//手当理由
	private String allowanceReason ;

	//厚生控除個人
	private String welfarePensionSelf;
	//厚生健康控除個人
	private String welfareHealthSelf;
	//厚生年金控除会社
	private String welfarePensionComp;
	//厚生健康控除会社
	private String welfareHealthComp;

	//雇用保険個人負担
	private String eplyInsSelf;
	//厚生控除子育(会社）
	private String welfareBaby;
	//労災保険（会社負担のみ）
	private String wkAcccpsIns;
	//雇用保険会社負担
	private String eplyInsComp;
	//雇用保拠出金（会社)
	private String eplyInsWithdraw;
	//源泉控除
	private String withholdingTax;
	//住民税控除
	private String municipalTax;
	//社宅家賃控除
	private String rental;
	//社宅共益費控除
	private String rentalMgmtFee;
	//総額
	@Size(min=1, max=30,message="総額を入力してください。例：60000")
	private String sum;
	//備考
	private String remark;
	//総費用
	@Size(min=1, max=30,message="総費用を入力してください。例：60000")
	private String totalFee;

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
	 * @return make
	 */
	public String getMake() {
		return make;
	}

	/**
	 * @param make セットする make
	 */
	public void setMake(String make) {
		this.make = make;
	}

	/**
	 * @return gamenMode
	 */
	public String getGamenMode() {
		return gamenMode;
	}

	/**
	 * @param gamenMode セットする gamenMode
	 */
	public void setGamenMode(String gamenMode) {
		this.gamenMode = gamenMode;
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
}
