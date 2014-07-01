package com.vcareinc.mappers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.vcareinc.constants.PriceType;
import com.vcareinc.vo.Articles;
import com.vcareinc.vo.Price;
import com.vcareinc.vo.User;

public class ArticleFieldSetMapper implements FieldSetMapper<Articles> {

	private EntityManager em;

	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	@Override
	public Articles mapFieldSet(FieldSet fieldSet) throws BindException {
		Articles articles = new Articles();
//		articles.setUser(getUser(fieldSet.readLong(1)));
//		articles.setTitle(fieldSet.readString(5));
//		articles.setName(fieldSet.readString(8));
//		articles.setDescription(fieldSet.readString(11));
//		if(fieldSet.readInt(39) == 10)
//			articles.setPrice(getPrice(PriceType.DIAMOND));
//		else if(fieldSet.readInt(39) == 30)
//			articles.setPrice(getPrice(PriceType.GOLD));
//		else if(fieldSet.readInt(39) == 50)
//			articles.setPrice(getPrice(PriceType.SILVER));
//		else
//			articles.setPrice(getPrice(PriceType.STUDENTS));
		return articles;
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

}
