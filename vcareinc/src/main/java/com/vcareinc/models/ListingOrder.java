	package com.vcareinc.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.primefaces.model.StreamedContent;
import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.vcareinc.constants.ContentType;
import com.vcareinc.validators.FileExtension;
import com.vcareinc.validators.PromotionCode;


@SuppressWarnings("rawtypes")
@Controller
public class ListingOrder extends BaseModel {

	private static final long serialVersionUID = 1L;

	@NotNull(message="Title is required")
	@NotEmpty(message="Title is required")
	private String title;

	@Email(message="Email is invalid")
	private String email;

	private String urlProtocol;
	private String url;
	private String displayUrl;
	private String phoneNumber;
	private String faxNumber;

	private String facebookPage;
	private String address1;
	private String address2;
	private String zipcode;
	private String city;

	private String state;

	private Float latitude;
	private Float longitude;

	private String country;

	@FileExtension(contentTypeList={ContentType.IMAGE_JPEG, ContentType.IMAGE_GIF, ContentType.IMAGE_PNG},
			message="Upload File only support .jpg, .gif, .png",
			maxFileSize=1500000,
			maxFileSizeMessage="Image File size must be less than {max}")
	private MultipartFile imageUpload;
	private String imageUploadFilename;
	private StreamedContent imageUploadStream;

	private String videoSnippet;
	private String videoDescription;

	@FileExtension(contentTypeList={ContentType.IMAGE_JPEG, ContentType.IMAGE_GIF, ContentType.IMAGE_PNG, ContentType.APPLICATION_MSWORD, ContentType.APPLICATION_PDF, ContentType.TEXT_PLAIN},
			message="Upload File only support .pdf, .doc, .txt, .jpg, .gif, .png",
			maxFileSize=1500000,
			maxFileSizeMessage="Additional File size must be less than {max}")
	private MultipartFile additionalFile;
	private String additionalFileFilename;
	private StreamedContent additionalFileStream;
	private String additionalFileDescription;

	private String summaryDescription;
	private String description;
	private String keyword;
	private String hourOfWork;
	private String location;
	private Boolean bestService;
	private Boolean bestValue;

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
	public String getUrlProtocol() {
		return urlProtocol;
	}
	public void setUrlProtocol(String urlProtocol) {
		this.urlProtocol = urlProtocol;
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
	public StreamedContent getImageUploadStream() {
		return imageUploadStream;
	}
	public void setImageUploadStream(StreamedContent imageUploadStream) {
		this.imageUploadStream = imageUploadStream;
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
	public MultipartFile getAdditionalFile() {
		return additionalFile;
	}
	public void setAdditionalFile(MultipartFile additionalFile) {
		this.additionalFile = additionalFile;
	}
	public String getAdditionalFileFilename() {
		return additionalFileFilename;
	}
	public void setAdditionalFileFilename(String additionalFileFilename) {
		this.additionalFileFilename = additionalFileFilename;
	}
	public StreamedContent getAdditionalFileStream() {
		return additionalFileStream;
	}
	public void setAdditionalFileStream(StreamedContent additionalFileStream) {
		this.additionalFileStream = additionalFileStream;
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
