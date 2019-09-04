package com.ykisswx.dto;

import org.ybk.basic.util.Util;

public class WxUserInfoSess implements java.io.Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2567486284000068424L;
	
	public static final String WX_USRTYPE = "W";
	public static final String USER_USRTYPE = "U";
	
	//wxid
	private Integer id = 0;
	private String openid = "";
	private String nickname = "";
	private String userName = "";
	//private String mobile = "";
	private Integer accId = 0;
	private Integer orgid = 0;
	//memberid对应uid
	private Integer memberId = 0;

	public WxUserInfoSess(){}
	
	public WxUserInfoSess(Integer id, String openid, String nickname, Integer accId, Integer memberId) {
		super();
		this.id = id;
		this.openid = openid;
		this.nickname = nickname;
		this.accId = accId;
		this.memberId = memberId;
	}

	//u.id, u.openid, u.nickname, u.accId, a.orgid,u.memberId
	public WxUserInfoSess(Integer id, String openid, String nickname, Integer accId, Integer orgid, Integer memberId) {
		super();
		this.id = id;
		this.openid = openid;
		this.nickname = nickname;
		this.accId = accId;
		this.orgid = orgid;
		this.memberId = memberId;
	}
	
	public WxUserInfoSess(Integer id, String openid, String nickname, String mobile,
			Integer accId, Integer orgid, Integer memberId) {
		super();
		this.id = id;
		this.openid = openid;
		this.nickname = nickname;
		//this.mobile = mobile;
		this.accId = accId;
		this.orgid = orgid;
		this.memberId = memberId;
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
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	/*public Integer getUid() {
		return memberId;
	}*/
	
	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String username) {
		this.userName = username;
	}
	
	public int getUid(){
		if(memberId > 0)
			return memberId;
		else
			return id;
	}
	
	public String getUserType(){
		if(memberId > 0)
			return USER_USRTYPE;
		else
			return WX_USRTYPE;
	}
	
	public boolean isNullUser(){
		//return memberId == 0 && id == 0;
		return memberId == 0 && Util.isNull(nickname);
	}

	/*public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}*/

	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append("id=").append(id);
		buf.append(",openid=").append(openid);
		buf.append(",nickname=").append(nickname);
		buf.append(",userName=").append(userName);
		buf.append(",accId=").append(accId);
		buf.append(",orgid=").append(orgid);
		buf.append(",memberId=").append(memberId);
		return buf.toString();
	}
}
