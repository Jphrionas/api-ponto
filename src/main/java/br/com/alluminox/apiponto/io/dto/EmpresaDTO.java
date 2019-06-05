package br.com.alluminox.apiponto.io.dto;

import java.util.Date;

public class EmpresaDTO implements DTO {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String publicId;
	private String privateId;
	private String razaoSocial;
	private String numeroIncricao;
	private String cnpj;
	private Date dataCriacao;
	private Date dataAtualizacao;	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
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
	
	public Date getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(Date dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	
	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}
	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}
	
	public String getPrivateId() {
		return privateId;
	}
	public void setPrivateId(String privateId) {
		this.privateId = privateId;
	}
}
