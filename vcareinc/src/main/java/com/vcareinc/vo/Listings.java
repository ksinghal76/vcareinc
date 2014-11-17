package com.vcareinc.vo;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import org.hibernate.validator.constraints.Email;

import com.vcareinc.constants.ListingType;
import com.vcareinc.constants.StatusType;

@Entity
public class Listings implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User user;

	private ListingType listingType;

	@ManyToOne
	private Price price;
	private String title;

	@Email(message="Email is invalid")
	private String email;

	private String url;
	private String displayUrl;
	private String phoneNumber;
	private String faxNumber;

	private String facebookPage;

	@OneToOne
	private Address address;

	@OneToOne
	private FileUpload imageUpload;

	private String videoSnippet;
	private String videoDescription;

	@OneToOne
	private FileUpload additionalFile;
	private String additionalFileDescription;

	private String summaryDescription;
	private String description;
	private String keyword;
	private String hourOfWork;
	private String location;
	private Boolean bestService;
	private Boolean bestValue;
	private StatusType status;

	@ManyToOne
	private PromotionCode promotionCode;

	@ManyToMany
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

	public ListingType getListingType() {
		return listingType;
	}

	public void setListingType(ListingType listingType) {
		this.listingType = listingType;
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

	public String getDisplayUrl() {
		return displayUrl;
	}

	public void setDisplayUrl(String displayUrl) {
		this.displayUrl = displayUrl;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getFaxNumber() {
		return faxNumber;
	}

	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}

	public String getFacebookPage() {
		return facebookPage;
	}

	public void setFacebookPage(String facebookPage) {
		this.facebookPage = facebookPage;
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

	public String getVideoSnippet() {
		return videoSnippet;
	}

	public void setVideoSnippet(String videoSnippet) {
		this.videoSnippet = videoSnippet;
	}

	public String getVideoDescription() {
		return videoDescription;
	}

	public void setVideoDescription(String videoDescription) {
		this.videoDescription = videoDescription;
	}

	public FileUpload getAdditionalFile() {
		return additionalFile;
	}

	public void setAdditionalFile(FileUpload additionalFile) {
		this.additionalFile = additionalFile;
	}

	public String getAdditionalFileDescription() {
		return additionalFileDescription;
	}

	public void setAdditionalFileDescription(String additionalFileDescription) {
		this.additionalFileDescription = additionalFileDescription;
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

	public String getHourOfWork() {
		return hourOfWork;
	}

	public void setHourOfWork(String hourOfWork) {
		this.hourOfWork = hourOfWork;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Boolean getBestService() {
		return bestService;
	}

	public void setBestService(Boolean bestService) {
		this.bestService = bestService;
	}

	public Boolean getBestValue() {
		return bestValue;
	}

	public void setBestValue(Boolean bestValue) {
		this.bestValue = bestValue;
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
