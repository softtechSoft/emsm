package com.softtech.mappers;

import java.util.List;

import com.softtech.common.*;
import org.apache.ibatis.annotations.Mapper;

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

	//選択枠
	List<WelfarefeeIDName> getYear();
}
