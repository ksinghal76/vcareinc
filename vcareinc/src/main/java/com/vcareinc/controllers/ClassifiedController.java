package com.vcareinc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vcareinc.services.ClassifiedService;
import com.vcareinc.services.UserService;
import com.vcareinc.vo.Classified;
import com.vcareinc.vo.User;

@Controller
public class ClassifiedController extends BaseMultiActionController {

	@Autowired
	private UserService userService;

	@Autowired
	private ClassifiedService classifiedService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ClassifiedService getClassifiedService() {
		return classifiedService;
	}

	public void setClassifiedService(ClassifiedService classifiedService) {
		this.classifiedService = classifiedService;
	}

	@RequestMapping("/classifiedManage")
	public String classifiedManage(ModelMap modelMap) {
		User user = userService.getUser();
		List<Classified> classifiedList = classifiedService.getClassifiedByUser(user);
		modelMap.addAttribute("classifiedList", classifiedList);
		return "classifiedManage";
	}

}
