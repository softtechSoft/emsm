package com.softtech.common;

import java.io.Serializable;

import lombok.Data;

@Data
public class BirthStone implements Serializable {

    private static final long serialVersionUID = 1L;

    /** 月 */
    public String month;

    /** 名前 */
    public String name;

    /** 色 */
    public String color;

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
	 * @return name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name セットする name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return color
	 */
	public String getColor() {
		return color;
	}

	/**
	 * @param color セットする color
	 */
	public void setColor(String color) {
		this.color = color;
	}

}
