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
	@Size(min=1, max=50, message="Firstname must contain between 1 and 50")
	private String firstname;

	@NotNull
	@Size(min=1, max=50, message="Lastname must contain between 1 and 50")
	private String lastname;

	@NotNull
	@Column(unique=true)
	@Pattern(regexp="^.+@.+\\..+$", message="Email is invalid")
	private String email;

	@OneToOne(fetch=FetchType.EAGER)
	private Address address;

	@NotNull
	private String password;

	@NotNull
	private Boolean activate;

	private String companyName;

	private String phonenumber;
	private String faxNumber;
	private String url;
	private Boolean enable;
	private Timestamp created;
	private User createdBy;
	private Boolean resetPassword;

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

	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
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
	public Boolean getActivate() {
		return activate;
	}
	public void setActivate(Boolean activate) {
		this.activate = activate;
	}
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getFaxNumber() {
		return faxNumber;
	}
	public void setFaxNumber(String faxNumber) {
		this.faxNumber = faxNumber;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
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
	public Boolean getResetPassword() {
		return resetPassword;
	}
	public void setResetPassword(Boolean resetPassword) {
		this.resetPassword = resetPassword;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname="
				+ lastname + ", email=" + email + ", address=" + address
				+ ", password=" + password + ", activate=" + activate
				+ ", companyName=" + companyName + ", phonenumber="
				+ phonenumber + ", faxNumber=" + faxNumber + ", url=" + url
				+ ", enable=" + enable + ", created=" + created
				+ ", createdBy=" + createdBy + ", resetPassword="
				+ resetPassword + ", roles=" + roles + "]";
	}
}
