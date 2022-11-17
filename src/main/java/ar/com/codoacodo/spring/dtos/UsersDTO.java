package ar.com.codoacodo.spring.dtos;

import java.util.Set;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UsersDTO {
	private String username;
	private Set<String> roles;
}
