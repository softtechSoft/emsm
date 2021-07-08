package com.softtech.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.softtech.actionForm.LoginBean;
import com.softtech.actionForm.MenuBean;
import com.softtech.entity.LoginEntity;
import com.softtech.service.LoginService;
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

	@Autowired
	LoginService loginService;

	/**
	 * Menu画面初期化
	 *   EMSM直接ログイン、EMSから遷移の場合もある。
	 *
	 * @param model　モデル
	 * @return  遷移先画面
	 */
	@RequestMapping(value = "/functionInit")
	public String menuInit(HttpServletRequest request,Model model,HttpSession session) {
		System.out.println("************** in emsm *************");
		String employeeID="";
		Map<String, String[]> map = request.getParameterMap();
		for (Map.Entry<String, String[]> entry : map.entrySet()) {

			//ログインID取得
			if (entry.getKey().equals("employeeID") ) {

				employeeID = entry.getValue()[0];
				//ユーザ情報取得
				LoginBean loginBean = new LoginBean();
				loginBean.setEmployeeID(employeeID);
				LoginEntity userEmployee=loginService.getEmployeeByID(loginBean);
				//ユーザ情報をセッションに設定
				if (session.getAttribute("loginUserID") != null) {
					session.setAttribute("loginUserID",userEmployee.getEmployeeID());
				}
				if (session.getAttribute("loginUserName") != null) {
				session.setAttribute("loginUserName",userEmployee.getEmployeeName());
				}
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
