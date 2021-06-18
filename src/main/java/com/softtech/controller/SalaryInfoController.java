package com.softtech.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.common.SalaryInfoRecord;
import com.softtech.service.SalaryInfoService;
import com.softtech.util.DateUtil;

/**
 * 概要：給料作成機能
 * 作成者：馬@ソフトテク
 * 作成日：2021/6/02
 */
@Controller
public class SalaryInfoController {
	@Autowired
	SalaryInfoService salaryInfoService;
	/**
	 *   給料作成処理。
	 * @param  salaryInfoBean 画面入力値
	 * @throws ParseException
	 */
	@RequestMapping(value = "salaryInfo", method = RequestMethod.POST )
	public String toWorkDetailList(HttpServletResponse response,@Valid @ModelAttribute("SalaryInfoBean") SalaryInfoBean salaryInfoBean,
			BindingResult result, Model model) throws ParseException {
		//作成ボタンを押す。（１：登録ボタン、２：作成ボタン）
		if("2".equals(salaryInfoBean.getMake())) {

			SalaryInfoRecord em = new SalaryInfoRecord();

		 	// 対象年月+1をする。
			String month= DateUtil.monthplus(DateUtil.chgMonthToYM(salaryInfoBean.getMonth()));
			em.setMonth(month);
			//社員ID
			em.setEmployeeID(salaryInfoBean.getEmployeeID());
			// DBから次月給料情報を取得
			SalaryInfoBean salaryInfoRecordss= salaryInfoService.querySalaryInfo(em);

			//給料新規作成
			if(null == salaryInfoRecordss) {
				// 画面モードに新規作成を設定すい（ 0:画面初期表示、1：給料情報の新規作成。2：給料情報の更新）
				model.addAttribute("gamenMode", "1");

				//作成場合、対象年月+1後、クライアント側へ渡す。
				salaryInfoBean.setMonth(DateUtil.modifymonth(month));
				model.addAttribute("salaryInfoBean",salaryInfoBean);

			//給料更新
			}else {
				//次月給料情報から支払日を取得
				String paymentDate=salaryInfoRecordss.getPaymentDate();

				//支払日は過去日付か、本日かの場合、変更不可にする。
				if(DateUtil.isLessThanNow(paymentDate)
						|| DateUtil.isNow(DateUtil.chgMonthToYM(paymentDate))) {
					//変更不可にする。
					model.addAttribute("gamenMode","0");
				//将来日付の場合、変更する。
				}else {
					//変更ボタン用
					model.addAttribute("gamenMode","2");
				}
				model.addAttribute("salaryInfoBean",salaryInfoRecordss);
			}

		//登録ボタンを押す
		}else if("1".equals(salaryInfoBean.getMake())) {
			//画面モードに新規作成を設定すい（ 0:画面初期表示、1：給料情報の新規作成。2：給料情報の更新）
			model.addAttribute("gamenMode",salaryInfoBean.getGamenMode());

			// エラーチェック用Flg;
			boolean errorJudge =false ;
			// エラーチェック用リスト
			List<FieldError> errorlst = new ArrayList<FieldError>();
			// NotNullチェック
			if (result.hasErrors()) {
				// エラーチェック用Flg;
			    errorJudge =true ;
				//エラーメッセージ。
				errorlst.addAll(result.getFieldErrors());
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			 }

			//Not数字チェック
			if(salaryInfoService.queryError(salaryInfoBean)!=null && !salaryInfoService.queryError(salaryInfoBean).isEmpty()) {
				// エラーチェック用Flg;
			    errorJudge =true ;
				//エラーメッセージ。
				errorlst.addAll(salaryInfoService.queryError(salaryInfoBean));
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			if(errorJudge) {
			//エラーメッセージ
			model.addAttribute("errors", errorlst);
			}else {
				//登録ボタンを押す時.作成場合
				if("1".equals(salaryInfoBean.getGamenMode())) {
					// DBまで給料作成情報を作成。
					salaryInfoService.setSalaryInfo2(salaryInfoService.queryRegister(salaryInfoBean));
					//作成成功後、画面の表示。
					return "makeSalarySuccess";
				//登録ボタンを押す時.変更場合
				}else if("2".equals(salaryInfoBean.getGamenMode())){
					// DBまで給料作成情報を更新。
					salaryInfoService.setSalaryInfo3(salaryInfoService.queryRegister(salaryInfoBean));
					//更新成功後、画面の表示。
					return "updateSalarySuccess";
					}
				}
			}
		return "salaryInfo";
	}
}
