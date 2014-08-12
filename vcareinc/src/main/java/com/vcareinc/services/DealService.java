package com.vcareinc.services;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.execution.RequestContext;

import com.vcareinc.constants.DiscountType;
import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.StatusType;
import com.vcareinc.exceptions.CommonException;
import com.vcareinc.exceptions.DBException;
import com.vcareinc.exceptions.ValidationException;
import com.vcareinc.models.DealOrder;
import com.vcareinc.utils.DateUtils;
import com.vcareinc.vo.Deals;
import com.vcareinc.vo.FileUpload;
import com.vcareinc.vo.User;

@Controller
public class DealService extends BaseService<DealOrder> {

	private Logger log = Logger.getLogger(DealService.class);

	@Autowired
	private UserService userService;

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
	public void saveDeal(RequestContext context, DealOrder dealOrder) throws CommonException {
		log.info(dealOrder.toString());
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = userService.getUserProfile(request);

		Long id = null;
		if(context.getFlowScope().get("optionTypeId") != null)
			id = Long.valueOf((String) context.getFlowScope().get("optionTypeId"));
		Deals deals = new Deals();
		try {
			validate(dealOrder);

			if(id != null && id > 0) {
				deals = getDealsById(id);
			} else {
				deals.setUser(user);
			}

			if(dealOrder.getTitle() != null && dealOrder.getTitle().trim().length() > 0)
				deals.setTitle(dealOrder.getTitle());

			if(dealOrder.getListingTitle() != null && dealOrder.getListingTitle().trim().length() > 0)
				deals.setListingTitle(dealOrder.getListingTitle());

			if(dealOrder.getSummaryDescription() != null && dealOrder.getSummaryDescription().trim().length() > 0)
				deals.setSummaryDescription(dealOrder.getSummaryDescription());

			if(dealOrder.getDescription() != null && dealOrder.getDescription().trim().length() > 0)
				deals.setDescription(dealOrder.getDescription());

			if(dealOrder.getConditions() != null && dealOrder.getConditions().trim().length() > 0)
				deals.setConditions(dealOrder.getConditions());

			if(dealOrder.getKeyword() != null && dealOrder.getKeyword().trim().length() > 0)
				deals.setKeyword(dealOrder.getKeyword());

			if(dealOrder.getVisibility() != null) {
				deals.setVisibility(dealOrder.getVisibility());

				if(dealOrder.getVisibility() == 1) {
					deals.setStartDate(DateUtils.getTimestamp(dealOrder.getStartDate() + " " + dealOrder.getStartHour() + ":" + dealOrder.getStartMinute() + " " + dealOrder.getStartAmPm(), "MM/dd/yyyy HH:mm a"));
					deals.setEndDate(DateUtils.getTimestamp(dealOrder.getEndDate() + dealOrder.getEndHour() + ":" + dealOrder.getEndMinute() + " " + dealOrder.getEndAmPm(), "MM/dd/yyyy HH:mm a"));
				} else {
					deals.setStartDate(DateUtils.getTimestamp(dealOrder.getStartDate()));
					deals.setEndDate(DateUtils.getTimestamp(dealOrder.getEndDate()));
				}
			}

			if( dealOrder.getDiscountType() != null)
				deals.setDiscountType(dealOrder.getDiscountType());

			if(dealOrder.getPriceNumber() != null)
				deals.setPrice(new Float(String.valueOf(dealOrder.getPriceNumber()) + "." + String.valueOf(dealOrder.getPriceDecimal())));

			if(dealOrder.getDiscountType().equals(DiscountType.FIXED))
				deals.setDiscountPrice(new Float(String.valueOf(dealOrder.getDiscountPriceNumber()) + "." + String.valueOf(dealOrder.getDiscountPriceDecimal())));
			else
				deals.setDiscountPrice(new Float(String.valueOf(dealOrder.getDiscountPriceNumber())));

			if(dealOrder.getTotalDeal() != null)
				deals.setTotalDeal(dealOrder.getTotalDeal());

			em.persist(deals);

			if(dealOrder.getImageUpload() != null && dealOrder.getImageUpload().getSize() > 0) {
				String filename = getFileName(deals.getId(), OptionType.DEAL, dealOrder.getImageUpload());
				FileUpload fileUpload = saveFileUpload(deals.getId(), filename, dealOrder.getImageUpload());
				deals.setImageUpload(fileUpload);
			}
			deals.setStatus(StatusType.ACTIVE);
			em.persist(deals);
		} catch (ValidationException | ParseException e) {
			throw new ValidationException(e.getMessage());
		} finally {
			context.getFlowScope().put("dealOrder", dealOrder);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public DealOrder getDealOrderById(RequestContext context, Long id) {
		DealOrder dealOrder = null;
		DealOrder dealOrderOld = (DealOrder) context.getFlowScope().get("dealOrder");
		try {
			if(id != null && id > 0) {
				Deals deals = getDealsById(id);
				if(deals != null) {
					dealOrder = new DealOrder();
					BeanUtils.copyProperties(dealOrder, deals);

					if(dealOrderOld != null) {
						if(dealOrderOld.getErrorConstraintViolation() != null && dealOrderOld.getErrorConstraintViolation().size() > 0)
							dealOrder.setErrorConstraintViolation(dealOrderOld.getErrorConstraintViolation());
					}
				}
			} else if(dealOrderOld != null) {
				dealOrder = dealOrderOld;
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return dealOrder;
	}

	@SuppressWarnings("unchecked")
	public List<Deals> getDealsByUser(User user) {
		return em.createQuery("SELECT d FROM Deals d WHERE d.user = :user").setParameter("user", user).getResultList();
	}

	public Deals getDealsById(Long id) {
		Deals deals = null;
		try {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Deals> dealQueries = cb.createQuery(Deals.class);
			Root<Deals> dealRoot = dealQueries.from(Deals.class);
			dealQueries.where(cb.equal(dealRoot.get("id"), id));

			deals =  em.createQuery(dealQueries).getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return deals;
	}

	@SuppressWarnings("unchecked")
	public List<Deals> getTopDealsLists(Integer numberOfLists) {
		return em.createQuery("SELECT d FROM Deals d WHERE d.status = :status")
				.setParameter("status", StatusType.ACTIVE)
				.setMaxResults(numberOfLists)
				.getResultList();
	}
}
