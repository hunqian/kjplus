package com.kjplus.dto;

import java.io.Serializable;

public class ChatLatelyMsgDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8606749333024635251L;
	private String chatId;
	private Integer chatTime;
	private String chatType;
	private String chatStatus;
	private String sessionCode1;
	private String user1Name;
	private String user1Face;
	private String sessionCode2;
	private String user2Name;
	private String user2Face;

	public ChatLatelyMsgDto() {
		super();
	}

	public ChatLatelyMsgDto(String chatId, Integer chatTime, String chatType, String chatStatus, String sessionCode1,
			String user1Name, String user1Face, String sessionCode2, String user2Name, String user2Face) {
		super();
		this.chatId = chatId;
		this.chatTime = chatTime;
		this.chatType = chatType;
		this.chatStatus = chatStatus;
		this.sessionCode1 = sessionCode1;
		this.user1Name = user1Name;
		this.user1Face = user1Face;
		this.sessionCode2 = sessionCode2;
		this.user2Name = user2Name;
		this.user2Face = user2Face;
	}

	public String getChatId() {
		return chatId;
	}

	public void setChatId(String chatId) {
		this.chatId = chatId;
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

	public String getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(String chatStatus) {
		this.chatStatus = chatStatus;
	}

	public String getSessionCode1() {
		return sessionCode1;
	}

	public void setSessionCode1(String sessionCode1) {
		this.sessionCode1 = sessionCode1;
	}

	public String getUser1Name() {
		return user1Name;
	}

	public void setUser1Name(String user1Name) {
		this.user1Name = user1Name;
	}

	public String getUser1Face() {
		return user1Face;
	}

	public void setUser1Face(String user1Face) {
		this.user1Face = user1Face;
	}

	public String getSessionCode2() {
		return sessionCode2;
	}

	public void setSessionCode2(String sessionCode2) {
		this.sessionCode2 = sessionCode2;
	}

	public String getUser2Name() {
		return user2Name;
	}

	public void setUser2Name(String user2Name) {
		this.user2Name = user2Name;
	}

	public String getUser2Face() {
		return user2Face;
	}

	public void setUser2Face(String user2Face) {
		this.user2Face = user2Face;
	}

	@Override
	public String toString() {
		return "ChatLatelyMsgDto [chatId=" + chatId + ", chatTime=" + chatTime + ", chatType=" + chatType + ", chatStatus="
				+ chatStatus + ", sessionCode1=" + sessionCode1 + ", user1Name=" + user1Name + ", user1Face=" + user1Face
				+ ", sessionCode2=" + sessionCode2 + ", user2Name=" + user2Name + ", user2Face=" + user2Face + "]";
	}

}
