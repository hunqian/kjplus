package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class ChatVedioEto {

	private String msgId;
	@Validation
	private Double vedioLenTime;
	@Validation
	private String vedioUrl;

	public ChatVedioEto() {
		super();
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public Double getVedioLenTime() {
		return vedioLenTime;
	}

	public void setVedioLenTime(Double vedioLenTime) {
		this.vedioLenTime = vedioLenTime;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

}
