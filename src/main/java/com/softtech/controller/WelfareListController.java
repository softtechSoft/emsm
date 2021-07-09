package com.softtech.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.WelfareBean;
import com.softtech.service.WelfareInfoService;
import com.softtech.service.WelfareListService;
import com.softtech.util.DataUtil;
import com.softtech.util.DateUtil;
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
	@Autowired
	WelfareInfoService welfareInfoService;
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
			BindingResult result,HttpSession session,Model model) {
			// NotNullの入力した社員IDをチェック。
			if (result.hasErrors()) {
				model.addAttribute("errors", result.getFieldErrors());
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
			//福祉情報を作成と更新場合
			if("3".equals(welfareBean.getWholeFlg())) {
				//ログインから作成者と更新者を取得
				String loginUserName=String.valueOf(session.getAttribute("loginUserName"));
				// DBから福祉情報を取得
				List<WelfareBean> welfareList = welfareListService.queryWelfare(welfareBean.getMakeEmployeeID());
				//福祉情報作成場合
				if(null == welfareList|| welfareList.size() ==0) {

					// DBから給作成用社員情報を取得する
					WelfareBean welfareEmployee=welfareInfoService.queryEmployee(welfareBean.getMakeEmployeeID());
					//作成者
					welfareEmployee.setInsertEmployee(loginUserName);
					//更新者
					welfareEmployee.setUpdateEmployee(loginUserName);
					//作成日
					welfareEmployee.setInsertDate(DateUtil.modifymonth1(DateUtil.getNowMonth1()));
					//更新日
					welfareEmployee.setUpdateDate(DateUtil.modifymonth1(DateUtil.getNowMonth1()));
					//控除ステータス
					welfareEmployee.setStatus("1");
					model.addAttribute("welfare",welfareEmployee);

				//福祉情報更新場合
				}else {
					String startDate = welfareBean.getStartDate();
					if(startDate == null || startDate.length()==0) {
						return "welfareInfo";
					}

					WelfareBean welfareBeanDB=null;
					for(WelfareBean weBean:welfareList) {
						if (startDate.equals(weBean.getStartDate()))
						{
							welfareBeanDB = weBean;
							break;
						}
					}
					//雇用保険個人負担
					welfareBeanDB.setEplyInsSelf(DateUtil.ma(DataUtil.functionText1(String.valueOf(Math.ceil(Integer.parseInt(DateUtil.chgMonthToYM1(welfareBeanDB.getBase()))*0.003)))));
					//雇用保険会社負担
					welfareBeanDB.setEplyInsComp(DateUtil.ma(DataUtil.functionText1(String.valueOf(Math.ceil(Integer.parseInt(DateUtil.chgMonthToYM1(welfareBeanDB.getBase()))*0.006)))));
					//一般拠出金（会社のみ)
					welfareBeanDB.setEplyInsWithdraw(DateUtil.ma(DataUtil.functionText1(String.valueOf(Math.ceil(Integer.parseInt(DateUtil.chgMonthToYM1(welfareBeanDB.getBase()))*0.00002)))));
					//労災保険（会社負担のみ）
					welfareBeanDB.setWkAcccpsIns(DateUtil.ma(DataUtil.functionText1(String.valueOf(Math.ceil(Integer.parseInt(DateUtil.chgMonthToYM1(welfareBeanDB.getBase()))*0.003)))));
					//作成者
					welfareBeanDB.setInsertEmployee(loginUserName);
					//更新者
					welfareBeanDB.setUpdateEmployee(loginUserName);
					//作成日
					welfareBeanDB.setInsertDate(DateUtil.modifymonth1(DateUtil.getNowMonth1()));
					//更新日
					welfareBeanDB.setUpdateDate(DateUtil.modifymonth1(DateUtil.getNowMonth1()));
					//控除ステータス
					welfareBeanDB.setStatus("1");
					model.addAttribute("welfare",welfareBeanDB);
				}
				return "welfareInfo";
			}
			return "welfarelist";
	}
}
