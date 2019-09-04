package com.kjplus.eto;

public class ZanHisEto {

	private Integer mainId = 0;
	private String mainType = "";
	private Integer zanId = 0;
	private Integer uid;
	private String ipAddr;

	public ZanHisEto() {
		super();
	}

	public Integer getZanId() {
		return zanId;
	}

	public void setZanId(Integer zanId) {
		this.zanId = zanId;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

}
