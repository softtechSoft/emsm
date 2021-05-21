package com.softtech.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.ClaimlistSelectJyoken;
import com.softtech.entity.ClaimInfo;
import com.softtech.service.ClaimListService;
import com.softtech.util.DateUtil;
import com.softtech.util.FileUtil;

/**
 * 概要：請求リスト機能
 *
 * 作成者：王@ソフトテク
 * 作成日：2021/5/18
 */
@Controller
public class ClaimListController {

	@Autowired
	ClaimListService claimListService;

	@RequestMapping("/claimlist")
	public String toClaimlist(Model model) {

		//現在年月取得
		String month = DateUtil.getNowMonth();
		ClaimlistSelectJyoken claimlistSelectJyoken = new ClaimlistSelectJyoken();
		String companyName = claimlistSelectJyoken.getCompanyName();

		// DBから請求情報を取得
		List<ClaimInfo> cl = claimListService.queryClaimList(month, companyName);
		model.addAttribute("list", cl);
		//検索条件初期化
		model.addAttribute("month", month);

		return "claimlist";
	}

	/**
	 * 機能：入力した年月と取引先に基づいて請求リストを表示する。
	 * @return  claimlist
	 * @author 王@ソフトテク
	 */
	@RequestMapping(value = "claimlist", method = RequestMethod.POST)
	public String ClaimlistSubmit(HttpServletResponse response, @Valid ClaimlistSelectJyoken claimlistSelectJyoken,
			BindingResult result, Model model) {
		// NotNullの入力した年月をチェック。
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getFieldErrors());
			return "claimlist";
		}

		model.addAttribute("month", claimlistSelectJyoken.getMonth());
		model.addAttribute("companyName", claimlistSelectJyoken.getCompanyName());
		// 入力した年月と取引先を持っち、DBから請求情報を取得
		List<ClaimInfo> cl = claimListService.queryClaimList(claimlistSelectJyoken.getMonth(),
				claimlistSelectJyoken.getCompanyName());
		// データダウンロード場合
		if (claimlistSelectJyoken.getDownloadFlg()) {
			FileUtil ft = new FileUtil();
			boolean rtn = ft.claimDownload(response, cl);
			if (!rtn) {
				// エラーメッセージを設定して、画面表示
			} else {
				//画面表示用データを設定する。
			}
			// 検索する場合
		} else {
			model.addAttribute("list", cl);
		}
		return "claimlist";
	}
}
