package ar.com.codoacodo.spring.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "socios")
public class Socios {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	private Long id;
	
	@Column(name = "apellido",nullable = false, length = 50)
	private String apellido;
	
	@Column(name = "nombre",nullable = false, length = 60)
	private String nombre;
	
	@Column(name = "codigo",unique = true, length = 6)
	private String codigo;
}
