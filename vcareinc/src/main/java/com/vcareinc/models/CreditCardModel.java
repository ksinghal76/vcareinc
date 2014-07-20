package com.vcareinc.models;

import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;

@SuppressWarnings("rawtypes")
@Controller
public class CreditCardModel extends BaseModel {

	private static final long serialVersionUID = 1L;

	@NotNull(message="Credit Card Number is required")
	private String cardNumber;

	@NotNull(message="Expire Date is required")
	@DateTimeFormat(pattern="MM/yyyy")
	private String expireDate;

	@NotNull(message="Credit Card Code is required")
	private String cardCode;

	@NotNull(message="Firstname is required")
	private String firstName;

	@NotNull(message="Lastname is required")
	private String lastName;

	@NotNull(message="Company Name is required")
	private String companyName;

	@NotNull(message="Address is required")
	private String address;

	@NotNull(message="City is required")
	private String city;

	@NotNull(message="State is required")
	private String state;

	@NotNull(message="Zipcode is required")
	private String zipCode;

	@NotNull(message="Country is required")
	private String country;

	@NotNull(message="Phone Number is required")
	private String phone;

	@Email
	private String email;

	/**
	 * @return the cardNumber
	 */
	public String getCardNumber() {
		return cardNumber;
	}
	/**
	 * @param cardNumber the cardNumber to set
	 */
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	/**
	 * @return the expireDate
	 */
	public String getExpireDate() {
		return expireDate;
	}
	/**
	 * @param expireDate the expireDate to set
	 */
	public void setExpireDate(String expireDate) {
		this.expireDate = expireDate;
	}
	/**
	 * @return the cardCode
	 */
	public String getCardCode() {
		return cardCode;
	}
	/**
	 * @param cardCode the cardCode to set
	 */
	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}
	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}
	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}
	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	/**
	 * @return the companyName
	 */
	public String getCompanyName() {
		return companyName;
	}
	/**
	 * @param companyName the companyName to set
	 */
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	/**
	 * @return the address
	 */
	public String getAddress() {
		return address;
	}
	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * @return the city
	 */
	public String getCity() {
		return city;
	}
	/**
	 * @param city the city to set
	 */
	public void setCity(String city) {
		this.city = city;
	}
	/**
	 * @return the state
	 */
	public String getState() {
		return state;
	}
	/**
	 * @param state the state to set
	 */
	public void setState(String state) {
		this.state = state;
	}
	/**
	 * @return the zipCode
	 */
	public String getZipCode() {
		return zipCode;
	}
	/**
	 * @param zipCode the zipCode to set
	 */
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	/**
	 * @return the country
	 */
	public String getCountry() {
		return country;
	}
	/**
	 * @param country the country to set
	 */
	public void setCountry(String country) {
		this.country = country;
	}
	/**
	 * @return the phone
	 */
	public String getPhone() {
		return phone;
	}
	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}
	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}
}
