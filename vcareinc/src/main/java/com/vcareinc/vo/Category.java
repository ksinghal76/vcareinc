package com.vcareinc.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.vcareinc.constants.OptionType;

@Entity
public class Category implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long id;

	private String name;
	private String description;
	private OptionType optionType;

	@OneToOne
	private Category parentCategory;
	private String pageTitle;
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
	public OptionType getOptionType() {
		return optionType;
	}
	public void setOptionType(OptionType optionType) {
		this.optionType = optionType;
	}
	public Category getParentCategory() {
		return parentCategory;
	}
	public void setParentCategory(Category parentCategory) {
		this.parentCategory = parentCategory;
	}
	public String getPageTitle() {
		return pageTitle;
	}
	public void setPageTitle(String pageTitle) {
		this.pageTitle = pageTitle;
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
				+ description + ", categoryType=" + optionType
				+ ", parentCategory=" + parentCategory + ", pageTitle="
				+ pageTitle + ", enable=" + enable + ", created=" + created
				+ "]";
	}
}
