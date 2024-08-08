/**
 * 
 */
package com.softtech.entity;

/**
 * 
 */
public class RequestToDownloadEntity {

	//社員氏名
	private String employeeName;
	//会社名
	private String companyName;
	
	//契約期間
	private String contractLowerTime;
	private String contractUpperTime;
	//単価
	private String price;
	//稼働時間
	private String workTime;
	
	//対象年月
	private String claimMonth;

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getContractLowerTime() {
		return contractLowerTime;
	}

	public void setContractLowerTime(String contractLowerTime) {
		this.contractLowerTime = contractLowerTime;
	}

	public String getContractUpperTime() {
		return contractUpperTime;
	}

	public void setContractUpperTime(String contractUpperTime) {
		this.contractUpperTime = contractUpperTime;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getClaimMonth() {
		return claimMonth;
	}

	public void setClaimMonth(String claimMonth) {
		this.claimMonth = claimMonth;
	}
	
	
}
