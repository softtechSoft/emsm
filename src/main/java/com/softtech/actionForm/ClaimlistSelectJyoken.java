package com.softtech.actionForm;

import javax.validation.constraints.Size;

public class ClaimlistSelectJyoken {
	//対象年月
		@Size(min=2, max=30,message="対象年月を入力してください。例：202104")
		private String month ;
		private String companyName ;

		//ダウンロード
		private boolean downloadFlg;

			/**
			 * @return month
			 */
			public String getMonth() {
				return month;
			}

			/**
			 * @param month セットする month
			 */
			public void setMonth(String month) {
				this.month = month;
			}

			/**
			 * @return downloadFlg
			 */
			public boolean getDownloadFlg() {
				return downloadFlg;
			}

			/**
			 * @param downloadFlg セットする downloadFlg
			 */
			public void setDownloadFlg(boolean downloadFlg) {
				this.downloadFlg = downloadFlg;
			}

			/**
			 * @return companyName
			 */
			public String getCompanyName() {
				return companyName;
			}

			/**
			 * @param companyName セットする companyName
			 */
			public void setCompanyName(String companyName) {
				this.companyName = companyName;
			}

}


