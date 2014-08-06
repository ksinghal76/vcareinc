package com.vcareinc.services;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.vcareinc.vo.Category;
import com.vcareinc.vo.City;

@SuppressWarnings("rawtypes")
@Controller
public class CommonService extends BaseService {

	@SuppressWarnings("unchecked")
	public List<City> getAllCity() {
		return em.createQuery("SELECT c FROM City c").getResultList();
	}

	@SuppressWarnings("unchecked")
	public Category getCategoryById(Long categoryId) {
		Category category = null;
		List<Category> categoryLst = em.createQuery("SELECT c FROM Category c WHERE c.id = :id").setParameter("id", categoryId).getResultList();
		if(categoryLst != null && categoryLst.size() > 0) {
			category = categoryLst.get(0);
		}
		return category;
	}
}
