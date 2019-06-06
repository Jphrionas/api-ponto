package br.com.alluminox.apiponto.data.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alluminox.apiponto.data.repository.FuncionarioRepository;
import br.com.alluminox.apiponto.data.repository.LancamentoRepository;
import br.com.alluminox.apiponto.io.dto.LancamentoDTO;
import br.com.alluminox.apiponto.io.http.request.LancamentoRequestModel;
import br.com.alluminox.apiponto.io.model.Funcionario;
import br.com.alluminox.apiponto.io.model.Lancamento;

@Service
public class LancamentoService implements IService<Lancamento, LancamentoRequestModel, LancamentoDTO> {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;

	@Autowired
	private ModelMapper modelMapper;
	
	public Optional<LancamentoDTO> save(LancamentoRequestModel requestModel, String token) {
		
		Funcionario funcionario = funcionarioRepository.checktTokenFuncionario(token);
			
		if(funcionario == null) {
			throw new RuntimeException("Funcionario não existe para o lancamento");	
		}
		
		Lancamento transform = modelMapper.map(requestModel, Lancamento.class);
		transform.setFuncionario(funcionario);
		Lancamento lancamento = this.lancamentoRepository.save(transform);
	
		return Optional.ofNullable(modelMapper.map(lancamento, LancamentoDTO.class));
	}

	public List<Lancamento> findLancamentos(String cpf) {
		List<Lancamento> lancamentos = this.lancamentoRepository.findAllByFuncionario(cpf);
		return Optional.ofNullable(lancamentos).orElse(Collections.emptyList());
	}

	@Override
	public Optional<LancamentoDTO> transformDto(LancamentoRequestModel requestModel) {
		return Optional.ofNullable(modelMapper.map(requestModel, LancamentoDTO.class));
	}

	@Override
	public Optional<Lancamento> transformEntity(LancamentoDTO dto) {
		return Optional.ofNullable(modelMapper.map(dto, Lancamento.class));
	}

	@Override
	public Optional<LancamentoDTO> save(LancamentoRequestModel arg0) {
		return null;
	}

}
