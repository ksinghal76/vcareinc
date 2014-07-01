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
import com.vcareinc.vo.Listings;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.User;

public class ListingFieldSetMapper implements FieldSetMapper<Listings> {

	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}	
	
	@Override
	public Listings mapFieldSet(FieldSet fieldSet) throws BindException {
		Listings listings = new Listings();
		
		if(fieldSet.readString(1) != null  && fieldSet.readString(1).trim().length() > 0)
			listings.setUser(getUser(fieldSet.readLong(1)));
		listings.setTitle(fieldSet.readString(10));
		listings.setUrl(fieldSet.readString(16));
		
		if(fieldSet.readString(17) != null && fieldSet.readString(17).trim().length() > 0) {
			Address address = new Address();
			address.setAddress1(fieldSet.readString(17));
			address.setAddress2(fieldSet.readString(18));
			address.setZipcode(fieldSet.readString(19));
			em.persist(address);
			listings.setAddress(address);
		}
		listings.setPhoneNumber(fieldSet.readString(25));
		listings.setFaxNumber(fieldSet.readString(26));
		listings.setDescription(fieldSet.readString(27));
		
		if(fieldSet.readInt(39) == 10)
			listings.setPrice(getPrice(PriceType.DIAMOND));
		else if(fieldSet.readInt(39) == 30)
			listings.setPrice(getPrice(PriceType.GOLD));
		else if(fieldSet.readInt(39) == 50)
			listings.setPrice(getPrice(PriceType.SILVER));
		else
			listings.setPrice(getPrice(PriceType.STUDENTS));
//		if(fieldSet.readString(36) != null && fieldSet.readString(36).trim().length() > 0)
//			listings.addCategory(getCategory(fieldSet.readLong(36)));
		
		return listings;
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
				.setParameter("categoryType", OptionType.LISTING).getResultList();
		if(categoryList != null && categoryList.size() > 0)
			category = categoryList.get(0);
		return category;
	}
}
