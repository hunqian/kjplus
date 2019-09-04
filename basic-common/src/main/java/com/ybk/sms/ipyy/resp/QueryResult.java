package com.ybk.sms.ipyy.resp;

import javax.xml.bind.annotation.XmlRootElement;

//调用返回结果
@XmlRootElement(name="returnsms")
public class QueryResult {
	private String returnstatus;
	private String message;
	private String payinfo;
	private String sendTotal;
	private Double overage;

	public QueryResult() {
	}

	public QueryResult(String returnstatus, String message, String payinfo, String sendTotal, Double overage) {
		super();
		this.returnstatus = returnstatus;
		this.message = message;
		this.payinfo = payinfo;
		this.sendTotal = sendTotal;
		this.overage = overage;
	}

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getPayinfo() {
		return payinfo;
	}

	public void setPayinfo(String payinfo) {
		this.payinfo = payinfo;
	}

	public String getSendTotal() {
		return sendTotal;
	}

	public void setSendTotal(String sendTotal) {
		this.sendTotal = sendTotal;
	}

	public Double getOverage() {
		return overage;
	}

	public void setOverage(Double overage) {
		this.overage = overage;
	}

		
}
