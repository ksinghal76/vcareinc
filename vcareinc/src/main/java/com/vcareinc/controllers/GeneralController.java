package com.vcareinc.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;

import com.vcareinc.services.SimpleMailService;

public class GeneralController extends BaseMultiActionController {
	
	@Autowired
	SimpleMailService simpleMailService;

	public ModelAndView saveContactUs(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		ModelAndView mv = null;
		
		Map<String, Object> model = new HashMap<String, Object>();
		model.put("name", "Kshitij Singhal");
		model.put("activationKey", "!@#@!#@!#!@#@2312312313");
		model.put("email", "ksinghal_98@yahoo.com");
		model.put("password", "abc123");
		
		simpleMailService.setFilename("ActivateUser.vm");
		simpleMailService.setTo(new String[] {"ksinghal_98@yahoo.com"});
		simpleMailService.setModel(model);
		simpleMailService.sendMessage();
		
		return mv;
	}
}
