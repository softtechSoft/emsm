package com.softtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.Yukyu;
import com.softtech.entity.YukyuFormBean;
import com.softtech.service.YukyuService;

@Controller
public class YukyuController {


	@Autowired
	YukyuService yukyuService;


	/**
     * 概要:初期化画面
     * @param model
     * @return:有給リスト
     * @author:sun
     */
	@RequestMapping("/yukyu")
    public String showYukyuList(Model model) {

		//DBから社員情報を取得する,社員情報画面の初期表示
		List<Yukyu> elist = yukyuService.getEmployee();
		//画面に渡す
		model.addAttribute("elist", elist);

		//画面初期表示用のFormBeam
		Yukyu yukyu = new Yukyu();
		model.addAttribute("yukyu", yukyu);

		//画面初期表示用のFormBeam
		YukyuFormBean yukyuFormBean = new YukyuFormBean();
		model.addAttribute("YukyuFormBean", yukyuFormBean);
		//以下の正しい
//		List<Yukyu> yk = yukyuService.getAllYukyu();
//		model.addAttribute("yukyulist", yk);


        return "yukyu";
    }

	/**
     * 概要:検索
    @param yukyu
     * @param action
     * @param model
     * @return:有給リスト
     * @throws IllegalStateException
     */

//	@RequestMapping(value = "/searchYukyu",method = RequestMethod.POST)
//	public String searchYukyu(@ModelAttribute("yukyuFormBean") YukyuFormBean yukyuFormBean,Model model){
//		List<Yukyu> yukyuList = yukyuService.getEmployee();
//		model.addAttribute("yukyuList",yukyuList);
//		//検索フラグ
//		String selectFlg = yukyuFormBean.getSelectFlg();
//		//検索ボタン押す時selectFlg　'0'
//		if("0".equals(selectFlg)) {
//			//IDを取得
//			String employeeID = yukyuFormBean.getEmployeeID();
//			List<Yukyu> bList=yukyuService.getEmployeeID(employeeID);
//			model.addAttribute("yukyulist",bList);
//			return "yukyu";
//		}
//		//全量検索ボタン押す時selectFlg　'1'
//		else {
//			List<Yukyu> ListAll=yukyuService.getAllYukyu();
//			yukyuFormBean.setSelectFlg(selectFlg);
//			model.addAttribute("yukyulist",ListAll);
//			return "yukyu";
//		}
//	}


}