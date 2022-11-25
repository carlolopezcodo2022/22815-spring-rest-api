package ar.com.codoacodo.spring.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.com.codoacodo.spring.domain.Users;
import ar.com.codoacodo.spring.repository.UserRepository;

@Service
@Transactional
public class UsersServiceImpl implements UsersService{
	
	//necesito el/los repositorio
	@Autowired
	private UserRepository userRepository;
	
	//abren una conexion con la db
	public Optional<Users> obtenerPorId(Long id) {
		//si todo sale bien!!
		//commit
		//si existe alguna Exception 
		//rollback
		return this.userRepository.findById(id);
	}

	@Override
	public Users findByName(String name) {
		return this.userRepository.findByUsername(name);//ctrl+shit+i
	}
}
