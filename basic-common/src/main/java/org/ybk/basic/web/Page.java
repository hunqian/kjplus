package org.ybk.basic.web;

import org.ybk.basic.util.Util;

public class Page {

	private String name;
	private int page;
	private String url;
	private boolean enabled;

	public Page(){}
	
	public Page(int page, String url, boolean enabled) {
		super();
		this.page = page; 
		this.name = page + "";
		this.url = url;
		this.enabled = enabled;
	}
	
	public Page(int page, String name, String url, boolean enabled) {
		super();
		this.page = page;
		this.name = name;
		this.url = url;
		this.enabled = enabled;
	}

	public String getName() {
		if(Util.isNull(name))
			return "";
		else
			return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String toString(){
		if(enabled)
			return name+"C|";
		else
			return name+"U|";
	}

}
