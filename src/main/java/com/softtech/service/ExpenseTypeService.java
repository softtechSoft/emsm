package com.softtech.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.entity.ExpenseTypeEntity;
import com.softtech.mappers.ExpenseTypeMapper;

/**
 * 经费种别关连のサービスクラス。
 * データアクセスと表示用のロジックを提供する。
 */
@Service
public class ExpenseTypeService {

    @Autowired
    private ExpenseTypeMapper expenseTypeMapper;

    /**
     * 全ての经费种别选项を取得する。
     * 
     * @return 经费种别选项のリスト
     */
    public List<Map<String, String>> getAllExpenseTypeOptions() {
        List<Map<String, String>> options = new ArrayList<>();
        
        // 经费种别のマスターデータ
        Map<String, String> typeMap = getexpensesTypeNameMap();
        
        // マスターデータからオプションリストを作成
        for (Map.Entry<String, String> entry : typeMap.entrySet()) {
            Map<String, String> option = new HashMap<>();
            option.put("typeCode", entry.getKey());
            option.put("typeName", entry.getValue());
            options.add(option);
        }
        
        return options;
    }

    /**
     * 经费种别名称のマップを取得する。
     * 
     * @return 经费种别代码と名称のマップ
     */
    private Map<String, String> getexpensesTypeNameMap() {
        Map<String, String> typeNameMap = new HashMap<>();
        
        List<Map<String, Object>> categoryList = expenseTypeMapper.findAllCategories();
        
        for (Map<String, Object> category : categoryList) {
            String typeCode = category.get("expensesType").toString();
            String typeName = category.get("expensesTypeName").toString();
            typeNameMap.put(typeCode, typeName);
        }
        
        return typeNameMap;
    }

    /**
     * 全ての有効な经费种别を种别ごとにグループ化して取得する。
     * 
     * @return 种别ごとにグループ化された经费种别のマップ
     */
    public Map<String, List<ExpenseTypeEntity>> getAllExpenseTypesByGroup() {
        // 全ての有効な经费种别を取得
        List<ExpenseTypeEntity> allExpenseTypes = expenseTypeMapper.findAllActive();
        
        return groupExpenseTypesByType(allExpenseTypes);
    }

    /**
     * 特定の种别に属する经费种别だけをグループ化して取得する。
     * 
     * @param expensesType 经费种别代码
     * @return 种别ごとにグループ化された经费种别のマップ
     */
    public Map<String, List<ExpenseTypeEntity>> getExpenseTypesByType(String expensesType) {
        // 特定の种别の经费种别を取得
        List<ExpenseTypeEntity> expenseTypes = expenseTypeMapper.findByExpensesType(expensesType);
        
        return groupExpenseTypesByType(expenseTypes);
    }

    /**
     * 经费种别リストを种别でグループ化する。
     * 
     * @param expenseTypes 经费种别リスト
     * @return 种别ごとにグループ化された经费种别のマップ
     */
    private Map<String, List<ExpenseTypeEntity>> groupExpenseTypesByType(List<ExpenseTypeEntity> expenseTypes) {
        Map<String, List<ExpenseTypeEntity>> groupedExpenseTypes = new LinkedHashMap<>();
        
        if (expenseTypes == null || expenseTypes.isEmpty()) {
            return groupedExpenseTypes;
        }
        
        // 经费种别でグループ化
        for (ExpenseTypeEntity expenseType : expenseTypes) {
            String typeCode = expenseType.getExpensesType();
            
            if (!groupedExpenseTypes.containsKey(typeCode)) {
                groupedExpenseTypes.put(typeCode, new ArrayList<>());
            }
            
            groupedExpenseTypes.get(typeCode).add(expenseType);
        }
        
        return groupedExpenseTypes;
    }
    
    /**
     * 经费种别の最大IDを取得する。
     * 
     * @return 最大ID値
     */
    public int getMaxExpenseId() {
        Integer maxId = expenseTypeMapper.findMaxId();
        return maxId != null ? maxId : 0;
    }
    
