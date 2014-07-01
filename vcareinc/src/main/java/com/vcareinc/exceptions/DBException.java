package com.vcareinc.exceptions;

public class DBException extends CommonException {

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

	public static <T extends Throwable> boolean is(Throwable exception, Class<T> type) {
		for(;exception != null; exception = exception.getCause()) {
			if(type.isInstance(exception)) {
				return true;
			}
		}
		return false;
	}
}
