package com.softtech.service;

import com.softtech.entity.BpPayment;
import com.softtech.mappers.BpPaymentMapper;
import com.softtech.util.DataUtil;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

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
    public void insertBpPayment(BpPayment bpPayment) {
        try {
             // 查询当前最大支付ID
        String maxId = bpPaymentMapper.getMaxPaymentId();
        // 生成新ID
        String newId = (maxId == null) ? "PY001" : DataUtil.getNextID(maxId, 2);
        // 设置新ID
        bpPayment.setPaymentId(newId);
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
    public void updateBpPayment(BpPayment bpPayment) {
        try {
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

    


}