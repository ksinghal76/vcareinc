package com.vcareinc.controllers;

import java.util.List;

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

//	@RequestMapping(value="/activateUser", method=RequestMethod.GET)
//	public void activateUser(@RequestParam String activationKey) {
//		SecureUtils secureUtils = new SecureUtils();
////		User user = (User) secureUtils.decryptObject(base64Data, base64IV)
//	}

	@RequestMapping(value="/memberUser")
	public String memberUser(ModelMap modelMap) {
		User user = userService.getUser();
		List<Listings> listingsList = listingService.getListingByUser(user);
		List<Events> eventList = eventService.getEventsByUser(user);
		List<Articles> articlesList = articleService.getArticlesByUser(user);
		List<Classified> classifiedList = classifiedService.getClassifiedByUser(user);
		List<Deals> dealsList = dealService.getDealsByUser(user);

		modelMap.addAttribute("listingList", listingsList);
		modelMap.addAttribute("eventList", eventList);
		modelMap.addAttribute("articleList", articlesList);
		modelMap.addAttribute("classifiedList", classifiedList);
		modelMap.addAttribute("dealsList", dealsList);
		modelMap.addAttribute("userService", userService);
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
