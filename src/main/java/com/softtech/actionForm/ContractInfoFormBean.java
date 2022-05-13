package com.softtech.actionForm;

import java.util.Date;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class ContractInfoFormBean {
	//契約ID
	private String contractID;
	//契約名称
	@NotEmpty(message = "契約名称を入力してください。")
	private String contractName;
	//社員ID
	private String employeeID;
	//社員名称
	private String employeeName;
	//取引先ID
	private String companyID;
	//取引先名称
	private String companyName;
	//単価
	@NotEmpty(message = "単価を入力してください。")
	@Pattern(message = "単価に数値のみを入力してください。", regexp = "\\\\\\d{0,7}")
	private String price;
	//精算タイプ
	private String payOff;
	//契約下限
	@NotEmpty(message = "契約下限を入力してください。")
	@Pattern(message = "契約下限に数値のみを入力してください。", regexp = "\\d{0,3}")
	private String lowerTime;
	//控除単価
	@NotEmpty(message = "控除単価を入力してください。")
	@Pattern(message = "控除単価に数値のみを入力してください。", regexp = "\\\\\\d{0,6}")
	private String lowerPrice;
	//契約上限
	@NotEmpty(message = "契約上限を入力してください。")
	@Pattern(message = "契約上限に数値のみを入力してください。", regexp = "\\d{0,3}")
	private String upperTime;
	//残業単価
	@NotEmpty(message = "残業単価を入力してください。")

	private String upperPrice;
	//契約開始日
	//@NotEmpty(message = "契約開始日を入力してください。")
	private Date contractBeginDate;
	private String contractBeginDateS;
	//契約終
	@NotEmpty(message = "契約終了日を入力してください。")
	private String contractEndDate;
	//支払サイト
	@NotEmpty(message = "支払サイトを入力してください。")
	private String paymentTerm;
	//原本郵送フラグ
	private String postNeed;
	//タイムレポートパス
	@NotEmpty(message = "タイムレポートパスを入力してください。")
	private String timeReportPath;
	//請求書名称
	private String invoice;
	//進行ステータス
	private String status;
	//作成日
	@NotEmpty(message = "支払サイトを入力してください。")
	private String insertDate;
	//更新日
	private String updateDate;


	//新規フラグ　0　新規　１　更新
	private String insertFlg;

//	private int id;
//	private String text;
	//社員IDリスト
//	private ArrayList<EmployeeIDName> employeeIDNameList;
//	//契約IDリスト
//	private ArrayList<ContractIDName> contractIDNameList;


	/**
	 * @return contractID
	 */
	public String getContractID() {
		return contractID;
	}
	/**
	 * @param contractID セットする contractID
	 */
	public void setContractID(String contractID) {
		this.contractID = contractID;
	}
	/**
	 * @return contractName
	 */
	public String getContractName() {
		return contractName;
	}
	/**
	 * @param contractName セットする contractName
	 */
	public void setContractName(String contractName) {
		this.contractName = contractName;
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
	/**
	 * @return companyID
	 */
	public String getCompanyID() {
		return companyID;
	}
	/**
	 * @param companyID セットする companyID
	 */
	public void setCompanyID(String companyID) {
		this.companyID = companyID;
	}
	/**
	 * @return companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName セットする companyName
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return price
	 */
	public String getPrice() {
		return price;
	}
	/**
	 * @param price セットする price
	 */
	public void setPrice(String price) {
		this.price = price;
	}
	/**
	 * @return payOff
	 */
	public String getPayOff() {
		return payOff;
	}
	/**
	 * @param payOff セットする payOff
	 */
	public void setPayOff(String payOff) {
		this.payOff = payOff;
	}
	/**
	 * @return lowerTime
	 */
	public String getLowerTime() {
		return lowerTime;
	}
	/**
	 * @param lowerTime セットする lowerTime
	 */
	public void setLowerTime(String lowerTime) {
		this.lowerTime = lowerTime;
	}
	/**
	 * @return lowerPrice
	 */
	public String getLowerPrice() {
		return lowerPrice;
	}
	/**
	 * @param lowerPrice セットする lowerPrice
	 */
	public void setLowerPrice(String lowerPrice) {
		this.lowerPrice = lowerPrice;
	}
	/**
	 * @return upperTime
	 */
	public String getUpperTime() {
		return upperTime;
	}
	/**
	 * @param upperTime セットする upperTime
	 */
	public void setUpperTime(String upperTime) {
		this.upperTime = upperTime;
	}
	/**
	 * @return upperPrice
	 */
	public String getUpperPrice() {
		return upperPrice;
	}
	/**
	 * @param upperPrice セットする upperPrice
	 */
	public void setUpperPrice(String upperPrice) {
		this.upperPrice = upperPrice;
	}

	/**
	 * @return contractEndDate
	 */
	public String getContractEndDate() {
		return contractEndDate;
	}
	/**
	 * @param contractEndDate セットする contractEndDate
	 */
	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}
	/**
	 * @return paymentTerm
	 */
	public String getPaymentTerm() {
		return paymentTerm;
	}
	/**
	 * @param paymentTerm セットする paymentTerm
	 */
	public void setPaymentTerm(String paymentTerm) {
		this.paymentTerm = paymentTerm;
	}
	/**
	 * @return postNeed
	 */
	public String getPostNeed() {
		return postNeed;
	}
	/**
	 * @param postNeed セットする postNeed
	 */
	public void setPostNeed(String postNeed) {
		this.postNeed = postNeed;
	}
	/**
	 * @return timeReportPath
	 */
	public String getTimeReportPath() {
		return timeReportPath;
	}
	/**
	 * @param timeReportPath セットする timeReportPath
	 */
	public void setTimeReportPath(String timeReportPath) {
		this.timeReportPath = timeReportPath;
	}
	/**
	 * @return invoice
	 */
	public String getInvoice() {
		return invoice;
	}
	/**
	 * @param invoice セットする invoice
	 */
	public void setInvoice(String invoice) {
		this.invoice = invoice;
	}
	/**
	 * @return status
	 */
	public String getStatus() {
		return status;
	}
	/**
	 * @param status セットする status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	/**
	 * @return insertDate
	 */
	public String getInsertDate() {
		return insertDate;
	}
	/**
	 * @param insertDate セットする insertDate
	 */
	public void setInsertDate(String insertDate) {
		this.insertDate = insertDate;
	}
	/**
	 * @return updateDate
	 */
	public String getUpdateDate() {
		return updateDate;
	}
	/**
	 * @param updateDate セットする updateDate
	 */
	public void setUpdateDate(String updateDate) {
		this.updateDate = updateDate;
	}
//	/**
//	 * @return id
//	 */
//	public int getId() {
//		return id;
//	}
//	/**
//	 * @param id セットする id
//	 */
//	public void setId(int id) {
//		this.id = id;
//	}
//	/**
//	 * @return text
//	 */
//	public String getText() {
//		return text;
//	}
//	/**
//	 * @param text セットする text
//	 */
//	public void setText(String text) {
//		this.text = text;
//	}
//	/**
//	 * @return employeeIDNameList
//	 */
//	public ArrayList<EmployeeIDName> getEmployeeIDNameList() {
//		return employeeIDNameList;
//	}
//	/**
//	 * @param employeeIDNameList セットする employeeIDNameList
//	 */
//	public void setEmployeeIDNameList(ArrayList<EmployeeIDName> employeeIDNameList) {
//		this.employeeIDNameList = employeeIDNameList;
//	}
//	/**
//	 * @return contractIDNameList
//	 */
//	public ArrayList<ContractIDName> getContractIDNameList() {
//		return contractIDNameList;
//	}
//	/**
//	 * @param contractIDNameList セットする contractIDNameList
//	 */
//	public void setContractIDNameList(ArrayList<ContractIDName> contractIDNameList) {
//		this.contractIDNameList = contractIDNameList;
//	}
	/**
	 * @return insertFlg
	 */
	public String getInsertFlg() {
		return insertFlg;
	}
	/**
	 * @param insertFlg セットする insertFlg
	 */
	public void setInsertFlg(String insertFlg) {
		this.insertFlg = insertFlg;
	}
	/**
	 * @return contractBeginDate
	 */
	public Date getContractBeginDate() {
		return contractBeginDate;
	}
	/**
	 * @param contractBeginDate セットする contractBeginDate
	 */
	public void setContractBeginDate(Date contractBeginDate) {
		this.contractBeginDate = contractBeginDate;
	}
	/**
	 * @return contractBeginDateS
	 */
	public String getContractBeginDateS() {
		return contractBeginDateS;
	}
	/**
	 * @param contractBeginDateS セットする contractBeginDateS
	 */
	public void setContractBeginDateS(String contractBeginDateS) {
		this.contractBeginDateS = contractBeginDateS;
	}



}


