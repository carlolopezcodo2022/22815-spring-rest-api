package ar.com.codoacodo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import ar.com.codoacodo.spring.domain.Ordenes;

@Repository
public interface OrdenesRepository extends JpaRepository<Ordenes, Long>{
//ctrl+o
}
