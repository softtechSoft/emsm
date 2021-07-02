package com.softtech.entity;

public class WelfareInfo {
	 //福祉情報作成の区別用変数
	 private String gamenMode;
	 //社員ID
	 private String employeeID;
	 //社員氏名
	 private String employeeName;
	 //控除開始日
	 private String startDate;
	 //基本給
	 private int base;
	 //厚生年金控除個人
	 private int welfarePensionSelf;
	 //厚生年金控除会社
	 private int welfarePensionComp;
	 //厚生健康控除会社
	 private int welfareHealthComp;
	 //厚生健康控除個人
	 private int welfareHealthSelf;
	 //厚生控除子育(会社)
	 private int welfareBaby;
	 //雇用保険個人負担
	 private int eplyInsSelf;
	 //雇用保険会社負担
	 private int eplyInsComp;
	 //雇用保拠出金（会社)
	 private int eplyInsWithdraw;
	 //労災保険（会社負担のみ）
	 private int wkAcccpsIns;
	 //源泉控除
	 private int withholdingTax;
	 //住民税控除
	 private int municipalTax;
	 //社宅家賃控除
	 private int rental;
	 //社宅管理費控除
	 private int rentalMgmtFee;
	 //控除ステータス
	 private String status;
	 //作成日
	 private String insertDate;
	 //作成者
	 private String insertEmployee;
	 //更新日
	 private String updateDate;
	 //更新者
	 private String updateEmployee;
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
	 * @return startDate
	 */
	public String getStartDate() {
		return startDate;
	}
	/**
	 * @param startDate セットする startDate
	 */
	public void setStartDate(String startDate) {
		this.startDate = startDate;
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
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status セットする status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return insertDate
	 */
	public String getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate セットする insertDate
	 */
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	/**
	 * @return insertEmployee
	 */
	public String getInsertEmployee() {
		return insertEmployee;
	}
	/**
	 * @param insertEmployee セットする insertEmployee
	 */
	public void setInsertEmployee(String insertEmployee) {
		this.insertEmployee = insertEmployee;
	}
	/**
	 * @return updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate セットする updateDate
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
	/**
	 * @return updateEmployee
	 */
	public String getUpdateEmployee() {
		return updateEmployee;
	}
	/**
	 * @param updateEmployee セットする updateEmployee
	 */
	public void setUpdateEmployee(String updateEmployee) {
		this.updateEmployee = updateEmployee;
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

}
