package com.vcareinc.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
@Constraint(validatedBy=BillingOrderListsValidator.class)
public @interface BillingOrderLists {
	String message() default "Please select at least one";

	String[] listingList();

	String[] eventList();

	String [] articleList();

	String[] classifiedList();

	@SuppressWarnings("rawtypes")
	Class[] groups() default {};

	@SuppressWarnings("rawtypes")
	Class[] payload() default {};

	@interface List {
		BillingOrderLists[] value();
	}
}
