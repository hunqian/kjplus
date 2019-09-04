package com.kjplus.eto;

/**
 * TSysFunc entity. @author MyEclipse Persistence Tools
 */

public class SysFuncEto {

	// Fields

	// code长度
	public final static int CODE_LEN = 4;

	private Integer pid = 0;
	private Integer menuSeq = 100;
	private String type;
	private String code;
	private String name;
	private String menu;

	// Constructors

	/** default constructor */
	public SysFuncEto() {
	}

	/** full constructor */
	public SysFuncEto(Integer pid, String type, String code, String name, String menu, String flag) {
		this.pid = pid;
		this.type = type;
		this.code = code;
		this.name = name;
		this.menu = menu;
	}

	public SysFuncEto(Integer pid, String type, String code, String name, String menu) {
		this.pid = pid;
		this.type = type;
		this.code = code;
		this.name = name;
		this.menu = menu;
	}

	public SysFuncEto(Integer pid, String type, String name, String menu) {
		this.pid = pid;
		this.type = type;
		this.name = name;
		this.menu = menu;
	}

	// Property accessors
	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenu() {
		return this.menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public Integer getMenuSeq() {
		return menuSeq;
	}

	public void setMenuSeq(Integer menuSeq) {
		this.menuSeq = menuSeq;
	}

}