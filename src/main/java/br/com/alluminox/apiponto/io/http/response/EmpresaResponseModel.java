package br.com.alluminox.apiponto.io.http.response;

import java.io.Serializable;

public class EmpresaResponseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String publicId;
	private String razaoSocial;
	private String numeroInscricao;
	private String cnpj;
	
	public String getPublicId() {
		return publicId;
	}
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}
	
	public String getRazaoSocial() {
		return razaoSocial;
	}
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}
	
	public String getNumeroInscricao() {
		return numeroInscricao;
	}
	public void setNumeroIncricao(String numeroInscricao) {
		this.setNumeroInscricao(numeroInscricao);
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}
	
}
