package com.vcareinc.mappers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.vcareinc.constants.OptionType;
import com.vcareinc.constants.PriceType;
import com.vcareinc.vo.Address;
import com.vcareinc.vo.Category;
import com.vcareinc.vo.Events;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.User;

public class EventFieldSetMapper implements FieldSetMapper<Events> {

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Events mapFieldSet(FieldSet fieldSet) throws BindException {
		Events events = new Events();
//		events.setUser(getUser(fieldSet.readLong(2)));
//		events.setTitle(fieldSet.readString(3));
//		events.setDescription(fieldSet.readString(11));
//
//		if(fieldSet.readString(25) != null && fieldSet.readString(25).trim().length() > 0) {
//			Address address = new Address();
//			address.setAddress1(fieldSet.readString(25));
//			address.setZipcode(fieldSet.readString(26));
//			em.persist(address);
//			events.setAddress(address);
//		}
//		events.setName(fieldSet.readString(33));
//		events.setPhoneNumber(fieldSet.readString(34));
//		events.setEmail(fieldSet.readString(35));
//		if(fieldSet.readInt(39) == 10)
//			events.setPrice(getPrice(PriceType.DIAMOND));
//		else if(fieldSet.readInt(39) == 30)
//			events.setPrice(getPrice(PriceType.GOLD));
//		else if(fieldSet.readInt(39) == 50)
//			events.setPrice(getPrice(PriceType.SILVER));
//		else
//			events.setPrice(getPrice(PriceType.STUDENTS));
		return events;
	}

	@SuppressWarnings("unchecked")
	private User getUser(Long id) {
		User user = null;
		List<User> userList = em.createQuery("SELECT u FROM User u WHERE u.oldId = :oldId").setParameter("oldId", id).getResultList();
		if(userList != null && userList.size() > 0)
			user = userList.get(0);
		return user;
	}

	@SuppressWarnings("unchecked")
	private Price getPrice(PriceType priceType) {
		Price price = null;
		List<Price> priceList = em.createQuery("SELECT p FROM Price p WHERE p.priceType = :priceType").setParameter("priceType", priceType).getResultList();
		if(priceList != null && priceList.size() > 0)
			price = priceList.get(0);
		else {
			price = new Price();
			price.setPriceType(priceType);
			em.persist(price);
		}
		return price;
	}

	@SuppressWarnings("unchecked")
	private Category getCategory(Long oldId) {
		Category category = null;
		List<Category> categoryList = em.createQuery("SELECT c FROM CATEGORY c WHERE c.oldId = :oldId and c.categoryType = :categoryType")
				.setParameter("oldId", oldId)
				.setParameter("categoryType", OptionType.EVENT).getResultList();
		if(categoryList != null && categoryList.size() > 0)
			category = categoryList.get(0);
		return category;
	}

}
