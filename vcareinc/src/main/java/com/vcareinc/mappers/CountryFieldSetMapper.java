package com.vcareinc.mappers;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.vcareinc.vo.Country;

public class CountryFieldSetMapper implements FieldSetMapper<Country> {

	@Override
	public Country mapFieldSet(FieldSet fieldSet) throws BindException {
		Country country = new Country();
		country.setName(fieldSet.readString(1));
		country.setCode(fieldSet.readString(2));
		return country;
	}

}
