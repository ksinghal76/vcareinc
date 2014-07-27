package com.vcareinc.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.PersistenceException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.webflow.execution.RequestContext;

import com.vcareinc.constants.RoleType;
import com.vcareinc.constants.StatusType;
import com.vcareinc.exceptions.CommonException;
import com.vcareinc.exceptions.DBException;
import com.vcareinc.models.Register;
import com.vcareinc.models.ResultEmailJson;
import com.vcareinc.models.Signup;
import com.vcareinc.services.securities.AuthenticationService;
import com.vcareinc.utils.DateUtils;
import com.vcareinc.utils.SecureUtils;
import com.vcareinc.vo.Role;
import com.vcareinc.vo.User;

@SuppressWarnings("rawtypes")
@Controller
public class UserService extends BaseService {

private Logger log = Logger.getLogger(UserService.class);


	@Autowired
	private SimpleMailService simpleMailService;

	@Autowired
	private RestMailService<ResultEmailJson> restMailService;

//	@Autowired
//	private AcegiAuthenticationService acegiAuthenticationService;

	private final String USER_PROFILE = "userProfile";

	@Autowired
	private AuthenticationService authenticationService;

//	public AcegiAuthenticationService getAcegiAuthenticationService() {
//		return acegiAuthenticationService;
//	}
//
//	public void setAcegiAuthenticationService(AcegiAuthenticationService acegiAuthenticationService) {
//		this.acegiAuthenticationService = acegiAuthenticationService;
//	}

	public AuthenticationService getAuthenticationService() {
		return authenticationService;
	}

	public void setAuthenticationService(AuthenticationService authenticationService) {
		this.authenticationService = authenticationService;
	}

	public String loginUser(Signup signup, RequestContext context) throws CommonException {
		log.debug(signup.toString());

		HttpSession session = ((HttpServletRequest) context.getExternalContext().getNativeRequest()).getSession();
		try {
			clearError(signup);
			if(signup != null) {
				User user = findUser(signup.getEmail());
				if(user != null) {
					if(SecureUtils.checkPassword(signup.getPassword().trim(), user.getPassword().trim())) {
						authenticationService.addUserAuthentication(user.getEmail(), user.getPassword(), user.getRoles());
						session.setAttribute(USER_PROFILE, user);

					} else {
						signup.setErrorMsg("User Email/Password invalid!!!");
						throw new DBException("User Email/Password invalid!!!");
					}
				} else {
					signup.setErrorMsg("User Email does not exists!!!!");
					throw new DBException("User Email does not exists!!!!");
				}
			}
		} catch (Exception e) {
			signup.setErrorMsg(e.getMessage());
			throw new DBException(e.getMessage());
		}
		return "success";
	}

