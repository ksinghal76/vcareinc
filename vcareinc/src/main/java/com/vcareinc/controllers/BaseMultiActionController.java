package com.vcareinc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vcareinc.services.UserService;

public class BaseMultiActionController extends MultiActionController {

	@Autowired
	protected UserService userService;


	protected UserService getUserService() {
		return userService;
	}

	protected void setUserService(UserService userService) {
		this.userService = userService;
	}
}
