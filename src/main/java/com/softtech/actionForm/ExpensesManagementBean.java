package com.softtech.actionForm;
import javax.validation.constraints.Size;
public class ExpensesManagementBean {
	    //発生日
		@Size(min=1, max=30,message="発生日を入力してください。")
		private String accrualDate ;
		//担当者
		@Size(min=1, max=30,message="担当者を入力してください。")
		private String tantouName ;
		//金額
		@Size(min=1, max=30,message="金額を入力してください。")
		private String cost ;
		//経費種別
		@Size(min=1, max=30,message="経費種別を入力してください。")
		private String expensesType ;
		//場所
		@Size(min=1, max=30,message="場所を入力してください。")
		private String happenAdderss;
		//承認ステータス
		private String confirmStatus;
		//精算タイプ
		private String stmtlType;
		//精算日
		@Size(min=1, max=30,message="精算日を入力してください。")
		private String stmtlDay ;
		//精算ステータス
		private String stmtlStatus;
		//備考
		@Size(min=1, max=30,message="備考を入力してください。")
		private String remark;
		public String getAccrualDate() {
			return accrualDate;
		}
		public void setAccrualDate(String accrualDate) {
			this.accrualDate = accrualDate;
		}
		public String getTantouName() {
			return tantouName;
		}
		public void setTantouName(String tantouName) {
			this.tantouName = tantouName;
		}
		public String getCost() {
			return cost;
		}
		public void setCost(String cost) {
			this.cost = cost;
		}
		public String getExpensesType() {
			return expensesType;
		}
		public void setExpensesType(String expensesType) {
			this.expensesType = expensesType;
		}
		public String getHappenAdderss() {
			return happenAdderss;
		}
		public void setHappenAdderss(String happenAdderss) {
			this.happenAdderss = happenAdderss;
		}
		public String getConfirmStatus() {
			return confirmStatus;
		}
		public void setConfirmStatus(String confirmStatus) {
			this.confirmStatus = confirmStatus;
		}
		public String getStmtlType() {
			return stmtlType;
		}
		public void setStmtlType(String stmtlType) {
			this.stmtlType = stmtlType;
		}
		public String getStmtlDay() {
			return stmtlDay;
		}
		public void setStmtlDay(String stmtlDay) {
			this.stmtlDay = stmtlDay;
		}
		public String getStmtlStatus() {
			return stmtlStatus;
		}
		public void setStmtlStatus(String stmtlStatus) {
			this.stmtlStatus = stmtlStatus;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}

}
