package com.softtech.actionForm;

import java.io.Serializable;

public class EmployeeActionForm implements Serializable {

	private String employeeID;

	private String employeeName;

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
		return "EmployeeActionForm{" +
				"employeeID='" + employeeID + '\'' +
				", employeeName='" + employeeName + '\'' +
				'}';
	}
}
