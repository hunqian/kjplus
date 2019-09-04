package com.kjplus.model;

import java.io.Serializable;

public class SessionSocketEbo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8931182009601156829L;

	private Integer id;
	private Integer uid;
	private Integer orgId;
	private String sessionCode;
	private String status;
	private Integer seq;
	private String face;
	
	public SessionSocketEbo() {
		super();
	}

	public SessionSocketEbo(Integer id, Integer uid, Integer orgId, String sessionCode, String status, Integer seq,
			String face) {
		super();
		this.id = id;
		this.uid = uid;
		this.orgId = orgId;
		this.sessionCode = sessionCode;
		this.status = status;
		this.seq = seq;
		this.face = face;
	}



	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	public String getSessionCode() {
		return sessionCode;
	}

	public void setSessionCode(String sessionCode) {
		this.sessionCode = sessionCode;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public String getFace() {
		return face;
	}

	public void setFace(String face) {
		this.face = face;
	}

	@Override
	public String toString() {
		return "SessionSocketEbo [id=" + id + ", uid=" + uid + ", orgId=" + orgId + ", sessionCode=" + sessionCode
				+ ", status=" + status + ", seq=" + seq + ", face=" + face + "]";
	}

}
