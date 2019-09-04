package com.kjplus.dto;

import java.io.Serializable;

import com.kjplus.annotation.Validation;

public class ChatContactsDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8572794017992186834L;
	private int id;
	@Validation
	private String thisSessionCode = null;// 我的this_session_code
	@Validation
	private String contSessionCode = null;// 联系人cont_session_code
	private String contNickName;// 联系人昵称
	private String contFace;// 联系人头像
	private String contSign;// 联系人个性签名
	private String contType;// 联系人类型
	private int latelyTime;// 与好友最近一次聊天时间
	private String status;// 联系人状态设置 /L临时/H好友/B不收此人发送的消息/G关注此联系人
	private String flag;// 联系人是否有效
	private Integer chatTime;// 消息创建时间

	public ChatContactsDto() {
		super();
	}

	public ChatContactsDto(int id, String thisSessionCode, String contSessionCode, String contNickName, String contFace, String contSign, String contType,
			int latelyTime, String status, String flag, Integer chatTime) {
		super();
		this.id = id;
		this.thisSessionCode = thisSessionCode;
		this.contSessionCode = contSessionCode;
		this.contNickName = contNickName;
		this.contFace = contFace;
		this.contSign = contSign;
		this.contType = contType;
		this.latelyTime = latelyTime;
		this.status = status;
		this.flag = flag;
		this.chatTime = chatTime;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getThisSessionCode() {
		return thisSessionCode;
	}

	public void setThisSessionCode(String thisSessionCode) {
		this.thisSessionCode = thisSessionCode;
	}

	public String getContSessionCode() {
		return contSessionCode;
	}

	public void setContSessionCode(String contSessionCode) {
		this.contSessionCode = contSessionCode;
	}

	public String getContNickName() {
		return contNickName;
	}

	public void setContNickName(String contNickName) {
		this.contNickName = contNickName;
	}

	public String getContFace() {
		return contFace;
	}

	public void setContFace(String contFace) {
		this.contFace = contFace;
	}

	public String getContSign() {
		return contSign;
	}

	public void setContSign(String contSign) {
		this.contSign = contSign;
	}

	public String getContType() {
		return contType;
	}

	public void setContType(String contType) {
		this.contType = contType;
	}

	public int getLatelyTime() {
		return latelyTime;
	}

	public void setLatelyTime(int latelyTime) {
		this.latelyTime = latelyTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getChatTime() {
		return chatTime;
	}

	public void setChatTime(Integer chatTime) {
		this.chatTime = chatTime;
	}

	@Override
	public String toString() {
		return "ChatContactsDto [id=" + id + ", thisSessionCode=" + thisSessionCode + ", contSessionCode=" + contSessionCode + ", contNickName="
				+ contNickName + ", contFace=" + contFace + ", contSign=" + contSign + ", contType=" + contType + ", latelyTime=" + latelyTime
				+ ", status=" + status + ", flag=" + flag + ", chatTime=" + chatTime + "]";
	}

}
