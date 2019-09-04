package com.kjplus.model;

import java.io.Serializable;

public class ChatMsgEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -633696269762204734L;
	private String id;
	// 消息发送人
	private Integer frmId;
	private String frmNickName;
	private String frmFace;

	// 消息接收人
	private Integer toId;
	private String toNickName;
	private String toFace;

	private Integer chatTime;// 消息创建时间
	private String chatType;// 消息类型
	private String status;// 消息状态
	// 各种消息内容
	private String dynUrl;
	private String fileUrl;
	private String picUrl;
	private String text;
	private String vedioUrl;

	public ChatMsgEbo() {
		super();
	}

	public ChatMsgEbo(String id, Integer frmId, String frmNickName, String frmFace, Integer toId, String toNickName, String toFace, Integer chatTime,
			String chatType, String status, String dynUrl, String fileUrl, String picUrl, String text, String vedioUrl) {
		super();
		this.id = id;
		this.frmId = frmId;
		this.frmNickName = frmNickName;
		this.frmFace = frmFace;
		this.toId = toId;
		this.toNickName = toNickName;
		this.toFace = toFace;
		this.chatTime = chatTime;
		this.chatType = chatType;
		this.status = status;
		this.dynUrl = dynUrl;
		this.fileUrl = fileUrl;
		this.picUrl = picUrl;
		this.text = text;
		this.vedioUrl = vedioUrl;
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

	public Integer getToId() {
		return toId;
	}

	public void setToId(Integer toId) {
		this.toId = toId;
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

	public String getDynUrl() {
		return dynUrl;
	}

	public void setDynUrl(String dynUrl) {
		this.dynUrl = dynUrl;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getPicUrl() {
		return picUrl;
	}

	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public String getVedioUrl() {
		return vedioUrl;
	}

	public void setVedioUrl(String vedioUrl) {
		this.vedioUrl = vedioUrl;
	}

	@Override
	public String toString() {
		return "ChatMsgEbo [id=" + id + ", frmId=" + frmId + ", frmNickName=" + frmNickName + ", frmFace=" + frmFace + ", toId=" + toId + ", toNickName="
				+ toNickName + ", toFace=" + toFace + ", chatTime=" + chatTime + ", chatType=" + chatType + ", status=" + status + ", dynUrl=" + dynUrl
				+ ", fileUrl=" + fileUrl + ", picUrl=" + picUrl + ", text=" + text + ", vedioUrl=" + vedioUrl + "]";
	}

}
