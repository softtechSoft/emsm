package com.softtech.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.ExpenseTypeEntity;

/**
 * 経費種別マスターテーブルのデータベース操作を行うマッパーインターフェース。
 * MyBatisを使用してデータベースとのやり取りを行う。
 */
@Mapper
public interface ExpenseTypeMapper {

    /**
     * 全ての有効な経費種別を取得する（論理削除されていないもの）。
     * 
     * @return 有効な経費種別のリスト
     */
    List<ExpenseTypeEntity> findAllActive();

    /**
     * 指定された経費種別コードに該当する経費種別を取得する。
     * 
     * @param expensesType 経費種別コード
     * @return 該当する経費種別のリスト
     */
    List<ExpenseTypeEntity> findByExpensesType(@Param("expensesType") String expensesType);
    
    /**
     * 全ての経費種別カテゴリを取得する。
     * 
     * @return 経費種別カテゴリのリスト
     */
    List<Map<String, Object>> findAllCategories();
    
    /**
     * 経費項目の最大IDを取得する。
     * 
     * @return 最大ID値
     */
    Integer findMaxId();
    
    /**
     * 新しい経費項目を挿入する。
     * 
     * @param entity 経費項目エンティティ
     */
    void insert(ExpenseTypeEntity entity);
    
    /**
     * 経費項目を更新する。
     * 
     * @param params 更新パラメータ
     * @return 更新された行数
     */
    int updateItemById(Map<String, Object> params);
    
    /**
     * 経費項目を論理削除する。
     * 
     * @param params 削除パラメータ
     * @return 削除された行数
     */
    int deleteItemById(Map<String, Object> params);
    
    /**
     * 次の利用可能な経費種別IDを取得する。
     * 
     * @return 次の経費種別ID
     */
    String getNextCategoryId();
    
    /**
     * 新しい経費種別を挿入する。
     * 
     * @param params 挿入パラメータ
     */
    void insertCategory(Map<String, Object> params);
    
    /**
     * 経費種別を更新する。
     * 
     * @param params 更新パラメータ
     * @return 更新された行数
     */
    int updateCategory(Map<String, Object> params);
    
    /**
     * 指定された経費種別に属する全ての経費項目を論理削除する。
     * 
     * @param params 削除パラメータ
     */
    int deleteItemsByType(Map<String, Object> param);
    
    /**
     * 経費種別を論理削除する。
     * 
     * @param params 削除パラメータ
     * @return 削除された行数
     */
    int deleteCategory(Map<String, Object> params);
}