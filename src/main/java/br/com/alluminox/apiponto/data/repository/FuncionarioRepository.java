package br.com.alluminox.apiponto.data.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alluminox.apiponto.io.model.Funcionario;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>{

	@Query("select f from Funcionario f join fetch f.empresa e where f.email = :email and  e.publicId = :empresaId")
	Funcionario findByEmail(@Param("email") String email, @Param("empresaId") String empresaId);
	
	@Query("select f from Funcionario f join fetch f.empresa e where f.publicId = :publicId")
	Funcionario findByPublicId(@Param("publicId") String publicId);

	
	@Query("select f from Funcionario f where f.email = :email")
	Funcionario checkFuncionario(@Param("email") String email);

	@Query("select f from Funcionario f where f.token = :token")
	Funcionario checktTokenFuncionario(@Param("token") String token);
	
	@Query("select f from Funcionario f join fetch f.empresa e where e.cnpj = :cnpj and f.cpf = :cpf ")
	Funcionario findByCpf(@Param("cnpj") String cnpj, @Param("cpf") String cpf);

	@Modifying
	@Query("delete from Funcionario f where f.cpf = :cpf")
	void deleteUser(@Param("cpf") String cpf);

	@Query("select f from Funcionario f join fetch f.empresa e where f.cpf = :cpf ")
	Funcionario findByCpf(@Param("cpf") String cpf);

}
