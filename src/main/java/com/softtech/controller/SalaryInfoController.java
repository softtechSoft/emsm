package com.softtech.controller;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

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
import com.softtech.actionForm.WelfareBean;
import com.softtech.common.SalaryInfoRecord;
import com.softtech.entity.SalaryInfo;
import com.softtech.service.SalaryInfoService;
import com.softtech.service.WelfareListService;
import com.softtech.util.DateUtil;

/**
 * 概要：給料作成機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/6/02
 */
@Controller
public class SalaryInfoController {
	@Autowired
	SalaryInfoService salaryInfoService;
	@Autowired
	WelfareListService welfareListService;
	/*
	 * 機能概要： 給料データの作成処理。
	 *
	 * @param  salaryInfoBean 画面入力値
	 * @throws ParseException
	 */
	@RequestMapping(value = "salaryInfo", method = RequestMethod.POST )
	public String toWorkDetailList(@Valid @ModelAttribute("SalaryInfoBean") SalaryInfoBean salaryInfoBean,
									BindingResult result, Model model) throws ParseException {

		//作成ボタンを押す。（１：作成ボタン、２：登録ボタン）
		if("1".equals(salaryInfoBean.getMake())) {

			SalaryInfoRecord em = new SalaryInfoRecord();

		 	// 対象年月+1をする。
			String month= DateUtil.monthplus(DateUtil.chgMonthToYM(salaryInfoBean.getMonth()));
			em.setMonth(month);
			//社員ID
			em.setEmployeeID(salaryInfoBean.getEmployeeID());
			// DBから次月給料情報を取得
			SalaryInfo salaryInfoDB= salaryInfoService.querySalaryInfo(em);

			//次月のデータまだ存在していない場合、給料新規作成
			if(null == salaryInfoDB) {
				//作成場合、対象年月+1後、クライアント側へ渡す。
				String monthYM = DateUtil.modifymonth(month);
				salaryInfoBean.setMonth(monthYM);
				// 支払日
				String paymentDate=DateUtil.modifymonth(DateUtil.monthplus(month))+"/20";
				salaryInfoBean.setPaymentDate(paymentDate);

				//　福祉情報を取得して、画面へ渡す。
				 List<WelfareBean>  welfareBeans = welfareListService.queryWelfare(salaryInfoBean.getEmployeeID());
				 salaryInfoService.setWelfareToGamen(salaryInfoBean,welfareBeans,monthYM.substring(5, 7));

				// 画面モードに新規作成を設定する。（ 0:画面初期表示、1：給料情報の新規作成。2：給料情報の更新）
				salaryInfoBean.setGamenMode("1");
				model.addAttribute("salaryInfoBean",salaryInfoBean);
				return "salaryInfo";

			//既に存在する場合、給料更新をする
			}else {
				// DBデータを画面用Beanに変更
				SalaryInfoBean salaryInfoGamen = salaryInfoService.transferToGamen(salaryInfoDB);

				//支払日を取得
				String paymentDate=salaryInfoGamen.getPaymentDate();

				//支払日は過去日付か、本日かの場合、変更不可にする。
				if(DateUtil.isLessThanNow(paymentDate)
						|| DateUtil.isNow(DateUtil.chgMonthToYM(paymentDate))) {

					//変更不可のため、画面初期モードに設定。
					salaryInfoGamen.setGamenMode("0");
				//将来日付の場合、変更する。
				}else {
					//変更モードに設定
					salaryInfoGamen.setGamenMode("2");
				}
				model.addAttribute("salaryInfoBean",salaryInfoGamen);

				return "salaryInfo";
			}

		//登録ボタンを押す
		}else if("2".equals(salaryInfoBean.getMake())) {
			// 数値に変更i
			salaryInfoService.deleteComma(salaryInfoBean);
			// 必須チェック
			if (result.hasErrors()) {
			    // エラーチェック用リスト
				List<FieldError> errorlst = new ArrayList<FieldError>();

				//エラーメッセージ。
				errorlst.addAll(result.getFieldErrors());
				//エラーメッセージ
				model.addAttribute("errors", errorlst);
				model.addAttribute("salaryInfoBean",salaryInfoBean);

				return "salaryInfo";
			 }

			//その他チェック
			List<FieldError> fieldErrors = salaryInfoService.checkData(salaryInfoBean);
			// エラーがある場合
			if(fieldErrors.size()>0) {
				model.addAttribute("errors", fieldErrors);
				model.addAttribute("salaryInfoBean",salaryInfoBean);

				return "salaryInfo";
			}
			SalaryInfoRecord em = new SalaryInfoRecord();

		 	// 対象年月+1をする。
			String month= DateUtil.chgMonthToYM(salaryInfoBean.getMonth());
			em.setMonth(month);
			//社員ID
			em.setEmployeeID(salaryInfoBean.getEmployeeID());
			// DBから次月給料情報を取得
			SalaryInfo salaryInfoDB= salaryInfoService.querySalaryInfo(em);


			//給料新規作成処理
			if(null == salaryInfoDB) {
				// 給料情報作成
				salaryInfoService.insertSalaryInfo(salaryInfoService.transferToDB(salaryInfoBean));
				//作成成功後、画面の表示。
				return "makeSalarySuccess";

			//給料変更処理
			}else {
				// 給料情報更新
				salaryInfoService.updateSalaryInfo(salaryInfoService.transferToDB(salaryInfoBean));
				//更新成功後、画面の表示。
				return "updateSalarySuccess";
				}
			}

		return "salaryInfo";
	}
}
