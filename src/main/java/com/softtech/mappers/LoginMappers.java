package com.softtech.mappers;

import org.apache.ibatis.annotations.Mapper;

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
}
