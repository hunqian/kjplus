package com.kjplus.model;

public class FileRepoEbo implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -4981124602253709605L;
	private int id;
	private String code;
	private int srvId;
	private int mainId;
	private String mainType;
	private String fileType;
	private String filePath;
	private int createTime;
	
	public FileRepoEbo() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
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

	@Override
	public String toString() {
		return "FileRepoEbo [id=" + id + ", srvId=" + srvId + ", mainId=" + mainId + ", mainType=" + mainType
				+ ", fileType=" + fileType + ", filePath=" + filePath + ", createTime=" + createTime + "]";
	}
	
}
