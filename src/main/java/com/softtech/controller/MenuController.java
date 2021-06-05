package com.softtech.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
	public String menuInit(HttpServletRequest request,Model model) {
		System.out.println("************** in emsm *************");
		String employeeID="";
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {

			//ログインID取得
			if (entry.getKey().equals("employeeID") ) {
				employeeID = entry.getValue()[0];
				break;
			}
		}
		System.out.println(employeeID);

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
