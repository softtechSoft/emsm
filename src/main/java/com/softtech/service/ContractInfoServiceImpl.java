package com.softtech.service;
import java.util.List;

import org.springframework.stereotype.Service;

import com.softtech.actionForm.ContractInfoBean;
/**
 * 概要：給料情報取得インタフェース
 *
 * @author テー@ソフトテク
 *
 */
@Service
public class ContractInfoServiceImpl implements ContractInfoService{

	/**
	 * 機能：給料情報リスト取得
	 *
	 * @author テー@ソフトテク
	 */
	@Override
	public List<ContractInfoBean> queryContractInfoList(String month) {


		return  null;
	}

}
