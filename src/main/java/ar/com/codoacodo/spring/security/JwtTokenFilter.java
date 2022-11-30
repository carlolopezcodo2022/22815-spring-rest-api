package ar.com.codoacodo.spring.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;

public class JwtTokenFilter extends OncePerRequestFilter {

	@Autowired
	private JwtProvider jwtProvider;
		
	@Autowired
	private MyUserDetailService myUserServiceDetail;
	
	@Override
	protected void doFilterInternal(
			HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		//recibe un string: jwt
		String jwt = getToken(request);
		
		//delega en el siguiente filtro Filter Chain
		if(jwt == null) {
			filterChain.doFilter(request, response);
			return;
		}
		
		//delega en el siguiente filtro Filter Chain		
		if(!this.jwtProvider.validateToken(jwt)) {
			filterChain.doFilter(request, response);
			return;
		}
		
		//username viene informado en el jwt
		String usernameFromJwtToken = this.jwtProvider.getUsernameFromToken(jwt);
		
		//validar que sea valido
		UserDetails userDetails = myUserServiceDetail.loadUserByUsername(usernameFromJwtToken);
		
		//ahora debo guardar el usuario en el contexto de seguridad
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
		
		//si, guardo en el contexto de seguridad a auth
		SecurityContextHolder.getContext().setAuthentication(auth);
		
		filterChain.doFilter(request, response);
	}

	private String getToken(HttpServletRequest request) {
		String header = request.getHeader("Authorization");
		//clausulas de guarda
		//java guards (replace if else)
		
		if(header == null) {
			return header;
		}
		
		if(!header.startsWith("Bearer")) {
			return header;
		}
		
		return header.replace("Bearer", "");
	}

}
