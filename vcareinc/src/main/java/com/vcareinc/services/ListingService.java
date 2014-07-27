package com.vcareinc.services;

import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

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
import com.vcareinc.models.ListingOrder;
import com.vcareinc.vo.Address;
import com.vcareinc.vo.Category;
import com.vcareinc.vo.Country;
import com.vcareinc.vo.FileUpload;
import com.vcareinc.vo.Listings;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.PromotionCode;
import com.vcareinc.vo.State;
import com.vcareinc.vo.User;

@Controller
public class ListingService extends BaseService<ListingOrder> {

	private Logger log = Logger.getLogger(ListingService.class);

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
	public void saveListing(RequestContext context, ListingOrder listingOrder) throws CommonException {
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = userService.getUserProfile(request);

		OptionType optionType = OptionType.valueOf((String) context.getFlowScope().get("optionType"));
		PriceType priceType = PriceType.valueOf((String) context.getFlowScope().get("priceType"));
		Price price = orderService.getPriceByType(optionType, priceType);
		Long id = null;
		if(context.getFlowScope().get("optionTypeId") != null)
			id = Long.valueOf((String) context.getFlowScope().get("optionTypeId"));

		log.info(listingOrder.toString());
		Listings listings = new Listings();
		try {
			validate(listingOrder);
			if(id != null && id > 0)
				listings.setId(id);
			listings.setUser(user);
			listings.setPrice(price);

			listings.setTitle(listingOrder.getTitle());
			listings.setEmail(listingOrder.getEmail());
			listings.setUrl(listingOrder.getUrlProtocol() + listingOrder.getUrl());
			listings.setDisplayUrl(listingOrder.getDisplayUrl());
			listings.setPhoneNumber(listingOrder.getPhoneNumber());
			listings.setFaxNumber(listingOrder.getFaxNumber());
			listings.setFacebookPage(listingOrder.getFacebookPage());

			Address address = new Address();
			address.setAddress1(listingOrder.getAddress1());
			address.setAddress2(listingOrder.getAddress2());
			address.setCity(listingOrder.getCity());
			address.setZipcode(listingOrder.getZipcode());
			address.setLatitude(listingOrder.getLatitude());
			address.setLongitude(listingOrder.getLongitude());

			if(listingOrder.getState() != null && Long.valueOf(listingOrder.getState().trim()) > 0) {
				State state = orderService.getStateById(Long.valueOf(listingOrder.getState()));
				address.setState(state);
			}

			if(listingOrder.getCountry() != null && Long.valueOf(listingOrder.getCountry().trim()) > 0) {
				Country country = orderService.getCountryById(Long.valueOf(listingOrder.getCountry()));
				address.setCountry(country);
			}
			em.persist(address);
			listings.setAddress(address);

			listings.setVideoSnippet(listingOrder.getVideoSnippet());
			listings.setVideoDescription(listingOrder.getVideoDescription());

			listings.setAdditionalFileDescription(listingOrder.getAdditionalFileDescription());

			listings.setSummaryDescription(listingOrder.getSummaryDescription());
			listings.setDescription(listingOrder.getDescription());
			listings.setKeyword(listingOrder.getKeyword());
			listings.setHourOfWork(listingOrder.getHourOfWork());
			listings.setLocation(listingOrder.getLocation());

			listings.setBestService(listingOrder.getBestService());
			listings.setBestValue(listingOrder.getBestValue());

			if(listingOrder.getCategories() != null && listingOrder.getCategories().length > 0) {
				Map<Long, Category> categoryMap = getCategories();
				for(String categoriesStr : listingOrder.getCategories()) {
					listings.addCategory(categoryMap.get(Long.valueOf(categoriesStr)));
				}
			}

			if(listingOrder.getPromotionCode() != null && listingOrder.getPromotionCode().trim().length() > 0) {
				PromotionCode promotionCode = orderService.getPromotionCode(listingOrder.getPromotionCode());
				listings.setPromotionCode(promotionCode);
			}

			em.persist(listings);

			if(listingOrder.getImageUpload() != null && listingOrder.getImageUpload().getSize() > 0) {
				String filename = getFileName(listings.getId(), OptionType.LISTING, listingOrder.getImageUpload());
				FileUpload fileUpload = saveFileUpload(listings.getId(), filename, listingOrder.getImageUpload());
				listings.setImageUpload(fileUpload);
			}

			if(listingOrder.getAdditionalFile() != null && listingOrder.getAdditionalFile().getSize() > 0) {
				String filename = getFileName(listings.getId(), OptionType.LISTING, listingOrder.getAdditionalFile());
				FileUpload fileUpload2 = saveFileUpload(listings.getId(), filename, listingOrder.getAdditionalFile());
				listings.setAdditionalFile(fileUpload2);
			}

			if(PriceType.valueOf(priceType.name()).equals(PriceType.STUDENTS))
				listings.setStatus(StatusType.ACTIVE);
			else
				listings.setStatus(StatusType.PENDING);

			em.persist(listings);

			clearObject(listingOrder);
		} catch (ConstraintViolationException e) {
			listingOrder.setErrorConstraintViolation(e.getConstraintViolations());
			throw new DBException(e.getMessage());
		} catch(PersistenceException e) {
			if(DBException.is(e, org.hibernate.exception.ConstraintViolationException.class)) {
				listingOrder.setErrorMsg("????listingOrderException????");
				throw new DBException(e.getMessage());
			} else {
				listingOrder.setErrorMsg(e.getMessage());
				throw new DBException(e.getMessage());
			}
		} catch (ValidationException e) {
			throw new ValidationException(e.getMessage());
		}
	}

