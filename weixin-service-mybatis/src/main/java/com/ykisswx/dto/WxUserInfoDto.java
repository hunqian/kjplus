package com.ykisswx.dto;

import java.util.ArrayList;
import java.util.List;

public class WxUserInfoDto {
	private Integer id;
	private Integer refUserId;
	private String openid;
	private String nickname;
	private String mobile;
	private String email;
	private Integer age;
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
	private List<WxUserInfoDto> subs = new ArrayList<WxUserInfoDto>();
	/** default constructor */
	public WxUserInfoDto() {
	}

	/** minimal constructor */
	public WxUserInfoDto(String openid, Integer createTime, Integer accId) {
		this.openid = openid;
		this.createTime = createTime;
		this.accId = accId;
	}

	/** full constructor 
	 * @param subtime */
	public WxUserInfoDto(Integer id,Integer refUserId,String openid, String nickname,
			String mobile, String email, Integer age, String sex,
			String address, String area, String city, String province,
			String country, String language, String photo) {
		this.id=id;
		this.refUserId=refUserId;
		this.openid = openid;
		this.nickname = nickname;
		this.mobile = mobile;
		this.email = email;
		this.age = age;
		this.sex = sex;
		this.address = address;
		this.area = area;
		this.city = city;
		this.province = province;
		this.country = country;
		this.language = language;
		this.photo = photo;
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

	public List<WxUserInfoDto> getSubs() {
		return subs;
	}

	public void setSubs(List<WxUserInfoDto> subs) {
		this.subs = subs;
	}
	
}
