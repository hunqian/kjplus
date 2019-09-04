package com.kjplus.dto;

import java.util.ArrayList;
import java.util.List;

public class SysRegionsDto {
	
	private Integer regionId;
	private String localName;
	private String zipcode;
	private List<SysRegionsDto> subs = new ArrayList<SysRegionsDto>();

	public SysRegionsDto() {
		super();
	}

	public Integer getRegionId() {
		return regionId;
	}

	public void setRegionId(Integer regionId) {
		this.regionId = regionId;
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

	public List<SysRegionsDto> getSubs() {
		return subs;
	}

	public void setSubs(List<SysRegionsDto> subs) {
		this.subs = subs;
	}
	
}
