package com.vcareinc.services.securities;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;

import com.vcareinc.constants.RoleType;
import com.vcareinc.controllers.ConversionManagedBean;
import com.vcareinc.vo.Role;
import com.vcareinc.vo.User;

@Controller
public class AuthenticationService implements AuthenticationManager {

	@Autowired
	private ConversionManagedBean conversionManagedBean;

	private List<GrantedAuthority> grantedAuthority;

	public List<GrantedAuthority> getGrantedAuthority() {
		return grantedAuthority;
	}
	public void setGrantedAuthority(List<GrantedAuthority> grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}

	public ConversionManagedBean getConversionManagedBean() {
		return conversionManagedBean;
	}
	public void setConversionManagedBean(ConversionManagedBean conversionManagedBean) {
		this.conversionManagedBean = conversionManagedBean;
	}

	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		return authenticate(auth, null);
	}

	public Authentication authenticate(Authentication auth, User user) {
		return new CustomUsernamePasswordAuthenticationToken(auth.getName(), auth.getCredentials(), grantedAuthority, user);
	}

	private void setAuthorities(RoleType roleType) {
		if(roleType != null) {
			grantedAuthority = new ArrayList<GrantedAuthority>();
			grantedAuthority.add(new SimpleGrantedAuthority(roleType.name()));
		}
	}

	private void setAuthorities(Set<Role> roles) {
		if(roles != null && roles.size() > 0) {
			grantedAuthority = new ArrayList<GrantedAuthority>();
			for(Role role : roles) {
				grantedAuthority.add(new SimpleGrantedAuthority(role.getAuthority().name()));
			}
		}
	}

	public void addUserAuthentication(String username, String password, RoleType roleType) {
		addUserAuthentication(username, password, roleType, null);
	}

	public void addUserAuthentication(String username, String password, RoleType roleType, User user) {
		setAuthorities(roleType);
		Authentication request = new UsernamePasswordAuthenticationToken(username, password);
		Authentication result = authenticate(request, user);
		SecurityContextHolder.getContext().setAuthentication(result);
	}

	public void addUserAuthentication(String username, String password, Set<Role> roles) {
		addUserAuthentication(username, password, roles, null);
	}

	public void addUserAuthentication(String username, String password, Set<Role> roles, User user) {
		setAuthorities(roles);
		Authentication request = new UsernamePasswordAuthenticationToken(username, password);
		Authentication result = authenticate(request, user);
		SecurityContextHolder.getContext().setAuthentication(result);
	}
}
