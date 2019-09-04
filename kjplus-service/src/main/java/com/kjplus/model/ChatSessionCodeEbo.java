package com.kjplus.model;

import java.io.Serializable;

public class ChatSessionCodeEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9011825754618236267L;
	private String uid;
	private String frmSessionCode;
	private String toSessionCode;
	
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getFrmSessionCode() {
		return frmSessionCode;
	}
	public void setFrmSessionCode(String frmSessionCode) {
		this.frmSessionCode = frmSessionCode;
	}
	public String getToSessionCode() {
		return toSessionCode;
	}
	public void setToSessionCode(String toSessionCode) {
		this.toSessionCode = toSessionCode;
	}
	
	@Override
	public String toString() {
		return "ChatSessionCodeEbo [uid=" + uid + ", frmSessionCode="
				+ frmSessionCode + ", toSessionCode=" + toSessionCode + "]";
	}
	
	
}
