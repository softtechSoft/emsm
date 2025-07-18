package com.softtech.mappers;

import com.softtech.entity.DpCompany;
import java.util.List;

/**
 * 会社管理用Service
 */
public interface DpCompanyMapper {
    /**
     * 全会社リスト取得
     * @return 会社リスト
     */
    List<DpCompany> getAllCompanies();

    /**
     * 会社IDで1件取得
     * @param companyId 会社ID
     * @return 会社情報
     */
    DpCompany getCompanyById(String companyId);

    /**
     * 新規登録
     * @param company 会社情報
     */
    void insertCompany(DpCompany company);

    /**
     * 更新
     * @param company 会社情報
     */
    void updateCompany(DpCompany company);

    /**
     * 削除
     * @param companyId 会社ID
     */
    void deleteCompany(String companyId);
} 
