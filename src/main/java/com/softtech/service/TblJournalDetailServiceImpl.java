package com.softtech.service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.TblJournalDetailFormBean;
import com.softtech.entity.TblAccount;
import com.softtech.entity.TblJournalDetail;
import com.softtech.mappers.TblJournalDetailMapper;

@Service
public class TblJournalDetailServiceImpl implements TblJournalDetailService {

    @Autowired
    private TblJournalDetailMapper journalDetailMapper;

    // すべての仕訳明細を取得する
    @Override
    public List<TblJournalDetail> getAllJournalDetails() {
        return journalDetailMapper.getAllJournalDetails();
    }

    // 仕訳明細を追加する
    @Override
    public void updateJournalDetail(TblJournalDetail journalDetail) {
        journalDetailMapper.updateJournalDetail(journalDetail);
    }

    // 行番号による仕訳明細の取得
    @Override
    public TblJournalDetail getJournalDetailByLineNumber(int lineNumber) {
        // 実装が必要
        return null;
    }

    // すべてのアカウントを取得する
    @Override
    public List<TblAccount> getAllAccounts() {
        // 実装が必要
        return journalDetailMapper.getAllAccounts();
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

    // 仕訳明細を追加する
    public void addJournal(TblJournalDetailFormBean tblJournalDetailFormBean) {
        List<TblJournalDetail> journalDetails = new ArrayList<>();

        // フォームビーンから文字列を分割する
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
        String[] uids = tblJournalDetailFormBean.getUid() != null ? tblJournalDetailFormBean.getUid().split(",") : new String[0];

        String bookDateStr = tblJournalDetailFormBean.getBookDate();
        Timestamp bookDateTimestamp = null;
        if (bookDateStr != null) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss.S");
            LocalDate localDate = LocalDate.parse(bookDateStr, formatter);
            bookDateTimestamp = Timestamp.valueOf(localDate.atStartOfDay());
        }

        // 各要素をループして `TblJournalDetail` オブジェクトを作成する
        int maxLength = Math.max(debitAccountTitleIDs.length, Math.max(creditAccountTitleIDs.length, Math.max(lineNumbers.length, uids.length)));

        for (int i = 0; i < maxLength; i++) {
            TblJournalDetail journalDetail = new TblJournalDetail();

            journalDetail.setdebitAccountTitleID(i < debitAccountTitleIDs.length ? debitAccountTitleIDs[i] : null);
            journalDetail.setcreditAccountTitleID(i < creditAccountTitleIDs.length ? creditAccountTitleIDs[i] : null);
            journalDetail.setdebitTransValue(i < debitTransValues.length ? debitTransValues[i] : null);
            journalDetail.setcreditTransValue(i < creditTransValues.length ? creditTransValues[i] : null);
            journalDetail.setdebitDescription(i < debitDescriptions.length ? debitDescriptions[i] : null);
            journalDetail.setcreditDescription(i < creditDescriptions.length ? creditDescriptions[i] : null);
            journalDetail.setdebitcdTaxationKbn(i < debitcdTaxationKbns.length ? debitcdTaxationKbns[i] : null);
            journalDetail.setcreditcdTaxationKbn(i < creditcdTaxationKbns.length ? creditcdTaxationKbns[i] : null);
            journalDetail.setdebitcdCTaxPriceKbn(i < debitcdCTaxPriceKbns.length ? debitcdCTaxPriceKbns[i] : null);
            journalDetail.setcreditcdCTaxPriceKbn(i < creditcdCTaxPriceKbns.length ? creditcdCTaxPriceKbns[i] : null);
            journalDetail.setLineNumber(i < lineNumbers.length ? lineNumbers[i] : null);
            journalDetail.setUid(i < uids.length ? uids[i] : null);
            journalDetail.setBookDate(bookDateTimestamp);

            journalDetails.add(journalDetail);
        }

        // 明細をデータベースに保存する
        journalDetailMapper.insertJournalDetail(journalDetails);
    }
}
