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
	
//	private int upperPrice;
//	
//	private int lowerPrice;
//	
//	
//	
//	private int upperTime;
//	
//	private int lowerTime;
//	
//	private int upperTotal;
//	
//	private int totalTax;
//	
//	private int claimPrice;
	
	

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

	
}
