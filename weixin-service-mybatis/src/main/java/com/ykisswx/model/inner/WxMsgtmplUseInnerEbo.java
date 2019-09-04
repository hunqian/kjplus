package com.ykisswx.model.inner;

public class WxMsgtmplUseInnerEbo {

	// Fields
	private Integer id;
	private Integer accId;
	private String account;
	private String tmplCode;
	private String title;
	private String content;
	private String tmplId;
	private String flag;

	public WxMsgtmplUseInnerEbo() {
		super();
	}

	public WxMsgtmplUseInnerEbo(Integer id, Integer accId, String account, String tmplCode, String title,
			String content, String tmplId, String flag) {
		super();
		this.id = id;
		this.accId = accId;
		this.account = account;
		this.tmplCode = tmplCode;
		this.title = title;
		this.content = content;
		this.tmplId = tmplId;
		this.flag = flag;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getAccId() {
		return accId;
	}

	public void setAccId(Integer accId) {
		this.accId = accId;
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public String getTmplCode() {
		return tmplCode;
	}

	public void setTmplCode(String tmplCode) {
		this.tmplCode = tmplCode;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getTmplId() {
		return tmplId;
	}

	public void setTmplId(String tmplId) {
		this.tmplId = tmplId;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}
}