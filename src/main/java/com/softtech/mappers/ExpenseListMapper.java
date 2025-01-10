package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.ExpenseListEntity;

@Mapper
public interface ExpenseListMapper {

    // 年/月で検索
    List<ExpenseListEntity> findByYearMonth(@Param("year") int year,
                                           @Param("month") int month);

    // IDで単一検索
    ExpenseListEntity findById(@Param("expensesID") String expensesID);

    // 論理削除：deleteFlg = '1'
    void deleteById(@Param("expensesID") String expensesID);

    // 更新
    void update(ExpenseListEntity entity);

    // 新しいレコードを挿入
    void insert(ExpenseListEntity expense);
}
