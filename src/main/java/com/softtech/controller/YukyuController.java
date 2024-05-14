package com.softtech.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.softtech.util.DateUtil;

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
		List<Yukyu> elist = yukyuService.getEmployee();
		//画面に渡す
		model.addAttribute("elist", elist);

		//画面初期表示用のFormBeam
		YukyuFormBean yukyuFormBean = new YukyuFormBean();
		//画面に渡す
		model.addAttribute("yukyuFormBean", yukyuFormBean);
		//DBから有給情報を取得する,初期表示
		List<Yukyu> yk = yukyuService.getAllYukyu();
        //初期表示すべて情報
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
		//フラグを取得
	    String selectFlg = yukyuFormBean.getSelectFlg();

	    List<Yukyu> yk;
	    switch (selectFlg) {
	        case "0":
	            String employeeID = yukyuFormBean.getEmployeeID();
	            yk = yukyuService.getEmployeeID(employeeID);
	            break;
	        default:
	            yk = yukyuService.getAllYukyu();
	            break;
	    }
	    model.addAttribute("yukyulist", yk);

	    //DBから有給情報の社員IDを取得する,社員情報選択リストの初期表示
		List<Yukyu> elist = yukyuService.getEmployee();
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
	public String toYukyuInfo(@ModelAttribute("yukyuFormBean") YukyuFormBean yukyuFormBean,
			BindingResult result,
			Model model) {

		//IDを取得
		  String employeeID1Se = yukyuFormBean.getEmployeeIDSelect();
		//現在の年度を取得
	    	String currentYear = DateUtil.getNowYear();
    	//取得したIDと年を受け取るコンテナを新規作成します
	    YukyuFormBean DetailForm = new YukyuFormBean();
    	DetailForm.setEmployeeID(employeeID1Se);
    	DetailForm.setNendo(currentYear);
		//コンテナをマップに変換する
    	Map<String, String> sqlParam = yukyuService.transferUIToMap(DetailForm);
    	//マップ型データでDBを検索し、最終的にFromBean形式に変換します
    	YukyuFormBean yukyuFormBean1 = yukyuService.findIDnendo(sqlParam);

    	if (yukyuFormBean1 == null) {
            // データが見つからない場合の処理はエラーメッセージを表示
            result.rejectValue("nendo", "error.nendo", "本年度ではない、更新不可");
            // エラーを表示する
            model.addAttribute("errors", result.getAllErrors());

          //DBから有給情報の社員IDを取得する,社員情報選択リストの初期表示
    		List<Yukyu> elist = yukyuService.getEmployee();
    		//画面に渡す
    		model.addAttribute("elist", elist);

    		//DBから有給情報を取得する,初期表示
    		List<Yukyu> yk = yukyuService.getAllYukyu();
            //初期表示すべて情報
    		model.addAttribute("yukyulist", yk);

            return "yukyu"; // return画面リスト、かつエラーを表示する
        }
//		//選択された内容を取得する
//		  List<Yukyu> bList = yukyuService.getEmployeeID(employeeID1Se);
//
//	      YukyuFormBean yukyuFormBean1 = yukyuService.transforEntityToUI(bList);
	      model.addAttribute("yukyuFormBean", yukyuFormBean1);

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
		 // 更新DBの結果
		    boolean updateResult = yukyuService.updateYk(yukyuFormBean);

		    if (updateResult) {
		        //更新成功
		        model.addAttribute("updateMsg", "有給情報を更新しました。");
		    }

		//リスト選択
		List<Yukyu> elist = yukyuService.getEmployee();
		//画面に渡す
		model.addAttribute("elist", elist);

		return "yukyu";

	}
}