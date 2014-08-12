package com.vcareinc.constants;

public enum SortingOrder {

	LEVEL("price.priceType"), ALPHABETICALLY("title"), POPULAR(""), RATING("");
	
	private String sortingName;
	private SortingOrder(String sortingName) {
		this.sortingName = sortingName;
	}
	public String getSortingName() {
		return sortingName;
	}
	public void setSortingName(String sortingName) {
		this.sortingName = sortingName;
	}
}
