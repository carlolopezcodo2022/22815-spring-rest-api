package ar.com.codoacodo.spring.domain;

import java.util.Date;

import lombok.Data;

@Data
public class Ordenes {

	private Long id;
	private Date fechaCreacion;
	private Socios socio;
	private EstadoOrdenes estado;
	private Float montoTotal;
	private Cupones cupon;
	
}
