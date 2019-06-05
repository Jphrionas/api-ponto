package br.com.alluminox.apiponto.data.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.alluminox.apiponto.io.model.Lancamento;

@Repository
public interface LancamentoRepository extends PagingAndSortingRepository<Lancamento, Long>{

}
