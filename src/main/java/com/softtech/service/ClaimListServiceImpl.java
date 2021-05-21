package com.softtech.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.entity.ClaimInfo;
import com.softtech.mappers.ClaimListMapper;
import com.softtech.util.DateUtil;
/**
 * 概要：請求リストのservice
 *
 * 作成者：王＠ソフトテク
 * 作成日：2021/05/20
 */
@Service
public class ClaimListServiceImpl implements ClaimListService{
	@Autowired
	ClaimListMapper claimlistMapper;
	@Override
	public List<ClaimInfo> queryClaimList(String month,String companyName) {

        // YYYY/MM→yyyymmに変換
		String monthP = DateUtil.chgMonthToYM(month);
		// 請求リストを取得する
		List<ClaimInfo> claimInfolist =  claimlistMapper.getClaimInfolist(monthP,companyName);

		return  claimInfolist;
	}


}


