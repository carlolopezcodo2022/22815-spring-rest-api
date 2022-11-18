package ar.com.codoacodo.spring.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
@Entity
@Table(name = "cupones")
public class Cupones {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "nombre",nullable = false, length = 100)
	private String nombre;
	
	@Column(name = "codigo",unique = true, length = 10)
	private String codigo;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_vigencia_desde",nullable = false)	
	private Date fechaVigenciaDesde;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "fecha_vigencia_hasta",nullable = true)
	private Date fechaVigenciaHasta;
	
	@Column(name = "descuento",nullable = true)
	private Float descuento;
}
