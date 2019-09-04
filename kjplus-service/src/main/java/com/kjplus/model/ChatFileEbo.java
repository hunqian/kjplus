package com.kjplus.model;

import java.io.Serializable;

public class ChatFileEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1404715673391431498L;
	private Integer id;
	private String msgId;
	private String fileType;
	private String fileUrl;

	public ChatFileEbo() {
		super();
	}

	public ChatFileEbo(Integer id, String msgId, String fileType, String fileUrl) {
		super();
		this.id = id;
		this.msgId = msgId;
		this.fileType = fileType;
		this.fileUrl = fileUrl;
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

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	@Override
	public String toString() {
		return "ChatFileEbo [id=" + id + ", msgId=" + msgId + ", fileType=" + fileType + ", fileUrl=" + fileUrl + "]";
	}

}
