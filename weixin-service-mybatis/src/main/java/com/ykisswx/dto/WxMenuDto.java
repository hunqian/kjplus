package com.ykisswx.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * TWxMenu entity. @author MyEclipse Persistence Tools
 */

public class WxMenuDto {

	private Integer id;
	private Integer pid;
	private Integer accid;
	private String name;
	private String pname;
	private String flag;
	private String url;
	private String type;
	private String createTime;
	private String key;
	private String note;
	private Integer orderSeq;
	private List<WxMenuDto> subs = new ArrayList<WxMenuDto>();

	// Constructors

	/** default constructor */
	public WxMenuDto() {
	}

	/** minimal constructor */
	public WxMenuDto(String name, String createTime, String key) {
		this.name = name;
		this.createTime = createTime;
		this.key = key;
	}

	/** full constructor */
	public WxMenuDto(Integer pid, Integer accid, String name, String url,
			String type, String createTime, String menuKey, String menuNote) {
		this.pid = pid;
		this.accid = accid;
		this.name = name;
		this.url = url;
		this.type = type;
		this.createTime = createTime;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getAccid() {
		return this.accid;
	}

	public void setAccid(Integer accid) {
		this.accid = accid;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getOrderSeq() {
		return orderSeq;
	}

	public void setOrderSeq(Integer orderSeq) {
		this.orderSeq = orderSeq;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public List<WxMenuDto> getSubs() {
		return subs;
	}

	public void setSubs(List<WxMenuDto> subs) {
		this.subs = subs;
	}
	
}