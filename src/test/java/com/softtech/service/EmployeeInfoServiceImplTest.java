package com.softtech.service;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.softtech.entity.EmployeeInfoEntity;
import com.softtech.mappers.EmployeeInfoMapper;


class EmployeeInfoServiceImplTest {

	@InjectMocks
	private EmployeeInfoServiceImpl target;

	@Mock
	private EmployeeInfoMapper employeeInfoMapper;

	@Mock
	private EmployeeInfoEntity employeeInfoEntity;

	@Spy
	List<EmployeeInfoEntity> employeesPara = new ArrayList<>();
	List<EmployeeInfoEntity> employeesName = new ArrayList<>();
	List<EmployeeInfoEntity> employeesRtn =  new ArrayList<>();

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void changeSexTest() {
		//Mockito.when(changeSex(employeeInfoMapper.getEmployeeAll()))
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

	@Test
	void getEmployeeAllTest() {
		// パラメータの準備
		EmployeeInfoEntity employeeInfoEntity1 = new EmployeeInfoEntity();
		employeeInfoEntity1.setSex("1");
		employeeInfoEntity1.setEmployeeName("あいう");
		employeeInfoEntity1.setAddress("Tokyo");
		employeesPara.add(employeeInfoEntity1);

		EmployeeInfoEntity employeeInfoEntity2 = new EmployeeInfoEntity();
		employeeInfoEntity2.setSex("0");
		employeeInfoEntity2.setEmployeeName("かきく");
		employeeInfoEntity2.setAddress("Osaka");
		employeesPara.add(employeeInfoEntity2);

		//対象メソッドを呼び出し
		Mockito.when(employeeInfoMapper.getEmployeeAll()).thenReturn(employeesPara);
		List<EmployeeInfoEntity> employeesRtn=target.getEmployeeAll();

		//対象データ確認
		EmployeeInfoEntity employeeInfoEntityrtn1 = new EmployeeInfoEntity();
		EmployeeInfoEntity employeeInfoEntityrtn2 = new EmployeeInfoEntity();
		int i = 0;
		for(EmployeeInfoEntity rtn:employeesRtn) {
			if (i==0) {
				employeeInfoEntityrtn1=rtn;
			} else {

				employeeInfoEntityrtn2=rtn;
			}
			i++;
		}
		Assertions.assertEquals(employeeInfoEntityrtn1.getSex(),"男");
		Assertions.assertEquals(employeeInfoEntityrtn1.getEmployeeName(),"あいう");
		Assertions.assertEquals(employeeInfoEntityrtn1.getAddress(),"Tokyo");
		Assertions.assertEquals(employeeInfoEntityrtn2.getSex(),"女");
		Assertions.assertEquals(employeeInfoEntityrtn2.getEmployeeName(),"かきく");
		Assertions.assertEquals(employeeInfoEntityrtn2.getAddress(),"Osaka");
	}
}

