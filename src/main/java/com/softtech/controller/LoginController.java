package com.softtech.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.LoginBean;
import com.softtech.actionForm.MenuBean;
import com.softtech.service.LoginService;

/**
 *  ログイン処理する。
 *
 * @author Softtech
 * @since  2021/04/10
 */
@Controller
public class LoginController {
	@Autowired
	LoginService loginService;
	/**
	 *    ログイン画面初期処理
	 *       ログイン画面へ遷移する。
	 * @param  モデル
	 */
	@RequestMapping(value = {"/","/login"}, method = RequestMethod.GET)
	public String home(Model model) {
		LoginBean loginbean = new LoginBean();
		model.addAttribute("loginBean", loginbean);
		return "login";
	}
	/**
	 * ログイン処理
	 *
	 * @param loginbean　画面入力値　
	 * @param result　チェック結果
	 * @param model　モデル
	 * @return  遷移先画面
	 */
	@RequestMapping(value = "login", params = "login", method = RequestMethod.POST)
	public String login(@Valid @ModelAttribute("loginBean") LoginBean loginBean,
			BindingResult result, Model model) {
		// 入力にエラーある場合、画面にエラーを表示する。
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getFieldErrors());
			return "login";
		}
		//Login処理
		boolean rtnbl = loginService.doLogin(loginBean);

		// ログイン成功。
		if (rtnbl) {
			List<MenuBean> Ofcfunction = new ArrayList<MenuBean>();
			MenuBean menuBean1 = new MenuBean();
			menuBean1.setFunctionText("勤怠情報リスト");
			menuBean1.setFunctionLink("/workdetaillist");
			Ofcfunction.add(menuBean1);

			model.addAttribute("list", Ofcfunction);



			return "menu";
			//　ログイン失敗
		}else {
			// エラーメッセージを表示する
			List<FieldError> lst = new ArrayList<FieldError>();
			FieldError err1 = new FieldError("", "", "ログインは失敗しました。再ログインしてください。");
			FieldError err2 = new FieldError("", "", "またはシステム管理者へ連絡してください。");
			lst.add(err1);
			lst.add(err2);
			model.addAttribute("errors", lst);
			return "login";
		}
	}
}