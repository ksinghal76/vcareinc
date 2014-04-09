package com.vcareinc.vo;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Controller;

@Controller
@Entity
@Table(name="user")
public class User implements Serializable {

	private static final long serialVersionUID = 1867515118091918594L;
	
	@Id
	@GeneratedValue
	private Long id;
	
	@NotNull
	@Size(min=5, max=50, message="Firstname must contain between 5 and 50")
	private String firstname;
	
	@NotNull
	@Size(min=5, max=50, message="Lastname must contain between 5 and 50")
	private String lastname;
	
	@Column(unique=true)
	@NotNull
	@Pattern(regexp="^.+@.+\\..+$", message="Email is invalid")
	private String email;
	
	@NotNull
	private String password;
	
	
	private String phonenumber;
	private Boolean enable;
	private Timestamp created;
	private User createdBy;

	@ManyToMany(fetch=FetchType.EAGER)
	private final Set<Role> roles = new HashSet<Role>();
	
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
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
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
		return roles;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", password=" + password
				+ ", phoneNumber=" + phonenumber + ", enable=" + enable
				+ ", created=" + created + ", createdBy=" + createdBy
				+ ", roles=" + roles + "]";
	}
}
