package com.softtech.service;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.softtech.actionForm.YukyuFormBean;
import com.softtech.entity.Yukyu;
import com.softtech.mappers.YukyuMapper;

public class YukyuServiceImplTest {

    @InjectMocks
    private YukyuServiceImpl target;

    @Mock
    private YukyuMapper yukyuMapper;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    public void testFindIDnendo() {

        Map<String, String> params = new HashMap<>();
        params.put("employeeID", "123");
        params.put("nendo", "2023");
        List<Yukyu> mockYukyuList = new ArrayList<>();
        Yukyu yukyu = new Yukyu();
        mockYukyuList.add(yukyu);


        when(yukyuMapper.findIDnendo(params)).thenReturn(mockYukyuList);


        List<Yukyu> result = target.findIDnendo(params);


        assertThat(result).isNotNull();
        assertThat(result).hasSize(1);
        verify(yukyuMapper, times(1)).findIDnendo(params);
    }


    @Test
    public void testUpdateYk() {

    	Yukyu yukyu=new Yukyu();
    	yukyu.setEmployeeID("123");
    	yukyu.setNendo("2023");
    	yukyu.setUsedDay("10");

    	Boolean result= target.updateYk(yukyu);

    	assertThat(result).isTrue();
    	verify(yukyuMapper, times(1)).update(yukyu);

    }

    @Test
    public void testTransferUIToMap() {
    	YukyuFormBean yukyuFormBean=new YukyuFormBean();
    	yukyuFormBean.setEmployeeID("123");
    	yukyuFormBean.setNendo("2023");
    	yukyuFormBean.setUsedDay("10");


    	Map<String, String> resultMap = target.transferUIToMap(yukyuFormBean);

    	assertThat(resultMap).isNotNull();
    	assertThat(resultMap.get("employeeID")).isEqualTo("123");
        assertThat(resultMap.get("nendo")).isEqualTo("2023");
        assertThat(resultMap.get("usedDay")).isEqualTo("10");

    }


}
