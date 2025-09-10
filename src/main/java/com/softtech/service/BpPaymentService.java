package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.BpPaymentFormBean;
import com.softtech.entity.BpPayment;

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
    String insertBpPayment(BpPaymentFormBean formBean);

    /**
     * 更新
     * @param BpPaymentFormBean 支払信息
     */
    boolean updateBpPayment(BpPaymentFormBean formBean);

    /**
     * 削除
     * @param paymentId 支払ID
     */
    void deleteBpPayment(String paymentId);



    /**
     * 請求書リスト取得
     * @return 請求書リスト
     */
    List<BpPayment> getInvoiceList();

    /**
     * 最大値ID取得
     * @return 最大値ID
     */
     String getMaxPaymentId();

     /**
      * frombean to entity
      * @param frombean 画面データ
      */
     BpPayment transferToEntity(BpPaymentFormBean formBean);



}