package com.softtech.mappers;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.BankAccount;

/**
 * 概要：銀行口座情報の検索、更新、登録
 */
@Mapper
public interface BankAccountMapper {

    // 日付範囲で口座情報を検索
    List<BankAccount> findByDateRange(@Param("startDate") LocalDate startDate,
                                      @Param("endDate") LocalDate endDate);

    // 出金総額
    BigDecimal sumWithdrawalByDateRange(@Param("startDate") LocalDate startDate,
                                        @Param("endDate") LocalDate endDate);

    // 入金総額
    BigDecimal sumDepositByDateRange(@Param("startDate") LocalDate startDate,
                                     @Param("endDate") LocalDate endDate);

    // 最新残高
    BigDecimal findLatestBalance(@Param("startDate") LocalDate startDate,
                                 @Param("endDate") LocalDate endDate);

    // ID 検索
    BankAccount findById(@Param("id") Long id);

    // 単件登録
    void insert(BankAccount account);

    // 一括登録
    void insertBatch(@Param("list") List<BankAccount> accounts);

    // 備考更新
    void updateRemarks(@Param("id") Long id, @Param("remarks") String remarks);

    // 重複存在チェック（組み合わせキー）
    int existsByUniqueKeys(@Param("transactionDate") LocalDate transactionDate,
                           @Param("transactionType") String transactionType,
                           @Param("withdrawal") BigDecimal withdrawal,
                           @Param("deposit") BigDecimal deposit,
                           @Param("description") String description);
}
