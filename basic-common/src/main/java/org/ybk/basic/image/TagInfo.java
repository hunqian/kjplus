package org.ybk.basic.image;

public class TagInfo {

	private String tag = null;
	private String value = null;
	public TagInfo(String tag, String value) {
		super();
		this.tag = tag;
		this.value = value;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	
	public String toString(){
		return "tag["+tag+"]=" + value;
	}
}
