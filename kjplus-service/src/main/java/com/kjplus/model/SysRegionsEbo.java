package com.kjplus.model;

import java.io.Serializable;

public class SysRegionsEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6084727123108754884L;
	private Integer regionId;
	private Integer pRegionId;
	private String regionPath;
	private Integer regionGrade;
	private String localName;
	private String zipcode;
	private String cod;

	public SysRegionsEbo() {
		super();
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
	}

	public Integer getpRegionId() {
		return pRegionId;
	}

	public void setpRegionId(Integer pRegionId) {
		this.pRegionId = pRegionId;
	}

	public String getRegionPath() {
		return regionPath;
	}

	public void setRegionPath(String regionPath) {
		this.regionPath = regionPath;
	}

	public Integer getRegionGrade() {
		return regionGrade;
	}

	public void setRegionGrade(Integer regionGrade) {
		this.regionGrade = regionGrade;
	}

	public String getLocalName() {
		return localName;
	}

	public void setLocalName(String localName) {
		this.localName = localName;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCod() {
		return cod;
	}

	public void setCod(String cod) {
		this.cod = cod;
	}
	
}
