package com.kjplus.eto;

import java.io.Serializable;

import com.kjplus.annotation.Validation;

public class ChatContactsEto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8572794017992186834L;
	private int id;
	@Validation
	private String thisSessionCode = null;// 我的ID this_session_code
	@Validation
	private String contSessionCode = null;// 联系人ID cont_session_code
	private String contSign;// 联系人个性签名

	public ChatContactsEto() {
		super();
	}

	public ChatContactsEto(String thisSessionCode, String contSessionCode, String contSign) {
		super();
		this.thisSessionCode = thisSessionCode;
		this.contSessionCode = contSessionCode;
		this.contSign = contSign;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getThisSessionCode() {
		return thisSessionCode;
	}

	public void setThisSessionCode(String thisSessionCode) {
		this.thisSessionCode = thisSessionCode;
	}

	public String getContSessionCode() {
		return contSessionCode;
	}

	public void setContSessionCode(String contSessionCode) {
		this.contSessionCode = contSessionCode;
	}

	public String getContSign() {
		return contSign;
	}

	public void setContSign(String contSign) {
		this.contSign = contSign;
	}

	@Override
	public String toString() {
		return "ChatContactsEto [thisSessionCode=" + thisSessionCode + ", contSessionCode=" + contSessionCode + ", contSign="
				+ contSign + "]";
	}

}
