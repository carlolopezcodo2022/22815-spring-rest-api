package ar.com.codoacodo.spring.domain;

import lombok.Data;

@Data
public class EstadoOrdenes {

	private Long id;
	private String descripcion;
	private String descripcionCorta;
	private Integer estadoFinal; /*boolean 0 false 1 true??*/
}
