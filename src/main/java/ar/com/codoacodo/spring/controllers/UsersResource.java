package ar.com.codoacodo.spring.controllers;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codoacodo.spring.domain.Users;
import ar.com.codoacodo.spring.dtos.UsersDTO;
import ar.com.codoacodo.spring.services.UsersService;

@RestController
public class UsersResource {

	//inyectamos el service de usuario
	@Autowired
	private UsersService userService;
	
	@GetMapping(value="/users/{id}")
	public UsersDTO get(
			@PathVariable(name="id", required = true)
			Long id
		) {	
		//no hace falta verificar si es !=null porque ya se encaga spring
		Optional<Users> users =  this.userService.obtenerPorId(id);
		
		UsersDTO dto = null;
		if(!users.isEmpty()) {
			
			Set<String> rolesStrs = users.get().getRoles()
					.stream()
					.map(r -> "ROLE_"+r.getRole())
					.collect(Collectors.toSet());
			
			dto = UsersDTO.builder()
					.username(users.get().getUsername())
					.roles(rolesStrs)
					.build();			
		}
		return dto;
	}
	
}
