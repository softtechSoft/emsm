package com.softtech.mappers;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.ExpensesManagementEntity;
@Mapper
public interface ExpensesManagementMapper {
	public void insertExpensesManagement(ExpensesManagementEntity expensesManagementEntity);

}
