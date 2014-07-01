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
import com.vcareinc.vo.Classified;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.User;

public class ClassifiedFieldSetMapper implements FieldSetMapper<Classified> {

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Classified mapFieldSet(FieldSet fieldSet) throws BindException {
		Classified classified = new Classified();
//		classified.setUser(getUser(fieldSet.readLong(1)));
//		classified.setTitle(fieldSet.readString(5));
//		classified.setUrl(fieldSet.readString(9));
//		classified.setName(fieldSet.readString(10));
//
//		if(fieldSet.readString(11) != null && fieldSet.readString(11).trim().length() > 0) {
//			Address address = new Address();
//			address.setAddress1(fieldSet.readString(11));
//			address.setAddress2(fieldSet.readString(12));
//			address.setZipcode(fieldSet.readString(21));
//			em.persist(address);
//			classified.setAddress(address);
//		}
//
//		classified.setPhoneNumber(fieldSet.readString(13));
//		classified.setFaxNumber(fieldSet.readString(14));
//		classified.setDescription(fieldSet.readString(15));
//		if(fieldSet.readInt(32) == 10)
//			classified.setPrice(getPrice(PriceType.DIAMOND));
//		else if(fieldSet.readInt(32) == 30)
//			classified.setPrice(getPrice(PriceType.GOLD));
//		else if(fieldSet.readInt(32) == 50)
//			classified.setPrice(getPrice(PriceType.SILVER));
//		else
//			classified.setPrice(getPrice(PriceType.STUDENTS));
//		classified.setPriceValue(fieldSet.readFloat(61));
//		if(fieldSet.readString(36) != null && fieldSet.readString(36).trim().length() > 0)
//			classified.addCategory(getCategory(fieldSet.readLong(36)));

		return classified;
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
				.setParameter("categoryType", OptionType.CLASSIFIED).getResultList();
		if(categoryList != null && categoryList.size() > 0)
			category = categoryList.get(0);
		return category;
	}

}
