package com.softtech.actionForm;

public class SalaryInfoBean {
	    // 社員id
		private  String employeeID;
		// 対象年月
		private String month;
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
		 * @return month
		 */
		public String getMonth() {
			return month;
		}
		/**
		 * @param month セットする month
		 */
		public void setMonth(String month) {
			this.month = month;
		}

}
