package br.com.alluminox.apiponto.ui.controller;

import java.io.Serializable;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.TimeZone;
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
import br.com.alluminox.apiponto.io.model.Lancamento;
import br.com.alluminox.apiponto.io.model.enums.TipoLancamento;
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
		
		// Pegar o ultimo lancamento
		Lancamento lancamentoVeirifyDB = this.lancamentoService.verificarLancamento(requestModel, token);
		
		Calendar now = Calendar.getInstance(TimeZone.getTimeZone(ZoneId.of("UTC")), new Locale("pt", "BR"));
		now.setTimeZone(TimeZone.getTimeZone("GMT-3"));
		
		Calendar db = Calendar.getInstance();
		db.setTimeZone(TimeZone.getTimeZone("GMT-3"));
		db.setTime(lancamentoVeirifyDB.getData());
		db.setTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC")));
		
		int dayNow = now.get(Calendar.DAY_OF_MONTH);
		int dayDb = now.get(Calendar.DAY_OF_MONTH);
		
		if(lancamentoVeirifyDB.getTipoLancamento().equals(TipoLancamento.TERMINO_TRABALHO)
				&& (dayNow == dayDb)) {
			
			response.addError("Você já finalizou sua jornada de trabalho");
			return ResponseEntity.badRequest().body(response);	
		}
		
		requestModel.setTipoLancamento(TipoLancamento.valueOf(lancamentoVeirifyDB.getTipoLancamento().getNext()));
		Optional<LancamentoDTO> lancamento = this.lancamentoService.save(requestModel, token);
		response.setData(new ModelMapper().map(lancamento.get(), LancamentoResponseModel.class));
		return ResponseEntity.ok(response);
	}

	@GetMapping("dia")
	@Transactional(readOnly = true)
	public ResponseEntity<?> listLancamentosDia(@RequestHeader("Authorization") String auth) {
		Response<List<LancamentoResponseModel>> response = new Response<>();
		
		String token = auth
				.replace(SecurityProperties.TOKEN_PREFIX, "")
				.trim();
		

		List<LancamentoResponseModel> lancamentos = this.lancamentoService.buscarLancamentosDia(token)
		.stream().map(lancamento -> new ModelMapper().map(lancamento, LancamentoResponseModel.class))
		.collect(Collectors.toList());
		response.setData(lancamentos);
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
