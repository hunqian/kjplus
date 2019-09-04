package com.kjplus.dto;

import java.util.ArrayList;
import java.util.List;


/**
 * TSysRefValue entity. @author MyEclipse Persistence Tools
 */

public class SysRefValueDto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5985410422622557276L;
	private Integer id;
	private Integer refId;
	private String code;
	private String refVl;
	private String name;
	private String flag;

	// Constructors

	/** default constructor */
	public SysRefValueDto() {
	}

	/** full constructor */
	public SysRefValueDto(Integer refId, String code, String refVl, String name) {
		this.refId = refId;
		this.code = code;
		this.refVl = refVl;
		this.name = name;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getRefId() {
		return this.refId;
	}

	public void setRefId(Integer refId) {
		this.refId = refId;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRefVl() {
		return this.refVl;
	}

	public void setRefVl(String refVl) {
		this.refVl = refVl;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
	public List<Object> toList() {
		List<Object> datas = new ArrayList<Object>();

		datas.add(id);
		datas.add(refId);
		datas.add(code);
		datas.add(refVl);
		datas.add(name); //
		datas.add(flag); // 

		return datas;
	}

}