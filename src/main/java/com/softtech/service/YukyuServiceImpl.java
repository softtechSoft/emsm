package com.softtech.service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.YukyuFormBean;
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
	public List<Yukyu> getEmployee() {
		 List<Yukyu> yukyu = yukyuMapper.getEmployee();

	        return yukyu;
	}

//	@Override
//	public void update(YukyuFormBean yukyuFormBean) {
//
//
//		 yukyuMapper.update(yukyuFormBean);
//	}

	@Override
	public YukyuFormBean findIDnendo(Map<String, String> map) {
		Yukyu yukyuInfo = yukyuMapper.findIDnendo(map);
		if (yukyuInfo == null) {
	        // 如果找不到符合条件的记录，返回 null 或者抛出异常，视情况而定
	        return null;
	    }
		YukyuFormBean yukyuDetailFormBean = new YukyuFormBean();
		yukyuDetailFormBean.setEmployeeID(yukyuInfo.getEmployeeID());
		yukyuDetailFormBean.setNendo(yukyuInfo.getNendo());
		yukyuDetailFormBean.setTotalDay(yukyuInfo.getTotalDay());
		yukyuDetailFormBean.setUsedDay(yukyuInfo.getUsedDay());
		yukyuDetailFormBean.setInsertDate(yukyuInfo.getInsertDate());
		yukyuDetailFormBean.setUpdateDate(yukyuInfo.getUpdateDate());

		return yukyuDetailFormBean;
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
	public boolean updateYk(YukyuFormBean yukyuFormBean) {


		yukyuMapper.update(yukyuFormBean);
        return true;

	}


}
