package com.ykisswx.eto;

/**
 * TWxMsgtmplLog entity. @author MyEclipse Persistence Tools
 */

public class WxMsgtmplLog implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8251151234532622228L;
	private Integer accId = 0;
	private Integer toWxUserid = 0;
	private Integer sendTime = 0;
	private String code;
	private String msgid;
	private String msgContent;
	private String msgCode;
	private String msgStatus;

	// Constructors

	/** default constructor */
	public WxMsgtmplLog() {
	}

	/** minimal constructor */
	public WxMsgtmplLog(Integer accId, Integer frmWxUserid, Integer toWxUser, String code, String msgContent,
			Integer sendTime, Integer respTime, Integer msgCode) {
		this.accId = accId;
		this.toWxUserid = toWxUser;
		this.code = code;
		this.msgContent = msgContent;
	}

	/** full constructor */
	public WxMsgtmplLog(Integer accId, Integer frmWxUserid, Integer toWxUser, String code, String msgContent,
			Integer sendTime, Integer respTime, String msgId, Integer msgCode) {
		this.accId = accId;
		this.toWxUserid = toWxUser;
		this.code = code;
		this.msgContent = msgContent;
	}

	// Property accessors
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

	public String getMsgStatus() {
		return msgStatus;
	}

	public void setMsgStatus(String msgStatus) {
		this.msgStatus = msgStatus;
	}

	public Integer getSendTime() {
		return sendTime;
	}

	public void setSendTime(Integer sendTime) {
		this.sendTime = sendTime;
	}

	public String getMsgid() {
		return msgid;
	}

	public void setMsgid(String msgid) {
		this.msgid = msgid;
	}

	public String getMsgCode() {
		return msgCode;
	}

	public void setMsgCode(String msgCode) {
		this.msgCode = msgCode;
	}

}