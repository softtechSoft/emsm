package com.softtech.util;

/**
 * 概要：データ処理機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */
public class DataUtil {
	/*
	 * 機能：機能リスト変更
	 *
	 * @param 機能
	 * @return 変更後機能
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static String functionText(String function) {
		// &#xe666;&emsp;社員情報管理→社員情報管理に変換
		return function.substring(function.lastIndexOf(";")+1);
	}
	public static String functionText1(String sb) {
		String str = sb.substring(0, sb.indexOf("."));
		return str;
	}
	/*
	 * 機能：koma(,)を除く。
	 *   123,456 → 123456
	 * @param 変更前文字列
	 * @return 変更後文字列
	 * @exception なし
	 * @author @ソフトテク
	 */
	public static String deleteComma(String sb) {
		if(sb.isEmpty()) return "0";

		String str = sb.replaceAll(",", "");
		return str;
	}
	/*
	 * 機能：koma(,)を追加。
	 *   123456 → 123,456
	 * @param 変更前文字列
	 * @return 変更後文字列
	 * @exception なし
	 * @author @ソフトテク
	 */
	public static String addComma(String sb) {
		if(sb.isEmpty()) return sb;

		StringBuilder str = new StringBuilder();
        str.append(sb);

        int last = str.length();
        for(int i = last - 3; i > 0; i-=3) {
            str.insert(i,',');
        }
        return str.toString();
	}
	/*
	 * 機能：数字チェック。
	 *
	 * @return ture:数字である、false　数字ではない。
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static boolean isNumeric(String str){

		  for (int i = str.length();--i>=0;){
			  if (!Character.isDigit(str.charAt(i))){
				  return false;
			  }
		  }

		  return true;
		}
	/*
	 * 機能：採番する
	 *
	 * @param nowID 現在ID
	 * @param headerIndex ヘッダー桁数
	 * @return 新ID
	 * @exception なし
	 * @author 馬@ソフトテク
	 */
	public static String getNextID(String nowID,int headerIndex)
	{
		if( nowID==null || nowID.length() <=headerIndex) return nowID;
		// 桁数取得
		int length=nowID.length();
		// ヘッダ
		String header = nowID.substring(0, headerIndex);
		// +1 にする
		String nextNumber= Integer.toString( Integer.parseInt( nowID.substring(headerIndex))+1);
		// 新ID
		int endIndex = length-nextNumber.length();
		endIndex = endIndex-headerIndex;

		for (int i=0;i<endIndex;i++) {
			nextNumber = "0"+nextNumber;
		}
		String nextID = header + nextNumber;
		return nextID;
	}

	public static String getNextMonth(String nowYear) {

		String yearMonth=nowYear.substring(0,6);
		String year=yearMonth.substring(0,4);
		String nextMonth = Integer.toString(Integer.parseInt(yearMonth.substring(4,6))+1);

		String newMonth=year + nextMonth;

		return newMonth;



	}





}
