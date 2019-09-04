package com.kjplus.model;

public class FollowupRecEbo implements java.io.Serializable {

	// fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 5989439801211166351L;
	private Integer id;
	private Integer mainid;
	private String maintype;
	private Integer begintime;
	private Integer endtime;
	private double longpos;
	private double latipos;
	private Integer orgid;

	// Constructors

	/** default constructor */
	public FollowupRecEbo() {
		super();
	}

	/** full constructor */
	public FollowupRecEbo(Integer id, Integer mainid, String maintype, Integer begintime, Integer endtime,
			double longpos, double latipos, Integer orgid) {
		super();
		this.id = id;
		this.mainid = mainid;
		this.maintype = maintype;
		this.begintime = begintime;
		this.endtime = endtime;
		this.longpos = longpos;
		this.latipos = latipos;
		this.orgid = orgid;
	}

	// Property accessors

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMainid() {
		return mainid;
	}

	public void setMainid(Integer mainid) {
		this.mainid = mainid;
	}

	public String getMaintype() {
		return maintype;
	}

	public void setMaintype(String maintype) {
		this.maintype = maintype;
	}

	public Integer getBegintime() {
		return begintime;
	}

	public void setBegintime(Integer begintime) {
		this.begintime = begintime;
	}

	public Integer getEndtime() {
		return endtime;
	}

	public void setEndtime(Integer endtime) {
		this.endtime = endtime;
	}

	public double getLongpos() {
		return longpos;
	}

	public void setLongpos(double longpos) {
		this.longpos = longpos;
	}

	public double getLatipos() {
		return latipos;
	}

	public void setLatipos(double latipos) {
		this.latipos = latipos;
	}

	public Integer getOrgid() {
		return orgid;
	}

	public void setOrgid(Integer orgid) {
		this.orgid = orgid;
	}

	@Override
	public String toString() {
		return "FollowupRecEbo [id=" + id + ", mainid=" + mainid + ", maintype=" + maintype + ", begintime=" + begintime
				+ ", endtime=" + endtime + ", longpos=" + longpos + ", latipos=" + latipos + ", orgid=" + orgid + "]";
	}

}
