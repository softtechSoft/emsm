package com.softtech.actionForm;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

public class YukyuFormBean {

		//ユーザID
		private String employeeID;

		//年度
	    private String nendo;

	    //総日数
	    private String totalDay;

	    //消化日数
	    @NotBlank(message = "消化日数を入力してください")
	    @Max(value = 20, message = "入力値は 20 以下に入力してください")
	    @Pattern(regexp = "^[0-9]+$", message = "整数で入力してください")
	    private String usedDay;

	  //作成日
	    private String insertDate;

	    //更新日
	    private String updateDate;

	    //更新選択ID用
	    private String employeeIDSelect;
	  //更新選択nendo用
	    private String nendoSelect;

	    //検索区別用のFlg
	  	private String selectFlg;

		public String getSelectFlg() {
			return selectFlg;
		}

		public void setSelectFlg(String selectFlg) {
			this.selectFlg = selectFlg;
		}

	    public String getEmployeeID() {
			return employeeID;
		}

		public void setEmployeeID(String employeeID) {
			this.employeeID = employeeID;
		}

		public String getNendo() {
			return nendo;
		}

		public void setNendo(String nendo) {
			this.nendo = nendo;
		}

		public String getTotalDay() {
			return totalDay;
		}

		public void setTotalDay(String totalDay) {
			this.totalDay = totalDay;
		}

		public String getUsedDay() {
			return usedDay;
		}

		public void setUsedDay(String usedDay) {
			this.usedDay = usedDay;
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

		public String getEmployeeIDSelect() {
			return employeeIDSelect;
		}

		public void setEmployeeIDSelect(String employeeIDSelect) {
			this.employeeIDSelect = employeeIDSelect;
		}

		public String getNendoSelect() {
			return nendoSelect;
		}

		public void setNendoSelect(String nendoSelect) {
			this.nendoSelect = nendoSelect;
		}



}
