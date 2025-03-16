package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.PaymentFormBean;
import com.softtech.actionForm.PaymentListFormBean;
import com.softtech.entity.Payment;
import com.softtech.mappers.PaymentMapper;
import com.softtech.util.DataUtil;

/**
 * 概要：支払情報処理サービス実装クラス
 *
 * @author グエンバンフン@ソフトテク
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    PaymentMapper paymentMapper;

    /**
     * 機能：支払情報リスト取得
     *
     * @return 支払情報リスト
     */
//    @Override
//    public List<PaymentListFormBean> queryPaymentInfo(String month) {
//    	 // YYYY/MM→yyyymmに変換
//		String monthP = DateUtil.chgMonthToYM(month);
//
//		List<Payment> paymentLst = paymentMapper.getPayment(monthP);
//
//		List<PaymentListFormBean> rtn
//        return paymentMapper.getPaymentList();
//    }

    /**
     * 機能：支払IDによる支払情報検索
     *
     * @param paymentID 支払ID
     * @return 支払情報リスト
     */


    /**
     * 機能：特定の支払情報取得
     *
     * @param paymentID 支払ID
     * @return 支払情報リスト
     */
    @Override
    public List<PaymentListFormBean> getPaymentList() {
        return paymentMapper.getAllPayments(); // 全支払情報を取得するためにマッパーを呼び出す
    }

    /**
     * 機能：支払情報更新
     *
     * @param paymentFormBean 支払情報フォーム
     * @return 更新成功フラグ
     */
    @Override
    public boolean updatePayment(PaymentFormBean paymentFormBean) {
        Payment paymentEntity = transferFormToEntity(paymentFormBean);
        paymentMapper.updatePayment(paymentEntity);
        return true;
    }

    /**
     * 機能：支払情報追加
     *
     * @param paymentFormBean 支払情報フォーム
     * @return 追加成功フラグ
     */
    @Override
    public boolean addPayment(PaymentFormBean paymentFormBean) {
        Payment paymentEntity = transferFormToEntity(paymentFormBean);
        paymentMapper.insertPayment(paymentEntity);
        return true;
    }

    /**
     * 機能：DB EntityからUI Formへデータ転送
     *
     * @param sList 支払情報エンティティリスト
     * @return 支払情報フォーム
     */
    @Override
    public PaymentFormBean transferEntityToUI(List<Payment> sList) {
        PaymentFormBean paymentFormBean = new PaymentFormBean();

        for (Payment payment : sList) {
            // 支払ID
            paymentFormBean.setPaymentID(payment.getPaymentID());
            // 対象月
            paymentFormBean.setPaymentMonth(payment.getPaymentMonth());
            // 会社名
            paymentFormBean.setCompanyID(payment.getCompanyID());
            // 社員名
            paymentFormBean.setPaymentEmployeeName(payment.getPaymentEmployeeName());
            // 基本金額
            paymentFormBean.setBasicAmount(payment.getBasicAmount());
            // 残業額
            paymentFormBean.setOvertimeAmount(payment.getOvertimeAmount());
            // 支払（予定）日
            paymentFormBean.setPaymentDate(payment.getPaymentDate());
        }

        return paymentFormBean;
    }

    /**
     * 機能：画面データをEntityクラスに設定する
     *
     * @param paymentFormBean 画面データ
     * @return Entityクラス
     */
    private Payment transferFormToEntity(PaymentFormBean paymentFormBean) {
        Payment paymentEntity = new Payment();

        // 支払ID
        paymentEntity.setPaymentID(paymentFormBean.getPaymentID());
        // 対象月
        paymentEntity.setPaymentMonth(paymentFormBean.getPaymentMonth());
        // 会社名
        paymentEntity.setCompanyID(paymentFormBean.getCompanyID());
        // 社員名
        paymentEntity.setPaymentEmployeeName(paymentFormBean.getPaymentEmployeeName());
        // 基本金額
        paymentEntity.setBasicAmount(paymentFormBean.getBasicAmount());
        // 残業額
        paymentEntity.setOvertimeAmount(paymentFormBean.getOvertimeAmount());
        // 支払（予定）日
        paymentEntity.setPaymentDate(paymentFormBean.getPaymentDate());

        return paymentEntity;
    }

    /**
     * 機能：支払情報の最大IDを取得する
     *
     * @return 最大ID
     */
    @Override
    public List<PaymentListFormBean> searchPaymentsByMonth(String paymentMonth) {
        return paymentMapper.searchPaymentsByMonth(paymentMonth);
    }

    /**
     * 機能：次の支払IDを取得
     *
     * @return 次の支払ID
     */
    @Override
    public String getNextPaymentID() {
        String maxPaymentID = paymentMapper.getMaxPaymentID();
        return DataUtil.getNextID(maxPaymentID, 2);


    }

	@Override
	public List<Payment> getPaymentInfo(String paymentID) {
		  List<Payment> payments = paymentMapper.getPaymentInfo(paymentID);
		return payments;
	}
	@Override
    public List<PaymentListFormBean> getConsolidatedPayments(String paymentMonth) {
        return paymentMapper.getConsolidatedPayments(paymentMonth);
    }


}