	@SuppressWarnings({ "unchecked" })
	@Transactional(rollbackFor=DBException.class)
	public User saveRegister(Register register, RequestContext context) throws CommonException  {
		log.info(register.toString());
		HttpSession session = ((HttpServletRequest) context.getExternalContext().getNativeRequest()).getSession();
		User user = new User();

		try {
			clearError(register);
			user.setFirstname(register.getFirstname());
			user.setLastname(register.getLastname());
			user.setPhonenumber(register.getPhonenumber());
			user.setPassword(SecureUtils.generatePassword(register.getPassword()));
			user.setEmail(register.getUsername());
			user.setCreated(DateUtils.getCurrentTimeStamp());
			user.setActivate(Boolean.FALSE);
			user.setStatus(StatusType.ACTIVE);

			Role role = null;
			List<Role> roleList = findRole(RoleType.ROLE_USER);
			if(roleList != null && roleList.size() > 0)
				role = roleList.get(0);

			if(role == null) {
				role = new Role();
				role.setAuthority(RoleType.ROLE_USER);
				role.setCreated(DateUtils.getCurrentTimeStamp());
			}
			em.persist(role);

			user.addRole(role);

			em.persist(user);

//			acegiAuthenticationService.addUserAuthentication(user.getEmail(), user.getPassword(), role.getAuthority(), user);
			authenticationService.addUserAuthentication(user.getEmail(), user.getPassword(), role.getAuthority());
			session.setAttribute(USER_PROFILE, user);
		} catch(ConstraintViolationException e) {
			register.setErrorConstraintViolation(e.getConstraintViolations());
			throw new DBException(e.getMessage());
		} catch(PersistenceException e) {
			if(DBException.is(e, org.hibernate.exception.ConstraintViolationException.class)) {
				register.setErrorMsg("Duplicate Email. Please enter other email");
				throw new DBException(e.getMessage());
			} else {
				register.setErrorMsg(e.getMessage());
				throw new DBException(e.getMessage());
			}
		}
		return user;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public User findUser(String email) {
		User user = null;
		if(email != null) {
			List<User> usrLst = em.createQuery("select u from User u where u.email = :email")
					.setParameter("email", email).getResultList();
			if(usrLst != null && usrLst.size() > 0) {
				user = usrLst.get(0);
			}
		}

		return user;
	}

	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Role> findRole(RoleType authority) {
		if(authority != null)
			return em.createQuery("select r from Role r where r.authority = :authority")
					.setParameter("authority", authority).getResultList();
		return null;
	}

	public void sendActivateUserEmail(RequestContext context) throws CommonException {
		User user = (User) context.getFlowScope().get("userProfile");

		SecureUtils secureUtils = new SecureUtils();

		Map<String, String> mapSecure = new HashMap<String, String>();
		mapSecure.put("userId", String.valueOf(user.getId()));
		try {
			String[] encrypted = secureUtils.encryptObject(mapSecure);

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("name", user.getFirstname().trim() + " " + user.getLastname().trim());
			model.put("url", encrypted[0]);
			model.put("email", user.getEmail());
			model.put("password", user.getPassword());

			simpleMailService.setFilename("ActivateUser.vm");
			simpleMailService.setTo(new String[] {user.getEmail()});
			simpleMailService.setModel(model);
			simpleMailService.sendMessage();
		} catch (Exception e) {
			throw new CommonException(e);
		}
	}

	public void sendActivateUserEmailRest(RequestContext context) throws CommonException {
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getNativeRequest();
		User user = getUserProfile(request);

		SecureUtils secureUtils = new SecureUtils();

		Map<String, String> mapSecure = new HashMap<String, String>();
		mapSecure.put("userId", String.valueOf(user.getId()));
		try {
			String[] encrypted = secureUtils.encryptObject(mapSecure);

			Map<String, Object> model = new HashMap<String, Object>();
			model.put("name", user.getFirstname().trim() + " " + user.getLastname().trim());
			model.put("url", encrypted[0]);
			model.put("email", user.getEmail());
			model.put("password", user.getPassword());

			MultiValueMap<String, String> jsonModel = new LinkedMultiValueMap<String, String>();
			jsonModel.add("to", user.getEmail());
			jsonModel.add("toname", user.getFirstname().trim() + "+" + user.getLastname().trim());
			jsonModel.add("subject", "Testing");
			jsonModel.add("from", "VCareInc");

			restMailService.setUrl("https://api.sendgrid.com/api/mail.send.json");
			restMailService.setVmFilename("ActivateUser.vm");
			restMailService.setVmModel(model);
			restMailService.setModel(jsonModel);
			ResultEmailJson result = restMailService.sendMessageTemplate("html", "api_user", "api_key");
			if(result != null)
				log.info(result.toString());
		} catch (Exception e) {
			throw new CommonException(e);
		}
	}

	public Boolean isUserAuthenticated(String role) {
		SecurityContext securityContext = SecurityContextHolder.getContext();
		if(securityContext == null)
			return false;
		Authentication authentication = securityContext.getAuthentication();
		if(authentication == null)
			return false;
		for(GrantedAuthority auth : authentication.getAuthorities()) {
			if(role.equalsIgnoreCase(auth.getAuthority())) {

				return true;
			}
		}

		return false;
	}

	public User getUserProfile(HttpServletRequest request) {
		User user = null;
		if(request != null && request.getSession().getAttribute(USER_PROFILE) != null)
			user = (User) request.getSession().getAttribute(USER_PROFILE);
		return user;
	}

	public User getUser() {
		return findUser(String.valueOf(SecurityContextHolder.getContext().getAuthentication().getPrincipal()));
	}
}
