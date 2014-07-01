package com.vcareinc.exceptions;

public class CommonException extends Exception {
	
	private static final long serialVersionUID = 1L;

	public CommonException(String msg) {
		super(msg);
	}
	
	public CommonException(Throwable e) {
		super(e);
	}
	
	public CommonException(String msg, Throwable e) {
		super(msg, e);
	}
}
