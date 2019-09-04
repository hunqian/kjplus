package com.kjplus.model;

import java.io.Serializable;

public class ZanEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3959178282886870297L;
	private Integer id;
	private String code;
	private Integer mainId;
	private String mainType;
	private String mainDesc;
	private Integer zanNum;
	private Integer startTime;
	private Integer lastTime;
	
	public ZanEbo() {
		super();
	}

	public ZanEbo(Integer id, String code, Integer mainId, String mainType, String mainDesc, Integer zanNum,
			Integer startTime, Integer lastTime) {
		super();
		this.id = id;
		this.code = code;
		this.mainId = mainId;
		this.mainType = mainType;
		this.mainDesc = mainDesc;
		this.zanNum = zanNum;
		this.startTime = startTime;
		this.lastTime = lastTime;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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
		return "ZanEbo [id=" + id + ", code=" + code + ", mainId=" + mainId + ", mainType=" + mainType + ", mainDesc="
				+ mainDesc + ", zanNum=" + zanNum + ", startTime=" + startTime + ", lastTime=" + lastTime + "]";
	}
	
}
