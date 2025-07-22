package com.softtech.service;

import java.util.ArrayList;
import java.util.Arrays;
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
    public void insertBpPayment(BpPaymentFormBean formBean) {
        try {
             // 查询当前最大支付ID
        // String maxId = bpPaymentMapper.getMaxPaymentId();
        // // 生成新ID
        // String newId = (maxId == null) ? "PY001" : DataUtil.getNextID(maxId, 2);
        // // 设置新ID
        // bpPayment.setPaymentId(newId);
        BpPayment bpPayment = transferToEntity(formBean);
        // 插入数据库
        bpPaymentMapper.insertBpPayment(bpPayment);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 更新
     */
    @Override
    public void updateBpPayment(BpPaymentFormBean formBean) {
        try {
            BpPayment bpPayment = transferToEntity(formBean);
            // 更新数据库
            bpPaymentMapper.updateBpPayment(bpPayment);
        } catch (Exception e) {
            e.printStackTrace();
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
     * 利用可能な月リスト取得
     */
    @Override
    public List<String> getAvailableMonths() {
        try {
            return bpPaymentMapper.getAvailableMonths();
        } catch (Exception e) {
            e.printStackTrace();
            // 仮のデータを返す
            return Arrays.asList("202501", "202502", "202503", "202504", "202505");
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
		//
		return bpPayment;
	}
    


}