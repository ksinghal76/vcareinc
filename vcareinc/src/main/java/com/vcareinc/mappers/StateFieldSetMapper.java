package com.vcareinc.mappers;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.vcareinc.vo.Country;
import com.vcareinc.vo.State;

public class StateFieldSetMapper implements FieldSetMapper<State> {

	private EntityManager em;
	
	@PersistenceContext
	public void setEm(EntityManager em) {
		this.em = em;
	}
	
	@Override
	public State mapFieldSet(FieldSet fieldSet) throws BindException {
		State state = new State();
		state.setName(fieldSet.readString(3));
		state.setCode(fieldSet.readString(4));
		state.setCountry(findCountry(Long.valueOf(fieldSet.readInt(2))));
		return state;
	}
	
	@SuppressWarnings("unchecked")
	public Country findCountry(Long id) {
		Country country = null;
		List<Country> countryLst = em.createQuery("SELECT c FROM Country c WHERE c.oldId = :oldId").setParameter("oldId", id).getResultList();
		if(countryLst != null && countryLst.size() > 0)
			country = countryLst.get(0);
		return country;
	}

}
