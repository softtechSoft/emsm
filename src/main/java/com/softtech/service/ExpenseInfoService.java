package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.entity.ExpenseListEntity;

@Service
public class ExpenseInfoService {

    @Autowired
    private ExpenseListService expenseListService;

    /**
     * 複数の経費エンティティを一括でデータベースに挿入する。
     *
     * @param expenseList 挿入する経費エンティティのリスト
     */
    @Transactional
    public void addMultipleExpenses(List<ExpenseListEntity> expenseList) {
        // 経費リストがnullまたは空の場合は処理を中断
        if (expenseList == null || expenseList.isEmpty()) {
            return;
        }

        // 各経費エンティティをサービス層を通じて挿入
        for (ExpenseListEntity expense : expenseList) {
            expenseListService.insertExpense(expense);
        }
    }
}
