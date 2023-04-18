package com.builderspay.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.builderspay.config.dto.AuthDTO;
import com.builderspay.config.dto.TokenDTO;

@RestController
@RequestMapping("/auth")
public class AuthController {
	@Autowired
	private TokenService tokenService;
	@PostMapping
	public ResponseEntity<TokenDTO> getToken(@RequestBody AuthDTO auth) {
		return ResponseEntity.status(HttpStatus.OK).body(tokenService.getToken(auth));
	}
}
