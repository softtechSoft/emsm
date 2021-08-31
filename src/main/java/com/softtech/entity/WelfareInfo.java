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
	 //住民税控除一月
	 private int municipalTax1;
	//住民税控除二月
	 private int municipalTax2;
	//住民税控除三月
	 private int municipalTax3;
	//住民税控除四月
	 private int municipalTax4;
	//住民税控除五月
	 private int municipalTax5;
	//住民税控除六月
	 private int municipalTax6;
	//住民税控除七月
	 private int municipalTax7;
	//住民税控除八月
	 private int municipalTax8;
	//住民税控除九月
	 private int municipalTax9;
	//住民税控除十月
	 private int municipalTax10;
	//住民税控除十一月
	 private int municipalTax11;
	//住民税控除十二月
	 private int municipalTax12;
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
	/**
	 * @return municipalTax1
	 */
	public int getMunicipalTax1() {
		return municipalTax1;
	}
	/**
	 * @param municipalTax1 セットする municipalTax1
	 */
	public void setMunicipalTax1(int municipalTax1) {
		this.municipalTax1 = municipalTax1;
	}
	/**
	 * @return municipalTax2
	 */
	public int getMunicipalTax2() {
		return municipalTax2;
	}
	/**
	 * @param municipalTax2 セットする municipalTax2
	 */
	public void setMunicipalTax2(int municipalTax2) {
		this.municipalTax2 = municipalTax2;
	}
	/**
	 * @return municipalTax3
	 */
	public int getMunicipalTax3() {
		return municipalTax3;
	}
	/**
	 * @param municipalTax3 セットする municipalTax3
	 */
	public void setMunicipalTax3(int municipalTax3) {
		this.municipalTax3 = municipalTax3;
	}
	/**
	 * @return municipalTax4
	 */
	public int getMunicipalTax4() {
		return municipalTax4;
	}
	/**
	 * @param municipalTax4 セットする municipalTax4
	 */
	public void setMunicipalTax4(int municipalTax4) {
		this.municipalTax4 = municipalTax4;
	}
	/**
	 * @return municipalTax5
	 */
	public int getMunicipalTax5() {
		return municipalTax5;
	}
	/**
	 * @param municipalTax5 セットする municipalTax5
	 */
	public void setMunicipalTax5(int municipalTax5) {
		this.municipalTax5 = municipalTax5;
	}
	/**
	 * @return municipalTax6
	 */
	public int getMunicipalTax6() {
		return municipalTax6;
	}
	/**
	 * @param municipalTax6 セットする municipalTax6
	 */
	public void setMunicipalTax6(int municipalTax6) {
		this.municipalTax6 = municipalTax6;
	}
	/**
	 * @return municipalTax7
	 */
	public int getMunicipalTax7() {
		return municipalTax7;
	}
	/**
	 * @param municipalTax7 セットする municipalTax7
	 */
	public void setMunicipalTax7(int municipalTax7) {
		this.municipalTax7 = municipalTax7;
	}
	/**
	 * @return municipalTax8
	 */
	public int getMunicipalTax8() {
		return municipalTax8;
	}
	/**
	 * @param municipalTax8 セットする municipalTax8
	 */
	public void setMunicipalTax8(int municipalTax8) {
		this.municipalTax8 = municipalTax8;
	}
	/**
	 * @return municipalTax9
	 */
	public int getMunicipalTax9() {
		return municipalTax9;
	}
	/**
	 * @param municipalTax9 セットする municipalTax9
	 */
	public void setMunicipalTax9(int municipalTax9) {
		this.municipalTax9 = municipalTax9;
	}
	/**
	 * @return municipalTax10
	 */
	public int getMunicipalTax10() {
		return municipalTax10;
	}
	/**
	 * @param municipalTax10 セットする municipalTax10
	 */
	public void setMunicipalTax10(int municipalTax10) {
		this.municipalTax10 = municipalTax10;
	}
	/**
	 * @return municipalTax11
	 */
	public int getMunicipalTax11() {
		return municipalTax11;
	}
	/**
	 * @param municipalTax11 セットする municipalTax11
	 */
	public void setMunicipalTax11(int municipalTax11) {
		this.municipalTax11 = municipalTax11;
	}
	/**
	 * @return municipalTax12
	 */
	public int getMunicipalTax12() {
		return municipalTax12;
	}
	/**
	 * @param municipalTax12 セットする municipalTax12
	 */
	public void setMunicipalTax12(int municipalTax12) {
		this.municipalTax12 = municipalTax12;
	}

}
