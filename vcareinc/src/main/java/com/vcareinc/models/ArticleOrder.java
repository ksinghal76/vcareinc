package com.vcareinc.models;

import java.sql.Timestamp;

import javax.validation.constraints.NotNull;

import org.springframework.web.multipart.MultipartFile;

import com.vcareinc.vo.PromotionCode;

@SuppressWarnings("rawtypes")
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

	private PromotionCode promotionCode;

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

	public PromotionCode getPromotionCode() {
		return promotionCode;
	}

	public void setPromotionCode(PromotionCode promotionCode) {
		this.promotionCode = promotionCode;
	}
}
