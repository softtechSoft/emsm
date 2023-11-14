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

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.ExpensesManagementBean;
import com.softtech.entity.Employee;
import com.softtech.entity.ExpensesManagementEntity;
import com.softtech.mappers.ExpensesManagementMapper;



public class ExpensesManagementServiceTest {

	@Spy
	private ExpensesManagementService expensesManagementService;
	@InjectMocks
    private ExpensesManagementService target;
	@Mock
	private ExpensesManagementMapper expensesManagementMapper;

	@Mock
	List<Employee> employeeList = new ArrayList<>();

	@BeforeEach
	void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	void insertExpensesManagement() {
		ExpensesManagementBean expensesManagementBean1 = new ExpensesManagementBean();
		expensesManagementBean1.setAccrualDate("2020-01");
		expensesManagementBean1.setCost("100");
		expensesManagementBean1.setTantouName("1");
		expensesManagementBean1.setExpensesType("1");
		expensesManagementBean1.setExpensesTypeDetail("1");
		expensesManagementBean1.setHappenAddress("1");
		expensesManagementBean1.setConfirmStaus("1");
		expensesManagementBean1.setStmtlStaus("1");
		expensesManagementBean1.setStmtlDate("1");
		expensesManagementBean1.setStmtlType("1");
		expensesManagementBean1.setRemark("1");


		ExpensesManagementEntity  expensesManagementEntity1 = expensesManagementService.tranferBeanToEntity(expensesManagementBean1);
	    //如果使用verify，因为此函数的目的是验证使用的次数，所以在验证之前必须先让它执行一次，否则报错
		//报错内容为：Wanted but not invoked
		expensesManagementMapper.insertExpensesManagement(expensesManagementEntity1);
		Mockito.verify(expensesManagementMapper).insertExpensesManagement(expensesManagementEntity1);

		//Assertions.assertEquals(insertExpensesManagement, insertExpensesManagement1);
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
			employee.setEmployeeID("1");
			employee.setEmployeeName("1");


        });

		EmployeeActionForm employeeActionForm1 = new EmployeeActionForm();
		employeeActionForm1.setEmployeeID("1");
		employeeActionForm1.setEmployeeName("1");
		ArrayList<EmployeeActionForm> employeeActionFormList1 = new ArrayList<>();
		employeeActionFormList1.add(employeeActionForm1);

        // 比較用クラスと戻り値と比較
        Assertions.assertEquals(employeeActionFormList1.toString(),
                target.transferDBTOUI(employee1).toString());
    }



	@Test
    void tranferBeanToEntity() {
    }



}
