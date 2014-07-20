package com.vcareinc.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class BillingOrderListsValidator implements
		ConstraintValidator<BillingOrderLists, Object> {

	String message;
	String[] listingList;
	String[] eventList;
	String[] articleList;
	String[] classifiedList;

	@Override
	public void initialize(BillingOrderLists params) {
		message = params.message();
		listingList = params.listingList();
		eventList = params.eventList();
		articleList = params.articleList();
		classifiedList = params.classifiedList();
	}

	@Override
	public boolean isValid(Object object,
			ConstraintValidatorContext ctx) {
		ctx.disableDefaultConstraintViolation();

//		if((billingOrder.getListingCheck() == null || billingOrder.getListingCheck().length <= 0)
//				&& (billingOrder.getEventCheck() == null || billingOrder.getEventCheck().length <= 0)
//				&& (billingOrder.getArticleCheck() == null || billingOrder.getArticleCheck().length <= 0)
//				&& (billingOrder.getClassifiedCheck() == null || billingOrder.getClassifiedCheck().length <= 0))
//			return false;

		ctx.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		return true;
	}

}
