package com.softtech.entity;

public class WelfareBabyInfoEntity {

	//徴収ID
	private String rateID;
	//対処年度
	private String year;
	//対処エリア
	private String area;
	//徴収率
	private String rate;
	//利用ステータス
	private int status;
	//作成日
	private String insertDate;
	//更新日
	private String updateDate;
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

}