package com.softtech.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.common.EmployeeIDName;
import com.softtech.common.SalaryInfoRecord;
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
			SalaryInfoRecord salaryInfoRecord= new SalaryInfoRecord();

			 //社員ID
			 String employeeID=employeeIDName.getEmployeeID();
			 salaryInfoRecord.setEmployeeID(employeeID);

			//対象月
			 salaryInfoRecord.setMonth(nextMonth);

			 //支払日
			 salaryInfoRecord.setPaymentDate(paymentDate);

			 //③基本給を取得
			 BaseSalaryInfoEntity baseSalaryInfoEntity=salarylistMapper.getBaseSalary(employeeID,year);
			 String basesalary = baseSalaryInfoEntity.getBaseSalary();
			 salaryInfoRecord.setBase(Integer.parseInt(basesalary));

			 //残業時間
			 WorkInfo workInfo=salarylistMapper.getWkTime(employeeID,maxMonth);
			 // 稼働時間-稼働時間TO
			 float overTime= Float.parseFloat( workInfo.getWorkTime()) -   Float.parseFloat(baseSalaryInfoEntity.getWkPeriodTo());
			 if (overTime <= 0) {
				    overTime = 0;
			 }
			 salaryInfoRecord.setOverTime((int)overTime);

			 //不足時間（稼働時間FROM-稼働時間）
			 float shortage =Float.parseFloat(baseSalaryInfoEntity.getWkPeriodFrom())-Float.parseFloat( workInfo.getWorkTime());
			 if (shortage <= 0) {
				 shortage = 0;
			 }
			 salaryInfoRecord.setShortage((int)shortage);

			 //残業加算
