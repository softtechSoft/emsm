package com.softtech.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.WelfareBean;
import com.softtech.service.WelfareInfoService;

/**
 * 概要：福祉情報作成機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/6/30
 */
@Controller
public class WelfareInfoController {
	@Autowired
	WelfareInfoService welfareInfoService;
	/*
	 * 機能概要： 福祉情報作成処理。
	 *
	 * @param  welfareBean 画面入力値
	 */
	@RequestMapping(value = "welfareInfo", method = RequestMethod.POST )
	public String toWorkDetailList(@Valid @ModelAttribute("welfare") WelfareBean welfareBean,
			BindingResult result,HttpSession session, Model model) {
			//福祉情報登録
			welfareInfoService.makeWelfare(welfareInfoService.changeWelfare(welfareBean,session));
		return "makeWelfareSuccess";
	}
}
