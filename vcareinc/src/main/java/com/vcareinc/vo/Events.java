package com.vcareinc.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;

import com.vcareinc.constants.DateRange;
import com.vcareinc.constants.MonthOfYear;
import com.vcareinc.constants.StatusType;

@Entity
public class Events implements Serializable {

	private static final long serialVersionUID = 1L;


	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Price price;
	private String title;

	@Email
	private String email;

	private String url;
	private String phoneNumber;
	private String contactName;

	@OneToOne
	private Address address;

	private Timestamp startDate;
	private Timestamp endDate;

	private Boolean recurring;
	private DateRange period;
	private MonthOfYear month;
	private MonthOfYear month2;
	private String precison;
	private String day;

	private String dayOfWeek;
	private String weekOfMonth;
	private String eventPeriod;
	private Timestamp untilDate;

	@OneToOne
	private FileUpload imageUpload;



	private String summaryDescription;
	private String description;
	private String keyword;
	private StatusType status;

	@ManyToOne
	private PromotionCode promotionCode;

	@OneToMany
	private final Set<Category> category = new HashSet<Category>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Boolean getRecurring() {
		return recurring;
	}

	public void setRecurring(Boolean recurring) {
		this.recurring = recurring;
	}

	public DateRange getPeriod() {
		return period;
	}

	public void setPeriod(DateRange period) {
		this.period = period;
	}

	public MonthOfYear getMonth() {
		return month;
	}

	public void setMonth(MonthOfYear month) {
		this.month = month;
	}

	public MonthOfYear getMonth2() {
		return month2;
	}

	public void setMonth2(MonthOfYear month2) {
		this.month2 = month2;
	}

	/**
	 * @return the precison
	 */
	public String getPrecison() {
		return precison;
	}

	/**
	 * @param precison the precison to set
	 */
	public void setPrecison(String precison) {
		this.precison = precison;
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

	public String getDayOfWeek() {
		return dayOfWeek;
	}

	public void setDayOfWeek(String dayOfWeek) {
		this.dayOfWeek = dayOfWeek;
	}

	public String getWeekOfMonth() {
		return weekOfMonth;
	}

	public void setWeekOfMonth(String weekOfMonth) {
		this.weekOfMonth = weekOfMonth;
	}

	public String getEventPeriod() {
		return eventPeriod;
	}

	public void setEventPeriod(String eventPeriod) {
		this.eventPeriod = eventPeriod;
	}

	public Timestamp getUntilDate() {
		return untilDate;
	}

	public void setUntilDate(Timestamp untilDate) {
		this.untilDate = untilDate;
	}

	public FileUpload getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(FileUpload imageUpload) {
		this.imageUpload = imageUpload;
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

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	/**
	 * @return the status
	 */
	public StatusType getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(StatusType status) {
		this.status = status;
	}

	public PromotionCode getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(PromotionCode promotionCode) {
		this.promotionCode = promotionCode;
	}

	public Set<Category> getCategory() {
		return category;
	}

	public Boolean addCategory(Category category) {
		return this.category.add(category);
	}

	public Boolean deleteCategory(Category category) {
		return this.category.remove(category);
	}

	public Boolean deleteAllCategory(Set<Category> categories) {
		return this.category.removeAll(categories);
	}
}
