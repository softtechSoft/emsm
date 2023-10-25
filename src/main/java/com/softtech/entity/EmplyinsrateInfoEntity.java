package com.softtech.entity;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-08
 * @return:
 */
public class EmplyinsrateInfoEntity {
    //雇用保険ID PKです。
    private String emplyinsrateID;
    //対象年度
    private String year;
    //雇用保険労働者負担料率‰
    private String laborBurdenRate;
    //雇用保険事業主負担料率‰
    private String employerBurdenRate;
    //労災保険料率(全額事業主)‰
    private String industrialAccidentInsuranceRate;
    //一般拠出金料率(全額事業主)‰
    private String contributionRate;
    //利用ステータス 0:未使用　1:使用中
    private String status;
    //作成日
    private String insertDate;
    //更新日
    private String updateDate;

    public String getEmplyinsrateID() {
        return emplyinsrateID;
    }

    public void setEmplyinsrateID(String emplyinsrateID) {
        this.emplyinsrateID = emplyinsrateID;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getLaborBurdenRate() {
        return laborBurdenRate;
    }

    public void setLaborBurdenRate(String laborBurdenRate) {
        this.laborBurdenRate = laborBurdenRate;
    }

    public String getEmployerBurdenRate() {
        return employerBurdenRate;
    }

    public void setEmployerBurdenRate(String employerBurdenRate) {
        this.employerBurdenRate = employerBurdenRate;
    }

    public String getIndustrialAccidentInsuranceRate() {
        return industrialAccidentInsuranceRate;
    }

    public void setIndustrialAccidentInsuranceRate(String industrialAccidentInsuranceRate) {
        this.industrialAccidentInsuranceRate = industrialAccidentInsuranceRate;
    }

    public String getContributionRate() {
        return contributionRate;
    }

    public void setContributionRate(String contributionRate) {
        this.contributionRate = contributionRate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
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

}
