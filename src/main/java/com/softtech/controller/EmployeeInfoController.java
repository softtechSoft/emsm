package com.softtech.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.EmployeeActionForm;
import com.softtech.actionForm.EmployeeInfoFormBean;
import com.softtech.entity.EmployeeInfo;
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
		logger.info("start index()");

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
	public String employeeInfo(@ModelAttribute("employeeInfoFormBean") EmployeeInfo employeeInfoFormBean,Model model){

		logger.debug("debug test");
		logger.info("info test");
		logger.warn("warn test");
		logger.error("error test");
		logger.fatal("fatal test");

		/*		String employeeID = employeeInfoFormBean.getEmployeeID();
				//社員IDにより、検索する
				List<EmployeeInfoEntity> bList=
				employeeInfoService.getEmployeeID(employeeID);

						//社員IDリスト候補生成
						List<EmployeeActionForm> employeeList = employeeInfoService.queryEmployeeInfo();
						//社員IDリスト候補を画面へ渡す
						model.addAttribute("employeeList",employeeList);
						model.addAttribute("employeeInfoFormBean",employeeInfoFormBean);
						model.addAttribute("list",bList);

						return "employeeInfoList";*/
		String employeeID = employeeInfoFormBean.getEmployeeID();
		List<EmployeeActionForm> employeeList = employeeInfoService.queryEmployeeInfo();
		model.addAttribute("employeeInfoFormBean",employeeInfoFormBean);
		model.addAttribute("employeeList",employeeList);
		//return "employeeInfoList";

		String selectFlg = employeeInfoFormBean.getSelectFlg();

		model.addAttribute("employeeInfoFormBean",employeeInfoFormBean);
		if("0".equals(selectFlg)) {

			List<EmployeeInfoEntity> bList=
					employeeInfoService.getEmployeeID(employeeID);
			//List<EmployeeActionForm> employeeList = employeeInfoService.queryEmployeeInfo();
			employeeInfoFormBean.setSelectFlg(selectFlg);
			model.addAttribute("employeeList",employeeList);
			model.addAttribute("employeeInfoFormBean",employeeInfoFormBean);
			model.addAttribute("list",bList);
			return "employeeInfoList";

		}
		else {
			List<EmployeeInfoEntity> ListAll=
					employeeInfoService.getEmployeeIDAll(employeeID);

			employeeID = employeeInfoFormBean.getEmployeeID();
			employeeInfoFormBean.setSelectFlg(selectFlg);
			List<EmployeeActionForm> employeeListAll= employeeInfoService.queryEmployeeInfo();
			model.addAttribute("employeeListAll",employeeListAll);
			model.addAttribute("employeeInfoFormBean",employeeInfoFormBean);
			model.addAttribute("list",employeeList);
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

				      //incomeTaxIDを採番する（既存の最大値＋１）
				      //获取当前时间，显示在页面上
				      LocalDateTime now = LocalDateTime.now();
				      DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
				      String format = formatter.format(now);

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


				  //更新画面の年度を表示する用リスト候補生成
				  //List<EmployeeIDName> year = employeeInfoService.getYear();
				  //model.addAttribute("year", year);

				  return "employeeInfoEdit";

				}

				@RequestMapping(value = "/employeeInfoEdit", method = RequestMethod.POST)
				public String registEmployeeInfo(@Valid @ModelAttribute("employeeInfoFormBean") EmployeeInfoFormBean employeeInfoFormBean,
				                                BindingResult result, HttpSession session, Model model) {

				  //必須チェック
				  if (result.hasErrors()) {
				      //DBから年度リスト生成
				     // List<EmployeeIDName> year = employeeInfoService.getYear();
				     // model.addAttribute("year", year);

				      //年度を任意設定
				      //employeeInfoFormBean.setYear("2020");

				      return "employeeInfoEdit";
				  }

				  String insertFlg = employeeInfoFormBean.getInsertFlg();
				  //新規の場合
				  if ("0".equals(insertFlg)) {
				  	employeeInfoService.insertEmployeeInfo(employeeInfoFormBean);
				  } else {
				      //DBに更新入力
				  	employeeInfoService.updateEmployeeInfo(employeeInfoFormBean);
				  }

				  //DBから年度リスト生成
				  //List<EmployeeIDName> year = employeeInfoService.getYear();
				  //model.addAttribute("year", year);

				  //年度を任意設定
				  //incomeTaxInfoFormBean.setYear("2020");
				  return "employeeInfoEdit";

				}


}
