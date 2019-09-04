package com.kjplus.eto;

import java.util.Date;

import com.kjplus.annotation.Validation;

public class HealthExamEto {

	public static final int MAX_CODE_LEN = 16;

	@Validation
	private int prsnId;
	private int staffId = 0;
	@Validation
	private int tbcfgId;
	private double temperature;// 体温
	private int respiratoryFrequency;// 呼吸频率
	private int pulseRate;// 脉率
	private int leftHightBlood;// 左高压
	private int leftLowBlood;// 左低压
	private int rightHightBlood;// 右高压
	private int rightLowBlood;// 右低压
	private double height;// 身高
	private double weight;// 体重
	private double theWaist;// 腰围
	private double bodyMassIndex;// 体质指数
	private String flag = "Y";
	private Date createTime = new Date();
	private String healthMemo;

	public HealthExamEto() {
		super();
	}

	public HealthExamEto(int prsnId, int staffId, int tbcfgId, double temperature, int respiratoryFrequency, int pulseRate, int leftHightBlood, int leftLowBlood, int rightHightBlood,
			int rightLowBlood, double height, double weight, double theWaist, double bodyMassIndex, String flag, Date createTime, String healthMemo) {
		super();
		this.prsnId = prsnId;
		this.staffId = staffId;
		this.tbcfgId = tbcfgId;
		this.temperature = temperature;
		this.respiratoryFrequency = respiratoryFrequency;
		this.pulseRate = pulseRate;
		this.leftHightBlood = leftHightBlood;
		this.leftLowBlood = leftLowBlood;
		this.rightHightBlood = rightHightBlood;
		this.rightLowBlood = rightLowBlood;
		this.height = height;
		this.weight = weight;
		this.theWaist = theWaist;
		this.bodyMassIndex = bodyMassIndex;
		this.flag = flag;
		this.createTime = createTime;
		this.healthMemo = healthMemo;
	}

	public int getPrsnId() {
		return prsnId;
	}

	public void setPrsnId(int prsnId) {
		this.prsnId = prsnId;
	}

	public int getStaffId() {
		return staffId;
	}

	public void setStaffId(int staffId) {
		this.staffId = staffId;
	}

	public int getTbcfgId() {
		return tbcfgId;
	}

	public void setTbcfgId(int tbcfgId) {
		this.tbcfgId = tbcfgId;
	}

	public double getTemperature() {
		return temperature;
	}

	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}

	public int getRespiratoryFrequency() {
		return respiratoryFrequency;
	}

	public void setRespiratoryFrequency(int respiratoryFrequency) {
		this.respiratoryFrequency = respiratoryFrequency;
	}

	public int getPulseRate() {
		return pulseRate;
	}

	public void setPulseRate(int pulseRate) {
		this.pulseRate = pulseRate;
	}

	public int getLeftHightBlood() {
		return leftHightBlood;
	}

	public void setLeftHightBlood(int leftHightBlood) {
		this.leftHightBlood = leftHightBlood;
	}

	public int getLeftLowBlood() {
		return leftLowBlood;
	}

	public void setLeftLowBlood(int leftLowBlood) {
		this.leftLowBlood = leftLowBlood;
	}

	public int getRightHightBlood() {
		return rightHightBlood;
	}

	public void setRightHightBlood(int rightHightBlood) {
		this.rightHightBlood = rightHightBlood;
	}

	public int getRightLowBlood() {
		return rightLowBlood;
	}

	public void setRightLowBlood(int rightLowBlood) {
		this.rightLowBlood = rightLowBlood;
	}

	public double getHeight() {
		return height;
	}

	public void setHeight(double height) {
		this.height = height;
	}

	public double getWeight() {
		return weight;
	}

	public void setWeight(double weight) {
		this.weight = weight;
	}

	public double getTheWaist() {
		return theWaist;
	}

	public void setTheWaist(double theWaist) {
		this.theWaist = theWaist;
	}

	public double getBodyMassIndex() {
		return bodyMassIndex;
	}

	public void setBodyMassIndex(double bodyMassIndex) {
		this.bodyMassIndex = bodyMassIndex;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getHealthMemo() {
		return healthMemo;
	}

	public void setHealthMemo(String healthMemo) {
		this.healthMemo = healthMemo;
	}

	@Override
	public String toString() {
		return "HealthExamEto [prsnId=" + prsnId + ", staffId=" + staffId + ", tbcfgId=" + tbcfgId + ", temperature=" + temperature + ", respiratoryFrequency=" + respiratoryFrequency + ", pulseRate="
				+ pulseRate + ", leftHightBlood=" + leftHightBlood + ", leftLowBlood=" + leftLowBlood + ", rightHightBlood=" + rightHightBlood + ", rightLowBlood=" + rightLowBlood + ", height="
				+ height + ", weight=" + weight + ", theWaist=" + theWaist + ", bodyMassIndex=" + bodyMassIndex + ", flag=" + flag + ", createTime=" + createTime + ", healthMemo=" + healthMemo + "]";
	}

}
