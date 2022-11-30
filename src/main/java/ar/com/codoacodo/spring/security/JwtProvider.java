package ar.com.codoacodo.spring.security;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class JwtProvider {
	
	public boolean validateToken(String jwtToken) {
		return true;
	}
	
	public String getUsernameFromToken(String jwtToken) {
		return "eduit";
	}
	
	public String generateToken(Authentication auth) {
		return "jwtdementiraporquedepueslocambiamos!!!";
	}
}
 