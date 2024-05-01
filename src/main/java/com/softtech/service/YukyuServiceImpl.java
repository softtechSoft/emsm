package com.softtech.service;

import java.util.List;

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
	public List<YukyuFormBean> getEmployee() {
		 List<YukyuFormBean> yukyu = yukyuMapper.getEmployee();

	        return yukyu;
	}

//	@Override
//	public void update(YukyuFormBean yukyuFormBean) {
//
//
//		 yukyuMapper.update(yukyuFormBean);
//	}

	@Override
	public void save(YukyuFormBean yukyuFormBean) {
		yukyuMapper.save(yukyuFormBean);

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
