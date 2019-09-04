package com.kjplus.dto;

import java.io.Serializable;

public class UserInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5559117789953160028L;
	private String sessionCode;

	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	@Override
	public String toString() {
		return "UserInfoDto [sessionCode=" + sessionCode + "]";
	}
}
