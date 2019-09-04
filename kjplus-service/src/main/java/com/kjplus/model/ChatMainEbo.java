package com.kjplus.model;

import java.io.Serializable;

public class ChatMainEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9011825754618236267L;
	private String id;
	private Integer frmId;
	private Integer toId;
	private Integer chatTime;
	private String chatType;
	private String status;

	public ChatMainEbo() {
		super();
	}

	public ChatMainEbo(String id, Integer frmId, Integer toId, Integer chatTime, String chatType, String status, String msgBody) {
		super();
		this.id = id;
		this.frmId = frmId;
		this.toId = toId;
		this.chatTime = chatTime;
		this.chatType = chatType;
		this.status = status;
	}


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getFrmId() {
		return frmId;
	}

	public void setFrmId(Integer frmId) {
		this.frmId = frmId;
	}

	public Integer getToId() {
		return toId;
	}

	public void setToId(Integer toId) {
		this.toId = toId;
	}

	public Integer getChatTime() {
		return chatTime;
	}

	public void setChatTime(Integer chatTime) {
		this.chatTime = chatTime;
	}

	public String getChatType() {
		return chatType;
	}

	public void setChatType(String chatType) {
		this.chatType = chatType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "ChatMainEbo [id=" + id + ", frmId=" + frmId + ", toId=" + toId + ", chatTime=" + chatTime + ", chatType=" + chatType + ", status="
				+ status + "]";
	}

}
