package com.kjplus.model;

import java.io.Serializable;

public class ChatTextMsgEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -391087595493501440L;
	private String id;
	private String msgId;
	private String msgBody;

	public ChatTextMsgEbo() {
		super();
	}

	public ChatTextMsgEbo(String id, String msgId, String msgBody) {
		super();
		this.id = id;
		this.msgId = msgId;
		this.msgBody = msgBody;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "ChatTextMsgEbo [id=" + id + ", msgId=" + msgId + ", msgBody=" + msgBody + "]";
	}

}
