package com.vcareinc.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.vcareinc.validators.PromotionCode;

@SuppressWarnings("rawtypes")
@Controller
public class ClassifiedOrder extends BaseModel {

	private static final long serialVersionUID = 1L;

	@NotNull(message="Title is required")
	@NotEmpty(message="Title is required")
	private String title;
	private String contactName;
	private String contactPhoneNumber;
	private String contactFax;

	@Email(message="Email is invalid")
	private String contactEmail;

	private String protocol;
	private String url;
	private Integer amountDollar;
	private Integer amountCent;

	private String address1;
	private String address2;
	private String zipcode;
	private String city;

	private String state;

	private Float latitude;
	private Float longitude;

	private String country;

	private MultipartFile imageUpload;
	private String imageUploadFilename;
	private String summarydesc;
	private String detailDescription;
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
	public Integer getAmountDollar() {
		return amountDollar;
	}
	public void setAmountDollar(Integer amountDollar) {
		this.amountDollar = amountDollar;
	}
	public Integer getAmountCent() {
		return amountCent;
	}
	public void setAmountCent(Integer amountCent) {
		this.amountCent = amountCent;
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
	/**
	 * @return the summarydesc
	 */
	public String getSummarydesc() {
		return summarydesc;
	}
	/**
	 * @param summarydesc the summarydesc to set
	 */
	public void setSummarydesc(String summarydesc) {
		this.summarydesc = summarydesc;
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
	/**
	 * @return the promotionCode
	 */
	public String getPromotionCode() {
		return promotionCode;
	}
	/**
	 * @param promotionCode the promotionCode to set
	 */
	public void setPromotionCode(String promotionCode) {
		this.promotionCode = promotionCode;
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
