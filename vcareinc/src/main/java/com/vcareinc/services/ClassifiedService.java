package com.vcareinc.services;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.webflow.execution.RequestContext;

import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.PriceType;
import com.vcareinc.constants.StatusType;
import com.vcareinc.exceptions.CommonException;
import com.vcareinc.exceptions.DBException;
import com.vcareinc.exceptions.ValidationException;
import com.vcareinc.models.ClassifiedOrder;
import com.vcareinc.vo.Address;
import com.vcareinc.vo.Classified;
import com.vcareinc.vo.Country;
import com.vcareinc.vo.FileUpload;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.State;
import com.vcareinc.vo.User;

@Controller
public class ClassifiedService extends BaseService<ClassifiedOrder> {

	private Logger log = Logger.getLogger(ClassifiedService.class);

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
	public void saveClassified(RequestContext context, ClassifiedOrder classifiedOrder) throws CommonException {
		log.info(classifiedOrder.toString());
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = userService.getUserProfile(request);
		OptionType optionType = OptionType.valueOf((String) context.getFlowScope().get("optionType"));
		PriceType priceType = PriceType.valueOf((String) context.getFlowScope().get("priceType"));
		Price price = orderService.getPriceByType(optionType, priceType);
		Long id = null;
		if(context.getFlowScope().get("optionTypeId") != null)
			id = (Long) context.getFlowScope().get("optionTypeId");

		Classified classified = new Classified();
		try {
			validate(classifiedOrder);
			if(id != null && id > 0)
				classified.setId(id);
			classified.setUser(user);
			classified.setPrice(price);
			classified.setTitle(classifiedOrder.getTitle());
			classified.setContactName(classifiedOrder.getContactName());
			classified.setContactPhoneNumber(classifiedOrder.getContactPhoneNumber());
			classified.setContactFax(classifiedOrder.getContactFax());
			classified.setContactEmail(classifiedOrder.getContactEmail());

			classified.setUrl(classifiedOrder.getProtocol() + classifiedOrder.getUrl());
			if(classifiedOrder.getAmountDollar() != null && classifiedOrder.getAmountCent() != null)
				classified.setAmount(new Float(String.valueOf(classifiedOrder.getAmountDollar()) + "." + String.valueOf(classifiedOrder.getAmountCent())));

			Address address = new Address();
			address.setAddress1(classifiedOrder.getAddress1());
			address.setAddress2(classifiedOrder.getAddress2());
			address.setCity(classifiedOrder.getCity());
			address.setZipcode(classifiedOrder.getZipcode());
			address.setLatitude(classifiedOrder.getLatitude());
			address.setLongitude(classifiedOrder.getLongitude());

			if(classifiedOrder.getState() != null && classifiedOrder.getState().trim().length() > 0 && !classifiedOrder.getState().trim().equals("0")) {
				State state = orderService.getStateById(Long.valueOf(classifiedOrder.getState()));
				address.setState(state);
			}

			if(classifiedOrder.getCountry() != null) {
				Country country = orderService.getCountryById(Long.valueOf(classifiedOrder.getCountry()));
				address.setCountry(country);
			}

			em.persist(address);
			classified.setAddress(address);

			classified.setSummaryDescription(classifiedOrder.getSummarydesc());
			classified.setDetailDescription(classifiedOrder.getDetailDescription());
			classified.setKeyword(classifiedOrder.getKeyword());
//			classified.setPromotionCode(classifiedOrder.getPromotionCode());
			em.persist(classified);

			if(classifiedOrder.getImageUpload() != null && classifiedOrder.getImageUpload().getSize() > 0) {
				String filename = getFileName(classified.getId(), OptionType.CLASSIFIED, classifiedOrder.getImageUpload());
				FileUpload fileUpload = saveFileUpload(classified.getId(), filename, classifiedOrder.getImageUpload());
				classified.setImageUpload(fileUpload);
			}
			if(PriceType.valueOf(priceType.name()).equals(PriceType.STUDENTS))
				classified.setStatus(StatusType.ACTIVE);
			else
				classified.setStatus(StatusType.PENDING);
			em.persist(classified);

			clearObject(classifiedOrder);
		} catch (ValidationException e) {
			throw new ValidationException(e);
		}
	}

	public ClassifiedOrder getClassifiedOrderById(Long id) {
		ClassifiedOrder classifiedOrder = null;
		Classified classified = getClassifiedById(id);
		if(classified != null) {
			classifiedOrder = new ClassifiedOrder();
			BeanUtils.copyProperties(classified, classifiedOrder);

			classifiedOrder.setProtocol(classified.getUrl().substring(0, classified.getUrl().indexOf("//")));
			classifiedOrder.setUrl(classified.getUrl().substring(classified.getUrl().indexOf("//"), classified.getUrl().length()));
			classifiedOrder.setSummarydesc(classified.getSummaryDescription());

			if(classified.getAddress() != null) {
				BeanUtils.copyProperties(classified.getAddress(), classifiedOrder);
			}

			if(classified.getImageUpload() != null) {
				classifiedOrder.setImageUploadFilename(classified.getImageUpload().getClientFilename());
			}
		}
		return classifiedOrder;
	}

	@SuppressWarnings("unchecked")
	public List<Classified> getClassifiedByUser(User user) {
		return em.createQuery("SELECT c FROM Classified c WHERE c.user = :user").setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	public Classified getClassifiedById(Long id) {
		Classified classified = null;
		List<Classified> classifiedList = em.createQuery("SELECT c FROM Classified c WHERE c.id = :id").setParameter("id", id).getResultList();
		if(classifiedList != null && classifiedList.size() > 0)
			classified = classifiedList.get(0);


		return classified;
	}

	@SuppressWarnings("unchecked")
	public List<Classified> getPendingClassified(RequestContext context) {
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = userService.getUserProfile(request);
		return em.createQuery("SELECT c FROM Classified c WHERE c.status = :status and c.user = :user")
				.setParameter("status", StatusType.PENDING)
				.setParameter("user", user)
				.getResultList();
	}

	public void changeActiveStatusById(String[] idLst) {
		if(idLst != null && idLst.length > 0) {
			for(String id : idLst) {
				Classified classified = getClassifiedById(Long.valueOf(id));
				classified.setStatus(StatusType.ACTIVE);
				em.persist(classified);
			}
		}
	}
	
	@SuppressWarnings("unchecked")
	public List<Classified> getTopClassifiedLists(Integer numberOfLists) {
		return em.createQuery("SELECT c FROM Classified c WHERE c.status = :status")
				.setParameter("status", StatusType.ACTIVE)
				.setMaxResults(numberOfLists)
				.getResultList();
	}
}
