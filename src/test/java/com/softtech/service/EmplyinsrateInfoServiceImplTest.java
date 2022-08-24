package com.softtech.service;

import com.softtech.entity.EmplyinsrateInfoEntity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;

import com.softtech.mappers.EmplyinsrateMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.testcontainers.shaded.com.google.common.base.Verify;
import java.util.ArrayList;
import java.util.List;


/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-23
 * @return:
 */
class EmplyinsrateInfoServiceImplTest {

    @InjectMocks
    private EmplyinsrateInfoServiceImpl target;

    @Mock
    private EmplyinsrateMapper emplyinsrateMapper;
    @Mock
    private EmplyinsrateInfoEntity emplyinsrateInfoEntity;

    @Spy
    List<EmplyinsrateInfoEntity> emplyinsrateInfoByYear = new ArrayList<>();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getEmplyinsrateInfoByYear() {
        //设定select的year是any，打桩返回一个List
        Mockito.when(emplyinsrateMapper.getEmplyinsrateInfoByYear(Mockito.anyString())).thenReturn(emplyinsrateInfoByYear);
        //mock的mapper执行这个select方法，得到返回值一个List
        List<EmplyinsrateInfoEntity> emplyinsrateInfoByYear1 = target.getEmplyinsrateInfoByYear(Mockito.anyString());
        //断言返回结果就是这个List
        Assertions.assertEquals(emplyinsrateInfoByYear, emplyinsrateInfoByYear1);

    }

    @Test
    void getUpdateEmplyinsrateInfoList() {
    }

    @Test
    void transforEntityToUI() {
    }

    @Test
    void getNextEmplyinsrateID() {
    }

    @Test
    void updateEmplyinsrateInfo() {
    }

    @Test
    void insertEmplyinsrateInfo() {
    }

    @Test
    void getYear() {
    }
}