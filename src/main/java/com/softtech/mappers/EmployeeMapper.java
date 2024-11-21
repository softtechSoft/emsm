package com.softtech.mappers;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import com.softtech.entity.Employee;

@Mapper
public interface EmployeeMapper {
    List<Employee> findAll();
    Employee findById(String employeeID);
    List<Employee> queryEmployee();
    int insert(Employee employee);
    int update(Employee employee);
    int deleteById(String employeeID);
    
    @Select("SELECT e.employeeName, ad.adjustmentStatus " +
            "FROM employee e " +
            "LEFT JOIN adjustmentDetail ad ON e.employeeID = ad.employeeID")
    @Results({
        @Result(property = "employeeName", column = "employeeName"),
        @Result(property = "adjustmentStatus", column = "adjustmentStatus")
    })
    List<Map<String, Object>> queryEmployeeAndAdjustments();
    
    String findEmployeeIdByEmail(String email);

}
