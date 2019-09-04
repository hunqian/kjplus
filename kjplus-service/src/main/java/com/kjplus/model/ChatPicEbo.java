package com.kjplus.model;

import java.io.Serializable;

public class ChatPicEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9117722617046710760L;
	private Integer id;
	private String msgId;
	private String picType;
	private String picUrl;

	public ChatPicEbo() {
		super();
	}

	public ChatPicEbo(Integer id, String msgId, String picType, String picUrl) {
		super();
		this.id = id;
		this.msgId = msgId;
		this.picType = picType;
		this.picUrl = picUrl;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	@Override
	public String toString() {
		return "ChatPicEbo [id=" + id + ", msgId=" + msgId + ", picType=" + picType + ", picUrl=" + picUrl + "]";
	}

}
