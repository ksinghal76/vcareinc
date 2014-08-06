package com.vcareinc.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.PageType;
import com.vcareinc.constants.PriceType;
import com.vcareinc.constants.StatusType;
import com.vcareinc.controllers.ConversionManagedBean;
import com.vcareinc.models.ListingOrder;
import com.vcareinc.models.Order;
import com.vcareinc.vo.Category;
import com.vcareinc.vo.Country;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.PromotionCode;
import com.vcareinc.vo.State;

@SuppressWarnings("rawtypes")
@Controller
public class OrderService extends BaseService {

	private static final Logger log = Logger.getLogger(OrderService.class);

	@Autowired
	private ConversionManagedBean conversionManagedBean;

	public ConversionManagedBean getConversionManagedBean() {
		return conversionManagedBean;
	}

	public void setConversionManagedBean(ConversionManagedBean conversionManagedBean) {
		this.conversionManagedBean = conversionManagedBean;
	}

	public Order initializeOrder(String optionType) {
		Order order = new Order();
		OptionType opTy = OptionType.valueOf(optionType);
		order.setOptionType(opTy);

		List<Price> priceList = getPriceByType(opTy);
		if(priceList != null && priceList.size() > 0)
			order.setPriceList(priceList);

		return order;
	}

	public Order initializeOrder(String optionType, String priceType) {
		Order order = new Order();
		OptionType opTy = OptionType.valueOf(optionType);
		PriceType pcTy = PriceType.valueOf(priceType);

		order.setOptionType(opTy);
		order.setPriceType(pcTy);
		Price price = getPriceByType(opTy, pcTy);
		if(price != null)
			order.setPrice(price);

		List<Category> categoryList = getCategoryByType(opTy);
		if(categoryList != null && categoryList.size() > 0) {
			Map<Category, List<Category>> categoryMap = new HashMap<Category, List<Category>>();
			for(Category category : categoryList) {
				if(category.getParentCategory() != null) {
					categoryMap.get(category.getParentCategory()).add(category);
				}
				else {
					List<Category> catList = new ArrayList<Category>();
					catList.add(category);
					categoryMap.put(category, catList);
				}
			}
			order.setCategoryMap(categoryMap);
		}

		List<Country> countryList = getCountry();
		if(countryList != null && countryList.size() > 0)
			order.setCountryList(countryList);

		List<State> stateList = getState();
		if(stateList != null && stateList.size() > 0)
			order.setStateList(stateList);
		return order;
	}

	@SuppressWarnings("unchecked")
	public List<Category> getCategoryByType(OptionType optionType) {
		return em.createQuery("SELECT c FROM Category c WHERE c.optionType = :optionType order by c.id")
				.setParameter("optionType", optionType).getResultList();
	}

	@SuppressWarnings("unchecked")
	public Map<String, List<Price>> getAllPriceByType() {
		Map<String, List<Price>> priceMap = null;
		OptionType[] optionTypeArr = this.conversionManagedBean.getOptionTypes();
		if(optionTypeArr != null && optionTypeArr.length > 0) {
			priceMap = new HashMap<String, List<Price>>();
			for(OptionType optionType: optionTypeArr) {
				List<Price> priceLst = em.createQuery("SELECT p FROM Price p WHERE p.optionType = :optionType")
						.setParameter("optionType", optionType)
						.getResultList();

				if(priceLst != null && priceLst.size() > 0) {
					priceMap.put(optionType.name(), priceLst);
				}
			}
		}
		return priceMap;
	}

	@SuppressWarnings("unchecked")
	public List<Price> getPriceByType(OptionType optionType) {
		List<Price> priceLst = null;
		priceLst = em.createQuery("SELECT p FROM Price p WHERE p.optionType = :optionType")
				.setParameter("optionType", optionType)
				.getResultList();
		return priceLst;
	}

	@SuppressWarnings("unchecked")
	public Price getPriceByType(OptionType optionType, PriceType priceType) {
		Price price = null;
		List<Price> priceList = em.createQuery("SELECT p FROM Price p WHERE p.optionType = :optionType and p.priceType = :priceType")
				.setParameter("optionType", optionType)
				.setParameter("priceType", priceType)
				.getResultList();
		if(priceList != null && priceList.size() > 0)
			price = priceList.get(0);
		return price;
	}

