package com.softtech.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.SalarylistBean3;
import com.softtech.common.SalaryInfocommom;
import com.softtech.common.SalaryInfocommom2;
import com.softtech.entity.SalaryInfo;
import com.softtech.service.SalaryInfoService;
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

	/**
	 *   給料作成処理。
	 *123
	 * @param  モデル
	 * @throws ParseException
	 */
	@RequestMapping(value = "salaryInfo", method = RequestMethod.POST )
	public String toWorkDetailList(HttpServletResponse response,@Valid @ModelAttribute("SalarylistBean3") SalarylistBean3 salarylistBean3,
			BindingResult result, Model model) throws ParseException {
		//作成ボタンを押す。
		if(salarylistBean3.getMake().equals("2")) {
		    // 作成ボタンをおした、inputが可入力になります。
			model.addAttribute("loadFlg", salarylistBean3.getLoadFlg());
			SalaryInfocommom em = new SalaryInfocommom();
		 	// 対象年月+1をする。
			String month= DateUtil.chgMonthToYM(salarylistBean3.getMonth());
			SimpleDateFormat ft = new SimpleDateFormat("yyyyMM");
			// 対象年月string型-->date型。
			Date date = ft.parse(month);
			Calendar time = Calendar.getInstance();
			time.setTime(date);
	        //日期加1个月
			time.add(Calendar.MONTH,1);
	        Date dt1=time.getTime();
	        SimpleDateFormat f = new SimpleDateFormat("yyyyMM");
	        // 対象年月date型-->string型。
	        String s = f.format(dt1);
			//社員ID
			em.setEmployeeID(salarylistBean3.getEmployeeIDb());
			//対象年月
			em.setMonth(s);
			// DBから給料作成情報を取得
			List<SalaryInfo> ss= salaryInfoService.querySalaryInfo(em);
			String ret = s.substring(0,4)+"/"+s.substring(4,6);
			model.addAttribute("month", ret);
			if(null == ss || ss.size() ==0) {
				//作成ボタン用
				String cc = "作成";
				model.addAttribute("button",cc);
				//作成場合、画面側の情報を取得。
				List<SalarylistBean3> rt =new ArrayList<SalarylistBean3>();
				SalarylistBean3 salarylistBean5 =new SalarylistBean3();
				salarylistBean5.setEmployeeID(salarylistBean3.getEmployeeIDb());
				salarylistBean5.setAddress(salarylistBean3.getAddressb());
				salarylistBean5.setEmployeeName(salarylistBean3.getNameb());
				salarylistBean5.setPaymentDate(salarylistBean3.getPaymentDate());
				salarylistBean5.setBase(salarylistBean3.getBase());
				salarylistBean5.setOverTimePlus(salarylistBean3.getOverTimePlus());
				salarylistBean5.setShortageReduce(salarylistBean3.getShortageReduce());
				salarylistBean5.setTransportExpense(salarylistBean3.getTransportExpense());
				salarylistBean5.setAllowancePlus(salarylistBean3.getAllowancePlus());
				salarylistBean5.setAllowanceReduce(salarylistBean3.getAllowanceReduce());
				salarylistBean5.setAllowanceReason(salarylistBean3.getAllowanceReason());
				salarylistBean5.setWelfarePensionSelf(salarylistBean3.getWelfarePensionSelf());
				salarylistBean5.setWelfareHealthSelf(salarylistBean3.getWelfareHealthSelf());
				salarylistBean5.setEplyInsSelf(salarylistBean3.getEplyInsSelf());
				salarylistBean5.setWithholdingTax(salarylistBean3.getWithholdingTax());
				salarylistBean5.setMunicipalTax(salarylistBean3.getMunicipalTax());
				salarylistBean5.setRental(salarylistBean3.getRental());
				salarylistBean5.setRentalMgmtFee(salarylistBean3.getRentalMgmtFee());
				salarylistBean5.setSum(salarylistBean3.getSum());
				salarylistBean5.setRemark(salarylistBean3.getRemark());
				salarylistBean5.setTotalFee(salarylistBean3.getTotalFee());
				salarylistBean5.setWkAcccpsIns(salarylistBean3.getWkAcccpsIns());
				salarylistBean5.setEplyInsWithdraw(salarylistBean3.getEplyInsWithdraw());
				salarylistBean5.setEplyInsComp(salarylistBean3.getEplyInsComp());
				salarylistBean5.setWelfareBaby(salarylistBean3.getWelfareBaby());
				salarylistBean5.setWelfarePensionComp(salarylistBean3.getWelfarePensionComp());
				salarylistBean5.setWelfareHealthComp(salarylistBean3.getWelfareHealthComp());
				salarylistBean5.setOverTime(salarylistBean3.getOverTime());
				salarylistBean5.setShortage(salarylistBean3.getShortage());
				salarylistBean5.setWelfareSelf(salarylistBean3.getWelfareSelf());
				salarylistBean5.setWelfareComp(salarylistBean3.getWelfareComp());
				rt.add(salarylistBean5);
				model.addAttribute("salaryInfo",rt);
				//登録ボタンを押す時、作成と更新を分ける用。
				String k="1";
				model.addAttribute("MakeDistinction",k);
			}else {
				//変更ボタン用
				String bb = "変更";
				model.addAttribute("button",bb);
				//変更場合
				model.addAttribute("salaryInfo",ss);
				//登録ボタンを押す時、作成と更新を分ける用。
				String o="2";
				model.addAttribute("MakeDistinction",o);
			}
			//登録ボタンを押す
		}else if(salarylistBean3.getMake().equals("1")) {
			// 作成ボタンをおした、inputが可入力。
			model.addAttribute("loadFlg", salarylistBean3.getLoadFlg());
			SalaryInfocommom sm = new SalaryInfocommom();
			//社員ID
			sm.setEmployeeID(salarylistBean3.getEmployeeIDb());
			//対象年月
			sm.setMonth(DateUtil.chgMonthToYM(salarylistBean3.getMonth()));
			List<SalaryInfo> ss= salaryInfoService.querySalaryInfo(sm);
			String u=DateUtil.chgMonthToYM(salarylistBean3.getMonth());
			String ret = u.substring(0,4)+"/"+u.substring(4,6);
			model.addAttribute("month", ret);
			//エラー処理用リスト
			List<SalarylistBean3> rtn =new ArrayList<SalarylistBean3>();
			//エラー処理時、画面から入力した情報
			SalarylistBean3 salarylistBean4=new SalarylistBean3();
				salarylistBean4.setEmployeeID(salarylistBean3.getEmployeeIDb());
				salarylistBean4.setAddress(salarylistBean3.getAddressb());
				salarylistBean4.setEmployeeName(salarylistBean3.getNameb());
				salarylistBean4.setPaymentDate(salarylistBean3.getPaymentDate());
				salarylistBean4.setBase(salarylistBean3.getBase());
				salarylistBean4.setOverTimePlus(salarylistBean3.getOverTimePlus());
				salarylistBean4.setShortageReduce(salarylistBean3.getShortageReduce());
				salarylistBean4.setTransportExpense(salarylistBean3.getTransportExpense());
				salarylistBean4.setAllowancePlus(salarylistBean3.getAllowancePlus());
				salarylistBean4.setAllowanceReduce(salarylistBean3.getAllowanceReduce());
				salarylistBean4.setAllowanceReason(salarylistBean3.getAllowanceReason());
				salarylistBean4.setWelfarePensionSelf(salarylistBean3.getWelfarePensionSelf());
				salarylistBean4.setWelfareHealthSelf(salarylistBean3.getWelfareHealthSelf());
				salarylistBean4.setEplyInsSelf(salarylistBean3.getEplyInsSelf());
				salarylistBean4.setWithholdingTax(salarylistBean3.getWithholdingTax());
				salarylistBean4.setMunicipalTax(salarylistBean3.getMunicipalTax());
				salarylistBean4.setRental(salarylistBean3.getRental());
				salarylistBean4.setRentalMgmtFee(salarylistBean3.getRentalMgmtFee());
				salarylistBean4.setSum(salarylistBean3.getSum());
				salarylistBean4.setRemark(salarylistBean3.getRemark());
				salarylistBean4.setTotalFee(salarylistBean3.getTotalFee());
				salarylistBean4.setWkAcccpsIns(salarylistBean3.getWkAcccpsIns());
				salarylistBean4.setEplyInsWithdraw(salarylistBean3.getEplyInsWithdraw());
				salarylistBean4.setEplyInsComp(salarylistBean3.getEplyInsComp());
				salarylistBean4.setWelfareBaby(salarylistBean3.getWelfareBaby());
				salarylistBean4.setWelfarePensionComp(salarylistBean3.getWelfarePensionComp());
				salarylistBean4.setWelfareHealthComp(salarylistBean3.getWelfareHealthComp());
				salarylistBean4.setOverTime(salarylistBean3.getOverTime());
				salarylistBean4.setShortage(salarylistBean3.getShortage());
				salarylistBean4.setWelfareSelf(salarylistBean3.getWelfareSelf());
				salarylistBean4.setWelfareComp(salarylistBean3.getWelfareComp());
				rtn.add(salarylistBean4);
			// エラーチェック用
			boolean number =true ;
			// 支払日が過去日チェック用
			boolean dateless = true ;
			// 支払日が通常以外の日付チェック用
			boolean date = true ;
			// 支払日が通常以外の日付チェック
			boolean isdate =DateUtil.isDate(salarylistBean3.getPaymentDate());
			// 基本給数字チェック。
			boolean base = DateUtil.isNumeric(DateUtil.chgMonthToYM1(salarylistBean3.getBase()));
			// 支払日数字チェック。
			boolean paymentDate = DateUtil.isNumeric(DateUtil.chgMonthToYM(salarylistBean3.getPaymentDate()));
			// 残業時間数字チェック。
			boolean overTime = DateUtil.isNumeric(salarylistBean3.getOverTime());
			// 不足時間数字チェック。
			boolean shortage = DateUtil.isNumeric(salarylistBean3.getShortage());
			// 残業加算数字チェック。
			boolean overTimePlus = DateUtil.isNumeric(salarylistBean3.getOverTimePlus());
			// 稼働不足減数字チェック。
			boolean shortageReduce = DateUtil.isNumeric(salarylistBean3.getShortageReduce());
			// 交通費数字チェック
			boolean transportExpense = DateUtil.isNumeric(salarylistBean3.getTransportExpense());
			// 手当加算数字チェック
			boolean allowancePlus = DateUtil.isNumeric(salarylistBean3.getAllowancePlus());
			// 手当減算数字チェック
			boolean allowanceReduce = DateUtil.isNumeric(salarylistBean3.getAllowanceReduce());
			// 厚生控除個人数字チェック
			boolean welfareSelf = DateUtil.isNumeric(salarylistBean3.getWelfareSelf());
			// 厚生控除会社数字チェック
			boolean welfareComp = DateUtil.isNumeric(salarylistBean3.getWelfareComp());
			// 厚生控除子育(会社)数字チェック
			boolean welfareBaby = DateUtil.isNumeric(salarylistBean3.getWelfareBaby());
			// 雇用保険個人負担数字チェック
			boolean eplyInsSelf = DateUtil.isNumeric(salarylistBean3.getEplyInsSelf());
			// 雇用保険会社負担数字チェック
			boolean eplyInsComp = DateUtil.isNumeric(salarylistBean3.getEplyInsComp());
			// 雇用保拠出金（会社)数字チェック
			boolean eplyInsWithdraw = DateUtil.isNumeric(salarylistBean3.getEplyInsWithdraw());
			// 労災保険（会社負担のみ）数字チェック
			boolean wkAcccpsIns = DateUtil.isNumeric(salarylistBean3.getWkAcccpsIns());
			// 源泉控除数字チェック
			boolean withholdingTax = DateUtil.isNumeric(salarylistBean3.getWithholdingTax());
			// 住民税控除数字チェック
			boolean municipalTax = DateUtil.isNumeric(salarylistBean3.getMunicipalTax());
			// 社宅家賃控除数字チェック
			boolean rental = DateUtil.isNumeric(salarylistBean3.getRental());
			// 社宅共益費控除数字チェック
			boolean rentalMgmtFee = DateUtil.isNumeric(salarylistBean3.getRentalMgmtFee());
			//総額数字チェック
			boolean sum = DateUtil.isNumeric(DateUtil.chgMonthToYM1(salarylistBean3.getSum()));
			//総費用数字チェック
			boolean totalFee = DateUtil.isNumeric(DateUtil.chgMonthToYM1(salarylistBean3.getTotalFee()));
			// 基本給数字チェック。
			if(base) {
				number = false;
				model.addAttribute("basenumber", "基本給が数字を入力してください。例：60,000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
					//変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 残業時間数字チェック。
			if(overTime) {
				number = false;
				model.addAttribute("overTimenumber", "残業時間が数字を入力してください。例：20");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 不足時間数字チェック。
			if(shortage) {
				number = false;
				model.addAttribute("shortagenumber", "不足時間が数字を入力してください。例：20");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 残業加算数字チェック。
			if(overTimePlus) {
				number = false;
				model.addAttribute("overTimePlusnumber", "残業加算が数字を入力してください。例：20");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 稼働不足減数字チェック。
			if(shortageReduce) {
				number = false;
				model.addAttribute("shortageReducenumber", "稼働不足減が数字を入力してください。例：20");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 交通費数字チェック
			if(transportExpense) {
				number = false;
				model.addAttribute("transportExpensenumber", "交通費が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 手当加算数字チェック
			if(allowancePlus) {
				number = false;
				model.addAttribute("allowancePlusnumber", "手当加算が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 手当減算数字チェック
			if(allowanceReduce) {
				number = false;
				model.addAttribute("allowanceReducenumber", "手当減算が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 厚生控除個人数字チェック
			if(welfareSelf) {
				number = false;
				model.addAttribute("welfareSelfnumber", "厚生控除個人が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 厚生控除会社数字チェック
			if(welfareComp) {
				number = false;
				model.addAttribute("welfareCompnumber", "厚生控除会社が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 厚生控除子育(会社)数字チェック
			if(welfareBaby) {
				number = false;
				model.addAttribute("welfareBabynumber", "厚生控除子育(会社)が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 雇用保険個人負担数字チェック
			if(eplyInsSelf) {
				number = false;
				model.addAttribute("eplyInsSelfnumber", "雇用保険個人負担が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 雇用保険会社負担数字チェック
			if(eplyInsComp) {
				number = false;
				model.addAttribute("eplyInsCompnumber", "雇用保険会社負担が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 雇用保拠出金（会社)数字チェック
			if(eplyInsWithdraw) {
				number = false;
				model.addAttribute("eplyInsWithdrawnumber", "雇用保拠出金（会社)が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 労災保険（会社負担のみ）数字チェック
			if(wkAcccpsIns) {
				number = false;
				model.addAttribute("wkAcccpsInsnumber", "労災保険（会社負担のみ）が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 源泉控除数字チェック
			if(withholdingTax) {
				number = false;
				model.addAttribute("withholdingTaxnumber", "源泉控除が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 住民税控除数字チェック
			if(municipalTax) {
				number = false;
				model.addAttribute("municipalTaxnumber", "住民税控除が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 社宅家賃控除数字チェック
			if(rental) {
				number = false;
				model.addAttribute("rentalnumber", "社宅家賃控除が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// 社宅共益費控除数字チェック
			if(rentalMgmtFee) {
				number = false;
				model.addAttribute("rentalMgmtFeenumber", "社宅共益費控除が数字を入力してください。例：2000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			//総費用数字チェック
			if(totalFee) {
				number = false;
				model.addAttribute("totalFeenumber", "総費用が数字を入力してください。例：60,000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			//総額数字チェック
			if(sum) {
				number = false;
				model.addAttribute("sumnumber", "総額が数字を入力してください。例：60,000");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
				    //変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			// NotNullチェック。
			if (result.hasErrors()) {
				number = false;
				//エラーメッセージ。
				model.addAttribute("errors", result.getFieldErrors());
				 //作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
					//変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			 }
			// 支払日数字チェック。
			if(paymentDate) {
				// 支払日が過去日チェック用
				date = false ;
				number = false;
				model.addAttribute("paymentDatenumber", "支払日が数字を入力してください。例：2021/06/15");
				//作成場合
				if(null == ss || ss.size() ==0) {
					//作成ボタン用
					String cc = "作成";
					model.addAttribute("button",cc);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String k="1";
					model.addAttribute("MakeDistinction",k);
					//変更場合
				}else {
					//変更ボタン用
					String bb = "変更";
					model.addAttribute("button",bb);
					model.addAttribute("salaryInfo",rtn);
					//登録ボタンを押す時、作成と更新を分ける用。
					String o="2";
					model.addAttribute("MakeDistinction",o);
				}
			}
			if(date) {
				// 支払日が通常の日付チェック
				if(isdate==false) {
					dateless = false;
					number = false;
					model.addAttribute("paymentDateisDate", "支払日がを通常の日付を入力してください、例：”2021/06/30”");
					//作成場合
					if(null == ss || ss.size() ==0) {
						//作成ボタン用
						String cc = "作成";
						model.addAttribute("button",cc);
						model.addAttribute("salaryInfo",rtn);
						//登録ボタンを押す時、作成と更新を分ける用。
						String k="1";
						model.addAttribute("MakeDistinction",k);
						//変更場合
					}else {
						//変更ボタン用
						String bb = "変更";
						model.addAttribute("button",bb);
						model.addAttribute("salaryInfo",rtn);
						//登録ボタンを押す時、作成と更新を分ける用。
						String o="2";
						model.addAttribute("MakeDistinction",o);
					}
				}
				if (dateless) {
					// 支払日が過去日チェック。
					if(DateUtil.isLessThanNow(salarylistBean3.getPaymentDate())) {
						number = false;
						model.addAttribute("paymentDateisLess", "支払日がを未来の日付を入力してください");
						//作成場合
						if(null == ss || ss.size() ==0) {
							//作成ボタン用
							String cc = "作成";
							model.addAttribute("button",cc);
							model.addAttribute("salaryInfo",rtn);
							//登録ボタンを押す時、作成と更新を分ける用。
							String k="1";
							model.addAttribute("MakeDistinction",k);
							//変更場合
						}else {
							//変更ボタン用
							String bb = "変更";
							model.addAttribute("button",bb);
							model.addAttribute("salaryInfo",rtn);
							//登録ボタンを押す時、作成と更新を分ける用。
							String o="2";
							model.addAttribute("MakeDistinction",o);
						}
					}
				}
			}
			if(number) {
				//登録ボタンを押す時.作成場合
				if(salarylistBean3.getMakeDistinction().equals("1") ) {
					SalaryInfocommom2 am = new SalaryInfocommom2();
					am.setEmployeeID(salarylistBean3.getEmployeeIDb());
					am.setMonth(DateUtil.chgMonthToYM(salarylistBean3.getMonth()));
					am.setPaymentDate(DateUtil.chgMonthToYM(salarylistBean3.getPaymentDate()));
					am.setBase(DateUtil.chgMonthToYM1(salarylistBean3.getBase()));
					am.setOverTimePlus(salarylistBean3.getOverTimePlus());
					am.setShortageReduce(salarylistBean3.getShortageReduce());
					am.setTransportExpense(salarylistBean3.getTransportExpense());
					am.setAllowancePlus(salarylistBean3.getAllowancePlus());
					am.setAllowanceReduce(salarylistBean3.getAllowanceReduce());
					am.setAllowanceReason(salarylistBean3.getAllowanceReason());
					am.setWelfarePensionSelf(salarylistBean3.getWelfarePensionSelf());
					am.setWelfareHealthSelf(salarylistBean3.getWelfareHealthSelf());
					am.setEplyInsSelf(salarylistBean3.getEplyInsSelf());
					am.setWithholdingTax(salarylistBean3.getWithholdingTax());
					am.setMunicipalTax(salarylistBean3.getMunicipalTax());
					am.setRental(salarylistBean3.getRental());
					am.setRentalMgmtFee(salarylistBean3.getRentalMgmtFee());
					am.setSum(DateUtil.chgMonthToYM1(salarylistBean3.getSum()));
					am.setRemark(salarylistBean3.getRemark());
					am.setTotalFee(DateUtil.chgMonthToYM1(salarylistBean3.getTotalFee()));
					am.setWkAcccpsIns(salarylistBean3.getWkAcccpsIns());
					am.setEplyInsWithdraw(salarylistBean3.getEplyInsWithdraw());
					am.setEplyInsComp(salarylistBean3.getEplyInsComp());
					am.setWelfareBaby(salarylistBean3.getWelfareBaby());
					am.setWelfarePensionComp(salarylistBean3.getWelfarePensionComp());
					am.setWelfareHealthComp(salarylistBean3.getWelfareHealthComp());
					am.setOverTime(salarylistBean3.getOverTime());
					am.setShortage(salarylistBean3.getShortage());
					am.setWelfareSelf(salarylistBean3.getWelfareSelf());
					am.setWelfareComp(salarylistBean3.getWelfareComp());
					// DBまで給料作成情報を作成。
				    salaryInfoService.setSalaryInfo2(am);
				    //作成成功後、画面の表示。
				    return "hello3";
				    //登録ボタンを押す時.変更場合
				}else if(salarylistBean3.getMakeDistinction().equals("2")) {
						SalaryInfocommom2 lm = new SalaryInfocommom2();
						lm.setEmployeeID(salarylistBean3.getEmployeeIDb());
						lm.setMonth(DateUtil.chgMonthToYM(salarylistBean3.getMonth()));
						lm.setPaymentDate(DateUtil.chgMonthToYM(salarylistBean3.getPaymentDate()));
						lm.setBase(DateUtil.chgMonthToYM1(salarylistBean3.getBase()));
						lm.setOverTimePlus(salarylistBean3.getOverTimePlus());
						lm.setShortageReduce(salarylistBean3.getShortageReduce());
						lm.setTransportExpense(salarylistBean3.getTransportExpense());
						lm.setAllowancePlus(salarylistBean3.getAllowancePlus());
						lm.setAllowanceReduce(salarylistBean3.getAllowanceReduce());
						lm.setAllowanceReason(salarylistBean3.getAllowanceReason());
						lm.setWelfarePensionSelf(salarylistBean3.getWelfarePensionSelf());
						lm.setWelfareHealthSelf(salarylistBean3.getWelfareHealthSelf());
						lm.setEplyInsSelf(salarylistBean3.getEplyInsSelf());
						lm.setWithholdingTax(salarylistBean3.getWithholdingTax());
						lm.setMunicipalTax(salarylistBean3.getMunicipalTax());
						lm.setRental(salarylistBean3.getRental());
						lm.setRentalMgmtFee(salarylistBean3.getRentalMgmtFee());
						lm.setSum(DateUtil.chgMonthToYM1(salarylistBean3.getSum()));
						lm.setRemark(salarylistBean3.getRemark());
						lm.setTotalFee(DateUtil.chgMonthToYM1(salarylistBean3.getTotalFee()));
						lm.setWkAcccpsIns(salarylistBean3.getWkAcccpsIns());
						lm.setEplyInsWithdraw(salarylistBean3.getEplyInsWithdraw());
						lm.setEplyInsComp(salarylistBean3.getEplyInsComp());
						lm.setWelfareBaby(salarylistBean3.getWelfareBaby());
						lm.setWelfarePensionComp(salarylistBean3.getWelfarePensionComp());
						lm.setWelfareHealthComp(salarylistBean3.getWelfareHealthComp());
						lm.setOverTime(salarylistBean3.getOverTime());
						lm.setShortage(salarylistBean3.getShortage());
						lm.setWelfareSelf(salarylistBean3.getWelfareSelf());
						lm.setWelfareComp(salarylistBean3.getWelfareComp());
						// DBまで給料作成情報を更新。
					    salaryInfoService.setSalaryInfo3(lm);
					    //更新成功後、画面の表示。
					    return "hello2";

					}
			}
		}
					return "salaryInfo";
	}
}
