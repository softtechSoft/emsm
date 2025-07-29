package com.softtech.entity;

/**
 * @program @概要: @作成者:孫曄 @作成日:2022-05-24
 * @return:
 */
public class WelfarefeeInfoEntity {
  // 厚生保険料ID PKです。
  private String welfarefeeID;
  // 対象年度
  private String year;
  // 対象エリア 都道府県の名前
  private String area;
  // 計算用標準報酬
  private String standSalary;
  // 給料From >=
  private String salaryFrom;
  // 給料To   <
  private String salaryTo;
  // 介護必要ない料率
  private String notCareRatio;
  // 介護必要料率
  private String careRatio;
  // 厚生年金保険料率
  private String annuityRatio;
  //厚生控除子育(会社)の控除率（固定0.36%）
  private String babyCareCompanyRate;
  // 利用ステータス 0:未使用　1:使用中
  private int status;
  // 作成日
  private String insertDate;
  // 更新日
  private String updateDate;
  // 入力の収入
  private String WfPension;


  public String getWelfarefeeID() {
    return welfarefeeID;
  }

  public void setWelfarefeeID(String welfarefeeID) {
    this.welfarefeeID = welfarefeeID;
  }

  public String getYear() {
    return year;
  }

  public void setYear(String year) {
    this.year = year;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public String getStandSalary() {
    return standSalary;
  }

  public void setStandSalary(String standSalary) {
    this.standSalary = standSalary;
  }

  public String getSalaryFrom() {
    return salaryFrom;
  }

  public void setSalaryFrom(String salaryFrom) {
    this.salaryFrom = salaryFrom;
  }

  public String getSalaryTo() {
    return salaryTo;
  }

  public void setSalaryTo(String salaryTo) {
    this.salaryTo = salaryTo;
  }

  public String getNotCareRatio() {
    return notCareRatio;
  }

  public void setNotCareRatio(String notCareRatio) {
    this.notCareRatio = notCareRatio;
  }

  public String getCareRatio() {
    return careRatio;
  }

  public void setCareRatio(String careRatio) {
    this.careRatio = careRatio;
  }

  public String getAnnuityRatio() {
    return annuityRatio;
  }

  public void setAnnuityRatio(String annuityRatio) {
    this.annuityRatio = annuityRatio;
  }

  public int getStatus() {
    return status;
  }

  public void setStatus(int status) {
    this.status = status;
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

/**
 * @return wfPension
 */
public String getWfPension() {
	return WfPension;
}

/**
 * @param wfPension セットする wfPension
 */
public void setWfPension(String wfPension) {
	WfPension = wfPension;
}

/**
 * @return babyCareCompanyRate
 */
public String getBabyCareCompanyRate() {
	return babyCareCompanyRate;
}

/**
 * @param babyCareCompanyRate セットする babyCareCompanyRate
 */
public void setBabyCareCompanyRate(String babyCareCompanyRate) {
	this.babyCareCompanyRate = babyCareCompanyRate;
}




}
