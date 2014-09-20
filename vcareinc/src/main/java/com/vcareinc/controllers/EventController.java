package com.vcareinc.controllers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.net.ntp.TimeStamp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vcareinc.services.EventService;
import com.vcareinc.services.UserService;
import com.vcareinc.utils.DateUtils;
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

	@RequestMapping("event")
	public String event(Pageable pageable, ModelMap modelMap,
			@RequestParam(value="date") String date,
			@RequestParam(value="orderBy", required=false) String orderBy,
			@RequestParam(value="pageNumber", required=false) Integer pageNumber,
			@RequestParam(value="numberPerPage", required=false) Integer numberPerPage) {

		Timestamp timeStamp;
		try {
			timeStamp = DateUtils.getTimestamp(date, "");
			List<Events> eventsList = eventService.getEventsByDate(timeStamp);
			modelMap.addAttribute("eventsList", eventsList);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "event";
	}
}
