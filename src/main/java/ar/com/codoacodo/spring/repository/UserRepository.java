package ar.com.codoacodo.spring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import ar.com.codoacodo.spring.domain.Users;
/*
 * repositorio de la entidad User
 */
@Repository
public interface UserRepository extends JpaRepository<Users, Long>{

	//named method
	//select * from users where nombre = 'jose'
	public Users findByUsername(String name);
	
	/*
	@Query(
			nativeQuery = true,
			value = "select * from table where",
			name = "buscarAJuanQuery"
	)
	public Users buscarAjuan();
	*/
	/*
	@Query(
			value = "from Users as u where u.username =:name"
	)
	public Users buscarAjuan(String name);
	*/
}
