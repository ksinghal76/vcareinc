package com.vcareinc.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

import org.springframework.stereotype.Controller;

@Controller
@Entity
public class User implements Serializable {

	private static final long serialVersionUID = 1867515118091918594L;
	
	private Long id;
	private String firstname;
	private String lastname;
	private String email;
	private String password;
	private String phoneNumber;
	private Boolean enable;
	private Timestamp created;
	private User createdBy;
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@ElementCollection(targetClass=Role.class)
	private Set<Role> roles = new HashSet<Role>();
	
	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
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
	
	@OneToOne(fetch=FetchType.LAZY)
	public User getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(User createdBy) {
		this.createdBy = createdBy;
	}
	
	public boolean addRole(Role role) {
		return roles.add(role);
	}
	
	public Set<Role> getRoles() {
		return this.roles;
	}
	
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + ", enable=" + enable
				+ ", created=" + created + ", createdBy=" + createdBy + "]";
	}
}
