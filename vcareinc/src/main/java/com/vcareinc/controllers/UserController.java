package com.vcareinc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vcareinc.services.ArticleService;
import com.vcareinc.services.ClassifiedService;
import com.vcareinc.services.DealService;
import com.vcareinc.services.EventService;
import com.vcareinc.services.ListingService;
import com.vcareinc.vo.Articles;
import com.vcareinc.vo.Classified;
import com.vcareinc.vo.Deals;
import com.vcareinc.vo.Events;
import com.vcareinc.vo.Listings;
import com.vcareinc.vo.User;

@Controller
public class UserController extends BaseMultiActionController {

	protected final Logger log = Logger.getLogger(UserController.class);

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

	@RequestMapping(value="/memberUser")
	public String memberUser(ModelMap modelMap, HttpServletRequest request) {
		User user = userService.getUserProfile(request);
		List<Listings> listingsList = listingService.getListingByUser(user);
		List<Events> eventList = eventService.getEventsByUser(user);
		List<Articles> articlesList = articleService.getArticlesByUser(user);
		List<Classified> classifiedList = classifiedService.getClassifiedByUser(user);
		List<Deals> dealsList = dealService.getDealsByUser(user);

		modelMap.addAttribute("listingList", (listingsList != null && listingsList.size() > 0 ? listingsList : null));
		modelMap.addAttribute("eventList", (eventList != null && eventList.size() > 0 ? eventList : null));
		modelMap.addAttribute("articleList", (articlesList != null && articlesList.size() > 0 ? articlesList : null));
		modelMap.addAttribute("classifiedList", (classifiedList != null && classifiedList.size() > 0 ? classifiedList : null));
		modelMap.addAttribute("dealsList", (dealsList != null && dealsList.size() > 0 ? dealsList : null));
		return "members";
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
}
