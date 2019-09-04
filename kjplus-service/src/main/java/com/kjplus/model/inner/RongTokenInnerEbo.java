package com.kjplus.model.inner;

import java.io.Serializable;

public class RongTokenInnerEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8914853902226484112L;
	private int id;
	private int uid;
	private String rToken;
	private int reqTime;
	private int resTime;
	private String flag;
	private String isBlock;
	private String isBlack;
	private String nickName;
	private String face;

	public RongTokenInnerEbo() {
		super();
	}

	public RongTokenInnerEbo(int id, int uid, String rToken, int reqTime, int resTime, String flag, String isBlock, String isBlack, String nickName,
			String face) {
		super();
		this.id = id;
		this.uid = uid;
		this.rToken = rToken;
		this.reqTime = reqTime;
		this.resTime = resTime;
		this.flag = flag;
		this.isBlock = isBlock;
		this.isBlack = isBlack;
		this.nickName = nickName;
		this.face = face;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getrToken() {
		return rToken;
	}

	public void setrToken(String rToken) {
		this.rToken = rToken;
	}

	public int getReqTime() {
		return reqTime;
	}

	public void setReqTime(int reqTime) {
		this.reqTime = reqTime;
	}

	public int getResTime() {
		return resTime;
	}

	public void setResTime(int resTime) {
		this.resTime = resTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getIsBlock() {
		return isBlock;
	}

	public void setIsBlock(String isBlock) {
		this.isBlock = isBlock;
	}

	public String getIsBlack() {
		return isBlack;
	}

	public void setIsBlack(String isBlack) {
		this.isBlack = isBlack;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	@Override
	public String toString() {
		return "RongTokenInnerEbo [id=" + id + ", uid=" + uid + ", rToken=" + rToken + ", reqTime=" + reqTime + ", resTime=" + resTime + ", flag=" + flag
				+ ", isBlock=" + isBlock + ", isBlack=" + isBlack + ", nickName=" + nickName + ", face=" + face + "]";
	}

}
