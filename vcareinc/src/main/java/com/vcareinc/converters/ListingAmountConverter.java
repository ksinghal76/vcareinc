package com.vcareinc.converters;

import java.text.DecimalFormat;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("listingAmountConverter")
public class ListingAmountConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		return getAmount(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		return getAmount(value);
	}

	private String getAmount(Object value) {
		if(value == null)
			return null;

		Float amount = Float.valueOf(String.valueOf(value));

		if(amount > 0) {
			return "<small>$</small>" + String.valueOf(amount).substring(0, String.valueOf(amount).indexOf("."))
					+ "<small>" + String.valueOf(new DecimalFormat("0.00").format(amount)).substring(String.valueOf(new DecimalFormat("0.00").format(amount)).indexOf("."), String.valueOf(new DecimalFormat("0.00").format(amount)).length())
					+ "</small><span> / month</span>";
		} else {
			return "FREE";
		}
	}

}
