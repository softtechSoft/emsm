package com.softtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.MenuBean;
import com.softtech.service.MenuService;
/**
 *  Menu処理する。
 *
 * @author Softtech
 * @since  2021/04/10
 */
@Controller
public class MenuController {

	@Autowired
	MenuService menuService;

	/**
	 * Menu処理
	 *
	 * @param model　モデル
	 * @return  遷移先画面
	 */
	@RequestMapping(value = "menu",  method = RequestMethod.POST)
	public String Menu( @ModelAttribute("MenuBean")  Model model) {
	// 機能リストを取得する
	List<MenuBean> Ofcfunction = menuService.queryOfcfunction("0");
	model.addAttribute("list", Ofcfunction);
	return "/";
	}

}
