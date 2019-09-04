package com.kjplus.model;

import java.io.Serializable;

public class ContactsEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6492332071252125999L;
	private String frNickName;
	private String frFace;
	private String frCode;

	private String toNickName;
	private String toFace;
	private String toCode;

	private String msgId;// 消息id
	private String msgStatus;// 消息状态
	private String msgType;// 消息类型
	private Integer msgTime;// 消息发送时间

	public ContactsEbo(String frNickName, String frFace, String frCode, String toNickName, String toFace, String toCode,
			String msgId, String msgStatus, String msgType, Integer msgTime) {
		super();
		this.frNickName = frNickName;
		this.frFace = frFace;
		this.frCode = frCode;
		this.toNickName = toNickName;
		this.toFace = toFace;
		this.toCode = toCode;
		this.msgId = msgId;
		this.msgStatus = msgStatus;
		this.msgType = msgType;
		this.msgTime = msgTime;
	}

	public ContactsEbo() {
		super();
	}

	public String getFrNickName() {
		return frNickName;
	}

	public void setFrNickName(String frNickName) {
		this.frNickName = frNickName;
	}

	public String getFrFace() {
		return frFace;
	}

	public void setFrFace(String frFace) {
		this.frFace = frFace;
	}

	public String getFrCode() {
		return frCode;
	}

	public void setFrCode(String frCode) {
		this.frCode = frCode;
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

	public String getToCode() {
		return toCode;
	}

	public void setToCode(String toCode) {
		this.toCode = toCode;
	}

	public String getMsgId() {
		return msgId;
	}

	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public String getMsgType() {
		return msgType;
	}

	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	public Integer getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Integer msgTime) {
		this.msgTime = msgTime;
	}

	@Override
	public String toString() {
		return "ContactsEbo [frNickName=" + frNickName + ", frFace=" + frFace + ", frCode=" + frCode + ", toNickName="
				+ toNickName + ", toFace=" + toFace + ", toCode=" + toCode + ", msgId=" + msgId + ", msgStatus=" + msgStatus
				+ ", msgType=" + msgType + ", msgTime=" + msgTime + "]";
	}

}
