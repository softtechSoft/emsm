package com.softtech.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.actionForm.MenuBean;
import com.softtech.entity.Ofcfunction;
import com.softtech.mappers.MenuMapper;
import com.softtech.util.DataUtil;
/**
 * 概要：Menuのservice
 *
 * 作成者：馬＠ソフトテク
 * 作成日：2021/04/10
 */
@Service
public class MenuService {
	@Autowired
	MenuMapper menumapper;


	public List<MenuBean> queryOfcfunction() {
		// 機能リストを取得する
		List<Ofcfunction> Ofcfunction = menumapper.queryOfcfunction();
		// 機能リストへ変更する。
		List<MenuBean> rtn  = menu(Ofcfunction);
		return  rtn;
	}
	//DBから取得したデータを機能リストへ変換する。
	private List<MenuBean>menu(List<Ofcfunction> lst){
		if(lst == null ) return new ArrayList<MenuBean>();

		List<MenuBean> rtn  =new ArrayList<MenuBean>();
		for(Ofcfunction tt : lst) {
			MenuBean menuBean = new MenuBean();
			menuBean.setFunctionText(DataUtil.functionText(tt.getFunctionText()));
			menuBean.setFunctionLink(tt.getFunctionLink());
			menuBean.setDisplayNo(tt.getDisplayNo());
			rtn.add(menuBean);
		}

		return rtn;
	}

}
