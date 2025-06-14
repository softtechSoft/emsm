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

import com.softtech.actionForm.EmplyinsrateInfoFormBean;
import com.softtech.entity.EmplyinsrateInfoEntity;
import com.softtech.mappers.EmplyinsrateMapper;


/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-23
 * @return:
 */
class EmplyInsrateInfoServiceImplTest {
    @InjectMocks
    private EmplyInsrateInfoServiceImpl target;

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
        //foreach循环遍历将从DB取出来的数据，一个Entity的List转换成EntityBean，然后再赋给画面需要的那个Bean
        //input　パラメータを準備
        List<EmplyinsrateInfoEntity> emplyinsrateInfoByYear = new ArrayList<>();
        EmplyinsrateInfoEntity testEntity = new EmplyinsrateInfoEntity();
        emplyinsrateInfoByYear.add(testEntity);
        emplyinsrateInfoByYear.forEach(emplyinsrateInfoEntity -> {
            emplyinsrateInfoEntity.setEmplyinsrateID(1);
            emplyinsrateInfoEntity.setYear("2020");
            emplyinsrateInfoEntity.setLaborBurdenRate("1");
            emplyinsrateInfoEntity.setEmployerBurdenRate("2");
            emplyinsrateInfoEntity.setIndustrialAccidentInsuranceRate("4");
            emplyinsrateInfoEntity.setContributionRate("6");
            emplyinsrateInfoEntity.setStatus("7");
            emplyinsrateInfoEntity.setUpdateDate("8");
            emplyinsrateInfoEntity.setInsertDate("9");
        });
        EmplyinsrateInfoFormBean emplyinsrateInfoFormBean = new EmplyinsrateInfoFormBean();
        emplyinsrateInfoFormBean.setEmplyinsrateID(1);
        emplyinsrateInfoFormBean.setYear("2020");
        emplyinsrateInfoFormBean.setLaborBurdenRate("1");
        emplyinsrateInfoFormBean.setEmployerBurdenRate("2");
        emplyinsrateInfoFormBean.setIndustrialAccidentInsuranceRate("4");
        emplyinsrateInfoFormBean.setContributionRate("6");
        emplyinsrateInfoFormBean.setStatus("7");
        emplyinsrateInfoFormBean.setUpdateDate("8");
        emplyinsrateInfoFormBean.setInsertDate("9");
        // 比較用クラスと戻り値と比較
        Assertions.assertEquals(emplyinsrateInfoFormBean.toString(),
                target.transforEntityToUI(emplyinsrateInfoByYear).toString());
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