package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class CanlendarActivityEto {

	@Validation
	private String calInfoCode;
	@Validation
	private Integer mainId;// 业务主键 每个业务都需要在此表中生成关联
	@Validation
	private String mainType;

	public CanlendarActivityEto() {
		super();
	}

	public CanlendarActivityEto(String calInfoCode, Integer mainId, String mainType) {
		super();
		this.calInfoCode = calInfoCode;
		this.mainId = mainId;
		this.mainType = mainType;
	}

	public String getCalInfoCode() {
		return calInfoCode;
	}

	public void setCalInfoCode(String calInfoCode) {
		this.calInfoCode = calInfoCode;
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
