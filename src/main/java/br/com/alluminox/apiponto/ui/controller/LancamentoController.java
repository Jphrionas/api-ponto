package br.com.alluminox.apiponto.ui.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alluminox.apiponto.data.services.LancamentoService;
import br.com.alluminox.apiponto.io.dto.LancamentoDTO;
import br.com.alluminox.apiponto.io.http.request.LancamentoRequestModel;
import br.com.alluminox.apiponto.io.http.response.LancamentoResponseModel;
import br.com.alluminox.apiponto.io.http.response.Response;
import br.com.alluminox.apiponto.security.SecurityProperties;

@RestController
@RequestMapping("/v1/lancamento")
public class LancamentoController implements Serializable {
	private static final long serialVersionUID = 1L;

	@Autowired
	private LancamentoService lancamentoService;
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> create(@Valid @RequestBody LancamentoRequestModel requestModel,
			BindingResult result, @RequestHeader("Authorization") String auth) {
		Response<LancamentoResponseModel> response = new Response<>();
		
		if(result.hasErrors()) {
			result.getAllErrors().forEach(error -> response.addError(error.getDefaultMessage()));
			return ResponseEntity.badRequest().body(result);
		}
				String token = auth
						.replace(SecurityProperties.TOKEN_PREFIX, "")
						.trim();
		
		Optional<LancamentoDTO> lancamento = this.lancamentoService.save(requestModel, token);
		response.setData(new ModelMapper().map(lancamento.get(), LancamentoResponseModel.class));
		return ResponseEntity.ok(response);
	}
	
	@GetMapping("{cpf}")
	@Transactional(readOnly = true)
	public ResponseEntity<?> listLancamentos(@PathVariable("cpf") String cpf) {
		Response<List<LancamentoResponseModel>> response = new Response<>();

		List<LancamentoResponseModel> lancamentos = this.lancamentoService
		.findLancamentos(cpf)
		.stream().map(lancamento -> new ModelMapper().map(lancamento, LancamentoResponseModel.class))
		.collect(Collectors.toList());
		response.setData(lancamentos);
		return ResponseEntity.ok(response);
	}
}
