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
    // 既に ExpenseListService が存在し、insertExpense(...) メソッドが実装されている

    /**
     * 複数の経費を一括挿入
     */
    @Transactional
    public void addMultipleExpenses(List<ExpenseListEntity> expenseList) {
        if (expenseList == null || expenseList.isEmpty()) {
            return;
        }
        for (ExpenseListEntity expense : expenseList) {
            expenseListService.insertExpense(expense);
        }
    }
}
