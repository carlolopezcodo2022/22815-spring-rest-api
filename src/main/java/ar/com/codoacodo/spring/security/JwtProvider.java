package ar.com.codoacodo.spring.security;

import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtProvider {
	
	@Value("${jwt.secret}")
	private String secret;
	
	@Value("${jwt.expiration}")
	private long expiration;
	
	public boolean validateToken(String jwtToken) {
		
		Jwts.parser()
			.setSigningKey(this.secret)
			.parseClaimsJws(jwtToken);
		
		return true;
	}
	
	public String getUsernameFromToken(String jwtToken) {
		
		String usernameFromToken = Jwts.parser()
			.setSigningKey(this.secret)
			.parseClaimsJws(jwtToken)
			.getBody()
			.getSubject();
		
		return usernameFromToken;
	}
	
	public String generateToken(Authentication auth) {
		
		User user = (User)auth.getPrincipal();
		
		String username = user.getUsername();
		Collection<GrantedAuthority> roles = user.getAuthorities();
		
		Claims claims = Jwts.claims()
				.setSubject(username);
		
		List<String> usersRoles = roles.stream()
			.map(r -> "ROLE_"+r.getAuthority())
			.collect(Collectors.toList());
		
		claims.put("roles", usersRoles);
		
		String jwtToken = Jwts.builder()
			.setClaims(claims)
			.signWith(SignatureAlgorithm.HS512, secret)
			.setIssuedAt(new Date())
			.setExpiration(new Date(System.currentTimeMillis()  + this.expiration * 1000))
			.compact();
		
		return jwtToken;
	}
}
 