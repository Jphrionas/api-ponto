package br.com.alluminox.apiponto.data.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.alluminox.apiponto.io.model.Lancamento;

@Repository
public interface LancamentoRepository extends PagingAndSortingRepository<Lancamento, Long>{
	
	@Query("select l from Lancamento l join fetch l.funcionario f where f.cpf = :cpf")
	List<Lancamento> findAllByFuncionario(@Param("cpf") String cpf);
	
	@Query("select l from Lancamento l join fetch l.funcionario f where f.token = :token order by l.id  desc")
	List<Lancamento> findLastLancamentoByFuncionario(@Param("token") String token);
	
	@Query("select l from Lancamento l join fetch l.funcionario f where f.token = :token order by l.id desc")
	List<Lancamento> findLancamentosDia(@Param("token")  String token);
}
