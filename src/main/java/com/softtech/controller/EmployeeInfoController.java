package com.softtech.controller;

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
import com.softtech.actionForm.EmployeeInfoBean;
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
	  * 概要:DBから社員情報を取得する,画面の社員ID選択
	  *
	  * @param:[model]
	  * @return:java.lang.String
	  * @author:スッ
	  * @date:2023/10/20
	  */
	@RequestMapping("/initEmployeeInfoList")
	public String toinitEmployeeInfoList(Model model) {
		//DBから社員情報を取得する,社員情報画面の初期表示
		List<EmployeeActionForm> employeeList = employeeInfoService.queryEmployeeInfo();
		//画面に渡す
		model.addAttribute("employeeList", employeeList);

		//画面初期表示用のFormBeam
		EmployeeInfoFormBean employeeInfoFormBean = new EmployeeInfoFormBean();
		model.addAttribute("employeeInfoFormBean", employeeInfoFormBean);
		return "employeeInfoList";
	}

	/**概要:社員情報の検索
	*@param:[employeeInfoFormBean, model]
	*@return:java.lang.String
	*@author:スッ
	*@date:2023/10/20
	*/
	@RequestMapping("/employeeInfoList")
	public String employeeInfo(@ModelAttribute("employeeInfoFormBean") EmployeeInfoFormBean employeeInfoFormBean,Model model){
		List<EmployeeActionForm> employeeList = employeeInfoService.queryEmployeeInfo();
		model.addAttribute("employeeList",employeeList);
		//検索フラグ
		String selectFlg = employeeInfoFormBean.getSelectFlg();
		//検索ボタン押す時selectFlg　'0'
		if("0".equals(selectFlg)) {
			//IDを取得
			String employeeID = employeeInfoFormBean.getEmployeeID();
			List<EmployeeInfoEntity> bList=employeeInfoService.getEmployeeID(employeeID);
			model.addAttribute("list",bList);
			return "employeeInfoList";
		}
		//全量検索ボタン押す時selectFlg　'1'
		else {
			List<EmployeeInfoEntity> ListAll=employeeInfoService.getEmployeeAll();
			employeeInfoFormBean.setSelectFlg(selectFlg);
			model.addAttribute("list",ListAll);
			return "employeeInfoList";
		}
	}
	/**概要:社員情報更新画面の初期表示
	    *@param:社員情報リスト画面のデータ
	    *@return:社員情報更新画面
	    *@author:
	    *@date:2023/10/20
	    */
	@RequestMapping("/toinitEmployeeInfo")
	public String toinitEmployeeInfo(@ModelAttribute("employeeInfoFormBean") EmployeeInfoFormBean employeeInfoFormBean,
	                                Model model) {

	  //新規フラグを取得
	  String insertFlg = employeeInfoFormBean.getInsertFlg();
	  //新規の場合
	  if ("0".equals(insertFlg)) {
	      EmployeeInfoFormBean employeeInfoFormBean1 = new EmployeeInfoFormBean();
	      model.addAttribute("employeeInfoFormBean", employeeInfoFormBean1);
	      //employeeIDを採番する（既存の最大値＋１）
	      String maxEmployeeID = employeeInfoService.getNextEmployeeID();
	      employeeInfoFormBean1.setEmployeeID(maxEmployeeID);
	      //新規
//	      employeeInfoFormBean1.setInsertFlg(insertFlg);

	  return "employeeInfoEdit";
	      //更新の場合
	  } else {

		  //IDを取得
		  String employeeID = employeeInfoFormBean.getEmployeeID();
		//選択された内容を取得する
	      List<EmployeeInfoEntity> bList =
	              employeeInfoService.getUpdateEmployeeByEmployeeID(employeeID);

	      EmployeeInfoFormBean employeeInfoFormBean1 =
	              employeeInfoService.transforEntityToUI(bList);
	      employeeInfoFormBean1.setInsertFlg(insertFlg);
	      model.addAttribute("employeeInfoFormBean", employeeInfoFormBean1);
	  }
	  return "infoEdit";

	}
    /**概要:更新と新規ボタン
    *@param:社員情報リスト画面のデータ
    *@return:社員情報更新画面
    *@author:スッ
    *@date:2023/10/30
    */
	@RequestMapping(value = "/employeeInfoEdit", method = RequestMethod.POST)
	public String registEmployeeInfo(@Valid @ModelAttribute EmployeeInfoBean employeeInfoFormBean,
	                                BindingResult result, HttpSession session, Model model) {

		 if (result.hasErrors()) {
				// エラーチェック用リスト
				  List<FieldError> errorlst = new ArrayList<FieldError>();
				//エラーメッセージ。
				  errorlst.addAll(result.getFieldErrors());
				  //エラーメッセージ
				  model.addAttribute("errors", errorlst);

			     return "employeeInfoEdit";
		 }
	        // ユーザー情報の登録
		 employeeInfoService.save(employeeInfoFormBean);
		 model.addAttribute("successMessage", "登録完了");
		 return "employeeInfoEdit";

	}
	@RequestMapping(value = "/employeeInfoEdit1", method = RequestMethod.POST)
	public String update(@Valid @ModelAttribute EmployeeInfoFormBean employeeInfoFormBean,
	                                BindingResult result, Model model) {

		 if (result.hasErrors()) {
				// エラーチェック用リスト
				  List<FieldError> errorlst = new ArrayList<FieldError>();
				//エラーメッセージ。
				  errorlst.addAll(result.getFieldErrors());
				  //エラーメッセージ
				  model.addAttribute("errors", errorlst);

			     return "infoEdit";
		 }
	        // ユーザー情報の登録
		 employeeInfoService.update(employeeInfoFormBean);
		 model.addAttribute("successMessage", "更新完了");
		 return "infoEdit";

	}

}
