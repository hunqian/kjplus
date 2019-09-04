package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class ChatCountenaceEto {

	private String msgId;
	@Validation
	private String ctneType;
	private String ctneCode;
	private String dynamicPicUrl;

	public ChatCountenaceEto() {
		super();
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getCtneType() {
		return ctneType;
	}

	public void setCtneType(String ctneType) {
		this.ctneType = ctneType;
	}

	public String getCtneCode() {
		return ctneCode;
	}

	public void setCtneCode(String ctneCode) {
		this.ctneCode = ctneCode;
	}

	public String getDynamicPicUrl() {
		return dynamicPicUrl;
	}

	public void setDynamicPicUrl(String dynamicPicUrl) {
		this.dynamicPicUrl = dynamicPicUrl;
	}

}
