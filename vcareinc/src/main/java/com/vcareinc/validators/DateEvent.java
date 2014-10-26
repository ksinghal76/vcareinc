package com.vcareinc.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;

@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE, ElementType.ANNOTATION_TYPE})
@Constraint(validatedBy=DateEventValidator.class)
public @interface DateEvent {

	String message() default "Please enter valid date";
	String startDate();
	String endDate();
	String recurring();
	String period();
	String precision();
	String day();
	String month();
	String[] dayOfWeekcb();
	String[] weekOfMonth();
	String month2();
	String eventPeriod();
	String untilDate();

	@SuppressWarnings("rawtypes")
	Class[] groups() default {};

	@SuppressWarnings("rawtypes")
	Class[] payload() default {};
}
