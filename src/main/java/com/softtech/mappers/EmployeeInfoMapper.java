package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.Employee;
import com.softtech.entity.EmployeeInfoEntity;


@Mapper
public interface EmployeeInfoMapper {

    //検索用のquery,社員IDにより、検索する
    List<EmployeeInfoEntity> getEmployeeID(@Param("employeeID") String employeeID) ;
    List<EmployeeInfoEntity> getEmployeeIDAll(@Param("employeeID") String employeeID) ;
    //更新画面への検索,表示される画面の所得税IDにより、検索する
     List<EmployeeInfoEntity> getUpdateEmployeeByEmployeeID(@Param("employeeID") String employeeID) ;
    //最大のIncomeTaxIDを取得、+1用
     String getMaxEmployeeID() ;
    // update
     void updateEmployeeInfo(EmployeeInfoEntity employeeInfoEntity) ;
    // insert
     void insertEmployeeInfo(EmployeeInfoEntity employeeInfoEntity) ;
    //DBから社員リスト生成
     List<Employee> getEmployee();
	//List<EmployeeInfoEntity> getEmployeeID(String employeeID);

}
