package 	br.com.alluminox.apiponto.data.services;

import java.util.Optional;

import javax.validation.Valid;

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
		if(find(requestModel.getCnpj()).isPresent()) {
			throw new RuntimeException("A empresa já está cadastrada");
		}
	
		Optional<EmpresaDTO> empresaDtoOpt = transformDto(requestModel);
		Optional<Empresa> empresaOpt = transformEntity(empresaDtoOpt.get());
		
		// Salvar
		Empresa empresa = empresaOpt.get();
		empresa = empresaRepository.save(empresa);
		
		
		return Optional.ofNullable(modelMapper.map(empresa, EmpresaDTO.class));
	}
	
	public Optional<EmpresaDTO> updateBody(@Valid EmpresaRequestModel requestModel) {
		Optional<Empresa> empresaOpt = find(requestModel.getCnpj());
		if(!find(requestModel.getCnpj()).isPresent()) {
			throw new RuntimeException("A empresa não está cadastrada em nossa base!");
		}
	
		Empresa empresa = empresaOpt.get();
		empresa.setCnpj(requestModel.getCnpj());
		empresa.setRazaoSocial(requestModel.getRazaoSocial());
		empresa.setNumeroInscricao(requestModel.getNumeroInscricao());
		empresa = this.empresaRepository.save(empresa);
		
		return Optional.ofNullable(modelMapper.map(empresa, EmpresaDTO.class));
	}

	public Optional<Empresa> find(String cnpj) {
		return empresaRepository.findByCnpj(cnpj);
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
