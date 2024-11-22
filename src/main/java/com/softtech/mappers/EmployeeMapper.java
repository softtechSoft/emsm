package com.softtech.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.Employee;

@Mapper
public interface EmployeeMapper {
    List<Employee> findAll();
    Employee findById(String employeeID);
    List<Employee> queryEmployee();
    int insert(Employee employee);
    int update(Employee employee);
    int deleteById(String employeeID);

    List<Map<String, Object>> queryEmployeeAndFileStatus(@Param("currentYear") int currentYear);
    
    List<Map<String, Object>> queryEmployeeAndAdjustmentStatuses(@Param("currentYear") int currentYear);

    String findEmployeeIdByEmail(String email);
}
