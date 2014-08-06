package com.vcareinc.services;

import java.lang.reflect.InvocationTargetException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

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

import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.PriceType;
import com.vcareinc.constants.StatusType;
import com.vcareinc.exceptions.CommonException;
import com.vcareinc.exceptions.DBException;
import com.vcareinc.exceptions.ValidationException;
import com.vcareinc.models.ArticleOrder;
import com.vcareinc.utils.DateUtils;
import com.vcareinc.vo.Articles;
import com.vcareinc.vo.Category;
import com.vcareinc.vo.FileUpload;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.PromotionCode;
import com.vcareinc.vo.User;

@Controller
public class ArticleService extends BaseService<ArticleOrder> {

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
	public void saveArticle(RequestContext context, ArticleOrder articleOrder) throws CommonException {
		log.info(articleOrder.toString());
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = userService.getUserProfile(request);
		OptionType optionType = OptionType.valueOf((String) context.getFlowScope().get("optionType"));
		PriceType priceType = PriceType.valueOf((String) context.getFlowScope().get("priceType"));
		Price price = orderService.getPriceByType(optionType, priceType);
		Long id = null;
		if(context.getFlowScope().get("optionTypeId") != null)
			id = (Long) context.getFlowScope().get("optionTypeId");

		Articles articles = new Articles();
		try {
			validate(articleOrder);
			if(id != null && id > 0) {
				articles.setId(id);
				if(articles.getCategory() != null && articles.getCategory().size() > 0) {
					articles.deleteAllCategory(articles.getCategory());
				}
			} else {
				articles.setUser(user);
				articles.setPrice(price);
			}

			if(articleOrder.getTitle() != null && articleOrder.getTitle().trim().length() > 0)
				articles.setTitle(articleOrder.getTitle());

			if(articleOrder.getAuthor() != null && articleOrder.getAuthor().trim().length() > 0)
				articles.setAuthor(articleOrder.getAuthor());

			if(articleOrder.getUrl() != null && articleOrder.getUrl().trim().length() > 0)
				articles.setUrl(articleOrder.getProtocol() + articleOrder.getUrl());

			if(articleOrder.getPublicationDateStr() != null && articleOrder.getPublicationDateStr().trim().length() > 0)
				articles.setPublicationDate(DateUtils.getTimestamp(articleOrder.getPublicationDateStr()));

			if(articleOrder.getDescription() != null && articleOrder.getDescription().trim().length() > 0)
				articles.setDescription(articleOrder.getDescription());

			if(articleOrder.getContent() != null && articleOrder.getContent().trim().length() > 0)
				articles.setContent(articleOrder.getContent());

			if(articleOrder.getKeyword() != null && articleOrder.getKeyword().trim().length() > 0)
				articles.setKeyword(articleOrder.getKeyword());


			if(articleOrder.getCategories() != null && articleOrder.getCategories().length > 0) {
				Map<Long, Category> categoryMap = getCategories();
				for(String categoriesStr : articleOrder.getCategories()) {
					articles.addCategory(categoryMap.get(Long.valueOf(categoriesStr)));
				}
			}

			if(articleOrder.getPromotionCode() != null && articleOrder.getPromotionCode().trim().length() > 0) {
				PromotionCode promotionCode = orderService.getPromotionCode(articleOrder.getPromotionCode());
				articles.setPromotionCode(promotionCode);
			}

			em.persist(articles);

			if(articleOrder.getImageUpload() != null && articleOrder.getImageUpload().getSize() > 0) {
				String filename = getFileName(articles.getId(), OptionType.EVENT, articleOrder.getImageUpload());
				FileUpload fileUpload = saveFileUpload(articles.getId(), filename, articleOrder.getImageUpload());
				articles.setImageUpload(fileUpload);
			}
			if(PriceType.valueOf(priceType.name()).equals(PriceType.STUDENTS))
				articles.setStatus(StatusType.ACTIVE);
			else
				articles.setStatus(StatusType.PENDING);
			em.persist(articles);

			clearObject(articleOrder);
		} catch (ValidationException | ParseException e) {
			throw new ValidationException(e);
		} finally {
			context.getFlowScope().put("articleOrder", articleOrder);
		}
	}

	@SuppressWarnings("unchecked")
	public ArticleOrder getArticleOrderById(RequestContext context, Long id) {
		ArticleOrder articleOrder = null;
		ArticleOrder articleOrderOld = (ArticleOrder) context.getFlowScope().get("articleOrder");
		try {
			if(id != null && id > 0) {
				Articles articles = getArticlesById(id);
				if(articles != null) {
					articleOrder = new ArticleOrder();
					BeanUtils.copyProperties(articles, articleOrder);

					if(articles.getPublicationDate() != null) {
						articleOrder.setPublicationDateStr(DateUtils.getStringforDate(articles.getPublicationDate()));
					}

					if(articles.getImageUpload() != null) {
						articleOrder.setImageUploadFilename(articles.getImageUpload().getClientFilename());
					}

					if(articles.getCategory() != null && articles.getCategory().size() > 0) {
						String[] strArr = new String[articles.getCategory().size()];
						int i = 0;
						for(Category category : articles.getCategory()) {
							strArr[i++] = String.valueOf(category.getId());
						}
						articleOrder.setCategories(strArr);
					}

					if(articleOrderOld != null) {
						if(articleOrderOld.getErrorConstraintViolation() != null && articleOrderOld.getErrorConstraintViolation().size() > 0)
							articleOrder.setErrorConstraintViolation(articleOrderOld.getErrorConstraintViolation());
					}
				}
			} else if(articleOrderOld != null) {
				articleOrder = articleOrderOld;
			}
		} catch (InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}

		return articleOrder;
	}

	@SuppressWarnings("unchecked")
	public List<Articles> getArticlesByUser(User user) {
		return em.createQuery("SELECT a FROM Articles a WHERE a.user = :user").setParameter("user", user).getResultList();
	}

	public Articles getArticlesById(Long id) {
		Articles articles = null;
		try {

			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Articles> articleQueries = cb.createQuery(Articles.class);
			Root<Articles> articleRoot = articleQueries.from(Articles.class);
			articleRoot.fetch("category");
			articleQueries.where(cb.equal(articleRoot.get("id"), id));

			articles =  em.createQuery(articleQueries).getSingleResult();
		} catch (NoResultException e) {
			e.printStackTrace();
		}
		return articles;
	}

	@SuppressWarnings("unchecked")
	public List<Articles> getPendingArticles(RequestContext context) {
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = userService.getUserProfile(request);
		return em.createQuery("SELECT a FROM Articles a WHERE a.status = :status and a.user = :user")
				.setParameter("status", StatusType.PENDING)
				.setParameter("user", user)
				.getResultList();
	}

	public void changeActiveStatusById(String[] idLst) {
		if(idLst != null && idLst.length > 0) {
			for(String id : idLst) {
				Articles articles = getArticlesById(Long.valueOf(id));
				articles.setStatus(StatusType.ACTIVE);
				em.persist(articles);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<Articles> getTopArticlesLists(Integer numberOfLists) {
		return em.createQuery("SELECT a FROM Articles a WHERE a.status = :status")
			.setParameter("status", StatusType.ACTIVE)
			.setMaxResults(numberOfLists)
			.getResultList();
	}
}
