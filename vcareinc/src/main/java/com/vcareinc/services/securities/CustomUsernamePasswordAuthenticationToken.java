package com.vcareinc.services.securities;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.vcareinc.vo.User;

public class CustomUsernamePasswordAuthenticationToken extends
		UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private User user;

	public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
		super(principal, credentials, authorities);
	}

	public CustomUsernamePasswordAuthenticationToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities
			, User user) {
		super(principal, credentials, authorities);
		this.setUser(user);
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}
}
