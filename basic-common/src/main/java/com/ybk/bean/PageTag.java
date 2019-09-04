package com.ybk.bean;

public class PageTag {
	public static final String FIRST_PAGE = "first";
	public static final String PREV_PAGE = "prev";
	public static final String CURR_PAGE = "curr";
	public static final String NEXT_PAGE = "next";
	public static final String LAST_PAGE = "last";

	public static final String LAST_CAPTION = "末页";
	public static final String PREV_CAPTION = "前一页";
	public static final String CURR_CAPTION = "当前页";
	public static final String NEXT_CAPTION = "下一页";
	public static final String FIRST_CAPTION = "首页";

	private String name;
	private String caption;
	private int page;
	private String flag = "N";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String toString() {
		return "[caption=" + caption + ", flag=" + flag + ", name=" + name
				+ ", page=" + page + "]";
	}

}