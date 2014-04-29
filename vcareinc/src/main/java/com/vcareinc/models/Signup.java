package com.vcareinc.models;

import java.io.Serializable;

import org.springframework.stereotype.Controller;

@SuppressWarnings("rawtypes")
@Controller
public class Signup extends BaseModel implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public enum LoginAccountType{
			DIRECTORY("Sign in with Directory Account"), 
			OPENID("Sign in with OpenID 2.0 Account"), 
			FACEBOOK("Sign in with Facebook Account"), 
			GOOGLE("Sign in with Google Account");
		private String label;
		private LoginAccountType(String label) {
			this.label = label;
		}
		
		public String getLabel() {
			return this.label;
		}
	}
	
	private String email;
	private String password;
	private LoginAccountType loginAccountType;
	private Boolean signMeAutomatically;
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
	public LoginAccountType getLoginAccountType() {
		return loginAccountType;
	}
	public void setLoginAccountType(LoginAccountType loginAccountType) {
		this.loginAccountType = loginAccountType;
	}
	
	public LoginAccountType[] getLoginAccountTypes() {
		return LoginAccountType.values();
	}
	
	public Boolean getSignMeAutomatically() {
		return signMeAutomatically;
	}
	public void setSignMeAutomatically(Boolean signMeAutomatically) {
		this.signMeAutomatically = signMeAutomatically;
	}
	@Override
	public String toString() {
		return "Signup [email=" + email + ", password=" + password
				+ ", loginAccountType=" + loginAccountType
				+ ", signMeAutomatically=" + signMeAutomatically + "]";
	}
}
