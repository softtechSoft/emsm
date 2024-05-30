package com.softtech.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.entity.Employee;
import com.softtech.entity.Yukyu;

@Mapper
public interface YukyuMapper {
    List<Yukyu> getAllYukyu();

	List<Yukyu> getEmployeeID(String employeeID);



	List<Yukyu> findIDnendo(Map<String, String> map);

	int update(Yukyu yukyu);

	List<Employee> getEmployeeName();


}