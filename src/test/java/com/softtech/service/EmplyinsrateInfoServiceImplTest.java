package com.softtech.service;

import com.softtech.actionForm.EmplyinsrateInfoFormBean;
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
        //foreach循环遍历将从DB取出来的数据，一个Entity的List转换成EntityBean，然后再赋给画面需要的哪个Bean
        //　input　パラメータを準備
        List<EmplyinsrateInfoEntity> emplyinsrateInfoByYear = new ArrayList<>();
        emplyinsrateInfoByYear.forEach(emplyinsrateInfoEntity -> {
            emplyinsrateInfoEntity.setEmplyinsrateID("1");
            emplyinsrateInfoEntity.setYear("2020");
            emplyinsrateInfoEntity.setLaborBurdenRate("1");
            emplyinsrateInfoEntity.setEmployerBurdenRate("2");
            emplyinsrateInfoEntity.setEmploymentInsuranceRate("3");
            emplyinsrateInfoEntity.setIndustrialAccidentInsuranceRate("4");
            emplyinsrateInfoEntity.setLaborInsuranceRate("5");
            emplyinsrateInfoEntity.setContributionRate("6");
            emplyinsrateInfoEntity.setStatus("7");
            emplyinsrateInfoEntity.setUpdateDate("8");
            emplyinsrateInfoEntity.setInsertDate("9");
        });


        // 比較用クラスと戻り値と比較
        Assertions.assertNotNull(target.transforEntityToUI(emplyinsrateInfoByYear));
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