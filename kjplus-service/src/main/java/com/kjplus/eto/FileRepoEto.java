package com.kjplus.eto;

import org.ybk.basic.util.DateUtils;

public class FileRepoEto{

	public static final int CODE_LEN = 16;
	
	private int srvId = 0;
	private int mainId = 0;
	private String mainType = "";
	private String fileType = "";
	private String filePath = "";
	private int createTime = DateUtils.getCurTimeInSec();
	
	public FileRepoEto() {
		super();
	}

	public int getSrvId() {
		return srvId;
	}

	public void setSrvId(int srvId) {
		this.srvId = srvId;
	}

	public int getMainId() {
		return mainId;
	}

	public void setMainId(int mainId) {
		this.mainId = mainId;
	}

	public String getMainType() {
		return mainType;
	}

	public void setMainType(String mainType) {
		this.mainType = mainType;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFilePath() {
		return filePath;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public int getCreateTime() {
		return createTime;
	}

	public void setCreateTime(int createTime) {
		this.createTime = createTime;
	}
	
}
