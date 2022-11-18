package ar.com.codoacodo.spring.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdenDTO {

	private Long id;

	private Long socioId;

	private Long estadoId;

	private Float montoTotal;

	private Long cuponId;
}
