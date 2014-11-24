package com.vcareinc.services;

import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
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

import com.google.code.geocoder.model.GeocodeResponse;
import com.google.code.geocoder.model.GeocoderStatus;
import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.PriceType;
import com.vcareinc.constants.SortingOrder;
import com.vcareinc.constants.StatusType;
import com.vcareinc.exceptions.CommonException;
import com.vcareinc.exceptions.DBException;
import com.vcareinc.exceptions.ValidationException;
import com.vcareinc.models.ListingOrder;
import com.vcareinc.services.repositories.ListingRepository;
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

	@Inject
	private ListingRepository listingRepository;

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
		String googleAddress = "";
		try {
			validate(listingOrder);
			Address address = null;
			if(id != null && id > 0) {
				listings = getListingById(id);
				if(listings.getAddress() ==  null)
					address = new Address();
				else
					address = listings.getAddress();
				if(listings.getCategory() != null && listings.getCategory().size() > 0) {
					listings.deleteAllCategory(listings.getCategory());
				}
			} else {
				address = new Address();
				listings.setUser(user);
				listings.setPrice(price);
			}

			if(listingOrder.getTitle() != null && listingOrder.getTitle().trim().length() > 0)
				listings.setTitle(listingOrder.getTitle());

			if(listingOrder.getEmail() != null && listingOrder.getEmail().trim().length() > 0)
				listings.setEmail(listingOrder.getEmail());

			if(listingOrder.getUrl() != null && listingOrder.getUrl().trim().length() > 0)
				listings.setUrl(listingOrder.getUrlProtocol() + listingOrder.getUrl());

			if(listingOrder.getDisplayUrl() != null && listingOrder.getDisplayUrl().trim().length() > 0)
				listings.setDisplayUrl(listingOrder.getDisplayUrl());

			if(listingOrder.getPhoneNumber() != null && listingOrder.getPhoneNumber().trim().length() > 0)
				listings.setPhoneNumber(listingOrder.getPhoneNumber());

			if(listingOrder.getFaxNumber() != null && listingOrder.getFaxNumber().trim().length() > 0)
				listings.setFaxNumber(listingOrder.getFaxNumber());

			if(listingOrder.getFacebookPage() != null && listingOrder.getFacebookPage().trim().length() > 0)
				listings.setFacebookPage(listingOrder.getFacebookPage());

			if(listingOrder.getAddress1() != null && listingOrder.getAddress1().trim().length() > 0) {
				address.setAddress1(listingOrder.getAddress1());
				googleAddress += listingOrder.getAddress1();
			}

			if(listingOrder.getAddress2() != null && listingOrder.getAddress2().trim().length() > 0) {
				address.setAddress2(listingOrder.getAddress2());
				googleAddress += (StringUtils.isNotBlank(googleAddress) ? "," : "") + listingOrder.getAddress2();
			}

			if(listingOrder.getCity() != null && listingOrder.getCity().trim().length() > 0) {
				address.setCity(listingOrder.getCity());
				googleAddress += (StringUtils.isNotBlank(googleAddress) ? "," : "") + listingOrder.getCity();
			}

			if(listingOrder.getState() != null && Long.valueOf(listingOrder.getState().trim()) > 0) {
				State state = orderService.getStateById(Long.valueOf(listingOrder.getState()));
				address.setState(state);
				googleAddress += (StringUtils.isNotBlank(googleAddress) ? "," : "") + state.getCode();
			}

			if(StringUtils.isNotBlank(googleAddress)) {
				GeocodeResponse response = googleMapApi.getGoogleResponse(googleAddress);
				if(response.getStatus().equals(GeocoderStatus.OK)) {
					address.setLatitude(response.getResults().get(0).getGeometry().getLocation().getLat().floatValue());
					address.setLongitude(response.getResults().get(0).getGeometry().getLocation().getLng().floatValue());
				}
			}

			if(listingOrder.getCountry() != null && Long.valueOf(listingOrder.getCountry().trim()) > 0) {
				Country country = orderService.getCountryById(Long.valueOf(listingOrder.getCountry()));
				address.setCountry(country);
			}

			if(listingOrder.getZipcode() != null && listingOrder.getZipcode().trim().length() > 0)
				address.setZipcode(listingOrder.getZipcode());

			if(listingOrder.getLatitude() != null)
				address.setLatitude(listingOrder.getLatitude());

			if(listingOrder.getLongitude() != null)
				address.setLongitude(listingOrder.getLongitude());


			em.persist(address);

			if( id != null && id > 0)
				listings.setAddress(address);

			if(listingOrder.getVideoSnippet() != null && listingOrder.getVideoSnippet().trim().length() > 0)
				listings.setVideoSnippet(listingOrder.getVideoSnippet());

			if(listingOrder.getVideoDescription() != null && listingOrder.getVideoDescription().trim().length() > 0)
				listings.setVideoDescription(listingOrder.getVideoDescription());

			if(listingOrder.getAdditionalFileDescription() != null && listingOrder.getAdditionalFileDescription().trim().length() > 0)
				listings.setAdditionalFileDescription(listingOrder.getAdditionalFileDescription());

			if(listingOrder.getSummaryDescription() != null && listingOrder.getSummaryDescription().trim().length() > 0)
				listings.setSummaryDescription(listingOrder.getSummaryDescription());

			if(listingOrder.getDescription() != null && listingOrder.getDescription().trim().length() > 0)
				listings.setDescription(listingOrder.getDescription());

			if(listingOrder.getKeyword() != null && listingOrder.getKeyword().trim().length() > 0)
				listings.setKeyword(listingOrder.getKeyword());

			if(listingOrder.getHourOfWork() != null && listingOrder.getHourOfWork().trim().length() > 0)
				listings.setHourOfWork(listingOrder.getHourOfWork());

			if(listingOrder.getLocation() != null && listingOrder.getLocation().trim().length() > 0)
				listings.setLocation(listingOrder.getLocation());

			if(listingOrder.getBestService() != null)
				listings.setBestService(listingOrder.getBestService());

			if(listingOrder.getBestValue() != null)
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

			if(!isProduction) {
				listings.setStatus(StatusType.ACTIVE);
			}
			em.persist(listings);

		} catch (ValidationException e) {
			throw new ValidationException(e.getMessage());
		} finally {
			context.getFlowScope().put("listingOrder", listingOrder);
		}
	}

	@SuppressWarnings("unchecked")
	public List<Listings> getListingByUser(User user) {
		return em.createQuery("SELECT l FROM Listings l WHERE l.user = :user").setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	public Listings getListingById(Long id) {
		Listings listings = null;
		List<Listings> listingsLst = em.createQuery("SELECT l FROM Listings l LEFT JOIN FETCH l.category c LEFT JOIN FETCH l.address a LEFT JOIN FETCH a.country co LEFT JOIN FETCH a.state s WHERE l.id = :id")
				.setParameter("id", id)
				.getResultList();
		if(listingsLst != null && listingsLst.size() > 0)
			listings = listingsLst.get(0);
		return listings;
//		Listings listings = null;
//		try {
//
//			CriteriaBuilder cb = em.getCriteriaBuilder();
//			CriteriaQuery<Listings> listingQueries = cb.createQuery(Listings.class);
//			Root<Listings> listingRoot = listingQueries.from(Listings.class);
//			listingRoot.fetch("category");
//			listingRoot.fetch("address");
//			listingQueries.where(cb.equal(listingRoot.get("id"), id));
//
//			listings =  em.createQuery(listingQueries).getSingleResult();
//		} catch (NoResultException e) {
//			e.printStackTrace();
//		}
//		return listings;
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public ListingOrder getListingOrderById(RequestContext context, Long id) {
		ListingOrder listingOrder = null;
		ListingOrder listingOrderOld = (ListingOrder) context.getFlowScope().get("listingOrder");
		try {
			if(id != null && id > 0) {
				Listings listings = getListingById(id);
				if(listings != null) {
					listingOrder = new ListingOrder();
					BeanUtils.copyProperties(listingOrder, listings);

					if(listings.getUrl() != null && listings.getUrl().trim().length() > 0) {
						listingOrder.setUrlProtocol(listings.getUrl().substring(0, listings.getUrl().indexOf("//")));
						listingOrder.setUrl(listings.getUrl().substring(listings.getUrl().indexOf("//"), listings.getUrl().length()));
					}

					if(listings.getAddress() != null) {
						BeanUtils.copyProperties(listingOrder, listings.getAddress());

						if(listings.getAddress().getState() != null)
							listingOrder.setState(listings.getAddress().getState().getCode());

						if(listings.getAddress().getCountry() != null)
							listingOrder.setCountry(listings.getAddress().getCountry().getCode());
					}

					if(listings.getImageUpload() != null) {
						listingOrder.setImageUploadFilename(listings.getImageUpload().getClientFilename());
					}

					if(listings.getAdditionalFile() != null) {
						listingOrder.setAdditionalFileFilename(listings.getAdditionalFile().getClientFilename());
					}

					if(listings.getCategory() != null && listings.getCategory().size() > 0) {
						String[] strArr = new String[listings.getCategory().size()];
						int i = 0;
						for(Category category : listings.getCategory()) {
							strArr[i++] = String.valueOf(category.getId());
						}
						listingOrder.setCategories(strArr);
					}

					if(listingOrderOld != null) {
						if(listingOrderOld.getErrorConstraintViolation() != null && listingOrderOld.getErrorConstraintViolation().size() > 0)
							listingOrder.setErrorConstraintViolation(listingOrderOld.getErrorConstraintViolation());
					}
				}
			} else if(listingOrderOld != null) {
				listingOrder = listingOrderOld;
			}
		} catch (InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
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

	@SuppressWarnings("unchecked")
	public List<Listings> getListView(Long categoryId, SortingOrder orderBy) {
		return em.createQuery("SELECT l FROM Listings l JOIN FETCH l.category c JOIN FETCH l.address a JOIN FETCH a.country co JOIN FETCH a.state s"
									+ " WHERE c.id = :categoryId"
									+ " ORDER BY :orderBy")
									.setParameter("categoryId", categoryId)
									.setParameter("orderBy", "l." + orderBy.getSortingName())
									.getResultList();
	}
	
	@SuppressWarnings("unchecked")
	public List<String> getAllCity() {
		return em.createQuery("SELECT distinct a.city FROM Listings l JOIN FETCH l.address a"
				+ " WHERE l.status = :status"
				+ " ORDER BY a.city")
				.setParameter("status", StatusType.ACTIVE)
				.getResultList();
	}

	public Page<Listings> getListingsByCategoryOrderbyPriceType(Long categoryId, Integer pageNumber, Integer numberPerPage) {
		PageRequest request = new PageRequest(pageNumber - 1, numberPerPage);
		return listingRepository.findListingsByCategoryOrderByPriceType(categoryId, request);
	}

	public Page<Listings> getListingsByCategoryOrderByTitle(Long categoryId, Integer pageNumber, Integer numberPerPage) {
		PageRequest request = new PageRequest(pageNumber - 1, numberPerPage);
		return listingRepository.findListingsByCategoryOrderByTitle(categoryId, request);
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getCategoriesById(Long id) {
		return em.createNativeQuery("SELECT c.id, c.name, count(*)"
				+ " FROM Category c"
				+ " INNER JOIN listings_category lc ON lc.category_id = c.id"
				+ " WHERE lc.listings_id = :id"
				+ " GROUP BY c.name"
				+ " HAVING count(*)  > 0")
				.setParameter("id", id)
				.getResultList();
	}
}
