package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.BpPaymentFormBean;
import com.softtech.entity.BpPayment;
import com.softtech.mappers.BpPaymentMapper;
import com.softtech.util.DataUtil;

/**
 * BP支払管理用Service实现
 */
@Service
public class BpPaymentServiceImpl implements BpPaymentService {
    @Autowired
    private BpPaymentMapper bpPaymentMapper;

    /**
     * 指定月の支払リスト取得
     */
    @Override
    public List<BpPayment> getBpPaymentList(String month) {
        try {
            return bpPaymentMapper.getBpPaymentList(month);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 指定月の支払汇总リスト取得
     */
    @Override
    public List<BpPayment> getBpPaymentSummaryList(String month) {
        try {
            return bpPaymentMapper.getBpPaymentSummaryList(month);
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    /**
     * 支払IDで1件取得
     */
    @Override
    public BpPayment getBpPaymentById(String paymentId) {
        try {
            return bpPaymentMapper.getBpPaymentById(paymentId);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 新規登録
     */
    @Override
    public boolean insertBpPayment(BpPaymentFormBean formBean) {
        
    	try {
	        // transferToEntity    
	        BpPayment bpPayment = transferToEntity(formBean);
	        // 插入数据库
	        bpPaymentMapper.insertBpPayment(bpPayment);
	        return true; 
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        
    }

    /**
     * 更新
     */
    @Override
    public boolean updateBpPayment(BpPaymentFormBean formBean) {
        try {
        	// transferToEntity
            BpPayment bpPayment = transferToEntity(formBean);
            // 更新数据库
            bpPaymentMapper.updateBpPayment(bpPayment);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 削除
     */
    @Override
    public void deleteBpPayment(String paymentId) {
        try {
            bpPaymentMapper.deleteBpPayment(paymentId);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    

    /**
     * 請求書リスト取得
     */
    @Override
    public List<BpPayment> getInvoiceList() {
        try {
            return bpPaymentMapper.getInvoiceList();
            
        } catch (Exception e) {
            e.printStackTrace();
            // 仮のデータを返す
            return new ArrayList<>();
        }
    }

    /**
     * 最大値ID取得
     * @return 最大値ID
     */
    @Override
    public String getMaxPaymentId() {
    	
		String maxBpPaymentId = bpPaymentMapper.getMaxPaymentId();
        
        if (maxBpPaymentId != null) {
        	maxBpPaymentId = maxBpPaymentId.toUpperCase();
        }
        String nextBpPaymentId = DataUtil.getNextID(maxBpPaymentId, 2);
//          return nextEmployeeID;
        return nextBpPaymentId != null ? nextBpPaymentId.toUpperCase() : null;
     
    }

    
    /**
     * transferToEntity
     * @return BpPayment
     */
	@Override
	public BpPayment transferToEntity(BpPaymentFormBean formBean) {
		BpPayment bpPayment = new BpPayment();

		bpPayment.setMonth(formBean.getMonth());
        bpPayment.setPaymentId(formBean.getNo());
        bpPayment.setEmployeeId(formBean.getEmployeeId());
        bpPayment.setCompanyId(formBean.getCompanyId());
        bpPayment.setDispatchCompanyId(formBean.getDispatchCompanyId());
        bpPayment.setUnitPriceExTax(formBean.getUnitPriceExTax());
        bpPayment.setOutsourcingAmountExTax(formBean.getOutsourcingAmountExTax());
        bpPayment.setOutsourcingAmountInTax(formBean.getOutsourcingAmountInTax());
        bpPayment.setCommission(formBean.getCommission());
        
        // 处理日期字段
        if (formBean.getTransferDate() != null && !formBean.getTransferDate().isEmpty()) {
            bpPayment.setTransferDate(java.sql.Date.valueOf(formBean.getTransferDate()));
        }
        
        if (formBean.getEntryDate() != null && !formBean.getEntryDate().isEmpty()) {
            bpPayment.setEntryDate(java.sql.Date.valueOf(formBean.getEntryDate()));
        }
        
        bpPayment.setRemarks(formBean.getRemarks());
        bpPayment.setInvoiceNumber(formBean.getInvoiceNumber());
        bpPayment.setStatus("1"); // 默认状态为1（有效）
        
		return bpPayment;
	}
    


}