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
import com.vcareinc.utils.CommonUtils;
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
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
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
	
	@Transactional(readOnly=true)
	public User findUser(String email) {
		if(email != null)
			return (User) em.createQuery("select u from User u where u.email = :email")
					.setParameter("email", email).getResultList().get(0);
		return null;
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
