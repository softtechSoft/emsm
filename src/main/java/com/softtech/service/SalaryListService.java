package com.softtech.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

	@Autowired
	LoginService loginService;
	public boolean autoCreate() throws ParseException {

		//①年月採番
		//給料テーブルから最大年月（month）を取得

		String maxMonth=salarylistMapper.getMaxMonth();

		//最大年月の次の年月を取得
		String nextMonth=DateUtil.monthplus(maxMonth);

		//対象年度
		 String year=DateUtil.getNowYear() ;

		//支払日
		 String paymentDate=DateUtil.getPayMonth(nextMonth);

		//②社員リストを取得
		List<EmployeeIDName> employeeList = loginService.getEmployeeList();

		for(EmployeeIDName employeeIDName:employeeList){
			//給料明細新規
			SalaryInfoEntity salaryInfoEntity = new SalaryInfoEntity();

			 //社員ID
			 String employeeID=employeeIDName.getEmployeeID();
			 salaryInfoEntity.setEmployeeID(employeeID);

			//対象月
			 salaryInfoEntity.setMonth(nextMonth);

			 //支払日
			 salaryInfoEntity.setPaymentDate(paymentDate);

			 //③基本給を取得
			 BaseSalaryInfoEntity baseSalaryInfoEntity=salarylistMapper.getBaseSalary(employeeID,year);
			 String basesalary = baseSalaryInfoEntity.getBaseSalary();
			 salaryInfoEntity.setBase(basesalary);

			 //残業時間
			 WorkInfo workInfo=salarylistMapper.getWkTime(employeeID,maxMonth);
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
//			 float overTimePlus= Float.parseFloat(baseSalaryInfoEntity.getPlusHour()) *  overTime;
//			 salaryInfoEntity.setOverTimePlus(Float.toString(overTimePlus));

			 float overTimePlus= Float.parseFloat(baseSalaryInfoEntity.getPlusHour()) *  overTime;
			 salaryInfoEntity.setOverTimePlus (String.format("%,d", (int)overTimePlus));

			 //稼働不足減
			 float shortageReduce=Float.parseFloat(baseSalaryInfoEntity.getMinusHour()) * shortage ;
			 salaryInfoEntity.setShortageReduce (String.format("%,d", (int) shortageReduce));

			 //交通費
			 TransportEntity transportEntity=salarylistMapper.getTransportExpense(employeeID, maxMonth);
			 float transportExpense =transportEntity.getTransport() + Float.parseFloat(transportEntity.getBusinessTrip());
			 salaryInfoEntity.setTransportExpense(String.format("%d",(int)transportExpense));

			 //特別加算
			 float specialAddition = 0 ;
			 salaryInfoEntity.setSpecialAddition (String.format("%,d", (int)specialAddition));
			 //手当加算
			 float allowancePlus = 0 ;
			 salaryInfoEntity.setAllowancePlus(String.format("%,d", (int) allowancePlus));
			 //手当減算
			 float allowanceReduce = 0 ;
			 salaryInfoEntity.setAllowanceReduce (String.format("%,d", (int)allowanceReduce));
			 //理由
			 String allowanceReason = "" ;
			 salaryInfoEntity.setAllowanceReason(allowanceReason);

			 //厚生マスタを取る
			 WelfarefeeInfoEntity welfarefeeInfoEntity = salarylistMapper.getWfPension(year);
			//厚生年金控除個人
			float wfPensionSelf =
					 Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(basesalary);
			salaryInfoEntity.setWelfarePensionSelf(String.format("%d", (int)wfPensionSelf));

			 //厚生年金控除会社
			float wfPensionComp = wfPensionSelf;
			salaryInfoEntity.setWelfarePensionComp(String.format("%d", (int)wfPensionComp));

			 //社員年齢を取る
			String age=salarylistMapper.getAge(employeeID);
			 //厚生健康控除個人
			float wfHealthSelf;
			//厚生健康控除会社
			float wfHealthComp;
			if ( Float.parseFloat(age) >= 40) {
				 wfHealthSelf =
						 Float.parseFloat(welfarefeeInfoEntity.getCareRatio()) * Float.parseFloat(basesalary);
				 salaryInfoEntity.setWelfareHealthSelf(String.format("%d", (int)wfHealthSelf));
				 wfHealthComp =wfHealthSelf;
				 salaryInfoEntity.setWelfareHealthComp(String.format("%d", (int)wfHealthComp));

			}else {
					wfHealthSelf =
							Float.parseFloat(welfarefeeInfoEntity.getNotCareRatio())* Float.parseFloat(basesalary);
					salaryInfoEntity.setWelfareHealthSelf(String.format("%d", (int)wfHealthSelf));
					wfHealthComp=wfHealthSelf;
					salaryInfoEntity.setWelfareHealthComp(String.format("%d", (int)wfHealthComp));
			}

			 //厚生控除子育(会社)
			 WelfareBabyInfoEntity welfareBabyInfoEntity = salarylistMapper.getWfBaby(year) ;
			 float welfareBaby =
					 Float.parseFloat(welfareBabyInfoEntity.getRate()) * Float.parseFloat(basesalary) ;
			 salaryInfoEntity.setWelfareBaby(String.format("%d", (int)welfareBaby));

			//雇用保険率を取る
			 EmplyinsrateInfoEntity emplyinsrateInfoEntity = salarylistMapper.getEmplyinsrate(year) ;
			 //雇用保険個人負担
			 float LaborBurden =
					 Float.parseFloat(emplyinsrateInfoEntity.getLaborBurdenRate())*Float.parseFloat(basesalary) ;
			 salaryInfoEntity.setEplyInsSelf(String.format("%d", (int)LaborBurden));

			 //雇用保険会社負担
			 float employerBurden =
					 Float.parseFloat(emplyinsrateInfoEntity.getEmployerBurdenRate())*Float.parseFloat(basesalary) ;
			 salaryInfoEntity.setEplyInsComp(String.format("%d", (int)employerBurden));

			 //雇用保拠出金（会社)
			 float employmentInsurance =
					 Float.parseFloat(emplyinsrateInfoEntity.getEmploymentInsuranceRate())*Float.parseFloat(basesalary) ;
			 salaryInfoEntity.setEplyInsWithdraw(String.format("%d", (int)employmentInsurance));

			 //労災保険（会社負担のみ）
			 float industrialAccidentInsurance =
			 Float.parseFloat(emplyinsrateInfoEntity.getIndustrialAccidentInsuranceRate())*Float.parseFloat(basesalary) ;
			 salaryInfoEntity.setWkAcccpsIns(String.format("%d", (int)industrialAccidentInsurance));

			 //源泉控除
			 IncomeTaxInfoEntity incomeTaxInfoEntity = salarylistMapper.getTax(employeeID, year);
			 String month = maxMonth.substring(4, 6);
			 //源泉
			 float incomeTax = 0;
			 if (month .equals ("01")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax1());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "02")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax2());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "03")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax3());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "04")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax4());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "05")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax5());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "06")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax6());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "07")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax7());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "08")) {
				 incomeTax = Float.parseFloat( incomeTaxInfoEntity.getIncomeTax8());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "09")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax9());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "10")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax10());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "11")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax11());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }else if (month.equals ( "12")) {
				 incomeTax = Float.parseFloat( incomeTaxInfoEntity.getIncomeTax12());
				 salaryInfoEntity.setWithholdingTax(String.format("%d", (int)incomeTax));
			 }
			//住民税
			 float residentTax =0;
			 if (month .equals ("01")){
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax1());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "02")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax2());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "03")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax3());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "04")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax4());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "05")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax5());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "06")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax6());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "07")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax7());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "08")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax8());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "09")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax9());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "10")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax10());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "11")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax11());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }else if (month.equals ( "12")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax12());
				 salaryInfoEntity.setMunicipalTax(String.format("%d", (int)residentTax));
			 }
			 //社宅家賃控除
			 float rental  = 0 ;
			 salaryInfoEntity.setRental(String.format("%d", (int)rental));
			 //社宅共益費控除
			 float rentalMgmtFee = 0 ;
			 salaryInfoEntity.setRentalMgmtFee(String.format("%d", (int)rentalMgmtFee));
			//特別控除
			 float specialReduce = 0 ;
			 salaryInfoEntity.setSpecialReduce(String.format("%d", (int)specialReduce));

			 //総額
			 float sum= Float.parseFloat(basesalary) - shortageReduce - allowanceReduce  -
					 	wfPensionSelf- wfHealthSelf- LaborBurden -
					 	incomeTax- residentTax -
					 	 rental-rentalMgmtFee- specialReduce +
					 	 overTimePlus +transportExpense+ specialAddition + allowancePlus   ;
			 salaryInfoEntity.setSum(String.format("%d", (int)sum));

			 //総費用
			 float totalFee = sum + wfPensionComp + wfHealthComp + welfareBaby + employerBurden + employmentInsurance
					 + industrialAccidentInsurance ;
			 salaryInfoEntity.setTotalFee(String.format("%d", (int)totalFee));

			 //備考
			 String remark = "" ;
			 salaryInfoEntity.setRemark(remark);

			 //削除フラグ
			 String deleteFlg = "0";
			 salaryInfoEntity.setDeleteFlg(deleteFlg);



		}


//		給料テーブルに新規追加
		 return true;
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
