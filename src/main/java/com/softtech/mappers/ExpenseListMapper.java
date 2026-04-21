package com.softtech.mappers;

import java.time.LocalDate;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.ExpenseListEntity;

/**
 * 経費リストのデータベース操作を行うマッパーインターフェース。
 * MyBatisを使用してデータベースとのやり取りを行う。
 */
@Mapper
public interface ExpenseListMapper {

	/**
	 * 指定された年と月に基づいて経費リストを取得する。
	 *
	 * @param year  検索対象の年度
	 * @param month 検索対象の月度
	 * @return 指定された年と月に該当する経費リスト
	 */
	List<ExpenseListEntity> searchExpenses(
//			@Param("year") int year,
//			@Param("month") Integer month,
			@Param("fromDate") LocalDate fromDate,
		    @Param("toDate") LocalDate toDate,
			@Param("tantouName") String tantouName,
			@Param("expensesType") String expensesType,
			@Param("settlementStatus") String settlementStatus);

	/**
	 * 担当者プルダウン候補を取得する
	 *
	 * @return 担当者一覧
	 */
	List<String> findTantouCandidates();

	/**
	 * 経費種別プルダウン候補を取得する
	 *
	 * @return 経費種別一覧
	 */
	List<ExpenseListEntity> findExpenseTypeOptions();

	/**
	 * 指定された経費IDに基づいて単一の経費エンティティを取得する。
	 *
	 * @param expensesID 検索対象の経費ID
	 * @return 指定された経費IDに該当する経費エンティティ
	 */
	ExpenseListEntity findById(@Param("expensesID") String expensesID);

	/**
	 * 指定された経費IDに基づいて経費を論理削除する。
	 *
	 * @param expensesID 論理削除対象の経費ID
	 */
	void deleteById(@Param("expensesID") String expensesID);

	/**
	 * 指定された経費エンティティの情報を更新する。
	 *
	 * @param entity 更新する経費エンティティ
	 */
	void update(ExpenseListEntity entity);

	/**
	 * 新しい経費エンティティをデータベースに挿入する。
	 *
	 * @param expense 挿入する新しい経費エンティティ
	 */
	void insert(ExpenseListEntity expense);

	/**
	 * 最大値を取得。
	 *
	 * @param
	 */
	String getMaxExpensesID();
}
