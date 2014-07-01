package com.vcareinc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vcareinc.services.ListingService;
import com.vcareinc.services.UserService;
import com.vcareinc.vo.Listings;
import com.vcareinc.vo.User;

@Controller
public class ListingController extends MultiActionController {

	@Autowired
	private UserService userService;

	@Autowired
	private ListingService listingService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ListingService getListingService() {
		return listingService;
	}

	public void setListingService(ListingService listingService) {
		this.listingService = listingService;
	}

	@RequestMapping("/listingManage")
	public String listingManage(ModelMap modelMap, HttpServletRequest request) {
		User user = userService.getUserProfile(request);
		List<Listings> listingList = listingService.getListingByUser(user);
		modelMap.addAttribute("listingList", listingList);
		return "listingManage";
	}
}
