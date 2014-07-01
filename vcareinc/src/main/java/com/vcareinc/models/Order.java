package com.vcareinc.models;

import java.util.List;
import java.util.Map;

import javax.validation.constraints.NotNull;

import com.vcareinc.constants.ListingType;
import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.PaymentMethodType;
import com.vcareinc.constants.PriceType;
import com.vcareinc.vo.Category;
import com.vcareinc.vo.Country;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.State;

@SuppressWarnings("rawtypes")
public class Order extends BaseModel {

	private static final long serialVersionUID = 1L;

	private OptionType optionType;
	private PriceType priceType;

	@NotNull(message="Title is required.")
	private String title;

	private String promotionCode;
	private PaymentMethodType paymentMethodType;
	private Map<Category, List<Category>> categoryMap;
	private List<Category> categoryList;
	private List<Price> priceList;
	private List<Country> countryList;
	private List<State> stateList;
	private List<ListingType> listingTypeList;

	private Price price;

	public OptionType getOptionType() {
		return optionType;
	}

	public void setOptionType(OptionType optionType) {
		this.optionType = optionType;
	}

	public PriceType getPriceType() {
		return priceType;
	}

	public void setPriceType(PriceType priceType) {
		this.priceType = priceType;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
	}

	public PaymentMethodType getPaymentMethodType() {
		return paymentMethodType;
	}

	public void setPaymentMethodType(PaymentMethodType paymentMethodType) {
		this.paymentMethodType = paymentMethodType;
	}

	public Map<Category, List<Category>> getCategoryMap() {
		return categoryMap;
	}

	public void setCategoryMap(Map<Category, List<Category>> categoryMap) {
		this.categoryMap = categoryMap;
	}

	public List<Category> getCategoryList() {
		return categoryList;
	}

	public void setCategoryList(List<Category> categoryList) {
		this.categoryList = categoryList;
	}

	public List<Price> getPriceList() {
		return priceList;
	}

	public void setPriceList(List<Price> priceList) {
		this.priceList = priceList;
	}

	public List<Country> getCountryList() {
		return countryList;
	}

	public void setCountryList(List<Country> countryList) {
		this.countryList = countryList;
	}

	public List<State> getStateList() {
		return stateList;
	}

	public void setStateList(List<State> stateList) {
		this.stateList = stateList;
	}

	public List<ListingType> getListingTypeList() {
		return listingTypeList;
	}

	public void setListingTypeList(List<ListingType> listingTypeList) {
		this.listingTypeList = listingTypeList;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

}
