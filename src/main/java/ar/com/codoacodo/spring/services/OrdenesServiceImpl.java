package ar.com.codoacodo.spring.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ar.com.codoacodo.spring.domain.Ordenes;
import ar.com.codoacodo.spring.repository.OrdenesRepository;

@Service
@Transactional
public class OrdenesServiceImpl implements OrdenService {

	@Autowired
	private OrdenesRepository repository;
	
	@Override
	public Ordenes save(Ordenes entity) {
		//alguna que otra valdacion!
		
		return this.repository.save(entity);
	}

	@Override
	public Ordenes getById(Long id) {
		return this.repository.findById(id).orElse(Ordenes.builder().build());
	}

	@Override
	public void update(Ordenes ordenDB) {
		this.repository.save(ordenDB);	
	}

	@Override
	public void eliminar(Long id) {
		this.repository.deleteById(id);		
	}

	@Override
	public List<Ordenes> findAll() {
		return this.repository.findAll();
	}
}
