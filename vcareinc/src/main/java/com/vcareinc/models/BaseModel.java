package com.vcareinc.models;

import java.io.Serializable;
import java.util.Set;

import javax.validation.ConstraintViolation;

public class BaseModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer errorCode;
	private String errorMsg;
	private Set<ConstraintViolation<T>> errorConstraintViolation;
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
	public Set<ConstraintViolation<T>> getErrorConstraintViolation() {
		return errorConstraintViolation;
	}
	public void setErrorConstraintViolation(Set<ConstraintViolation<T>> errorConstraintViolation) {
		this.errorConstraintViolation = errorConstraintViolation;
	}
	@Override
	public String toString() {
		return "BaseModel [errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", errorConstraintViolation=" + errorConstraintViolation
				+ "]";
	}
}
