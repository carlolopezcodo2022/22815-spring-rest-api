package ar.com.codoacodo.spring.services;

import ar.com.codoacodo.spring.domain.Ordenes;

public interface OrdenService {

	public Ordenes save(Ordenes entity);

	public Ordenes getById(Long id);
}
