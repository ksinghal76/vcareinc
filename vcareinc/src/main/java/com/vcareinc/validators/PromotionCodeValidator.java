package com.vcareinc.validators;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.vcareinc.services.OrderService;

public class PromotionCodeValidator implements
		ConstraintValidator<PromotionCode, String> {

	String message;

	@Autowired
	private OrderService orderService;

	/**
	 * @return the orderService
	 */
	public OrderService getOrderService() {
		return orderService;
	}

	/**
	 * @param orderService the orderService to set
	 */
	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public void initialize(PromotionCode params) {
		this.message = params.message();
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext ctx) {
		ctx.disableDefaultConstraintViolation();
		if(value == null || value.trim().length() <= 0)
			return true;

		if(orderService.isPromotionCodeExists(value))
			return true;

		ctx.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		return false;
	}

}
