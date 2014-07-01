package com.vcareinc.services.securities;

import java.util.Set;

import org.acegisecurity.Authentication;
import org.acegisecurity.AuthenticationException;
import org.acegisecurity.AuthenticationManager;
import org.acegisecurity.GrantedAuthority;
import org.acegisecurity.GrantedAuthorityImpl;
import org.acegisecurity.context.SecurityContextHolder;
import org.acegisecurity.providers.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;

import com.vcareinc.constants.RoleType;
import com.vcareinc.vo.Role;
import com.vcareinc.vo.User;

@Controller
public class AcegiAuthenticationService implements AuthenticationManager {

	private GrantedAuthority[] grantedAuthority;

	@Override
	public Authentication authenticate(Authentication auth)
			throws AuthenticationException {
		return authenticate(auth, null);
	}

	public Authentication authenticate(Authentication auth, User user) {
		return new AcegiCustomUsernamePasswordAuthenticationToken(auth.getPrincipal(), auth.getCredentials(), grantedAuthority, user);
	}

	private void setAuthorities(RoleType roleType) {
		if(roleType != null) {
			grantedAuthority = new GrantedAuthority[1];
			grantedAuthority[0] = new GrantedAuthorityImpl(roleType.name());
		}
	}

	private void setAuthorities(Set<Role> roles) {
		if(roles != null && roles.size() > 0) {
			grantedAuthority = new GrantedAuthority[roles.size()];
			int i = 0;
			for(Role role : roles) {
				grantedAuthority[i++] = new GrantedAuthorityImpl(role.getAuthority().name());
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

	public GrantedAuthority[] getGrantedAuthority() {
		return grantedAuthority;
	}

	public void setGrantedAuthority(GrantedAuthority[] grantedAuthority) {
		this.grantedAuthority = grantedAuthority;
	}
}
