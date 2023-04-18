package com.builderspay.domain;

public class BankSlip {
	private String code;
	private String due_date;
	private Double amount;
	private String recipient_name;
	private String recipient_document;
	private String type;

	public String getCode() {
		return code;
	}

	public String getDue_date() {
		return due_date;
	}

	public Double getAmount() {
		return amount;
	}

	public String getRecipient_name() {
		return recipient_name;
	}

	public String getRecipient_document() {
		return recipient_document;
	}

	public String getType() {
		return type;
	}
}
