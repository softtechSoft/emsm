package com.softtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

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
	 * Menu画面初期化
	 *
	 * @param model　モデル
	 * @return  遷移先画面
	 */
	@RequestMapping(value = "/functionInit")
	public String menuInit(Model model) {

		return "functionset";
	}

	@RequestMapping(value = "/menu")
	public String menu(Model model) {
	    // 機能リストを取得する
		List<MenuBean> Ofcfunction = menuService.queryOfcfunction();
		model.addAttribute("list", Ofcfunction);

		return "menu";
	}
	@RequestMapping(value = "/blank")
	public String blank(Model model) {

		return "blank";
	}
}
