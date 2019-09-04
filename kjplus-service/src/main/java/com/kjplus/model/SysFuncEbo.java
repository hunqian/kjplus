package com.kjplus.model;

/**
 * TSysFunc entity. @author MyEclipse Persistence Tools
 */

public class SysFuncEbo implements java.io.Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 7242734524712385344L;
	private Integer id;
	private Integer pid;
	private Integer menuSeq;
	private String type;
	private String code;
	private String name;
	private String menu;
	private String flag;
	private String funcPath;
	private String icon;

	// Constructors

	/** default constructor */
	public SysFuncEbo() {
	}

	/** full constructor */
	public SysFuncEbo(Integer pid, String type, String code, String name, String menu, String flag, String icon) {
		this.pid = pid;
		this.type = type;
		this.code = code;
		this.name = name;
		this.menu = menu;
		this.flag = flag;
		this.icon = icon;
	}

	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPid() {
		return this.pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMenu() {
		return this.menu;
	}

	public void setMenu(String menu) {
		this.menu = menu;
	}

	public String getFlag() {
		return this.flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getFuncPath() {
		return funcPath;
	}

	public void setFuncPath(String funcPath) {
		this.funcPath = funcPath;
	}

	public Integer getMenuSeq() {
		return menuSeq;
	}

	public void setMenuSeq(Integer menuSeq) {
		this.menuSeq = menuSeq;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	@Override
	public String toString() {
		return "SysFuncEbo [id=" + id + ", pid=" + pid + ", menuSeq=" + menuSeq + ", type=" + type + ", code=" + code + ", name=" + name + ", menu=" + menu + ", flag=" + flag + ", funcPath="
				+ funcPath + ", icon=" + icon + "]";
	}

}