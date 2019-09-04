package com.kjplus.model;


/**
 * TAdminUser entity. @author MyEclipse Persistence Tools
 */

public class ActivityEnrolEbo implements java.io.Serializable {

	/**
	 * 
	 */
	// Fields
	private static final long serialVersionUID = -8326312274939637701L;
	private Integer id;
	private Integer uid;
	private String userType;
	private Integer mainId;
	private String mainType;
	private String status;
	private Integer enrolTime;
	
	//Constructors
	
	public ActivityEnrolEbo() {
		super();
	}
	
	public ActivityEnrolEbo(Integer id, Integer uid, String userType, Integer mainId, String mainType, String status,
			Integer enrolTime) {
		super();
		this.id = id;
		this.uid = uid;
		this.userType = userType;
		this.mainId = mainId;
		this.mainType = mainType;
		this.status = status;
		this.enrolTime = enrolTime;
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

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getEnrolTime() {
		return enrolTime;
	}

	public void setEnrolTime(Integer enrolTime) {
		this.enrolTime = enrolTime;
	}

	@Override
	public String toString() {
		return "ActivityEnrolEbo [id=" + id + ", uid=" + uid + ", userType=" + userType + ", mainId=" + mainId
				+ ", mainType=" + mainType + ", status=" + status + ", enrolTime=" + enrolTime + "]";
	}
	
}