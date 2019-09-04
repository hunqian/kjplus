package com.kjplus.eto;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;
import com.mq.util.DateUtil;

public class ChatMainEto {

	@Validation
	private Integer frmId;// 消息发送方
	@Validation
	private Integer toId;// 消息待接收方
	private Integer chatTime = DateUtil.getCurTimeInSec();// 定义创建时间为发送时间
	@Validation
	private String chatType;
	private String status = Constant.FLAG_SET;// 默认状态为消息发送状态

	public ChatMainEto() {
		super();
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

}
