package com.softtech.service;

import java.util.List;
import java.util.Map;

import com.softtech.actionForm.YukyuFormBean;
import com.softtech.entity.Yukyu;

public interface YukyuService {


	List<Yukyu> getAllYukyu();


	List<Yukyu> getEmployeeID(String employeeID);


	List<Yukyu> getEmployee();


//	void update(YukyuFormBean yukyuFormBean);


	Map<String, String> transferUIToMap(YukyuFormBean yukyuFormBean);
	YukyuFormBean findIDnendo(Map<String, String> map);

	YukyuFormBean transforEntityToUI(List<Yukyu> eList);


	boolean updateYk(YukyuFormBean yukyuFormBean);



}

