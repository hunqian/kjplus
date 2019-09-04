package com.kjplus.dto;

import java.io.Serializable;

public class TokenUserOrgDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8011059484278062295L;
	private int orgid = 0;
	private int uid = 0;

	public TokenUserOrgDto() {
	}
	
	public TokenUserOrgDto(int orgid, int uid) {
		super();
		this.orgid = orgid;
		this.uid = uid;
	}

	public int getOrgid() {
		return orgid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

}
