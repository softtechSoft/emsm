package com.softtech.entity;
/**
 * 概要：基本給情報クラス
 *
 * 作成者：孫@ソフトテク
 * 作成日：2022/5/4
 */

public class BaseSalaryInfoEntity {
	//社員ID
	private String employeeID;
	//基本給
	private int baseSalary;
	//不足減単価(h)
	private int minusHour;
	//残業加単価(h)
	private int plusHour;
	//稼働期間From
	private int wkPeriodFrom;
	//稼働期間To
	private int wkPeriodTo;
	//利用ステータス
	private int status;
	//作成日
	private String insertDate;
	//更新日
	private String updateDate;
	//基本給ID
	private String baseSalaryID;

	//get,set,toString,hashCode,equals
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public int getBaseSalary() {
		return baseSalary;
	}
	public void setBaseSalary(int baseSalary) {
		this.baseSalary = baseSalary;
	}
	public int getMinusHour() {
		return minusHour;
	}
	public void setMinusHour(int minusHour) {
		this.minusHour = minusHour;
	}
	public int getPlusHour() {
		return plusHour;
	}
	public void setPlusHour(int plusHour) {
		this.plusHour = plusHour;
	}
	public int getWkPeriodFrom() {
		return wkPeriodFrom;
	}
	public void setWkPeriodFrom(int wkPeriodFrom) {
		this.wkPeriodFrom = wkPeriodFrom;
	}
	public int getWkPeriodTo() {
		return wkPeriodTo;
	}
	public void setWkPeriodTo(int wkPeriodTo) {
		this.wkPeriodTo = wkPeriodTo;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
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
	public String getBaseSalaryID() {
		return baseSalaryID;
	}
	public void setBaseSalaryID(String baseSalaryID) {
		this.baseSalaryID = baseSalaryID;
	}
	@Override
	public String toString() {
		return "BaseSalaryInfo [employeeID=" + employeeID + ", baseSalary=" + baseSalary + ", minusHour=" + minusHour
				+ ", plusHour=" + plusHour + ", wkPeriodFrom=" + wkPeriodFrom + ", wkPeriodTo=" + wkPeriodTo
				+ ", status=" + status + ", insertDate=" + insertDate + ", updateDate=" + updateDate + ", baseSalaryID="
				+ baseSalaryID + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + baseSalary;
		result = prime * result + ((baseSalaryID == null) ? 0 : baseSalaryID.hashCode());
		result = prime * result + ((employeeID == null) ? 0 : employeeID.hashCode());
		result = prime * result + ((insertDate == null) ? 0 : insertDate.hashCode());
		result = prime * result + minusHour;
		result = prime * result + plusHour;
		result = prime * result + status;
		result = prime * result + ((updateDate == null) ? 0 : updateDate.hashCode());
		result = prime * result + wkPeriodFrom;
		result = prime * result + wkPeriodTo;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BaseSalaryInfoEntity other = (BaseSalaryInfoEntity) obj;
		if (baseSalary != other.baseSalary)
			return false;
		if (baseSalaryID == null) {
			if (other.baseSalaryID != null)
				return false;
		} else if (!baseSalaryID.equals(other.baseSalaryID))
			return false;
		if (employeeID == null) {
			if (other.employeeID != null)
				return false;
		} else if (!employeeID.equals(other.employeeID))
			return false;
		if (insertDate == null) {
			if (other.insertDate != null)
				return false;
		} else if (!insertDate.equals(other.insertDate))
			return false;
		if (minusHour != other.minusHour)
			return false;
		if (plusHour != other.plusHour)
			return false;
		if (status != other.status)
			return false;
		if (updateDate == null) {
			if (other.updateDate != null)
				return false;
		} else if (!updateDate.equals(other.updateDate))
			return false;
		if (wkPeriodFrom != other.wkPeriodFrom)
			return false;
		if (wkPeriodTo != other.wkPeriodTo)
			return false;
		return true;
	}



}
