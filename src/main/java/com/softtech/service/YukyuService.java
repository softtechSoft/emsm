package com.softtech.service;

import java.util.List;
import java.util.Map;

import com.softtech.actionForm.YukyuFormBean;
import com.softtech.entity.Employee;
import com.softtech.entity.Yukyu;

public interface YukyuService {

	
	List<Yukyu> getAllYukyu();

	List<Yukyu> getEmployeeID(String employeeID);

	List<Yukyu> findIDnendo(Map<String, String> map);

	List<Employee> getEmployeeName();


//	void update(YukyuFormBean yukyuFormBean);


	Map<String, String> transferUIToMap(YukyuFormBean yukyuFormBean);


	YukyuFormBean transforEntityToUI(List<Yukyu> eList);


	boolean updateYk(Yukyu yukyu);


	Yukyu transforFormBeanToEntity(YukyuFormBean yukyuFormBean);



}

