package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class ChatPicEto {

	@Validation
	private String msgId;
	@Validation
	private String picType;
	@Validation
	private String picUrl;

	public ChatPicEto() {
		super();
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getPicType() {
		return picType;
	}

	public void setPicType(String picType) {
		this.picType = picType;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

}
