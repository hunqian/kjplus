package com.kjplus.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

//主服务目录
public class MainSrvCatalogDto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7877606471707450890L;
	private Integer id;
	private Integer pid;
	private String srvType;
	private String srvCode;
	private String srvName;
	private String srvTypeCode;
	private String srvTypeName;
	private String srvMemo;
	private String flag;
	private Date createTime;
	private String orgCode;
	private String orgName;
	
	//配置
	private Integer MainId;
	private String MainType;
	private Double acceptorPoint;
	private Double providerPoint;

	//private List<SonSrvCatalogDto> sons = new ArrayList<SonSrvCatalogDto>();
	private List<MainSrvCatalogDto> subs = new ArrayList<MainSrvCatalogDto>();
	
	public MainSrvCatalogDto() {
		super();
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getSrvType() {
		return srvType;
	}

	public void setSrvType(String srvType) {
		this.srvType = srvType;
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

	public String getSrvTypeCode() {
		return srvTypeCode;
	}

	public void setSrvTypeCode(String srvTypeCode) {
		this.srvTypeCode = srvTypeCode;
	}

	public String getSrvTypeName() {
		return srvTypeName;
	}

	public void setSrvTypeName(String srvTypeName) {
		this.srvTypeName = srvTypeName;
	}

	public String getSrvMemo() {
		return srvMemo;
	}

	public void setSrvMemo(String srvMemo) {
		this.srvMemo = srvMemo;
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

	public String getOrgCode() {
		return orgCode;
	}

	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}


	public Integer getMainId() {
		return MainId;
	}

	public void setMainId(Integer mainId) {
		MainId = mainId;
	}

	public String getMainType() {
		return MainType;
	}

	public void setMainType(String mainType) {
		MainType = mainType;
	}

	public Double getAcceptorPoint() {
		return acceptorPoint;
	}

	public void setAcceptorPoint(Double acceptorPoint) {
		this.acceptorPoint = acceptorPoint;
	}

	public Double getProviderPoint() {
		return providerPoint;
	}

	public void setProviderPoint(Double providerPoint) {
		this.providerPoint = providerPoint;
	}

	public List<MainSrvCatalogDto> getSubs() {
		return subs;
	}

	public void setSubs(List<MainSrvCatalogDto> subs) {
		this.subs = subs;
	}

	@Override
	public String toString() {
		return "MainSrvCatalogDto [id=" + id + ", pid=" + pid + ", srvType=" + srvType + ", srvCode=" + srvCode
				+ ", srvName=" + srvName + ", srvTypeCode=" + srvTypeCode + ", srvTypeName=" + srvTypeName
				+ ", srvMemo=" + srvMemo + ", flag=" + flag + ", createTime=" + createTime + ", orgCode=" + orgCode
				+ ", orgName=" + orgName + ", MainId=" + MainId + ", MainType=" + MainType + ", acceptorPoint="
				+ acceptorPoint + ", providerPoint=" + providerPoint + ", subs=" + subs + "]";
	}

}
