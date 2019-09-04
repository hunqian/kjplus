package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class AppiontUserInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8439435841045577239L;
	private Integer uid;
	private String userName;
	private String userMobile;
	private List<Map<String, AppiontPersonInfoDto>> persons = new ArrayList<Map<String, AppiontPersonInfoDto>>();
	private List<AppiontPersonInfoDto> infos = new ArrayList<AppiontPersonInfoDto>();

	public AppiontUserInfoDto() {
		super();
	}

	public AppiontUserInfoDto(Integer uid, String userName, String userMobile,
			List<Map<String, AppiontPersonInfoDto>> persons) {
		super();
		this.uid = uid;
		this.userName = userName;
		this.userMobile = userMobile;
		this.persons = persons;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public List<Map<String, AppiontPersonInfoDto>> getPersons() {
		return persons;
	}

	public void setPersons(List<Map<String, AppiontPersonInfoDto>> persons) {
		this.persons = persons;
	}

	public List<AppiontPersonInfoDto> getInfos() {
		return infos;
	}

	public void setInfos(List<AppiontPersonInfoDto> infos) {
		this.infos = infos;
	}

	@Override
	public String toString() {
		return "AppiontUserInfoDto [uid=" + uid + ", userName=" + userName + ", userMobile=" + userMobile
				+ ", persons=" + persons + ", infos=" + infos + "]";
	}

}
