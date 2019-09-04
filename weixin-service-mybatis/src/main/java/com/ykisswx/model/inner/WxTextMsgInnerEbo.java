package com.ykisswx.model.inner;

/**
 * TWxGroup entity. @author MyEclipse Persistence Tools
 */

public class WxTextMsgInnerEbo {

	// 消息文本
	private int id;
	private String textMsg;
	private int createTime;
	// 微信账户
	private int accid;
	private String account;
	// 用户信息
	private int userid;
	private String nickname;		
	
	public WxTextMsgInnerEbo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTextMsg() {
		return textMsg;
	}

	public void setTextMsg(String textMsg) {
		this.textMsg = textMsg;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int msgCreateTime) {
		this.createTime = msgCreateTime;
	}

	public int getAccid() {
		return accid;
	}

	public void setAccid(int accid) {
		this.accid = accid;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

}