package com.vcareinc.models;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.vcareinc.constants.DiscountType;

@SuppressWarnings("rawtypes")
@Controller
public class DealOrder extends BaseModel {

	private static final long serialVersionUID = 1L;

	private String title;
	private String listingTitle;
	private String summaryDescription;
	private String description;
	private String conditions;
	private String keyword;
	private String startDate;
	private String endDate;
	private Integer visibility;
	private String startHour;
	private String startMinute;
	private String startAmPm;
	private String endHour;
	private String endMinute;
	private String endAmPm;
	private Integer priceNumber;
	private Integer priceDecimal;
	private DiscountType discountType;
	private Integer discountPriceNumber;
	private Integer discountPriceDecimal;
	private Integer totalDeal;
	private MultipartFile imageUpload;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getListingTitle() {
		return listingTitle;
	}
	public void setListingTitle(String listingTitle) {
		this.listingTitle = listingTitle;
	}
	public String getSummaryDescription() {
		return summaryDescription;
	}
	public void setSummaryDescription(String summaryDescription) {
		this.summaryDescription = summaryDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getConditions() {
		return conditions;
	}
	public void setConditions(String conditions) {
		this.conditions = conditions;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public Integer getVisibility() {
		return visibility;
	}
	public void setVisibility(Integer visibility) {
		this.visibility = visibility;
	}
	public String getStartHour() {
		return startHour;
	}
	public void setStartHour(String startHour) {
		this.startHour = startHour;
	}
	public String getStartMinute() {
		return startMinute;
	}
	public void setStartMinute(String startMinute) {
		this.startMinute = startMinute;
	}
	public String getStartAmPm() {
		return startAmPm;
	}
	public void setStartAmPm(String startAmPm) {
		this.startAmPm = startAmPm;
	}
	public String getEndHour() {
		return endHour;
	}
	public void setEndHour(String endHour) {
		this.endHour = endHour;
	}
	public String getEndMinute() {
		return endMinute;
	}
	public void setEndMinute(String endMinute) {
		this.endMinute = endMinute;
	}
	public String getEndAmPm() {
		return endAmPm;
	}
	public void setEndAmPm(String endAmPm) {
		this.endAmPm = endAmPm;
	}
	public Integer getPriceNumber() {
		return priceNumber;
	}
	public void setPriceNumber(Integer priceNumber) {
		this.priceNumber = priceNumber;
	}
	public Integer getPriceDecimal() {
		return priceDecimal;
	}
	public void setPriceDecimal(Integer priceDecimal) {
		this.priceDecimal = priceDecimal;
	}
	public DiscountType getDiscountType() {
		return discountType;
	}
	public void setDiscountType(DiscountType discountType) {
		this.discountType = discountType;
	}
	public Integer getDiscountPriceNumber() {
		return discountPriceNumber;
	}
	public void setDiscountPriceNumber(Integer discountPriceNumber) {
		this.discountPriceNumber = discountPriceNumber;
	}
	public Integer getDiscountPriceDecimal() {
		return discountPriceDecimal;
	}
	public void setDiscountPriceDecimal(Integer discountPriceDecimal) {
		this.discountPriceDecimal = discountPriceDecimal;
	}
	public Integer getTotalDeal() {
		return totalDeal;
	}
	public void setTotalDeal(Integer totalDeal) {
		this.totalDeal = totalDeal;
	}
	public MultipartFile getImageUpload() {
		return imageUpload;
	}
	public void setImageUpload(MultipartFile imageUpload) {
		this.imageUpload = imageUpload;
	}
}
