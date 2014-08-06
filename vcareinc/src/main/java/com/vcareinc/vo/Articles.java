package com.vcareinc.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.vcareinc.constants.StatusType;

@Entity
public class Articles implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	@ManyToOne
	private User user;

	private String title;

	private String author;
	private String url;
	private Timestamp publicationDate;

	@OneToOne
	private FileUpload imageUpload;

	private String description;
	private String content;
	private String keyword;
	private StatusType status;

	private PromotionCode promotionCode;

	@ManyToOne
	private Price price;

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

	public FileUpload getImageUpload() {
		return imageUpload;
	}

	public void setImageUpload(FileUpload imageUpload) {
		this.imageUpload = imageUpload;
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

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
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
