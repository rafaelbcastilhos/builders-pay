package com.builderspay.config.security;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import com.builderspay.config.dto.AuthDTO;
import com.builderspay.config.dto.TokenDTO;

@Service
public class TokenService {
	public TokenDTO getToken(AuthDTO auth) {
		String endpoint = "https://vagas.builders/api/builders/auth/tokens";
		RestTemplate rest = new RestTemplate();
		return rest.postForEntity(endpoint, auth, TokenDTO.class).getBody();
	}
}
