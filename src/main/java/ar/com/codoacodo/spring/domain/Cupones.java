package ar.com.codoacodo.spring.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Cupones {

	private Long id;
	private String nombre;
	private String codigo;
	private Date fechaVigenciaDesde;
	private Date fechaVigenciaHasta;
	private Float descuento;
}
