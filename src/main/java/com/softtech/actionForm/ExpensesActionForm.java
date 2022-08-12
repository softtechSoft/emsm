package com.softtech.actionForm;

import java.io.Serializable;

public class ExpensesActionForm implements Serializable {

	private String expensesID;

	private String expensesName;

	/**
	 * @return expensesID
	 */
	public String getExpensesID() {
		return expensesID;
	}

	/**
	 * @param expensesID セットする expensesID
	 */
	public void setExpensesID(String expensesID) {
		this.expensesID = expensesID;
	}

	/**
	 * @return expensesName
	 */
	public String getExpensesName() {
		return expensesName;
	}

	/**
	 * @param expensesName セットする expensesName
	 */
	public void setExpensesName(String expensesName) {
		this.expensesName = expensesName;
	}



}
