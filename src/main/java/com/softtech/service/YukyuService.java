package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.YukyuFormBean;
import com.softtech.entity.Yukyu;

public interface YukyuService {


	List<Yukyu> getAllYukyu();


	List<Yukyu> getEmployeeID(String employeeID);


	List<Yukyu> getEmployee();


	void update(YukyuFormBean yukyuFormBean);


	void save(YukyuFormBean yukyuFormBean);



}

