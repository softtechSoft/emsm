package com.softtech.util;
/**
 * 概要：データ処理機能
 *
 * 作成者：馬@ソフトテク
 * 作成日：2021/4/10
 */
public class DataUtil {
	/**
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
}
