package com.softtech.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.YukyuFormBean;
import com.softtech.entity.Employee;
import com.softtech.entity.Yukyu;
import com.softtech.mappers.YukyuMapper;


@Service
public class YukyuServiceImpl implements YukyuService {

    @Autowired
    private YukyuMapper yukyuMapper;



    /**
     * 概要:社員IDより、検索する
     * @param employeeID
     * @return:社員リスト
     * @author:sun
     */
	@Override
	public List<Yukyu> getEmployeeID(String employeeID) {
		List<Yukyu> yukyu =  yukyuMapper.getEmployeeID(employeeID);
        return yukyu;
	}
	/**
     * 概要:社員全量検索する
     * @param
     * @return:社員リスト
     * @author:スッ
     */

	@Override
	public List<Yukyu> getAllYukyu() {
		List<Yukyu> yukyu = yukyuMapper.getAllYukyu();
		return yukyu;
    }

	@Override
	public List<Employee> getEmployeeName() {
		List<Employee> elist = yukyuMapper.getEmployeeName();

	        return elist;
	}

	@Override
	public List<Yukyu> findIDnendo(Map<String, String> map) {
		List<Yukyu> yukyuInfo = yukyuMapper.findIDnendo(map);

		return yukyuInfo;
	}
	@Override
	public Map<String, String> transferUIToMap(YukyuFormBean yukyuFormBean){
		Map<String, String> map = new HashMap<String, String>();

		map.put("employeeID", yukyuFormBean.getEmployeeID());
		map.put("nendo", yukyuFormBean.getNendo());
		map.put("totalDay", yukyuFormBean.getTotalDay());
		map.put("usedDay", yukyuFormBean.getUsedDay());
		map.put("insertDate", yukyuFormBean.getInsertDate());


		SimpleDateFormat sdFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar cl = Calendar.getInstance();
		String str = sdFormat.format(cl.getTime());
		map.put("updateDate", str);

		return map;
	}

	@Override
	public YukyuFormBean transforEntityToUI(List<Yukyu> eList) {
		YukyuFormBean yukyuFormBean = new YukyuFormBean();
        for (Yukyu yukyu : eList) {
            yukyuFormBean.setEmployeeID(yukyu.getEmployeeID());
            yukyuFormBean.setNendo(yukyu.getNendo());
            yukyuFormBean.setTotalDay(yukyu.getTotalDay());
            yukyuFormBean.setUsedDay(yukyu.getUsedDay());
            yukyuFormBean.setInsertDate(yukyu.getInsertDate());
            yukyuFormBean.setUpdateDate(yukyu.getUpdateDate());


            yukyuFormBean.setInsertDate(yukyu.getInsertDate());
            yukyuFormBean.setUpdateDate(yukyu.getUpdateDate());
        }
        return yukyuFormBean;
	}
	@Override
	public boolean updateYk(Yukyu yukyu) {
		yukyuMapper.update(yukyu);
        return true;

	}
	@Override
	public Yukyu transforFormBeanToEntity(YukyuFormBean yukyuFormBean) {
		// TODO 自動生成されたメソッド・スタブ
		Yukyu yukyu = new Yukyu();
		yukyu.setEmployeeID(yukyuFormBean.getEmployeeID());
		yukyu.setNendo(yukyuFormBean.getNendo());
		yukyu.setTotalDay(yukyuFormBean.getTotalDay());
		yukyu.setUsedDay(yukyuFormBean.getUsedDay());
		yukyu.setInsertDate(yukyuFormBean.getInsertDate());
		yukyu.setUpdateDate(yukyuFormBean.getUpdateDate());


		yukyu.setInsertDate(yukyuFormBean.getInsertDate());
		yukyu.setUpdateDate(yukyuFormBean.getUpdateDate());
		return yukyu;
	}


}
