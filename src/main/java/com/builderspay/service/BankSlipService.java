package com.builderspay.service;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.builderspay.domain.BankSlip;
import com.builderspay.domain.Payment;
import com.builderspay.domain.dto.CodeDTO;
import com.builderspay.domain.dto.PayDTO;
import com.builderspay.repository.PaymentRepository;
import com.google.gson.Gson;

@Service
public class BankSlipService {
	@Autowired
	private PaymentRepository paymentRepository;
	
	public BankSlip searchBankSlip(String code, String token) {
		return getDate(code, token);
	}
	
	public Payment payment(PayDTO pay, String token) {
		BankSlip bankSlip = getDate(string2json(pay.getBar_code()), token);
		Payment payment = new Payment();
		if(bankSlip.getType().equalsIgnoreCase("NPC")) {
			payment = validateBankSlip(bankSlip, pay);
		}
		return payment;
	}
	
	private String string2json(String codeString) {
		CodeDTO code = new CodeDTO(codeString);
		Gson gson = new Gson();
		return gson.toJson(code);
	}
	
	private Payment validateBankSlip(BankSlip bankSlip, PayDTO pay) {
		Long days = calculateDays(bankSlip.getDue_date(), pay.getPayment_date());
		double fees = days * 0.033; 
		double feesValue = (bankSlip.getAmount() * fees) / 100;
		double penalty = (bankSlip.getAmount() * 2) / 100;
		double value = bankSlip.getAmount() + penalty + feesValue;
		
		Payment payment = new Payment(bankSlip.getAmount().doubleValue(), value,
				bankSlip.getDue_date(), pay.getPayment_date(), feesValue, penalty);

		return paymentRepository.saveAndFlush(payment);
	}
	
	private Long calculateDays(String dataVencimento, String dataPagamento) {
		DateFormat sdf = new SimpleDateFormat("YYYY-MM-DD");
		Long countDays = 0L;
		try {
			Date dueDate = sdf.parse(dataVencimento);
			Date paymentDate = sdf.parse(dataPagamento);
			countDays = (paymentDate.getTime() - dueDate.getTime()) / (1000 * 60 * 60 * 24);
		} catch(ParseException e) {
			System.out.println("Invalid date format");
		}
		
		return countDays;
	}
	
	private BankSlip getDate(String code, String token) {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.add("Authorization", token);
		HttpEntity<String> request = new HttpEntity<>(code, headers);
		String url = "https://vagas.builders/api/builders/bill-payments/codes";
		RestTemplate rest = new RestTemplate();
		return rest.postForEntity(url, request, BankSlip.class).getBody();
	}
}
