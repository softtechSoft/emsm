package com.softtech.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import com.softtech.mappers.EmployeeInfoMapper;

public class EmployeeInfoServiceImplTest {

    @InjectMocks
    private EmployeeInfoServiceImpl target;

    @Mock
    private EmployeeInfoMapper employeeInfoMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }



    @Test
    public void testGetNextEmployeeID() {
        // モック
        Mockito.when(employeeInfoMapper.getMaxEmployeeID()).thenReturn("E0001");

        // 実行
        String result = target.getNextEmployeeID();

        // 結果確認
        assertThat(result).isEqualTo("E00002");
    }
}

