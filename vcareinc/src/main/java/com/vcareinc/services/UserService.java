package com.vcareinc.services;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;

import com.vcareinc.models.Register;
import com.vcareinc.vo.Role;
import com.vcareinc.vo.User;

@Controller
public class UserService {

private Logger log = Logger.getLogger(UserService.class);

	private EntityManager em;
	
	@PersistenceContext
	public void setEntityManager(EntityManager em) {
		this.em = em;
	}
	
	@Transactional
	public void saveRegister(Register register) {
		log.info(register.toString());
		
		User user = new User();
		user.setFirstname(register.getFirstname());
		user.setLastname(register.getLastname());
		user.setPhoneNumber(register.getPhonenumber());
		user.setPassword(register.getPassword());
		user.setEmail(register.getEmail());
		user.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		
		
		Role role = findRole("ROLE_USER").get(0);
		if(role == null) {
			role = new Role();
			role.setAuthority("ROLE_USER");
			role.setCreated(new Timestamp(Calendar.getInstance().getTimeInMillis()));
		}
		
		user.addRole(role);
		
		em.persist(role);
		em.persist(user);
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<User> findUser(String email) {
		if(email != null)
			return em.createQuery("select u from User u where u.email = :email")
					.setParameter("email", email).getResultList();
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Transactional(readOnly=true)
	public List<Role> findRole(String authority) {
		if(authority != null)
			return em.createQuery("select r from Role r where r.authority = :authority")
					.setParameter("authority", authority).getResultList();
		return null;
	}
}
