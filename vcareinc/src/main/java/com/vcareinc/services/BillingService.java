package com.vcareinc.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.webflow.execution.RequestContext;

import com.vcareinc.exceptions.CommonException;
import com.vcareinc.models.BaseModel;
import com.vcareinc.models.BillingOrder;
import com.vcareinc.vo.Articles;
import com.vcareinc.vo.Classified;
import com.vcareinc.vo.Events;
import com.vcareinc.vo.Listings;

@Controller
public class BillingService extends BaseService<BillingOrder> {

	@Autowired
	private UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@SuppressWarnings("unchecked")
	public Float totalAmount(RequestContext context, BillingOrder billOrder) throws CommonException {

		clearError(billOrder);
		Float amount = 0.0f;

		validate(billOrder);

		List<Listings> listingLst = (List<Listings>) context.getFlowScope().get("listingsList");
		List<Events> eventsLst = (List<Events>) context.getFlowScope().get("eventsList");
		List<Classified> classifiedLst = (List<Classified>) context.getFlowScope().get("classifiedList");
		List<Articles> articlesLst = (List<Articles>) context.getFlowScope().get("articleList");

		if(billOrder.getListingCheck() != null && billOrder.getListingCheck().length > 0) {
			for(String listingsId : billOrder.getListingCheck()) {
				for(Listings listings : listingLst) {
					if(listings.getId().equals(Long.valueOf(listingsId))) {
						amount += listings.getPrice().getAmount();
						break;
					}
				}
			}
		}

		if(billOrder.getEventCheck() != null && billOrder.getEventCheck().length > 0) {
			for(String eventsId : billOrder.getEventCheck()) {
				for(Events events : eventsLst) {
					if(events.getId().equals(Long.valueOf(eventsId))) {
						amount += events.getPrice().getAmount();
						break;
					}
				}
			}
		}

		if(billOrder.getClassifiedCheck() != null && billOrder.getClassifiedCheck().length > 0) {
			for(String classifiedId : billOrder.getClassifiedCheck()) {
				for(Classified classified : classifiedLst) {
					if(classified.getId().equals(Long.valueOf(classifiedId))) {
						amount += classified.getPrice().getAmount();
						break;
					}
				}
			}
		}

		if(billOrder.getArticleCheck() != null && billOrder.getArticleCheck().length > 0) {
			for(String articlesId : billOrder.getArticleCheck()) {
				for(Articles articles : articlesLst) {
					if(articles.getId().equals(Long.valueOf(articlesId))) {
						amount += articles.getPrice().getAmount();
						break;
					}
				}
			}
		}

		return amount;
	}

	@Override
	public void clearObject(BaseModel<BillingOrder> billingOrder) {
		billingOrder = new BaseModel<BillingOrder>();
	}
}
