package com.mq.message;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.mq.util.DateUtil;

//命令消息对象
@XmlRootElement(name = "command")
public class MsgCommand implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3568403169409653434L;
	// 什么系统发送过来的  mobile  console  manager  api 
	private String srcSys;
	// 命令类别
	private String cmd;
	// 消息体
	private Object msgBody;
	// 扩展说明 可以空
	private String ext;
	// 系统发起时间,默认为当前时间
	private int cmdTime = DateUtil.getCurTimeInSec();

	public MsgCommand() {
		super();
	}

	public MsgCommand(String srcSys) {
		super();
		this.srcSys = srcSys;
	}

	public String getSrcSys() {
		return srcSys;
	}

	@XmlElement
	public void setSrcSys(String srcSys) {
		this.srcSys = srcSys;
	}

	public String getCmd() {
		return cmd;
	}

	@XmlElement
	public void setCmd(String cmd) {
		this.cmd = cmd;
	}

	public Object getMsgBody() {
		return msgBody;
	}

	@XmlElement
	public void setMsgBody(Object msgBody) {
		this.msgBody = msgBody;
	}

	public String getExt() {
		return ext;
	}

	@XmlElement
	public void setExt(String ext) {
		this.ext = ext;
	}

	public int getCmdTime() {
		return cmdTime;
	}

	@XmlElement
	public void setCmdTime(int cmdTime) {
		this.cmdTime = cmdTime;
	}

}
