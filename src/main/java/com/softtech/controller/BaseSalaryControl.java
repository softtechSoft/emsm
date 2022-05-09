package com.softtech.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.service.LoginService;


/**
 * 概要：契約情報リスト初期表示
 *
 * 作成者：孫曄@ソフトテク
 * 作成日：2022/05/05
 *
 */
@Controller
public class BaseSalaryControl {
	// ログインサービス
	@Autowired
	LoginService loginService;

	@RequestMapping("/initBaseSalaryList")
	public String toBaseSalaryList(Model model, String contractInfoList) {
		return "initBaseSalaryList";


	}
}