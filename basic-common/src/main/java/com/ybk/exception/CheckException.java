package com.ybk.exception;

public class CheckException extends Exception {
	
	private String code = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3962835779303738524L;
	
	public CheckException(String msg){
		super(msg);
	}
	
	public CheckException(String code,String msg){
		super(msg);
		this.code = code;
	}

}
