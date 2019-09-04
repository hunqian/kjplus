package com.kjplus.model;

import java.io.Serializable;

import com.kjplus.annotation.Validation;

public class ChatContactsEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2217203055896535736L;

	private int id;
	@Validation
	private String thisSessionCode = null;// 我的this_session_code
	@Validation
	private String contSessionCode = null;// 联系人cont_session_code
	private String contNickName;// 联系人昵称
	private String contFace;// 联系人头像
	private String contSign;// 联系人个性签名
	@Validation
	private String contType;// 联系人类型
	private int latelyTime;// 我与好友最近聊天时间
	@Validation
	private String status;// 联系人状态设置 /L临时/H好友/B不收此人发送的消息/G关注此联系人
	@Validation
	private String flag;// 联系人是否有效
	private Integer chatTime;// 消息创建时间
	private String chatMainId;// 消息ID
	private String chatMainType;// 消息类型
	private Integer UnreadNum = 0;// 未读消息的条数
	private String chatStatus;// 消息类型

	public ChatContactsEbo() {
		super();
	}

	public ChatContactsEbo(int id, String thisSessionCode, String contSessionCode, String contNickName, String contFace, String contSign, String contType,
			int latelyTime, String status, String flag, Integer chatTime, String chatMainId, String chatMainType, Integer unreadNum, String chatStatus) {
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
		this.chatMainId = chatMainId;
		this.chatMainType = chatMainType;
		UnreadNum = unreadNum;
		this.chatStatus = chatStatus;
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

	public String getChatMainId() {
		return chatMainId;
	}

	public void setChatMainId(String chatMainId) {
		this.chatMainId = chatMainId;
	}

	public String getChatMainType() {
		return chatMainType;
	}

	public void setChatMainType(String chatMainType) {
		this.chatMainType = chatMainType;
	}

	public Integer getUnreadNum() {
		return UnreadNum;
	}

	public void setUnreadNum(Integer unreadNum) {
		UnreadNum = unreadNum;
	}

	public String getChatStatus() {
		return chatStatus;
	}

	public void setChatStatus(String chatStatus) {
		this.chatStatus = chatStatus;
	}

	@Override
	public String toString() {
		return "ChatContactsEbo [id=" + id + ", thisSessionCode=" + thisSessionCode + ", contSessionCode=" + contSessionCode + ", contNickName="
				+ contNickName + ", contFace=" + contFace + ", contSign=" + contSign + ", contType=" + contType + ", latelyTime=" + latelyTime
				+ ", status=" + status + ", flag=" + flag + ", chatTime=" + chatTime + ", chatMainId=" + chatMainId + ", chatMainType=" + chatMainType
				+ ", UnreadNum=" + UnreadNum + ", chatStatus=" + chatStatus + "]";
	}

}
