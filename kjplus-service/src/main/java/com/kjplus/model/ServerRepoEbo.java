package com.kjplus.model;

public class ServerRepoEbo implements java.io.Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -7910213069097848872L;
	private int id;
	private String code;
	private String srvName;
	private String ipAddr;
	private String flag;
	
	public ServerRepoEbo() {
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

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	@Override
	public String toString() {
		return "ServerRepoEbo [id=" + id + ", code=" + code + ", srvName=" + srvName + ", ipAddr=" + ipAddr + ", flag="
				+ flag + "]";
	}
	
}
