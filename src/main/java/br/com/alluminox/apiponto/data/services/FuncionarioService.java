package br.com.alluminox.apiponto.data.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.alluminox.apiponto.data.repository.EmpresaRepository;
import br.com.alluminox.apiponto.data.repository.FuncionarioRepository;
import br.com.alluminox.apiponto.io.dto.FuncionarioDTO;
import br.com.alluminox.apiponto.io.http.request.FuncionarioRequestModel;
import br.com.alluminox.apiponto.io.model.Empresa;
import br.com.alluminox.apiponto.io.model.Funcionario;
import br.com.alluminox.apiponto.security.generator.PasswordGenerator;

@Service
public class FuncionarioService implements IService<Funcionario ,FuncionarioRequestModel, FuncionarioDTO>,
UserDetailsService {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PasswordGenerator encoder;
	
	@Autowired
	private FuncionarioRepository funcionarioRepository;
	
	@Autowired
	private EmpresaRepository empresaRepository;
	
	@Override
	public Optional<FuncionarioDTO> save(FuncionarioRequestModel requestModel) {
		Optional<Empresa> empresaOpt = empresaRepository.findByCnpj(requestModel.getEmpresa().getCnpj());
		
		if(empresaOpt == null) throw new RuntimeException("A empresa atribuida a este não existe");
		
		if(funcionarioRepository.findByCpf(requestModel.getEmpresa().getCnpj(), requestModel.getCpf()) != null) {
			throw new RuntimeException("O funcionario já  possui o cadastro!");
		}
		
		
		Optional<FuncionarioDTO> dtoOpt = transformDto(requestModel);
		Optional<Funcionario> entityOpt = transformEntity(dtoOpt.get());
		
		Funcionario entity = entityOpt.get();
		entity.setEmpresa(new Empresa(empresaOpt.get().getId()));
		entity.setSenha(encoder.generate(entity.getSenha()));
		
		Funcionario entitySaved = funcionarioRepository.save(entity);
		entitySaved.setEmpresa(empresaOpt.get());
		
		return Optional.ofNullable(modelMapper.map(entitySaved, FuncionarioDTO.class));
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Funcionario funcionario = Optional.ofNullable(this.funcionarioRepository.checkFuncionario(username)).orElseThrow(() -> 
		new UsernameNotFoundException("User is not found by username: " + username));
		
		List<GrantedAuthority> authorties = new ArrayList<>();
		authorties.add(new SimpleGrantedAuthority(funcionario.getPerfil().name()));
	
		return new User(funcionario.getEmail(), funcionario.getSenha(), true, true, true, true, authorties);
	}	
	
	public Optional<Funcionario> findByEmail(String email) {
		return	Optional.ofNullable(this.funcionarioRepository.checkFuncionario(email));
	}
	
	
	@Override
	public Optional<FuncionarioDTO> transformDto(FuncionarioRequestModel requestModel) {
		return Optional.ofNullable(modelMapper.map(requestModel, FuncionarioDTO.class));
	}

	@Override
	public Optional<Funcionario> transformEntity(FuncionarioDTO dto) {
		return Optional.ofNullable(modelMapper.map(dto, Funcionario.class));
	}

	/* Implements THIS */
	public void update(FuncionarioRequestModel requestModel) {
		//	Optional<FuncionarioDTO> dtoOpt = transformDto(requestModel);
		//  Optional<Funcionario> entityOpt = transformEntity(dtoOpt.get());
		//  Funcionario entity = entityOpt.get();
	}

	public void updateToken(Funcionario funcionario) {
		Funcionario db = this.findByEmail(funcionario.getEmail()).get();
		if(db != null) {
			db.setToken(funcionario.getToken());
			db.setExpirationTime(funcionario.getExpirationTime());
			
			this.funcionarioRepository.save(db);
		}
	}

	
	public void delete(String cpf) {
		this.funcionarioRepository.deleteUser(cpf);
	}

	
	public Optional<Funcionario> find(String cnpj, String cpf) {
		return Optional.ofNullable(this.funcionarioRepository.findByCpf(cnpj, cpf));
	}


}
