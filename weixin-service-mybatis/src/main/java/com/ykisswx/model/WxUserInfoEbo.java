package com.ykisswx.model;

/**
 * TWxUserInfo entity. @author MyEclipse Persistence Tools
 */

public class WxUserInfoEbo implements java.io.Serializable {

	// Fields
	/**
	 * 
	 */
	private static final long serialVersionUID = 7793676368951229560L;
	private Integer id;
	private Integer refUserId;
	private String openid;
	private String nickname;
	private String remarkName;
	private String mobile;
	private String email;
	private Integer age;
	private Integer level;
	private String sex;
	private String address;
	private String area;
	private String city;
	private String province;
	private String country;
	private String language;
	private String photo;
	private Integer subtime;
	private Integer createTime;
	private String flag;
	private Integer accId;
	private Integer memberId;
	private String multiTag;
	private Integer orgId;

	public WxUserInfoEbo() {
		super();
	}

	public WxUserInfoEbo(Integer id, Integer refUserId, String openid, String nickname, String remarkName,
			String mobile, String email, Integer age, Integer level, String sex, String address, String area,
			String city, String province, String country, String language, String photo, Integer subtime,
			Integer createTime, String flag, Integer accId, Integer memberId, String multiTag, Integer orgId) {
		super();
		this.id = id;
		this.refUserId = refUserId;
		this.openid = openid;
		this.nickname = nickname;
		this.remarkName = remarkName;
		this.mobile = mobile;
		this.email = email;
		this.age = age;
		this.level = level;
		this.sex = sex;
		this.address = address;
		this.area = area;
		this.city = city;
		this.province = province;
		this.country = country;
		this.language = language;
		this.photo = photo;
		this.subtime = subtime;
		this.createTime = createTime;
		this.flag = flag;
		this.accId = accId;
		this.memberId = memberId;
		this.multiTag = multiTag;
		this.orgId = orgId;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRefUserId() {
		return refUserId;
	}

	public void setRefUserId(Integer refUserId) {
		this.refUserId = refUserId;
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

	public String getRemarkName() {
		return remarkName;
	}

	public void setRemarkName(String remarkName) {
		this.remarkName = remarkName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getAge() {
		return age;
	}

	public void setAge(Integer age) {
		this.age = age;
	}

	public Integer getLevel() {
		return level;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public Integer getSubtime() {
		return subtime;
	}

	public void setSubtime(Integer subtime) {
		this.subtime = subtime;
	}

	public Integer getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Integer createTime) {
		this.createTime = createTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public Integer getMemberId() {
		return memberId;
	}

	public void setMemberId(Integer memberId) {
		this.memberId = memberId;
	}

	public String getMultiTag() {
		return multiTag;
	}

	public void setMultiTag(String multiTag) {
		this.multiTag = multiTag;
	}

	public Integer getOrgId() {
		return orgId;
	}

	public void setOrgId(Integer orgId) {
		this.orgId = orgId;
	}

	@Override
	public String toString() {
		return "WxUserInfoEbo [id=" + id + ", refUserId=" + refUserId + ", openid=" + openid + ", nickname=" + nickname
				+ ", remarkName=" + remarkName + ", mobile=" + mobile + ", email=" + email + ", age=" + age
				+ ", level=" + level + ", sex=" + sex + ", address=" + address + ", area=" + area + ", city=" + city
				+ ", province=" + province + ", country=" + country + ", language=" + language + ", photo=" + photo
				+ ", subtime=" + subtime + ", createTime=" + createTime + ", flag=" + flag + ", accId=" + accId
				+ ", memberId=" + memberId + ", multiTag=" + multiTag + ", orgId=" + orgId + "]";
	}

}