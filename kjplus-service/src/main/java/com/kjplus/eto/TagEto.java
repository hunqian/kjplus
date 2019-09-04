package com.kjplus.eto;

public class TagEto {

	private Integer mainId;
	private String mainType;
	private Integer refValId;
	
	public TagEto() {
		super();
	}

	public TagEto(Integer mainId, String mainType, Integer refValId) {
		super();
		this.mainId = mainId;
		this.mainType = mainType;
		this.refValId = refValId;
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

	public Integer getRefValId() {
		return refValId;
	}

	public void setRefValId(Integer refValId) {
		this.refValId = refValId;
	}

	@Override
	public String toString() {
		return "TagEbo [mainId=" + mainId + ", mainType=" + mainType + ", refValId=" + refValId + "]";
	}
	
}
