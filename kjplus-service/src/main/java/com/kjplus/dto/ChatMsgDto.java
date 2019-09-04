package com.kjplus.dto;

import java.io.Serializable;

public class ChatMsgDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6715512781324565042L;

	private String msgId;
	// 消息发送人
	private String frmSessionCode;
	private String frmNickName;
	private String frmFace;

	// 消息接收人
	private String toSessionCode;
	private String toNickName;
	private String toFace;

	private Integer chatTime;// 消息创建时间
	private String chatType;// 消息类型
	private String status;// 消息状态
	private String msgBody;

	public ChatMsgDto() {
		super();
	}

	public ChatMsgDto(String msgId, String frmSessionCode, String frmNickName, String frmFace, String toSessionCode, String toNickName, String toFace,
			Integer chatTime, String chatType, String status, String msgBody) {
		super();
		this.msgId = msgId;
		this.frmSessionCode = frmSessionCode;
		this.frmNickName = frmNickName;
		this.frmFace = frmFace;
		this.toSessionCode = toSessionCode;
		this.toNickName = toNickName;
		this.toFace = toFace;
		this.chatTime = chatTime;
		this.chatType = chatType;
		this.status = status;
		this.msgBody = msgBody;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getFrmSessionCode() {
		return frmSessionCode;
	}

	public void setFrmSessionCode(String frmSessionCode) {
		this.frmSessionCode = frmSessionCode;
	}

	public String getToSessionCode() {
		return toSessionCode;
	}

	public void setToSessionCode(String toSessionCode) {
		this.toSessionCode = toSessionCode;
	}

	public String getFrmNickName() {
		return frmNickName;
	}

	public void setFrmNickName(String frmNickName) {
		this.frmNickName = frmNickName;
	}

	public String getFrmFace() {
		return frmFace;
	}

	public void setFrmFace(String frmFace) {
		this.frmFace = frmFace;
	}

	public String getToNickName() {
		return toNickName;
	}

	public void setToNickName(String toNickName) {
		this.toNickName = toNickName;
	}

	public String getToFace() {
		return toFace;
	}

	public void setToFace(String toFace) {
		this.toFace = toFace;
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

	public String getMsgBody() {
		return msgBody;
	}

	public void setMsgBody(String msgBody) {
		this.msgBody = msgBody;
	}

	@Override
	public String toString() {
		return "ChatMsgDto [msgId=" + msgId + ", frmSessionCode=" + frmSessionCode + ", frmNickName=" + frmNickName + ", frmFace=" + frmFace
				+ ", toSessionCode=" + toSessionCode + ", toNickName=" + toNickName + ", toFace=" + toFace + ", chatTime=" + chatTime + ", chatType="
				+ chatType + ", status=" + status + ", msgBody=" + msgBody + "]";
	}

}
