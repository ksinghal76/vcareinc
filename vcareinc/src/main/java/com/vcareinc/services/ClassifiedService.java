package com.vcareinc.services;

import java.lang.reflect.InvocationTargetException;
import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.NoResultException;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import com.vcareinc.services.repositories.ClassifiedRepository;
import com.vcareinc.vo.Address;
import com.vcareinc.vo.Category;
import com.vcareinc.vo.Classified;
import com.vcareinc.vo.Country;
import com.vcareinc.vo.FileUpload;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.PromotionCode;
import com.vcareinc.vo.State;
import com.vcareinc.vo.User;

@Controller
public class ClassifiedService extends BaseService<ClassifiedOrder> {

	private Logger log = Logger.getLogger(ClassifiedService.class);

	@Autowired
	private OrderService orderService;

	@Autowired
	private UserService userService;

	@Inject
	private ClassifiedRepository classifiedRepository;


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
			id = Long.valueOf((String) context.getFlowScope().get("optionTypeId"));

		Classified classified = new Classified();
		try {
			validate(classifiedOrder);
			Address address = null;
			if(id != null && id > 0) {
				classified = getClassifiedById(id);
				if(classified.getAddress() == null)
					address = new Address();
				else
					address = classified.getAddress();
				if(classified.getCategory() != null && classified.getCategory().size() > 0) {
					classified.deleteAllCategory(classified.getCategory());
				}
			} else {
				address = new Address();
				classified.setUser(user);
				classified.setPrice(price);
			}

			if(classifiedOrder.getTitle() != null && classifiedOrder.getTitle().trim().length() > 0)
				classified.setTitle(classifiedOrder.getTitle());

			if(classifiedOrder.getContactName() != null && classifiedOrder.getContactName().trim().length() > 0)
				classified.setContactName(classifiedOrder.getContactName());

			if(classifiedOrder.getContactPhoneNumber() != null && classifiedOrder.getContactPhoneNumber().trim().length() > 0)
				classified.setContactPhoneNumber(classifiedOrder.getContactPhoneNumber());

			if(classifiedOrder.getContactFax() != null && classifiedOrder.getContactFax().trim().length() > 0)
				classified.setContactFax(classifiedOrder.getContactFax());

			if(classifiedOrder.getContactEmail() != null && classifiedOrder.getContactEmail().trim().length() > 0)
				classified.setContactEmail(classifiedOrder.getContactEmail());

			if(classifiedOrder.getUrl() != null && classifiedOrder.getUrl().trim().length() > 0)
				classified.setUrl(classifiedOrder.getProtocol() + classifiedOrder.getUrl());

			if(classifiedOrder.getAmountDollar() != null && classifiedOrder.getAmountCent() != null)
				classified.setAmount(new Float(String.valueOf(classifiedOrder.getAmountDollar()) + "." + String.valueOf(classifiedOrder.getAmountCent())));

			if(classifiedOrder.getAddress1() != null && classifiedOrder.getAddress1().trim().length() > 0)
				address.setAddress1(classifiedOrder.getAddress1());

			if(classifiedOrder.getAddress2() != null && classifiedOrder.getAddress2().trim().length() > 0)
				address.setAddress2(classifiedOrder.getAddress2());

			if(classifiedOrder.getCity() != null && classifiedOrder.getCity().trim().length() > 0)
				address.setCity(classifiedOrder.getCity());

			if(classifiedOrder.getZipcode() != null && classifiedOrder.getZipcode().trim().length() > 0)
				address.setZipcode(classifiedOrder.getZipcode());

			if(classifiedOrder.getLatitude() != null)
				address.setLatitude(classifiedOrder.getLatitude());

			if(classifiedOrder.getLongitude() != null)
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

			if(id != null && id > 0)
				classified.setAddress(address);

			if(classifiedOrder.getSummarydesc() != null && classifiedOrder.getSummarydesc().trim().length() > 0)
				classified.setSummaryDescription(classifiedOrder.getSummarydesc());

			if(classifiedOrder.getDetailDescription() != null && classifiedOrder.getDetailDescription().trim().length() > 0)
				classified.setDetailDescription(classifiedOrder.getDetailDescription());

			if(classifiedOrder.getKeyword() != null && classifiedOrder.getKeyword().trim().length() > 0)
				classified.setKeyword(classifiedOrder.getKeyword());

			if(classifiedOrder.getCategories() != null && classifiedOrder.getCategories().length > 0) {
				Map<Long, Category> categoryMap = getCategories();
				for(String categoriesStr : classifiedOrder.getCategories()) {
					classified.addCategory(categoryMap.get(Long.valueOf(categoriesStr)));
				}
			}

			if(classifiedOrder.getPromotionCode() != null && classifiedOrder.getPromotionCode().trim().length() > 0) {
				PromotionCode promotionCode = orderService.getPromotionCode(classifiedOrder.getPromotionCode());
				classified.setPromotionCode(promotionCode);
			}
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
		} catch (ValidationException e) {
			throw new ValidationException(e);
		} finally {
			context.getFlowScope().put("classifiedOrder", classifiedOrder);
		}
	}

	@SuppressWarnings("unchecked")
	@Transactional
	public ClassifiedOrder getClassifiedOrderById(RequestContext context, Long id) {
		ClassifiedOrder classifiedOrder = null;
		ClassifiedOrder classifiedOrderOld = (ClassifiedOrder) context.getFlowScope().get("classifiedOrder");

		try {
			if(id != null && id > 0) {
				Classified classified = getClassifiedById(id);
				if(classified != null) {
					classifiedOrder = new ClassifiedOrder();
					BeanUtils.copyProperties(classifiedOrder, classified);

					if(classified.getUrl() != null && classified.getUrl().trim().length() > 0) {
						classifiedOrder.setProtocol(classified.getUrl().substring(0, classified.getUrl().indexOf("//")));
						classifiedOrder.setUrl(classified.getUrl().substring(classified.getUrl().indexOf("//"), classified.getUrl().length()));
					}

					if(classified.getSummaryDescription() != null && classified.getSummaryDescription().trim().length() > 0)
						classifiedOrder.setSummarydesc(classified.getSummaryDescription());

					if(classified.getAmount() != null) {
						classifiedOrder.setAmountDollar(Integer.valueOf((new DecimalFormat("#").format(classified.getAmount()))));
						String amount = new DecimalFormat(".##").format(classified.getAmount());
						classifiedOrder.setAmountCent(Integer.valueOf((amount.substring(amount.indexOf(".") + 1))));
					}

					if(classified.getAddress() != null) {
						BeanUtils.copyProperties(classifiedOrder, classified.getAddress());

						if(classified.getAddress().getState() != null)
							classifiedOrder.setState(classified.getAddress().getState().getCode());

						if(classified.getAddress().getCountry() != null)
							classifiedOrder.setCountry(classified.getAddress().getCountry().getCode());
					}

					if(classified.getImageUpload() != null) {
						classifiedOrder.setImageUploadFilename(classified.getImageUpload().getClientFilename());
					}

					if(classified.getCategory() != null && classified.getCategory().size() > 0) {
						String[] strArr = new String[classified.getCategory().size()];
						int i = 0;
						for(Category category : classified.getCategory()) {
							strArr[i++] = String.valueOf(category.getId());
						}
						classifiedOrder.setCategories(strArr);
					}

					if(classifiedOrderOld != null) {
						if(classifiedOrderOld.getErrorConstraintViolation() != null && classifiedOrderOld.getErrorConstraintViolation().size() > 0)
							classifiedOrder.setErrorConstraintViolation(classifiedOrderOld.getErrorConstraintViolation());
					}
				}
			} else if(classifiedOrderOld != null) {
				classifiedOrder = classifiedOrderOld;
			}
		} catch (IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}
		return classifiedOrder;
	}

	@SuppressWarnings("unchecked")
	public List<Classified> getClassifiedByUser(User user) {
		return em.createQuery("SELECT c FROM Classified c WHERE c.user = :user").setParameter("user", user).getResultList();
	}

	public Classified getClassifiedById(Long id) {
		Classified classified = null;
		try {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Classified> classifiedQueries = cb.createQuery(Classified.class);
			Root<Classified> classifiedRoot = classifiedQueries.from(Classified.class);
			classifiedRoot.fetch("category");
			classifiedQueries.where(cb.equal(classifiedRoot.get("id"), id));

			classified =  em.createQuery(classifiedQueries).getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}

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

	@SuppressWarnings("unchecked")
	public List<Object[]> getCategoriesById(Long id) {
		return em.createNativeQuery("SELECT c.id, c.name, count(*)"
				+ " FROM Category c"
				+ " INNER JOIN classified_category cc ON cc.category_id = c.id"
				+ " WHERE cc.classified_id = :id"
				+ " GROUP BY c.name"
				+ " HAVING count(*)  > 0")
				.setParameter("id", id)
				.getResultList();
	}

	public Page<Classified> getClassifiedByCategoryOrderByPriceType(Long categoryId, Integer pageNumber, Integer numberPerPage) {
		PageRequest request = new PageRequest(pageNumber - 1, numberPerPage);
		return classifiedRepository.findClassifiedByCategoryOrderByPriceType(categoryId, request);
	}

	public Page<Classified> getClassifiedByCategoryOrderByTitle(Long categoryId, Integer pageNumber, Integer numberPerPage) {
		PageRequest request = new PageRequest(pageNumber - 1, numberPerPage);
		return classifiedRepository.findClassifiedByCategoryOrderByTitle(categoryId, request);
	}
}
