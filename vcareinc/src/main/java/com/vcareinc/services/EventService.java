package com.vcareinc.services;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.execution.RequestContext;

import com.vcareinc.constants.DateRange;
import com.vcareinc.constants.DayOfWeek;
import com.vcareinc.constants.MonthOfYear;
import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.PriceType;
import com.vcareinc.constants.StatusType;
import com.vcareinc.constants.WeekOfMonth;
import com.vcareinc.exceptions.CommonException;
import com.vcareinc.exceptions.DBException;
import com.vcareinc.exceptions.ValidationException;
import com.vcareinc.models.EventOrder;
import com.vcareinc.utils.DateUtils;
import com.vcareinc.vo.Address;
import com.vcareinc.vo.Country;
import com.vcareinc.vo.Events;
import com.vcareinc.vo.FileUpload;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.State;
import com.vcareinc.vo.User;

@Controller
public class EventService extends BaseService<EventOrder> {

	private Logger log = Logger.getLogger(EventService.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	public OrderService getOrderService() {
		return orderService;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	/**
	 * @return the userService
	 */
	public UserService getUserService() {
		return userService;
	}

	/**
	 * @param userService the userService to set
	 */
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@SuppressWarnings("unchecked")
	@Transactional(rollbackFor=DBException.class)
	public void saveEvent(RequestContext context, EventOrder eventOrder) throws CommonException {
		log.info(eventOrder.toString());

		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = userService.getUserProfile(request);

		OptionType optionType = OptionType.valueOf((String) context.getFlowScope().get("optionType"));
		PriceType priceType = PriceType.valueOf((String) context.getFlowScope().get("priceType"));
		Price price = orderService.getPriceByType(optionType, priceType);
		Long id = null;
		if(context.getFlowScope().get("optionTypeId") != null)
			id = (Long) context.getFlowScope().get("optionTypeId");

		try {
			validate(eventOrder);
			Events events = new Events();
			if(id != null && id > 0)
				events.setId(id);
			events.setUser(user);
			events.setPrice(price);

			events.setTitle(eventOrder.getTitle());
			events.setEmail(eventOrder.getEmail());
			events.setUrl(eventOrder.getProtocol() + eventOrder.getUrl());
			events.setPhoneNumber(eventOrder.getPhoneNumber());
			events.setContactName(eventOrder.getContactName());

			Address address = new Address();
			address.setLocationName(eventOrder.getLocationName());
			address.setAddress1(eventOrder.getAddress1());
			address.setAddress2(eventOrder.getAddress2());
			address.setCity(eventOrder.getCity());
			address.setZipcode(eventOrder.getZipcode());
			address.setLatitude(eventOrder.getLatitude());
			address.setLongitude(eventOrder.getLongitude());

			if(eventOrder.getState() != null && Long.valueOf(eventOrder.getState().trim()) > 0) {
				State state = orderService.getStateById(Long.valueOf(eventOrder.getState()));
				address.setState(state);
			}

			if(eventOrder.getCountry() != null && Long.valueOf(eventOrder.getCountry().trim()) > 0) {
				Country country = orderService.getCountryById(Long.valueOf(eventOrder.getCountry()));
				address.setCountry(country);
			}

			em.persist(address);
			events.setAddress(address);

			if(eventOrder.getStartDate() != null && eventOrder.getStartDate().trim().length() > 0
					&& eventOrder.getStartHour() != null && eventOrder.getStartHour().trim().length() > 0
					&& eventOrder.getStartMinute() != null && eventOrder.getStartMinute().trim().length() > 0
					&& eventOrder.getStartAmPm() != null && eventOrder.getStartAmPm().trim().length() > 0) {
					events.setStartDate(DateUtils.getTimestamp(eventOrder.getStartDate() + " " + eventOrder.getStartHour() + ":" + eventOrder.getStartMinute() + " " + eventOrder.getStartAmPm(), "MM/dd/yyyy HH:mm a"));
			}

			if(eventOrder.getEndDate() != null && eventOrder.getEndDate().trim().length() > 0
					&& eventOrder.getEndHour() != null && eventOrder.getEndHour().trim().length() > 0
					&& eventOrder.getEndMinute() != null && eventOrder.getEndMinute().trim().length() > 0
					&& eventOrder.getEndAmPm() != null && eventOrder.getEndAmPm().trim().length() > 0) {
				events.setEndDate(DateUtils.getTimestamp(eventOrder.getEndDate() + " " + eventOrder.getEndHour() + ":" + eventOrder.getEndMinute() + " " + eventOrder.getEndAmPm(), "MM/dd/yyyy HH:mm a"));
			}
			events.setRecurring(eventOrder.getRecurring());

			if(eventOrder.getRecurring() != null && eventOrder.getRecurring()) {
				if(eventOrder.getEventPeriod().equalsIgnoreCase("until")) {
					if(eventOrder.getUntilDate() != null && eventOrder.getUntilDate().trim().length() > 0)
						events.setUntilDate(DateUtils.getTimestamp(eventOrder.getUntilDate()));
				}

				if(eventOrder.getPeriod().equals(DateRange.WEEKLY) || eventOrder.getPeriod().equals(DateRange.MONTHLY) || eventOrder.getPeriod().equals(DateRange.YEARLY)) {
					events.setDayOfWeek(StringUtils.join(eventOrder.getDayOfWeekcb(), ":"));
				}

				if(eventOrder.getPeriod().equals(DateRange.MONTHLY) || eventOrder.getPeriod().equals(DateRange.YEARLY)) {
					events.setWeekOfMonth(StringUtils.join(eventOrder.getWeekOfMonth(), ":"));
				}

				if(eventOrder.getPeriod().equals(DateRange.YEARLY)) {
					if(eventOrder.getMonth() != null)
						events.setMonth(MonthOfYear.valueOf(eventOrder.getMonth()));
					if(eventOrder.getMonth2() != null)
						events.setMonth2(MonthOfYear.valueOf(eventOrder.getMonth2()));
				}
			}

			events.setSummaryDescription(eventOrder.getSummarydesc());
			events.setDescription(eventOrder.getDescription());
			events.setKeyword(eventOrder.getKeyword());
			if(PriceType.valueOf(priceType.name()).equals(PriceType.STUDENTS))
				events.setStatus(StatusType.ACTIVE);
			else
				events.setStatus(StatusType.PENDING);

//			if(eventOrder.getPromotionCode() != null)
//				events.setPromotionCode(eventOrder.getPromotionCode());
			em.persist(events);

			if(eventOrder.getImageUpload() != null && eventOrder.getImageUpload().getSize() > 0) {
				String filename = getFileName(events.getId(), OptionType.EVENT, eventOrder.getImageUpload());
				FileUpload fileUpload = saveFileUpload(events.getId(), filename, eventOrder.getImageUpload());
				events.setImageUpload(fileUpload);
			}
			em.persist(events);

			clearObject(eventOrder);

		} catch (ValidationException | ParseException e) {
			throw new ValidationException(e);
		}
	}

	public EventOrder getEventOrderById(Long id) {
		EventOrder eventOrder = null;
		Events events = getEventById(id);
		if(events != null) {
			eventOrder = new EventOrder();
			BeanUtils.copyProperties(events, eventOrder);
		}

		eventOrder.setProtocol(events.getUrl().substring(0, events.getUrl().indexOf("//")));
		eventOrder.setUrl(events.getUrl().substring(events.getUrl().indexOf("//"), events.getUrl().length()));
		eventOrder.setSummarydesc(events.getSummaryDescription());

		if(events.getDayOfWeek() != null && events.getDayOfWeek().trim().length() > 0) {
			String[] datOfWkArr = events.getDayOfWeek().split(":");
			DayOfWeek[] dayOfWeek = new DayOfWeek[datOfWkArr.length];
			int i = 0;
			for(String datOfWk : datOfWkArr) {
				dayOfWeek[i++] = DayOfWeek.valueOf(datOfWk);
			}
			eventOrder.setDayOfWeekcb(dayOfWeek);
		}

		if(events.getWeekOfMonth() != null && events.getWeekOfMonth().trim().length() > 0) {
			String[] wkOfMthArr = events.getWeekOfMonth().split(":");
			WeekOfMonth[] weekOfMonth = new WeekOfMonth[wkOfMthArr.length];
			int i = 0;
			for(String wkOfMth : wkOfMthArr) {
				weekOfMonth[i++] = WeekOfMonth.valueOf(wkOfMth);
			}
			eventOrder.setWeekOfMonth(weekOfMonth);
		}

		if(events.getAddress() != null) {
			BeanUtils.copyProperties(events.getAddress(), eventOrder);
		}

		if(events.getImageUpload() != null) {
			eventOrder.setImageUploadFilename(events.getImageUpload().getClientFilename());
		}
		return eventOrder;
	}

	@SuppressWarnings("unchecked")
	public List<Events> getEventsByUser(User user) {
		return em.createQuery("SELECT e FROM Events e WHERE e.user = :user").setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	public Events getEventById(Long id) {
		Events events = null;
		List<Events> eventsList = em.createQuery("SELECT e FROM Events e WHERE e.id = :id").setParameter("id", id).getResultList();
		if(eventsList != null && eventsList.size() > 0)
			events = eventsList.get(0);
		return events;
	}

	@SuppressWarnings("unchecked")
	public List<Events> getPendingEvents(RequestContext context) {
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = userService.getUserProfile(request);
		return em.createQuery("SELECT e FROM Events e WHERE e.status = :status and e.user = :user")
				.setParameter("status", StatusType.PENDING)
				.setParameter("user", user)
				.getResultList();
	}

	public void changeActiveStatusById(String[] idLst) {
		if(idLst != null && idLst.length > 0) {
			for(String id : idLst) {
				Events events = getEventById(Long.valueOf(id));
				events.setStatus(StatusType.ACTIVE);
				em.persist(events);
			}
		}
	}
}
