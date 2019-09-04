package com.kjplus.eto;

import com.kjplus.constant.Constant;

public class ServerRepoEto {


	/**
	 * 
	 */
	public static final int CODE_LEN = 8;
	
	private String srvName = "";
	private String ipAddr = "0:0:0:0:0:0:0:1";
	private String flag = Constant.FLAG_YES;
	
	public ServerRepoEto() {
		super();
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
	
}
