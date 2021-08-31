package com.softtech.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.ExpensesEntity;

@Mapper
public interface ExpensesMapper {
	public void insertExpenses(ExpensesEntity expensesEntity);

}
