package com.kjplus.eto;

import org.ybk.basic.util.DateUtil;

public class ZanEto {

	public static final int CODE_LEN = 32;
	private String code;
	private Integer mainId;
	private String mainType;
	private String mainDesc;
	private Integer zanNum = 0;
	private Integer startTime = DateUtil.getCurTimeInSec();
	private Integer lastTime = DateUtil.getCurTimeInSec();
	
	public ZanEto() {
		super();
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	public String getMainDesc() {
		return mainDesc;
	}

	public void setMainDesc(String mainDesc) {
		this.mainDesc = mainDesc;
	}

	public Integer getZanNum() {
		return zanNum;
	}

	public void setZanNum(Integer zanNum) {
		this.zanNum = zanNum;
	}

	public Integer getStartTime() {
		return startTime;
	}

	public void setStartTime(Integer startTime) {
		this.startTime = startTime;
	}

	public Integer getLastTime() {
		return lastTime;
	}

	public void setLastTime(Integer lastTime) {
		this.lastTime = lastTime;
	}

	@Override
	public String toString() {
		return "ZanEto [code=" + code + ", mainId=" + mainId + ", mainType=" + mainType + ", mainDesc=" + mainDesc
				+ ", zanNum=" + zanNum + ", startTime=" + startTime + ", lastTime=" + lastTime + "]";
	}
	
}
