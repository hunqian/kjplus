package com.ybk.sms.ipyy.resp;

import javax.xml.bind.annotation.XmlRootElement;

//调用返回结果
@XmlRootElement(name="returnsms")
public class SendResult {
	private String returnstatus;
	private String message;
	private String taskID;
	private String successCounts;
	private Double remainpoint;

	public SendResult() {
	}

	public SendResult(String returnstatus, String message, String taskID, String successCounts, Double remainpoint) {
		super();
		this.returnstatus = returnstatus;
		this.message = message;
		this.taskID = taskID;
		this.successCounts = successCounts;
		this.remainpoint = remainpoint;
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

	public Double getRemainpoint() {
		return remainpoint;
	}

	public void setRemainpoint(Double remainpoint) {
		this.remainpoint = remainpoint;
	}
	
}
