package com.softtech.actionForm;

import javax.validation.constraints.Size;

public class WelfareBean {
	 //福祉情報リストの区別用変数
	 private String wholeFlg;
	 //社員IDを押す時、社員IDを転送用
	 private String makeEmployeeID;
	 //福祉情報作成の区別用変数
	 private String gamenMode;
	 //社員ID
	 @Size(min=2, max=30,message="社員IDを入力してください。例：E001")
	 private String employeeID;
	 //社員氏名
	 private String employeeName;
	 //控除開始日
	 private String startDate;
	 //基本給
	 private String base;
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
	 //住民税控除一月
	 private String municipalTax1;
	//住民税控除二月
	 private String municipalTax2;
	//住民税控除三月
	 private String municipalTax3;
	//住民税控除四月
	 private String municipalTax4;
	//住民税控除五月
	 private String municipalTax5;
	//住民税控除六月
	 private String municipalTax6;
	//住民税控除七月
	 private String municipalTax7;
	//住民税控除八月
	 private String municipalTax8;
	//住民税控除九月
	 private String municipalTax9;
	//住民税控除十月
	 private String municipalTax10;
	//住民税控除十一月
	 private String municipalTax11;
	//住民税控除十二月
	 private String municipalTax12;
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
	/**
	 * @return makeEmployeeID
	 */
	public String getMakeEmployeeID() {
		return makeEmployeeID;
	}
	/**
	 * @param makeEmployeeID セットする makeEmployeeID
	 */
	public void setMakeEmployeeID(String makeEmployeeID) {
		this.makeEmployeeID = makeEmployeeID;
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
	 * @return municipalTax1
	 */
	public String getMunicipalTax1() {
		return municipalTax1;
	}
	/**
	 * @param municipalTax1 セットする municipalTax1
	 */
	public void setMunicipalTax1(String municipalTax1) {
		this.municipalTax1 = municipalTax1;
	}
	/**
	 * @return municipalTax2
	 */
	public String getMunicipalTax2() {
		return municipalTax2;
	}
	/**
	 * @param municipalTax2 セットする municipalTax2
	 */
	public void setMunicipalTax2(String municipalTax2) {
		this.municipalTax2 = municipalTax2;
	}
	/**
	 * @return municipalTax3
	 */
	public String getMunicipalTax3() {
		return municipalTax3;
	}
	/**
	 * @param municipalTax3 セットする municipalTax3
	 */
	public void setMunicipalTax3(String municipalTax3) {
		this.municipalTax3 = municipalTax3;
	}
	/**
	 * @return municipalTax4
	 */
	public String getMunicipalTax4() {
		return municipalTax4;
	}
	/**
	 * @param municipalTax4 セットする municipalTax4
	 */
	public void setMunicipalTax4(String municipalTax4) {
		this.municipalTax4 = municipalTax4;
	}
	/**
	 * @return municipalTax5
	 */
	public String getMunicipalTax5() {
		return municipalTax5;
	}
	/**
	 * @param municipalTax5 セットする municipalTax5
	 */
	public void setMunicipalTax5(String municipalTax5) {
		this.municipalTax5 = municipalTax5;
	}
	/**
	 * @return municipalTax6
	 */
	public String getMunicipalTax6() {
		return municipalTax6;
	}
	/**
	 * @param municipalTax6 セットする municipalTax6
	 */
	public void setMunicipalTax6(String municipalTax6) {
		this.municipalTax6 = municipalTax6;
	}
	/**
	 * @return municipalTax7
	 */
	public String getMunicipalTax7() {
		return municipalTax7;
	}
	/**
	 * @param municipalTax7 セットする municipalTax7
	 */
	public void setMunicipalTax7(String municipalTax7) {
		this.municipalTax7 = municipalTax7;
	}
	/**
	 * @return municipalTax8
	 */
	public String getMunicipalTax8() {
		return municipalTax8;
	}
	/**
	 * @param municipalTax8 セットする municipalTax8
	 */
	public void setMunicipalTax8(String municipalTax8) {
		this.municipalTax8 = municipalTax8;
	}
	/**
	 * @return municipalTax9
	 */
	public String getMunicipalTax9() {
		return municipalTax9;
	}
	/**
	 * @param municipalTax9 セットする municipalTax9
	 */
	public void setMunicipalTax9(String municipalTax9) {
		this.municipalTax9 = municipalTax9;
	}
	/**
	 * @return municipalTax10
	 */
	public String getMunicipalTax10() {
		return municipalTax10;
	}
	/**
	 * @param municipalTax10 セットする municipalTax10
	 */
	public void setMunicipalTax10(String municipalTax10) {
		this.municipalTax10 = municipalTax10;
	}
	/**
	 * @return municipalTax11
	 */
	public String getMunicipalTax11() {
		return municipalTax11;
	}
	/**
	 * @param municipalTax11 セットする municipalTax11
	 */
	public void setMunicipalTax11(String municipalTax11) {
		this.municipalTax11 = municipalTax11;
	}
	/**
	 * @return municipalTax12
	 */
	public String getMunicipalTax12() {
		return municipalTax12;
	}
	/**
	 * @param municipalTax12 セットする municipalTax12
	 */
	public void setMunicipalTax12(String municipalTax12) {
		this.municipalTax12 = municipalTax12;
	}

}
