package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.softtech.entity.BaseSalaryInfoEntity;
import com.softtech.entity.EmplyinsrateInfoEntity;
import com.softtech.entity.IncomeTaxInfoEntity;
import com.softtech.entity.SalaryInfoEntity;
import com.softtech.entity.TransportEntity;
import com.softtech.entity.WelfareBabyInfoEntity;
import com.softtech.entity.WelfarefeeInfoEntity;
import com.softtech.entity.WorkInfo;

@Mapper
public interface SalarylistMapper {

	List<SalaryInfoEntity> getSalaryinfolist(String month);

	//年度最大値を取得
	String getMaxMonth();

	/**
	 * @return
	 */
	BaseSalaryInfoEntity getBaseSalary(@Param("employeeID") String employeeID,@Param("year") String year);

	String getPayMonth(String month);

	WorkInfo getWkTime(@Param("employeeID") String employeeID,@Param("month") String month);

	TransportEntity getTransportExpense(@Param("employeeID") String employeeID,@Param("month") String month) ;

	WelfarefeeInfoEntity getWfPension (@Param("year") String year);

	String getAge(String employeeID);

	WelfareBabyInfoEntity getWfBaby(@Param("year")String year);

	EmplyinsrateInfoEntity getEmplyinsrate(@Param("year")String year);

	IncomeTaxInfoEntity getTax(@Param("employeeID") String employeeID , @Param("year")String Year ) ;

	SalaryInfoEntity getDate() ;

	int insertSalaryList(SalaryInfoEntity salaryInfoEntity);

}
