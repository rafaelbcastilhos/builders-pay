package com.builderspay.domain;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "payment")
public class Payment implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "original_amount")
	private double originalAmount;
	
	@Column(name = "amount")
	private double amount;
	
	@Column(name = "due_date")
	private String dueDate;
	
	@Column(name = "payment_date")
	private String paymentDate;
	
	@Column(name = "interest_amount_calculated")
	private double interestAmountCalculated;
	
	@Column(name = "fine_amount_calculated")
	private double fineAmountCalculated;

	public Payment() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public double getOriginalAmount() {
		return originalAmount;
	}

	public void setOriginalAmount(double originalAmount) {
		this.originalAmount = originalAmount;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public String getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(String paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getInterestAmountCalculated() {
		return interestAmountCalculated;
	}

	public void setInterestAmountCalculated(double interestAmountCalculated) {
		this.interestAmountCalculated = interestAmountCalculated;
	}

	public double getFineAmountCalculated() {
		return fineAmountCalculated;
	}

	public void setFineAmountCalculated(double fineAmountCalculated) {
		this.fineAmountCalculated = fineAmountCalculated;
	}

	public Payment(
			double originalAmount, double amount, String dueDate, String paymentDate, 
			double interestAmountCalculated, double fineAmountCalculated) {
		this.originalAmount = originalAmount;
		this.amount = amount;
		this.dueDate = dueDate;
		this.paymentDate = paymentDate;
		this.interestAmountCalculated = interestAmountCalculated;
		this.fineAmountCalculated = fineAmountCalculated;
	}
}
