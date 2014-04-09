package com.vcareinc.exceptions;

public class DBException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public DBException(String msg) {
		super(msg);
	}
	
	public DBException(Throwable e) {
		super(e);
	}
	
	public DBException(String msg, Throwable e) {
		super(msg, e);
	}
}
