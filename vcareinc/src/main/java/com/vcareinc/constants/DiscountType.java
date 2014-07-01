package com.vcareinc.constants;

public enum DiscountType {
	FIXED("$", "Fixed Value Discount"), PERCENTAGE("%", "Percentage Discount");

	private String sign;
	private String text;
	DiscountType(String sign, String text) {
		this.sign = sign;
		this.text = text;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
	public String getText() {
		return text;
	}
	public void setText(String text) {
		this.text = text;
	}
}
