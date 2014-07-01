package com.vcareinc.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.webflow.context.ExternalContext;

@Controller
public class RequestController {

	public String getRequestParameters(String name, ExternalContext context) {
		return context.getRequestParameterMap().get(name);
	}
}
