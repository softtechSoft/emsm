package com.softtech.actionForm;

import java.util.HashMap;
import java.util.Map;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class RequestFromBean {

	//社員氏名
	private String employeeName;
	//会社名
	private String companyName;
	//契約ID
	private String contractID;
	//契約期間
	private String contractTime;
	private String contractLowerTime;
	private String contractUpperTime;
	//単価
	private String price;
	//稼働時間
	private String workTime;
	
//計算した
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
	//交通費
	private String transport;
	//出張旅費
	private String businessTrip;
	//特別請求
	private String specialClaim;
	//税抜総額
	private String claimPrice;
	//請求税込額
	private String sum;
	
	//対象年月
	@Size(min=2, max=6,message="対象月は数字6桁で入力してください。例：202104")
	@Pattern(regexp = "^\\d{6}$", message = "対象月は数字で入力してください。例：202104")
	@NotBlank(message="対象月を入力してください。例：202104")
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


	public String getContractTime() {
		return contractTime;
	}


	public void setContractTime(String contractTime) {
		this.contractTime = contractTime;
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


	public String getClaimPrice() {
		return claimPrice;
	}


	public void setClaimPrice(String claimPrice) {
		this.claimPrice = claimPrice;
	}


	public String getClaimMonth() {
		return claimMonth;
	}


	public void setClaimMonth(String claimMonth) {
		this.claimMonth = claimMonth;
	}


	public String getTransport() {
		return transport;
	}


	public void setTransport(String transport) {
		this.transport = transport;
	}


	public String getBusinessTrip() {
		return businessTrip;
	}


	public void setBusinessTrip(String businessTrip) {
		this.businessTrip = businessTrip;
	}


	public String getSpecialClaim() {
		return specialClaim;
	}


	public void setSpecialClaim(String specialClaim) {
		this.specialClaim = specialClaim;
	}


	public String getSum() {
		return sum;
	}


	public void setSum(String sum) {
		this.sum = sum;
	}


	// ヘッダーマッピング
    public static Map<String, String> getHeaderMapping() {
        Map<String, String> headerMapping = new HashMap<>();
        headerMapping.put("employeeName", "社員氏名");
        headerMapping.put("companyName", "客先");
        headerMapping.put("contractTime", "契約期間(H)");
        headerMapping.put("price", "標準単価(円)");
        headerMapping.put("upperPrice", "超過単価(円)");
        headerMapping.put("lowerPrice", "不足単価(円)");
        headerMapping.put("workTime", "稼働時間(H)");
        headerMapping.put("upperTime", "超過時間(H)");
        headerMapping.put("lowerTime", "不足時間(H)");
        headerMapping.put("upperTotal", "超過額(円)");
        headerMapping.put("lowerTotal", "控除額(円)");
        headerMapping.put("claimPrice", "請求額(円)");
        return headerMapping;
    }


	public String getContractID() {
		return contractID;
	}


	public void setContractID(String contractID) {
		this.contractID = contractID;
	}

	
}
