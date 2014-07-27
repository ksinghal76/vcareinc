package com.vcareinc.services;

import java.util.List;
import java.util.Map;

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
import com.vcareinc.models.ArticleOrder;
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
			if(id != null && id > 0)
				articles.setId(id);
			articles.setUser(user);
			articles.setPrice(price);
			articles.setTitle(articleOrder.getTitle());
			articles.setAuthor(articleOrder.getAuthor());
			articles.setUrl(articleOrder.getProtocol() + articleOrder.getUrl());
			articles.setPublicationDate(articleOrder.getPublicationDate());

			articles.setDescription(articleOrder.getDescription());
			articles.setContent(articleOrder.getContent());
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
		} catch (ValidationException e) {
			throw new ValidationException(e);
		}
	}

	public ArticleOrder getArticleOrderById(Long id) {
		ArticleOrder articleOrder = null;
		Articles articles = getArticlesById(id);
		if(articles != null) {
			articleOrder = new ArticleOrder();
			BeanUtils.copyProperties(articles, articleOrder);

			if(articles.getImageUpload() != null) {
				articleOrder.setImageUploadFilename(articles.getImageUpload().getClientFilename());
			}
		}
		return articleOrder;
	}

	@SuppressWarnings("unchecked")
	public List<Articles> getArticlesByUser(User user) {
		return em.createQuery("SELECT a FROM Articles a WHERE a.user = :user").setParameter("user", user).getResultList();
	}

	@SuppressWarnings("unchecked")
	public Articles getArticlesById(Long id) {
		Articles articles = null;
		List<Articles> articlesList = em.createQuery("SELECT a FROM Articles a WHERE a.id = :id").setParameter("id", id).getResultList();
		if(articlesList != null && articlesList.size() > 0)
			articles = articlesList.get(0);
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
