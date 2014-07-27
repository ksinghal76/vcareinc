package com.vcareinc.services;

import java.util.List;

import org.springframework.stereotype.Controller;

import com.vcareinc.vo.City;

@SuppressWarnings("rawtypes")
@Controller
public class CommonService extends BaseService {

	@SuppressWarnings("unchecked")
	public List<City> getAllCity() {
		return em.createQuery("SELECT c FROM City c").getResultList();
	}
}
