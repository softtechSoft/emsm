package com.softtech.service;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.WelfareBean;
import com.softtech.entity.WelfareInfo;
import com.softtech.mappers.WelfareInfoMapper;
import com.softtech.util.DateUtil;

/**
 * 概要：福祉情報作成のサービスクラス
 *
 * 作成者：馬＠ソフトテク
 * 作成日：2021/06/30
 */
@Service
public class WelfareInfoService {
    @Autowired
    WelfareInfoMapper welfareInfoMapper;

    /*
     * 機能概要： 社員情報を取得する機能
     *           検索条件：社員ID
     *
     * @param  employeeID 検索パラメータ
     * @return 作成用社員情報
     */
    public WelfareBean queryEmployee(String employeeID) {
        // DBから作成用社員情報を取得する
        WelfareInfo welfareInfoEmployee = welfareInfoMapper.getEmployeeDetail(employeeID);

        // DBから取得したデータを画面データに変換する
        WelfareBean welfareBean = new WelfareBean();

        // 社員ID
        welfareBean.setEmployeeID(welfareInfoEmployee.getEmployeeID());
        // 社員氏名
        welfareBean.setEmployeeName(welfareInfoEmployee.getEmployeeName());

        return welfareBean;
    }

    /*
     * 機能概要： 福祉情報を変更する機能
     *
     * @param  welfareBean 画面からのデータ
     * @param  session HTTPセッション
     * @return 変更後の福祉情報
     */
    public WelfareInfo changeWelfare(WelfareBean welfareBean, HttpSession session) {
        // ログイン中のユーザーIDを取得
        String loginUserID = String.valueOf(session.getAttribute("loginUserID"));
        WelfareInfo welfareInfo = new WelfareInfo();

        // 社員ID
        welfareInfo.setEmployeeID(welfareBean.getEmployeeID());

        // 控除開始日
        welfareInfo.setStartDate(DateUtil.chgMonthToYM(welfareBean.getStartDate()));

        // 基本給のバリデーションと設定
        if (isValidNumber(welfareBean.getBase())) {
            welfareInfo.setBase(Integer.parseInt(DateUtil.chgMonthToYM1(welfareBean.getBase())));
        } else {
            welfareInfo.setBase(0); // 不正な値の場合は0に設定
        }

        // その他の項目も同様に処理
        // 福祉年金自己負担額
        if (isValidNumber(welfareBean.getWelfarePensionSelf())) {
            welfareInfo.setWelfarePensionSelf(Integer.parseInt(welfareBean.getWelfarePensionSelf()));
        }

        // 福祉年金会社負担額
        if (isValidNumber(welfareBean.getWelfarePensionComp())) {
            welfareInfo.setWelfarePensionComp(Integer.parseInt(welfareBean.getWelfarePensionComp()));
        }

        // 健康保険会社負担額
        if (isValidNumber(welfareBean.getWelfareHealthComp())) {
            welfareInfo.setWelfareHealthComp(Integer.parseInt(welfareBean.getWelfareHealthComp()));
        }

        // 健康保険自己負担額
        if (isValidNumber(welfareBean.getWelfareHealthSelf())) {
            welfareInfo.setWelfareHealthSelf(Integer.parseInt(welfareBean.getWelfareHealthSelf()));
        }

        // 育児保険
        if (isValidNumber(welfareBean.getWelfareBaby())) {
            welfareInfo.setWelfareBaby(Integer.parseInt(welfareBean.getWelfareBaby()));
        }

        // 雇用保険自己負担額
        if (isValidNumber(welfareBean.getEplyInsSelf())) {
            welfareInfo.setEplyInsSelf(Integer.parseInt(DateUtil.chgMonthToYM1(welfareBean.getEplyInsSelf())));
        }

        // 雇用保険会社負担額
        if (isValidNumber(welfareBean.getEplyInsComp())) {
            welfareInfo.setEplyInsComp(Integer.parseInt(DateUtil.chgMonthToYM1(welfareBean.getEplyInsComp())));
        }

        // 雇用保険控除額
        if (isValidNumber(welfareBean.getEplyInsWithdraw())) {
            welfareInfo.setEplyInsWithdraw(Integer.parseInt(DateUtil.chgMonthToYM1(welfareBean.getEplyInsWithdraw())));
        }

        // 労災保険
        if (isValidNumber(welfareBean.getWkAcccpsIns())) {
            welfareInfo.setWkAcccpsIns(Integer.parseInt(DateUtil.chgMonthToYM1(welfareBean.getWkAcccpsIns())));
        }

        // 源泉徴収税
        if (isValidNumber(welfareBean.getWithholdingTax())) {
            welfareInfo.setWithholdingTax(Integer.parseInt(welfareBean.getWithholdingTax()));
        }

        // 市町村税1
        if (isValidNumber(welfareBean.getMunicipalTax1())) {
            welfareInfo.setMunicipalTax1(Integer.parseInt(welfareBean.getMunicipalTax1()));
        }

        // 市町村税2〜12もここで同様に処理を追加

        // 家賃控除
        if (isValidNumber(welfareBean.getRental())) {
            welfareInfo.setRental(Integer.parseInt(welfareBean.getRental()));
        }

        // 家賃管理費控除
        if (isValidNumber(welfareBean.getRentalMgmtFee())) {
            welfareInfo.setRentalMgmtFee(Integer.parseInt(welfareBean.getRentalMgmtFee()));
        }

        // 控除ステータス
        welfareInfo.setStatus(welfareBean.getStatus());

        // 登録日
        welfareInfo.setInsertDate(DateUtil.chgMonthToYM(welfareBean.getInsertDate()));

        // 登録社員（ログインユーザー）
        welfareInfo.setInsertEmployee(loginUserID);

        // 更新日
        welfareInfo.setUpdateDate(DateUtil.chgMonthToYM(welfareBean.getUpdateDate()));

        // 更新社員（ログインユーザー）
        welfareInfo.setUpdateEmployee(loginUserID);

        return welfareInfo;
    }

    // 文字列が有効な数値かどうかを確認するユーティリティメソッド
    private boolean isValidNumber(String str) {
        if (str == null || str.trim().isEmpty()) {
            return false;
        }
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /*
     * 機能概要： DBに福祉情報を作成および更新する機能
     *
     * @param  welfareInfo 変更後の福祉情報
     * @return なし
     */
    public void makeWelfare(WelfareInfo welfareInfo) {
        // DBに福祉情報を作成および更新
        welfareInfoMapper.makeWelfare(welfareInfo);
    }
}