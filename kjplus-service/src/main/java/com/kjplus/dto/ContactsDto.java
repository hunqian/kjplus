package com.kjplus.dto;

import java.io.Serializable;

//聊天  联系人对象
public class ContactsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4860262036020387142L;
	private String contFace;// 联系人头像
	private String contSCode;// 联系人sessionCode
	private String contNName;// 联系人昵称
	private String chatType;// 聊天类型
	private String msgInfo;// 消息信息
	private String msgType;// 消息类型
	private String msgTime;// 消息时间
	private String msgStatus;// 消息状态
	private Integer UnreadNum = 0;// 未读消息数

	public ContactsDto() {
		super();
	}

	public ContactsDto(String contFace, String contSCode, String contNName, String chatType, String msgInfo, String msgType,
			String msgTime, String msgStatus, Integer unreadNum) {
		super();
		this.contFace = contFace;
		this.contSCode = contSCode;
		this.contNName = contNName;
		this.chatType = chatType;
		this.msgInfo = msgInfo;
		this.msgType = msgType;
		this.msgTime = msgTime;
		this.msgStatus = msgStatus;
		UnreadNum = unreadNum;
	}

	public String getContFace() {
		return contFace;
	}

	public void setContFace(String contFace) {
		this.contFace = contFace;
	}

	public String getContSCode() {
		return contSCode;
	}

	public void setContSCode(String contSCode) {
		this.contSCode = contSCode;
	}

	public String getContNName() {
		return contNName;
	}

	public void setContNName(String contNName) {
		this.contNName = contNName;
	}

	public String getChatType() {
		return chatType;
	}

	public void setChatType(String chatType) {
		this.chatType = chatType;
	}

	public String getMsgInfo() {
		return msgInfo;
	}

	public void setMsgInfo(String msgInfo) {
		this.msgInfo = msgInfo;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public String getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(String msgTime) {
		this.msgTime = msgTime;
	}

	public Integer getUnreadNum() {
		return UnreadNum;
	}

	public void setUnreadNum(Integer unreadNum) {
		UnreadNum = unreadNum;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	@Override
	public String toString() {
		return "ContactsDto [contFace=" + contFace + ", contSCode=" + contSCode + ", contNName=" + contNName + ", chatType="
				+ chatType + ", msgInfo=" + msgInfo + ", msgType=" + msgType + ", msgTime=" + msgTime + ", msgStatus="
				+ msgStatus + ", UnreadNum=" + UnreadNum + "]";
	}

}
