package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.BpInvoiceFormBean;
import com.softtech.entity.BpInvoice;

public interface BpInvoiceService {
    List<BpInvoice> getAllInvoices(String paymentId);
    void insertInvoice(BpInvoiceFormBean formBean);
    BpInvoice getInvoiceById(String invoiceId);
    
    void clearInvoice(String invoiceId);
    void updateInvoice(BpInvoice invoice); // 更新請求書情報
    void updateInvoiceByPaymentId(BpInvoice invoice); // 通过支付ID更新請求書情報
	/**
	 * @return
	 */
	String getMaxBpInvoiceId();
}