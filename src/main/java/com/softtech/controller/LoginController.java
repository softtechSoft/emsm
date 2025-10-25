package com.softtech.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
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
import com.softtech.entity.LoginEntity;
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
	@RequestMapping(value = {"/toLogin"}, method = RequestMethod.GET)
	public String home(Model model) {
		LoginBean loginbean = new LoginBean();
		model.addAttribute("loginBean", loginbean);
		return "login";
		//return "hello";
	}
	@RequestMapping(value = {"/"}, method = RequestMethod.GET)
	public String toReact(Model model) {
		LoginBean loginbean = new LoginBean();
		model.addAttribute("loginBean", loginbean);
		return "index";
		//return "hello";
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
	public String login(@Valid @ModelAttribute("loginBean") LoginBean loginBean,HttpSession session,
			BindingResult result, Model model) {
		// 入力にエラーある場合、画面にエラーを表示する。
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getFieldErrors());
			return "login";
		}

		LoginEntity userEmployee = loginService.getEmployeeByMailAddress(loginBean);

	    if (userEmployee == null) {
	        // ユーザがない
	        List<FieldError> lst = new ArrayList<FieldError>();
	        FieldError err1 = new FieldError("", "", "ユーザーが存在しません。");
	        lst.add(err1);
	        model.addAttribute("errors", lst);
	        return "login";
	    }

		//Login処理
		boolean rtnbl = loginService.doLogin(loginBean);

		// ログイン成功。
		if (rtnbl) {
			userEmployee=loginService.qureyEmployee(loginBean);
			session.setAttribute("loginUserName",userEmployee.getEmployeeName());
			session.setAttribute("loginUserID",userEmployee.getEmployeeID());
			session.setAttribute("userMailAdress",userEmployee.getMailAdress());


			// Menu画面初期化遷移
			return "redirect:/functionInit";

			//　ログイン失敗
		}else {
			// エラーメッセージを表示する
			List<FieldError> lst = new ArrayList<FieldError>();
				if (userEmployee != null && !"1".equals(userEmployee.getAuthority())) {
		            // 权限不足
		            FieldError err1 = new FieldError("", "", "権限が不足しています。");
		            lst.add(err1);
		        } else {
					FieldError err1 = new FieldError("", "", "ログインは失敗しました。再ログインしてください。");
					FieldError err2 = new FieldError("", "", "またはシステム管理者へ連絡してください。");
					lst.add(err1);
					lst.add(err2);
		        }
			model.addAttribute("errors", lst);
			return "login";
		}
	}
}