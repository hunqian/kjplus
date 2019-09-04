package com.kjplus.eto;

import com.kjplus.annotation.Validation;

public class ChatFileEto {

	@Validation
	private String msgId;
	@Validation
	private String fileType;
	@Validation
	private String fileUrl;

	public ChatFileEto() {
		super();
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

}
