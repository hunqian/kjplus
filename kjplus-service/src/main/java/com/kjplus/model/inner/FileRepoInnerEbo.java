package com.kjplus.model.inner;

public class FileRepoInnerEbo implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 2841748822363688058L;
	private int id;
	private String code;
	private int srvId;
	private int mainId;
	private String mainType;
	private String fileType;
	private String filePath;
	private int createTime;
	
	//服务器信息
	private String srvCode;
	private String srvName;
	private String ipAddr;

	public FileRepoInnerEbo() {
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

	

	public String getSrvCode() {
		return srvCode;
	}

	public void setSrvCode(String srvCode) {
		this.srvCode = srvCode;
	}

	public String getSrvName() {
		return srvName;
	}

	public void setSrvName(String srvName) {
		this.srvName = srvName;
	}

	public String getIpAddr() {
		return ipAddr;
	}

	public void setIpAddr(String ipAddr) {
		this.ipAddr = ipAddr;
	}

	@Override
	public String toString() {
		return "FileRepoInnerEbo [id=" + id + ", code=" + code + ", srvId=" + srvId + ", mainId=" + mainId
				+ ", mainType=" + mainType + ", fileType=" + fileType + ", filePath=" + filePath + ", createTime="
				+ createTime + ", srvCode=" + srvCode + ", srvName=" + srvName + ", ipAddr=" + ipAddr + "]";
	}

}
