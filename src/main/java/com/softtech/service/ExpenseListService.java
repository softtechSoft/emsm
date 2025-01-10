package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.entity.ExpenseListEntity;
import com.softtech.mappers.ExpenseListMapper;

@Service
public class ExpenseListService {

    @Autowired
    private ExpenseListMapper expenseListMapper;

    public List<ExpenseListEntity> findExpensesByYearMonth(int year, int month) {
        return expenseListMapper.findByYearMonth(year, month);
    }

    public ExpenseListEntity findById(String expensesID) {
        return expenseListMapper.findById(expensesID);
    }

    @Transactional
    public void deleteExpense(String expensesID) {
        expenseListMapper.deleteById(expensesID);
    }

    @Transactional
    public void updateExpense(ExpenseListEntity expense) {
        expenseListMapper.update(expense);
    }

    @Transactional
    public void insertExpense(ExpenseListEntity expense) {
        expenseListMapper.insert(expense);
    }
}
