package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import com.softtech.entity.EmployeeInfoEntity;


class EmplyeeInfoServiceImplTest2 {
    @InjectMocks
    private EmployeeInfoServiceImpl target;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
	void changeSex() {
		//Mockito.when(changeSex(employeeInfoMapper.getEmployeeIDAll()))
		List<EmployeeInfoEntity> employeeInfoEntity=new ArrayList<>();
		EmployeeInfoEntity employeeInfoEntity1 = new EmployeeInfoEntity();
		employeeInfoEntity1.setSex("1");
		employeeInfoEntity.add(employeeInfoEntity1);

		EmployeeInfoEntity employeeInfoEntity2 = new EmployeeInfoEntity();
		employeeInfoEntity2.setSex("0");
		employeeInfoEntity.add(employeeInfoEntity2);

		List<EmployeeInfoEntity> employeeInfoEntityRtn = target.changeSex(employeeInfoEntity);

		EmployeeInfoEntity employeeInfoEntityrtn1 = new EmployeeInfoEntity();
		EmployeeInfoEntity employeeInfoEntityrtn2 = new EmployeeInfoEntity();
		int i = 0;
		for(EmployeeInfoEntity rtn:employeeInfoEntityRtn) {
			if (i==0) {
				employeeInfoEntityrtn1=rtn;
			} else {

				employeeInfoEntityrtn2=rtn;
			}
			i++;
		}

		Assertions.assertEquals(employeeInfoEntityrtn1.getSex(),"男");
		Assertions.assertEquals(employeeInfoEntityrtn2.getSex(),"女");
	}

}