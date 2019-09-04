package com.kjplus.eto;

import com.kjplus.annotation.Validation;
import com.kjplus.constant.Constant;
import org.ybk.basic.util.DateUtil;


public class InfoReadlogEto {

	@Validation
	private Integer uid = 0;
	private Integer mainId = 0;
	// 阅读文章的编号
	private String mainCode;
	// 阅读的类型文章/视频
	@Validation
	private String mainType;
	private Integer readStartTime = DateUtil.getCurTimeInSec();
	private Integer readTime = 0;
	private String flag = Constant.FLAG_YES;

	public InfoReadlogEto() {
	}

	public InfoReadlogEto(Integer uid, String mainCode, String mainType, Integer readStartTime, Integer readTime,
			String flag) {
		this.uid = uid;
		this.mainCode = mainCode;
		this.mainType = mainType;
		this.readStartTime = readStartTime;
		this.readTime = readTime;
		this.flag = flag;
	}

	public Integer getUid() {
		return uid;
	}

	public void setUid(Integer uid) {
		this.uid = uid;
	}

	public String getMainCode() {
		return mainCode;
	}

	public void setMainCode(String mainCode) {
		this.mainCode = mainCode;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public Integer getReadStartTime() {
		return readStartTime;
	}

	public void setReadStartTime(Integer readStartTime) {
		this.readStartTime = readStartTime;
	}

	public Integer getReadTime() {
		return readTime;
	}

	public void setReadTime(Integer readTime) {
		this.readTime = readTime;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Integer getMainId() {
		return mainId;
	}

	public void setMainId(Integer mainId) {
		this.mainId = mainId;
	}	
	
}
