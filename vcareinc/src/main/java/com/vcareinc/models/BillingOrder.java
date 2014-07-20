package com.vcareinc.models;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;

import com.vcareinc.validators.BillingOrderLists;

@SuppressWarnings("rawtypes")
@Controller
@BillingOrderLists.List(value= {
		@BillingOrderLists(listingList="listingCheck", eventList="eventCheck", articleList="articleCheck", classifiedList="classifiedCheck")
})
public class BillingOrder extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String[] listingCheck;
	private String[] discountListing;
	private String[] eventCheck;
	private String[] discountEvent;
	private String[] classifiedCheck;
	private String[] discountClassified;
	private String[] articleCheck;
	private String[] discountArticle;

	@NotNull(message="Please select payment method")
	private String paymentMethod;

	/**
	 * @return the listingCheck
	 */
	public String[] getListingCheck() {
		return listingCheck;
	}

	/**
	 * @param listingCheck the listingCheck to set
	 */
	public void setListingCheck(String[] listingCheck) {
		this.listingCheck = listingCheck;
	}

	/**
	 * @return the discountListing
	 */
	public String[] getDiscountListing() {
		return discountListing;
	}

	/**
	 * @param discountListing the discountListing to set
	 */
	public void setDiscountListing(String[] discountListing) {
		this.discountListing = discountListing;
	}

	/**
	 * @return the eventCheck
	 */
	public String[] getEventCheck() {
		return eventCheck;
	}

	/**
	 * @param eventCheck the eventCheck to set
	 */
	public void setEventCheck(String[] eventCheck) {
		this.eventCheck = eventCheck;
	}

	/**
	 * @return the discountEvent
	 */
	public String[] getDiscountEvent() {
		return discountEvent;
	}

	/**
	 * @param discountEvent the discountEvent to set
	 */
	public void setDiscountEvent(String[] discountEvent) {
		this.discountEvent = discountEvent;
	}

	/**
	 * @return the classifiedCheck
	 */
	public String[] getClassifiedCheck() {
		return classifiedCheck;
	}

	/**
	 * @param classifiedCheck the classifiedCheck to set
	 */
	public void setClassifiedCheck(String[] classifiedCheck) {
		this.classifiedCheck = classifiedCheck;
	}

	/**
	 * @return the discountClassified
	 */
	public String[] getDiscountClassified() {
		return discountClassified;
	}

	/**
	 * @param discountClassified the discountClassified to set
	 */
	public void setDiscountClassified(String[] discountClassified) {
		this.discountClassified = discountClassified;
	}

	/**
	 * @return the articleCheck
	 */
	public String[] getArticleCheck() {
		return articleCheck;
	}

	/**
	 * @param articleCheck the articleCheck to set
	 */
	public void setArticleCheck(String[] articleCheck) {
		this.articleCheck = articleCheck;
	}

	/**
	 * @return the discountArticle
	 */
	public String[] getDiscountArticle() {
		return discountArticle;
	}

	/**
	 * @param discountArticle the discountArticle to set
	 */
	public void setDiscountArticle(String[] discountArticle) {
		this.discountArticle = discountArticle;
	}

	/**
	 * @return the paymentMethod
	 */
	public String getPaymentMethod() {
		return paymentMethod;
	}

	/**
	 * @param paymentMethod the paymentMethod to set
	 */
	public void setPaymentMethod(String paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
