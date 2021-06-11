package com.softtech.actionForm;

import javax.validation.constraints.Size;

////865412

public class SalarylistBean3  {
	//対象年月
		@Size(min=2, max=30,message="対象月を入力してください。例：202104")
		private String month ;
//　郭　競合テスト２
		//ダウンロード
		private int downloadFlg;

//　同じ行でないない修正
		private String EmployeeIDb;
		private String Nameb;
		private String Addressb;
		private String MakeDistinction;
		private String Make;
		private String loadFlg;
		private String cFlg;
		private String EmployeeMonthFlg;
		private String EmployeeIDFlg;
		private String employeeName ;
		private String employeeID ;
		@Size(min=2, max=30,message="支払日を入力してください。例：2021/08/15")
		private String paymentDate ;
		@Size(min=1, max=30,message="基本給を入力してください。例：60000")
		private String base ;
		private String overTimePlus ;
		private String shortageReduce ;
		private String transportExpense ;
		private String allowancePlus ;
		private String allowanceReduce;
		private String allowanceReason ;
		private String welfarePensionSelf;
		private String welfareHealthSelf;
		private String eplyInsSelf;
		private String withholdingTax;
		private String municipalTax;
		private String rental;
		private String rentalMgmtFee;
		@Size(min=1, max=30,message="総額を入力してください。例：60000")
		private String sum;
		private String remark;
		@Size(min=1, max=30,message="総費用を入力してください。例：60000")
		private String totalFee;
		private String wkAcccpsIns;
		private String eplyInsWithdraw;
		private String eplyInsComp;
		private String welfareBaby;
		private String welfarePensionComp;
		private String welfareHealthComp;
		private String overTime;
		private String shortage;
		private String address;
		private String welfareSelf;
		private String welfareComp;

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
			 * @return employeeIDFlg
			 */
			public String getEmployeeIDFlg() {
				return EmployeeIDFlg;
			}

			/**
			 * @param employeeIDFlg セットする employeeIDFlg
			 */
			public void setEmployeeIDFlg(String employeeIDFlg) {
				EmployeeIDFlg = employeeIDFlg;
			}

			/**
			 * @return downloadFlg
			 */
			public int getDownloadFlg() {
				return downloadFlg;
			}

			/**
			 * @param downloadFlg セットする downloadFlg
			 */
			public void setDownloadFlg(int downloadFlg) {
				this.downloadFlg = downloadFlg;
			}

			/**
			 * @return employeeName
			 */
			public String getEmployeeName() {
				return employeeName;
			}

			/**
			 * @param employeeName セットする employeeName
			 */
			public void setEmployeeName(String employeeName) {
				this.employeeName = employeeName;
			}

			/**
			 * @return employeeID
			 */
			public String getEmployeeID() {
				return employeeID;
			}

			/**
			 * @param employeeID セットする employeeID
			 */
			public void setEmployeeID(String employeeID) {
				this.employeeID = employeeID;
			}

			/**
			 * @return paymentDate
			 */
			public String getPaymentDate() {
				return paymentDate;
			}

			/**
			 * @param paymentDate セットする paymentDate
			 */
			public void setPaymentDate(String paymentDate) {
				this.paymentDate = paymentDate;
			}

			/**
			 * @return base
			 */
			public String getBase() {
				return base;
			}

			/**
			 * @param base セットする base
			 */
			public void setBase(String base) {
				this.base = base;
			}

			/**
			 * @return overTimePlus
			 */
			public String getOverTimePlus() {
				return overTimePlus;
			}

			/**
			 * @param overTimePlus セットする overTimePlus
			 */
			public void setOverTimePlus(String overTimePlus) {
				this.overTimePlus = overTimePlus;
			}

			/**
			 * @return shortageReduce
			 */
			public String getShortageReduce() {
				return shortageReduce;
			}

			/**
			 * @param shortageReduce セットする shortageReduce
			 */
			public void setShortageReduce(String shortageReduce) {
				this.shortageReduce = shortageReduce;
			}

			/**
			 * @return transportExpense
			 */
			public String getTransportExpense() {
				return transportExpense;
			}

			/**
			 * @param transportExpense セットする transportExpense
			 */
			public void setTransportExpense(String transportExpense) {
				this.transportExpense = transportExpense;
			}

			/**
			 * @return allowancePlus
			 */
			public String getAllowancePlus() {
				return allowancePlus;
			}

			/**
			 * @param allowancePlus セットする allowancePlus
			 */
			public void setAllowancePlus(String allowancePlus) {
				this.allowancePlus = allowancePlus;
			}

