package com.softtech.actionForm;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

/**
 * @program
 * @概要:
 * @作成者:孫曄
 * @作成日:2022-08-10
 * @return:
 */
public class IncomeTaxInfoFormBean {
    //社員ID
    private String employeeID;
    //社員name
    private String employeeName;
    //所得税ID
//    @NotEmpty(message = "所得税IDを入力してください。")
    private String incomeTaxID;
    //年度
    @NotEmpty(message = "年度を入力してください。")
    @Pattern(message = "年度に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String year;
    
    private String searchYear;
    
    //所得税
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax1;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax2;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax3;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax4;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax5;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax6;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax7;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax8;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax9;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax10;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax11;
    @NotEmpty(message = "所得税を入力してください。")
    @Pattern(message = "所得税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String incomeTax12;
    //住民税
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax1;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax2;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax3;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax4;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax5;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax6;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax7;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax8;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax9;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax10;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax11;
    @NotEmpty(message = "住民税を入力してください。")
    @Pattern(message = "住民税に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String residentTax12;
    
 // 社宅家賃字段
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental01;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental02;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental03;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental04;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental05;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental06;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental07;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental08;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental09;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental10;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental11;
    @NotEmpty(message = "社宅家賃を入力してください。")
    @Pattern(message = "社宅家賃に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rental12;

    // 共益費字段
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee01;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee02;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee03;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee04;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee05;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee06;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee07;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee08;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee09;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee10;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee11;
    @NotEmpty(message = "共益費を入力してください。")
    @Pattern(message = "共益費に数値のみを入力してください。", regexp = "^[0-9]*$")
    private String rentalMgmtFee12;
    
    //利用ステータス 0:未使用　1:使用中
    private String status;
    //作成日
    private String insertDate;
    //更新日
    private String updateDate;
    //更新と新規区別用のFlg
    private String insertFlg;

    public String getIncomeTaxID() {
        return incomeTaxID;
    }

    public void setIncomeTaxID(String incomeTaxID) {
        this.incomeTaxID = incomeTaxID;
    }
    
    public String getEmployeeName() {
        return employeeName;
    }
    
    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    public String getSearchYear() {
        return searchYear;  
    }

    public void setSearchYear(String searchYear) {
        this.searchYear = searchYear;
    }

    public String getIncomeTax1() {
        return incomeTax1;
    }

    public void setIncomeTax1(String incomeTax1) {
        this.incomeTax1 = incomeTax1;
    }

    public String getIncomeTax2() {
        return incomeTax2;
    }

    public void setIncomeTax2(String incomeTax2) {
        this.incomeTax2 = incomeTax2;
    }

    public String getIncomeTax3() {
        return incomeTax3;
    }

    public void setIncomeTax3(String incomeTax3) {
        this.incomeTax3 = incomeTax3;
    }

    public String getIncomeTax4() {
        return incomeTax4;
    }

    public void setIncomeTax4(String incomeTax4) {
        this.incomeTax4 = incomeTax4;
    }

    public String getIncomeTax5() {
        return incomeTax5;
    }

    public void setIncomeTax5(String incomeTax5) {
        this.incomeTax5 = incomeTax5;
    }

    public String getIncomeTax6() {
        return incomeTax6;
    }

    public void setIncomeTax6(String incomeTax6) {
        this.incomeTax6 = incomeTax6;
    }

    public String getIncomeTax7() {
        return incomeTax7;
    }

    public void setIncomeTax7(String incomeTax7) {
        this.incomeTax7 = incomeTax7;
    }

    public String getIncomeTax8() {
        return incomeTax8;
    }

    public void setIncomeTax8(String incomeTax8) {
        this.incomeTax8 = incomeTax8;
    }

    public String getIncomeTax9() {
        return incomeTax9;
    }

    public void setIncomeTax9(String incomeTax9) {
        this.incomeTax9 = incomeTax9;
    }

    public String getIncomeTax10() {
        return incomeTax10;
    }

    public void setIncomeTax10(String incomeTax10) {
        this.incomeTax10 = incomeTax10;
    }

    public String getIncomeTax11() {
        return incomeTax11;
    }

    public void setIncomeTax11(String incomeTax11) {
        this.incomeTax11 = incomeTax11;
    }

    public String getIncomeTax12() {
        return incomeTax12;
    }

    public void setIncomeTax12(String incomeTax12) {
        this.incomeTax12 = incomeTax12;
    }

    public String getResidentTax1() {
        return residentTax1;
    }

    public void setResidentTax1(String residentTax1) {
        this.residentTax1 = residentTax1;
    }

    public String getResidentTax2() {
        return residentTax2;
    }

    public void setResidentTax2(String residentTax2) {
        this.residentTax2 = residentTax2;
    }

    public String getResidentTax3() {
        return residentTax3;
    }

    public void setResidentTax3(String residentTax3) {
        this.residentTax3 = residentTax3;
    }

    public String getResidentTax4() {
        return residentTax4;
    }

    public void setResidentTax4(String residentTax4) {
        this.residentTax4 = residentTax4;
    }

    public String getResidentTax5() {
        return residentTax5;
    }

    public void setResidentTax5(String residentTax5) {
        this.residentTax5 = residentTax5;
    }

    public String getResidentTax6() {
        return residentTax6;
    }

    public void setResidentTax6(String residentTax6) {
        this.residentTax6 = residentTax6;
    }

    public String getResidentTax7() {
        return residentTax7;
    }

    public void setResidentTax7(String residentTax7) {
        this.residentTax7 = residentTax7;
    }

    public String getResidentTax8() {
        return residentTax8;
    }

    public void setResidentTax8(String residentTax8) {
        this.residentTax8 = residentTax8;
    }

    public String getResidentTax9() {
        return residentTax9;
    }

    public void setResidentTax9(String residentTax9) {
        this.residentTax9 = residentTax9;
    }

    public String getResidentTax10() {
        return residentTax10;
    }

    public void setResidentTax10(String residentTax10) {
        this.residentTax10 = residentTax10;
    }

    public String getResidentTax11() {
        return residentTax11;
    }

    public void setResidentTax11(String residentTax11) {
        this.residentTax11 = residentTax11;
    }

    public String getResidentTax12() {
        return residentTax12;
    }

    public void setResidentTax12(String residentTax12) {
        this.residentTax12 = residentTax12;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getInsertDate() {
        return insertDate;
    }

    public void setInsertDate(String insertDate) {
        this.insertDate = insertDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public String getInsertFlg() {
        return insertFlg;
    }

    public void setInsertFlg(String insertFlg) {
        this.insertFlg = insertFlg;
    }

    public String getEmployeeID() {
        return employeeID;
    }

    public void setEmployeeID(String employeeID) {
        this.employeeID = employeeID;
    }

	public String getRental01() {
		return rental01;
	}

	public void setRental01(String rental01) {
		this.rental01 = rental01;
	}

	public String getRental02() {
		return rental02;
	}

	public void setRental02(String rental02) {
		this.rental02 = rental02;
	}

	public String getRental03() {
		return rental03;
	}

	public void setRental03(String rental03) {
		this.rental03 = rental03;
	}

	public String getRental04() {
		return rental04;
	}

	public void setRental04(String rental04) {
		this.rental04 = rental04;
	}

	public String getRental05() {
		return rental05;
	}

	public void setRental05(String rental05) {
		this.rental05 = rental05;
	}

	public String getRental06() {
		return rental06;
	}

	public void setRental06(String rental06) {
		this.rental06 = rental06;
	}

	public String getRental07() {
		return rental07;
	}

	public void setRental07(String rental07) {
		this.rental07 = rental07;
	}

	public String getRental08() {
		return rental08;
	}

	public void setRental08(String rental08) {
		this.rental08 = rental08;
	}

	public String getRental09() {
		return rental09;
	}

	public void setRental09(String rental09) {
		this.rental09 = rental09;
	}

	public String getRental10() {
		return rental10;
	}

	public void setRental10(String rental10) {
		this.rental10 = rental10;
	}

	public String getRental11() {
		return rental11;
	}

	public void setRental11(String rental11) {
		this.rental11 = rental11;
	}

	public String getRental12() {
		return rental12;
	}

	public void setRental12(String rental12) {
		this.rental12 = rental12;
	}

	public String getRentalMgmtFee01() {
		return rentalMgmtFee01;
	}

	public void setRentalMgmtFee01(String rentalMgmtFee01) {
		this.rentalMgmtFee01 = rentalMgmtFee01;
	}

	public String getRentalMgmtFee02() {
		return rentalMgmtFee02;
	}

	public void setRentalMgmtFee02(String rentalMgmtFee02) {
		this.rentalMgmtFee02 = rentalMgmtFee02;
	}

	public String getRentalMgmtFee03() {
		return rentalMgmtFee03;
	}

	public void setRentalMgmtFee03(String rentalMgmtFee03) {
		this.rentalMgmtFee03 = rentalMgmtFee03;
	}

	public String getRentalMgmtFee04() {
		return rentalMgmtFee04;
	}

	public void setRentalMgmtFee04(String rentalMgmtFee04) {
		this.rentalMgmtFee04 = rentalMgmtFee04;
	}

	public String getRentalMgmtFee05() {
		return rentalMgmtFee05;
	}

	public void setRentalMgmtFee05(String rentalMgmtFee05) {
		this.rentalMgmtFee05 = rentalMgmtFee05;
	}

	public String getRentalMgmtFee06() {
		return rentalMgmtFee06;
	}

	public void setRentalMgmtFee06(String rentalMgmtFee06) {
		this.rentalMgmtFee06 = rentalMgmtFee06;
	}

	public String getRentalMgmtFee07() {
		return rentalMgmtFee07;
	}

	public void setRentalMgmtFee07(String rentalMgmtFee07) {
		this.rentalMgmtFee07 = rentalMgmtFee07;
	}

	public String getRentalMgmtFee08() {
		return rentalMgmtFee08;
	}

	public void setRentalMgmtFee08(String rentalMgmtFee08) {
		this.rentalMgmtFee08 = rentalMgmtFee08;
	}

	public String getRentalMgmtFee09() {
		return rentalMgmtFee09;
	}

	public void setRentalMgmtFee09(String rentalMgmtFee09) {
		this.rentalMgmtFee09 = rentalMgmtFee09;
	}

	public String getRentalMgmtFee10() {
		return rentalMgmtFee10;
	}

	public void setRentalMgmtFee10(String rentalMgmtFee10) {
		this.rentalMgmtFee10 = rentalMgmtFee10;
	}

	public String getRentalMgmtFee11() {
		return rentalMgmtFee11;
	}

	public void setRentalMgmtFee11(String rentalMgmtFee11) {
		this.rentalMgmtFee11 = rentalMgmtFee11;
	}

	public String getRentalMgmtFee12() {
		return rentalMgmtFee12;
	}

	public void setRentalMgmtFee12(String rentalMgmtFee12) {
		this.rentalMgmtFee12 = rentalMgmtFee12;
	}
    
    
}
