package com.softtech.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.Yukyu;
import com.softtech.entity.YukyuFormBean;
import com.softtech.mappers.YukyuMapper;


@Service
public class YukyuServiceImpl implements YukyuService {

    @Autowired
    private YukyuMapper yukyuMapper;

    public YukyuServiceImpl(YukyuMapper yukyuMapper) {
    	this.yukyuMapper=yukyuMapper;
    }

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

	@Override
	public void update(Yukyu yukyu) {
		// TODO 自動生成されたメソッド・スタブ

	}



}
