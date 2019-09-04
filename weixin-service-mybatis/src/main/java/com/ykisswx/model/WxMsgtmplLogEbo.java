package com.ykisswx.model;

/**
 * TWxMsgtmplLog entity. @author MyEclipse Persistence Tools
 */

public class WxMsgtmplLogEbo implements java.io.Serializable {

	// Fields
	
	private static final long serialVersionUID = 8635672362530429297L;

	private Integer id;
	private Integer accId;
	private Integer toWxUserid;
	private String code;
	private String msgContent;
	private Integer sendTime;
	private String msgId;
	private String msgStatus;
	private String msgCode;

	// Constructors

	/** default constructor */
	public WxMsgtmplLogEbo() {
	}

	/** minimal constructor */
	public WxMsgtmplLogEbo(Integer accId, Integer frmWxUserid, Integer toWxUser, String code, String msgContent,
			Integer sendTime, Integer respTime, Integer msgCode) {
		this.accId = accId;
		this.toWxUserid = toWxUser;
		this.code = code;
		this.msgContent = msgContent;
		this.sendTime = sendTime;
	}

	/** full constructor */
	public WxMsgtmplLogEbo(Integer accId, Integer frmWxUserid, Integer toWxUser, String code, String msgContent,
			Integer sendTime, Integer respTime, String msgId, Integer msgCode) {
		this.accId = accId;
		this.toWxUserid = toWxUser;
		this.code = code;
		this.msgContent = msgContent;
		this.sendTime = sendTime;
		this.msgId = msgId;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccId() {
		return this.accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getToWxUserid() {
		return this.toWxUserid;
	}

	public void setToWxUserid(Integer toWxUser) {
		this.toWxUserid = toWxUser;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsgContent() {
		return this.msgContent;
	}

	public void setMsgContent(String msgContent) {
		this.msgContent = msgContent;
	}

	public Integer getSendTime() {
		return this.sendTime;
	}

	public void setSendTime(Integer sendTime) {
		this.sendTime = sendTime;
	}

	public String getMsgId() {
		return this.msgId;
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

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

}