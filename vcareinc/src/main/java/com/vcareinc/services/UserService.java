package com.vcareinc.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.ConstraintViolationException;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.vcareinc.exceptions.DBException;
import com.vcareinc.models.Register;
import com.vcareinc.models.Signup;
import com.vcareinc.vo.Role;
import com.vcareinc.vo.User;

@Controller
public class UserService extends BaseService {

private Logger log = Logger.getLogger(UserService.class);

	private EntityManager em;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@SuppressWarnings("finally")
	public String loginUser(Signup signup) throws DBException {
		log.debug(signup.toString());
		
		try {
			clearError(signup);
			if(signup != null) {
//				if(signup.getLoginAccountType().equals(Signup.LoginAccountType.DIRECTORY)) {
					User user = findUser(signup.getEmail());
					if(user != null) {
						if(user.getPassword().trim().equals(signup.getPassword())) {
							signup.setErrorMsg("User Email Successful!!!");
							return "success";
						} else {
							signup.setErrorMsg("User Email/Password invalid!!!");
							throw new DBException("User Email/Password invalid!!!");
						}
					} else {
						signup.setErrorMsg("User Email does not exists!!!!");
						throw new DBException("User Email does not exists!!!!");
					}
//				}
			}
		} catch (Exception e) {
			signup.setErrorMsg(e.getMessage());
			throw new DBException(e.getMessage());
		} finally {
			return "fail";
		}
		
	}
	
	@SuppressWarnings({ "unchecked" })
	@Transactional(rollbackFor=DBException.class)
	public void saveRegister(Register register) throws DBException  {
		log.info(register.toString());
		
		try {
			clearError(register);
			User user = new User();
			user.setFirstname(register.getFirstname());
			user.setLastname(register.getLastname());
			user.setPhonenumber(register.getPhonenumber());
			user.setPassword(register.getPassword());
			user.setEmail(register.getEmail());
			user.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			
			Role role = null;
			List<Role> roleList = findRole(Role.RoleType.ROLE_USER);
			if(roleList != null && roleList.size() > 0)
				role = roleList.get(0);
			
			if(role == null) {
				role = new Role();
				role.setAuthority(Role.RoleType.ROLE_USER);
				role.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
			}
			em.persist(role);
			
			user.addRole(role);

			em.persist(user);
		} catch(ConstraintViolationException e) {
			register.setErrorConstraintViolation(e.getConstraintViolations());
			throw new DBException(e.getMessage());
		}
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
	public List<Role> findRole(Role.RoleType authority) {
		if(authority != null)
			return em.createQuery("select r from Role r where r.authority = :authority")
					.setParameter("authority", authority).getResultList();
		return null;
	}
}
