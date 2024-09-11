package com.softtech.service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.TblJournalDetailFormBean;
import com.softtech.entity.TblAccount;
import com.softtech.entity.TblJournalDetailEntity;
import com.softtech.mappers.TblJournalDetailMapper;

@Service
public class TblJournalDetailServiceImpl implements TblJournalDetailService {

    @Autowired
    private TblJournalDetailMapper journalDetailMapper;

    // すべての仕訳明細を取得する
    @Override
    public List<TblJournalDetailFormBean> getAllJournalDetails() {
        // マッパーからすべての仕訳明細を取得
        List<TblJournalDetailEntity> journalDetails = journalDetailMapper.getAllJournalDetails();

        // lineNumber に基づいて仕訳明細をグループ化するためのマップ
        Map<String, TblJournalDetailFormBean> formBeanMap = new HashMap<>();

        // 仕訳明細リストをループして処理
        for (TblJournalDetailEntity entity : journalDetails) {
            String lineNumber = entity.getLineNumber() != null ? entity.getLineNumber().toString() : null;
            TblJournalDetailFormBean formBean = formBeanMap.get(lineNumber);

            if (formBean == null) {
                // マップに存在しない場合は新しい formBean を作成
                formBean = new TblJournalDetailFormBean();
                formBean.setLineNumber(lineNumber);
                formBeanMap.put(lineNumber, formBean);
            }

            // bookDate をフォーマットして設定
            if (entity.getBookDate() != null) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                String bookDateStr = entity.getBookDate().toLocalDateTime().format(formatter);
                formBean.setBookDate(bookDateStr);
            }

            // debit 情報をマージ
            if (entity.getCdDrCrKbn() == 1) { // Debit
                if (formBean.getdebitAccountTitleID() == null) {
                    formBean.setdebitAccountTitleID(entity.getAccountTitleID());
                }
                if (formBean.getdebitcdTaxationKbn() == null) {
                    formBean.setdebitcdTaxationKbn(entity.getCdTaxationKbn().toString());
                }
                if (formBean.getdebitcdCTaxPriceKbn() == null) {
                    formBean.setdebitcdCTaxPriceKbn(entity.getCdCTaxPriceKbn().toString());
                }
                if (formBean.getdebitTransValue() == null) {
                    formBean.setdebitTransValue(entity.getTransValue().toString());
                }
                if (formBean.getdebitDescription() == null) {
                    formBean.setdebitDescription(entity.getDescription());
                }
            }

            // credit 情報をマージ
            if (entity.getCdDrCrKbn() == 2) { // Credit
                if (formBean.getcreditAccountTitleID() == null) {
                    formBean.setcreditAccountTitleID(entity.getAccountTitleID());
                }
                if (formBean.getcreditcdTaxationKbn() == null) {
                    formBean.setcreditcdTaxationKbn(entity.getCdTaxationKbn().toString());
                }
                if (formBean.getcreditcdCTaxPriceKbn() == null) {
                    formBean.setcreditcdCTaxPriceKbn(entity.getCdCTaxPriceKbn().toString());
                }
                if (formBean.getcreditTransValue() == null) {
                    formBean.setcreditTransValue(entity.getTransValue().toString());
                }
                if (formBean.getcreditDescription() == null) {
                    formBean.setcreditDescription(entity.getDescription());
                }
            }
        }

