package com.kjplus.model;

import java.io.Serializable;

import com.kjplus.annotation.Validation;

public class RongTokenEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4746959286670960624L;
	private int id;
	@Validation
	private int uid;
	private String rToken;
	private int reqTime;
	private int resTime;
	@Validation
	private String flag;
	private String isBlock;
	private String isBlack;

	public RongTokenEbo() {
		super();
	}

	public RongTokenEbo(int id, int uid, String rToken, int reqTime, int resTime, String flag, String isBlock, String isBlack) {
		super();
		this.id = id;
		this.uid = uid;
		this.rToken = rToken;
		this.reqTime = reqTime;
		this.resTime = resTime;
		this.flag = flag;
		this.isBlock = isBlock;
		this.isBlack = isBlack;
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

	@Override
	public String toString() {
		return "RongTokenEbo [id=" + id + ", uid=" + uid + ", rToken=" + rToken + ", reqTime=" + reqTime + ", resTime=" + resTime + ", flag=" + flag
				+ ", isBlock=" + isBlock + ", isBlack=" + isBlack + "]";
	}

}
