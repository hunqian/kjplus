package com.kjplus.eto;

import java.util.Date;

import com.mq.util.DateUtil;

/**
 * TAdminUser entity. @author MyEclipse Persistence Tools
 */

public class RichContentEto implements java.io.Serializable {

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = -2061270096906132409L;
	public static final int CODE_LEN = 6;
	private Integer id;
	private String code;
	private Integer mainId = 0;
	private String mainTypeCode;
	private String title;
	private String showPic1;
	private String showPic2;
	private String showPic3;
	private String showPic4;
	private String showPic5;
	private String content;
	private Date createTime = DateUtil.newTime();// 创建时间
	private int orgid;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}

	public String getMainTypeCode() {
		return mainTypeCode;
	}

	public void setMainTypeCode(String mainTypeCode) {
		this.mainTypeCode = mainTypeCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getShowPic1() {
		return showPic1;
	}

	public void setShowPic1(String showPic1) {
		this.showPic1 = showPic1;
	}

	public String getShowPic2() {
		return showPic2;
	}

	public void setShowPic2(String showPic2) {
		this.showPic2 = showPic2;
	}

	public String getShowPic3() {
		return showPic3;
	}

	public void setShowPic3(String showPic3) {
		this.showPic3 = showPic3;
	}

	public String getShowPic4() {
		return showPic4;
	}

	public void setShowPic4(String showPic4) {
		this.showPic4 = showPic4;
	}

	public String getShowPic5() {
		return showPic5;
	}

	public void setShowPic5(String showPic5) {
		this.showPic5 = showPic5;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getOrgid() {
		return orgid;
	}

	public void setOrgid(int orgid) {
		this.orgid = orgid;
	}
	

}