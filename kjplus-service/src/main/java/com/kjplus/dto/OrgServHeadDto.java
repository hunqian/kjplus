package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class OrgServHeadDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1719989478174132816L;
	private Integer orgId;
	private String orgCode;
	private String orgName;
	private List<ServHeadDto> srvHeads = new ArrayList<ServHeadDto>();

	public OrgServHeadDto() {
		super();
	}

	public OrgServHeadDto(Integer orgId, String orgCode, String orgName, List<ServHeadDto> srvHeads) {
		super();
		this.orgId = orgId;
		this.orgCode = orgCode;
		this.orgName = orgName;
		this.srvHeads = srvHeads;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public List<ServHeadDto> getSrvHeads() {
		return srvHeads;
	}

	public void setSrvHeads(List<ServHeadDto> srvHeads) {
		this.srvHeads = srvHeads;
	}

	@Override
	public String toString() {
		return "OrgServHeadDto [orgId=" + orgId + ", orgCode=" + orgCode + ", orgName=" + orgName + ", srvHeads="
				+ srvHeads + "]";
	}

}
