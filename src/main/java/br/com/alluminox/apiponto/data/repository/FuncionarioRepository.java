package br.com.alluminox.apiponto.data.repository;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alluminox.apiponto.io.model.Funcionario;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Long>{

	@Modifying
	@Query("select f from Funcionario f join fetch f.empresa e where f.email = ?1 and  e.publicId = ?2")
	Funcionario findByEmail(String email, String empresaId);
	
	@Modifying
	@Query("select f from Funcionario f join fetch f.empresa e where f.cpf = ?1 and  e.publicId = ?2")
	Funcionario findByCpf(String cpf, String empresaId);
}
