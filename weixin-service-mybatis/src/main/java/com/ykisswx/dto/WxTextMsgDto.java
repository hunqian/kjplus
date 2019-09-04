package com.ykisswx.dto;

import java.io.Serializable;

public class WxTextMsgDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4797128537937224510L;
	private int id;
	private int userId;
	private String nickName;
	private int accId;
	private String account;
	private String textMsg;
	private Integer createTime;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(int accId) {
		this.accId = accId;
	}

	public String getTextMsg() {
		return textMsg;
	}

	public void setTextMsg(String textMsg) {
		this.textMsg = textMsg;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	@Override
	public String toString() {
		return "WxTextMsgDto [id=" + id + ", userId=" + userId + ", nickName=" + nickName + ", accId=" + accId
				+ ", account=" + account + ", textMsg=" + textMsg + ", createTime=" + createTime + "]";
	}

}
