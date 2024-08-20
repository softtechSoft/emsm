package com.softtech.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.softtech.actionForm.TblJournalDetailFormBean;
import com.softtech.entity.TblAccount;
import com.softtech.entity.TblJournalDetail;

public interface TblJournalDetailService {

    // すべての仕訳明細を取得するメソッド
    List<TblJournalDetail> getAllJournalDetails();

    // 新しい仕訳明細を追加するメソッド


    // 既存の仕訳明細を更新するメソッド
    void updateJournalDetail(TblJournalDetail journalDetail);

    // 行番号によって仕訳明細を取得するメソッド
    TblJournalDetail getJournalDetailByLineNumber(int lineNumber);

    // すべての勘定科目を取得するメソッド
    List<TblAccount> getAllAccounts();
    int getNextLineNumber();

    @Transactional
    public void addJournal(TblJournalDetailFormBean tblJournalDetailFormBean) ;
    int getMaxLineNumber();
}

