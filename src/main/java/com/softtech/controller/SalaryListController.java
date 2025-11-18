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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.actionForm.SalarylistBean2;
import com.softtech.common.AutoSalaryRtn;
import com.softtech.common.SalaryInfoRecord;
import com.softtech.entity.SalaryInfoEntity;
import com.softtech.mappers.EmployeeInfoMapper;
import com.softtech.mappers.SalarylistMapper;
import com.softtech.service.SalaryInfoService;
import com.softtech.service.SalaryListService;
import com.softtech.util.DateUtil;
import com.softtech.util.FileUtil;
/**
 *  Menu処理する。
 *
 * @author Softtech
 * @since  2021/04/10
 */
@Controller
//@RequestMapping("/emsm")
public class SalaryListController {

	@Autowired
	SalaryListService salarylistService;
	@Autowired
	SalaryInfoService salaryInfoService;
	@Autowired
	SalarylistMapper salarylistMapper;
	@Autowired
	EmployeeInfoMapper employeeInfoMapper;

	/**
	 *    給料リスト画面初期処理
	 *    給料リスト画面へ遷移する。
	 * @param  モデル
	 */
	@RequestMapping(value = {"/salarylist"})
	public String home(Model model) {
		//現在年月取得
		String month = DateUtil.getNowMonth();
		model.addAttribute("month",month);
		// DBから給料情報を取得
		List<SalaryInfoEntity> sl= salarylistService.querySalarylist(month);
		model.addAttribute("list",sl);
		return "salarylist";
	}
	/**
	 *   給料リスト処理。
	 *   給料リスト処理。
	 * @param  モデル
	 */
	@RequestMapping(value = "salarylist", method = RequestMethod.POST)
	public String SalarylistSubmit(HttpServletResponse response, @Valid SalarylistBean2 salarylistBean2,
			BindingResult result, Model model) {
		// NotNullの入力した年月をチェック。
		if (result.hasErrors()) {
			model.addAttribute("errors", result.getFieldErrors());
					return "salarylist";
				 }
        model.addAttribute("month",salarylistBean2.getMonth());
		// 入力した年月を持っち、DBから給料情報を取得
	     List<SalaryInfoEntity> sl = salarylistService.querySalarylist(salarylistBean2.getMonth());

		// データダウンロード場合
		if(salarylistBean2.getDownloadFlg()==2){
			 FileUtil ft = new FileUtil();
			 boolean rtn = ft.salaryDownload(response,sl);
			 if(!rtn) {
				 // エラーメッセージを設定して、画面表示
			 }else {
				 //画面表示用データを設定する。
//				 model.addAttribute("month", salarylistBean2.getMonth());
			 }
			 // 検索する場合
		 } else if(salarylistBean2.getDownloadFlg()==1){
			     model.addAttribute("list", sl);
		 // 画面の給料リスト中の社員IDを押す時。

		 //  給料変更画面へ遷移
		 }else if(salarylistBean2.getDownloadFlg()==3) {
			 SalaryInfoRecord em = new SalaryInfoRecord();

			 //社員ID
			em.setEmployeeID(salarylistBean2.getEmployeeIDFlg());
			//対象年月と対象年月YYYY/MM→yyyymmに変換
			em.setMonth(DateUtil.chgMonthToYM(salarylistBean2.getMonth()));

			// DBから社員IDの対象年月の給料情報を取得
			SalaryInfoEntity salaryInfoDB= salaryInfoService.querySalaryInfo(em);
			SalaryInfoBean salaryInfoBean=salaryInfoService.transferToGamen(salaryInfoDB);

			// 初期モードに設定
			//salaryInfoBean.setGamenMode("1");
			model.addAttribute("salaryInfoBean",salaryInfoBean);

		    return "salaryInfo";
		 }
		return "salarylist";
	}

	/**
	 *  一括作成
	 * @param  モデル
	 * @throws ParseException
	 */
	@RequestMapping(value = "/autoCreate", method = RequestMethod.POST)
	public String autoCreate(Model model) {
	    // 年月採番取得
	    String newMonth;
	    try {
	        newMonth = salarylistService.getNextMonth();

	        // 給料自動作成
	        AutoSalaryRtn rtn = salarylistService.autoCreate(newMonth);

	        if (!"0".equals(rtn.getRtn())) {
	            // エラーメッセージを表示する
	            List<FieldError> lst = new ArrayList<FieldError>();

	            switch (rtn.getRtn()) {
	                case "1":
	                    lst.add(new FieldError("", "", rtn.getEmplyeeName() + "の【対象年度】年度の基本給のデータが足りません。"));
	                    break;
	                case "2":
	                    lst.add(new FieldError("", "", rtn.getYear() + "の【対象年度】年度の厚生保険料のデータが足りません。"));
	                    break;
	                case "3":
	                    lst.add(new FieldError("", "", rtn.getEmplyeeName() + "の【対象社員】勤怠情報のデータが足りません。"));
	                    break;
	                case "4":
	                    lst.add(new FieldError("", "", rtn.getEmplyeeName() + "の【対象月】の交通情報のデータが足りません。"));
	                    break;
	                case "5":
	                    lst.add(new FieldError("", "", rtn.getYear() + "の【対象年度】年度の雇用保険率データがありません。"));
	                    break;
	                case "6":
	                    lst.add(new FieldError("", "", rtn.getEmplyeeName() + "の【対象年月】住民税住宅のデータがありません。"));
	                    break;
	                case "7":
	                    lst.add(new FieldError("", "", rtn.getYear() + "のマスタ_厚生子育徴収率データが存在していません。"));
	                    break;
	                case "8":
	                    lst.add(new FieldError("", "", rtn.getEmplyeeName() + "の【対象年月】所得税のデータがありません。"));
	                case "99":
	                    lst.add(new FieldError("", "", "給料テーブルに新規追加エラー。"));
	                    break;
	                default:
	                    lst.add(new FieldError("", "", "不明なエラーが発生しました。"));
	                    break;
	            }

	            model.addAttribute("errors", lst);
	        } else {
	            model.addAttribute("month", newMonth);
	            // DBから給料情報を取得
	            List<SalaryInfoEntity> sl = salarylistService.querySalarylist(newMonth);
	            model.addAttribute("list", sl);
	            return "salarylist";
	        }
	    } catch (ParseException e) {
	        model.addAttribute("errors", "作成に失敗しました。" + e.getMessage());
	    }
	    return "salarylist";
	}
}








