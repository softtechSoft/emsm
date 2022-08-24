package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.Employee;
import com.softtech.entity.ExpensesManagementEntity;


@Mapper
public interface ExpensesManagementMapper {

	public void insertExpensesManagement(ExpensesManagementEntity expensesManagementEntity);
	//画面データをDBデータ登録
    public void updateExpensesManagement(ExpensesManagementEntity expensesManagementEntity);

	public List<Employee> getEmployee();


}
