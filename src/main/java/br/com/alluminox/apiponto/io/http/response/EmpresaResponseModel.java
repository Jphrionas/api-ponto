package br.com.alluminox.apiponto.io.http.response;

import java.io.Serializable;

public class EmpresaResponseModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String publicId;
	private String razaoSocial;
	private String numeroIncricao;
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
	
	public String getNumeroIncricao() {
		return numeroIncricao;
	}
	public void setNumeroIncricao(String numeroIncricao) {
		this.numeroIncricao = numeroIncricao;
	}
	
	public String getCnpj() {
		return cnpj;
	}
	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}
	
}
