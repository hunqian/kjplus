package com.ykisswx.model;

/**
 * TWxGroup entity. @author MyEclipse Persistence Tools
 */

public class WxGroupEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 8114682635358740984L;
	private Integer id;
	private Integer accId;
	private Integer grpId;
	private String grpName;
	private Integer userCount;
	private String flag;
	private Integer createTime;

	// Constructors

	/** default constructor */
	public WxGroupEbo() {
	}

	/** full constructor */
	public WxGroupEbo(Integer accId, Integer grpId, String grpName,
			Integer userCount, String flag, Integer createTime) {
		this.accId = accId;
		this.grpId = grpId;
		this.grpName = grpName;
		this.userCount = userCount;
		this.flag = flag;
		this.createTime = createTime;
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

	public Integer getGrpId() {
		return this.grpId;
	}

	public void setGrpId(Integer grpId) {
		this.grpId = grpId;
	}

	public String getGrpName() {
		return this.grpName;
	}

	public void setGrpName(String grpName) {
		this.grpName = grpName;
	}

	public Integer getUserCount() {
		return this.userCount;
	}

	public void setUserCount(Integer userCount) {
		this.userCount = userCount;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

}