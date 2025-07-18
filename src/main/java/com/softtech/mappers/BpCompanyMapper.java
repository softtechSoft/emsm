package com.softtech.mappers;

import com.softtech.entity.BpCompany;
import java.util.List;

/**
 * BP会社管理用Mapper
 */
public interface BpCompanyMapper {
    /**
     * 全会社リスト取得
     * @return 会社リスト
     */
    List<BpCompany> getAllCompanies();

    /**
     * 会社IDで1件取得
     * @param companyId 会社ID
     * @return 会社信息
     */
    BpCompany getCompanyById(String companyId);

    /**
     * 新規登録
     * @param company 会社信息
     */
    void insertCompany(BpCompany company);

    /**
     * 更新
     * @param company 会社信息
     */
    void updateCompany(BpCompany company);

    /**
     * 削除
     * @param companyId 会社ID
     */
    void deleteCompany(String companyId);
} 