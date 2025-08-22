package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.BpInvoiceFormBean;
import com.softtech.entity.BpInvoice;
import com.softtech.mappers.BpInvoiceMapper;
import com.softtech.util.DataUtil;

@Service
public class BpInvoiceServiceImpl implements BpInvoiceService {
    @Autowired
    private BpInvoiceMapper bpInvoiceMapper;

    @Override
    public List<BpInvoice> getAllInvoices(String paymentId) {
        return bpInvoiceMapper.getAllInvoices(paymentId);
    }

    @Override
    public void insertInvoice(BpInvoiceFormBean formBean) {
        bpInvoiceMapper.insertInvoice(formBean);
    }

    @Override
    public BpInvoice getInvoiceById(String invoiceId) {
        return bpInvoiceMapper.getInvoiceById(invoiceId);
    }

    @Override
    public void clearInvoice(String invoiceId) {
        //bpInvoiceMapper.deleteInvoice(invoiceId);
        bpInvoiceMapper.clearInvoiceFileInfo(invoiceId);
        
    }
    
    @Override
    public void updateInvoice(BpInvoice invoice) {
        bpInvoiceMapper.updateInvoice(invoice);
    }
    
    @Override
    public void updateInvoiceByPaymentId(BpInvoice invoice) {
        bpInvoiceMapper.updateInvoiceByPaymentId(invoice);
    }
    
    /**
     * 最大値ID取得
     * @return 最大値ID
     */
    @Override
    public String getMaxBpInvoiceId() {
    	
		String maxBpInvoiceId = bpInvoiceMapper.getMaxBpInvoiceId();
        
        if (maxBpInvoiceId != null) {
        	maxBpInvoiceId = maxBpInvoiceId.toUpperCase();
        }
        String nextBpInvoiceId = DataUtil.getNextID(maxBpInvoiceId, 2);
//          return nextEmployeeID;
        return nextBpInvoiceId != null ? nextBpInvoiceId.toUpperCase() : null;
     
    }
}