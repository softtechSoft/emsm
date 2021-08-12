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
		if(sb.isEmpty()) return sb;

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
}
