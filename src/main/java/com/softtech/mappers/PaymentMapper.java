package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.actionForm.PaymentListFormBean;
import com.softtech.entity.Payment;

/**
 * 支払情報マッパーインターフェース
 */
@Mapper
public interface PaymentMapper {
    /**
     * 支払情報リストを取得する
     *
     * @return 支払情報リスト
     */
	List<PaymentListFormBean> getAllPayments();

    /**
     * 支払IDによる支払情報を検索する
     *
     * @param paymentID 支払ID
     * @return 支払情報リスト
     */
	 List<PaymentListFormBean> searchPaymentsByMonth(@Param("paymentMonth") String paymentMonth);

    /**
     * 特定の支払情報を取得する
     *
     * @param paymentID 支払ID
     * @return 支払情報リスト
     */
    List<Payment> getPaymentInfo(@Param("paymentID") String paymentID);

    /**
     * 支払情報を更新する
     *
     * @param payment 支払情報エンティティ
     */
    void updatePayment(Payment payment);

    /**
     * 支払情報を追加する
     *
     * @param payment 支払情報エンティティ
     */
    void insertPayment(Payment payment);

    /**
     * 支払IDの最大値を取得する
     *
     * @return 最大支払ID
     */
    String getMaxPaymentID();
    List<PaymentListFormBean> getConsolidatedPayments(@Param("paymentMonth") String paymentMonth);

}