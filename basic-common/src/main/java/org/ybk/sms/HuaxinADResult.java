package org.ybk.sms;

import javax.xml.bind.annotation.XmlAccessOrder;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorOrder;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

// 华信广告类短信发送结果返回
@XmlRootElement(name = "returnsms")
@XmlAccessorType(XmlAccessType.FIELD)
@XmlAccessorOrder(XmlAccessOrder.ALPHABETICAL)
public class HuaxinADResult {
	private String returnstatus;
	private String message;
	private Integer remainpoint;
	private String taskID;
	private String successCounts;

	public HuaxinADResult() {
		super();
	}

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Integer getRemainpoint() {
		return remainpoint;
	}

	public void setRemainpoint(Integer remainpoint) {
		this.remainpoint = remainpoint;
	}

	public String getTaskID() {
		return taskID;
	}

	public void setTaskID(String taskID) {
		this.taskID = taskID;
	}

	public String getSuccessCounts() {
		return successCounts;
	}

	public void setSuccessCounts(String successCounts) {
		this.successCounts = successCounts;
	}

}
