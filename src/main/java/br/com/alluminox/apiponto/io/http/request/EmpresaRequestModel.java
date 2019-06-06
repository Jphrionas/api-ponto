package br.com.alluminox.apiponto.io.http.request;

import javax.validation.constraints.NotEmpty;

public class EmpresaRequestModel implements RequestModel {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "O campo razaoSocial é obrigatório")
	private String razaoSocial;
	
	@NotEmpty(message = "O campo numeroInscrição é obrigatório")
	private String numeroInscricao;
	
	@NotEmpty(message = "O campo cnpj é obrigatório")
	private String cnpj;
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getNumeroInscricao() {
		return numeroInscricao;
	}
	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
}
