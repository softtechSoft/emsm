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
			// ①Ｖａｌｉｄａｔｉｏｎチェックエラーの場合、エラーを画面へ渡す、処理終了。
			//②他のエラーチェック、（全部でOK）例：Service





			SalaryInfoRecord sm = new SalaryInfoRecord();
			//社員ID
			sm.setEmployeeID(salaryInfoBean.getEmployeeID());
			//対象年月
			sm.setMonth(DateUtil.chgMonthToYM(salaryInfoBean.getMonth()));
			SalaryInfoBean salaryInfoRecords= salaryInfoService.querySalaryInfo(sm);

			// エラーチェック用リスト
			List<FieldError> errorlst = new ArrayList<FieldError>();
			// エラーチェック用
			boolean number =true ;
			// 支払日が過去日チェック用
			boolean dateless = true ;
			// 支払日が通常以外の日付チェック用
			boolean date = true ;
			// 支払日が通常以外の日付チェック
			boolean isdate =DateUtil.isDate(salaryInfoBean.getPaymentDate());
			// 基本給数字チェック。
			boolean base = DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getBase()));
			// 支払日数字チェック。
			boolean paymentDate = DateUtil.isNumeric(DateUtil.chgMonthToYM(salaryInfoBean.getPaymentDate()));
			// 残業時間数字チェック。
			boolean overTime = DateUtil.isNumeric(salaryInfoBean.getOverTime());
			// 不足時間数字チェック。
			boolean shortage = DateUtil.isNumeric(salaryInfoBean.getShortage());
			// 残業加算数字チェック。
			boolean overTimePlus = DateUtil.isNumeric(salaryInfoBean.getOverTimePlus());
			// 稼働不足減数字チェック。
			boolean shortageReduce = DateUtil.isNumeric(salaryInfoBean.getShortageReduce());
			// 交通費数字チェック
			boolean transportExpense = DateUtil.isNumeric(salaryInfoBean.getTransportExpense());
			// 手当加算数字チェック
			boolean allowancePlus = DateUtil.isNumeric(salaryInfoBean.getAllowancePlus());
			// 手当減算数字チェック
			boolean allowanceReduce = DateUtil.isNumeric(salaryInfoBean.getAllowanceReduce());
			// 厚生控除個人数字チェック
			boolean welfareSelf = DateUtil.isNumeric(salaryInfoBean.getWelfareSelf());
			// 厚生控除会社数字チェック
			boolean welfareComp = DateUtil.isNumeric(salaryInfoBean.getWelfareComp());
			// 厚生控除子育(会社)数字チェック
			boolean welfareBaby = DateUtil.isNumeric(salaryInfoBean.getWelfareBaby());
			// 雇用保険個人負担数字チェック
			boolean eplyInsSelf = DateUtil.isNumeric(salaryInfoBean.getEplyInsSelf());
			// 雇用保険会社負担数字チェック
			boolean eplyInsComp = DateUtil.isNumeric(salaryInfoBean.getEplyInsComp());
			// 雇用保拠出金（会社)数字チェック
			boolean eplyInsWithdraw = DateUtil.isNumeric(salaryInfoBean.getEplyInsWithdraw());
			// 労災保険（会社負担のみ）数字チェック
			boolean wkAcccpsIns = DateUtil.isNumeric(salaryInfoBean.getWkAcccpsIns());
			// 源泉控除数字チェック
			boolean withholdingTax = DateUtil.isNumeric(salaryInfoBean.getWithholdingTax());
			// 住民税控除数字チェック
			boolean municipalTax = DateUtil.isNumeric(salaryInfoBean.getMunicipalTax());
			// 社宅家賃控除数字チェック
			boolean rental = DateUtil.isNumeric(salaryInfoBean.getRental());
			// 社宅共益費控除数字チェック
			boolean rentalMgmtFee = DateUtil.isNumeric(salaryInfoBean.getRentalMgmtFee());
			//総額数字チェック
			boolean sum = DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getSum()));
			//総費用数字チェック
			boolean totalFee = DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getTotalFee()));
			// 基本給数字チェック。
			if(base) {
				number = false;
				FieldError err1 = new FieldError("", "", "基本給が数字を入力してください。例：60,000");
				errorlst.add(err1);
			    model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 残業時間数字チェック。
			if(overTime) {
				number = false;
				FieldError err2 = new FieldError("", "", "残業時間が数字を入力してください。例：20");
				errorlst.add(err2);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 不足時間数字チェック。
			if(shortage) {
				number = false;
				FieldError err3= new FieldError("", "", "不足時間が数字を入力してください。例：20");
				errorlst.add(err3);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 残業加算数字チェック。
			if(overTimePlus) {
				number = false;
				FieldError err4= new FieldError("", "", "残業加算が数字を入力してください。例：20");
				errorlst.add(err4);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 稼働不足減数字チェック。
			if(shortageReduce) {
				number = false;
				FieldError err5= new FieldError("", "", "稼働不足減が数字を入力してください。例：20");
				errorlst.add(err5);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 交通費数字チェック
			if(transportExpense) {
				number = false;
				FieldError err6= new FieldError("", "", "交通費が数字を入力してください。例：2000");
				errorlst.add(err6);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 手当加算数字チェック
			if(allowancePlus) {
				number = false;
				FieldError err7= new FieldError("", "", "手当加算が数字を入力してください。例：2000");
				errorlst.add(err7);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 手当減算数字チェック
			if(allowanceReduce) {
				number = false;
				FieldError err8= new FieldError("", "", "手当減算が数字を入力してください。例：2000");
				errorlst.add(err8);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 厚生控除個人数字チェック
			if(welfareSelf) {
				number = false;
				FieldError err9= new FieldError("", "", "厚生控除個人が数字を入力してください。例：2000");
				errorlst.add(err9);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 厚生控除会社数字チェック
			if(welfareComp) {
				number = false;
				FieldError err10= new FieldError("", "", "厚生控除会社が数字を入力してください。例：2000");
				errorlst.add(err10);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 厚生控除子育(会社)数字チェック
			if(welfareBaby) {
				number = false;
				FieldError err11= new FieldError("", "", "厚生控除子育(会社)が数字を入力してください。例：2000");
				errorlst.add(err11);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 雇用保険個人負担数字チェック
			if(eplyInsSelf) {
				number = false;
				FieldError err12= new FieldError("", "", "雇用保険個人負担が数字を入力してください。例：2000");
				errorlst.add(err12);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 雇用保険会社負担数字チェック
			if(eplyInsComp) {
				number = false;
				FieldError err13= new FieldError("", "", "雇用保険会社負担が数字を入力してください。例：2000");
				errorlst.add(err13);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 雇用保拠出金（会社)数字チェック
			if(eplyInsWithdraw) {
				number = false;
				FieldError err14= new FieldError("", "", "雇用保拠出金（会社)が数字を入力してください。例：2000");
				errorlst.add(err14);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 労災保険（会社負担のみ）数字チェック
			if(wkAcccpsIns) {
				number = false;
				FieldError err15= new FieldError("", "", "労災保険（会社負担のみ）が数字を入力してください。例：2000");
				errorlst.add(err15);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 源泉控除数字チェック
			if(withholdingTax) {
				number = false;
				FieldError err16= new FieldError("", "", "源泉控除が数字を入力してください。例：2000");
				errorlst.add(err16);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 住民税控除数字チェック
			if(municipalTax) {
				number = false;
				FieldError err17= new FieldError("", "", "住民税控除が数字を入力してください。例：2000");
				errorlst.add(err17);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 社宅家賃控除数字チェック
			if(rental) {
				number = false;
				FieldError err18= new FieldError("", "", "社宅家賃控除が数字を入力してください。例：2000");
				errorlst.add(err18);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// 社宅共益費控除数字チェック
			if(rentalMgmtFee) {
				number = false;;
				FieldError err19= new FieldError("", "", "社宅共益費控除が数字を入力してください。例：2000");
				errorlst.add(err19);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			//総費用数字チェック
			if(totalFee) {
				number = false;
				FieldError err20= new FieldError("", "", "総費用が数字を入力してください。例：60,000");
				errorlst.add(err20);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			//総額数字チェック
			if(sum) {
				number = false;
				FieldError err21= new FieldError("", "", "総額が数字を入力してください。例：60,000");
				errorlst.add(err21);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			// NotNullチェック。
			if (result.hasErrors()) {
				number = false;
				//エラーメッセージ。
				errorlst.addAll(result.getFieldErrors());
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			 }
			// 支払日数字チェック。
			if(paymentDate) {
				// 支払日が過去日チェック用
				date = false ;
				number = false;
				FieldError err22= new FieldError("", "", "支払日が数字を入力してください。例：2021/06/15");
				errorlst.add(err22);
				model.addAttribute("salaryInfoBean",salaryInfoBean);
			}
			if(date) {
				// 支払日が通常の日付チェック
				if(isdate==false) {
					dateless = false;
					number = false;
					FieldError err23= new FieldError("", "", "支払日がを通常の日付を入力してください、例：”2021/06/30”");
					errorlst.add(err23);
					model.addAttribute("salaryInfoBean",salaryInfoBean);
				}
				if (dateless) {
					// 支払日が過去日チェック。
					if(DateUtil.isLessThanNow(salaryInfoBean.getPaymentDate())) {
						number = false;
						FieldError err24= new FieldError("", "", "支払日がを未来の日付を入力してください");
						errorlst.add(err24);
						model.addAttribute("salaryInfoBean",salaryInfoBean);
					}
				}
			}

			if(number) {
				//登録ボタンを押す時.作成場合
				if(salaryInfoRecords == null) {
					// DBまで給料作成情報を作成。
				    salaryInfoService.setSalaryInfo2(salaryInfoService.queryRegister(salaryInfoBean));
				    //作成成功後、画面の表示。
				    return "makeSalarySuccess";
				//登録ボタンを押す時.変更場合
				}else{
					// DBまで給料作成情報を更新。
					salaryInfoService.setSalaryInfo3(salaryInfoService.queryRegister(salaryInfoBean));
					//更新成功後、画面の表示。
					return "updateSalarySuccess";
					}
			}		//エラーメッセージ
					model.addAttribute("errors", errorlst);
		}
					return "salaryInfo";
	}
}
