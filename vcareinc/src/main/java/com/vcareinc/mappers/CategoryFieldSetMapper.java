package com.vcareinc.mappers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.vcareinc.constants.OptionType;
import com.vcareinc.utils.DateUtils;
import com.vcareinc.vo.Category;

public class CategoryFieldSetMapper implements FieldSetMapper<Category> {
	
	private EntityManager em;
	
	private Map<Long, Category> categoryList = new HashMap<Long, Category>();
	private OptionType categoryType;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}

	public OptionType getCategoryType() {
		return categoryType;
	}

	public void setCategoryType(OptionType categoryType) {
		this.categoryType = categoryType;
	}

	@Override
	public Category mapFieldSet(FieldSet fieldSet) throws BindException {
		Category category = new Category();
		category.setCreated(DateUtils.getCurrentTimeStamp());
		category.setEnable(Boolean.TRUE);
		category.setOptionType(getCategoryType());
		
		if(getCategoryType().equals(OptionType.LISTING)) {
			category.setName(fieldSet.readString(4));			
			category.setPageTitle(fieldSet.readString(11));
			
			try {
				if(fieldSet.readInt(5) > 0) {
					Integer parentCategoryId = fieldSet.readInt(5);
					Category parentCategory = findCategory(Long.valueOf(parentCategoryId));
					category.setParentCategory(parentCategory);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(getCategoryType().equals(OptionType.EVENT)) {
			category.setName(fieldSet.readString(1));
			category.setPageTitle(fieldSet.readString(8));
			
			try {
				if(fieldSet.readInt(2) > 0) {
					Integer parentCategoryId = fieldSet.readInt(2);
					Category parentCategory = findCategory(Long.valueOf(parentCategoryId));
					category.setParentCategory(parentCategory);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(getCategoryType().equals(OptionType.CLASSIFIED)) {
			category.setName(fieldSet.readString(1));
			category.setPageTitle(fieldSet.readString(8));
			
			try {
				if(fieldSet.readInt(2) > 0) {
					Integer parentCategoryId = fieldSet.readInt(2);
					Category parentCategory = findCategory(Long.valueOf(parentCategoryId));
					category.setParentCategory(parentCategory);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		} else if(getCategoryType().equals(OptionType.ARTICLE)) {
			category.setName(fieldSet.readString(1));
			category.setPageTitle(fieldSet.readString(8));
			
			try {
				if(fieldSet.readInt(2) > 0) {
					Integer parentCategoryId = fieldSet.readInt(2);
					Category parentCategory = findCategory(Long.valueOf(parentCategoryId));
					category.setParentCategory(parentCategory);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}	
		categoryList.put(Long.valueOf(fieldSet.readInt(0)), category);
		return category;
	}
	
	@SuppressWarnings("unchecked")
	private Category findCategory(Long id) {
		Category categoryVal = null;
		for(Map.Entry<Long, Category> entry : categoryList.entrySet()) {
			if(entry.getKey().equals(id)) {
				List<Category> categoryList = em.createQuery("SELECT c FROM Category c WHERE c.name = :name and c.categoryType = :categoryType")
						.setParameter("name", entry.getValue().getName())
						.setParameter("categoryType", entry.getValue().getOptionType())
						.getResultList();
				if(categoryList != null && categoryList.size() > 0)
					categoryVal = categoryList.get(0);
				break;
			}
		}
		return categoryVal;
	}
	
	

}
