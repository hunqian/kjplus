package com.kjplus.model;

import java.util.Date;

public class FollowupExamEbo implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 7742259395655884227L;
	private Integer id;
	private String code;
	private Integer prsnid;
	private Integer stafffid;
	private Integer examtime;
	private String examtype;
	private Date examday;
	private Integer orgid;
	private Integer resid;
	private String resmemo;
	private Integer resstaffid;

	// Constructors

	/** default constructor */
	public FollowupExamEbo() {
		super();
	}

	/** full constructor */
	public FollowupExamEbo(Integer id, String code, Integer prsnid, Integer stafffid, Integer examtime, String examtype,
			Date examday, Integer orgid, Integer resid, String resmemo, Integer resstaffid) {
		super();
		this.id = id;
		this.code = code;
		this.prsnid = prsnid;
		this.stafffid = stafffid;
		this.examtime = examtime;
		this.examtype = examtype;
		this.examday = examday;
		this.orgid = orgid;
		this.resid = resid;
		this.resmemo = resmemo;
		this.resstaffid = resstaffid;
	}

	// Property accessors

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getPrsnid() {
		return prsnid;
	}

	public void setPrsnid(Integer prsnid) {
		this.prsnid = prsnid;
	}

	public Integer getStafffid() {
		return stafffid;
	}

	public void setStafffid(Integer stafffid) {
		this.stafffid = stafffid;
	}

	public Integer getExamtime() {
		return examtime;
	}

	public void setExamtime(Integer examtime) {
		this.examtime = examtime;
	}

	public String getExamtype() {
		return examtype;
	}

	public void setExamtype(String examtype) {
		this.examtype = examtype;
	}

	public Date getExamday() {
		return examday;
	}

	public void setExamday(Date examday) {
		this.examday = examday;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	public Integer getResid() {
		return resid;
	}

	public void setResid(Integer resid) {
		this.resid = resid;
	}

	public String getResmemo() {
		return resmemo;
	}

	public void setResmemo(String resmemo) {
		this.resmemo = resmemo;
	}

	public Integer getResstaffid() {
		return resstaffid;
	}

	public void setResstaffid(Integer resstaffid) {
		this.resstaffid = resstaffid;
	}

	@Override
	public String toString() {
		return "FollowupExamEbo [id=" + id + ", code=" + code + ", prsnid=" + prsnid + ", stafffid=" + stafffid
				+ ", examtime=" + examtime + ", examtype=" + examtype + ", examday=" + examday + ", orgid=" + orgid
				+ ", resid=" + resid + ", resmemo=" + resmemo + ", resstaffid=" + resstaffid + "]";
	}

}
