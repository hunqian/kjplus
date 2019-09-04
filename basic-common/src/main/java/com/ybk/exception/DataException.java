package com.ybk.exception;

public class DataException extends Exception {
	
	private String code = null;

	/**
	 * 
	 */
	private static final long serialVersionUID = 3962835779303738524L;
	
	public DataException(String msg){
		super(msg);
	}
	
	public DataException(String code,String msg){
		super(msg);
		this.code = code;
	}

}