//			 float overTimePlus= Float.parseFloat(baseSalaryInfoEntity.getPlusHour()) *  overTime;
//			 salaryInfoEntity.setOverTimePlus(Float.toString(overTimePlus));

			 float overTimePlus= Float.parseFloat(baseSalaryInfoEntity.getPlusHour()) *  overTime;
			 salaryInfoRecord.setOverTimePlus ((int)overTimePlus);

			 //稼働不足減
			 float shortageReduce=Float.parseFloat(baseSalaryInfoEntity.getMinusHour()) * shortage ;
			 salaryInfoRecord.setShortageReduce ( (int) shortageReduce);

			 //交通費
			 TransportEntity transportEntity=salarylistMapper.getTransportExpense(employeeID, maxMonth);
			 float transportExpense =transportEntity.getTransport() + Float.parseFloat(transportEntity.getBusinessTrip());
			 salaryInfoRecord.setTransportExpense((int)transportExpense);

			 //特別加算
			 float specialAddition = 0 ;
			 salaryInfoRecord.setSpecialAddition ((int)specialAddition);
			 //手当加算
			 float allowancePlus = 0 ;
			 salaryInfoRecord.setAllowancePlus( (int) allowancePlus);
			 //手当減算
			 float allowanceReduce = 0 ;
			 salaryInfoRecord.setAllowanceReduce ((int)allowanceReduce);
			 //理由
			 String allowanceReason = "" ;
			 salaryInfoRecord.setAllowanceReason(allowanceReason);

			 //厚生マスタを取る
			 WelfarefeeInfoEntity welfarefeeInfoEntity = salarylistMapper.getWfPension(year);
			//厚生年金控除個人
			float wfPensionSelf =
					 Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(basesalary);
			salaryInfoRecord.setWelfarePensionSelf((int)wfPensionSelf);

			 //厚生年金控除会社
			float wfPensionComp = wfPensionSelf;
			salaryInfoRecord.setWelfarePensionComp((int)wfPensionComp);

			 //社員年齢を取る
			String age=salarylistMapper.getAge(employeeID);
			 //厚生健康控除個人
			float wfHealthSelf;
			//厚生健康控除会社
			float wfHealthComp;
			if ( Float.parseFloat(age) >= 40) {
				 wfHealthSelf =
						 Float.parseFloat(welfarefeeInfoEntity.getCareRatio()) * Float.parseFloat(basesalary);
				 salaryInfoRecord.setWelfareHealthSelf( (int)wfHealthSelf);
				 wfHealthComp =wfHealthSelf;
				 salaryInfoRecord.setWelfareHealthComp((int)wfHealthComp);

			}else {
					wfHealthSelf =
							Float.parseFloat(welfarefeeInfoEntity.getNotCareRatio())* Float.parseFloat(basesalary);
					salaryInfoRecord.setWelfareHealthSelf((int)wfHealthSelf);
					wfHealthComp=wfHealthSelf;
					salaryInfoRecord.setWelfareHealthComp((int)wfHealthComp);
			}

			 //厚生控除子育(会社)
			 WelfareBabyInfoEntity welfareBabyInfoEntity = salarylistMapper.getWfBaby(year) ;
			 float welfareBaby =
					 Float.parseFloat(welfareBabyInfoEntity.getRate()) * Float.parseFloat(basesalary) ;
			 salaryInfoRecord.setWelfareBaby((int)welfareBaby);

			//雇用保険率を取る
			 EmplyinsrateInfoEntity emplyinsrateInfoEntity = salarylistMapper.getEmplyinsrate(year) ;
			 //雇用保険個人負担
			 float LaborBurden =
					 Float.parseFloat(emplyinsrateInfoEntity.getLaborBurdenRate())*Float.parseFloat(basesalary) ;
			 salaryInfoRecord.setEplyInsSelf((int)LaborBurden);

			 //雇用保険会社負担
			 float employerBurden =
					 Float.parseFloat(emplyinsrateInfoEntity.getEmployerBurdenRate())*Float.parseFloat(basesalary) ;
			 salaryInfoRecord.setEplyInsComp((int)employerBurden);

			 //雇用保拠出金（会社)
			 float employmentInsurance =
					 Float.parseFloat(emplyinsrateInfoEntity.getEmploymentInsuranceRate())*Float.parseFloat(basesalary) ;
			 salaryInfoRecord.setEplyInsWithdraw((int)employmentInsurance);

			 //労災保険（会社負担のみ）
			 float industrialAccidentInsurance =
			 Float.parseFloat(emplyinsrateInfoEntity.getIndustrialAccidentInsuranceRate())*Float.parseFloat(basesalary) ;
			 salaryInfoRecord.setWkAcccpsIns((int)industrialAccidentInsurance);

			 //源泉控除
			 IncomeTaxInfoEntity incomeTaxInfoEntity = salarylistMapper.getTax(employeeID, year);
			 String month = maxMonth.substring(4, 6);
			 //源泉
			 float incomeTax = 0;
			 if (month .equals ("01")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax1());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }else if (month.equals ( "02")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax2());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }else if (month.equals ( "03")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax3());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }else if (month.equals ( "04")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax4());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }else if (month.equals ( "05")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax5());
				 salaryInfoRecord.setWithholdingTax( (int)incomeTax);
			 }else if (month.equals ( "06")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax6());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }else if (month.equals ( "07")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax7());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }else if (month.equals ( "08")) {
				 incomeTax = Float.parseFloat( incomeTaxInfoEntity.getIncomeTax8());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }else if (month.equals ( "09")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax9());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }else if (month.equals ( "10")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax10());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }else if (month.equals ( "11")) {
				 incomeTax =  Float.parseFloat(incomeTaxInfoEntity.getIncomeTax11());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }else if (month.equals ( "12")) {
				 incomeTax = Float.parseFloat( incomeTaxInfoEntity.getIncomeTax12());
				 salaryInfoRecord.setWithholdingTax((int)incomeTax);
			 }
			//住民税
			 float residentTax =0;
			 if (month .equals ("01")){
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax1());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }else if (month.equals ( "02")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax2());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }else if (month.equals ( "03")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax3());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }else if (month.equals ( "04")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax4());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }else if (month.equals ( "05")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax5());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }else if (month.equals ( "06")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax6());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }else if (month.equals ( "07")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax7());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }else if (month.equals ( "08")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax8());
				 salaryInfoRecord.setMunicipalTax( (int)residentTax);
			 }else if (month.equals ( "09")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax9());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }else if (month.equals ( "10")) {
				 residentTax = Float.parseFloat(incomeTaxInfoEntity.getResidentTax10());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }else if (month.equals ( "11")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax11());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }else if (month.equals ( "12")) {
				 residentTax =Float.parseFloat( incomeTaxInfoEntity.getResidentTax12());
				 salaryInfoRecord.setMunicipalTax((int)residentTax);
			 }
			 //社宅家賃控除
			 float rental  = 0 ;
			 salaryInfoRecord.setRental((int)rental);
			 //社宅共益費控除
			 float rentalMgmtFee = 0 ;
			 salaryInfoRecord.setRentalMgmtFee((int)rentalMgmtFee);
			//特別控除
			 float specialReduce = 0 ;
			 salaryInfoRecord.setSpecialReduce((int)specialReduce);

			 //総額
			 float sum= Float.parseFloat(basesalary) - shortageReduce - allowanceReduce  -
					 	wfPensionSelf- wfHealthSelf- LaborBurden -
					 	incomeTax- residentTax -
					 	 rental-rentalMgmtFee- specialReduce +
					 	 overTimePlus +transportExpense+ specialAddition + allowancePlus   ;
			 salaryInfoRecord.setSum((int)sum);

			 //総費用
			 float totalFee = sum + wfPensionComp + wfHealthComp + welfareBaby + employerBurden + employmentInsurance
					 + industrialAccidentInsurance ;
			 salaryInfoRecord.setTotalFee((int)totalFee);

			 //備考
			 String remark = "" ;
			 salaryInfoRecord.setRemark(remark);

			 //削除フラグ
			 String deleteFlg = "0";
			 salaryInfoRecord.setDeleteFlg(deleteFlg);

			 salarylistMapper.insertSalaryList(salaryInfoRecord);

//			 SalaryInfoEntity salaryInfoEntity1=salarylistMapper.insertSalaryList(salaryInfoEntity);

//			 BaseSalaryInfoEntity baseSalaryInfoEntity=salarylistMapper.getBaseSalary(employeeID,year);
//			 String basesalary = baseSalaryInfoEntity.getBaseSalary();
//			 salaryInfoEntity.setBase(basesalary);
			 ;

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
