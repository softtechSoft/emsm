package com.softtech.service;

import java.util.List;

import com.softtech.actionForm.Yukyu;

public interface YukyuService {


	List<Yukyu> getAllYukyu();


	List<Yukyu> getEmployeeID(String employeeID);


	List<Yukyu> getEmployee();


	void update(Yukyu yukyu);



}

