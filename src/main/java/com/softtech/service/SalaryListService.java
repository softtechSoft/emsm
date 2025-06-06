package com.softtech.service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.common.AutoSalaryRtn;
import com.softtech.common.EmployeeIDName;
import com.softtech.entity.BaseSalaryInfoEntity;
import com.softtech.entity.EmplyinsrateInfoEntity;
import com.softtech.entity.IncomeTaxInfoEntity;
import com.softtech.entity.SalaryInfoEntity;
import com.softtech.entity.TransportEntity;
import com.softtech.entity.WelfareBabyInfoEntity;
import com.softtech.entity.WelfarefeeInfoEntity;
import com.softtech.entity.WorkInfo;
import com.softtech.mappers.SalarylistMapper;
import com.softtech.util.DateUtil;
/**
 * 概要：Salarylistのservice
 *
 * 作成者：王＠ソフトテク
 * 作成日：2021/05/10
 */
@Service
public class SalaryListService {
	@Autowired
	SalarylistMapper salarylistMapper;
	@Autowired
	LoginService loginService;

	/**
	 * 機能：DBから取得したデータを取得する。
	 *
	 * @param lst DBから取得したデータ
	 * @return 給料情報リスト
	 *
	 * @author 王@ソフトテク
	 */

	public List<SalaryInfoEntity> querySalarylist(String month) {

        // YYYY/MM→yyyymmに変換
		String monthP = DateUtil.chgMonthToYM(month);

		// DBからデータを取得する
		// 給料情報を取得する
		List<SalaryInfoEntity> salaryinfolist =  salarylistMapper.getSalaryinfolist(monthP);

		return  salaryinfolist;
	}

	/*
	 * 機能：給料の自動作成
	 *
	 * @param なし
	 * @return True：成功、False：失敗
	 *
	 * @author YADANAR@ソフトテク
	 */
	public String getNextMonth() throws ParseException {
		//①年月採番
		//給料テーブルから最大年月（month）を取得

		String maxMonth=salarylistMapper.getMaxMonth();
		
		// 空表の場合の処理
		if (maxMonth == null || maxMonth.isEmpty()) {
	        // 現在の年月を取得（yyyy/MM形式）
	        String currentMonth = DateUtil.getNowMonth();
	        // yyyy/MM → yyyyMM形式に変換
	        return DateUtil.chgMonthToYM(currentMonth);
	    }

		//最大年月の次の年月を取得
		String nextMonth=DateUtil.monthplus(maxMonth);
		return nextMonth;
	}

	/*
	 * 機能：給料の自動作成
	 *
	 * @param 対象年月
	 * @return 結果
	 *
	 *
	 * @author YADANAR@ソフトテク
	 */

