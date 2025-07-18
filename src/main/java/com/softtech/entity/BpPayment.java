package com.softtech.entity;

import java.util.Date;

public class BpPayment {
    private String paymentId;              // 支払ID
    private String month;                  // 対象月
    private String employeeId;             // 社員ID
    private String employeeName;           // 社員名称（表示用）
    private String companyId;              // BP会社ID
    private String companyName;            // BP会社名称（表示用）
    private String dispatchCompanyId;      // 派遣会社ID
    private String dispatchCompanyName;    // 派遣会社名称（表示用）
    private String contractId;             // 契約ID
    private String bpContractId;           // BP契約ID
    private Integer unitPriceExTax;        // 税抜単価
    private Integer outsourcingAmountExTax;// 税抜外注金額
    private Integer outsourcingAmountInTax;// 税込外注金額
    private Integer commission;            // 手数料
    private Date transferDate;             // 振込日
    private Date entryDate;                // 登録日
    private String remarks;                // 備考
    private String invoiceNumber;          // 請書番号
    private String status;                 // ステータス
    private Integer employeeCount;         // 员工数量（汇总用）

    public String getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(String paymentId) {
        this.paymentId = paymentId;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getDispatchCompanyId() {
        return dispatchCompanyId;
    }

    public void setDispatchCompanyId(String dispatchCompanyId) {
        this.dispatchCompanyId = dispatchCompanyId;
    }

    public String getDispatchCompanyName() {
        return dispatchCompanyName;
    }

    public void setDispatchCompanyName(String dispatchCompanyName) {
        this.dispatchCompanyName = dispatchCompanyName;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getBpContractId() {
        return bpContractId;
    }

    public void setBpContractId(String bpContractId) {
        this.bpContractId = bpContractId;
    }

    public Integer getUnitPriceExTax() {
        return unitPriceExTax;
    }

    public void setUnitPriceExTax(Integer unitPriceExTax) {
        this.unitPriceExTax = unitPriceExTax;
    }

    public Integer getOutsourcingAmountExTax() {
        return outsourcingAmountExTax;
    }

    public void setOutsourcingAmountExTax(Integer outsourcingAmountExTax) {
        this.outsourcingAmountExTax = outsourcingAmountExTax;
    }

    public Integer getOutsourcingAmountInTax() {
        return outsourcingAmountInTax;
    }

    public void setOutsourcingAmountInTax(Integer outsourcingAmountInTax) {
        this.outsourcingAmountInTax = outsourcingAmountInTax;
    }

    public Integer getCommission() {
        return commission;
    }

    public void setCommission(Integer commission) {
        this.commission = commission;
    }

    public Date getTransferDate() {
        return transferDate;
    }

    public void setTransferDate(Date transferDate) {
        this.transferDate = transferDate;
    }

    public Date getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(Date entryDate) {
        this.entryDate = entryDate;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Integer getEmployeeCount() {
        return employeeCount;
    }

    public void setEmployeeCount(Integer employeeCount) {
        this.employeeCount = employeeCount;
    }

    // 为了兼容JSP页面，添加no属性的getter和setter方法
    public String getNo() {
        return paymentId;
    }

    public void setNo(String no) {
        this.paymentId = no;
    }
}