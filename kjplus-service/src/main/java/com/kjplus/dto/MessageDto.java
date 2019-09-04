package com.kjplus.dto;

import java.io.Serializable;

//消息流转对象
public class MessageDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8343201335930298368L;
	// 发送消息的用户
	private String frSCode;
	// 发送消息的用户昵称
	private String frName;
	// 发送消息的用户昵称
	private String frFace;
	// 接收消息的用户
	private String contSCode;
	// 聊天类型 视频 图片 文字 文本等
	private String chatType;
	// 所有信息
	private String msgInfo;
	// 发送消息的类型 图片 可能为原图 缩略图 视频可能为 长视频 短视频
	private String msgType;
	// 消息时间
	private Integer msgTime;
	// 消息状态
	private String msgStatus;

	public MessageDto() {
		super();
	}

	public String getFrName() {
		return frName;
	}

	public void setFrName(String frName) {
		this.frName = frName;
	}

	public String getFrFace() {
		return frFace;
	}

	public void setFrFace(String frFace) {
		this.frFace = frFace;
	}

	public String getFrSCode() {
		return frSCode;
	}

	public void setFrSCode(String frSCode) {
		this.frSCode = frSCode;
	}

	public String getContSCode() {
		return contSCode;
	}

	public void setContSCode(String contSCode) {
		this.contSCode = contSCode;
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

	public Integer getMsgTime() {
		return msgTime;
	}

	public void setMsgTime(Integer msgTime) {
		this.msgTime = msgTime;
	}

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	@Override
	public String toString() {
		return "MessageDto [frSCode=" + frSCode + ", frName=" + frName + ", frFace=" + frFace + ", contSCode=" + contSCode + ", chatType=" + chatType
				+ ", msgInfo=" + msgInfo + ", msgType=" + msgType + ", msgTime=" + msgTime + ", msgStatus=" + msgStatus + "]";
	}

}
