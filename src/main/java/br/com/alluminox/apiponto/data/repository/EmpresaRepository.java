package br.com.alluminox.apiponto.data.repository;

import java.util.Optional;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alluminox.apiponto.io.model.Empresa;

@Repository
public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, Long> {
	
	Optional<Empresa> findByCnpj(String cnpj);
	Optional<Empresa> findByRazaoSocial(String razaoSocial);
	Optional<Empresa> findByPublicId(String razaoSocial);
	
}
