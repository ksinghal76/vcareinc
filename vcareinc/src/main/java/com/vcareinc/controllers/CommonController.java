package com.vcareinc.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.multiaction.MultiActionController;

import com.vcareinc.services.ArticleService;
import com.vcareinc.services.ClassifiedService;
import com.vcareinc.services.CommonService;
import com.vcareinc.services.DealService;
import com.vcareinc.services.EventService;
import com.vcareinc.services.ListingService;

@Controller
public class CommonController extends MultiActionController {

	@Autowired
	private ListingService listingService;

	@Autowired
	private EventService eventService;

	@Autowired
	private ArticleService articleService;

	@Autowired
	private ClassifiedService classifiedService;

	@Autowired
	private DealService dealService;

	@Autowired
	private CommonService commonService;

	@RequestMapping("/home")
	public String home(ModelMap map) {
		map.addAttribute("listingLists", listingService.getTopListingsLists(3));
		map.addAttribute("classifiedLists", classifiedService.getTopClassifiedLists(1));
		map.addAttribute("articleLists", articleService.getTopArticlesLists(1));
		map.addAttribute("dealLists", dealService.getTopDealsLists(1));
		map.addAttribute("cityLists", commonService.getAllCity());
		return "home";
	}

	@RequestMapping("/listing")
	public String listing(ModelMap map) {
		map.addAttribute("listingLists", listingService.getTopListingsLists(5));
		map.addAttribute("dealLists", dealService.getTopDealsLists(4));
		return "listing";
	}

	public ListingService getListingService() {
		return listingService;
	}

	public void setListingService(ListingService listingService) {
		this.listingService = listingService;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	public ArticleService getArticleService() {
		return articleService;
	}

	public void setArticleService(ArticleService articleService) {
		this.articleService = articleService;
	}

	public ClassifiedService getClassifiedService() {
		return classifiedService;
	}

	public void setClassifiedService(ClassifiedService classifiedService) {
		this.classifiedService = classifiedService;
	}

	public DealService getDealService() {
		return dealService;
	}

	public void setDealService(DealService dealService) {
		this.dealService = dealService;
	}

	/**
	 * @return the commonService
	 */
	public CommonService getCommonService() {
		return commonService;
	}

	/**
	 * @param commonService the commonService to set
	 */
	public void setCommonService(CommonService commonService) {
		this.commonService = commonService;
	}
}
