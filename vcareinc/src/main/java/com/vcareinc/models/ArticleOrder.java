package com.vcareinc.models;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.web.multipart.MultipartFile;

import com.vcareinc.validators.PromotionCode;

@SuppressWarnings("rawtypes")
@Controller
public class ArticleOrder extends BaseModel {

	private static final long serialVersionUID = 1L;

	@NotNull(message="Title is required")
	private String title;

	private String author;
	private String protocol;
	private String url;
	private Timestamp publicationDate;

	private MultipartFile imageUpload;
	private String imageUploadFilename;

	@NotNull(message="Abstract is required")
	private String description;
	private String content;
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

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
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

	public Timestamp getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(Timestamp publicationDate) {
		this.publicationDate = publicationDate;
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
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
