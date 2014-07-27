package com.vcareinc.controllers;

import javax.faces.bean.ManagedBean;

import org.springframework.stereotype.Controller;

@Controller
@ManagedBean(name="commonManagedBean")
public class CommonManagedBean {

	public Boolean compareCategories(String[] categories, Integer id) {
		Boolean isExists = Boolean.FALSE;
		if(categories != null && categories.length > 0) {
			for(String category : categories) {
				if(Integer.valueOf(category).equals(id)) {
					isExists = Boolean.TRUE;
					break;
				}
			}
		}
		return isExists;
	}
}
