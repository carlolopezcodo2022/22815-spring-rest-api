package ar.com.codoacodo.spring.services;

import java.util.Optional;

import ar.com.codoacodo.spring.domain.Users;

public interface UsersService {
	public Optional<Users> obtenerPorId(Long id);
}
