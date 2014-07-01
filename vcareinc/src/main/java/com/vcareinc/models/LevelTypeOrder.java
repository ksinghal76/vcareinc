package com.vcareinc.models;

import org.springframework.stereotype.Controller;

@SuppressWarnings("rawtypes")
@Controller
public class LevelTypeOrder extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String listingType;
	private String priceType;
	public String getListingType() {
		return listingType;
	}
	public void setListingType(String listingType) {
		this.listingType = listingType;
	}
	public String getPriceType() {
		return priceType;
	}
	public void setPriceType(String priceType) {
		this.priceType = priceType;
	}
}