	public AutoSalaryRtn autoCreate(String nextMonth) throws ParseException {

		AutoSalaryRtn autoSalaryRtn= new AutoSalaryRtn();
		//成功
		autoSalaryRtn.setRtn("0");


		//年月採番
		//対象年度
		 String year=DateUtil.getNowYear() ;

		//支払日
		 String paymentDate=DateUtil.getPayMonth(nextMonth);

		//②社員リストを取得
		List<EmployeeIDName> employeeList = loginService.getEmployeeList();

		for(EmployeeIDName employeeIDName:employeeList){
			//給料明細新規
			SalaryInfoEntity salaryInfoEntity= new SalaryInfoEntity();

			 //社員ID
			 String employeeID=employeeIDName.getEmployeeID();
			 salaryInfoEntity.setEmployeeID(employeeID);

			//対象月
			 salaryInfoEntity.setMonth(nextMonth);

			 //支払日
			 salaryInfoEntity.setPaymentDate(paymentDate);

			 //④基本給を取得
			 BaseSalaryInfoEntity baseSalaryInfoEntity=salarylistMapper.getBaseSalary(employeeID,year);
			 if( baseSalaryInfoEntity == null ||  baseSalaryInfoEntity.getBaseSalary() == null || baseSalaryInfoEntity.getBaseSalary().length()==0) {
				 autoSalaryRtn.setEmplyeeName(employeeIDName.getEmployeeName());
				 autoSalaryRtn.setYearMonth(nextMonth);
				 autoSalaryRtn.setRtn("1");
				 return autoSalaryRtn;
			 }

			 String basesalary = baseSalaryInfoEntity.getBaseSalary();
			 salaryInfoEntity.setBase(basesalary);

			 //残業時間
			 //⑥勤怠情報を取得
			 WorkInfo workInfo=salarylistMapper.getWkTime(employeeID,nextMonth);
			 if( workInfo == null ) {
				 autoSalaryRtn.setEmplyeeName(employeeIDName.getEmployeeName());
				 autoSalaryRtn.setYearMonth(nextMonth);
				 autoSalaryRtn.setRtn("3");
				 return autoSalaryRtn;
			 }

			 // 稼働時間-稼働時間TO
			 float overTime= Float.parseFloat( workInfo.getWorkTime()) -   Float.parseFloat(baseSalaryInfoEntity.getWkPeriodTo());
			 if (overTime <= 0) {
				    overTime = 0;
			 }

			 salaryInfoEntity.setOverTime(Float.toString(overTime));

			 //不足時間（稼働時間FROM-稼働時間）
			 float shortage =Float.parseFloat(baseSalaryInfoEntity.getWkPeriodFrom())-Float.parseFloat( workInfo.getWorkTime());
			 if (shortage <= 0) {
				 shortage = 0;
			 }

			 salaryInfoEntity.setShortage(Float.toString(shortage));

			 //残業加算

			 float overTimePlus= Float.parseFloat(baseSalaryInfoEntity.getOvertimePay()) *  overTime;

			 salaryInfoEntity.setOverTimePlus (Float.toString(overTimePlus));

			 //稼働不足減
			 float shortageReduce=Float.parseFloat(baseSalaryInfoEntity.getInsufficienttimePay()) * shortage ;

			 salaryInfoEntity.setShortageReduce ( Float.toString( shortageReduce));

			 //交通費
			 //⑦交通情報を取得
			 TransportEntity transportEntity=salarylistMapper.getTransportExpense(employeeID, nextMonth);
			 if( transportEntity == null ||  transportEntity.getTransportExpense() == null || transportEntity.getTransportExpense().length()==0) {
				 autoSalaryRtn.setEmplyeeName(employeeIDName.getEmployeeName());
				 autoSalaryRtn.setYearMonth(nextMonth);
				 autoSalaryRtn.setRtn("4");
				 return autoSalaryRtn;
			 }

			 float transportExpense =transportEntity.getTransport() + Float.parseFloat(transportEntity.getBusinessTrip());
			 salaryInfoEntity.setTransportExpense(Float.toString(transportExpense));

			 //特別加算
			 float specialAddition = 0 ;
			 salaryInfoEntity.setSpecialAddition (Float.toString(specialAddition));
			 //手当加算
			 float allowancePlus = 0 ;
			 salaryInfoEntity.setAllowancePlus( Float.toString( allowancePlus));
			 //手当減算
			 float allowanceReduce = 0 ;
			 salaryInfoEntity.setAllowanceReduce (Float.toString(allowanceReduce));
			 //理由
			 String allowanceReason = "" ;
			 salaryInfoEntity.setAllowanceReason(allowanceReason);

			 //厚生マスタを取る
			 //⑤厚生保険料を取得
			 WelfarefeeInfoEntity welfarefeeInfoEntity = salarylistMapper.getWfPension(basesalary);
//			 if( welfarefeeInfoEntity == null ||  welfarefeeInfoEntity.getWfPension() == null || welfarefeeInfoEntity.getWfPension().length()==0) {
			 if( welfarefeeInfoEntity == null) {
				 //autoSalaryRtn.setEmplyeeName(employeeIDName.getEmployeeName());
				 autoSalaryRtn.setYear(year);
				 autoSalaryRtn.setRtn("2");
				 return autoSalaryRtn;
			 }

			//厚生年金控除個人
			float wfPensionSelf =
					(( Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(welfarefeeInfoEntity.getStandSalary()))/100)/2;
			salaryInfoEntity.setWelfarePensionSelf(Float.toString(wfPensionSelf));


			 //厚生年金控除会社
			float wfPensionComp = wfPensionSelf;
			salaryInfoEntity.setWelfarePensionComp(Float.toString(wfPensionComp));

			 //社員年齢を取る
			String age=salarylistMapper.getAge(employeeID);
			 //厚生健康控除個人
			float wfHealthSelf;
			//厚生健康控除会社
			float wfHealthComp;
			if ( Float.parseFloat(age) >= 40) {
				 wfHealthSelf =
						 ((Float.parseFloat(welfarefeeInfoEntity.getCareRatio()) * Float.parseFloat(welfarefeeInfoEntity.getStandSalary()))/100)/2;
				 salaryInfoEntity.setWelfareHealthSelf( Float.toString(wfHealthSelf));
				 wfHealthComp =wfHealthSelf;
				 salaryInfoEntity.setWelfareHealthComp(Float.toString(wfHealthComp));

			}else {
					wfHealthSelf =
							((Float.parseFloat(welfarefeeInfoEntity.getNotCareRatio())* Float.parseFloat(welfarefeeInfoEntity.getStandSalary()))/100)/2;
					salaryInfoEntity.setWelfareHealthSelf(Float.toString(wfHealthSelf));
					wfHealthComp=wfHealthSelf;
					salaryInfoEntity.setWelfareHealthComp(Float.toString(wfHealthComp));
			}

			 //厚生控除子育(会社)
			//⑩厚生子育徴収率を取得
			 WelfareBabyInfoEntity welfareBabyInfoEntity = salarylistMapper.getWfBaby(year) ;
//			 if( welfareBabyInfoEntity == null ||  welfareBabyInfoEntity.getWfBaby() == null || welfareBabyInfoEntity.getWfBaby().length()==0) {
//				 autoSalaryRtn.setEmplyeeName(employeeIDName.getEmployeeName());
//				 autoSalaryRtn.setYearMonth(nextMonth);
//				 autoSalaryRtn.setRtn("7");
//				 return autoSalaryRtn;
//			 }

			 float welfareBaby =
					 (Float.parseFloat(welfareBabyInfoEntity.getRate()) * Float.parseFloat(basesalary)) / 1000 ;
			 salaryInfoEntity.setWelfareBaby(Float.toString(welfareBaby));

			//⑧雇用保険率をを取得
			 EmplyinsrateInfoEntity emplyinsrateInfoEntity = salarylistMapper.getEmplyinsrate(year) ;
//			 if( emplyinsrateInfoEntity == null ||  emplyinsrateInfoEntity.getEmplyinsrate() == null || emplyinsrateInfoEntity.getEmplyinsrate().length()==0) {
//				 //autoSalaryRtn.setEmplyeeName(employeeIDName.getEmployeeName());
//				 autoSalaryRtn.setYear(nextMonth);
//				 autoSalaryRtn.setRtn("5");
//				 return autoSalaryRtn;
//			 }

			 //雇用保険個人負担
			 float laborBurden =
					( Float.parseFloat(emplyinsrateInfoEntity.getLaborBurdenRate())*Float.parseFloat(basesalary))/1000 ;
			 salaryInfoEntity.setEplyInsSelf(Float.toString(laborBurden));

			 //雇用保険会社負担
			 float employerBurden =
					 (Float.parseFloat(emplyinsrateInfoEntity.getEmployerBurdenRate())*Float.parseFloat(basesalary))/1000 ;
			 salaryInfoEntity.setEplyInsComp(Float.toString(employerBurden));

			 //雇用保拠出金（会社)
			 float employmentInsurance =
					 (Float.parseFloat(emplyinsrateInfoEntity.getContributionRate())*Float.parseFloat(basesalary))/1000 ;
			 salaryInfoEntity.setEplyInsWithdraw(Float.toString(employmentInsurance));

			 //労災保険（会社負担のみ）
			 float industrialAccidentInsurance =
			 (Float.parseFloat(emplyinsrateInfoEntity.getIndustrialAccidentInsuranceRate())*Float.parseFloat(basesalary))/1000 ;
			 salaryInfoEntity.setWkAcccpsIns(Float.toString(industrialAccidentInsurance));

			 //源泉控除
			 //所得税と住民税を取得
			 IncomeTaxInfoEntity incomeTaxInfoEntity = salarylistMapper.getTax(employeeID, year);
//			 if( incomeTaxInfoEntity == null ||  incomeTaxInfoEntity.getTax() == null || incomeTaxInfoEntity.getTax().length()==0) {
//				 autoSalaryRtn.setEmplyeeName(employeeIDName.getEmployeeName());
//				 autoSalaryRtn.setYearMonth(nextMonth);
//				 autoSalaryRtn.setRtn("6");
//				 return autoSalaryRtn;
//			 }


			 String month = nextMonth.substring(4, 6);
			 //源泉
			 float incomeTax = 0;
			 if (month .equals ("01")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax1());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }else if (month.equals ( "02")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax2());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }else if (month.equals ( "03")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax3());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }else if (month.equals ( "04")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax4());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }else if (month.equals ( "05")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax5());
				 salaryInfoEntity.setWithholdingTax( Float.toString(incomeTax));
			 }else if (month.equals ( "06")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax6());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }else if (month.equals ( "07")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax7());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }else if (month.equals ( "08")) {
				 incomeTax = Float.parseFloat( incomeTaxInfoEntity.getIncomeTax8());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }else if (month.equals ( "09")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax9());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }else if (month.equals ( "10")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax10());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }else if (month.equals ( "11")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax11());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }else if (month.equals ( "12")) {
				 incomeTax = Float.parseFloat( incomeTaxInfoEntity.getIncomeTax12());
				 salaryInfoEntity.setWithholdingTax(Float.toString(incomeTax));
			 }
			//住民税
			 float residentTax =0;
			 if (month .equals ("01")){
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax1());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }else if (month.equals ( "02")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax2());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }else if (month.equals ( "03")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax3());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }else if (month.equals ( "04")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax4());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }else if (month.equals ( "05")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax5());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }else if (month.equals ( "06")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax6());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }else if (month.equals ( "07")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax7());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }else if (month.equals ( "08")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax8());
				 salaryInfoEntity.setMunicipalTax( Float.toString(residentTax));
			 }else if (month.equals ( "09")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax9());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }else if (month.equals ( "10")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax10());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }else if (month.equals ( "11")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax11());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }else if (month.equals ( "12")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax12());
				 salaryInfoEntity.setMunicipalTax(Float.toString(residentTax));
			 }
			 //社宅家賃控除
			 float rental  = 0 ;
			 salaryInfoEntity.setRental(Float.toString(rental));
			 //社宅共益費控除
			 float rentalMgmtFee = 0 ;
			 salaryInfoEntity.setRentalMgmtFee(Float.toString(rentalMgmtFee));
			//特別控除
			 float specialReduce = 0 ;
			 salaryInfoEntity.setSpecialReduce(Float.toString(specialReduce));

			 //総額
			 float sum= Float.parseFloat(basesalary) - shortageReduce - allowanceReduce  -
					 	wfPensionSelf- wfHealthSelf- laborBurden -
					 	incomeTax- residentTax -
					 	 rental-rentalMgmtFee- specialReduce +
					 	 overTimePlus +transportExpense+ specialAddition + allowancePlus   ;
			 salaryInfoEntity.setSum(Float.toString(sum));

			 //総費用
			 float totalFee = sum + wfPensionComp + wfHealthComp + welfareBaby + employerBurden + employmentInsurance
					 + industrialAccidentInsurance ;
			 salaryInfoEntity.setTotalFee(Float.toString(totalFee));

			 //備考
			 String remark = "" ;
			 salaryInfoEntity.setRemark(remark);

			 //削除フラグ
			 String deleteFlg = "0";
			 salaryInfoEntity.setDeleteFlg(deleteFlg);

			 LocalDateTime now = LocalDateTime.now();
		     DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");
		     String format = formatter.format(now);

		     salaryInfoEntity.setInsertDate(format);

		     salaryInfoEntity.setUpdateDate(format);

			 int insert= salarylistMapper.insertSalaryList(salaryInfoEntity);
		     if(insert != 1) {
		    	 //return false;
		    	 autoSalaryRtn.setRtn("99");
		     }

		}

//		給料テーブルに新規追加
		 //return true;
		 return autoSalaryRtn;
	}



	/*public List<MenuBean> queryOfcfunction(String string) {
		// 機能リストを取得する
		List<Ofcfunction> Ofcfunction = salarylistMapper.getSalaryinfolist(month);
		// 機能リストへ変更する。
		List<MenuBean> rtn  = menu(Ofcfunction);
		return  rtn;
	}
	//DBから取得したデータを機能リストへ変換する。
	private List<MenuBean>menu(List<Ofcfunction> lst){
		if(lst == null ) return new ArrayList<MenuBean>();

		List<MenuBean> rtn  =new ArrayList<MenuBean>();
		for(Ofcfunction tt : lst) {
			MenuBean menuBean = new MenuBean();
			menuBean.setFunctionText(tt.getFunctionText());
			menuBean.setFunctionLink(tt.getFunctionLink());
			menuBean.setDisplayNo(tt.getDisplayNo());
			rtn.add(menuBean);
		}

		return rtn;
	}*/

}
