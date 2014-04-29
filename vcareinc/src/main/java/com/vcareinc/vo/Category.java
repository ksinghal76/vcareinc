package com.vcareinc.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.stereotype.Controller;

@Controller
@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum CategoryType{LISTING, EVENT, CLASSIFIED, ARTICLE}

	@Id
	@GeneratedValue
	private Long id;
	
	private String name;
	private String description;
	private CategoryType categoryType;
	private Category parentCategory;
	private Boolean enable;
	private Timestamp created;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public CategoryType getCategoryType() {
		return categoryType;
	}
	public void setCategoryType(CategoryType categoryType) {
		this.categoryType = categoryType;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public Boolean getEnable() {
		return enable;
	}
	public void setEnable(Boolean enable) {
		this.enable = enable;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description="
				+ description + ", categoryType=" + categoryType
				+ ", parentCategory=" + parentCategory + ", enable=" + enable
				+ ", created=" + created + "]";
	}
}
