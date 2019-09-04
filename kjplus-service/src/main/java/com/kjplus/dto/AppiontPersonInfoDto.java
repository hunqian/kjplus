package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AppiontPersonInfoDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3997560038647147070L;
	// 居民表
	private String personCode;
	private String personName;
	private String personType;
	private String personIdCard;
	private List<AppiontInfoDto> infos = new ArrayList<AppiontInfoDto>();

	public AppiontPersonInfoDto() {
		super();
	}

	public AppiontPersonInfoDto(String personCode, String personName, String personType, String personIdCard,
			List<AppiontInfoDto> infos) {
		super();
		this.personCode = personCode;
		this.personName = personName;
		this.personType = personType;
		this.personIdCard = personIdCard;
		this.infos = infos;
	}

	public String getPersonCode() {
		return personCode;
	}

	public void setPersonCode(String personCode) {
		this.personCode = personCode;
	}

	public String getPersonName() {
		return personName;
	}

	public void setPersonName(String personName) {
		this.personName = personName;
	}

	public String getPersonType() {
		return personType;
	}

	public void setPersonType(String personType) {
		this.personType = personType;
	}

	public String getPersonIdCard() {
		return personIdCard;
	}

	public void setPersonIdCard(String personIdCard) {
		this.personIdCard = personIdCard;
	}

	public List<AppiontInfoDto> getInfos() {
		return infos;
	}

	public void setInfos(List<AppiontInfoDto> infos) {
		this.infos = infos;
	}

	@Override
	public String toString() {
		return "AppiontPersonInfoDto [personCode=" + personCode + ", personName=" + personName + ", personType="
				+ personType + ", personIdCard=" + personIdCard + ", infos=" + infos + "]";
	}

}
