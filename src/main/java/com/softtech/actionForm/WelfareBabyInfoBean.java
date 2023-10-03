package com.softtech.actionForm;

public class WelfareBabyInfoBean {
	 // 厚生保険料ID PKです。
	  private String rateID;
	  // 対象年度
	  private String year;
	  // 対象エリア 都道府県の名前
	  private String area;
	  // 徴収率
	  private String rate;
	// 利用ステータス 0:未使用　1:使用中
	  private int status;
	  // 作成日
	  private String insertDate;
	  // 更新日
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
