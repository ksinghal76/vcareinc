package com.vcareinc.constants;

public enum PriceType {
	STUDENTS(0), SILVER(1), GOLD(2), DIAMOND(3);

	private Integer id;
	PriceType(Integer id) {
		this.setId(id);
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
}
