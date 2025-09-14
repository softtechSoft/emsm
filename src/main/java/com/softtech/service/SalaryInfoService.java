package com.softtech.service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import com.softtech.actionForm.SalaryInfoBean;
import com.softtech.actionForm.WelfareBean;
import com.softtech.common.SalaryInfoRecord;
import com.softtech.entity.BaseSalaryInfoEntity;
import com.softtech.entity.EmployeeInfoEntity;
import com.softtech.entity.EmplyinsrateInfoEntity;
import com.softtech.entity.SalaryInfoEntity;
import com.softtech.entity.WelfareInfo;
import com.softtech.entity.WelfarefeeInfoEntity;
import com.softtech.mappers.EmployeeInfoMapper;
import com.softtech.mappers.SalaryInfoMapper;
import com.softtech.mappers.SalarylistMapper;
import com.softtech.util.DataUtil;
import com.softtech.util.DateUtil;

/**
 * 概要：給料作成のservice
 *
 * 作成者：馬＠ソフトテク
 * 作成日：2021/06/15
 */
@Service
public class SalaryInfoService {
	@Autowired
	SalaryInfoMapper salaryInfoMapper;
	@Autowired
	SalarylistMapper salarylistMapper;
	@Autowired
    private EmployeeInfoMapper employeeInfoMapper;
	/*
	 * 機能概要： 給料情報を検索
	 *			条件：年月、社員ID
	 *
	 * @param  em 検索パラメータ
	 * @return 給料情報
	 */
	public SalaryInfoEntity querySalaryInfo(SalaryInfoRecord em) {
		// DBから給料作成情報を取得する
		SalaryInfoEntity salaryInfo= salaryInfoMapper.getSalaryInfoDetail(em);
		if(salaryInfo==null) {
			return null;
		}
		return salaryInfo;
	}
	/*
	 * 機能概要： 福祉情報情報を検索
	 *			条件：社員ID
	 *
	 * @param  社員ID 検索パラメータ
	 * @return 福祉情報
	 */
	public WelfareInfo queryWelfare(String EmployeeID) {
		// DBから給料作成情報を取得する
		WelfareInfo welfareInfo= salaryInfoMapper.getWelfareInfoDetail(EmployeeID);
		if(welfareInfo==null) {
			return null;
		}
		return welfareInfo;
	}
	/*
	 * 機能概要： 給料情報DBへ新規追加
	 *
	 * @param  em 新規追加データ
	 * @return
	 */
	public  void insertSalaryInfo(SalaryInfoRecord am) {
		 salaryInfoMapper.insertSalaryInfo(am);
	}
	/*
	 * 機能概要： 給料情報DBへ更新
	 *
	 * @param  lm 更新データ
	 * @return
	 */
	public  void updateSalaryInfo(SalaryInfoRecord lm) {
		 salaryInfoMapper.updateSalaryInfo(lm);
	}

