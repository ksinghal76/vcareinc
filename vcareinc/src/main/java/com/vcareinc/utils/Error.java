package com.vcareinc.utils;

public enum Error {
	DUPLICATE_EMAIL(10001, "Duplicate email");
	
	private Integer errorCode;
	private String errorMsg;
	Error(Integer errorCode, String errorMsg) {
		this.setErrorCode(errorCode);
		this.setErrorMsg(errorMsg);
	}
	public Integer getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(Integer errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorMsg() {
		return errorMsg;
	}
	public void setErrorMsg(String errorMsg) {
		this.errorMsg = errorMsg;
	}
}
