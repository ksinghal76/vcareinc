package com.vcareinc.constants;

public enum NumberOfRecordPerPage {
	TEN(10), TWENTY(20), THIRTY(30), FORTY(40);

	private Integer number;

	private NumberOfRecordPerPage(Integer number) {
		this.number = number;
	}

	public Integer getNumber() {
		return number;
	}

	public void setNumber(Integer number) {
		this.number = number;
	}
}
