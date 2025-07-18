package com.softtech.actionForm;

import org.springframework.web.multipart.MultipartFile;

/**
 * BP支払管理用FormBean
 * Spring表单绑定用，字段与实体一致
 */
public class BpPaymentFormBean {
    private String no;                 // 支払ID
    private String month;              // 対象月
    private String employeeId;         // 社員ID
    private String companyId;          // BP会社ID
    private String dispatchCompanyId;  // 派遣会社ID
    private String contractId;         // 契約ID
    private String bpContractId;       // BP契約ID
    private Integer unitPriceExTax;    // 税抜単価
    private Integer outsourcingAmountExTax; // 税抜外注金額
    private Integer outsourcingAmountInTax; // 税込外注金額
    private Integer commission;        // 手数料
    private String transferDate;       // 振込日（yyyy-MM-dd）
    private String entryDate;          // 登録日（yyyy-MM-dd）
    private String remarks;            // 備考
    private String invoiceNumber;      // 請書番号
    private String status;             // ステータス
    private String insertFlg;          // 新規/更新フラグ
    private MultipartFile invoiceFile; // 請求書ファイル

    // getter/setter
    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    public String getEmployeeId() { return employeeId; }
    public void setEmployeeId(String employeeId) { this.employeeId = employeeId; }
    public String getCompanyId() { return companyId; }
    public void setCompanyId(String companyId) { this.companyId = companyId; }
    public String getDispatchCompanyId() { return dispatchCompanyId; }
    public void setDispatchCompanyId(String dispatchCompanyId) { this.dispatchCompanyId = dispatchCompanyId; }
    public String getContractId() { return contractId; }
    public void setContractId(String contractId) { this.contractId = contractId; }
    public String getBpContractId() { return bpContractId; }
    public void setBpContractId(String bpContractId) { this.bpContractId = bpContractId; }
    public Integer getUnitPriceExTax() { return unitPriceExTax; }
    public void setUnitPriceExTax(Integer unitPriceExTax) { this.unitPriceExTax = unitPriceExTax; }
    public Integer getOutsourcingAmountExTax() { return outsourcingAmountExTax; }
    public void setOutsourcingAmountExTax(Integer outsourcingAmountExTax) { this.outsourcingAmountExTax = outsourcingAmountExTax; }
    public Integer getOutsourcingAmountInTax() { return outsourcingAmountInTax; }
    public void setOutsourcingAmountInTax(Integer outsourcingAmountInTax) { this.outsourcingAmountInTax = outsourcingAmountInTax; }
    public Integer getCommission() { return commission; }
    public void setCommission(Integer commission) { this.commission = commission; }
    public String getTransferDate() { return transferDate; }
    public void setTransferDate(String transferDate) { this.transferDate = transferDate; }
    public String getEntryDate() { return entryDate; }
    public void setEntryDate(String entryDate) { this.entryDate = entryDate; }
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public String getInsertFlg() { return insertFlg; }
    public void setInsertFlg(String insertFlg) { this.insertFlg = insertFlg; }
    public MultipartFile getInvoiceFile() { return invoiceFile; }
    public void setInvoiceFile(MultipartFile invoiceFile) { this.invoiceFile = invoiceFile; }
} 