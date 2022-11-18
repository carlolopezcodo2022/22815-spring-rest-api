package ar.com.codoacodo.spring.controllers;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.codoacodo.spring.domain.Cupones;
import ar.com.codoacodo.spring.domain.EstadoOrdenes;
import ar.com.codoacodo.spring.domain.Ordenes;
import ar.com.codoacodo.spring.domain.Socios;
import ar.com.codoacodo.spring.dtos.OrdenDTO;
import ar.com.codoacodo.spring.services.OrdenService;

@RestController
public class OrdenResource {
	
	@Autowired
	private OrdenService ordenService;
	
	//POST
	//LOCALHOST:8080/orden
	//request 
	/*
	 {
	   id:
	   titulo:
	   precio:
	   sociosId:
	   estadoOrdenId:
	   montoTotal:
	   cuponesId:
	 }
	 * */
		
	@PostMapping(value="/orden")
	public ResponseEntity<Ordenes> post(
			//@Valid
			@RequestBody OrdenDTO ordenDto
			) {
		
		Ordenes ordenDb;
		
		if(ordenDto.getId() == null) {
			ordenDb = Ordenes.builder()
				.montoTotal(ordenDto.getMontoTotal())
				.socio(Socios.builder().id(ordenDto.getSocioId()).build())
				.estado(EstadoOrdenes.builder().id(ordenDto.getSocioId()).build())
				.cupon(ordenDto.getCuponId() != null ? Cupones.builder().id(ordenDto.getSocioId()).build() : null)
				.fechaCreacion(new Date())//ahora se esta creado
				.build();
			this.ordenService.save(ordenDb);
		}
		
		ordenDb = this.ordenService.getById(ordenDto.getId());
		
		return ResponseEntity.ok(ordenDb);
	}
	
}
