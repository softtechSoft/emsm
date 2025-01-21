package com.softtech.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

public class ExpenseListEntity {

	private String expensesID; // 経費ID
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate accrualDate; // 発生日
	private BigDecimal cost; // 金額
	private String tantouName; // 担当者
	private String settlementType; // 精算種別 '0':現金, '1':口座
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private LocalDate settlementDate; // 精算日
	private String expensesType; // 経費種別 '1':一般経費, '2':固定経費
	private String deleteFlg; // 削除フラグ '0':未削除, '1':削除
	private String happenAddress; // 用途

	// ------------------ Getter / Setter ------------------
	public String getExpensesID() {
		return expensesID;
	}

	public void setExpensesID(String expensesID) {
		this.expensesID = expensesID;
	}

	public LocalDate getAccrualDate() {
		return accrualDate;
	}

	public void setAccrualDate(LocalDate accrualDate) {
		this.accrualDate = accrualDate;
	}

	public BigDecimal getCost() {
		return cost;
	}

	public void setCost(BigDecimal cost) {
		this.cost = cost;
	}

	public String getTantouName() {
		return tantouName;
	}

	public void setTantouName(String tantouName) {
		this.tantouName = tantouName;
	}

	public String getSettlementType() {
		return settlementType;
	}

	public void setSettlementType(String settlementType) {
		this.settlementType = settlementType;
	}

	public LocalDate getSettlementDate() {
		return settlementDate;
	}

	public void setSettlementDate(LocalDate settlementDate) {
		this.settlementDate = settlementDate;
	}

	public String getExpensesType() {
		return expensesType;
	}

	public void setExpensesType(String expensesType) {
		this.expensesType = expensesType;
	}

	public String getDeleteFlg() {
		return deleteFlg;
	}

	public void setDeleteFlg(String deleteFlg) {
		this.deleteFlg = deleteFlg;
	}

	public String getHappenAddress() {
		return happenAddress;
	}

	public void setHappenAddress(String happenAddress) {
		this.happenAddress = happenAddress;
	}

}