	@SuppressWarnings("unchecked")
	public List<Listings> getListingByUser(User user) {
		return em.createQuery("SELECT l FROM Listings l WHERE l.user = :user").setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	public Listings getListingById(Long id) {
		Listings listings = null;
		List<Listings> listingsList = em.createQuery("SELECT l from Listings l WHERE l.id = :id").setParameter("id", id).getResultList();
		if(listingsList != null && listingsList.size() > 0) {
			listings = listingsList.get(0);
		}
		return listings;
	}

	public ListingOrder getListingOrderById(Long id) {
		ListingOrder listingOrder = null;
		Listings listings = getListingById(id);
		if(listings != null) {
			listingOrder = new ListingOrder();
			BeanUtils.copyProperties(listings, listingOrder);

			listingOrder.setUrlProtocol(listings.getUrl().substring(0, listings.getUrl().indexOf("//")));
			listingOrder.setUrl(listings.getUrl().substring(listings.getUrl().indexOf("//"), listings.getUrl().length()));

			if(listings.getAddress() != null) {
				BeanUtils.copyProperties(listings.getAddress(), listingOrder);
			}

			if(listings.getImageUpload() != null) {
				listingOrder.setImageUploadFilename(listings.getImageUpload().getClientFilename());
			}

			if(listings.getAdditionalFile() != null) {
				listingOrder.setAdditionalFileFilename(listings.getAdditionalFile().getClientFilename());
			}
		}
		return listingOrder;
	}

	@SuppressWarnings("unchecked")
	public List<Listings> getPendingListings(RequestContext context) {
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = userService.getUserProfile(request);
		return em.createQuery("SELECT l from Listings l WHERE l.status = :status and l.user = :user")
				.setParameter("status", StatusType.PENDING)
				.setParameter("user", user)
				.getResultList();
	}

	public void changeActiveStatusById(String[] idLst) {
		if(idLst != null && idLst.length > 0) {
			for(String id : idLst) {
				Listings listings = getListingById(Long.valueOf(id));
				listings.setStatus(StatusType.ACTIVE);
				em.persist(listings);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Listings> getTopListingsLists(Integer numberOfLists) {
		return em.createQuery("SELECT l from Listings l WHERE l.status = :status")
				.setParameter("status", StatusType.ACTIVE)
				.setMaxResults(numberOfLists)
				.getResultList();
	}
}
