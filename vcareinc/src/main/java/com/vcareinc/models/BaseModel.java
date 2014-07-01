package com.vcareinc.models;

import java.io.Serializable;
import java.util.Set;

import javax.validation.ConstraintViolation;

import com.vcareinc.services.UserService;
import com.vcareinc.vo.User;

public class BaseModel<T> implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer errorCode;
	private String errorMsg;
	private Set<ConstraintViolation<BaseModel<T>>> errorConstraintViolation;
	private User user;

	private UserService userService;

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
	public Set<ConstraintViolation<BaseModel<T>>> getErrorConstraintViolation() {
		return errorConstraintViolation;
	}
	public void setErrorConstraintViolation(Set<ConstraintViolation<BaseModel<T>>> errorConstraintViolation) {
		this.errorConstraintViolation = errorConstraintViolation;
	}
	public User getUser() {
		user = userService.getUser();
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Override
	public String toString() {
		return "BaseModel [errorCode=" + errorCode + ", errorMsg=" + errorMsg
				+ ", errorConstraintViolation=" + errorConstraintViolation
				+ "]";
	}
}
