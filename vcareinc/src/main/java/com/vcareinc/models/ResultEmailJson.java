package com.vcareinc.models;

import java.io.Serializable;
import java.util.Arrays;

public class ResultEmailJson extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String message;
	private String[] errors;
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String[] getErrors() {
		return errors;
	}
	public void setErrors(String[] errors) {
		this.errors = errors;
	}
	@Override
	public String toString() {
		return "ResultEmailJson [message=" + message + ", errors="
				+ Arrays.toString(errors) + "]";
	}
}
