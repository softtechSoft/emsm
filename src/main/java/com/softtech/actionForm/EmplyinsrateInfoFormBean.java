package com.softtech.actionForm;

import com.softtech.common.EmplyinsrateIDName;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-08
 * @return:
 */
public class EmplyinsrateInfoFormBean {
    private String emplyinsrateID;
    //対象年度
    @NotEmpty(message = "年度を入力してください")
    @Pattern(message = "対象年度に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String year;
    //雇用保険労働者負担料率‰
    @NotEmpty(message = "雇用保険労働者負担料率‰を入力してください")
    @Pattern(message = "雇用保険労働者負担料率‰に小数点2位までの数字のみを入力してください。", regexp = "^[0-9]+(.[0-9]{1,2})?$")
    private String laborBurdenRate;
    //雇用保険事業主負担料率‰
    @NotEmpty(message = "雇用保険事業主負担料率‰を入力してください")
    @Pattern(message = "雇用保険事業主負担料率‰に小数点2位までの数字のみを入力してください。", regexp = "^[0-9]+(.[0-9]{1,2})?$")
    private String employerBurdenRate;
    //雇用保険料率‰
    private String employmentInsuranceRate;
    //労災保険料率(全額事業主)‰
    @NotEmpty(message = "労災保険料率(全額事業主)‰を入力してください")
    @Pattern(message = "労災保険料率(全額事業主)‰に小数点2位までの数字のみを入力してください。", regexp = "^[0-9]+(.[0-9]{1,2})?$")
    private String industrialAccidentInsuranceRate;
    //労働保険料率‰
    private String laborInsuranceRate;
    //一般拠出金料率(全額事業主)‰
    @NotEmpty(message = "一般拠出金料率(全額事業主)‰を入力してください")
    @Pattern(message = "一般拠出金料率(全額事業主)‰に小数点2位までの数字のみを入力してください。", regexp = "^[0-9]+(.[0-9]{1,2})?$")
    private String contributionRate;
    //利用ステータス 0:未使用　1:使用中
    private String status;
    //作成日
    private String insertDate;
    //更新日
    private String updateDate;
    //更新と新規区別用のFlg
    private String insertFlg;
    //更新画面への用 data伝送 PK welfarefeeIDのList
    private ArrayList<EmplyinsrateIDName> emplyinsrateIDNameList;

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

    public String getEmploymentInsuranceRate() {
        return employmentInsuranceRate;
    }

    public void setEmploymentInsuranceRate(String employmentInsuranceRate) {
        this.employmentInsuranceRate = employmentInsuranceRate;
    }

    public String getIndustrialAccidentInsuranceRate() {
        return industrialAccidentInsuranceRate;
    }

    public void setIndustrialAccidentInsuranceRate(String industrialAccidentInsuranceRate) {
        this.industrialAccidentInsuranceRate = industrialAccidentInsuranceRate;
    }

    public String getLaborInsuranceRate() {
        return laborInsuranceRate;
    }

    public void setLaborInsuranceRate(String laborInsuranceRate) {
        this.laborInsuranceRate = laborInsuranceRate;
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

    public String getInsertFlg() {
        return insertFlg;
    }

    public void setInsertFlg(String insertFlg) {
        this.insertFlg = insertFlg;
    }

    public ArrayList<EmplyinsrateIDName> getEmplyinsrateIDNameList() {
        return emplyinsrateIDNameList;
    }

    public void setEmplyinsrateIDNameList(ArrayList<EmplyinsrateIDName> emplyinsrateIDNameList) {
        this.emplyinsrateIDNameList = emplyinsrateIDNameList;
    }
}
