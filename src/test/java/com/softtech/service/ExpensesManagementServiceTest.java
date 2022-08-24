package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import com.softtech.actionForm.ExpensesManagementBean;
import com.softtech.entity.ExpensesManagementEntity;
import com.softtech.mappers.ExpensesManagementMapper;



public class ExpensesManagementServiceTest {

	@InjectMocks
    private ExpensesManagementService target;



	@Mock
	private ExpensesManagementMapper expensesManagementMapper;

	@Mock
	private ExpensesManagementEntity expensesManagementEntity;

	@Spy
	List<ExpensesManagementEntity> eme = new ArrayList<>();


	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}
	@Test
	public void testExpensesManagement() {
		Mockito.when(expensesManagementMapper.getInsertExpensesManagement(Mockito.anyString())).thenReturn(insertExpensesManagement);


		List<ExpensesManagementEntity>  insertExpensesManagement1 = target.getInsertExpensesManagement(Mockito.anyString());
		Assertions.assertEquals(insertExpensesManagement, insertExpensesManagement1);
		/*
		 * ①　対象関数のパラメータ準備
		 * ②　対象関数を呼び出す
		 * ③　②の戻り値の正しさを確認する
		 *
		 */

	}

	@Test
    void transforDBToUI() {
        //foreach循环遍历将从DB取出来的数据，一个Entity的List转换成EntityBean，然后再赋给画面需要的那个Bean
        //input　パラメータを準備
		List<ExpensesManagementEntity> expensesManagementEntity1 = new ArrayList<>();
		ExpensesManagementEntity testEntity = new ExpensesManagementEntity();
		expensesManagementEntity1.add(testEntity);
		expensesManagementEntity1.forEach(expensesManagementEntity -> {
			expensesManagementEntity.setAccrualDate("20220512");
			expensesManagementEntity.setTantouName("社員１");
			expensesManagementEntity.setCost("500");
			expensesManagementEntity.setExpensesType("種別");
			expensesManagementEntity.setHappenAddress("豊島区池袋");
			expensesManagementEntity.setConfirmStaus("承認");
			expensesManagementEntity.setStmtlDate("20220412");
			expensesManagementEntity.setStmtlStaus("精算");
			expensesManagementEntity.setRemark("chitone");//expensesManagementEntity.setPaymentType("口座");
        });

        ExpensesManagementBean expensesManagementBean = new ExpensesManagementBean();
        expensesManagementBean.setAccrualDate("20220512");
        expensesManagementBean.setTantouName("社員１");
        expensesManagementBean.setCost("500");
		expensesManagementBean.setExpensesType("種別");
		expensesManagementBean.setHappenAddress("豊島区池袋");
		expensesManagementBean.setConfirmStaus("承認");
		expensesManagementBean.setStmtlDate("20220412");
		expensesManagementBean.setStmtlStaus("精算");
		expensesManagementBean.setRemark("chitone");
	
        // 比較用クラスと戻り値と比較
        Assertions.assertEquals(expensesManagementBean.toString(),
                target.transforDBToUI(expensesManagementEntity1).toString());
    }


	@Test
    void insertExpensesManagement() {
    }

	@Test
    void tranferBeanToEntity() {
    }



}
