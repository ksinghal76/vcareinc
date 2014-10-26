package com.vcareinc.controllers;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.vcareinc.constants.NumberOfRecordPerPage;
import com.vcareinc.constants.SortingOrder;
import com.vcareinc.services.EventService;
import com.vcareinc.services.UserService;
import com.vcareinc.utils.DateUtils;
import com.vcareinc.vo.Category;
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

	@RequestMapping("eventList")
	public String eventList(Pageable pageable, ModelMap map,
			@RequestParam(value="date", required=false) String date,
			@RequestParam(value="categoryId", required=false) Long categoryId,
			@RequestParam(value="optionType", required=false) String optionType,
			@RequestParam(value="orderBy", required=false) String orderBy,
			@RequestParam(value="pageNumber", required=false) Integer pageNumber,
			@RequestParam(value="numberPerPage", required=false) Integer numberPerPage) {

		SortingOrder sortingOrder = null;

		try {
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

			Page<Events> eventList = null;

			if(date != null && date.trim().length() > 0) {
				Timestamp dateTs = DateUtils.getTimestamp(date, "yyyyMMdd");
				if(SortingOrder.LEVEL.equals(sortingOrder))
					eventList = eventService.getEventsByDateOrderByTitle(dateTs, pageNumber, numberPerPage);
			} else {
				if(SortingOrder.LEVEL.equals(sortingOrder))
					eventList = eventService.getEventsByCategoryOrderByPriceType(categoryId, pageNumber, numberPerPage);
				else if(SortingOrder.ALPHABETICALLY.equals(sortingOrder))
					eventList = eventService.getEventsByCategoryOrderByTitle(categoryId, pageNumber, numberPerPage);
			}

			int current = eventList.getNumber() + 1;
			int begin = Math.max(1, current - 5);
			int end = Math.min(begin + 10, eventList.getTotalPages());

			if(eventList.getContent() != null && eventList.getContent().size() > 0) {
				Category category = eventList.getContent().get(0).getCategory().iterator().next();
				map.addAttribute("eventVal", eventList.getContent());
				map.addAttribute("pageable", eventList);
				map.addAttribute("categoryName", category.getName());
			}

			map.addAttribute("beginIndex", begin);
			map.addAttribute("endIndex", end);
			map.addAttribute("currentIndex", current);
			map.addAttribute("numberPerPage", numberPerPage);
			map.addAttribute("optionType", optionType);
			map.addAttribute("orderBy", orderBy);
			map.addAttribute("date", date);
			map.addAttribute("categoryId", categoryId);
		} catch(ParseException e) {
			e.printStackTrace();
		}

		return "eventList";
	}

	@RequestMapping("/eventDescription")
	public String eventDescription(ModelMap map, @RequestParam("id") Long id) {
		Events events = eventService.getEventById(id);
		map.addAttribute("events", events);
		map.addAttribute("categories", eventService.getCategoriesById(id));
		return "listingDescription";
	}
}
