package com.kjplus.dto;

import org.ybk.basic.util.Util;

public class UserSess implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2567486284000068424L;

	public static final String WX_USRTYPE = "W";
	public static final String USER_USRTYPE = "U";

	// wxid
	private Integer id = 0;
	//识别微信的openid
	private String openid = "";
	private String nickName = "";
	//private String userName = "";
	// private String mobile = "";
	private Integer orgId = 0;
	// memberid对应uid
	private Integer uid = 0;

	public UserSess() {
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenid() {
		return openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public String getNickname() {
		return nickName;
	}

	public void setNickname(String nickname) {
		this.nickName = nickname;
	}

	/*public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}*/

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public int getUid() {
		if (uid > 0)
			return uid;
		else
			return id;
	}

	public String getUserType() {
		if (uid > 0)
			return USER_USRTYPE;
		else
			return WX_USRTYPE;
	}

	public boolean isNullUser() {
		// return memberId == 0 && id == 0;
		return uid == 0 && Util.isNull(nickName);
	}

	/*
	 * public String getMobile() { return mobile; }
	 * 
	 * public void setMobile(String mobile) { this.mobile = mobile; }
	 */

	public String toString() {
		StringBuffer buf = new StringBuffer();
		buf.append("id=").append(id);
		buf.append(",openid=").append(openid);
		buf.append(",nickname=").append(nickName);
		//buf.append(",userName=").append(userName);
		buf.append(",orgid=").append(orgId);
		buf.append(",memberId=").append(uid);
		return buf.toString();
	}
}
