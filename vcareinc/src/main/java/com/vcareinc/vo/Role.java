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
public class Role implements Serializable {

	private static final long serialVersionUID = -2859455369294931640L;
	
	public enum RoleType{ROLE_USER, ROLE_ADMIN, ROLE_CLIENT};
	
	private Long id;
	private RoleType authority;
	private Timestamp created;
	private User createdBy;
	
	@OneToOne
	private User user;
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public RoleType getAuthority() {
		return authority;
	}
	public void setAuthority(RoleType authority) {
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
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() {
		return "Role [id=" + id + ", authority=" + authority + ", created="
				+ created + ", createdBy=" + createdBy + ", user=" + user + "]";
	}
}
