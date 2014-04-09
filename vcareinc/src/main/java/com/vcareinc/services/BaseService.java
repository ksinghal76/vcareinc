package com.vcareinc.services;

import com.vcareinc.models.BaseModel;

public class BaseService {
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	protected void clearError(BaseModel model) {
		model.setErrorCode(null);
		model.setErrorMsg(null);
		model.setErrorConstraintViolation(null);
	}
}
