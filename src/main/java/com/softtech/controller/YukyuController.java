package com.softtech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.YukyuFormBean;
import com.softtech.entity.Yukyu;
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

		//DBから有給情報の社員IDを取得する,社員情報選択リストの初期表示
		List<Yukyu> elist = yukyuService.getEmployee();
		//画面に渡す
		model.addAttribute("elist", elist);

		//画面初期表示用のFormBeam
//		Yukyu yukyu = new Yukyu();
//		model.addAttribute("yukyu", yukyu);
		YukyuFormBean yukyuFormBean = new YukyuFormBean();
		model.addAttribute("yukyuFormBean", yukyuFormBean);
		//DBから有給情報を取得する,初期表示
		List<Yukyu> yk = yukyuService.getAllYukyu();
		model.addAttribute("yukyulist", yk);


        return "yukyu";
    }

	/**
     * 概要:検索ボタン
    @param yukyu
     * @param action
     * @param model
     * @return:有給リスト
     * @throws IllegalStateException
     */


	@RequestMapping(value = "/searchYukyu", method = RequestMethod.POST)
	public String toSearchJsp(@ModelAttribute("yukyuFormBean") YukyuFormBean yukyuFormBean, Model model) {
	    List<Yukyu> employeeList = yukyuService.getEmployee();
	    model.addAttribute("employeeList", employeeList);

	    String selectFlg = yukyuFormBean.getSelectFlg();

	    if ("0".equals(selectFlg)) {
	        String employeeID = yukyuFormBean.getEmployeeID();
	        List<Yukyu> yk = yukyuService.getEmployeeID(employeeID);
	        model.addAttribute("yukyulist", yk);
	    } else {
	        List<Yukyu> yk = yukyuService.getAllYukyu();
	        model.addAttribute("yukyulist", yk);
	    }

	    //DBから有給情報の社員IDを取得する,社員情報選択リストの初期表示
		List<Yukyu> elist = yukyuService.getEmployee();
		//画面に渡す
		model.addAttribute("elist", elist);

	    return "yukyu";
	}


	 /**概要:更新ボタン
	    *@param:社員情報リスト画面のデータ
	    *@return:社員情報更新画面
	    *@author:スッ
	    *@date:2023/10/30
	    */

//	@RequestMapping(value = "/update/{employeeID}", method = RequestMethod.POST)
//	public String updateYukyu(@PathVariable("employeeID") String employeeID, @ModelAttribute YukyuFormBean yukyuFormBean, BindingResult result, Model model) {
//	    // 进行表单验证，如果有错误，返回到编辑页面
//	    if (result.hasErrors()) {
//	        // 添加错误信息到模型中，以便在页面显示
//	        model.addAttribute("errors", result.getAllErrors());
//	        return "infoEdit"; // 返回编辑页面
//	    }
//
//	    // 调用服务层方法更新数据
//	    yukyuService.update(employeeID, yukyuFormBean);
//
//	    return "redirect:/yukyu"; // 重定向到显示页面
//	}
}