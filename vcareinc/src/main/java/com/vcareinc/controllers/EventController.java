package com.vcareinc.controllers;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.vcareinc.services.EventService;
import com.vcareinc.services.UserService;
import com.vcareinc.vo.Events;
import com.vcareinc.vo.User;

@Controller
public class EventController extends BaseMultiActionController {

	@Autowired
	private UserService userService;

	@Autowired
	private EventService eventService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public EventService getEventService() {
		return eventService;
	}

	public void setEventService(EventService eventService) {
		this.eventService = eventService;
	}

	@RequestMapping("/eventManage")
	public String eventManage(ModelMap modelMap, HttpServletRequest request) {
		User user = userService.getUserProfile(request);
		List<Events> eventsList = eventService.getEventsByUser(user);
		modelMap.addAttribute("eventsList", eventsList);
		return "eventManage";
	}
}
