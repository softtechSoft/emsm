package com.softtech.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.EmployeeInfoFormBean;
import com.softtech.entity.EmployeeInfoEntity;
import com.softtech.service.EmployeeInfoService;

/**
 * @program
 * @概要:
 * @作成者:
 * @作成日:2023-10-20
 * @return:
 */
@Controller
public class EmployeeInfoController {

	//IOC
	@Autowired
	private EmployeeInfoService employeeInfoService;

	//logger
	static protected Logger logger = LogManager.getLogger(EmployeeInfoController.class);

	/**
	  * 概要:社員情報画面の初期表示
	  *
	  * @param:[model]
	  * @return:java.lang.String
	  * @author:スッ
	  * @date:2023/10/20
	  */
	@RequestMapping("/initEmployeeInfoList")
	public String toinitEmployeeInfoList(Model model) {

		//DBから社員情報を取得する,画面の社員ID選択,枠に表示する
		List<EmployeeActionForm> employeeList = employeeInfoService.queryEmployeeInfo();
		model.addAttribute("employeeList", employeeList);

		//画面初期表示用のFormBeam
		EmployeeInfoFormBean employeeInfoFormBean = new EmployeeInfoFormBean();
		model.addAttribute("employeeInfoFormBean", employeeInfoFormBean);
		return "employeeInfoList";
	}

	/**概要:社員IDにより、検索する
	*@param:[employeeInfoFormBean, model]
	*@return:java.lang.String
	*@author:スッ
	*@date:2023/10/20
	*/
	@RequestMapping("/employeeInfoList")
	public String employeeInfo(@ModelAttribute("employeeInfoFormBean") EmployeeInfoFormBean employeeInfoFormBean,Model model){
		String employeeID = employeeInfoFormBean.getEmployeeID();
		List<EmployeeActionForm> employeeList = employeeInfoService.queryEmployeeInfo();
		model.addAttribute("employeeInfoFormBean",employeeInfoFormBean);
		model.addAttribute("employeeList",employeeList);
		String selectFlg = employeeInfoFormBean.getSelectFlg();
		model.addAttribute("employeeInfoFormBean",employeeInfoFormBean);

		//検索ボタン押す時selectFlg　'0'
		if("0".equals(selectFlg)) {
			List<EmployeeInfoEntity> bList=employeeInfoService.getEmployeeID(employeeID);
			for(EmployeeInfoEntity employeeInfoEntity:bList) {
				if ("1".equals(employeeInfoEntity.getSex())){
					employeeInfoEntity.setSex("男");
				}
				else {
					employeeInfoEntity.setSex("女");
				}
			}
			employeeInfoFormBean.setSelectFlg(selectFlg);
			//employeeInfoFormBean.setSex(String);
			model.addAttribute("employeeList",employeeList);
			model.addAttribute("employeeInfoFormBean",employeeInfoFormBean);
			model.addAttribute("list",bList);
			return "employeeInfoList";
		}
		//全量検索ボタン押す時selectFlg　'1'
		else {
			List<EmployeeInfoEntity> ListAll=employeeInfoService.getEmployeeIDAll(employeeID);
			for(EmployeeInfoEntity employeeInfoEntity:ListAll) {
				if ("1".equals(employeeInfoEntity.getSex())){
					employeeInfoEntity.setSex("男");
				}
				else {
					employeeInfoEntity.setSex("女");
				}
			}
			employeeInfoFormBean.setSelectFlg(selectFlg);
			//employeeInfoFormBean.setSex(String);
			List<EmployeeActionForm> employeeListAll= employeeInfoService.queryEmployeeInfo();
			model.addAttribute("employeeListAll",employeeListAll);
			model.addAttribute("list",ListAll);
			return "employeeInfoList";
		}
	}
	/**概要:検索と更新画面の初期表示
	    *@param:[employeeInfoFormBean, model]
	    *@return:java.lang.String
	    *@author:
	    *@date:2023/10/20
	    */
	@RequestMapping("/toinitEmployeeInfo")
	public String toinitEmployeeInfo(@ModelAttribute("employeeInfoFormBean") EmployeeInfoFormBean employeeInfoFormBean,
	                                Model model) {
	  //IDを取得
	  String employeeID = employeeInfoFormBean.getEmployeeID();

	  //新規フラグを取得
	  String insertFlg = employeeInfoFormBean.getInsertFlg();
	  //新規の場合
	  if ("0".equals(insertFlg)) {
	      EmployeeInfoFormBean employeeInfoFormBean1 = new EmployeeInfoFormBean();

	      //获取当前时间，显示在页面上
	      LocalDateTime now = LocalDateTime.now();
	      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
	      String format = formatter.format(now);
	      //employeeIDを採番する（既存の最大値＋１）
	      String maxEmployeeID = employeeInfoService.getNextEmployeeID();
	      employeeInfoFormBean1.setEmployeeID(maxEmployeeID);
	      //新規
	      employeeInfoFormBean1.setInsertFlg(insertFlg);
	      employeeInfoFormBean1.setInsertDate(format);
	      employeeInfoFormBean1.setUpdateDate(format);
	      model.addAttribute("employeeInfoFormBean", employeeInfoFormBean1);

	      //更新の場合
	  } else {
	      //選択された内容を取得する
	      employeeID = employeeInfoFormBean.getEmployeeID();
	      List<EmployeeInfoEntity> bList =
	              employeeInfoService.getUpdateEmployeeByEmployeeID(employeeID);

	      EmployeeInfoFormBean employeeInfoFormBean2 =
	              employeeInfoService.transforEntityToUI(bList);
	      //更新
	      employeeInfoFormBean2.setInsertFlg(insertFlg);
	      model.addAttribute("employeeInfoFormBean", employeeInfoFormBean2);
	  }

	  return "employeeInfoEdit";

	}
    /**概要:更新と新規ボタン
    *@param:[employeeInfoFormBean, result, session, model]
    *@return:java.lang.String
    *@author:スッ
    *@date:2023/10/30
    */
	@RequestMapping(value = "/employeeInfoEdit", method = RequestMethod.POST)
	public String registEmployeeInfo(@Valid @ModelAttribute("employeeInfoFormBean") EmployeeInfoFormBean employeeInfoFormBean,
	                                BindingResult result, HttpSession session, Model model) {

	  //必須チェック
	  if (result.hasErrors()) {
		// エラーチェック用リスト
		  List<FieldError> errorlst = new ArrayList<FieldError>();
		//エラーメッセージ。
		  errorlst.addAll(result.getFieldErrors());
		  //エラーメッセージ
		  model.addAttribute("errors", errorlst);
		  model.addAttribute("employeeInfoFormBean",employeeInfoFormBean);
	     return "employeeInfoEdit";
	  }
	  {

	  String insertFlg = employeeInfoFormBean.getInsertFlg();
	  //新規の場合
	  if ("0".equals(insertFlg)) {
	  	employeeInfoService.insertEmployeeInfo(employeeInfoFormBean);
	  } else {
	      //DBに更新入力
	  	employeeInfoService.updateEmployeeInfo(employeeInfoFormBean);
	  }

	  return "employeeInfoEdit";

	}
	}

}
