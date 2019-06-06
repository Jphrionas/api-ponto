package br.com.alluminox.apiponto.ui.controller;

import java.io.Serializable;
import java.util.Optional;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alluminox.apiponto.data.services.FuncionarioService;
import br.com.alluminox.apiponto.io.dto.FuncionarioDTO;
import br.com.alluminox.apiponto.io.http.request.FuncionarioRequestModel;
import br.com.alluminox.apiponto.io.http.response.FuncionarioResponseModel;
import br.com.alluminox.apiponto.io.http.response.Response;

@RestController
@RequestMapping("/v1/funcionario")
public class FuncionarioController 
	implements Serializable {

	private static final long serialVersionUID = 1L;

	@Autowired
	private FuncionarioService funcionarioService;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> create(@Valid @RequestBody FuncionarioRequestModel requestModel, BindingResult result) {
		Response<FuncionarioResponseModel> response = new Response<>();
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addError(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(response);
		}
		
		Optional<FuncionarioDTO> dtoOptional = funcionarioService.save(requestModel);
		FuncionarioResponseModel responseModel = modelMapper.map(dtoOptional.get(), FuncionarioResponseModel.class);
		response.setData(responseModel);
		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}
	
}
