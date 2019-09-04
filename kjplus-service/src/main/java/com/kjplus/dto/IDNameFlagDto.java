package com.kjplus.dto;

public class IDNameFlagDto {

	private int id;
	private String name;
	private String flag;

	public IDNameFlagDto() {
		
	}

	public IDNameFlagDto(int id, String name, String flag) {
		super();
		this.id = id;
		this.name = name;
		this.flag = flag;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

}