	public PageType getPageType() {
		return PageType.ORDER;
	}

	public Boolean isPageType(PageType pageType) {
		return pageType.equals(PageType.ORDER);
	}

	public String getPageType(String optionType) {
		String pageType = null;
		for(OptionType ot: conversionManagedBean.getOptionTypes()) {
			if(ot.name().equals(optionType))
				pageType = ot.name();
		}
		return pageType;
	}

	public Boolean isLevelRequired(OptionType optionType, PriceType priceType) {
		Boolean isLevelReq = Boolean.FALSE;
		if(priceType == null) {
			isLevelReq = optionType.getLevelRequired();
		}
		return isLevelReq;
	}

	public Boolean isOptionTypeIdExists(Integer id) {
		if(id != null && id > 0)
			return Boolean.TRUE;
		else
			return Boolean.FALSE;
	}

	@SuppressWarnings("unchecked")
	public List<Country> getCountry() {
		return em.createQuery("SELECT c FROM Country c").getResultList();
	}

	@SuppressWarnings("unchecked")
	public Country getCountryById(Long id) {
		Country country = null;
		List<Country> countryList = em.createQuery("SELECT c FROM Country c WHERE c.id = :id").setParameter("id", id).getResultList();
		if(countryList != null && countryList.size() > 0)
			country = countryList.get(0);
		return country;
	}

	@SuppressWarnings("unchecked")
	public List<State> getState() {
		return em.createQuery("SELECT s FROM State s").getResultList();
	}

	@SuppressWarnings("unchecked")
	public State getStateById(Long id) {
		State state = null;
		List<State> stateList = em.createQuery("SELECT s FROM State s WHERE s.id = :id").setParameter("id", id).getResultList();
		if(stateList != null && stateList.size() > 0)
			state = stateList.get(0);
		return state;
	}

	public Boolean isPromotionCodeExists(String promotionCodeStr) {
		PromotionCode promotionCode = getPromotionCode(promotionCodeStr);
		if(promotionCode != null)
			return Boolean.TRUE;

		return Boolean.FALSE;
	}

	@SuppressWarnings("unchecked")
	public PromotionCode getPromotionCode(String promotionCodeStr) {
		PromotionCode promotionCode = null;
		List<PromotionCode> promotionCodeLst = em.createQuery("SELECT p FROM PromotionCode p where p.code = :code and active = :active")
				.setParameter("code", promotionCodeStr)
				.setParameter("active", StatusType.ACTIVE)
				.getResultList();

		if(promotionCodeLst != null && promotionCodeLst.size() > 0)
			promotionCode = promotionCodeLst.get(0);

		return promotionCode;
	}

	@SuppressWarnings("unchecked")
	public List<Object[]> getAllCategories() {
		return em.createNativeQuery("SELECT c.id, c.name, \"LISTING\" as optionType, count(*)"
								+ " FROM category c"
								+ " inner join listings_category lc on lc.category_id = c.id"
								+ " group by c.id, c.name, optionType"
								+ " having count(*) > 0"
								+ " union"
								+ " SELECT c.id, c.name, \"CLASSIFIED\" as optionType, count(*)"
								+ " FROM category c"
								+ " inner join classified_category cc on cc.category_id = c.id"
								+ " group by c.id, c.name, optionType"
								+ " having count(*) > 0"
								+ " union"
								+ " SELECT c.id, c.name, \"ARTICLE\" as optionType, count(*)"
								+ " FROM category c"
								+ " inner join articles_category ac on ac.category_id = c.id"
								+ " group by c.id, c.name, optionType"
								+ " having count(*) > 0"
								+ " union"
								+ " SELECT c.id, c.name, \"EVENT\" as optionType, count(*)"
								+ " FROM category c"
								+ " inner join events_category ec on ec.category_id = c.id"
								+ " group by c.id, c.name, optionType"
								+ " having count(*) > 0").getResultList();
	}

	public void testingOrder(RequestContext context) {
		ListingOrder listingOrder = (ListingOrder) context.getFlowScope().get("listingOrder");
		log.info("Listing Order: " + listingOrder.toString());
	}
}
