package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.Employee;
import com.softtech.entity.EmployeeInfoEntity;

@Mapper
public interface EmployeeInfoMapper {

	//DBから社員リスト生成
    List<Employee> getEmployee();

    //検索用のquery,社員IDにより、検索する
	List<EmployeeInfoEntity> getEmployeeID(Object employeeID);

    //全量検索する
    List<EmployeeInfoEntity> getEmployeeAll() ;

    //更新画面への検索,表示される画面の社員IDにより、検索する
     List<EmployeeInfoEntity> getUpdateEmployeeByEmployeeID(@Param("employeeID") String employeeID) ;

    //最大のEmployeeIDを取得、+1用
     String getMaxEmployeeID();

    // update
     void updateEmployeeInfo(EmployeeInfoEntity employeeInfoEntity) ;

    // insert
     void insertEmployeeInfo(EmployeeInfoEntity employeeInfoEntity) ;

}
