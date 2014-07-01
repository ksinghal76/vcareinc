package com.vcareinc.mappers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindException;

import com.vcareinc.constants.RoleType;
import com.vcareinc.services.UserService;
import com.vcareinc.utils.DateUtils;
import com.vcareinc.utils.SecureUtils;
import com.vcareinc.vo.Address;
import com.vcareinc.vo.Role;
import com.vcareinc.vo.State;
import com.vcareinc.vo.User;

public class UserFieldSetMapper implements FieldSetMapper<User> {

	private EntityManager em;

	@Autowired
	private UserService userService;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Override
	public User mapFieldSet(FieldSet fieldSet) throws BindException {
		User user = new User();
		user.setActivate(Boolean.TRUE);
		user.setResetPassword(Boolean.TRUE);
		user.setCreated(DateUtils.getCurrentTimeStamp());

		Role role = null;
		List<Role> roleList = userService.findRole(RoleType.ROLE_USER);
		if(roleList != null && roleList.size() > 0)
			role = roleList.get(0);

		if(role == null) {
			role = new Role();
			role.setAuthority(RoleType.ROLE_USER);
			role.setCreated(DateUtils.getCurrentTimeStamp());
			em.persist(role);
		}

		user.addRole(role);

		user.setEmail(fieldSet.readString(4));
		String generatePassword = SecureUtils.getRandomPassword(10);
		user.setPassword(SecureUtils.generatePassword(generatePassword));
		user.setFirstname(fieldSet.readString(22));
		user.setLastname(fieldSet.readString(23));
		user.setCompanyName(fieldSet.readString(24));

		Address address = new Address();
		address.setAddress1(fieldSet.readString(25));
		address.setAddress2(fieldSet.readString(26));
		address.setCity(fieldSet.readString(27));
		if(fieldSet.readString(28) != null && fieldSet.readString(28).trim().length() > 0)
			address.setState(getState(fieldSet.readString(28)));
		address.setZipcode(fieldSet.readString(29));
		em.persist(address);

		user.setAddress(address);

		user.setPhonenumber(fieldSet.readString(31));
		user.setFaxNumber(fieldSet.readString(32));
		user.setUrl(fieldSet.readString(34));
		return user;
	}

	@SuppressWarnings("unchecked")
	private State getState(String code) {
		State st = null;
		List<State> stList = em.createQuery("SELECT s FROM State s WHERE s.code = :code").setParameter("code", code).getResultList();
		if(stList != null && stList.size() > 0)
			st = stList.get(0);
		return st;
	}

}
