package ar.com.codoacodo.spring.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
		
	@PreAuthorize("hasAuthority('ADMIN')")
	@PostMapping(value="/orden")
	public ResponseEntity<Ordenes> post(
			//@Valid
			@RequestBody OrdenDTO ordenDto
			) {
		
		Ordenes ordenDb = null;
		
		if(ordenDto.getId() == null) {
			ordenDb = Ordenes.builder()
				.montoTotal(ordenDto.getMontoTotal())
				.socio(Socios.builder().id(ordenDto.getSocioId()).build())
				.estado(EstadoOrdenes.builder().id(ordenDto.getEstadoId()).build())
				.cupon(ordenDto.getCuponId() != null ? Cupones.builder().id(ordenDto.getCuponId()).build() : null)
				.fechaCreacion(new Date())//ahora se esta creado
				.build();
			this.ordenService.save(ordenDb);
		}
		
		ordenDb = this.ordenService.getById(ordenDb.getId());		
		
		return ResponseEntity.status(HttpStatus.CREATED).body(ordenDb);
	}
	
	@PutMapping(value="/orden/{id}")
	public ResponseEntity<?> put(
			@PathVariable(name = "id",required = true) 
			Long id,
			@RequestBody 
			OrdenDTO ordenDto){
		
		//logica
		Ordenes ordenDB = this.ordenService.getById(id);
		
		if(ordenDB == null) {
			//404 no se encontro el recurso /orden/1
			return ResponseEntity.notFound().build();
		}
		
		//409 conflict ctrl+shit+i
		if(ordenDB.isEstadoFinal() || !id.equals(ordenDB.getId())) {
			var res = new HashMap<String, String>();
			res.put("code", "-ABC001");
			res.put("msj","ORDEN EN ESTADO INVALIDA");			
			return ResponseEntity.status(HttpStatus.CONFLICT).body(res);
		}
		
		//actualizo el/los datos!
		if(ordenDto.getEstadoId() != null) { 
			EstadoOrdenes nuevoEstado = EstadoOrdenes.builder().id(ordenDto.getEstadoId()).build();
			ordenDB.setEstado(nuevoEstado);
		}
		//si hay mas datos que modificar, ej: el monto!
		
		this.ordenService.update(ordenDB);
		
		return ResponseEntity.ok(ordenDB);
	}
	
	@GetMapping("/orden/{id}")
	public ResponseEntity<Ordenes> get(
			@PathVariable(name = "id", required = true) 
			Long id
		) {
		
		Ordenes orden = this.ordenService.getById(id);
		
		if(orden == null || orden.getId() == null) {
			//NOT FOUND > 404
			return ResponseEntity.notFound().build();
		}
		
		//POST: 200 > OK
		return ResponseEntity.ok(orden);
	}
	
	@GetMapping(value="/orden",produces = "application/json")
	public ResponseEntity<List<Ordenes>> findAll() {
		//POST: 200 > OK
		return ResponseEntity.ok(this.ordenService.findAll());
	}
	
	@DeleteMapping("/orden/{id}")
	public ResponseEntity<Ordenes> delete(
			@PathVariable(name="id", required = true) Long id
			) {
		
		//ya estoy dentro del metodo!!!!
		try {
			this.ordenService.eliminar(id);
		}catch(RuntimeException re) {
			System.out.println(re.getMessage());
		}
		
		return ResponseEntity.ok(null);
	}
	
	
	
}
