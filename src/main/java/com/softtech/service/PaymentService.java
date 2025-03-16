package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.PaymentFormBean;
import com.softtech.actionForm.PaymentListFormBean;
import com.softtech.entity.Payment;

/**
 * 概要：支払情報取得インタフェース
 *
 * @author グエンバンフン@ソフトテク
 */
public interface PaymentService {
    /**
     * 機能：支払情報リスト取得
     *
     * @return 支払情報リスト
     */
	List<PaymentListFormBean> searchPaymentsByMonth(String paymentMonth);

    /**
     * 機能：支払IDによる支払情報検索
     *
     * @param paymentID 支払ID
     * @return 支払情報リスト
     */
    List<PaymentListFormBean> getPaymentList(); // 全支払情報を取得する

    /**
     * 機能：特定の支払情報取得
     *
     * @param paymentID 支払ID
     * @return 支払情報リスト
     */
    public List<Payment> getPaymentInfo(String paymentID);

    /**
     * 機能：支払情報更新
     *
     * @param paymentFormBean 支払情報フォーム
     * @return 更新成功フラグ
     */
    public boolean updatePayment(PaymentFormBean paymentFormBean);

    /**
     * 機能：DB EntityからUI Formへデータ転送
     *
     * @param sList 支払情報エンティティリスト
     * @return 支払情報フォーム
     */
    public PaymentFormBean transferEntityToUI(List<Payment> sList);

    /**
     * 機能：次の支払IDを取得
     *
     * @return 次の支払ID
     */
    public String getNextPaymentID();

    /**
     * 機能：支払情報追加
     *
     * @param paymentFormBean 支払情報フォーム
     * @return 追加成功フラグ
     */
    public boolean addPayment(PaymentFormBean paymentFormBean);
    List<PaymentListFormBean> getConsolidatedPayments(String paymentMonth);
}