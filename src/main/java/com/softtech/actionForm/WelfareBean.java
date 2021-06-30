package com.softtech.actionForm;

import javax.validation.constraints.Size;

public class WelfareBean {
	 //区別用変数
	 private String wholeFlg;
	 //社員ID
	 @Size(min=2, max=30,message="社員IDを入力してください。例：E001")
	 private String employeeID;
	 //社員氏名
	 private String employeeName;
	 //控除開始日
	 private String startDate;
	 //厚生年金控除個人
	 private String welfarePensionSelf;
	 //厚生年金控除会社
	 private String welfarePensionComp;
	 //厚生健康控除会社
	 private String welfareHealthComp;
	 //厚生健康控除個人
	 private String welfareHealthSelf;
	 //厚生控除子育(会社)
	 private String welfareBaby;
	 //雇用保険個人負担
	 private String eplyInsSelf;
	 //雇用保険会社負担
	 private String eplyInsComp;
	 //雇用保拠出金（会社)
	 private String eplyInsWithdraw;
	 //労災保険（会社負担のみ）
	 private String wkAcccpsIns;
	 //源泉控除
	 private String withholdingTax;
	 //住民税控除
	 private String municipalTax;
	 //社宅家賃控除
	 private String rental;
	 //社宅管理費控除
	 private String rentalMgmtFee;
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
	 * @return wholeFlg
	 */
	public String getWholeFlg() {
		return wholeFlg;
	}
	/**
	 * @param wholeFlg セットする wholeFlg
	 */
	public void setWholeFlg(String wholeFlg) {
		this.wholeFlg = wholeFlg;
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

}
