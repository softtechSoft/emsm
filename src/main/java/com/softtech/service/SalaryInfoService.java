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
	public List<FieldError>  checkData(SalaryInfoBean salaryInfoBean) {

		// エラーチェック用リスト
		List<FieldError> errorlst = new ArrayList<FieldError>();
		// 基本給数字チェック。
		if(DateUtil.isNumeric(DateUtil.chgMonthToYM1(salaryInfoBean.getBase()))) {
			FieldError err1 = new FieldError("", "", "基本給が数字を入力してください。例：60,000");
			errorlst.add(err1);
		}
		// 残業時間数字チェック。
//		if(DateUtil.isNumeric(salaryInfoBean.getOverTime())) {
//			FieldError err2 = new FieldError("", "", "残業時間が数字を入力してください。例：20");
//			errorlst.add(err2);
//		}
		// 残業時間数字チェック（正の整数または小数）
		String overTime = salaryInfoBean.getOverTime();
		if (!NumberUtils.isParsable(overTime) || Double.parseDouble(overTime) < 0) {
		    FieldError err2 = new FieldError("", "", "残業時間は正の数字（整数または小数）で入力してください。例：20 または 1.5");
		    errorlst.add(err2);
		}
		// 不足時間数字チェック。
//		if(DateUtil.isNumeric(salaryInfoBean.getShortage())) {
//			FieldError err3= new FieldError("", "", "不足時間が数字を入力してください。例：20");
//			errorlst.add(err3);
//		}
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
	 * @return DB登録用Entity
	 */
	public SalaryInfoBean transferToGamen(SalaryInfoEntity salaryInfo) {
		// DBから社員ID福祉情報を取得
		//WelfareInfo welfareInfoDB= queryWelfare(salaryInfo.getEmployeeID());
		//if(welfareInfoDB==null) {
//			welfareInfoDB.setBase(0);
//			welfareInfoDB.setEplyInsComp(0);
//			welfareInfoDB.setEplyInsSelf(0);
//			welfareInfoDB.setEplyInsWithdraw(0);
//			welfareInfoDB.setMunicipalTax(0);
//			welfareInfoDB.setRental(0);
//			welfareInfoDB.setRentalMgmtFee(0);
//			welfareInfoDB.setWelfareBaby(0);
//			welfareInfoDB.setWelfareHealthComp(0);
//			welfareInfoDB.setWelfareHealthSelf(0);
//			welfareInfoDB.setWelfarePensionComp(0);
//			welfareInfoDB.setWelfarePensionSelf(0);
//			welfareInfoDB.setWithholdingTax(0);
//			welfareInfoDB.setWkAcccpsIns(0);
		//}
		//DBから取得したデータを画面データへ変換する
		SalaryInfoBean salaryInfoBean = new SalaryInfoBean();
		//社員ID
		salaryInfoBean.setEmployeeID(salaryInfo.getEmployeeID());
		//氏名
		salaryInfoBean.setEmployeeName(salaryInfo.getEmployeeName());
		//住所
		salaryInfoBean.setAddress(salaryInfo.getAddress());
		//対象月
		salaryInfoBean.setMonth(salaryInfo.getMonth());
		//基本給
//		if(welfareInfoDB == null ) {
//			salaryInfoBean.setBase("0");
//		}else {
//			salaryInfoBean.setBase(DateUtil.ma(Integer.toString(welfareInfoDB.getBase())));
//		}
		salaryInfoBean.setBase(salaryInfo.getBase());
		//支払日
		salaryInfoBean.setPaymentDate(salaryInfo.getPaymentDate());
		//残業時間
		salaryInfoBean.setOverTime(salaryInfo.getOverTime());
		//不足時間
		salaryInfoBean.setShortage(salaryInfo.getShortage());
		//残業加算
		salaryInfoBean.setOverTimePlus(salaryInfo.getOverTimePlus());
		//稼働不足減
		salaryInfoBean.setShortageReduce(salaryInfo.getShortageReduce());
		//交通費
		salaryInfoBean.setTransportExpense(salaryInfo.getTransportExpense());
		//特別加算
		//特別加算
		salaryInfoBean.setSpecialAddition(salaryInfo.getSpecialAddition());
		//手当加算
		salaryInfoBean.setAllowancePlus(salaryInfo.getAllowancePlus());
		//手当減算
		salaryInfoBean.setAllowanceReduce(salaryInfo.getAllowanceReduce());
		//手当理由
		salaryInfoBean.setSpecialAddition(salaryInfo.getAllowanceReason());
//		if(welfareInfoDB != null ) {
//			//厚生年金控除個人
//			salaryInfoBean.setWelfarePensionSelf(DateUtil.ma(Integer.toString(welfareInfoDB.getWelfarePensionSelf())));
//			//厚生健康控除個人
//			salaryInfoBean.setWelfareHealthSelf(DateUtil.ma(Integer.toString(welfareInfoDB.getWelfareHealthSelf())));
//			//厚生年金控除会社
//			salaryInfoBean.setWelfarePensionComp(DateUtil.ma(Integer.toString(welfareInfoDB.getWelfarePensionComp())));
//			//厚生健康控除会社
//			salaryInfoBean.setWelfareHealthComp(DateUtil.ma(Integer.toString(welfareInfoDB.getWelfareHealthComp())));
//			//厚生控除子育(会社）
//			salaryInfoBean.setWelfareBaby(DateUtil.ma(Integer.toString(welfareInfoDB.getWelfareBaby())));
//			//雇用保険個人負担
//			salaryInfoBean.setEplyInsSelf(DateUtil.ma(Integer.toString(welfareInfoDB.getEplyInsSelf())));
//			//雇用保険会社負担
//			salaryInfoBean.setEplyInsComp(DateUtil.ma(Integer.toString(welfareInfoDB.getEplyInsComp())));
//			//雇用保拠出金（会社)
//			salaryInfoBean.setEplyInsWithdraw(DateUtil.ma(Integer.toString(welfareInfoDB.getEplyInsWithdraw())));
//			//労災保険（会社負担のみ）
//			salaryInfoBean.setWkAcccpsIns(DateUtil.ma(Integer.toString(welfareInfoDB.getWkAcccpsIns())));
//			//源泉控除
//			salaryInfoBean.setWithholdingTax(DateUtil.ma(Integer.toString(welfareInfoDB.getWithholdingTax())));
//			//住民税控除
//			salaryInfoBean.setMunicipalTax(DateUtil.ma(Integer.toString(welfareInfoDB.getMunicipalTax1())));
//			//社宅家賃控除
//			salaryInfoBean.setRental(DateUtil.ma(Integer.toString(welfareInfoDB.getRental())));
//			//社宅共益費控除
//			salaryInfoBean.setRentalMgmtFee(DateUtil.ma(Integer.toString(welfareInfoDB.getRentalMgmtFee())));
//
//		}
		//厚生年金控除個人
		salaryInfoBean.setWelfarePensionSelf(salaryInfo.getWelfarePensionSelf());
		//厚生健康控除個人
		salaryInfoBean.setWelfareHealthSelf(salaryInfo.getWelfareHealthSelf());
		//厚生年金控除会社
		salaryInfoBean.setWelfarePensionComp(salaryInfo.getWelfarePensionComp());
		//厚生健康控除会社
		salaryInfoBean.setWelfareHealthComp(salaryInfo.getWelfareHealthComp());
		//厚生控除子育(会社）
		salaryInfoBean.setWelfareBaby(salaryInfo.getWelfareBaby());
		//雇用保険個人負担
		salaryInfoBean.setEplyInsSelf(salaryInfo.getEplyInsSelf());
		//雇用保険会社負担
		salaryInfoBean.setEplyInsComp(salaryInfo.getEplyInsComp());
		//雇用保拠出金（会社)
		salaryInfoBean.setEplyInsWithdraw(salaryInfo.getEplyInsWithdraw());
		//労災保険（会社負担のみ）
		salaryInfoBean.setWkAcccpsIns(salaryInfo.getWkAcccpsIns());
		//源泉控除
		salaryInfoBean.setWithholdingTax(salaryInfo.getWithholdingTax());
		//住民税控除
		salaryInfoBean.setMunicipalTax(salaryInfo.getMunicipalTax());
		//社宅家賃控除
		salaryInfoBean.setRental(salaryInfo.getRental());
		//社宅共益費控除
		salaryInfoBean.setRentalMgmtFee(salaryInfo.getRentalMgmtFee());

		//特別控除
		salaryInfoBean.setShortageReduce(salaryInfo.getShortageReduce());
		//総額
		salaryInfoBean.setSum(salaryInfo.getSum());
		//総費用
		salaryInfoBean.setTotalFee(salaryInfo.getTotalFee());
		//備考
		salaryInfoBean.setRemark(salaryInfo.getRemark());

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
		if(salaryInfoBean.getOverTimePlus().length()==0) {
	     	salaryInfoBean.setOverTimePlus("0") ;
		}
		//残業加算
		if(salaryInfoBean.getOverTimePlus()!= null) {
			am.setOverTimePlus(Integer.parseInt(salaryInfoBean.getOverTimePlus()));
		}

		if(salaryInfoBean.getShortageReduce().length()==0) {
	     	salaryInfoBean.setShortageReduce("0") ;
		}

		//稼働不足減
		if(salaryInfoBean.getShortageReduce()!= null) {
			am.setShortageReduce(Integer.parseInt(salaryInfoBean.getShortageReduce()));
		}

		if(salaryInfoBean.getTransportExpense().length()==0) {
	     	salaryInfoBean.setTransportExpense("0") ;
		}
		//交通費
		am.setTransportExpense(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getTransportExpense())));


		if(salaryInfoBean.getAllowancePlus().length()==0) {
	     	salaryInfoBean.setAllowancePlus("0") ;
		}

		//特別加算
		am.setSpecialAddition(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getSpecialAddition())));


		if(salaryInfoBean.getAllowancePlus().length()==0) {
			salaryInfoBean.setAllowancePlus("0") ;
		}

		//手当加算
		if(salaryInfoBean.getAllowancePlus()!= null) {
			am.setAllowancePlus(Integer.parseInt(salaryInfoBean.getAllowancePlus()));
		}
		if(salaryInfoBean.getAllowanceReduce().length()==0) {
	     	salaryInfoBean.setAllowanceReduce("0") ;
		}
		//手当減算
		if(salaryInfoBean.getAllowanceReduce()!= null) {
			am.setAllowanceReduce(Integer.parseInt(salaryInfoBean.getAllowanceReduce()));
		}
		//手当理由
		am.setAllowanceReason(salaryInfoBean.getAllowanceReason());
		if(salaryInfoBean.getEplyInsSelf().length()==0) {
	     	salaryInfoBean.setEplyInsSelf("0") ;
		}
		//雇用保険個人負担
		am.setEplyInsSelf(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getEplyInsSelf())));
		if(salaryInfoBean.getWithholdingTax().length()==0) {
	     	salaryInfoBean.setWithholdingTax("0") ;
		}
		//源泉控除
		am.setWithholdingTax(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWithholdingTax())));
		if(salaryInfoBean.getMunicipalTax().length()==0) {
	     	salaryInfoBean.setMunicipalTax("0") ;
		}
		//住民税控除
		am.setMunicipalTax(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getMunicipalTax())));
		if(salaryInfoBean.getRental().length()==0) {
	     	salaryInfoBean.setRental("0") ;
		}
		//社宅家賃控除
		am.setRental(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getRental())));
		if(salaryInfoBean.getRentalMgmtFee().length()==0) {
	     	salaryInfoBean.setRentalMgmtFee("0") ;
		}
		//社宅共益費控除
		am.setRentalMgmtFee(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getRentalMgmtFee())));

		//特別控除
		am.setSpecialReduce(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getSpecialReduce())));
		//総額
		am.setSum(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getSum())));
		//備考
		am.setRemark(salaryInfoBean.getRemark());
		//総費用
		am.setTotalFee(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getTotalFee())));
		if(salaryInfoBean.getWkAcccpsIns().length()==0) {
	     	salaryInfoBean.setWkAcccpsIns("0") ;
		}
		//労災保険（会社負担のみ）
		am.setWkAcccpsIns(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWkAcccpsIns())));
		if(salaryInfoBean.getEplyInsWithdraw().length()==0) {
	     	salaryInfoBean.setEplyInsWithdraw("0") ;
		}
		//雇用保拠出金（会社)
		am.setEplyInsWithdraw(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getEplyInsWithdraw())));
		if(salaryInfoBean.getEplyInsComp().length()==0) {
	     	salaryInfoBean.setEplyInsComp("0") ;
		}
		//雇用保険会社負担
		am.setEplyInsComp(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getEplyInsComp())));
		if(salaryInfoBean.getWelfareBaby().length()==0) {
	     	salaryInfoBean.setWelfareBaby("0") ;
		}
		//厚生控除子育(会社）
		am.setWelfareBaby(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWelfareBaby())));
		if(salaryInfoBean.getOverTime().length()==0) {
	     	salaryInfoBean.setOverTime("0") ;
		}
		//残業時間
		if(salaryInfoBean.getOverTime() !=null ) {
			am.setOverTime(Integer.parseInt(salaryInfoBean.getOverTime()));
		}
		if(salaryInfoBean.getShortage().length()==0) {
	     	salaryInfoBean.setShortage("0") ;
		}
		//不足時間
		if(salaryInfoBean.getShortage() !=null ) {
			am.setShortage(Integer.parseInt(salaryInfoBean.getShortage()));
		}
		if(salaryInfoBean.getWelfarePensionSelf().length()==0) {
	     	salaryInfoBean.setWelfarePensionSelf("0") ;
		}
		//厚生年金控除個人
		am.setWelfareSelf(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWelfarePensionSelf())));
		if(salaryInfoBean.getWelfareHealthSelf().length()==0) {
	     	salaryInfoBean.setWelfareHealthSelf("0") ;
		}
		//厚生健康控除個人
		am.setWelfareComp(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWelfareHealthSelf())));
		if(salaryInfoBean.getWelfarePensionComp().length()==0) {
	     	salaryInfoBean.setWelfarePensionComp("0") ;
		}
		//厚生年金控除会社
		am.setWelfareComp(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWelfarePensionComp())));
		if(salaryInfoBean.getWelfareHealthComp().length()==0) {
	     	salaryInfoBean.setWelfareHealthComp("0") ;
		}
		//厚生健康控除会社
		am.setWelfareComp(Integer.parseInt(DataUtil.deleteComma(salaryInfoBean.getWelfareHealthComp())));
		return am;
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

	public void calSalary(SalaryInfoBean salaryInfoBean) throws ParseException {
			 //社員ID
			 String employeeID=salaryInfoBean.getEmployeeID();
			 //対象年度
			 String year=DateUtil.getNowYear() ;
			 //DBから基本給情報を取得する
			 BaseSalaryInfoEntity baseSalaryInfoEntity=salarylistMapper.getBaseSalary(employeeID,year);

			 //④基本給を取得
			 String basesalary = salaryInfoBean.getBase();

			 //残業時間
			 String overTime= salaryInfoBean.getOverTime();
			  //残業加算
			 salaryInfoBean.setOverTimePlus(""+ Float.parseFloat(baseSalaryInfoEntity.getOvertimePay()) * Float.parseFloat(overTime));

			 //不足時間
			 String shortage= salaryInfoBean.getShortage();
			 //稼働不足減
			 salaryInfoBean.setShortageReduce(""+ Float.parseFloat(baseSalaryInfoEntity.getInsufficienttimePay()) * Float.parseFloat(shortage));

			 //厚生マスタを取る
			 //⑤厚生保険料を取得
			 WelfarefeeInfoEntity welfarefeeInfoEntity = salarylistMapper.getWfPension(basesalary);

			//厚生年金控除個人
			float wfPensionSelf =
					(( Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(welfarefeeInfoEntity.getStandSalary()))/100)/2;
			salaryInfoBean.setWelfarePensionSelf(Float.toString(wfPensionSelf));
			 //厚生年金控除会社
			float wfPensionComp = wfPensionSelf;
			salaryInfoBean.setWelfarePensionComp(Float.toString(wfPensionComp));

			 //社員年齢を取る
			String age=salarylistMapper.getAge(employeeID);
			 //厚生健康控除個人
			float wfHealthSelf;
			//厚生健康控除会社
			float wfHealthComp;
			if ( Float.parseFloat(age) >= 40) {
				 wfHealthSelf =
						 ((Float.parseFloat(welfarefeeInfoEntity.getCareRatio()) * Float.parseFloat(basesalary))/100)/2;
				 salaryInfoBean.setWelfareHealthSelf( Float.toString(wfHealthSelf));
				 wfHealthComp =wfHealthSelf;
				 salaryInfoBean.setWelfareHealthComp(Float.toString(wfHealthComp));

			}else {
					wfHealthSelf =
							((Float.parseFloat(welfarefeeInfoEntity.getNotCareRatio())* Float.parseFloat(basesalary))/100)/2;
					salaryInfoBean.setWelfareHealthSelf(Float.toString(wfHealthSelf));
					wfHealthComp=wfHealthSelf;
					salaryInfoBean.setWelfareHealthComp(Float.toString(wfHealthComp));
			}
			//厚生控除子育(会社)
		    //標準報酬×厚生控除子育(会社)の控除率
			float welfareBaby =
				 (Float.parseFloat(welfarefeeInfoEntity.getBabyCareCompanyRate()) * (Float.parseFloat(basesalary))/100);

			salaryInfoBean.setWelfareBaby(Float.toString(welfareBaby));

             //////////再計算/////////////
			//最大段階の厚生年金再計算
			if (Float.parseFloat(basesalary) >= 665000) {
			    welfarefeeInfoEntity = salarylistMapper.getWfPension("664000");

				//厚生年金控除個人
				wfPensionSelf =
						(( Float.parseFloat(welfarefeeInfoEntity.getAnnuityRatio()) * Float.parseFloat(basesalary))/100)/2;
				salaryInfoBean.setWelfarePensionSelf(Float.toString(wfPensionSelf));


				 //厚生年金控除会社
				wfPensionComp = wfPensionSelf;
				salaryInfoBean.setWelfarePensionComp(Float.toString(wfPensionComp));

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
			 float hoKenSalary = Float.parseFloat(basesalary)
					             +Float.parseFloat(salaryInfoBean.getTransportExpense())
					             +Float.parseFloat(salaryInfoBean.getOverTimePlus())
					             +Float.parseFloat(salaryInfoBean.getAllowancePlus());
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
						 (Float.parseFloat(emplyinsrateInfoEntity.getEmplyinsrate())*hoKenSalary)/1000 ;

				 industrialAccidentInsurance =
				 (Float.parseFloat(emplyinsrateInfoEntity.getIndustrialAccidentInsuranceRate())*hoKenSalary)/1000 ;

			 }
			 //雇用保険個人負担
			 salaryInfoBean.setEplyInsSelf(Float.toString(laborBurden));
			 //雇用保険会社負担
			 salaryInfoBean.setEplyInsComp(Float.toString(employerBurden));
			 //雇用保拠出金（会社)
			 salaryInfoBean.setEplyInsWithdraw(Float.toString(employmentInsurance));
			 //労災保険（会社負担のみ）
			 salaryInfoBean.setWkAcccpsIns(Float.toString(industrialAccidentInsurance));

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
			 String shortageReduce = "0";
			if(salaryInfoBean.getShortageReduce()!=null)
			{
				shortageReduce=salaryInfoBean.getShortageReduce();
			}

			 String allowanceReduce = "0";
			if(salaryInfoBean.getAllowanceReduce()!=null)
			{
				allowanceReduce=salaryInfoBean.getAllowanceReduce();
			}

			String withholdingTax = "0";
			if(salaryInfoBean.getWithholdingTax()!=null)
			{
				withholdingTax=salaryInfoBean.getWithholdingTax();
			}
			String municipalTax = "0";
			if(salaryInfoBean.getMunicipalTax()!=null)
			{
				municipalTax=salaryInfoBean.getMunicipalTax();
			}
			String rental = "0";
			if(salaryInfoBean.getRental()!=null)
			{
				rental=salaryInfoBean.getRental();
			}
			String rentalMgmtFee = "0";
			if(salaryInfoBean.getRentalMgmtFee()!=null)
			{
				rentalMgmtFee=salaryInfoBean.getRentalMgmtFee();
			}
			String specialReduce = "0";
			if(salaryInfoBean.getSpecialReduce()!=null)
			{
				specialReduce=salaryInfoBean.getSpecialReduce();
			}
			String overTimePlus = "0";
			if(salaryInfoBean.getOverTimePlus()!=null)
			{
				overTimePlus=salaryInfoBean.getOverTimePlus();
			}
			String transportExpense = "0";
			if(salaryInfoBean.getTransportExpense()!=null)
			{
				transportExpense=salaryInfoBean.getTransportExpense();
			}
			String specialAddition = "0";
			if(salaryInfoBean.getSpecialAddition()!=null)
			{
				specialAddition=salaryInfoBean.getSpecialAddition();
			}
			String allowancePlus = "0";
			if(salaryInfoBean.getAllowancePlus()!=null)
			{
				specialAddition=salaryInfoBean.getAllowancePlus();
			}
			 float sum = Float.parseFloat(basesalary)
					 - Float.parseFloat(shortageReduce)
					 - Float.parseFloat(allowanceReduce)
					 - wfPensionSelf
					 - wfHealthSelf
					 - laborBurden
					 - Float.parseFloat(withholdingTax)
					 - Float.parseFloat(municipalTax)
					 - Float.parseFloat(rental)
					 - Float.parseFloat(rentalMgmtFee)
					 - Float.parseFloat(specialReduce)
					 + Float.parseFloat(overTimePlus)
					 + Float.parseFloat(transportExpense)
					 + Float.parseFloat(specialAddition)
					 + Float.parseFloat(allowancePlus)   ;
			 salaryInfoBean.setSum(Float.toString(sum));

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
					 +  Float.parseFloat(overTimePlus)
					 + Float.parseFloat(transportExpense)
					 + Float.parseFloat(specialAddition)
					 + Float.parseFloat(allowancePlus)   ;
			 salaryInfoBean.setTotalFee(Float.toString(totalFee));
	}

}
