package com.vcareinc.services.securities;

import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;

import com.vcareinc.vo.User;

public class AcegiCustomUsernamePasswordAuthenticationToken extends
		UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	private User user;

	public AcegiCustomUsernamePasswordAuthenticationToken(Object principal,
			Object credentials, GrantedAuthority[] authorities) {
		super(principal, credentials, authorities);
	}

	public AcegiCustomUsernamePasswordAuthenticationToken(Object principal,
			Object credentials, GrantedAuthority[] authorities, User user) {
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
