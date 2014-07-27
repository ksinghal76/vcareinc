package com.vcareinc.vo;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.vcareinc.constants.StatusType;

@Entity
public class PromotionCode implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Integer id;

	private String code;
	private String description;
	private StatusType active;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the active
	 */
	public StatusType getActive() {
		return active;
	}

	/**
	 * @param active the active to set
	 */
	public void setActive(StatusType active) {
		this.active = active;
	}
}
