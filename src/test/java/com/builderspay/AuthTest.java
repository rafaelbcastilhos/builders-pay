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
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.builderspay.config.dto.AuthDTO;
import com.builderspay.config.dto.TokenDTO;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AuthTest {
	@Autowired
    private TestRestTemplate testRestTemplate;
	TokenDTO tokenDto;
	AuthDTO authDto;
	@BeforeAll
    public void start() {
        this.authDto = new AuthDTO();
        authDto.setClient_id("bd753592-cf9b-4d1a-96b9-cb8b2c01bd12");
        authDto.setClient_secret("4e8229fe-1131-439c-9846-799895a8be5b");
    }
	
	@Test
    public void token() {
        HttpEntity<AuthDTO> httpEntity = new HttpEntity<>(this.authDto);

        ResponseEntity<TokenDTO> response = this.testRestTemplate
            .exchange("/auth", HttpMethod.POST, httpEntity, TokenDTO.class);
    
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }
}
