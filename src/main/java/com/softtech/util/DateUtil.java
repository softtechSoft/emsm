
package com.softtech.util;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.softtech.common.EmplyinsrateIDName;

/**
 * 概要：データ処理機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */

public class DateUtil {

	/**
	 * 機能：現在月を生成する
	 *
	 * @return 現在月
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static String getNowMonth() {
		// YYYY/MM→yyyymmに変換
		// 現在月生成
		String b;
		Date dNow = new Date( );
		SimpleDateFormat a = new SimpleDateFormat ("yyyyMM");
		 b = a.format(dNow);
		return b;
	}
	public static String getNowMonth1() {
		// YYYY/MM→yyyymmに変換
		// 現在月生成
		String b;
		Date dNow = new Date( );
		SimpleDateFormat a = new SimpleDateFormat ("yyyyMMdd");
		 b = a.format(dNow);
		return b;
	}
	/**
	 * 機能：指定数の過去年度リストを生成する
	 *
	 * @param 過去年度数
	 * @return 過去年度リスト
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static List<EmplyinsrateIDName> getYears(int yearNumber) {

		ArrayList<EmplyinsrateIDName> emplyinsrateIDNameList = new ArrayList<>();

		if (yearNumber < 1) return emplyinsrateIDNameList;

		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy");
        //当年度
        String thisYear = formatter.format(now);
        EmplyinsrateIDName emplyinsrateIDName = new EmplyinsrateIDName();
        emplyinsrateIDName.setYear(thisYear);
        emplyinsrateIDNameList.add(emplyinsrateIDName);
        String wkYear=thisYear;

        for(int i=0;i<yearNumber-1;i++) {
        	String oldYear =new BigDecimal(wkYear).subtract(BigDecimal.ONE).toString();
        	EmplyinsrateIDName emplyinsrateIDNameOld = new EmplyinsrateIDName();
        	emplyinsrateIDNameOld.setYear(oldYear);
            emplyinsrateIDNameList.add(emplyinsrateIDNameOld);
            wkYear=oldYear;
        }

        return emplyinsrateIDNameList;

	}

	/**
	 * 機能：YYYY/MMからYYYMMに変更
	 *
	 * @param 年月
	 * @return 変更後年月
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static String chgMonthToYM(String month) {
		if(month==null) return month;

		// YYYY/MM→yyyymmに変換
		month = month.replace("-", "");
		return month.replace("/", "");
	}
	public static String chgMonthToYM1(String month) {
		// YYYY/MM→yyyymmに変換
		return month.replace(",", "");
	}
	/**
	 * 機能：float→Stringに変換
	 *
	 * @param 年月
	 * @return 変更後年月
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static String formatTosepara(float getTransportExpense1) {
		DecimalFormat df = new DecimalFormat("#,###.00");
		return df.format(getTransportExpense1);
		}
	public static String formatTosepara1(float getTransportExpense1) {
		DecimalFormat df = new DecimalFormat("#,###");
		return df.format(getTransportExpense1);
		}

	/**
	 * 機能：数字チェック。
	 * @return tureとfalse
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static boolean isNumeric(String str){
		  for (int i = str.length();--i>=0;){
		   if (!Character.isDigit(str.charAt(i))){
		    return true;
		   }
		  }
		  return false;
		}
	 /**
     * 過去日チェック。
     * @param
     *  "yyyy-MM-dd
     * * @author 馬@ソフトテク
     * */
    public static boolean isLessThanNow(final String dateStr) {
        final String[] date = dateStr.split("/");
        return LocalDate.now().isAfter(
                LocalDate.of(
                        Integer.parseInt(date[0]),
                        Integer.parseInt(date[1]),
                        Integer.parseInt(date[2])
                )
        );
    }
    /**
     * 通常以外の日付チェック
     * @param
     *  "yyyy-MM-dd"
     * @author 馬@ソフトテク
     * */
    public static boolean isDate(final String dateStr) {
        final DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("yyyy/MM/dd");
        try {
            dateFormat.parse(dateStr);
            return true;
        } catch (DateTimeException e) {
            return false;
        }
    }
    /**
     * 対象年月+1
     * @param
     * @author 馬@ソフトテク
     * @throws ParseException
     * */
    public static String monthplus(String month) throws ParseException {
    	SimpleDateFormat ft = new SimpleDateFormat("yyyyMM");
		// 対象年月string型-->date型。
		Date date = ft.parse(month);
		Calendar time = Calendar.getInstance();
		time.setTime(date);
        //対象年月+1
		time.add(Calendar.MONTH,1);
        Date dt1=time.getTime();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMM");
        // 対象年月date型-->string型。
        String s = f.format(dt1);
    	return s;
    }
    /**
     * 対象年月"/"を追加
     * @param
     * @author 馬@ソフトテク
     * */
    public static String modifymonth(String month){
    	String month1 = month.substring(0,4)+"/"+month.substring(4,6);
    	return month1;

    }
    /**
     * 対象年月日"/"を追加
     * @param
     * @author 馬@ソフトテク
     * */
    public static String modifymonth1(String month){
    	String month1 = month.substring(0,4)+"/"+month.substring(4,6)+"/"+month.substring(6,8);
    	return month1;
    }
    /**
     * 対象年月日"-"を追加
     * @param
     * @author 馬@ソフトテク
     * */
    public static String modifyDateToYMDH(String month){
    	String month1 = month.substring(0,4)+"-"+month.substring(4,6)+"-"+month.substring(6,8);
    	return month1;
    }
    /**
     * 支払日と現在時間判断
     * @param
     * @author 馬@ソフトテク
     * */
    public static boolean isNow(String day) {
        //当前时间
        Date now = new Date();
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMdd");
        //获取今天的日期
        String nowDay = sf.format(now);
        return day.equals(nowDay);
    }
    public static String ma(String a) {
        // 创建一个空的StringBuilder对象
        StringBuilder str = new StringBuilder();
        str.append(a);

        int last = str.length();
        for(int i = last - 3; i > 0; i-=3) {
            str.insert(i,',');
        }
        return str.toString();
    }
	/**
	 * 機能：現在年度を生成する
	 *
	 * @return 現在年度
	 * @exception なし
	 * @author @ソフトテク
	 */
	public static String getNowYear() {
		// YYYY/MM→yyyymmに変換
		// 現在月生成
		String b;
		Date dNow = new Date( );
		SimpleDateFormat a = new SimpleDateFormat ("yyyy");
		 b = a.format(dNow);
		//TETS COMMIT
		 //TEST
		return b;
	}

	//支払20日に固定
	public static String getPayMonth(String month) throws ParseException {
		SimpleDateFormat ft = new SimpleDateFormat("yyyyMM");
		// 対象年月string型-->date型。
		Date date = ft.parse(month);

		Calendar time = Calendar.getInstance();
		time.setTime(date);

        //対象年月+1
		time.add(Calendar.MONTH,1);
        time.set(Calendar.DAY_OF_MONTH,20);
        Date dt1=time.getTime();
        SimpleDateFormat f = new SimpleDateFormat("yyyyMMdd");
        // 対象年月date型-->string型。
        String s = f.format(dt1);
    	return s;


	}





}
