package com.kjplus.model.inner;

/**
 * TAdminUser entity. @author songyuebin
 */

public class UserMapInnerEbo implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6875219689110779300L;
	//adminUserMap
	private Integer id;
	private Integer mainId;
	private String mainType;
	private Integer uid;
	private String userType;
	
	//staff
	private Integer sStaffId;
	//表中唯一编码
	private String sCode;
	//组织内部编码
	private String sStaffCode;
	private String sStaffName;
	private Integer sStaffTypeId;
	private Integer sStaffDeptId;
	private Integer sStaffOrgId;
	private String sStaffIdCard;
	//是否有效
	private String sFlag;
	//是否可服务
	private String sStatus;
	
	//adminUser
	private Integer adminUserId;
	private String adminUserName;
	private String adminUserType;
	private String adminUserNickName;
	private Integer adminUserOrgId;
	
	//user
	private Integer uUserId;
	private String uUserName;
	private String uUserNickName;
	private String uUserType;
	private String uUserStatus;
	private Integer uUserOrgId;
	
	// Constructors
	
	public UserMapInnerEbo() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public Integer getsStaffId() {
		return sStaffId;
	}

	public void setsStaffId(Integer sStaffId) {
		this.sStaffId = sStaffId;
	}

	public String getsCode() {
		return sCode;
	}

	public void setsCode(String sCode) {
		this.sCode = sCode;
	}

	public String getsStaffCode() {
		return sStaffCode;
	}

	public void setsStaffCode(String sStaffCode) {
		this.sStaffCode = sStaffCode;
	}

	public String getsStaffName() {
		return sStaffName;
	}

	public void setsStaffName(String sStaffName) {
		this.sStaffName = sStaffName;
	}

	public Integer getsStaffTypeId() {
		return sStaffTypeId;
	}

	public void setsStaffTypeId(Integer sStaffTypeId) {
		this.sStaffTypeId = sStaffTypeId;
	}

	public Integer getsStaffDeptId() {
		return sStaffDeptId;
	}

	public void setsStaffDeptId(Integer sStaffDeptId) {
		this.sStaffDeptId = sStaffDeptId;
	}

	public Integer getsStaffOrgId() {
		return sStaffOrgId;
	}

	public void setsStaffOrgId(Integer sStaffOrgId) {
		this.sStaffOrgId = sStaffOrgId;
	}

	public String getsStaffIdCard() {
		return sStaffIdCard;
	}

	public void setsStaffIdCard(String sStaffIdCard) {
		this.sStaffIdCard = sStaffIdCard;
	}

	public String getsFlag() {
		return sFlag;
	}

	public void setsFlag(String sFlag) {
		this.sFlag = sFlag;
	}

	public String getsStatus() {
		return sStatus;
	}

	public void setsStatus(String sStatus) {
		this.sStatus = sStatus;
	}

	public Integer getAdminUserId() {
		return adminUserId;
	}

	public void setAdminUserId(Integer adminUserId) {
		this.adminUserId = adminUserId;
	}

	public String getAdminUserName() {
		return adminUserName;
	}

	public void setAdminUserName(String adminUserName) {
		this.adminUserName = adminUserName;
	}

	public String getAdminUserType() {
		return adminUserType;
	}

	public void setAdminUserType(String adminUserType) {
		this.adminUserType = adminUserType;
	}

	public String getAdminUserNickName() {
		return adminUserNickName;
	}

	public void setAdminUserNickName(String adminUserNickName) {
		this.adminUserNickName = adminUserNickName;
	}

	public Integer getAdminUserOrgId() {
		return adminUserOrgId;
	}

	public void setAdminUserOrgId(Integer adminUserOrgId) {
		this.adminUserOrgId = adminUserOrgId;
	}

	public Integer getuUserId() {
		return uUserId;
	}

	public void setuUserId(Integer uUserId) {
		this.uUserId = uUserId;
	}

	public String getuUserName() {
		return uUserName;
	}

	public void setuUserName(String uUserName) {
		this.uUserName = uUserName;
	}

	public String getuUserNickName() {
		return uUserNickName;
	}

	public void setuUserNickName(String uUserNickName) {
		this.uUserNickName = uUserNickName;
	}

	public String getuUserType() {
		return uUserType;
	}

	public void setuUserType(String uUserType) {
		this.uUserType = uUserType;
	}

	public String getuUserStatus() {
		return uUserStatus;
	}

	public void setuUserStatus(String uUserStatus) {
		this.uUserStatus = uUserStatus;
	}

	public Integer getuUserOrgId() {
		return uUserOrgId;
	}

	public void setuUserOrgId(Integer uUserOrgId) {
		this.uUserOrgId = uUserOrgId;
	}

	@Override
	public String toString() {
		return "StaffMapInnerEbo [id=" + id + ", mainId=" + mainId + ", mainType=" + mainType + ", uid=" + uid
				+ ", userType=" + userType + ", sStaffId=" + sStaffId + ", sCode=" + sCode + ", sStaffCode="
				+ sStaffCode + ", sStaffName=" + sStaffName + ", sStaffTypeId=" + sStaffTypeId + ", sStaffDeptId="
				+ sStaffDeptId + ", sStaffOrgId=" + sStaffOrgId + ", sStaffIdCard=" + sStaffIdCard + ", sFlag=" + sFlag
				+ ", sStatus=" + sStatus + ", adminUserId=" + adminUserId + ", adminUserName=" + adminUserName
				+ ", adminUserType=" + adminUserType + ", adminUserNickName=" + adminUserNickName + ", adminUserOrgId="
				+ adminUserOrgId + ", uUserId=" + uUserId + ", uUserName=" + uUserName + ", uUserNickName="
				+ uUserNickName + ", uUserType=" + uUserType + ", uUserStatus=" + uUserStatus + ", uUserOrgId="
				+ uUserOrgId + "]";
	}

}