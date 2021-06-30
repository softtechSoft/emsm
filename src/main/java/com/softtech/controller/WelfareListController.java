package com.softtech.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.WelfareBean;
import com.softtech.service.WelfareListService;
import com.softtech.util.FileUtil;

/**
 * 概要：福祉控除リスト機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/6/21
 */
@Controller
public class WelfareListController {
	@Autowired
	WelfareListService welfareListService;
	/**
	 * 機能：初期画面を表示
	 * @return  welfarelist
	 * @author 馬@ソフトテク
	 */
	@RequestMapping("/welfarelist")
	public String toWorkDetailList(Model model) {
		// DBから福祉控除情報を取得
		List<WelfareBean> welfareBeanList = welfareListService.queryWelfareDate();
		model.addAttribute("list",welfareBeanList);
		return "welfarelist";
	}
	/**
	 * 機能：入力した社員IDに基づいて福祉控除リストを表示する。
	 * @return  welfarelist
	 * @author 馬@ソフトテク
	 */
	@RequestMapping(value = "welfarelist", method = RequestMethod.POST)
	public String WorkDetailSubmit(HttpServletResponse response,@Valid @ModelAttribute("welfareBean") WelfareBean welfareBean,
			BindingResult result,Model model) {
			// NotNullの入力した社員IDをチェック。
			if (result.hasErrors()) {
				model.addAttribute("errors", result.getFieldErrors());
				return "welfarelist";
			}
			// 入力した社員IDを持っち、DBから福祉控除情報を取得
			List<WelfareBean> welfareBeanList = welfareListService.queryWelfare(welfareBean.getEmployeeID());
			//検索場合
			if("1".equals(welfareBean.getWholeFlg())) {
				model.addAttribute("list",welfareBeanList);
				model.addAttribute("employeeID",welfareBean.getEmployeeID());
			}
			//ダウンロード場合
			if("2".equals(welfareBean.getWholeFlg())) {
				 FileUtil ft = new FileUtil();
				 boolean rtn = ft.welfareDownload(response,welfareBeanList);
				 if(!rtn) {
					 // エラーメッセージを設定して、画面表示
				 }else {
					 //画面表示用データを設定する。
					 model.addAttribute("employeeID", welfareBean.getEmployeeID());
				 }
			}
			//更新場合
			if("3".equals(welfareBean.getWholeFlg())) {

			}
			return "welfarelist";
	}
}
