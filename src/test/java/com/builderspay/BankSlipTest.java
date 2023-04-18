package com.builderspay;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.builderspay.config.dto.AuthDTO;
import com.builderspay.config.dto.TokenDTO;
import com.builderspay.domain.BankSlip;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class BankSlipTest {
	@Autowired
    private TestRestTemplate testRestTemplate;
	AuthDTO authDto;
	String code;
	String token;
	
	@BeforeAll
    public void start() {
        this.authDto = new AuthDTO();
        authDto.setClient_id("bd753592-cf9b-4d1a-96b9-cb8b2c01bd12");
        authDto.setClient_secret("4e8229fe-1131-439c-9846-799895a8be5b");
        this.code = "34191790010104351004791020150008291070026000";
        this.token = token();
    }
	@Test
    public void bankslip() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", this.token);
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> request = new HttpEntity<>(code, headers);

        ResponseEntity<BankSlip> response = this.testRestTemplate
            .exchange("/bankslip", HttpMethod.POST, request, BankSlip.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
	
	private String token() {
        HttpEntity<AuthDTO> httpEntity = new HttpEntity<>(this.authDto);
        ResponseEntity<TokenDTO> response = this.testRestTemplate
            .exchange("/auth", HttpMethod.POST, httpEntity, TokenDTO.class);
        return response.getBody().getToken();
    }
}
