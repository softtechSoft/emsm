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
	@RequestMapping("/toinYukyulist")
    public String showYukyuList(Model model) {


		//DBから有給情報の社員IDを取得する,社員情報選択リストの初期表示
		List<YukyuFormBean> elist = yukyuService.getEmployee();
		//画面に渡す
		model.addAttribute("elist", elist);

		//画面初期表示用のFormBeam
		YukyuFormBean yukyuFormBean = new YukyuFormBean();
		model.addAttribute("yukyuFormBean", yukyuFormBean);
		//DBから有給情報を取得する,初期表示
//		List<Yukyu> yk = yukyuService.getAllYukyu();
        //初期表示すべて情報
		//model.addAttribute("yukyulist", yk);


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
		List<YukyuFormBean> elist = yukyuService.getEmployee();
		//
		YukyuFormBean defaultEmployee = new YukyuFormBean();
		defaultEmployee.setEmployeeID("Select");
		//
		elist.add(0, defaultEmployee);
		//画面に渡す
		model.addAttribute("elist", elist);

	    return "yukyu";
	}

	/**概要:社員情報更新画面の初期表示
	    *@param:社員情報リスト画面のデータ
	    *@return:社員情報更新画面
	    *@author:
	    *@date:2023/10/20
	    */
	@RequestMapping("/yukyuInfo")
	public String toYukyuInfo(@ModelAttribute("yukyuFormBean") YukyuFormBean yukyuFormBean, Model model) {

		  //フラグを取得
		  String insertFlg = yukyuFormBean.getInsertFlg();
		  //更新の場合
		  if ("0".equals(insertFlg)) {
			//IDを取得
			  String employeeID1Se = yukyuFormBean.getEmployeeIDSelect();
			//選択された内容を取得する

			  List<Yukyu> bList = yukyuService.getEmployeeID(employeeID1Se);

		      YukyuFormBean yukyuFormBean1 = yukyuService.transforEntityToUI(bList);
		      yukyuFormBean1.setInsertFlg(insertFlg);
		      model.addAttribute("yukyuFormBean", yukyuFormBean1);
		  }
		  return "yukyuEdit";
	}



	 /**概要:更新ボタン
	    *@param:社員情報リスト画面のデータ
	    *@return:社員情報更新画面
	    *@author:スッ
	    *@date:2023/10/30
	    */


	@RequestMapping(value = "/updateYukyu", method = RequestMethod.POST)
	public String updateYukyu(@Valid @ModelAttribute("yukyuFormBean")  YukyuFormBean yukyuFormBean,
	                                BindingResult result, HttpSession session, Model model) {

		 if (result.hasErrors()) {
				// エラーチェック用リスト
				  List<FieldError> errorlst = new ArrayList<FieldError>();
				//エラーメッセージ。
				  errorlst.addAll(result.getFieldErrors());
				  //エラーメッセージ
				  model.addAttribute("errors", errorlst);

			     return "yukyuEdit";
		 }
		// 更新DB
//		  yukyuService.update(yukyuFormBean);
		 // 更新DBの結果
		    boolean updateResult = yukyuService.updateYk(yukyuFormBean);

		    if (updateResult) {
		        //更新成功
		        model.addAttribute("updateMsg", "有給情報を更新しました。");
		    }
//

		//リスト選択
		List<YukyuFormBean> elist = yukyuService.getEmployee();
		//画面に渡す
		model.addAttribute("elist", elist);

		return "yukyu";

	}
}