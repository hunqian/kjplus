package com.ykisswx.model;

/**
 * TWxGroup entity. @author MyEclipse Persistence Tools
 */

public class WxTextMsgEbo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2889477137449280958L;
	// 消息文本
	private int id;
	private String textMsg;
	private int msgCreateTime;
	// 微信账户
	private int accid;

	// 用户信息
	private int userid;

	public WxTextMsgEbo() {
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

	public int getMsgCreateTime() {
		return msgCreateTime;
	}

	public void setMsgCreateTime(int msgCreateTime) {
		this.msgCreateTime = msgCreateTime;
	}

	public int getAccid() {
		return accid;
	}

	public void setAccid(int accid) {
		this.accid = accid;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

}