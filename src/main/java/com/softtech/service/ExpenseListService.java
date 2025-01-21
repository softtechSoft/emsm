package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.softtech.entity.ExpenseListEntity;
import com.softtech.mappers.ExpenseListMapper;

/**
 * 経費リストを管理するサービスクラス。
 * 経費の検索、取得、削除、更新、挿入の機能を提供する。
 */
@Service
public class ExpenseListService {

	@Autowired
	private ExpenseListMapper expenseListMapper;

	/**
	 * 指定された年と月に基づいて経費リストを取得する。
	 *
	 * @param year  年度
	 * @param month 月度
	 * @return 経費リスト
	 */
	public List<ExpenseListEntity> findExpensesByYearMonth(int year, int month) {
		return expenseListMapper.findByYearMonth(year, month);
	}

	/**
	 * 指定された経費IDに基づいて経費を取得する。
	 *
	 * @param expensesID 経費ID
	 * @return 経費エンティティ
	 */
	public ExpenseListEntity findById(String expensesID) {
		return expenseListMapper.findById(expensesID);
	}

	/**
	 * 指定された経費IDに基づいて経費を論理削除する。
	 *
	 * @param expensesID 削除対象の経費ID
	 */
	@Transactional
	public void deleteExpense(String expensesID) {
		expenseListMapper.deleteById(expensesID);
	}

	/**
	 * 指定された経費エンティティを更新する。
	 *
	 * @param expense 更新する経費エンティティ
	 */
	@Transactional
	public void updateExpense(ExpenseListEntity expense) {
		expenseListMapper.update(expense);
	}

	/**
	 * 新しい経費エンティティを挿入する。
	 *
	 * @param expense 挿入する経費エンティティ
	 */
	@Transactional
	public void insertExpense(ExpenseListEntity expense) {
		expenseListMapper.insert(expense);
	}
}
