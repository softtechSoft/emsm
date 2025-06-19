package com.softtech.actionForm;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.softtech.common.WelfarefeeIDName;

import lombok.Data;

/**
 * @program @概要: @作成者:孫曄 @作成日:2022-05-24
 * @return:
 */
@Data
public class WelfarefeeInfoFormBean {
  // 厚生保険料ID PKです。
  private String welfarefeeID;
  // 対象年度
  // 年度と収入、２の１選択、@NotEmptyは必要ない
  @Pattern(message = "対象年度に数値のみを入力してください。", regexp = "^[0-9]{4}$")
  private String year;
  // 対象エリア 都道府県の名前
  @Pattern(message = "対象エリアに漢字のみを入力してください。", regexp = "^[\\u4E00-\\u9FFF]+$")
  @NotEmpty(message = "対象エリアを入力してください。")
  private String area;
  // 計算用標準報酬
  @NotEmpty(message = "計算用標準報酬を入力してください。")
  @Pattern(message = "計算用標準報酬に数値のみを入力してください。", regexp = "^[0-9]*$")
  private String standSalary;
  // 給料From
  @NotEmpty(message = "給料Fromを入力してください。")
  @Pattern(message = "給料Fromに数値のみを入力してください。", regexp = "^[0-9]*$")
  private String salaryFrom;
  // 給料To
  @NotEmpty(message = "給料Toを入力してください。")
  @Pattern(message = "給料Toに数値のみを入力してください。", regexp = "^[0-9]*$")
  private String salaryTo;
  // 介護必要ない料率
  @NotEmpty(message = "介護必要ない料率を入力してください。")
  @Pattern(message = "介護必要ない料率に小数点3位までの数字のみを入力してください。", regexp = "^[0-9]+(.[0-9]{1,3})?$")
  private String notCareRatio;
  // 介護必要料率
  @NotEmpty(message = "介護必要料率を入力してください。")
  @Pattern(message = "介護必要料率に小数点3位までの数字のみを入力してください。", regexp = "^[0-9]+(.[0-9]{1,3})?$")
  private String careRatio;
  // 厚生年金保険料率
  @NotEmpty(message = "厚生年金保険料率を入力してください。")
  @Pattern(message = "厚生年金保険料率小数点3位までの数字のみを入力してください。", regexp = "^[0-9]+(.[0-9]{1,3})?$")
  private String annuityRatio;

  // 利用ステータス 0:未使用　1:使用中
  private int status;
  // 作成日
  private String insertDate;
  // 更新日
  private String updateDate;
  // 更新と新規区別用のFlg
  private String insertFlg;
  // 更新画面への用 data伝送 PK welfarefeeIDのList
  private ArrayList<WelfarefeeIDName> welfarefeeIDNameArrayList;
  // 入力の収入
  // 年度と収入、２の１選択、、@NotEmptyは必要ない
 // @Pattern(message = "収入に整数のみを入力してください。", regexp = "^-[1-9]*$")
 // private String enterSalary;

	/*public String getWelfarefeeID() {
	return welfarefeeID;
	}*/

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

  public String getInsertFlg() {
    return insertFlg;
  }

  public void setInsertFlg(String insertFlg) {
    this.insertFlg = insertFlg;
  }

  public ArrayList<WelfarefeeIDName> getWelfarefeeIDNameArrayList() {
    return welfarefeeIDNameArrayList;
  }

  public void setWelfarefeeIDNameArrayList(ArrayList<WelfarefeeIDName> welfarefeeIDNameArrayList) {
    this.welfarefeeIDNameArrayList = welfarefeeIDNameArrayList;
  }

  @Override
	public String toString() {
	    return "WelfarefeeInfoFormBean{" +
	            "welfarefeeID='" + welfarefeeID +
	            ", year='" + year + '\'' +
	            ", area='" + area + '\'' +
	            ", standSalary='" + standSalary + '\'' +
	            ", salaryFrom='" + salaryFrom + '\'' +
	            ", salaryTo='" + salaryTo + '\'' +
	            ", notCareRatio='" + notCareRatio + '\'' +
	            ", careRatio='" + careRatio + '\'' +
	            ", annuityRatio='" + annuityRatio + '\'' +
	            ", status='" + status + '\'' +
	            ", insertDate='" + insertDate + '\'' +
	            ", updateDate='" + updateDate + '\'' +
	            ", insertFlg='" + insertFlg + '\'' +
	            ", welfarefeeIDNameArrayList=" + welfarefeeIDNameArrayList +
	            '}';
  }

}
