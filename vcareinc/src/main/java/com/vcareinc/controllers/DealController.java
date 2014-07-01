package com.vcareinc.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vcareinc.services.DealService;
import com.vcareinc.services.UserService;
import com.vcareinc.vo.Deals;
import com.vcareinc.vo.User;

@Controller
public class DealController extends BaseMultiActionController {

	@Autowired
	private UserService userService;

	@Autowired
	private DealService dealService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public DealService getDealService() {
		return dealService;
	}

	public void setDealService(DealService dealService) {
		this.dealService = dealService;
	}

	@RequestMapping("/dealManage")
	public String dealManage(ModelMap modelMap) {
		User user = userService.getUser();
		List<Deals> dealsList = dealService.getDealsByUser(user);
		modelMap.addAttribute("dealsList", dealsList);
		return "dealManage";
	}

}
