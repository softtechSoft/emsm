package com.softtech.service;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softtech.common.EmployeeIDName;
import com.softtech.entity.SalaryInfo;
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

	public List<SalaryInfo> querySalarylist(String month) {

        // YYYY/MM→yyyymmに変換
		String monthP = DateUtil.chgMonthToYM(month);

		// DBからデータを取得する
		// 給料情報を取得する
		List<SalaryInfo> salaryinfolist =  salarylistMapper.getSalaryinfolist(monthP);

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
			 //対象年月
			 String year=DateUtil.chgMonthToYM(nextMonth) ;
			 //支払日
			 String paymentDate=DateUtil.getPayMonth(year);

			 //③基本給を取得
			 String baseSalary=salarylistMapper.getBaseSalary(employeeID,year);

			 //workinfoのworkTime　-　m_basesalaryのwkPeriodTo　マイナスの場合、０とする
//			 String overTime=
//			 if(overTime >0) {
//
//			 }else {
//
//			 }




//			 employeeList.add(salaryInfo);

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