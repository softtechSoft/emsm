package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.actionForm.Yukyu;

@Mapper
public interface YukyuMapper {
    List<Yukyu> getAllYukyu();


	List<Yukyu> getEmployeeID(String employeeID);


	List<Yukyu> getEmployee();

	void updateYukyu(Yukyu yukyu);


}