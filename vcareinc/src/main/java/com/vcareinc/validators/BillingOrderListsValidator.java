package com.vcareinc.validators;

import java.lang.reflect.InvocationTargetException;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.apache.commons.beanutils.BeanUtils;

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
		int count = 0;
		try {

			ctx.disableDefaultConstraintViolation();

			for(String listingLst : listingList) {
				String listing = BeanUtils.getProperty(object, listingLst);
				if(listing != null && listing.length() > 0) {
					count++;
				}
			}

			for(String eventLst : eventList) {
				String event = BeanUtils.getProperty(object, eventLst);
				if(event != null && event.length() > 0) {
					count++;
				}
			}

			for(String articleLst : articleList) {
				String article = BeanUtils.getProperty(object, articleLst);
				if(article != null && article.length() > 0) {
					count++;
				}
			}

			for(String classifiedLst : classifiedList) {
				String classified = BeanUtils.getProperty(object, classifiedLst);
				if(classified != null && classified.length() > 0) {
					count++;
				}
			}
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			e.printStackTrace();
		}

		if(count == 0) {
			ctx.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		}

		return count > 0;
	}

}
