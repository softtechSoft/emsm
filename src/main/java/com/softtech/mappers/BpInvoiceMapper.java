package com.softtech.mappers;

import java.util.List;

import com.softtech.actionForm.BpInvoiceFormBean;
import com.softtech.entity.BpInvoice;

public interface BpInvoiceMapper {
    List<BpInvoice> getAllInvoices(String paymentId);
    void insertInvoice(BpInvoiceFormBean formBean);
    BpInvoice getInvoiceById(String invoiceId);
    void deleteInvoice(String invoiceId); // 逻辑删除
    void clearInvoiceFileInfo(String invoiceId); //請求書情報
    
    void updateInvoice(BpInvoice invoice); // 更新請求書情報
    void updateInvoiceByPaymentId(BpInvoice invoice); // 通过支付ID更新請求書情報
	/**
	 * @return
	 */
	String getMaxBpInvoiceId();
}