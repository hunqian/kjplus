package com.kjplus.model;

/**
 * TSysMiniappKey entity. @author MyEclipse Persistence Tools
 */

public class SysAppKeyEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -8899555482953070731L;
	private Integer id;
	private String openid;
	private Integer openSeq;
	private String opentoken;
	private Integer lastVisitTime;
	private Integer visitNum;
	private String visitIp;
	private String flag;
	private Integer visitUid;
	private Integer visitOrgid;
	private Integer createTime;
	// Constructors

	/** default constructor */
	public SysAppKeyEbo() {
	}

	/** minimal constructor */
	public SysAppKeyEbo(String openid, Integer openSeq, String opentoken, Integer createTime, Integer lastVisitTime,
						Integer visitNum) {
		this.openid = openid;
		this.openSeq = openSeq;
		this.opentoken = opentoken;
		this.createTime = createTime;
		this.lastVisitTime = lastVisitTime;
		this.visitNum = visitNum;
	}

	/** full constructor */
	public SysAppKeyEbo(String openid, Integer openSeq, String opentoken, Integer createTime, Integer lastVisitTime,
						Integer visitNum, String visitIp, Integer visitUid, Integer visitOrgid) {
		this.openid = openid;
		this.openSeq = openSeq;
		this.opentoken = opentoken;
		this.createTime = createTime;
		this.lastVisitTime = lastVisitTime;
		this.visitNum = visitNum;
		this.visitIp = visitIp;
		this.visitUid = visitUid;
		this.visitOrgid = visitOrgid;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getOpenid() {
		return this.openid;
	}

	public void setOpenid(String openid) {
		this.openid = openid;
	}

	public Integer getOpenSeq() {
		return this.openSeq;
	}

	public void setOpenSeq(Integer openSeq) {
		this.openSeq = openSeq;
	}

	public String getOpentoken() {
		return this.opentoken;
	}

	public void setOpentoken(String opentoken) {
		this.opentoken = opentoken;
	}

	public Integer getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public Integer getLastVisitTime() {
		return this.lastVisitTime;
	}

	public void setLastVisitTime(Integer lastVisitTime) {
		this.lastVisitTime = lastVisitTime;
	}

	public Integer getVisitNum() {
		return this.visitNum;
	}

	public void setVisitNum(Integer visitNum) {
		this.visitNum = visitNum;
	}

	public String getVisitIp() {
		return this.visitIp;
	}

	public void setVisitIp(String visitIp) {
		this.visitIp = visitIp;
	}

	public Integer getVisitUid() {
		return this.visitUid;
	}

	public void setVisitUid(Integer visitUid) {
		this.visitUid = visitUid;
	}

	public Integer getVisitOrgid() {
		return this.visitOrgid;
	}

	public void setVisitOrgid(Integer visitOrgid) {
		this.visitOrgid = visitOrgid;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}