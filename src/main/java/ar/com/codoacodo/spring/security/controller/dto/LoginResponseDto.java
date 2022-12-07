package ar.com.codoacodo.spring.security.controller.dto;

import lombok.Data;

@Data
public class LoginResponseDto {
	private String jwt;
	private String type = "Bearer";
	public LoginResponseDto(String jwt) {
		super();
		this.jwt = jwt;
	}
	
}
