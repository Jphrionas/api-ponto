package br.com.alluminox.apiponto.data.services;

import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alluminox.apiponto.data.repository.EmpresaRepository;
import br.com.alluminox.apiponto.io.dto.EmpresaDTO;
import br.com.alluminox.apiponto.io.http.request.EmpresaRequestModel;
import br.com.alluminox.apiponto.io.model.Empresa;

@Service
public class EmpresaService implements IService<Empresa, EmpresaRequestModel, EmpresaDTO> {
	private static final long serialVersionUID = 1L;

	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Optional<EmpresaDTO> save(EmpresaRequestModel requestModel) {
		if(empresaExists(requestModel.getCnpj())) {
			throw new RuntimeException("A empresa já está cadastrada");
		}
		
		Optional<EmpresaDTO> empresaDtoOpt = transformDto(requestModel);
		Optional<Empresa> empresaOpt = transformEntity(empresaDtoOpt.get());
		
		// Salvar
		Empresa empresa = empresaOpt.get();
		empresaRepository.save(empresa);
		
		return Optional.ofNullable(modelMapper.map(empresa, EmpresaDTO.class));
	}
	
	
	public boolean empresaExists(String cnpj) {
		return empresaRepository.findByCnpj(cnpj).isPresent();
	}

	@Override
	public Optional<EmpresaDTO> transformDto(EmpresaRequestModel requestModel) {
		return Optional.ofNullable(this.modelMapper.map(requestModel, EmpresaDTO.class));
	}
	
	@Override
	public Optional<Empresa> transformEntity(EmpresaDTO dto) {
		return Optional.ofNullable(this.modelMapper.map(dto, Empresa.class));
	}
	

}
