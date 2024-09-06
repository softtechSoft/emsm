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
	private String sum;
	//超過単価
	private String upperPrice;
	//不足単価
	private String lowerPrice;
	//超過時間
	private String upperTime;
	//不足時間
	private String lowerTime;
	//超過額
	private String upperTotal;
	//不足額
	private String lowerTotal;
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

	public String getSum() {
		return sum;
	}

	public void setSum(String sum) {
		this.sum = sum;
	}

	public String getUpperPrice() {
		return upperPrice;
	}

	public void setUpperPrice(String upperPrice) {
		this.upperPrice = upperPrice;
	}

	public String getLowerPrice() {
		return lowerPrice;
	}

	public void setLowerPrice(String lowerPrice) {
		this.lowerPrice = lowerPrice;
	}

	public String getUpperTime() {
		return upperTime;
	}

	public void setUpperTime(String upperTime) {
		this.upperTime = upperTime;
	}

	public String getLowerTime() {
		return lowerTime;
	}

	public void setLowerTime(String lowerTime) {
		this.lowerTime = lowerTime;
	}

	public String getUpperTotal() {
		return upperTotal;
	}

	public void setUpperTotal(String upperTotal) {
		this.upperTotal = upperTotal;
	}

	public String getLowerTotal() {
		return lowerTotal;
	}

	public void setLowerTotal(String lowerTotal) {
		this.lowerTotal = lowerTotal;
	}


}
