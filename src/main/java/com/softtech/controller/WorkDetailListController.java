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

import com.softtech.actionForm.WorkDetail;
import com.softtech.actionForm.WorkSelectJyoken;
import com.softtech.service.WorkDetailListService;
import com.softtech.util.DateUtil;
import com.softtech.util.FileUtil;
/**
 * 概要：勤怠リスト機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */
@Controller
public class WorkDetailListController {

	@Autowired
	WorkDetailListService workDetailListService;
	@RequestMapping("/workdetaillist")
	public String toWorkDetailList(Model model) {

        //現在年月取得
		String month=DateUtil.getNowMonth();
		// DBから勤怠情報を取得
		List<WorkDetail> workDetailList = workDetailListService.queryWorkDetail(month);
		model.addAttribute("list", workDetailList);
		//検索条件初期化
		model.addAttribute("month", month);
		return "workdetaillist";
	}
	/**
	 * 機能：入力した年月に基づいて勤怠リストを表示する。
	 * @return  workdetaillist
	 * @author 馬@ソフトテク
	 */
	@RequestMapping(value = "workdetaillist", method = RequestMethod.POST)
	public String WorkDetailSubmit(HttpServletResponse response,@Valid @ModelAttribute("selectjyolken") WorkSelectJyoken selectjyolken, BindingResult result,Model model) {
		// NotNullの入力した年月をチェック。
		if (result.hasErrors()) {
		model.addAttribute("errors", result.getFieldErrors());
		return "workdetaillist";
		}

		// 入力した年月を持っち、DBから勤怠情報を取得
	     List<WorkDetail> workDetailList1 = workDetailListService.queryWorkDetail(selectjyolken.getMonth());

		 // データダウンロード場合
		 if(selectjyolken.getDownloadFlg()){
			 FileUtil ft = new FileUtil();
			 boolean rtn = ft.workSheetDownload(response,workDetailList1);
			 if(!rtn) {
				 // エラーメッセージを設定して、画面表示
			 }else {
				 //画面表示用データを設定する。
				 model.addAttribute("month", selectjyolken.getMonth());
			 }

		 // 検索する場合
		 } else {

			 model.addAttribute("month", selectjyolken.getMonth());
			 model.addAttribute("list", workDetailList1);
		 }
		 return "workdetaillist";
	}

}