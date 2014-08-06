package com.vcareinc.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;

import com.vcareinc.constants.StatusType;

@Entity
public class Classified implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User user;

	@ManyToOne
	private Price price;
	private String title;
	private String contactName;
	private String contactPhoneNumber;
	private String contactFax;

	@Email(message="Email is invalid")
	private String contactEmail;

	private String url;
	private Float amount;

	@OneToOne
	private Address address;

	@OneToOne
	private FileUpload imageUpload;
	private String summaryDescription;
	private String detailDescription;
	private String keyword;
	private PromotionCode promotionCode;
	private StatusType status;

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

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactPhoneNumber() {
		return contactPhoneNumber;
	}

	public void setContactPhoneNumber(String contactPhoneNumber) {
		this.contactPhoneNumber = contactPhoneNumber;
	}

	public String getContactFax() {
		return contactFax;
	}

	public void setContactFax(String contactFax) {
		this.contactFax = contactFax;
	}

	public String getContactEmail() {
		return contactEmail;
	}

	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Float getAmount() {
		return amount;
	}

	public void setAmount(Float amount) {
		this.amount = amount;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
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

	public String getDetailDescription() {
		return detailDescription;
	}

	public void setDetailDescription(String detailDescription) {
		this.detailDescription = detailDescription;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public PromotionCode getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(PromotionCode promotionCode) {
		this.promotionCode = promotionCode;
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
