package com.vcareinc.services;

import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.execution.RequestContext;

import com.vcareinc.constants.DiscountType;
import com.vcareinc.constants.OptionType;
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
		try {
			validate(dealOrder);
			Deals deals = new Deals();
			deals.setUser(user);
			deals.setTitle(dealOrder.getTitle());
			deals.setListingTitle(dealOrder.getListingTitle());
			deals.setSummaryDescription(dealOrder.getSummaryDescription());
			deals.setDescription(dealOrder.getDescription());
			deals.setConditions(dealOrder.getConditions());
			deals.setKeyword(dealOrder.getKeyword());
			deals.setVisibility(dealOrder.getVisibility());
			if(dealOrder.getVisibility() == 1) {
				deals.setStartDate(DateUtils.getTimestamp(dealOrder.getStartDate() + " " + dealOrder.getStartHour() + ":" + dealOrder.getStartMinute() + " " + dealOrder.getStartAmPm(), "MM/dd/yyyy HH:mm a"));
				deals.setEndDate(DateUtils.getTimestamp(dealOrder.getEndDate() + dealOrder.getEndHour() + ":" + dealOrder.getEndMinute() + " " + dealOrder.getEndAmPm(), "MM/dd/yyyy HH:mm a"));
			} else {
				deals.setStartDate(DateUtils.getTimestamp(dealOrder.getStartDate()));
				deals.setEndDate(DateUtils.getTimestamp(dealOrder.getEndDate()));
			}
			deals.setDiscountType(dealOrder.getDiscountType());
			deals.setPrice(new Float(String.valueOf(dealOrder.getPriceNumber()) + "." + String.valueOf(dealOrder.getPriceDecimal())));

			if(dealOrder.getDiscountType().equals(DiscountType.FIXED))
				deals.setDiscountPrice(new Float(String.valueOf(dealOrder.getDiscountPriceNumber()) + "." + String.valueOf(dealOrder.getDiscountPriceDecimal())));
			else
				deals.setDiscountPrice(new Float(String.valueOf(dealOrder.getDiscountPriceNumber())));
			deals.setTotalDeal(dealOrder.getTotalDeal());

			em.persist(deals);

			if(dealOrder.getImageUpload() != null && dealOrder.getImageUpload().getSize() > 0) {
				String filename = getFileName(deals.getId(), OptionType.DEAL, dealOrder.getImageUpload());
				FileUpload fileUpload = saveFileUpload(deals.getId(), filename, dealOrder.getImageUpload());
				deals.setImageUpload(fileUpload);
			}
			em.persist(deals);
		} catch (ValidationException | ParseException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	public DealOrder getDealOrderById(Long id) {
		DealOrder dealOrder = null;
		Deals deals = getDealsById(id);
		if(deals != null) {
			dealOrder = new DealOrder();
			BeanUtils.copyProperties(deals, dealOrder);
		}
		return dealOrder;
	}

	@SuppressWarnings("unchecked")
	public List<Deals> getDealsByUser(User user) {
		return em.createQuery("SELECT d FROM Deals d WHERE d.user = :user").setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	public Deals getDealsById(Long id) {
		Deals deals = null;
		List<Deals> dealsList = em.createQuery("SELECT d FROM Deals d WHERE d.id = :id").setParameter("id", id).getResultList();
		if(dealsList != null && dealsList.size() > 0)
			deals = dealsList.get(0);
		return deals;
	}
}
