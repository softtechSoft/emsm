
package com.softtech.util;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * 概要：対象月処理機能
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
	/**
	 * 機能：YYYY/MMからYYYMMに変更
	 *
	 * @param 年月
	 * @return 変更後年月
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static String chgMonthToYM(String month) {
		// YYYY/MM→yyyymmに変換
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
}
