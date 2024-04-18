package com.softtech.mappers;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.softtech.actionForm.YukyuFormBean;
import com.softtech.entity.Yukyu;

@Mapper
public interface YukyuMapper {
    List<Yukyu> getAllYukyu();


	List<Yukyu> getEmployeeID(String employeeID);


	List<Yukyu> getEmployee();

	void update(YukyuFormBean yukyuFormBean);


	void save(YukyuFormBean yukyuFormBean);


}