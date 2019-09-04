package com.kjplus.model;

import java.io.Serializable;

public class ChatCountenaceEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6270266024821862398L;
	private Integer id;
	private String msgId;
	private String ctneType;
	private String ctneCode;
	private String dynamicPicUrl;

	public ChatCountenaceEbo() {
		super();
	}

	public ChatCountenaceEbo(Integer id, String msgId, String ctneType, String ctneCode, String dynamicPicUrl) {
		super();
		this.id = id;
		this.msgId = msgId;
		this.ctneType = ctneType;
		this.ctneCode = ctneCode;
		this.dynamicPicUrl = dynamicPicUrl;
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

	@Override
	public String toString() {
		return "ChatCountenaceEbo [id=" + id + ", msgId=" + msgId + ", ctneType=" + ctneType + ", ctneCode=" + ctneCode
				+ ", dynamicPicUrl=" + dynamicPicUrl + "]";
	}

}
