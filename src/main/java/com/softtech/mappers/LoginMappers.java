package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.common.BaseSalaryIDName;
import com.softtech.common.CompanyIDName;
import com.softtech.common.ContractIDName;
import com.softtech.common.EmployeeIDName;
import com.softtech.common.LoginEmployee;
import com.softtech.entity.LoginEntity;
/**
 *社員情報DAO
 *
 * @author softtech
 */
@Mapper
public interface LoginMappers {
	//LoginEntity getMailAdress(String mailAdress);
	LoginEntity getOldPassword(LoginEmployee em);
	LoginEntity getEmployeeByID(LoginEmployee em);
	//社員情報IDリストを取得する
	List<EmployeeIDName> getEmployees();
	//契約情報IDリストを取得する
	List<ContractIDName> getContracts();

	List<BaseSalaryIDName> getBaseSalarys();

	List<CompanyIDName> getCompanys();
}
