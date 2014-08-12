package com.vcareinc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.SortingOrder;
import com.vcareinc.services.CommonService;
import com.vcareinc.services.ListingService;
import com.vcareinc.services.UserService;
import com.vcareinc.vo.Category;
import com.vcareinc.vo.Listings;
import com.vcareinc.vo.User;

@Controller
public class ListingController extends MultiActionController {

	@Autowired
	private UserService userService;

	@Autowired
	private ListingService listingService;

	@Autowired
	private CommonService commonService;

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

	public CommonService getCommonService() {
		return commonService;
	}

	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}

	@RequestMapping("/listingManage")
	public String listingManage(ModelMap modelMap, HttpServletRequest request) {
		User user = userService.getUserProfile(request);
		List<Listings> listingList = listingService.getListingByUser(user);
		modelMap.addAttribute("listingList", listingList);
		return "listingManage";
	}

	@RequestMapping("/listingList")
	public String listingList(ModelMap map, @RequestParam("categoryId") Long categoryId,
			@RequestParam("optionType") String optionType, @RequestParam(value="orderby", required=false) String orderBy) {
		if(OptionType.valueOf(optionType).equals(OptionType.LISTING)) {
			SortingOrder sortingOrder = SortingOrder.LEVEL;
			if(orderBy != null && orderBy.trim().length() > 0) {
				sortingOrder = SortingOrder.valueOf(orderBy);
			}
			List<Listings> listVal = listingService.getListView(categoryId, sortingOrder);
			Category category = commonService.getCategoryById(categoryId);
			map.addAttribute("listVal", listVal);
			map.addAttribute("total", listVal.size());
			map.addAttribute("categoryName", category.getName());
			map.addAttribute("optionType", OptionType.LISTING.name());
		}
		return "listingList";
	}

	@RequestMapping("/listingDescription")
	public String listingDescription(ModelMap map, @RequestParam("id") Long id) {
		Listings listings = listingService.getListingById(id);
		map.addAttribute("listings", listings);
		map.addAttribute("categories", listingService.getCategoriesById(id));
		return "listingDescription";
	}
}
