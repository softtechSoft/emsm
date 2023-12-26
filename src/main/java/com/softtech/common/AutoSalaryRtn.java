package com.softtech.common;

public class AutoSalaryRtn {
	 // 対象社員
	 private String emplyeeName;

	// 対象年度
	private String year;

	// 対象年月
	private String yearMonth;

	// 結果
	// 0:成功
	// 99:データ新規エラー
	// 1:基本給データ存在していません.
	//2:厚生保険料データが存在していません。
	//3:勤怠情報データが存在していません。
	//4:交通情報データが存在していません。
	//5:雇用保険率データが存在していません。
	//6:所得税と住民税データが存在していません。
	//7:マスタ_厚生子育徴収率データが存在していません。


	private String rtn;

	/**
	 * @return emplyeeName
	 */
	public String getEmplyeeName() {
		return emplyeeName;
	}


	/**
	 * @param emplyeeName セットする emplyeeName
	 */
	public void setEmplyeeName(String emplyeeName) {
		this.emplyeeName = emplyeeName;
	}


	/**
	 * @return year
	 */
	public String getYear() {
		return year;
	}


	/**
	 * @param year セットする year
	 */
	public void setYear(String year) {
		this.year = year;
	}


	/**
	 * @return yearMonth
	 */
	public String getYearMonth() {
		return yearMonth;
	}


	/**
	 * @param yearMonth セットする yearMonth
	 */
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}


	/**
	 * @return rtn
	 */
	public String getRtn() {
		return rtn;
	}


	/**
	 * @param rtn セットする rtn
	 */
	public void setRtn(String rtn) {
		this.rtn = rtn;
	}
}