        // マップをリストに変換して返す
        return new ArrayList<>(formBeanMap.values());
    }

    // 仕訳明細を更新する
    @Override
    public void updateJournalDetail(TblJournalDetailEntity journalDetail) {
        journalDetailMapper.updateJournalDetail(journalDetail);
    }

    // 行番号による仕訳明細の取得（実装が必要）
    @Override
    public TblJournalDetailEntity getJournalDetailByLineNumber(int lineNumber) {
        return null; // 実装が必要
    }

    // すべてのアカウントを取得する
    @Override
    public List<TblAccount> getAllAccounts() {
        return journalDetailMapper.getAllAccounts(); // 実装
    }

    // 次の行番号を取得する
    @Override
    public int getNextLineNumber() {
        Integer maxLineNumber = journalDetailMapper.getMaxLineNumber();
        return (maxLineNumber != null) ? maxLineNumber : 0;
    }

    // 最大行番号を取得する
    @Override
    public int getMaxLineNumber() {
        return journalDetailMapper.getMaxLineNumber();  // 実装
    }

    // formBean を TblJournalDetailEntity のリストに変換する
    public List<TblJournalDetailEntity> convertFormBeanToEntities(TblJournalDetailFormBean tblJournalDetailFormBean, Long maxUid) {
        List<TblJournalDetailEntity> journalDetails = new ArrayList<>();

        // データをカンマで分割して配列に変換
        String[] debitAccountTitleIDs = tblJournalDetailFormBean.getdebitAccountTitleID() != null ? tblJournalDetailFormBean.getdebitAccountTitleID().split(",") : new String[0];
        String[] creditAccountTitleIDs = tblJournalDetailFormBean.getcreditAccountTitleID() != null ? tblJournalDetailFormBean.getcreditAccountTitleID().split(",") : new String[0];
        String[] debitTransValues = tblJournalDetailFormBean.getdebitTransValue() != null ? tblJournalDetailFormBean.getdebitTransValue().split(",") : new String[0];
        String[] creditTransValues = tblJournalDetailFormBean.getcreditTransValue() != null ? tblJournalDetailFormBean.getcreditTransValue().split(",") : new String[0];
        String[] debitDescriptions = tblJournalDetailFormBean.getdebitDescription() != null ? tblJournalDetailFormBean.getdebitDescription().split(",") : new String[0];
        String[] creditDescriptions = tblJournalDetailFormBean.getcreditDescription() != null ? tblJournalDetailFormBean.getcreditDescription().split(",") : new String[0];
        String[] debitcdTaxationKbns = tblJournalDetailFormBean.getdebitcdTaxationKbn() != null ? tblJournalDetailFormBean.getdebitcdTaxationKbn().split(",") : new String[0];
        String[] creditcdTaxationKbns = tblJournalDetailFormBean.getcreditcdTaxationKbn() != null ? tblJournalDetailFormBean.getcreditcdTaxationKbn().split(",") : new String[0];
        String[] debitcdCTaxPriceKbns = tblJournalDetailFormBean.getdebitcdCTaxPriceKbn() != null ? tblJournalDetailFormBean.getdebitcdCTaxPriceKbn().split(",") : new String[0];
        String[] creditcdCTaxPriceKbns = tblJournalDetailFormBean.getcreditcdCTaxPriceKbn() != null ? tblJournalDetailFormBean.getcreditcdCTaxPriceKbn().split(",") : new String[0];
        String[] lineNumbers = tblJournalDetailFormBean.getLineNumber() != null ? tblJournalDetailFormBean.getLineNumber().split(",") : new String[0];

        // bookDate を Timestamp に変換
        String bookDateStr = tblJournalDetailFormBean.getBookDate();
        Timestamp bookDateTimestamp = null;
        if (bookDateStr != null) {
            // 小数点以下の部分がある場合は取り除く
            if (bookDateStr.contains(".")) {
                bookDateStr = bookDateStr.split("\\.")[0];
            }
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
            LocalDateTime localDateTime = LocalDateTime.parse(bookDateStr, formatter);
            bookDateTimestamp = Timestamp.valueOf(localDateTime);
        }

        int maxLength = Math.max(debitAccountTitleIDs.length, creditAccountTitleIDs.length);

        // Debit と Credit の詳細を TblJournalDetailEntity に変換
        for (int i = 0; i < maxLength; i++) {
            if (i < debitAccountTitleIDs.length) {
                TblJournalDetailEntity debitJournalDetail = new TblJournalDetailEntity();
                debitJournalDetail.setAccountTitleID(debitAccountTitleIDs[i]);
                if (i < debitTransValues.length && !debitTransValues[i].isEmpty()) {
                    debitJournalDetail.setTransValue(Integer.parseInt(debitTransValues[i]));
                } else {
                    debitJournalDetail.setTransValue(0);
                }
                debitJournalDetail.setDescription(i < debitDescriptions.length ? debitDescriptions[i] : "");
                debitJournalDetail.setCdDrCrKbn(1); // Debit
                if (i < debitcdTaxationKbns.length && !debitcdTaxationKbns[i].isEmpty()) {
                    debitJournalDetail.setCdTaxationKbn(Integer.parseInt(debitcdTaxationKbns[i]));
                } else {
                    debitJournalDetail.setCdTaxationKbn(0);
                }
                if (i < debitcdCTaxPriceKbns.length && !debitcdCTaxPriceKbns[i].isEmpty()) {
                    debitJournalDetail.setCdCTaxPriceKbn(Integer.parseInt(debitcdCTaxPriceKbns[i]));
                } else {
                    debitJournalDetail.setCdCTaxPriceKbn(0);
                }
                debitJournalDetail.setLineNumber(i < lineNumbers.length ? Integer.parseInt(lineNumbers[i]) : null);
                debitJournalDetail.setUid("UID" + maxUid++);
                debitJournalDetail.setBookDate(bookDateTimestamp);

                journalDetails.add(debitJournalDetail);
            }

            if (i < creditAccountTitleIDs.length) {
                TblJournalDetailEntity creditJournalDetail = new TblJournalDetailEntity();
                creditJournalDetail.setAccountTitleID(creditAccountTitleIDs[i]);
                if (i < creditTransValues.length && !creditTransValues[i].isEmpty()) {
                    creditJournalDetail.setTransValue(Integer.parseInt(creditTransValues[i]));
                } else {
                    creditJournalDetail.setTransValue(0);
                }
                creditJournalDetail.setDescription(i < creditDescriptions.length ? creditDescriptions[i] : "");
                creditJournalDetail.setCdDrCrKbn(2); // Credit
                if (i < creditcdTaxationKbns.length && !creditcdTaxationKbns[i].isEmpty()) {
                    creditJournalDetail.setCdTaxationKbn(Integer.parseInt(creditcdTaxationKbns[i]));
                } else {
                    creditJournalDetail.setCdTaxationKbn(0);
                }
                if (i < creditcdCTaxPriceKbns.length && !creditcdCTaxPriceKbns[i].isEmpty()) {
                    creditJournalDetail.setCdCTaxPriceKbn(Integer.parseInt(creditcdCTaxPriceKbns[i]));
                } else {
                    creditJournalDetail.setCdCTaxPriceKbn(0);
                }
                creditJournalDetail.setLineNumber(i < lineNumbers.length ? Integer.parseInt(lineNumbers[i]) : null);
                creditJournalDetail.setUid("UID" + maxUid++);
                creditJournalDetail.setBookDate(bookDateTimestamp);

                journalDetails.add(creditJournalDetail);
            }
        }

        return journalDetails;
    }

    // 仕訳明細を追加する
    public void addJournal(TblJournalDetailFormBean tblJournalDetailFormBean) {

        String maxUid = journalDetailMapper.getMaxUid();
        Long uidNumber;
        if (maxUid == null || maxUid.isEmpty()) {
            uidNumber = 1L;
        } else {
            try {
                // UID の数字部分を取り出してインクリメント
                String numericPart = maxUid.replaceAll("[^0-9]", "");
                uidNumber = Long.parseLong(numericPart);
                uidNumber++;
            } catch (NumberFormatException e) {
                // UID のフォーマットが不正な場合のエラーハンドリング
                throw new RuntimeException("Invalid UID format: " + maxUid, e);
            }
        }

        // formBean をエンティティのリストに変換
        List<TblJournalDetailEntity> journalDetails = convertFormBeanToEntities(tblJournalDetailFormBean, uidNumber);

        // 仕訳明細をデータベースに追加
        journalDetailMapper.insertJournalDetail(journalDetails);
    }
}
