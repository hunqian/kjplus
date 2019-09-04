package com.ybk.bean;

import java.util.Date;

import org.ybk.basic.util.Util;

public class AvgPrice {
	private Date day;
	private double price;

	public AvgPrice(Date date, double price) {
		super();
		this.day = date;
		this.price = price;
	}

	public String getD() {
		if (day == null)
			return null;
		else
			return Util.getDayStr(day);
	}

	public void setDay(Date date) {
		this.day = date;
	}

	public double getP() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

}
