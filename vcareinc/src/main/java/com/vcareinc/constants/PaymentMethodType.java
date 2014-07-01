package com.vcareinc.constants;

public enum PaymentMethodType {
	CREDITCARD("By Credit Card"),
	PAYPAL("By PayPal"),
	SIMPLEPAY("By SimplePay"),
	INVOICECHECK("Print Invoice and Mail a Check");

	private String desc;
	PaymentMethodType(String desc) {
		setDesc(desc);
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
