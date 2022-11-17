package ar.com.codoacodo.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "roles")
public class Roles {
	
	@Id
	private Long id;
	
	@Column(name = "role",length = 50,nullable = false,unique = true)
	private String role;
}
