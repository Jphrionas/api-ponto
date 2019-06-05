package br.com.alluminox.apiponto.ui.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.alluminox.apiponto.io.http.request.EmpresaRequestModel;

@RestController
@RequestMapping("/v1/empresa")
public class EmpresaController implements Serializable {
	private static final long serialVersionUID = 1L;

	@PostMapping
	@org.springframework.transaction.annotation.Transactional
	public void save(@RequestBody EmpresaRequestModel empresaRequestModel) {}
}
