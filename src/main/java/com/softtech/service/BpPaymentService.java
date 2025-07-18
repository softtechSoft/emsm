package com.softtech.service;

import com.softtech.entity.BpPayment;
import java.util.List;

/**
 * BP支払管理用Service
 */
public interface BpPaymentService {
    /**
     * 指定月の支払リスト取得
     * @param month 対象月
     * @return 支払リスト
     */
    List<BpPayment> getBpPaymentList(String month);

    /**
     * 指定月の支払汇总リスト取得
     * @param month 対象月
     * @return 支払汇总リスト
     */
    List<BpPayment> getBpPaymentSummaryList(String month);

    /**
     * 支払IDで1件取得
     * @param paymentId 支払ID
     * @return 支払信息
     */
    BpPayment getBpPaymentById(String paymentId);

    /**
     * 新規登録
     * @param bpPayment 支払信息
     */
    void insertBpPayment(BpPayment bpPayment);

    /**
     * 更新
     * @param bpPayment 支払信息
     */
    void updateBpPayment(BpPayment bpPayment);

    /**
     * 削除
     * @param paymentId 支払ID
     */
    void deleteBpPayment(String paymentId);

    /**
     * 利用可能な月リスト取得
     * @return 月リスト
     */
    List<String> getAvailableMonths();

    /**
     * 請求書リスト取得
     * @return 請求書リスト
     */
    List<BpPayment> getInvoiceList();
} 