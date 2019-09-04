package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class ChatTextMsgEto {

	@Validation
	private String msgId;
	@Validation
	private String msgBody;

	public ChatTextMsgEto() {
		super();
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

}
