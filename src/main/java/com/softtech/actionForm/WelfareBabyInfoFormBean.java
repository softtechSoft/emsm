package com.softtech.actionForm;

import java.util.ArrayList;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

import com.softtech.common.RateIDName;

public class WelfareBabyInfoFormBean {
// 厚生保険料ID PKです。
 private String rateID;
// 対象年度
// 年度と収入、２の１選択、@NotEmptyは必要ない
 @Pattern(message = "対象年度に数値のみを入力してください。", regexp = "^[0-9]*$")
 private String year;
// 対象エリア 都道府県の名前
 @Pattern(message = "対象エリアに漢字のみを入力してください。", regexp = "^[\\u4E00-\\u9FFF]+$")
 @NotEmpty(message = "対象エリアを入力してください。")
 private String area;
// 徴収率
 @NotEmpty(message = "徴収率を入力してください。")
 @Pattern(message = "徴収率に数値のみを入力してください。", regexp = "^[0-9]*$")
 private String rate;
// 利用ステータス 0:未使用　1:使用中
 private int status;
// 作成日
 private String insertDate;
// 更新日
 private String updateDate;
// 更新と新規区別用のFlg
 private String insertFlg;
// 更新画面への用 data伝送 PK rateIDのList
 private ArrayList<RateIDName> rateIDNameArrayList;
 public String getRateID() {
 return rateID;
 }
 public void setRateID(String rateID) {
  this.rateID = rateID;
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

 public String getRate() {
  return rate;
 }

 public void setRate(String rate) {
  this.rate = rate;
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

 public ArrayList<RateIDName> getRateIDNameArrayList() {
  return rateIDNameArrayList;
 }

 public void setRateIDNameArrayList(ArrayList<RateIDName> rateIDNameArrayList) {
  this.rateIDNameArrayList = rateIDNameArrayList;
 }
public void setInsertFlg(String insertFlg2) {
	// TODO 自動生成されたメソッド・スタブ

}

}

