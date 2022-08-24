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

import com.softtech.actionForm.ExpensesManagementBean;
import com.softtech.entity.Employee;
import com.softtech.entity.EmplyinsrateInfoEntity;
import com.softtech.entity.ExpensesManagementEntity;
import com.softtech.mappers.EmplyinsrateMapper;
import com.softtech.mappers.ExpensesManagementMapper;



public class ExpensesManagementServiceTest {

	@Spy
	private ExpensesManagementService expensesManagementService;
	@InjectMocks
    private ExpensesManagementService target;



	@Mock
	private ExpensesManagementMapper expensesManagementMapper;

	@Mock
	private ExpensesManagementEntity expensesManagementEntity;

	@Spy
	List<ExpensesManagementEntity> eme = new ArrayList<>();


	@Mock
    private EmplyinsrateMapper emplyinsrateMapper;
    @Mock
    private EmplyinsrateInfoEntity emplyinsrateInfoEntity;

    @Mock
    private ExpensesManagementBean expensesManagementBean;

    @Spy
    List<EmplyinsrateInfoEntity> emplyinsrateInfoByYear = new ArrayList<>();

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void insertExpensesManagement() {

		Mockito.verify(expensesManagementMapper).insertExpensesManagement(expensesManagementEntity);

		List<ExpensesManagementEntity>  insertExpensesManagement1 = expensesManagementService.tranferBeanToEntity(expensesManagementBean);
		Assertions.assertEquals(insertExpensesManagement, insertExpensesManagement1);
		/*
		 * ①　対象関数のパラメータ準備
		 * ②　対象関数を呼び出す
		 * ③　②の戻り値の正しさを確認する
		 *
		 */

	}

	@Test
    void transferDBTOUI() {
        //foreach循环遍历将从DB取出来的数据，一个Entity的List转换成EntityBean，然后再赋给画面需要的那个Bean
        //input　パラメータを準備
		List<Employee> employee1 = new ArrayList<>();
		Employee testEntity = new Employee();
		employee1.add(testEntity);
		employee1.forEach(employee -> {
			employee.setEmployeeID(employee.getEmployeeID());
			employee.setEmployeeName(employee.getEmployeeName());


        });

        ExpensesManagementBean expensesManagementBean = new ExpensesManagementBean();
        expensesManagementBean.setEmployeeID(expensesManagementBean.getEmployeeID());
        expensesManagementBean.setEmployeeName(expensesManagementBean.getEmployeeName());

        // 比較用クラスと戻り値と比較
        Assertions.assertEquals(expensesManagementBean.toString(),
                target.transferDBTOUI(employee1).toString());
    }



	@Test
    void tranferBeanToEntity() {
    }



}
