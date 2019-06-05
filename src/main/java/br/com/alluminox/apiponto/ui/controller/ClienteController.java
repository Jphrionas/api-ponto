package br.com.alluminox.apiponto.ui.controller;

import java.io.Serializable;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cliente")
public class ClienteController 
	implements Serializable {

	private static final long serialVersionUID = 1L;

	public void get() {}
	public void getAll() {}
	
	public void post() {}
	public void put() {}
	public void delete() {}
}
