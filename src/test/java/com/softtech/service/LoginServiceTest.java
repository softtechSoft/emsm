package com.softtech.service;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.softtech.actionForm.LoginBean;
import com.softtech.common.LoginEmployee;
import com.softtech.mappers.LoginMappers;

public class LoginServiceTest {

	@InjectMocks
	private LoginService target;
	
	@Mock
	private LoginMappers loginMappers;
	
	@SuppressWarnings("deprecation")
	@BeforeEach
	public void setup() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testDoLogin01() {
		
		// モック
		Mockito.when(loginMappers.getOldPassword(Mockito.any(LoginEmployee.class))).thenReturn(null);
		
		// 前提条件確認
		LoginBean loginBean = new LoginBean();

		LoginEmployee em = new LoginEmployee();
		em.setEmployeeID(loginBean.getEmployeeID());
		em.setPassword(loginBean.getPassword());
		assertThat(loginMappers.getOldPassword(em))
			.as("【条件】loginMappers.getOldPasswordの戻り値がnull")
			.isNull();
		
		//実行
		boolean result = target.doLogin(loginBean);
		
		// 結果確認
		assertThat(result)
			.as("【確認】戻り値がfalseであること。")
			.isEqualTo(false);
		
	}
}
