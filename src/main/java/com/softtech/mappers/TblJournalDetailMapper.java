package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.TblAccount;
import com.softtech.entity.TblJournalDetailEntity;

@Mapper
public interface TblJournalDetailMapper {

    // すべてのジャーナル詳細を取得する
    List<TblJournalDetailEntity> getAllJournalDetails();

    // 新しいジャーナル詳細を挿入する
    void insertJournalDetail(List<TblJournalDetailEntity> journalDetail);

    // ジャーナル詳細を更新する
    void updateJournalDetail(TblJournalDetailEntity journalDetail);

    // 行番号でジャーナル詳細を取得する
    TblJournalDetailEntity getJournalDetailByLineNumber(int lineNumber);

    // 最大の行番号を取得する
    Integer getMaxLineNumber();

    // すべての勘定科目を取得する
    List<TblAccount> getAllAccounts();
    String getMaxUid();
}
