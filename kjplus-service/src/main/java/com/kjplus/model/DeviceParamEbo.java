package com.kjplus.model;

/**
 * TAdminUser entity. @author MyEclipse Persistence Tools
 */

public class DeviceParamEbo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5554037972782341600L;
	// Fields
	private Integer id;
	private String deviceType;
	private String paramType;
	private String deviceParam;
	private String flag;
	private Integer createTime;
	
	// Constructors
	
	public DeviceParamEbo() {
		super();
	}

	// Property accessors
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDeviceType() {
		return deviceType;
	}

	public void setDeviceType(String deviceType) {
		this.deviceType = deviceType;
	}

	public String getParamType() {
		return paramType;
	}

	public void setParamType(String paramType) {
		this.paramType = paramType;
	}

	public String getDeviceParam() {
		return deviceParam;
	}

	public void setDeviceParam(String deviceParam) {
		this.deviceParam = deviceParam;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "DvcParamEbo [id=" + id + ", deviceType=" + deviceType + ", paramType=" + paramType + ", deviceParam="
				+ deviceParam + ", flag=" + flag + ", createTime=" + createTime + "]";
	}

	
}