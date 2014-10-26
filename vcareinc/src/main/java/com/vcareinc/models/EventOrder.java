package com.vcareinc.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.vcareinc.validators.DateEvent;
import com.vcareinc.validators.PromotionCode;

@SuppressWarnings("rawtypes")
@Controller
@DateEvent(startDate="startDate", endDate="endDate", period="period", month="month", month2="month2", dayOfWeekcb="dayOfWeekcb", weekOfMonth="weekOfMonth", eventPeriod="eventPeriod", untilDate="untilDate", day = "day", precision = "precision", recurring = "recurring")
public class EventOrder extends BaseModel {

	private static final long serialVersionUID = 1L;

	@NotNull(message="Title is required")
	@NotEmpty(message="Title is required")
	private String title;

	@Email
	private String email;

	private String protocol;
	private String url;
	private String phoneNumber;
	private String contactName;

	private String locationName;
	private String address1;
	private String address2;
	private String zipcode;
	private String city;

	private String state;

	private Float latitude;
	private Float longitude;

	private String country;

	private String startDate;

	private String endDate;
	private String startHour;
	private String startMinute;
	private String startAmPm;

	private String endHour;
	private String endMinute;
	private String endAmPm;

	private String recurring;
	private String period;
	private String month;
	private String month2;
	private String precision;
	private String day;

	private String[] dayOfWeekcb;
	private String[] weekOfMonth;
	private String eventPeriod;
	private String untilDate;

	private MultipartFile imageUpload;
	private String imageUploadFilename;

	private String summarydesc;
	private String description;
	private String keyword;

	@PromotionCode(message="Promotional Code is expired or invalid. Please try other.")
	private String promotionCode;

	@NotNull(message="You must select at least one category")
	private String[] categories;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getLocationName() {
		return locationName;
	}

	public void setLocationName(String locationName) {
		this.locationName = locationName;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public Float getLatitude() {
		return latitude;
	}

	public void setLatitude(Float latitude) {
		this.latitude = latitude;
	}

	public Float getLongitude() {
		return longitude;
	}

	public void setLongitude(Float longitude) {
		this.longitude = longitude;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
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

	public String getRecurring() {
		return recurring;
	}

	public void setRecurring(String recurring) {
		this.recurring = recurring;
	}
	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	/**
	 * @return the month
	 */
	public String getMonth() {
		return month;
	}

	/**
	 * @param month the month to set
	 */
	public void setMonth(String month) {
		this.month = month;
	}

	/**
	 * @return the month2
	 */
	public String getMonth2() {
		return month2;
	}

	/**
	 * @param month2 the month2 to set
	 */
	public void setMonth2(String month2) {
		this.month2 = month2;
	}

	/**
	 * @return the precision
	 */
	public String getPrecision() {
		return precision;
	}

	/**
	 * @param precision the precision to set
	 */
	public void setPrecision(String precision) {
		this.precision = precision;
	}

	/**
	 * @return the day
	 */
	public String getDay() {
		return day;
	}

	/**
	 * @param day the day to set
	 */
	public void setDay(String day) {
		this.day = day;
	}

	public String[] getDayOfWeekcb() {
		return dayOfWeekcb;
	}

	public void setDayOfWeekcb(String[] dayOfWeekcb) {
		this.dayOfWeekcb = dayOfWeekcb;
	}

	public String[] getWeekOfMonth() {
		return weekOfMonth;
	}

	public void setWeekOfMonth(String[] weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}

	public String getEventPeriod() {
		return eventPeriod;
	}

	public void setEventPeriod(String eventPeriod) {
		this.eventPeriod = eventPeriod;
	}

	public String getUntilDate() {
		return untilDate;
	}

	public void setUntilDate(String untilDate) {
		this.untilDate = untilDate;
	}

	public MultipartFile getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(MultipartFile imageUpload) {
		this.imageUpload = imageUpload;
	}

	public String getImageUploadFilename() {
		return imageUploadFilename;
	}

	public void setImageUploadFilename(String imageUploadFilename) {
		this.imageUploadFilename = imageUploadFilename;
	}

	public String getSummarydesc() {
		return summarydesc;
	}

	public void setSummarydesc(String summarydesc) {
		this.summarydesc = summarydesc;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}


	public String getPromotionCode() {
		return promotionCode;
	}

	/**
	 * @return the categories
	 */
	public String[] getCategories() {
		return categories;
	}

	/**
	 * @param categories the categories to set
	 */
	public void setCategories(String[] categories) {
		this.categories = categories;
	}
}