    /**
     * 新规经费项目を追加する。
     * 
     */
    @Transactional
    public ExpenseTypeEntity addExpenseItem(String expensesType, String expensesTypeName, String expenseName, String currentUser) {
        
        if (expensesType == null || expensesType.isEmpty()) {
            throw new IllegalArgumentException("经费种别代码は必須です。");
        }
        if (expensesTypeName == null || expensesTypeName.isEmpty()) {
            throw new IllegalArgumentException("经费种别名称は必須です。");
        }
        if (expenseName == null || expenseName.isEmpty()) {
            throw new IllegalArgumentException("经费名称は必須です。");
        }
        
        ExpenseTypeEntity entity = new ExpenseTypeEntity();
        entity.setExpensesType(expensesType);
        entity.setExpensesTypeName(expensesTypeName);
        entity.setExpenseName(expenseName);
        entity.setDeleteFlg("0");
        entity.setCreatedBy(currentUser);
        entity.setUpdatedBy(currentUser);
        entity.setInsertDate(LocalDateTime.now());
        entity.setUpdateDate(LocalDateTime.now());
        
        expenseTypeMapper.insert(entity);
        
        return entity;
    }
    
    /**
     * 经费项目を更新する。
     * 
     */
    @Transactional
    public void updateExpenseItem(Integer id, String expenseName, String currentUser) {
        // 入力検証
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("经费项目IDは必須です。");
        }
        if (expenseName == null || expenseName.isEmpty()) {
            throw new IllegalArgumentException("经费名称は必須です。");
        }
        
        // 更新情報を設定
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("expenseName", expenseName);
        params.put("updatedBy", currentUser);
        params.put("updateDate", LocalDateTime.now());
        
        // データベースを更新
        int updated = expenseTypeMapper.updateItemById(params);
        
        if (updated == 0) {
            throw new IllegalArgumentException("指定されたIDの经费项目が見つかりません。");
        }
    }
    
    /**
     * 经费项目を削除する（論理削除）。
     * 
     */
    @Transactional
    public void deleteExpenseItem(Integer id, String currentUser) {
        // 入力検証
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("经费项目IDは必須です。");
        }
        
        // 削除情報を設定
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        params.put("updatedBy", currentUser);
        params.put("updateDate", LocalDateTime.now());
        
        // データベースで論理削除
        int deleted = expenseTypeMapper.deleteItemById(params);
        
        if (deleted == 0) {
            throw new IllegalArgumentException("指定されたIDの经费项目が見つかりません。");
        }
    }
    
    /**
     * 新规经费种别を追加する。
     * 
     */
    @Transactional
    public String addExpenseCategory(String expenseName, String expenseItemName, String currentUser) {
        // 入力検証
        if (expenseName == null || expenseName.isEmpty()) {
            throw new IllegalArgumentException("经费种别名称は必須です。");
        }
        if (expenseItemName == null || expenseItemName.isEmpty()) {
            throw new IllegalArgumentException("经费名称は必須です。");
        }
        
        // 新しい经费种别IDを取得
        String newCategoryId = expenseTypeMapper.getNextCategoryId();
        
        // 新しいカテゴリ情報を設定
        Map<String, Object> params = new HashMap<>();
        params.put("expensesType", newCategoryId);
        params.put("expensesTypeName", expenseName);
        params.put("expenseName", expenseItemName);
        params.put("createdBy", currentUser);
        params.put("updatedBy", currentUser);
        params.put("insertDate", LocalDateTime.now());
        params.put("updateDate", LocalDateTime.now());
        
        expenseTypeMapper.insertCategory(params);
        
        return newCategoryId;
    }
    
    /**
     * 经费种别を更新する。
     * 
     */
    @Transactional
    public void updateExpenseCategory(Integer id, String expenseName, String currentUser) {
        // 入力検証
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("经费种别IDは必須です。");
        }
        if (expenseName == null || expenseName.isEmpty()) {
            throw new IllegalArgumentException("经费种别名称は必須です。");
        }
        
        // 更新情報を設定
        Map<String, Object> params = new HashMap<>();
        params.put("expensesType", id);
        params.put("expensesTypeName", expenseName);
        params.put("updatedBy", currentUser);
        params.put("updateDate", LocalDateTime.now());
        
        // データベースを更新
        int updated = expenseTypeMapper.updateCategory(params);
        
        if (updated == 0) {
            throw new IllegalArgumentException("指定されたIDの经费种别が見つかりません。");
        }
    }
    
    /**
     * 经费种别とそれに関連する经费项目を削除する。
     * 
     */
    @Transactional
    public void deleteExpenseCategory(Integer id, String currentUser) {
        // 入力検証
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("经费种别IDは必須です。");
        }

        Map<String, Object> param = new HashMap<>();
        param.put("expensesType", id.toString());
        param.put("updatedBy", currentUser);
        param.put("updateDate", LocalDateTime.now());

        int deleted = expenseTypeMapper.deleteItemsByType(param);

        if (deleted == 0) {
            throw new IllegalArgumentException("指定されたIDの经费种别が見つかりません");
        }
    }

}