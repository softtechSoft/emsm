package com.softtech.actionForm;

import org.springframework.web.multipart.MultipartFile;

/**
 * 請求書アップロード用FormBean
 */
public class InvoiceUploadForm {
    private String no;                 // No
    private String insertFlg;          // 新規フラグ 0:新規 1:更新
    private String month;              // 対象月
    private String companyId;          // company から companyId に変更
    private String invoiceNumber;      // インボイス番号
    private String paymentNo;          // 支払番号
    private String fileName;           // ファイル名
    private String fileSize;           // ファイルサイズ
    private String insertDate;         // アップロード日
    private String remarks;            // 備考
    private MultipartFile invoiceFile; // 請求書ファイル

    // getter/setter
    public String getNo() { return no; }
    public void setNo(String no) { this.no = no; }
    
    public String getInsertFlg() { return insertFlg; }
    public void setInsertFlg(String insertFlg) { this.insertFlg = insertFlg; }
    
    public String getMonth() { return month; }
    public void setMonth(String month) { this.month = month; }
    
    public String getCompanyId() { return companyId; }
    public void setCompanyId(String companyId) { this.companyId = companyId; }
    
    public String getInvoiceNumber() { return invoiceNumber; }
    public void setInvoiceNumber(String invoiceNumber) { this.invoiceNumber = invoiceNumber; }
    
    public String getPaymentNo() { return paymentNo; }
    public void setPaymentNo(String paymentNo) { this.paymentNo = paymentNo; }
    
    public String getFileName() { return fileName; }
    public void setFileName(String fileName) { this.fileName = fileName; }
    
    public String getFileSize() { return fileSize; }
    public void setFileSize(String fileSize) { this.fileSize = fileSize; }
    
    public String getInsertDate() { return insertDate; }
    public void setInsertDate(String insertDate) { this.insertDate = insertDate; }
    
    public String getRemarks() { return remarks; }
    public void setRemarks(String remarks) { this.remarks = remarks; }
    
    public MultipartFile getInvoiceFile() { return invoiceFile; }
    public void setInvoiceFile(MultipartFile invoiceFile) { this.invoiceFile = invoiceFile; }
} 