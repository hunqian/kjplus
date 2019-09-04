package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.kjplus.constant.Constant;

//菜单对象DTO
public class FuncDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4058664261509703604L;
	// Fields
	private Integer id;
	private String code;
	private String name;
	private String menu;
	private String flag;
	//@SuppressWarnings("unused")
	private String leaf = Constant.FLAG_YES;
	private String icon;
	private List<FuncDto> subs = new ArrayList<FuncDto>();

	// Constructors

	/** default constructor */
	public FuncDto() {
	}

	/** full constructor */
	public FuncDto(Integer pid, String type, String code, String name, String menu, String flag, String icon) {
		this.code = code;
		this.name = name;
		this.menu = menu;
		this.flag = flag;
		this.icon = icon;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public boolean isLeaf() {
		return subs.size() == 0;
	}

	public List<FuncDto> getSubs() {
		return subs;
	}

	public void setSubs(List<FuncDto> subs) {
		this.subs = subs;
	}
	
	public String getLeaf() {
		if(subs.size() == 0)
			return Constant.FLAG_YES;
		else
			return Constant.FLAG_NO;
	}

	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "FuncDto [id=" + id + ", code=" + code + ", name=" + name + ", menu=" + menu + ", flag=" + flag + ", leaf=" + leaf + ", icon=" + icon + ", subs=" + subs + "]";
	}


}