	/*
	 * 機能概要： データチェック
	 *
	 * @param  チェック対象データ
	 * @return エラーリスト
	 */
	public List<FieldError>  getSuccessMessage() {

		// エラーチェック用リスト
		List<FieldError> errorlst = new ArrayList<FieldError>();
		FieldError err1 = new FieldError("", "", "給料更新成功しました！！");
		errorlst.add(err1);
		return errorlst;
	}
	/*
	 * 機能概要： データチェック
	 *
	 * @param  チェック対象データ
	 * @return エラーリスト
	 */
	public List<FieldError>  checkData(SalaryInfoBean salaryInfoBean) {

		// エラーチェック用リスト
		List<FieldError> errorlst = new ArrayList<FieldError>();
		// 基本給数字チェック。
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getBase()))) {
			FieldError err1 = new FieldError("", "", "基本給が数字を入力してください。例：60,000");
			errorlst.add(err1);
		}
		// 残業時間数字チェック。
		if(!DateUtil.checkNumeric(salaryInfoBean.getOverTime())) {
			FieldError err2 = new FieldError("", "", "残業時間が数字を入力してください。例：20.5");
			errorlst.add(err2);
		}
		// 残業時間数字チェック（正の整数または小数）
		String overTime = salaryInfoBean.getOverTime();
		if (!NumberUtils.isParsable(overTime) || Double.parseDouble(overTime) < 0) {
		    FieldError err2 = new FieldError("", "", "残業時間は正の数字（整数または小数）で入力してください。例：20 または 1.5");
		    errorlst.add(err2);
		}
		// 不足時間数字チェック。
		if(!DateUtil.checkNumeric(salaryInfoBean.getShortage())) {
			FieldError err3= new FieldError("", "", "不足時間が数字を入力してください。例：20.5");
			errorlst.add(err3);
		}
		// 不足時間数字チェック（正の整数または小数）
		String shortage = salaryInfoBean.getShortage();
		if (!NumberUtils.isParsable(shortage) || Double.parseDouble(shortage) < 0) {
		    FieldError err3 = new FieldError("", "", "不足時間は正の数字（整数または小数）で入力してください。例：20 または 1.5");
		    errorlst.add(err3);
		}
		// 残業加算数字チェック。
		if(DateUtil.isNumeric(salaryInfoBean.getOverTimePlus())) {;
			FieldError err4= new FieldError("", "", "残業加算が数字を入力してください。例：20");
			errorlst.add(err4);
		}
		// 稼働不足減数字チェック。
		if(DateUtil.isNumeric(salaryInfoBean.getShortageReduce())) {
			FieldError err5= new FieldError("", "", "稼働不足減が数字を入力してください。例：20");
			errorlst.add(err5);
		}
		// 交通費数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getTransportExpense()))) {
			FieldError err6= new FieldError("", "", "交通費が数字を入力してください。例：2000");
			errorlst.add(err6);
		}
		//特別加算
		// 特別加算数字チェック
		if(DateUtil.isNumeric(salaryInfoBean.getSpecialAddition())) {
			FieldError err7= new FieldError("", "", "特別加算が数字を入力してください。例：2000");
			errorlst.add(err7);
		}

		// 手当加算数字チェック
		if(DateUtil.isNumeric(salaryInfoBean.getAllowancePlus())) {
			FieldError err7= new FieldError("", "", "手当加算が数字を入力してください。例：2000");
			errorlst.add(err7);
		}
		// 手当減算数字チェック
		if(DateUtil.isNumeric(salaryInfoBean.getAllowanceReduce())) {
			FieldError err8= new FieldError("", "", "手当減算が数字を入力してください。例：2000");
			errorlst.add(err8);
		}
		// 厚生年金控除個人数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getWelfarePensionSelf()))) {
			FieldError err9= new FieldError("", "", "厚生年金控除個人が数字を入力してください。例：2000");
			errorlst.add(err9);
		}
		// 厚生健康控除個人数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getWelfareHealthSelf()))) {
			FieldError err10= new FieldError("", "", "厚生健康控除個人が数字を入力してください。例：2000");
			errorlst.add(err10);
		}
		// 厚生年金控除会社数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getWelfarePensionComp()))) {
			FieldError err27= new FieldError("", "", "厚生年金控除会社が数字を入力してください。例：2000");
			errorlst.add(err27);
		}
		// 厚生年金控除会社数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getWelfareHealthComp()))) {
			FieldError err28= new FieldError("", "", "厚生年金控除会社が数字を入力してください。例：2000");
			errorlst.add(err28);
		}
		// 厚生控除子育(会社)数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getWelfareBaby()))) {
			FieldError err11= new FieldError("", "", "厚生控除子育(会社)が数字を入力してください。例：2000");
			errorlst.add(err11);
		}
		// 雇用保険個人負担数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getEplyInsSelf()))) {
			FieldError err12= new FieldError("", "", "雇用保険個人負担が数字を入力してください。例：2000");
			errorlst.add(err12);
		}
		// 雇用保険会社負担数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getEplyInsComp()))) {
			FieldError err13= new FieldError("", "", "雇用保険会社負担が数字を入力してください。例：2000");
			errorlst.add(err13);
		}
		// 雇用保拠出金（会社)数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getEplyInsWithdraw()))) {
			FieldError err14= new FieldError("", "", "一般拠出金（会社のみ)が数字を入力してください。例：2000");
			errorlst.add(err14);
		}
		// 労災保険（会社負担のみ）数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getWkAcccpsIns()))) {
			FieldError err15= new FieldError("", "", "労災保険（会社負担のみ）が数字を入力してください。例：2000");
			errorlst.add(err15);
		}
		// 源泉控除数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getWithholdingTax()))) {
			FieldError err16= new FieldError("", "", "源泉控除が数字を入力してください。例：2000");
			errorlst.add(err16);
		}
		// 住民税控除数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getMunicipalTax()))) {
			FieldError err17= new FieldError("", "", "住民税控除が数字を入力してください。例：2000");
			errorlst.add(err17);
		}
		// 社宅家賃控除数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getRental()))) {
			FieldError err18= new FieldError("", "", "社宅家賃控除が数字を入力してください。例：2000");
			errorlst.add(err18);
		}
		// 社宅共益費控除数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getRentalMgmtFee()))) {
			FieldError err19= new FieldError("", "", "社宅共益費控除が数字を入力してください。例：2000");
			errorlst.add(err19);
		}
		//総費用数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getTotalFee()))) {
			FieldError err20= new FieldError("", "", "総費用が数字を入力してください。例：60,000");
			errorlst.add(err20);
		}
		//特別控除
		//特別控除数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getShortageReduce()))) {
			FieldError err20= new FieldError("", "", "特別控除が数字を入力してください。例：60,000");
			errorlst.add(err20);
		}

		//総額数字チェック
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getSum()))) {
			FieldError err21= new FieldError("", "", "総額が数字を入力してください。例：60,000");
			errorlst.add(err21);
		}

		// 支払日が通常の日付チェック
		boolean payment=true;
		if(DateUtil.isDate(salaryInfoBean.getPaymentDate())==false) {
			payment=false;
			FieldError err23= new FieldError("", "", "支払日がを通常の日付を入力してください、例：”2021/06/30”");
			errorlst.add(err23);
		}

		// 支払日が過去日チェック。
		if(payment){
			if(DateUtil.isLessThanNow(salaryInfoBean.getPaymentDate())
					|| DateUtil.isNow(DateUtil.chgMonthToYM(salaryInfoBean.getPaymentDate()))) {
				FieldError err24= new FieldError("", "", "支払日がを未来の日付を入力してください");
				errorlst.add(err24);
			}
		}
		return errorlst;
	}
	/*
	 * 機能概要： DBデータから画面データ用変換
	 *
	 * @param  salaryInfo DBデータ
	 * @return 画面FORM
	 */
	public SalaryInfoBean transferToGamen(SalaryInfoEntity salaryInfo) {

		//DBから取得したデータを画面データへ変換する
		SalaryInfoBean salaryInfoBean = new SalaryInfoBean();
		//社員ID
		salaryInfoBean.setEmployeeID(salaryInfo.getEmployeeID());
		//氏名
		salaryInfoBean.setEmployeeName(salaryInfo.getEmployeeName());
		//住所
		salaryInfoBean.setAddress(salaryInfo.getAddress());
		//対象月
		salaryInfoBean.setMonth(salaryInfo.getMonth()==null ? "":DateUtil.modifymonth(salaryInfo.getMonth()));
		//基本給
		salaryInfoBean.setBase(salaryInfo.getBase()==null ? "" : DataUtil.addComma(salaryInfo.getBase()));
		//支払日
		salaryInfoBean.setPaymentDate(DateUtil.YMDAddSlash(salaryInfo.getPaymentDate()));
		//残業時間
		salaryInfoBean.setOverTime(salaryInfo.getOverTime());
		//不足時間
		salaryInfoBean.setShortage(salaryInfo.getShortage());
		//交通費
		salaryInfoBean.setTransportExpense(DataUtil.addComma(salaryInfo.getTransportExpense()));
		//手当加算
		salaryInfoBean.setAllowancePlus(DataUtil.addComma(salaryInfo.getAllowancePlus()));
		//残業加算
		salaryInfoBean.setOverTimePlus(DataUtil.addComma(salaryInfo.getOverTimePlus()));
		//稼働不足減
		salaryInfoBean.setShortageReduce(DataUtil.addComma(salaryInfo.getShortageReduce()));
		//特別加算
		salaryInfoBean.setSpecialAddition(DataUtil.addComma(salaryInfo.getSpecialAddition()));
		//手当減算
		salaryInfoBean.setAllowanceReduce(DataUtil.addComma(salaryInfo.getAllowanceReduce()));
		//手当理由
		salaryInfoBean.setAllowanceReason(salaryInfo.getAllowanceReason());

		//厚生年金控除個人
		salaryInfoBean.setWelfarePensionSelf(DataUtil.addComma(salaryInfo.getWelfarePensionSelf()));
		//厚生健康控除個人
		salaryInfoBean.setWelfareHealthSelf(DataUtil.addComma(salaryInfo.getWelfareHealthSelf()));
		//厚生年金控除会社
		salaryInfoBean.setWelfarePensionComp(DataUtil.addComma(salaryInfo.getWelfarePensionComp()));
		//厚生健康控除会社
		salaryInfoBean.setWelfareHealthComp(DataUtil.addComma(salaryInfo.getWelfareHealthComp()));
		//厚生控除子育(会社）
		salaryInfoBean.setWelfareBaby(DataUtil.addComma(salaryInfo.getWelfareBaby()));

		//雇用保険個人負担
		salaryInfoBean.setEplyInsSelf(DataUtil.addComma(salaryInfo.getEplyInsSelf()));
		//雇用保険会社負担
		salaryInfoBean.setEplyInsComp(DataUtil.addComma(salaryInfo.getEplyInsComp()));
		//雇用保拠出金（会社)
		salaryInfoBean.setEplyInsWithdraw(DataUtil.addComma(salaryInfo.getEplyInsWithdraw()));
		//労災保険（会社負担のみ）
		salaryInfoBean.setWkAcccpsIns(DataUtil.addComma(salaryInfo.getWkAcccpsIns()));

		//源泉控除
		salaryInfoBean.setWithholdingTax(DataUtil.addComma(salaryInfo.getWithholdingTax()));
		//住民税控除
		salaryInfoBean.setMunicipalTax(DataUtil.addComma(salaryInfo.getMunicipalTax()));

		//社宅家賃控除
		salaryInfoBean.setRental(DataUtil.addComma(salaryInfo.getRental()));
		//社宅共益費控除
		salaryInfoBean.setRentalMgmtFee(DataUtil.addComma(salaryInfo.getRentalMgmtFee()));

		//特別控除
		salaryInfoBean.setSpecialReduce(DataUtil.addComma(salaryInfo.getSpecialReduce()));
		//総額
		salaryInfoBean.setSum(DataUtil.addComma(salaryInfo.getSum()));
		//総費用
		salaryInfoBean.setTotalFee(DataUtil.addComma(salaryInfo.getTotalFee()));
		//備考
		salaryInfoBean.setRemark(DataUtil.addComma(salaryInfo.getRemark()));

		return salaryInfoBean;
	}
	/*
	 * 機能概要： 画面データからDB用変換
	 *
	 * @param  salaryInfoBean 画面入力値
	 * @return DB登録用Entity
	 */
	public SalaryInfoRecord  transferToDB(SalaryInfoBean salaryInfoBean) {
		//登録用情報
		SalaryInfoRecord am = new SalaryInfoRecord();
		//社員ID
		am.setEmployeeID(salaryInfoBean.getEmployeeID());
		//対象月
		am.setMonth(DateUtil.chgMonthToYM(salaryInfoBean.getMonth()));

		//支払日
		am.setPaymentDate(DateUtil.chgMonthToYM(salaryInfoBean.getPaymentDate()));

		//基本給
		am.setBase(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getBase())));

		//残業時間
		am.setOverTime(salaryInfoBean.getOverTime()==null ? 0 : Float.parseFloat(salaryInfoBean.getOverTime()));

		//不足時間
		am.setShortage(salaryInfoBean.getShortage()==null ? 0 : Float.parseFloat(salaryInfoBean.getShortage()));

		//交通費
		am.setTransportExpense(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getTransportExpense())));

		//手当加算
		if(salaryInfoBean.getAllowancePlus()!= null) {
			am.setAllowancePlus(Integer.parseInt(salaryInfoBean.getAllowancePlus()));
		}else {
			am.setAllowancePlus(0);
		}

		//残業加算
		if(salaryInfoBean.getOverTimePlus()!= null) {
			am.setOverTimePlus(Integer.parseInt(salaryInfoBean.getOverTimePlus()));
		}else {
			am.setOverTimePlus(0);
		}

		//稼働不足減
		if(salaryInfoBean.getShortageReduce()!= null) {
			am.setShortageReduce(Integer.parseInt(salaryInfoBean.getShortageReduce()));
		}else {
			am.setShortageReduce(0);
		}

		//特別加算
		am.setSpecialAddition(salaryInfoBean.getSpecialAddition()==null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getSpecialAddition())));

		//手当減算
		if(salaryInfoBean.getAllowanceReduce()!= null) {
			am.setAllowanceReduce(Integer.parseInt(salaryInfoBean.getAllowanceReduce()));
		}else {
			am.setAllowanceReduce(0);
		}

		//手当理由
		am.setAllowanceReason(salaryInfoBean.getAllowanceReason());

		//厚生年金控除個人
		am.setWelfarePensionSelf(salaryInfoBean.getWelfarePensionSelf() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWelfarePensionSelf())));

		//厚生健康控除個人
		am.setWelfareHealthSelf(salaryInfoBean.getWelfareHealthSelf() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWelfareHealthSelf())));

		//厚生年金控除会社
		am.setWelfarePensionComp(salaryInfoBean.getWelfarePensionComp() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWelfarePensionComp())));

		//厚生健康控除会社
		am.setWelfareHealthComp(salaryInfoBean.getWelfareHealthComp() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWelfareHealthComp())));

		//厚生控除子育(会社）
		am.setWelfareBaby(salaryInfoBean.getWelfareBaby() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWelfareBaby())));

		//雇用保険個人負担
		am.setEplyInsSelf(salaryInfoBean.getEplyInsSelf() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getEplyInsSelf())));
		//雇用保険会社負担
		am.setEplyInsComp(salaryInfoBean.getEplyInsComp() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getEplyInsComp())));
		//雇用保拠出金（会社)
		am.setEplyInsWithdraw(salaryInfoBean.getEplyInsWithdraw() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getEplyInsWithdraw())));
		//労災保険（会社負担のみ）
		am.setWkAcccpsIns(salaryInfoBean.getWkAcccpsIns() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWkAcccpsIns())));

		//源泉控除
		am.setWithholdingTax(salaryInfoBean.getWithholdingTax() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWithholdingTax())));

		//住民税控除
		am.setMunicipalTax(salaryInfoBean.getMunicipalTax() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getMunicipalTax())));

		//社宅家賃控除
		am.setRental(salaryInfoBean.getRental() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getRental())));

		//社宅共益費控除
		am.setRentalMgmtFee(salaryInfoBean.getRentalMgmtFee() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getRentalMgmtFee())));

		//特別控除
		am.setSpecialReduce(salaryInfoBean.getSpecialReduce() == null ? 0 :Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getSpecialReduce())));

		//総額
		am.setSum(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getSum())));

		//総費用
		am.setTotalFee(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getTotalFee())));

		//備考
		am.setRemark(salaryInfoBean.getRemark());
		return am;
	}
	/*
	 * 機能概要： 数値化を表示標準化
	 *
	 * @param  salaryInfoBean 画面入力値
	 * @return なし
	 */
	public void  addComma(SalaryInfoBean salaryInfoBean) {
		salaryInfoBean.setBase(DataUtil.addComma(salaryInfoBean.getBase()));
		salaryInfoBean.setOverTime(DataUtil.addComma(salaryInfoBean.getOverTime()));
		salaryInfoBean.setOverTimePlus(DataUtil.addComma(salaryInfoBean.getOverTimePlus()));
		salaryInfoBean.setShortageReduce(DataUtil.addComma(salaryInfoBean.getShortageReduce()));
		salaryInfoBean.setTransportExpense(DataUtil.addComma(salaryInfoBean.getTransportExpense()));

		salaryInfoBean.setAllowancePlus(DataUtil.addComma(salaryInfoBean.getAllowancePlus()));
		salaryInfoBean.setAllowanceReduce(DataUtil.addComma(salaryInfoBean.getAllowanceReduce()));
		salaryInfoBean.setWelfarePensionSelf(DataUtil.addComma(salaryInfoBean.getWelfarePensionSelf()));
		salaryInfoBean.setWelfareHealthSelf(DataUtil.addComma(salaryInfoBean.getWelfareHealthSelf()));
		salaryInfoBean.setWelfarePensionComp(DataUtil.addComma(salaryInfoBean.getWelfarePensionComp()));
		salaryInfoBean.setWelfareHealthComp(DataUtil.addComma(salaryInfoBean.getWelfareHealthComp()));
		salaryInfoBean.setWelfareBaby(DataUtil.addComma(salaryInfoBean.getWelfareBaby()));
		salaryInfoBean.setEplyInsSelf(DataUtil.addComma(salaryInfoBean.getEplyInsSelf()));
		salaryInfoBean.setEplyInsComp(DataUtil.addComma(salaryInfoBean.getEplyInsComp()));
		salaryInfoBean.setEplyInsWithdraw(DataUtil.addComma(salaryInfoBean.getEplyInsWithdraw()));
		salaryInfoBean.setWkAcccpsIns(DataUtil.addComma(salaryInfoBean.getWkAcccpsIns()));
		salaryInfoBean.setWithholdingTax(DataUtil.addComma(salaryInfoBean.getWithholdingTax()));
		salaryInfoBean.setMunicipalTax(DataUtil.addComma(salaryInfoBean.getMunicipalTax()));
		salaryInfoBean.setRental(DataUtil.addComma(salaryInfoBean.getRental()));
		salaryInfoBean.setRentalMgmtFee(DataUtil.addComma(salaryInfoBean.getRentalMgmtFee()));
		salaryInfoBean.setSpecialReduce(DataUtil.addComma(salaryInfoBean.getSpecialReduce()));
		salaryInfoBean.setSum(DataUtil.addComma(salaryInfoBean.getSum()));
		salaryInfoBean.setTotalFee(DataUtil.addComma(salaryInfoBean.getTotalFee()));

	}
	/*
	 * 機能概要： 画面数値項目を数値化
	 *
	 * @param  salaryInfoBean 画面入力値
	 * @return なし
	 */
	public void  deleteComma(SalaryInfoBean salaryInfoBean) {
		salaryInfoBean.setBase(salaryInfoBean.getBase().replaceAll(",", ""));
		salaryInfoBean.setOverTime(salaryInfoBean.getOverTime().replaceAll(",", ""));
		salaryInfoBean.setOverTimePlus(salaryInfoBean.getOverTimePlus().replaceAll(",", ""));
		salaryInfoBean.setShortageReduce(salaryInfoBean.getShortageReduce().replaceAll(",", ""));
		salaryInfoBean.setTransportExpense(salaryInfoBean.getTransportExpense().replaceAll(",", ""));
		salaryInfoBean.setSpecialAddition(salaryInfoBean.getSpecialAddition().replaceAll(",", ""));
		salaryInfoBean.setAllowancePlus(salaryInfoBean.getAllowancePlus().replaceAll(",", ""));
		salaryInfoBean.setAllowanceReduce(salaryInfoBean.getAllowanceReduce().replaceAll(",", ""));
		salaryInfoBean.setWelfarePensionSelf(salaryInfoBean.getWelfarePensionSelf().replaceAll(",", ""));
		salaryInfoBean.setWelfareHealthSelf(salaryInfoBean.getWelfareHealthSelf().replaceAll(",", ""));
		salaryInfoBean.setWelfarePensionComp(salaryInfoBean.getWelfarePensionComp().replaceAll(",", ""));
		salaryInfoBean.setWelfareHealthComp(salaryInfoBean.getWelfareHealthComp().replaceAll(",", ""));
		salaryInfoBean.setWelfareBaby(salaryInfoBean.getWelfareBaby().replaceAll(",", ""));
		salaryInfoBean.setEplyInsSelf(salaryInfoBean.getEplyInsSelf().replaceAll(",", ""));
		salaryInfoBean.setEplyInsComp(salaryInfoBean.getEplyInsComp().replaceAll(",", ""));
		salaryInfoBean.setEplyInsWithdraw(salaryInfoBean.getEplyInsWithdraw().replaceAll(",", ""));
		salaryInfoBean.setWkAcccpsIns(salaryInfoBean.getWkAcccpsIns().replaceAll(",", ""));
		salaryInfoBean.setWithholdingTax(salaryInfoBean.getWithholdingTax().replaceAll(",", ""));
		salaryInfoBean.setMunicipalTax(salaryInfoBean.getMunicipalTax().replaceAll(",", ""));
		salaryInfoBean.setRental(salaryInfoBean.getRental().replaceAll(",", ""));
		salaryInfoBean.setRentalMgmtFee(salaryInfoBean.getRentalMgmtFee().replaceAll(",", ""));
		salaryInfoBean.setSpecialReduce(salaryInfoBean.getSpecialReduce().replaceAll(",", ""));
		salaryInfoBean.setSum(salaryInfoBean.getSum().replaceAll(",", ""));
		salaryInfoBean.setTotalFee(salaryInfoBean.getTotalFee().replaceAll(",", ""));

	}
	/*
	 * 機能概要： 計算1用項目を数値化
	 *
	 * @param  salaryInfoBean 画面入力値
	 * @return なし
	 */
	public void  deleteComma1(SalaryInfoBean salaryInfoBean) {
		//基本給
		salaryInfoBean.setBase(salaryInfoBean.getBase().replaceAll(",", ""));
		//残業時間
		salaryInfoBean.setOverTime(salaryInfoBean.getOverTime().replaceAll(",", ""));
		//不足時間
		salaryInfoBean.setShortage(salaryInfoBean.getShortage().replaceAll(",", ""));
		//交通費
		salaryInfoBean.setTransportExpense(salaryInfoBean.getTransportExpense().replaceAll(",", ""));
		//手当加算
		salaryInfoBean.setAllowancePlus(salaryInfoBean.getAllowancePlus().replaceAll(",", ""));
	}

	/*
	 * 機能概要： 画面数値項目を数値化
	 *
	 * @param  salaryInfoBean 画面入力値
	 * @param  welfareBeans 福祉情報
	 * @param  month 対象月
	 *
	 * @return なし
	 */
	public void  setWelfareToGamen(SalaryInfoBean salaryInfoBean,List<WelfareBean>  welfareBeans,String month)
	{
		for(WelfareBean welfareBean:welfareBeans) {
			//基本給
			salaryInfoBean.setBase(welfareBean.getBase());

			//厚生年金控除個人
			salaryInfoBean.setWelfarePensionSelf(DataUtil.addComma( welfareBean.getWelfarePensionSelf()));
			//厚生健康控除個人
			salaryInfoBean.setWelfareHealthSelf(DataUtil.addComma(welfareBean.getWelfareHealthSelf()));
			//厚生年金控除会社
			salaryInfoBean.setWelfarePensionComp(DataUtil.addComma(welfareBean.getWelfarePensionComp()));
			//厚生健康控除会社
			salaryInfoBean.setWelfareHealthComp(DataUtil.addComma(welfareBean.getWelfareHealthComp()));
			//厚生控除子育(会社）
			salaryInfoBean.setWelfareBaby(DataUtil.addComma(welfareBean.getWelfareBaby()));
			//雇用保険個人負担
			salaryInfoBean.setEplyInsSelf(DataUtil.addComma(welfareBean.getEplyInsSelf()));
			//雇用保険会社負担
			salaryInfoBean.setEplyInsComp(DataUtil.addComma(welfareBean.getEplyInsComp()));
			//一般拠出金（会社のみ)
			salaryInfoBean.setEplyInsWithdraw(DataUtil.addComma(welfareBean.getEplyInsWithdraw()));
			//労災保険（会社負担のみ）
			salaryInfoBean.setWkAcccpsIns(DataUtil.addComma(welfareBean.getWkAcccpsIns()));
			//源泉控除
			salaryInfoBean.setWithholdingTax(DataUtil.addComma(welfareBean.getWithholdingTax()));
			//住民税控除
			switch (month) {
			case "01":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax1()));
				break;
			case "02":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax2()));
				break;
			case "03":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax3()));
				break;
			case "04":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax4()));
				break;
			case "05":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax5()));
				break;
			case "06":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax6()));
				break;
			case "07":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax7()));
				break;
			case "08":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax8()));
				break;
			case "09":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax9()));
				break;
			case "10":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax10()));
				break;
			case "11":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax11()));
				break;
			case "12":
				salaryInfoBean.setMunicipalTax(DataUtil.addComma(welfareBean.getMunicipalTax12()));
				break;

			}
			//社宅家賃控除
			salaryInfoBean.setRental(DataUtil.addComma(welfareBean.getRental()));
			//社宅共益費控除
			salaryInfoBean.setRentalMgmtFee(DataUtil.addComma(welfareBean.getRentalMgmtFee()));
		}

	}
	/*
	 * 機能：画面入力された値から給料を生成する
	 *
	 * @param 対象年月
	 * @return 結果
	 */
	public void calSalary1(SalaryInfoBean salaryInfoBean) throws ParseException {
		//////// 画面から取得////////
		//基本給
		String basesalary = salaryInfoBean.getBase();
		if(basesalary==null || basesalary.length()==0) basesalary="0";
		//残業時間
		String overTime= salaryInfoBean.getOverTime();
		if(overTime==null || overTime.length()==0) overTime="0";
		//不足時間
		String shortage= salaryInfoBean.getShortage();
		if(shortage==null  || shortage.length()==0) shortage="0";
		//交通費
		String transportExpense = salaryInfoBean.getTransportExpense();
		if(transportExpense==null  || transportExpense.length()==0) transportExpense="0";
		//手当加算
		String allowancePlus = salaryInfoBean.getAllowancePlus();
		if(allowancePlus==null  || allowancePlus.length()==0) allowancePlus="0";

		////////稼働により残業不足減計算//////////////
		//社員ID
		String employeeID=salaryInfoBean.getEmployeeID();
		//対象年度
		String year=DateUtil.getNowYear() ;
		//基本給情報
		BaseSalaryInfoEntity baseSalaryInfoEntity=salarylistMapper.getBaseSalary(employeeID,year);
		//残業加算
		Float overTimePlus = Float.parseFloat(baseSalaryInfoEntity.getOvertimePay()) * Float.parseFloat(overTime);
		salaryInfoBean.setOverTimePlus(DataUtil.addComma(""+ Math.round(overTimePlus)));
		//稼働不足減
		Float shortageReduce = Float.parseFloat(baseSalaryInfoEntity.getInsufficienttimePay()) * Float.parseFloat(shortage);
		salaryInfoBean.setShortageReduce(DataUtil.addComma(""+ Math.round(shortageReduce)));

		////////基本給から厚生計算//////////////
		//厚生マスタを取る
		//厚生保険料情報
		WelfarefeeInfoEntity welfarefeeInfoEntity = salarylistMapper.getWfPension(basesalary);

		//厚生年金控除個人
		float wfPensionSelf =
				(( Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(welfarefeeInfoEntity.getStandSalary()))/100)/2;
		salaryInfoBean.setWelfarePensionSelf(DataUtil.addComma("" + Math.round(wfPensionSelf)));
		 //厚生年金控除会社
		float wfPensionComp = wfPensionSelf;
		salaryInfoBean.setWelfarePensionComp(DataUtil.addComma("" + Math.round(wfPensionComp)));

		//社員年齢を取る
		String age=salarylistMapper.getAge(employeeID);

		//厚生健康控除個人
		float wfHealthSelf;
		//厚生健康控除会社
		float wfHealthComp;
		if ( Float.parseFloat(age) >= 40) {
			 wfHealthSelf =
					 ((Float.parseFloat(welfarefeeInfoEntity.getCareRatio()) * Float.parseFloat(welfarefeeInfoEntity.getStandSalary()))/100)/2;
			 salaryInfoBean.setWelfareHealthSelf(DataUtil.addComma("" + Math.round(wfHealthSelf)));
			 wfHealthComp =wfHealthSelf;
			 salaryInfoBean.setWelfareHealthComp(DataUtil.addComma("" + Math.round(wfHealthComp)));

		}else {
				wfHealthSelf =
						((Float.parseFloat(welfarefeeInfoEntity.getNotCareRatio())* Float.parseFloat(welfarefeeInfoEntity.getStandSalary()))/100)/2;
				salaryInfoBean.setWelfareHealthSelf(DataUtil.addComma("" + Math.round(wfHealthSelf)));
				wfHealthComp=wfHealthSelf;
				salaryInfoBean.setWelfareHealthComp(DataUtil.addComma("" + Math.round(wfHealthComp)));
		}
		//厚生控除子育(会社)
	    //標準報酬×厚生控除子育(会社)の控除率
		float welfareBaby =
			 (Float.parseFloat(welfarefeeInfoEntity.getBabyCareCompanyRate()) * (Float.parseFloat(welfarefeeInfoEntity.getStandSalary()))/100);

		salaryInfoBean.setWelfareBaby(DataUtil.addComma("" + Math.round(welfareBaby)));

		//基本給66万5千超えた場合の厚生年金再計算
		if (Float.parseFloat(basesalary) >= 665000) {
		    welfarefeeInfoEntity = salarylistMapper.getWfPension("664000");

			//厚生年金控除個人
			wfPensionSelf =
					(( Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(welfarefeeInfoEntity.getStandSalary()))/100)/2;
			salaryInfoBean.setWelfarePensionSelf(DataUtil.addComma("" + Math.round(wfPensionSelf)));

			 //厚生年金控除会社
			wfPensionComp = wfPensionSelf;
			salaryInfoBean.setWelfarePensionComp(DataUtil.addComma("" + Math.round(wfPensionComp)));

		}

		////////基本給から厚生計算//////////////
		//雇用保険率をを取得
		EmplyinsrateInfoEntity emplyinsrateInfoEntity = salarylistMapper.getEmplyinsrate(year) ;

		List<EmployeeInfoEntity> epInfo = employeeInfoMapper.getEmployeeID(employeeID);
		EmployeeInfoEntity emp = epInfo.isEmpty() ? null : epInfo.get(0);
		//役職を取得
		String postion = emp.getPosition();
		//雇用保険個人負担
		 float laborBurden = 0;
		//雇用保険会社負担
		 float employerBurden = 0;
		//雇用保拠出金（会社)
		 float employmentInsurance = 0;
		//労災保険（会社負担のみ）
		float industrialAccidentInsurance = 0;
		//雇用保険の対象額(基本給＋交通費＋残業金額＋手当)
		float hoKenSalary = Float.parseFloat(basesalary)
				             + Float.parseFloat(transportExpense)
				             + overTimePlus
				             + Float.parseFloat(allowancePlus);
		 //役員の場合
		if(("0").equals(postion)) {
			 laborBurden = 0;
			 employerBurden = 0;
			 employmentInsurance = 0;
			 industrialAccidentInsurance = 0;
		//一般社員の場合
		 }else {
			 laborBurden =
					( Float.parseFloat(emplyinsrateInfoEntity.getLaborBurdenRate())*hoKenSalary)/1000 ;

			 employerBurden =
					 (Float.parseFloat(emplyinsrateInfoEntity.getEmployerBurdenRate())*hoKenSalary)/1000 ;

			 employmentInsurance =
					 (Float.parseFloat(emplyinsrateInfoEntity.getContributionRate())*hoKenSalary)/1000 ;

			 industrialAccidentInsurance =
			 (Float.parseFloat(emplyinsrateInfoEntity.getIndustrialAccidentInsuranceRate())*hoKenSalary)/1000 ;

		 }
		 //雇用保険個人負担
		 salaryInfoBean.setEplyInsSelf(DataUtil.addComma("" + Math.round(laborBurden)));
		 //雇用保険会社負担
		 salaryInfoBean.setEplyInsComp(DataUtil.addComma("" + Math.round(employerBurden)));
		 //雇用保拠出金（会社)
		 salaryInfoBean.setEplyInsWithdraw(DataUtil.addComma("" + Math.round(employmentInsurance)));
		 //労災保険（会社負担のみ）
		 salaryInfoBean.setWkAcccpsIns(DataUtil.addComma("" + Math.round(industrialAccidentInsurance)));

		 //画面表示変更
		 //基本給
		 salaryInfoBean.setBase(DataUtil.addComma(salaryInfoBean.getBase()));
		 //残業時間
		 salaryInfoBean.setOverTime(DataUtil.addComma(salaryInfoBean.getOverTime()));
		 //不足時間
		 salaryInfoBean.setShortage(DataUtil.addComma(salaryInfoBean.getShortage()));
		 //交通費
		 salaryInfoBean.setTransportExpense(DataUtil.addComma(salaryInfoBean.getTransportExpense()));
		 //手当加算
		 salaryInfoBean.setAllowancePlus(DataUtil.addComma(salaryInfoBean.getAllowancePlus()));
	}
	/*
	 * 機能：総額、総費用の後継を加算する
	 *
	 * @param 対象年月
	 * @return 結果
	 */
	public void calSalary2(SalaryInfoBean salaryInfoBean) throws ParseException {
		 //////////画面の値を取得////////////////
		 //基本給を取得
		 String basesalary = salaryInfoBean.getBase();
		 if(basesalary==null || basesalary.length()==0) basesalary="0";
		 //稼働不足減
		 String shortageReduce= salaryInfoBean.getShortageReduce();
		 if(shortageReduce==null  || shortageReduce.length()==0) shortageReduce="0";
		 //手当減算
		 String allowanceReduce = salaryInfoBean.getAllowanceReduce();
		 if(allowanceReduce==null  || allowanceReduce.length()==0) allowanceReduce="0";
		 //厚生年金控除個人
		 String wfPensionSelf = salaryInfoBean.getWelfarePensionSelf();
		 if(wfPensionSelf==null  || wfPensionSelf.length()==0) wfPensionSelf="0";
		 //厚生健康控除個人
		 String wfHealthSelf = salaryInfoBean.getWelfareHealthSelf();
		 if(wfHealthSelf==null  || wfHealthSelf.length()==0) wfHealthSelf="0";
		 //雇用保険個人負担
		 String laborBurden = salaryInfoBean.getEplyInsSelf();
		 if(laborBurden==null  || laborBurden.length()==0) laborBurden="0";
		 //源泉
		 String withholdingTax = salaryInfoBean.getWithholdingTax();
		 if(withholdingTax==null  || withholdingTax.length()==0) withholdingTax="0";
		 //住民税
		 String municipalTax = salaryInfoBean.getMunicipalTax();
		 if(municipalTax==null  || municipalTax.length()==0) municipalTax="0";
		 //社宅家賃控除
		 String rental = salaryInfoBean.getRental();
		 if(rental==null  || rental.length()==0) rental="0";
		 //社宅共益費控除
		 String rentalMgmtFee = salaryInfoBean.getRentalMgmtFee();
		 if(rentalMgmtFee==null  || rentalMgmtFee.length()==0) rentalMgmtFee="0";
		 //特別控除
		 String specialReduce = salaryInfoBean.getSpecialReduce();
		 if(specialReduce==null  || specialReduce.length()==0) specialReduce="0";
		 //残業加算
		 String overTimePlus = salaryInfoBean.getOverTimePlus();
		 if(overTimePlus==null  || overTimePlus.length()==0) overTimePlus="0";
		 //交通費(通勤交通+他の交通費）
		 String transportExpense = salaryInfoBean.getTransportExpense();
		 if(transportExpense==null  || transportExpense.length()==0) transportExpense="0";
		 //特別加算
		 String specialAddition = salaryInfoBean.getSpecialAddition();
		 if(specialAddition==null  || specialAddition.length()==0) specialAddition="0";
		 //手当加算
		 String allowancePlus = salaryInfoBean.getAllowancePlus();
		 if(allowancePlus==null  || allowancePlus.length()==0) allowancePlus="0";

		 //振込総額=基本給basesalary
		 //ー稼働不足減shortageReduce
		 //ー手当減算allowanceReduce
		 //ー厚生年金控除個人wfPensionSelf
		 //ー厚生健康控除個人wfHealthSelf
		 //ー雇用保険個人負担laborBurden
		 //ー源泉WithholdingTax
		 //ー住民税municipalTax
		 //ー社宅家賃控除rental
		 //ー社宅共益費控除rentalMgmtFee
		 //ー特別控除specialReduce
		 //+残業加算overTimePlus
		 //+交通費(通勤交通+他の交通費）transportExpense
		 //+特別加算specialAddition
		 //+手当加算allowancePlus
		 float sum = Float.parseFloat(basesalary)
				 - Float.parseFloat(shortageReduce)
				 - Float.parseFloat(allowanceReduce)
				 - Float.parseFloat(wfPensionSelf)
				 - Float.parseFloat(wfHealthSelf)
				 - Float.parseFloat(laborBurden)
				 - Float.parseFloat(withholdingTax)
				 - Float.parseFloat(municipalTax)
				 - Float.parseFloat(rental)
				 - Float.parseFloat(rentalMgmtFee)
				 - Float.parseFloat(specialReduce)
				 + Float.parseFloat(overTimePlus)
				 + Float.parseFloat(transportExpense)
				 + Float.parseFloat(specialAddition)
				 + Float.parseFloat(allowancePlus)   ;

		 salaryInfoBean.setSum(DataUtil.addComma("" + Math.round(sum)));


		/////////////総費用の合計/////////////////////
		//厚生年金控除会社
		 String wfPensionComp = salaryInfoBean.getWelfarePensionComp();
		 if(wfPensionComp==null  || wfPensionComp.length()==0) wfPensionComp="0";
		 //厚生健康控除会社
		 String wfHealthComp = salaryInfoBean.getWelfareHealthComp();
		 if(wfHealthComp==null  || wfHealthComp.length()==0) wfHealthComp="0";
		 //厚生控除子育(会社)
		 String welfareBaby = salaryInfoBean.getWelfareBaby();
		 if(welfareBaby==null  || welfareBaby.length()==0) welfareBaby="0";
		 //雇用保険会社負担
		 String employerBurden = salaryInfoBean.getEplyInsComp();
		 if(employerBurden==null  || employerBurden.length()==0) employerBurden="0";
		 //雇用保拠出金（会社)
		 String employmentInsurance = salaryInfoBean.getEplyInsWithdraw();
		 if(employmentInsurance==null  || employmentInsurance.length()==0) employmentInsurance="0";
		 //労災保険（会社負担のみ）
		 String industrialAccidentInsurance = salaryInfoBean.getWkAcccpsIns();
		 if(industrialAccidentInsurance==null  || industrialAccidentInsurance.length()==0) industrialAccidentInsurance="0";
		 //総費用＝基本給basesalary
		 //＋厚生年金控除会社wfPensionComp
		 //＋厚生健康控除会社wfHealthComp
		 //＋厚生控除子育(会社)welfareBaby
		 //＋雇用保険会社負担employerBurden
		 //＋雇用保拠出金（会社)employmentInsurance
		 //＋労災保険（会社負担のみ）industrialAccidentInsurance
		 //+残業加算overTimePlus
		 //+交通費(通勤交通+他の交通費）transportExpense
		 //+特別加算specialAddition
		 //+手当加算allowancePlus
		 float totalFee = Float.parseFloat(basesalary)
				 + Float.parseFloat(wfPensionComp)
				 + Float.parseFloat(wfHealthComp)
				 + Float.parseFloat(welfareBaby)
				 + Float.parseFloat(employerBurden)
				 + Float.parseFloat(employmentInsurance)
				 + Float.parseFloat(industrialAccidentInsurance)
				 + Float.parseFloat(overTimePlus)
				 + Float.parseFloat(transportExpense)
				 + Float.parseFloat(specialAddition)
				 + Float.parseFloat(allowancePlus)   ;
		 salaryInfoBean.setTotalFee(DataUtil.addComma("" + Math.round(totalFee)));
	}




	public void calSalary2bk(SalaryInfoBean salaryInfoBean) throws ParseException {
		 //社員ID
		 String employeeID=salaryInfoBean.getEmployeeID();
		 //対象年度
		 String year=DateUtil.getNowYear() ;
		 //DBから基本給情報を取得する
		 BaseSalaryInfoEntity baseSalaryInfoEntity=salarylistMapper.getBaseSalary(employeeID,year);

		 //④基本給を取得
		 String basesalary = salaryInfoBean.getBase();
		 if(basesalary==null || basesalary.length()==0) basesalary="0";

		 //残業時間
		 String overTime= salaryInfoBean.getOverTime();
		 if(overTime==null || overTime.length()==0) overTime="0";
		  //残業加算
		 Float overTimePlus = Float.parseFloat(baseSalaryInfoEntity.getOvertimePay()) * Float.parseFloat(overTime);
		 salaryInfoBean.setOverTimePlus(""+ Math.round(overTimePlus));

		 //不足時間
		 String shortage= salaryInfoBean.getShortage();
		 if(shortage==null  || shortage.length()==0) shortage="0";
		 //稼働不足減
		 Float shortageReduce = Float.parseFloat(baseSalaryInfoEntity.getInsufficienttimePay()) * Float.parseFloat(shortage);
		 salaryInfoBean.setShortageReduce(""+ Math.round(shortageReduce));

		 //厚生マスタを取る
		 //⑤厚生保険料を取得
		 WelfarefeeInfoEntity welfarefeeInfoEntity = salarylistMapper.getWfPension(basesalary);

		//厚生年金控除個人
		float wfPensionSelf =
				(( Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(basesalary))/100)/2;
		salaryInfoBean.setWelfarePensionSelf("" + Math.round(wfPensionSelf));
		 //厚生年金控除会社
		float wfPensionComp = wfPensionSelf;
		salaryInfoBean.setWelfarePensionComp("" + Math.round(wfPensionComp));

		 //社員年齢を取る
		String age=salarylistMapper.getAge(employeeID);

		 //厚生健康控除個人
		float wfHealthSelf;
		//厚生健康控除会社
		float wfHealthComp;
		if ( Float.parseFloat(age) >= 40) {
			 wfHealthSelf =
					 ((Float.parseFloat(welfarefeeInfoEntity.getCareRatio()) * Float.parseFloat(basesalary))/100)/2;
			 salaryInfoBean.setWelfareHealthSelf("" + Math.round(wfHealthSelf));
			 wfHealthComp =wfHealthSelf;
			 salaryInfoBean.setWelfareHealthComp("" + Math.round(wfHealthComp));

		}else {
				wfHealthSelf =
						((Float.parseFloat(welfarefeeInfoEntity.getNotCareRatio())* Float.parseFloat(basesalary))/100)/2;
				salaryInfoBean.setWelfareHealthSelf("" + Math.round(wfHealthSelf));
				wfHealthComp=wfHealthSelf;
				salaryInfoBean.setWelfareHealthComp("" + Math.round(wfHealthComp));
		}
		//厚生控除子育(会社)
	    //標準報酬×厚生控除子育(会社)の控除率
		float welfareBaby =
			 (Float.parseFloat(welfarefeeInfoEntity.getBabyCareCompanyRate()) * (Float.parseFloat(basesalary))/100);

		salaryInfoBean.setWelfareBaby("" + Math.round(welfareBaby));

        //////////再計算/////////////
		//最大段階の厚生年金再計算
		if (Float.parseFloat(basesalary) >= 665000) {
		    welfarefeeInfoEntity = salarylistMapper.getWfPension("664000");

			//厚生年金控除個人
			wfPensionSelf =
					(( Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(basesalary))/100)/2;
			salaryInfoBean.setWelfarePensionSelf("" + Math.round(wfPensionSelf));


			 //厚生年金控除会社
			wfPensionComp = wfPensionSelf;
			salaryInfoBean.setWelfarePensionComp("" + Math.round(wfPensionComp));

		}

		//⑧雇用保険率をを取得
		 EmplyinsrateInfoEntity emplyinsrateInfoEntity = salarylistMapper.getEmplyinsrate(year) ;

		 List<EmployeeInfoEntity> epInfo = employeeInfoMapper.getEmployeeID(employeeID);
		 EmployeeInfoEntity emp = epInfo.isEmpty() ? null : epInfo.get(0);
		 //役職を取得
		 String postion = emp.getPosition();
		//雇用保険個人負担
		 float laborBurden = 0;
		//雇用保険会社負担
		 float employerBurden = 0;
		//雇用保拠出金（会社)
		 float employmentInsurance = 0;
		//労災保険（会社負担のみ）
		 float industrialAccidentInsurance = 0;
		 //雇用保険の対象額(修正後：基本給＋交通費＋残業金額＋手当)
		 String transportExpense = "0";
		if(salaryInfoBean.getTransportExpense()!=null  && salaryInfoBean.getTransportExpense().length()!=0)
		{
			transportExpense=salaryInfoBean.getTransportExpense();
		}

		String allowancePlus = "0";
		if(salaryInfoBean.getAllowancePlus()!=null && salaryInfoBean.getAllowancePlus().length()!=0)
		{
			allowancePlus=salaryInfoBean.getAllowancePlus();
		}
		 float hoKenSalary = Float.parseFloat(basesalary)
				             +Float.parseFloat(transportExpense)
				             +overTimePlus
				             +Float.parseFloat(allowancePlus);
		 if(("0").equals(postion)) {
			 laborBurden = 0;
			 employerBurden = 0;
			 employmentInsurance = 0;
			 industrialAccidentInsurance = 0;

		 }else {

			 laborBurden =
					( Float.parseFloat(emplyinsrateInfoEntity.getLaborBurdenRate())*hoKenSalary)/1000 ;

			 employerBurden =
					 (Float.parseFloat(emplyinsrateInfoEntity.getEmployerBurdenRate())*hoKenSalary)/1000 ;

			 employmentInsurance =
					 (Float.parseFloat(emplyinsrateInfoEntity.getContributionRate())*hoKenSalary)/1000 ;

			 industrialAccidentInsurance =
			 (Float.parseFloat(emplyinsrateInfoEntity.getIndustrialAccidentInsuranceRate())*hoKenSalary)/1000 ;

		 }
		 //雇用保険個人負担
		 salaryInfoBean.setEplyInsSelf("" + Math.round(laborBurden));
		 //雇用保険会社負担
		 salaryInfoBean.setEplyInsComp("" + Math.round(employerBurden));
		 //雇用保拠出金（会社)
		 salaryInfoBean.setEplyInsWithdraw("" + Math.round(employmentInsurance));
		 //労災保険（会社負担のみ）
		 salaryInfoBean.setWkAcccpsIns("" + Math.round(industrialAccidentInsurance));

		 //振込総額=基本給basesalary
		 //ー稼働不足減shortageReduce
		 //ー手当減算allowanceReduce
		 //ー厚生年金控除個人wfPensionSelf
		 //ー厚生健康控除個人wfHealthSelf
		 //ー雇用保険個人負担laborBurden
		 //ー源泉WithholdingTax
		 //ー住民税municipalTax
		 //ー社宅家賃控除rental
		 //ー社宅共益費控除rentalMgmtFee
		 //ー特別控除specialReduce
		 //+残業加算overTimePlus
		 //+交通費(通勤交通+他の交通費）transportExpense
		 //+特別加算specialAddition
		 //+手当加算allowancePlus

		 String allowanceReduce = "0";
		if(salaryInfoBean.getAllowanceReduce()!=null && salaryInfoBean.getAllowanceReduce().length()!=0)
		{
			allowanceReduce=salaryInfoBean.getAllowanceReduce();
		}

		String withholdingTax = "0";
		if(salaryInfoBean.getWithholdingTax()!=null && salaryInfoBean.getWithholdingTax().length()!=0)
		{
			withholdingTax=salaryInfoBean.getWithholdingTax();
		}
		String municipalTax = "0";
		if(salaryInfoBean.getMunicipalTax()!=null  && salaryInfoBean.getMunicipalTax().length()!=0)
		{
			municipalTax=salaryInfoBean.getMunicipalTax();
		}
		String rental = "0";
		if(salaryInfoBean.getRental()!=null  && salaryInfoBean.getRental().length()!=0)
		{
			rental=salaryInfoBean.getRental();
		}
		String rentalMgmtFee = "0";
		if(salaryInfoBean.getRentalMgmtFee()!=null && salaryInfoBean.getRentalMgmtFee().length()!=0)
		{
			rentalMgmtFee=salaryInfoBean.getRentalMgmtFee();
		}
		String specialReduce = "0";
		if(salaryInfoBean.getSpecialReduce()!=null  && salaryInfoBean.getSpecialReduce().length()!=0)
		{
			specialReduce=salaryInfoBean.getSpecialReduce();
		}


		String specialAddition = "0";
		if(salaryInfoBean.getSpecialAddition()!=null && salaryInfoBean.getSpecialAddition().length()!=0)
		{
			specialAddition=salaryInfoBean.getSpecialAddition();
		}

		 float sum = Float.parseFloat(basesalary)
				 - shortageReduce
				 - Float.parseFloat(allowanceReduce)
				 - wfPensionSelf
				 - wfHealthSelf
				 - laborBurden
				 - Float.parseFloat(withholdingTax)
				 - Float.parseFloat(municipalTax)
				 - Float.parseFloat(rental)
				 - Float.parseFloat(rentalMgmtFee)
				 - Float.parseFloat(specialReduce)
				 + overTimePlus
				 + Float.parseFloat(transportExpense)
				 + Float.parseFloat(specialAddition)
				 + Float.parseFloat(allowancePlus)   ;
		 salaryInfoBean.setSum("" + Math.round(sum));

		 //総費用＝基本給basesalary
		 //＋厚生年金控除会社wfPensionComp
		 //＋厚生健康控除会社wfHealthComp
		 //＋厚生控除子育(会社)welfareBaby
		 //＋雇用保険会社負担employerBurden
		 //＋雇用保拠出金（会社)employmentInsurance
		 //＋労災保険（会社負担のみ）industrialAccidentInsurance
		 //+残業加算overTimePlus
		 //+交通費(通勤交通+他の交通費）transportExpense
		 //+特別加算specialAddition
		 //+手当加算allowancePlus
		 float totalFee = Float.parseFloat(basesalary)
				 + wfPensionComp
				 + wfHealthComp
				 + welfareBaby
				 + employerBurden
				 + employmentInsurance
				 + industrialAccidentInsurance
				 +  overTimePlus
				 + Float.parseFloat(transportExpense)
				 + Float.parseFloat(specialAddition)
				 + Float.parseFloat(allowancePlus)   ;
		 salaryInfoBean.setTotalFee("" + Math.round(totalFee));
	}
}