			/**
			 * @return allowanceReduce
			 */
			public String getAllowanceReduce() {
				return allowanceReduce;
			}

			/**
			 * @param allowanceReduce セットする allowanceReduce
			 */
			public void setAllowanceReduce(String allowanceReduce) {
				this.allowanceReduce = allowanceReduce;
			}

			/**
			 * @return allowanceReason
			 */
			public String getAllowanceReason() {
				return allowanceReason;
			}

			/**
			 * @param allowanceReason セットする allowanceReason
			 */
			public void setAllowanceReason(String allowanceReason) {
				this.allowanceReason = allowanceReason;
			}

			/**
			 * @return welfarePensionSelf
			 */
			public String getWelfarePensionSelf() {
				return welfarePensionSelf;
			}

			/**
			 * @param welfarePensionSelf セットする welfarePensionSelf
			 */
			public void setWelfarePensionSelf(String welfarePensionSelf) {
				this.welfarePensionSelf = welfarePensionSelf;
			}

			/**
			 * @return welfareHealthSelf
			 */
			public String getWelfareHealthSelf() {
				return welfareHealthSelf;
			}

			/**
			 * @param welfareHealthSelf セットする welfareHealthSelf
			 */
			public void setWelfareHealthSelf(String welfareHealthSelf) {
				this.welfareHealthSelf = welfareHealthSelf;
			}

			/**
			 * @return eplyInsSelf
			 */
			public String getEplyInsSelf() {
				return eplyInsSelf;
			}

			/**
			 * @param eplyInsSelf セットする eplyInsSelf
			 */
			public void setEplyInsSelf(String eplyInsSelf) {
				this.eplyInsSelf = eplyInsSelf;
			}

			/**
			 * @return withholdingTax
			 */
			public String getWithholdingTax() {
				return withholdingTax;
			}

			/**
			 * @param withholdingTax セットする withholdingTax
			 */
			public void setWithholdingTax(String withholdingTax) {
				this.withholdingTax = withholdingTax;
			}

			/**
			 * @return municipalTax
			 */
			public String getMunicipalTax() {
				return municipalTax;
			}

			/**
			 * @param municipalTax セットする municipalTax
			 */
			public void setMunicipalTax(String municipalTax) {
				this.municipalTax = municipalTax;
			}

			/**
			 * @return rental
			 */
			public String getRental() {
				return rental;
			}

			/**
			 * @param rental セットする rental
			 */
			public void setRental(String rental) {
				this.rental = rental;
			}

			/**
			 * @return rentalMgmtFee
			 */
			public String getRentalMgmtFee() {
				return rentalMgmtFee;
			}

			/**
			 * @param rentalMgmtFee セットする rentalMgmtFee
			 */
			public void setRentalMgmtFee(String rentalMgmtFee) {
				this.rentalMgmtFee = rentalMgmtFee;
			}

			/**
			 * @return sum
			 */
			public String getSum() {
				return sum;
			}

			/**
			 * @param sum セットする sum
			 */
			public void setSum(String sum) {
				this.sum = sum;
			}

			/**
			 * @return remark
			 */
			public String getRemark() {
				return remark;
			}

			/**
			 * @param remark セットする remark
			 */
			public void setRemark(String remark) {
				this.remark = remark;
			}

			/**
			 * @return totalFee
			 */
			public String getTotalFee() {
				return totalFee;
			}

			/**
			 * @param totalFee セットする totalFee
			 */
			public void setTotalFee(String totalFee) {
				this.totalFee = totalFee;
			}

			/**
			 * @return wkAcccpsIns
			 */
			public String getWkAcccpsIns() {
				return wkAcccpsIns;
			}

			/**
			 * @param wkAcccpsIns セットする wkAcccpsIns
			 */
			public void setWkAcccpsIns(String wkAcccpsIns) {
				this.wkAcccpsIns = wkAcccpsIns;
			}

			/**
			 * @return eplyInsWithdraw
			 */
			public String getEplyInsWithdraw() {
				return eplyInsWithdraw;
			}

			/**
			 * @param eplyInsWithdraw セットする eplyInsWithdraw
			 */
			public void setEplyInsWithdraw(String eplyInsWithdraw) {
				this.eplyInsWithdraw = eplyInsWithdraw;
			}

			/**
			 * @return eplyInsComp
			 */
			public String getEplyInsComp() {
				return eplyInsComp;
			}

