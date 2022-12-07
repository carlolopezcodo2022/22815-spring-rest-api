package ar.com.codoacodo.spring.security.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codoacodo.spring.security.JwtProvider;
import ar.com.codoacodo.spring.security.controller.dto.LoginRequestDto;
import ar.com.codoacodo.spring.security.controller.dto.LoginResponseDto;

@RestController
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private JwtProvider jwtProvider;
	
	@PostMapping(value="/auth/login",consumes = "application/json",produces = "application/json")
	public ResponseEntity<LoginResponseDto> login(
			@Valid @RequestBody LoginRequestDto loginReqDto
		) {

		UsernamePasswordAuthenticationToken userToken = new UsernamePasswordAuthenticationToken(
				loginReqDto.getUsername(),loginReqDto.getPassword());
		
		Authentication authentication = authenticationManager.authenticate(userToken);
		
		String jwtToken = this.jwtProvider.generateToken(authentication);
		
		return ResponseEntity.ok(new LoginResponseDto(jwtToken));
	}
}
