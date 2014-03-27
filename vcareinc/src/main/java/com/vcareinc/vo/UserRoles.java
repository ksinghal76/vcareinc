package com.vcareinc.vo;

import java.io.Serializable;
import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Controller;

@Controller
@Entity
public class UserRoles implements Serializable {

	private static final long serialVersionUID = -2859455369294931640L;
	
	private Long id;
	private User user;
	private String authority;
	private Timestamp created;
	private User createdBy;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAuthority() {
		return authority;
	}
	public void setAuthority(String authority) {
		this.authority = authority;
	}
	public Timestamp getCreated() {
		return created;
	}
	public void setCreated(Timestamp created) {
		this.created = created;
	}
	
	@OneToOne(fetch=FetchType.LAZY)
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	@Override
	public String toString() {
		return "UserRoles [id=" + id + ", user=" + user + ", authority="
				+ authority + ", created=" + created + ", createdBy="
				+ createdBy + "]";
	}
}
