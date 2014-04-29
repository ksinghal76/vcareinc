package com.vcareinc.models;

import java.io.Serializable;

import org.springframework.stereotype.Controller;

@SuppressWarnings("rawtypes")
@Controller
public class Register extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private String retypePassword;
	private String phonenumber;
	private Boolean signupTip;
	private Boolean agreeTerms;
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getRetypePassword() {
		return retypePassword;
	}
	public void setRetypePassword(String retypePassword) {
		this.retypePassword = retypePassword;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public Boolean getSignupTip() {
		return signupTip;
	}
	public void setSignupTip(Boolean signupTip) {
		this.signupTip = signupTip;
	}
	public Boolean getAgreeTerms() {
		return agreeTerms;
	}
	public void setAgreeTerms(Boolean agreeTerms) {
		this.agreeTerms = agreeTerms;
	}
}
