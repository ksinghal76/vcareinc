package com.vcareinc.exceptions;

public class ValidationException extends CommonException {

	private static final long serialVersionUID = 1L;

	public ValidationException(String msg) {
		super(msg);
	}

	public ValidationException(Throwable e) {
		super(e);
	}

	public ValidationException(String msg, Throwable e) {
		super(msg, e);
	}

}
