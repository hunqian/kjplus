package com.kjplus.model;

import java.io.Serializable;

public class ChatVedioEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6702085798978668446L;
	private Integer id;
	private String msgId;
	private String vedioType;
	private Double vedioLenTime;
	private String vedioUrl;

	public ChatVedioEbo() {
		super();
	}

	public ChatVedioEbo(Integer id, String msgId, String vedioType, Double vedioLenTime, String vedioUrl) {
		super();
		this.id = id;
		this.msgId = msgId;
		this.vedioType = vedioType;
		this.vedioLenTime = vedioLenTime;
		this.vedioUrl = vedioUrl;
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

	public String getVedioType() {
		return vedioType;
	}

	public void setVedioType(String vedioType) {
		this.vedioType = vedioType;
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

	@Override
	public String toString() {
		return "ChatVedioEbo [id=" + id + ", msgId=" + msgId + ", vedioType=" + vedioType + ", vedioLenTime="
				+ vedioLenTime + ", vedioUrl=" + vedioUrl + "]";
	}

}
