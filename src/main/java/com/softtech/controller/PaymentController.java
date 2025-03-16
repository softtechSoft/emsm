package com.softtech.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.PaymentFormBean;
import com.softtech.actionForm.PaymentListFormBean;
import com.softtech.common.CompanyIDName;
import com.softtech.entity.Payment;
import com.softtech.service.LoginService;
import com.softtech.service.PaymentService;


/**
 * 支払情報管理コントローラー
 */
@Controller
public class PaymentController {
    static protected Logger logger = LogManager.getLogger(PaymentController.class);
    @Autowired
	LoginService loginService;
    // 支払情報サービス
    @Autowired
    PaymentService paymentService;

    /**
     * 機能概要：支払情報リストの初期表示
     *
     * @param model Modelオブジェクト
     * @return 支払情報一覧画面
     */
    @RequestMapping("/payment")
    public String showPayment(@ModelAttribute("paymentListFormBean") PaymentListFormBean formBean, Model model) {

        model.addAttribute("paymentListFormBean", formBean);

        return "paymentList";
    }// JSPファイル名を修正

    @RequestMapping("/paymentSearch")
    public String showPaymentList(@ModelAttribute("paymentListFormBean") PaymentListFormBean formBean, Model model) {
        String paymentMonth = formBean.getPaymentMonth();
        List<PaymentListFormBean> paymentList = null;

        boolean searchFlg = false;
        if (paymentMonth != null && !paymentMonth.isEmpty()) {
            // 検索が実行された場合
            paymentList = paymentService.searchPaymentsByMonth(paymentMonth);
            searchFlg = true;
        } else {
            // 検索が実行されない場合
            paymentList = new ArrayList<>(); // 空のリストを渡す
        }

        model.addAttribute("list", paymentList);
        model.addAttribute("paymentListFormBean", formBean);
        model.addAttribute("searchFlg", searchFlg);

        return "paymentList";
    }
    /**
     * 機能概要：支払情報の追加/編集画面の初期表示
     *
     * @param paymentFormBean フォームデータ
     * @param model Modelオブジェクト
     * @return 追加画面または編集画面
     */
    @RequestMapping("/toInitPaymentInfo")
    public String initPaymentInfo(@ModelAttribute("paymentFormBean") PaymentListFormBean paymentListFormBean, Model model) {
    	//会社IDリスト候補生成
    	List<CompanyIDName> companyList = loginService.getCompanyList();
    	model.addAttribute("companyList",companyList);
    	// 追加フラグを取得する
        String insertFlg = paymentListFormBean.getInsertFlg();
        if ("0".equals(insertFlg)) {
            // 新規追加の場合
            PaymentFormBean paymentFormBeans = new PaymentFormBean();
            String maxPaymentID = paymentService.getNextPaymentID();
            paymentFormBeans.setPaymentID(maxPaymentID);
            model.addAttribute("paymentFormBean", paymentFormBeans);
            return "paymentAdd";
        } else {
            // 更新の場合
            String paymentID = paymentListFormBean.getPaymentID();
            List<Payment> sList = paymentService.getPaymentInfo(paymentID);
            PaymentFormBean paymentFormBean1 = paymentService.transferEntityToUI(sList);
            model.addAttribute("paymentFormBean", paymentFormBean1);
            return "paymentEdit";
        }
    }

    /**
     * 機能概要：支払情報の追加処理
     *
     * @param paymentFormBean フォームデータ
     * @param result バリデーション結果
     * @param model Modelオブジェクト
     * @return 追加画面
     */
    @RequestMapping(value = "/paymentAdd", method = RequestMethod.POST)
    public String addPayment(@Valid @ModelAttribute("paymentFormBean") PaymentFormBean paymentFormBean,
                            BindingResult result, Model model) {
        // 入力チェック
        if (result.hasErrors()) {
            List<FieldError> errorList = new ArrayList<>();
            errorList.addAll(result.getFieldErrors());
            model.addAttribute("errors", errorList);
            model.addAttribute("paymentFormBean", paymentFormBean);
            return "paymentAdd";
        }


        try {
            // 支払情報を追加する
            paymentService.addPayment(paymentFormBean);
            model.addAttribute("message", "登録が完了しました");

            return "paymentAdd";
        } catch (Exception e) {
            model.addAttribute("errors", "支払情報の追加中にエラーが発生しました: " + e.getMessage());
            return "paymentAdd";
        }

    }

    /**
     * 機能概要：支払情報の更新処理
     *
     * @param paymentFormBean フォームデータ
     * @param result バリデーション結果
     * @param model Modelオブジェクト
     * @return 編集画面
     */
    @RequestMapping(value = "/paymentUpdate", method = RequestMethod.POST)
    public String updatePayment(@Valid @ModelAttribute("paymentFormBean") PaymentFormBean paymentFormBean,
                                BindingResult result, Model model) {
        // 入力チェック
        if (result.hasErrors()) {
            model.addAttribute("errors", result.getFieldErrors());
            return "paymentEdit";
        }
        // 支払情報を更新する
        boolean updateSuccess = paymentService.updatePayment(paymentFormBean);
        if (updateSuccess) {
            model.addAttribute("successMessage", "更新が成功しました");
        } else {
            model.addAttribute("successMessage", "更新が失敗しました");
        }

        return "paymentEdit";
}
}