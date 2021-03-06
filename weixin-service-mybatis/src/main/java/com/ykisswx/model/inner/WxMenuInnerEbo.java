package com.ykisswx.model.inner;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ykisswx.dto.WxMenuDto;

/**
 * TWxMenu entity. @author MyEclipse Persistence Tools
 */

public class WxMenuInnerEbo {

	private Integer id;
	private Integer pid;
	private Integer accid;
	private String name;
	private String pname;
	private String flag;
	private String url;
	private String type;
	private Date createTime;
	private String key;
	private String note;
	private Integer orderSeq;

	// Constructors

	/** default constructor */
	public WxMenuInnerEbo() {
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

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
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
	
}