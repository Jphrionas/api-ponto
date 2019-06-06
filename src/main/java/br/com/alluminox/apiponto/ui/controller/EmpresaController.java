package br.com.alluminox.apiponto.ui.controller;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alluminox.apiponto.data.services.EmpresaService;
import br.com.alluminox.apiponto.data.services.FuncionarioService;
import br.com.alluminox.apiponto.io.dto.EmpresaDTO;
import br.com.alluminox.apiponto.io.http.request.EmpresaRequestModel;
import br.com.alluminox.apiponto.io.http.response.EmpresaResponseModel;
import br.com.alluminox.apiponto.io.http.response.FuncionarioResponseModel;
import br.com.alluminox.apiponto.io.http.response.Response;
import br.com.alluminox.apiponto.io.model.Empresa;
import br.com.alluminox.apiponto.io.model.Funcionario;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/v1/empresa") // Vesionamento por Header : X-API-VERSION
@Api(value = "Resources para controller")
public class EmpresaController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private EmpresaService empresaService;

	@Autowired
	private FuncionarioService funcionarioService;
	
			
	@PostMapping
	@Transactional
	@ApiOperation(value = "Create a new empresa object", consumes = "application/json",
			httpMethod = "POST")
	public ResponseEntity<?> save(@Valid  @RequestBody EmpresaRequestModel empresaRequestModel, 
			BindingResult result) {
		Response<EmpresaResponseModel> response = new Response<>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addError(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<EmpresaDTO> empresa = this.empresaService.save(empresaRequestModel);
		EmpresaDTO empresaDTO = empresa.get();
		response.setData(new ModelMapper().map(empresaDTO, EmpresaResponseModel.class));
		return ResponseEntity.ok(response);
	}	
	
	@PutMapping("{cnpj}")
	@Transactional
	public ResponseEntity<?> update(@Valid @RequestBody EmpresaRequestModel empresaRequestModel,
			BindingResult result) {
		
		Response<EmpresaResponseModel> response = new Response<>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addError(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<EmpresaDTO> empresa = this.empresaService.updateBody(empresaRequestModel);
		EmpresaDTO empresaDTO = empresa.get();
		response.setData(new ModelMapper().map(empresaDTO, EmpresaResponseModel.class));
		return ResponseEntity.ok(response);
	}

	
	@GetMapping("{cnpj}")
	@Transactional(readOnly = true)
	public ResponseEntity<?> find(@PathVariable("cnpj") String cnpj) {
		Response<EmpresaResponseModel> response = new Response<>();

		Optional<Empresa> empresa = this.empresaService.find(cnpj);
		if(!empresa.isPresent()) {
			response.addError("A empresa não existe!");
			return ResponseEntity.badRequest().body(response);			
		}
		response.setData(new ModelMapper().map(empresa.get(), EmpresaResponseModel.class));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{cnpj}/{cpf}")
	@Transactional(readOnly = true)
	public ResponseEntity<?> findFuncionarioEmpresa(@PathVariable("cnpj") String cnpj,
			@PathVariable("cpf") String cpf) {
		
		Response<FuncionarioResponseModel> response = new Response<>();
		Optional<Funcionario> opt = this.funcionarioService.find(cnpj, cpf);
		
		System.out.println(opt.get());
		if(!opt.isPresent()) {
			response.addError("O funcionario não existe!");
			return ResponseEntity.badRequest().body(response);
		}
		
		response.setData(new ModelMapper().map(opt.get(), FuncionarioResponseModel.class));
		return ResponseEntity.ok(response);
		
	}

}	
