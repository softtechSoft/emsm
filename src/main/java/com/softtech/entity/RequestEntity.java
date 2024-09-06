package com.softtech.entity;

public class RequestEntity {

	//社員氏名
	private String employeeName;
	//会社名
	private String companyName;
	//契約ID
	private String contractID;
	//契約下限と上限
//	private String contractTime;
	private String contractLowerTime;
	private String contractUpperTime;
	//単価
	private int price;
	//稼働時間
	private float workTime;
	//交通費
	private int transport;
	//出張旅費
	private int businessTrip;
	//特別請求
	private int specialClaim;

	private int upperPrice;

	private int lowerPrice;

	private String claimID;

	private int upperTime;

	private int lowerTime;

	private int upperTotal;

	private int totalTax;

	private int claimPrice;
	private String claimMonth;

	private int exceTime;

	private int addpayOff;

	private int deficiTime;
	private int minusPayOff;

	private int sum;
	private String taxRate;

	private double consumpTax;

	private String claimStatus;
	public String getEmployeeName() {
		return employeeName;
	}

	public int getTransport() {
		return transport;
	}

	public void setTransport(int transport) {
		this.transport = transport;
	}

	public int getBusinessTrip() {
		return businessTrip;
	}

	public void setBusinessTrip(int businessTrip) {
		this.businessTrip = businessTrip;
	}

	public int getSpecialClaim() {
		return specialClaim;
	}

	public void setSpecialClaim(int specialClaim) {
		this.specialClaim = specialClaim;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public float getWorkTime() {
		return workTime;
	}

	public void setWorkTime(float workTime) {
		this.workTime = workTime;
	}


	public RequestEntity() {
		super();
	}

	public RequestEntity(String employeeName, String companyName, String contractLowerTime, String contractUpperTime, int price,
			float workTime) {
		super();
		this.employeeName = employeeName;
		this.companyName = companyName;
		this.contractLowerTime = contractLowerTime;
		this.contractUpperTime = contractUpperTime;
		this.price = price;
		this.workTime = workTime;
	}

	public String getContractID() {
		return contractID;
	}

	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	public int getUpperPrice() {
		return upperPrice;
	}

	public void setUpperPrice(int upperPrice) {
		this.upperPrice = upperPrice;
	}

	public int getLowerPrice() {
		return lowerPrice;
	}

	public void setLowerPrice(int lowerPrice) {
		this.lowerPrice = lowerPrice;
	}

	public int getUpperTime() {
		return upperTime;
	}

	public void setUpperTime(int upperTime) {
		this.upperTime = upperTime;
	}

	public int getLowerTime() {
		return lowerTime;
	}

	public void setLowerTime(int lowerTime) {
		this.lowerTime = lowerTime;
	}

	public int getUpperTotal() {
		return upperTotal;
	}

	public void setUpperTotal(int upperTotal) {
		this.upperTotal = upperTotal;
	}

	public int getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(int totalTax) {
		this.totalTax = totalTax;
	}

	public int getClaimPrice() {
		return claimPrice;
	}

	public void setClaimPrice(int claimPrice) {
		this.claimPrice = claimPrice;
	}

	public int getExceTime() {
		return exceTime;
	}

	public void setExceTime(int exceTime) {
		this.exceTime = exceTime;
	}

	public int getAddpayOff() {
		return addpayOff;
	}

	public void setAddpayOff(int addpayOff) {
		this.addpayOff = addpayOff;
	}

	public int getDeficiTime() {
		return deficiTime;
	}

	public void setDeficiTime(int deficiTime) {
		this.deficiTime = deficiTime;
	}

	public int getMinusPayOff() {
		return minusPayOff;
	}

	public void setMinusPayOff(int minusPayOff) {
		this.minusPayOff = minusPayOff;
	}

	public int getSum() {
		return sum;
	}

	public void setSum(int sum) {
		this.sum = sum;
	}

	public String getTaxRate() {
		return taxRate;
	}

	public void setTaxRate(String taxRate) {
		this.taxRate = taxRate;
	}

	public double getConsumpTax() {
		return consumpTax;
	}

	public void setConsumpTax(double consumpTax) {
		this.consumpTax = consumpTax;
	}

	public String getClaimStatus() {
		return claimStatus;
	}

	public void setClaimStatus(String claimStatus) {
		this.claimStatus = claimStatus;
	}

	public String getClaimID() {
		return claimID;
	}

	public void setClaimID(String claimID) {
		this.claimID = claimID;
	}

	public String getClaimMonth() {
		return claimMonth;
	}

	public void setClaimMonth(String claimMonth) {
		this.claimMonth = claimMonth;
	}


}
