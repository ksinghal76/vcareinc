package com.vcareinc.services;

import java.lang.reflect.InvocationTargetException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.execution.RequestContext;

import com.vcareinc.constants.DateRange;
import com.vcareinc.constants.MonthOfYear;
import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.PriceType;
import com.vcareinc.constants.StatusType;
import com.vcareinc.exceptions.CommonException;
import com.vcareinc.exceptions.DBException;
import com.vcareinc.exceptions.ValidationException;
import com.vcareinc.models.EventOrder;
import com.vcareinc.services.repositories.EventRepository;
import com.vcareinc.utils.DateUtils;
import com.vcareinc.vo.Address;
import com.vcareinc.vo.Category;
import com.vcareinc.vo.Country;
import com.vcareinc.vo.Events;
import com.vcareinc.vo.FileUpload;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.PromotionCode;
import com.vcareinc.vo.State;
import com.vcareinc.vo.User;

@Controller
public class EventService extends BaseService<EventOrder> {

	private Logger log = Logger.getLogger(EventService.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Inject
	private EventRepository eventRepository;

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
			id = Long.valueOf((String) context.getFlowScope().get("optionTypeId"));

		try {
			validate(eventOrder);
			Events events = new Events();
			Address address = null;
			if(id != null && id > 0) {
				events = getEventById(id);
				if(events.getAddress() == null)
					address = new Address();
				else
					address = events.getAddress();
				if(events.getCategory() != null && events.getCategory().size() > 0) {
					events.deleteAllCategory(events.getCategory());
				}
			} else {
				events.setUser(user);
				events.setPrice(price);
				address = new Address();
			}

			if(eventOrder.getTitle() != null && eventOrder.getTitle().trim().length() > 0)
				events.setTitle(eventOrder.getTitle());

			if(eventOrder.getEmail() != null && eventOrder.getEmail().trim().length() > 0)
				events.setEmail(eventOrder.getEmail());

			if(eventOrder.getUrl() != null && eventOrder.getUrl().trim().length() > 0)
				events.setUrl(eventOrder.getProtocol() + eventOrder.getUrl());

			if(eventOrder.getPhoneNumber() != null && eventOrder.getPhoneNumber().trim().length() > 0)
				events.setPhoneNumber(eventOrder.getPhoneNumber());

			if(eventOrder.getContactName() != null && eventOrder.getContactName().trim().length() > 0)
				events.setContactName(eventOrder.getContactName());

			if(eventOrder.getLocationName() != null && eventOrder.getLocationName().trim().length() > 0)
				address.setLocationName(eventOrder.getLocationName());

			if(eventOrder.getAddress1() != null && eventOrder.getAddress1().trim().length() > 0)
				address.setAddress1(eventOrder.getAddress1());

			if(eventOrder.getAddress2() != null && eventOrder.getAddress2().trim().length() > 0)
				address.setAddress2(eventOrder.getAddress2());

			if(eventOrder.getCity() != null && eventOrder.getCity().trim().length() > 0)
				address.setCity(eventOrder.getCity());

			if(eventOrder.getZipcode() != null && eventOrder.getZipcode().trim().length() > 0)
				address.setZipcode(eventOrder.getZipcode());

			if(eventOrder.getLatitude() != null)
				address.setLatitude(eventOrder.getLatitude());

			if(eventOrder.getLongitude() != null)
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

			if(id != null && id > 0)
				events.setAddress(address);

			if(eventOrder.getStartDate() != null && eventOrder.getStartDate().trim().length() > 0) {
				if(eventOrder.getStartHour() != null && eventOrder.getStartHour().trim().length() > 0
					&& eventOrder.getStartMinute() != null && eventOrder.getStartMinute().trim().length() > 0
					&& eventOrder.getStartAmPm() != null && eventOrder.getStartAmPm().trim().length() > 0) {
					events.setStartDate(DateUtils.getTimestamp(eventOrder.getStartDate() + " " + eventOrder.getStartHour() + ":" + eventOrder.getStartMinute() + " " + eventOrder.getStartAmPm(), "MM/dd/yyyy HH:mm a"));
				} else {
					events.setStartDate(DateUtils.getTimestamp(eventOrder.getStartDate()));
				}
			}

			if(eventOrder.getEndDate() != null && eventOrder.getEndDate().trim().length() > 0) {
				if(eventOrder.getEndHour() != null && eventOrder.getEndHour().trim().length() > 0
					&& eventOrder.getEndMinute() != null && eventOrder.getEndMinute().trim().length() > 0
					&& eventOrder.getEndAmPm() != null && eventOrder.getEndAmPm().trim().length() > 0) {
					events.setEndDate(DateUtils.getTimestamp(eventOrder.getEndDate() + " " + eventOrder.getEndHour() + ":" + eventOrder.getEndMinute() + " " + eventOrder.getEndAmPm(), "MM/dd/yyyy HH:mm a"));
				} else {
					events.setEndDate(DateUtils.getTimestamp(eventOrder.getEndDate()));
				}
			}

			if(eventOrder.getRecurring() != null && eventOrder.getRecurring().trim().length() > 0)
				events.setRecurring(Boolean.valueOf(eventOrder.getRecurring()));
			else
				events.setRecurring(Boolean.FALSE);

			if(eventOrder.getRecurring() != null) {
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

			if(eventOrder.getSummarydesc() != null && eventOrder.getSummarydesc().trim().length() > 0)
				events.setSummaryDescription(eventOrder.getSummarydesc());

			if(eventOrder.getDescription() != null && eventOrder.getDescription().trim().length() > 0)
				events.setDescription(eventOrder.getDescription());

			if(eventOrder.getKeyword() != null && eventOrder.getKeyword().trim().length() > 0)
				events.setKeyword(eventOrder.getKeyword());

			if(PriceType.valueOf(priceType.name()).equals(PriceType.STUDENTS))
				events.setStatus(StatusType.ACTIVE);
			else
				events.setStatus(StatusType.PENDING);

			if(eventOrder.getCategories() != null && eventOrder.getCategories().length > 0) {
				Map<Long, Category> categoryMap = getCategories();
				for(String categoriesStr : eventOrder.getCategories()) {
					events.addCategory(categoryMap.get(Long.valueOf(categoriesStr)));
				}
			}

			if(eventOrder.getPromotionCode() != null && eventOrder.getPromotionCode().trim().length() > 0) {
				PromotionCode promotionCode = orderService.getPromotionCode(eventOrder.getPromotionCode());
				events.setPromotionCode(promotionCode);
			}

			em.persist(events);

			if(eventOrder.getImageUpload() != null && eventOrder.getImageUpload().getSize() > 0) {
				String filename = getFileName(events.getId(), OptionType.EVENT, eventOrder.getImageUpload());
				FileUpload fileUpload = saveFileUpload(events.getId(), filename, eventOrder.getImageUpload());
				events.setImageUpload(fileUpload);
			}
			if(!isProduction) {
				events.setStatus(StatusType.ACTIVE);
			}
			em.persist(events);
		} catch (ValidationException | ParseException e) {
			throw new ValidationException(e);
		} finally {
			context.getFlowScope().put("eventOrder", eventOrder);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public EventOrder getEventOrderById(RequestContext context, Long id) {
		EventOrder eventOrder = null;
		EventOrder eventOrderOld = (EventOrder) context.getFlowScope().get("eventOrder");
		try {
			if(id != null && id > 0) {
				Events events = getEventById(id);
				if(events != null) {
					eventOrder = new EventOrder();
					BeanUtils.copyProperties(eventOrder, events);

					if(events.getUrl() != null && events.getUrl().trim().length() > 0) {
						eventOrder.setProtocol(events.getUrl().substring(0, events.getUrl().indexOf("//")));
						eventOrder.setUrl(events.getUrl().substring(events.getUrl().indexOf("//"), events.getUrl().length()));
					}

					if(events.getSummaryDescription() != null && events.getSummaryDescription().trim().length() > 0)
						eventOrder.setSummarydesc(events.getSummaryDescription());

					if(events.getStartDate() != null) {
						eventOrder.setStartDate(DateUtils.getStringforDate(events.getStartDate()));
						eventOrder.setStartHour(DateUtils.getStringforDate(events.getStartDate(), "HH"));
						eventOrder.setStartMinute(DateUtils.getStringforDate(events.getStartDate(), "mm"));
						eventOrder.setStartAmPm(DateUtils.getStringforDate(events.getStartDate(), "a"));
					}

					if(events.getEndDate() != null) {
						eventOrder.setEndDate(DateUtils.getStringforDate(events.getEndDate()));
						eventOrder.setEndHour(DateUtils.getStringforDate(events.getEndDate(), "HH"));
						eventOrder.setEndMinute(DateUtils.getStringforDate(events.getEndDate(), "mm"));
						eventOrder.setEndAmPm(DateUtils.getStringforDate(events.getEndDate(), "a"));
					}

					if(events.getRecurring() != null && events.getRecurring()) {
						eventOrder.setPeriod(events.getPeriod().name());
						eventOrder.setMonth(events.getMonth().name());
						eventOrder.setMonth2(events.getMonth2().name());

						if(events.getDayOfWeek() != null && events.getDayOfWeek().trim().length() > 0) {
							String[] datOfWkArr = events.getDayOfWeek().split(":");
	//						DayOfWeek[] dayOfWeek = new DayOfWeek[datOfWkArr.length];
	//						int i = 0;
	//						for(String datOfWk : datOfWkArr) {
	//							dayOfWeek[i++] = DayOfWeek.valueOf(datOfWk);
	//						}
							eventOrder.setDayOfWeekcb(datOfWkArr);
						}

						if(events.getWeekOfMonth() != null && events.getWeekOfMonth().trim().length() > 0) {
							String[] wkOfMthArr = events.getWeekOfMonth().split(":");
	//						WeekOfMonth[] weekOfMonth = new WeekOfMonth[wkOfMthArr.length];
	//						int i = 0;
	//						for(String wkOfMth : wkOfMthArr) {
	//							weekOfMonth[i++] = WeekOfMonth.valueOf(wkOfMth);
	//						}
							eventOrder.setWeekOfMonth(wkOfMthArr);
						}
					}

					if(events.getAddress() != null) {
						BeanUtils.copyProperties(eventOrder, events.getAddress());

						if(events.getAddress().getState() != null)
							eventOrder.setState(events.getAddress().getState().getCode());

						if(events.getAddress().getCountry() != null)
							eventOrder.setCountry(events.getAddress().getCountry().getCode());
					}

					if(events.getImageUpload() != null) {
						eventOrder.setImageUploadFilename(events.getImageUpload().getClientFilename());
					}

					if(events.getCategory() != null && events.getCategory().size() > 0) {
						String[] strArr = new String[events.getCategory().size()];
						int i = 0;
						for(Category category : events.getCategory()) {
							strArr[i++] = String.valueOf(category.getId());
						}
						eventOrder.setCategories(strArr);
					}

					if(eventOrderOld != null) {
						if(eventOrderOld.getErrorConstraintViolation() != null && eventOrderOld.getErrorConstraintViolation().size() > 0)
							eventOrder.setErrorConstraintViolation(eventOrderOld.getErrorConstraintViolation());
					}
				}
			} else if(eventOrderOld != null) {
				eventOrder = eventOrderOld;
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return eventOrder;
	}

	@SuppressWarnings("unchecked")
	public List<Events> getEventsByUser(User user) {
		return em.createQuery("SELECT e FROM Events e WHERE e.user = :user").setParameter("user", user).getResultList();
	}

	public Events getEventById(Long id) {
		Events events = null;
		try {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Events> eventQueries = cb.createQuery(Events.class);
			Root<Events> eventRoot = eventQueries.from(Events.class);
			eventRoot.fetch("category");
			eventQueries.where(cb.equal(eventRoot.get("id"), id));

			events =  em.createQuery(eventQueries).getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
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

	@SuppressWarnings("unchecked")
	public List<Events> getEventsByDate(Timestamp date) {
		return em.createQuery("SELECT e FROM Events e WHERE e.status = :status and e.startDate >= :selectedDate and e.endDate <= :selectedDate")
				.setParameter("status", StatusType.PENDING)
				.setParameter("selectedDate", date)
				.getResultList();
	}

	@SuppressWarnings("unchecked")
	public List<Events> getTopEventLists(Integer numberOfLists) {
		return em.createQuery("SELECT e FROM Events e WHERE e.status = :status")
				.setParameter("status", StatusType.PENDING)
				.setMaxResults(numberOfLists)
				.getResultList();
	}

	public Page<Events> getEventsByCategoryOrderByPriceType(Long categoryId, Integer pageNumber, Integer numberPerPage) {
		PageRequest request = new PageRequest(pageNumber - 1, numberPerPage);
		return eventRepository.findEventsByCategoryOrderByPriceType(categoryId, request);
	}

	public Page<Events> getEventsByCategoryOrderByTitle(Long categoryId, Integer pageNumber, Integer numberPerPage) {
		PageRequest request = new PageRequest(pageNumber - 1, numberPerPage);
		return eventRepository.findEventsByCategoryOrderByTitle(categoryId, request);
	}

	public Page<Events> getEventsByDateOrderByTitle(Timestamp date, Integer pageNumber, Integer numberPerPage) {
		PageRequest request = new PageRequest(pageNumber -1, numberPerPage);
		return eventRepository.findEventsByDateOrderByPriceType(date, request);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getCategoriesById(Long id) {
		return em.createNativeQuery("SELECT c.id, c.name, count(*)"
				+ " FROM Category c"
				+ " INNER JOIN events_category ec ON ec.category_id = c.id"
				+ " WHERE ec.events_id = :id"
				+ " GROUP BY c.name"
				+ " HAVING count(*)  > 0")
				.setParameter("id", id)
				.getResultList();
	}
}
