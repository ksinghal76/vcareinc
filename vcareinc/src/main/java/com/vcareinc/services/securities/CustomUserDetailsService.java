package com.vcareinc.services.securities;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.vcareinc.services.UserService;
import com.vcareinc.vo.User;

public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserService userService;

	private CustomUserDetails customUserDetails;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public CustomUserDetailsService() {

	}

	public CustomUserDetailsService(CustomUserDetails customUserDetails) {
		this.customUserDetails = customUserDetails;
	}

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		return customUserDetails.getUserByEmail(username);
	}

	public CustomUserDetails getCustomUserDetails() {
		return customUserDetails;
	}

	public void setCustomUserDetails(CustomUserDetails customUserDetails) {
		this.customUserDetails = customUserDetails;
	}

}
