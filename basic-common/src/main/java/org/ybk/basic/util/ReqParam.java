package org.ybk.basic.util;

public class ReqParam {
	private String name;
	private String value;

	public ReqParam() {	
	}
	
	public ReqParam(String name, String value) {
		super();
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String toString(){
		return "[n="+name + "]v=" + value;
	}
}