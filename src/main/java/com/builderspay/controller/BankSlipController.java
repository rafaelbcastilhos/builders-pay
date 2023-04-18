package com.builderspay.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.builderspay.domain.BankSlip;
import com.builderspay.domain.Payment;
import com.builderspay.domain.dto.PayDTO;
import com.builderspay.service.BankSlipService;

@RestController
@RequestMapping("/bankslip")
public class BankSlipController {
	@Autowired
	private BankSlipService bankslipService;

	@PostMapping
	public ResponseEntity<BankSlip> searchBankSlip(@RequestBody String code, @RequestHeader String token) {
		return ResponseEntity.status(HttpStatus.OK).body(bankslipService.searchBankSlip(code, token));
	}
	
	@PostMapping("/payment")
	public ResponseEntity<Payment> payment(@RequestBody PayDTO pay, @RequestHeader String token){
		return ResponseEntity.status(HttpStatus.CREATED).body(bankslipService.payment(pay, token));
	}
	
}
