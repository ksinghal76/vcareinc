package com.vcareinc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vcareinc.constants.NumberOfRecordPerPage;
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
	public String listingList(Pageable pageable, ModelMap map,
			@RequestParam("categoryId") Long categoryId,
			@RequestParam("optionType") String optionType,
			@RequestParam(value="orderBy", required=false) String orderBy,
			@RequestParam(value="pageNumber", required=false) Integer pageNumber,
			@RequestParam(value="numberPerPage", required=false) Integer numberPerPage) {
		SortingOrder sortingOrder = null;

		if(pageNumber == null || pageNumber == 0)
			pageNumber = 1;
		if(numberPerPage == null || numberPerPage == 0)
			numberPerPage = NumberOfRecordPerPage.TEN.getNumber();

		if(orderBy == null || orderBy.trim().length() <= 0) {
			orderBy = SortingOrder.LEVEL.toString();
		}

		if(orderBy != null && orderBy.trim().length() > 0) {
			sortingOrder = SortingOrder.valueOf(orderBy);
		}

		Page<Listings> listings = null;

		if(SortingOrder.LEVEL.equals(sortingOrder))
			listings = listingService.getListingsByCategoryOrderbyPriceType(categoryId, pageNumber, numberPerPage);
		else if(SortingOrder.ALPHABETICALLY.equals(sortingOrder))
			listings = listingService.getListingsByCategoryOrderByTitle(categoryId, pageNumber, numberPerPage);

		int current = listings.getNumber() + 1;
		int begin = Math.max(1, current - 5);
		int end = Math.min(begin + 10, listings.getTotalPages());

		Category category = listings.getContent().get(0).getCategory().iterator().next();
		map.addAttribute("listVal", listings.getContent());
		map.addAttribute("pageable", listings);
		map.addAttribute("beginIndex", begin);
		map.addAttribute("endIndex", end);
		map.addAttribute("currentIndex", current);
		map.addAttribute("numberPerPage", numberPerPage);
		map.addAttribute("optionType", optionType);
		map.addAttribute("orderBy", orderBy);
		map.addAttribute("categoryId", categoryId);
		map.addAttribute("categoryName", category.getName());
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
