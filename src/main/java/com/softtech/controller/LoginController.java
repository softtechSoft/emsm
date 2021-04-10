package com.softtech.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.LoginBean;

/**
 *  ログイン処理する。
 *
 * @author Softtech
 * @since  2021/04/10
 */
@Controller
@RequestMapping("/emsm")
public class LoginController {
	/**
	 *    ログイン画面初期処理
	 *       ログイン画面へ遷移する。
	 * @param  モデル
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		LoginBean loginbean = new LoginBean();
		loginbean.setEmployeeID("testID");
		loginbean.setPassword("111");
		model.addAttribute("loginBean", loginbean);
		return "login";
	}
}