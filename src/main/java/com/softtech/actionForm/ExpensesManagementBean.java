package com.softtech.actionForm;
import javax.validation.constraints.Size;
public class ExpensesManagementBean {
	     private String employeeID;

	     private String employeeName;
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
		//経費種別明細
		private String expensesTypeDetail ;
		//場所
		@Size(min=1, max=15,message="場所の長さが15文字を越えています。")
		private String happenAddress;
		//承認ステータス
		private String confirmStaus;
		//精算タイプ
		private String stmtlType;
		//精算日
		@Size(min=1, max=30,message="精算日を入力してください。")
		private String stmtlDate ;
		//精算ステータス
		private String stmtlStaus;
		//備考
		@Size(min=1, max=15,message="備考の長さが15文字を越えています。")
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
		public String getStmtlType() {
			return stmtlType;
		}
		public void setStmtlType(String stmtlType) {
			this.stmtlType = stmtlType;
		}
		public String getStmtlDate() {
			return stmtlDate;
		}
		public void setStmtlDate(String stmtlDate) {
			this.stmtlDate = stmtlDate;
		}

		public void setRemark(String remark) {
			this.remark = remark;
		}
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
		 * @return happenAddress
		 */
		public String getHappenAddress() {
			return happenAddress;
		}
		/**
		 * @param happenAddress セットする happenAddress
		 */
		public void setHappenAddress(String happenAddress) {
			this.happenAddress = happenAddress;
		}
		/**
		 * @return paymentType
		 */

		/**
		 * @return confirmStaus
		 */
		public String getConfirmStaus() {
			return confirmStaus;
		}
		/**
		 * @param confirmStaus セットする confirmStaus
		 */
		public void setConfirmStaus(String confirmStaus) {
			this.confirmStaus = confirmStaus;
		}
		/**
		 * @return stmtlStaus
		 */
		public String getStmtlStaus() {
			return stmtlStaus;
		}
		/**
		 * @param stmtlStaus セットする stmtlStaus
		 */
		public void setStmtlStaus(String stmtlStaus) {
			this.stmtlStaus = stmtlStaus;
		}
		/**
		 * @return remark
		 */
		public String getRemark() {
			return remark;
		}
		/**
		 * @return expensesType
		 */
		public String getExpensesType() {
			return expensesType;
		}
		/**
		 * @param expensesType セットする expensesType
		 */
		public void setExpensesType(String expensesType) {
			this.expensesType = expensesType;
		}
		/**
		 * @return expensesTypeDetail
		 */
		public String getExpensesTypeDetail() {
			return expensesTypeDetail;
		}
		/**
		 * @param expensesTypeDetail セットする expensesTypeDetail
		 */
		public void setExpensesTypeDetail(String expensesTypeDetail) {
			this.expensesTypeDetail = expensesTypeDetail;
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
		@Override
		public String toString() {
			return "ExpensesManagementBean [employeeID=" + employeeID + ", employeeName=" + employeeName
					+ ", accrualDate=" + accrualDate + ", tantouName=" + tantouName + ", cost=" + cost
					+ ", expensesType=" + expensesType + ", expensesTypeDetail=" + expensesTypeDetail
					+ ", happenAddress=" + happenAddress + ", confirmStaus=" + confirmStaus + ", stmtlType=" + stmtlType
					+ ", stmtlDate=" + stmtlDate + ", stmtlStaus=" + stmtlStaus + ", remark=" + remark + "]";
		}




}
