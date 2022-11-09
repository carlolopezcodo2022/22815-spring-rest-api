package ar.com.codoacodo.spring.domain;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "users")
public class Users {
	//ctrl+shift+o
	@Id
	private Long id;
	
	@Column(name = "username",nullable = false, unique = true)	
	private String username;
	
	@Column(name="password",nullable = false)
	private String password;
	
	/*para la otra clase*/
	private Set<Roles> roles;
}