			/**
			 * @param eplyInsComp セットする eplyInsComp
			 */
			public void setEplyInsComp(String eplyInsComp) {
				this.eplyInsComp = eplyInsComp;
			}

			/**
			 * @return welfareBaby
			 */
			public String getWelfareBaby() {
				return welfareBaby;
			}

			/**
			 * @param welfareBaby セットする welfareBaby
			 */
			public void setWelfareBaby(String welfareBaby) {
				this.welfareBaby = welfareBaby;
			}

			/**
			 * @return welfarePensionComp
			 */
			public String getWelfarePensionComp() {
				return welfarePensionComp;
			}

			/**
			 * @param welfarePensionComp セットする welfarePensionComp
			 */
			public void setWelfarePensionComp(String welfarePensionComp) {
				this.welfarePensionComp = welfarePensionComp;
			}

			/**
			 * @return welfareHealthComp
			 */
			public String getWelfareHealthComp() {
				return welfareHealthComp;
			}

			/**
			 * @param welfareHealthComp セットする welfareHealthComp
			 */
			public void setWelfareHealthComp(String welfareHealthComp) {
				this.welfareHealthComp = welfareHealthComp;
			}

			/**
			 * @return overTime
			 */
			public String getOverTime() {
				return overTime;
			}

			/**
			 * @param overTime セットする overTime
			 */
			public void setOverTime(String overTime) {
				this.overTime = overTime;
			}

			/**
			 * @return shortage
			 */
			public String getShortage() {
				return shortage;
			}

			/**
			 * @param shortage セットする shortage
			 */
			public void setShortage(String shortage) {
				this.shortage = shortage;
			}

			/**
			 * @return address
			 */
			public String getAddress() {
				return address;
			}

			/**
			 * @param address セットする address
			 */
			public void setAddress(String address) {
				this.address = address;
			}

			/**
			 * @return welfareSelf
			 */
			public String getWelfareSelf() {
				return welfareSelf;
			}

			/**
			 * @param welfareSelf セットする welfareSelf
			 */
			public void setWelfareSelf(String welfareSelf) {
				this.welfareSelf = welfareSelf;
			}

			/**
			 * @return welfareComp
			 */
			public String getWelfareComp() {
				return welfareComp;
			}

			/**
			 * @param welfareComp セットする welfareComp
			 */
			public void setWelfareComp(String welfareComp) {
				this.welfareComp = welfareComp;
			}

			/**
			 * @return employeeMonthFlg
			 */
			public String getEmployeeMonthFlg() {
				return EmployeeMonthFlg;
			}

			/**
			 * @param employeeMonthFlg セットする employeeMonthFlg
			 */
			public void setEmployeeMonthFlg(String employeeMonthFlg) {
				EmployeeMonthFlg = employeeMonthFlg;
			}

			/**
			 * @return loadFlg
			 */
			public String getLoadFlg() {
				return loadFlg;
			}

			/**
			 * @param loadFlg セットする loadFlg
			 */
			public void setLoadFlg(String loadFlg) {
				this.loadFlg = loadFlg;
			}

			/**
			 * @return cFlg
			 */
			public String getcFlg() {
				return cFlg;
			}

			/**
			 * @param cFlg セットする cFlg
			 */
			public void setcFlg(String cFlg) {
				this.cFlg = cFlg;
			}
			/**
			 * @return make
			 */
			public String getMake() {
				return Make;
			}

			/**
			 * @param make セットする make
			 */
			public void setMake(String make) {
				Make = make;
			}

			/**
			 * @return makeDistinction
			 */
			public String getMakeDistinction() {
				return MakeDistinction;
			}

			/**
			 * @param makeDistinction セットする makeDistinction
			 */
			public void setMakeDistinction(String makeDistinction) {
				MakeDistinction = makeDistinction;
			}

			/**
			 * @return employeeIDb
			 */
			public String getEmployeeIDb() {
				return EmployeeIDb;
			}

			/**
			 * @param employeeIDb セットする employeeIDb
			 */
			public void setEmployeeIDb(String employeeIDb) {
				EmployeeIDb = employeeIDb;
			}

			/**
			 * @return nameb
			 */
			public String getNameb() {
				return Nameb;
			}

			/**
			 * @param nameb セットする nameb
			 */
			public void setNameb(String nameb) {
				Nameb = nameb;
			}

			/**
			 * @return addressb
			 */
			public String getAddressb() {
				return Addressb;
			}

			/**
			 * @param addressb セットする addressb
			 */
			public void setAddressb(String addressb) {
				Addressb = addressb;
			}


}


