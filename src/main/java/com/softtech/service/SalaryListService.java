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

		//②社員リストを取得

		List<EmployeeIDName> employeeList = loginService.getEmployeeList();

		 for(EmployeeIDName employeeIDName:employeeList){
			 //社員ID
			 String employeeID=employeeIDName.getEmployeeID();
			 //対象年度
			 String year=DateUtil.getNowYear() ;
			 //支払日
			 String paymentDate=DateUtil.getPayMonth(nextMonth);
			 //③基本給を取得
			 BaseSalaryInfoEntity baseSalaryInfoEntity=salarylistMapper.getBaseSalary(employeeID,year);
			 String basesalary = baseSalaryInfoEntity.getBaseSalary();

			 //残業時間
			 WorkInfo workInfo=salarylistMapper.getWkTime(employeeID,maxMonth);
			 float overTime= Float.parseFloat( workInfo.getWorkTime()) -   Float.parseFloat(baseSalaryInfoEntity.getWkPeriodTo());
			 if (overTime < 0) {
				    overTime = 0;
				}
//			 salaryInfoEntity.setoverTime(overTime);

			 //不足時間
			 float shortage =Float.parseFloat(baseSalaryInfoEntity.getWkPeriodFrom())-Float.parseFloat( workInfo.getWorkTime());
			 if (shortage < 0) {
				 shortage = 0;
				}

			 //残業加算
			 float overTimePlus= Float.parseFloat(baseSalaryInfoEntity.getPlusHour()) *  overTime;

			 //稼働不足減
			 float shortageReduce=Float.parseFloat(baseSalaryInfoEntity.getMinusHour()) * shortage ;

			 //交通費  transportinfoのtransport　＋　transportinfoのbusinessTrip  条件①対象社員、②monthの前月
			 TransportEntity transportEntity=salarylistMapper.getTransportExpense(employeeID, maxMonth);
			 float transportExpense = transportEntity.getTransport() + Float.parseFloat( transportEntity.getBusinessTrip());

			 //特別加算
			 String specialAddition = " 0" ;
			 //手当加算
			 String allowancePlus = " 0" ;
			 //手当減算
			 String allowanceReduce = " 0" ;
			 //理由
			 String allowanceReason = " 0" ;

			 WelfarefeeInfoEntity welfarefeeInfoEntity = salarylistMapper.getWfPension(year);
			//厚生年金控除個人
			 float wfPensionSelf =
					 Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(basesalary );

			 //厚生年金控除会社
			 float wfPensionComp	=
					 Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(basesalary);

			 String age=salarylistMapper.getAge(employeeID);
			 //厚生健康控除個人
			 float wfHealthSelf;
			 if ( Float.parseFloat(age) >= 40) {
				 wfHealthSelf =
						 Float.parseFloat(welfarefeeInfoEntity.getCareRatio()) * Float.parseFloat(basesalary);
				}else {
					wfHealthSelf =
							Float.parseFloat(welfarefeeInfoEntity.getNotCareRatio())* Float.parseFloat(basesalary);
				}
			 //厚生健康控除会社
			 float wfHealthComp;
			 if ( Float.parseFloat(age) < 40) {
				 wfHealthComp =
						 Float.parseFloat(welfarefeeInfoEntity.getNotCareRatio()) * Float.parseFloat(basesalary);
				}else {
					wfHealthComp =
							Float.parseFloat(welfarefeeInfoEntity.getCareRatio())* Float.parseFloat(basesalary);
				}

			 //厚生控除子育(会社)
			 WelfareBabyInfoEntity welfareBabyInfoEntity = salarylistMapper.getWfBaby(year) ;
			 float welfareBaby =
					 Float.parseFloat(welfareBabyInfoEntity.getRate()) * Float.parseFloat(basesalary) ;

			 EmplyinsrateInfoEntity emplyinsrateInfoEntity = salarylistMapper.getEmplyinsrate(year) ;

			 //雇用保険個人負担
			 float LaborBurden =
					 Float.parseFloat(emplyinsrateInfoEntity.getLaborBurdenRate())*Float.parseFloat(basesalary) ;

			 //雇用保険会社負担
			 float employerBurden =
					 Float.parseFloat(emplyinsrateInfoEntity.getEmployerBurdenRate())*Float.parseFloat(basesalary) ;

			 //雇用保拠出金（会社)
			 float employmentInsurance =
					 Float.parseFloat(emplyinsrateInfoEntity.getEmploymentInsuranceRate())*Float.parseFloat(basesalary) ;

			 //労災保険（会社負担のみ）
			 float industrialAccidentInsurance =
			 Float.parseFloat(emplyinsrateInfoEntity.getIndustrialAccidentInsuranceRate())*Float.parseFloat(basesalary) ;

			 //源泉控除
			 IncomeTaxInfoEntity incomeTaxInfoEntity = salarylistMapper.getTax(employeeID, year);
			 String month = maxMonth.substring(4, 6);
			 String incomeTax = "" ;
			 if (month .equals ("01")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax1();
			 }else if (month.equals ( "02")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax2();
			 }else if (month.equals ( "03")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax3();
			 }else if (month.equals ( "04")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax4();
			 }else if (month.equals ( "05")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax5();
			 }else if (month.equals ( "06")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax6();
			 }else if (month.equals ( "07")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax7();
			 }else if (month.equals ( "08")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax8();
			 }else if (month.equals ( "09")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax9();
			 }else if (month.equals ( "10")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax10();
			 }else if (month.equals ( "11")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax11();
			 }else if (month.equals ( "12")) {
				 incomeTax = incomeTaxInfoEntity.getIncomeTax12();
			 }

			 String residentTax ="";
			 if (month .equals ("01")){
				 residentTax = incomeTaxInfoEntity.getResidentTax1();
			 }else if (month.equals ( "02")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax2();
			 }else if (month.equals ( "03")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax3();
			 }else if (month.equals ( "04")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax4();
			 }else if (month.equals ( "05")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax5();
			 }else if (month.equals ( "06")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax6();
			 }else if (month.equals ( "07")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax7();
			 }else if (month.equals ( "08")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax8();
			 }else if (month.equals ( "09")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax9();
			 }else if (month.equals ( "10")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax10();
			 }else if (month.equals ( "11")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax11();
			 }else if (month.equals ( "12")) {
				 residentTax = incomeTaxInfoEntity.getResidentTax12();
			 }
			 //社宅家賃控除
			 String rental  = " 0" ;
			 //社宅共益費控除
			 String rentalMgmtFee = " 0" ;
			//特別控除
			 String specialReduce	 = " 0" ;

			 //総額
			 float sum= (Float.parseFloat(basesalary) -  shortageReduce -Float.parseFloat(allowanceReduce) -
					 	 wfPensionSelf- wfHealthSelf- LaborBurden -
					 	 Float.parseFloat(incomeTax)-Float.parseFloat( residentTax ) -
					 	 Float.parseFloat(rental)-Float.parseFloat(rentalMgmtFee) - Float.parseFloat(specialReduce)) +
					 	 overTimePlus + transportExpense  + Float.parseFloat(specialAddition) +
					 	Float.parseFloat(allowancePlus) ;

			 //総費用
			 float totalFee = sum + wfPensionComp + wfHealthComp + welfareBaby + employerBurden + employmentInsurance
					 + industrialAccidentInsurance ;

			 //備考
			 String remark = "" ;

			 //削除フラグ
			 String deleteFlg = "0";

			 //作成日
			 SalaryInfoEntity salaryInfoEntity = salarylistMapper.getDate();
			 String insertDate = salaryInfoEntity.getInsertDate();
			 String updateDate = salaryInfoEntity.getUpdateDate();

		 }

		//給料テーブルに新規追加

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
