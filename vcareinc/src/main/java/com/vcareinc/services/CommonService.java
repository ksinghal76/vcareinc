package com.vcareinc.services;

import java.util.ArrayList;
import java.util.Calendar;
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

	public List<String> getWholeMonth(Calendar selectCalendar) {
		List<String> dayOfMonth = new ArrayList<String>();
		Integer leadSpaces = selectCalendar.get(Calendar.DAY_OF_WEEK);
		Integer maxDay = selectCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		if(leadSpaces > 0) {
			for(int i=0; i< leadSpaces; i++) {
				dayOfMonth.add("");
			}
		}

		for(int i=0; i<maxDay; i++) {
			dayOfMonth.add(String.valueOf(i+1));
		}
		return dayOfMonth;
	}

	public static void main(String[] args)  {
		CommonService com = new CommonService();
		Calendar calendar = Calendar.getInstance();
		for(String str : com.getWholeMonth(calendar)) {
			System.out.println("++ " + str + " ++");
		}
	}
}
