package com.vcareinc.controllers;

import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Controller;

import com.vcareinc.constants.DateRange;
import com.vcareinc.constants.DayOfWeek;
import com.vcareinc.constants.DiscountType;
import com.vcareinc.constants.MonthOfYear;
import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.ListingType;
import com.vcareinc.constants.PageType;
import com.vcareinc.constants.PaymentMethodType;
import com.vcareinc.constants.PriceType;
import com.vcareinc.constants.RoleType;
import com.vcareinc.constants.SortingOrder;
import com.vcareinc.constants.WeekOfMonth;

@Controller
@ManagedBean(name="conversionManagedBean")
public class ConversionManagedBean {

	public OptionType[] getOptionTypes() {
		return OptionType.values();
	}

	public ListingType[] getListingTypes() {
		return ListingType.values();
	}

	public PriceType[] getPriceTypes() {
		return PriceType.values();
	}

	public PaymentMethodType[] getPaymentMethodTypes() {
		return PaymentMethodType.values();
	}

	public PageType[] getPageType() {
		return PageType.values();
	}

	public RoleType[] getRoleType() {
		return RoleType.values();
	}

	public DiscountType[] getDiscountType() {
		return DiscountType.values();
	}

	public MonthOfYear[] getMonthOfYear() {
		return MonthOfYear.values();
	}

	public DayOfWeek[] getDayOfWeek() {
		return DayOfWeek.values();
	}

	public WeekOfMonth[] getWeekOfMonth() {
		return WeekOfMonth.values();
	}

	public DateRange[] getDateRange() {
		return DateRange.values();
	}
	
	public SortingOrder[] getSortingOrder() {
		return SortingOrder.values();
	}
}
