package com.vcareinc.models;

import java.io.Serializable;

public class Register implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String email;
	private String firstname;
	private String lastname;
	private String password;
	private String phoneNumber;
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
	public String getPhoneNumber() {
		return phoneNumber;
	}
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	@Override
	public String toString() {
		return "Register [email=" + email + ", firstname=" + firstname
				+ ", lastname=" + lastname + ", password=" + password
				+ ", phoneNumber=" + phoneNumber + "]";
	}
}
