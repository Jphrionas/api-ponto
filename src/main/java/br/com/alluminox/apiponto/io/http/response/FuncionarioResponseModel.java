package br.com.alluminox.apiponto.io.http.response;

import java.io.Serializable;

import br.com.alluminox.apiponto.io.model.enums.Perfil;

public class FuncionarioResponseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String publicId;
	private String nome;
	private String sobrenome;
	private String email;
	private Perfil perfil;
	
	private EmpresaResponseModel empresa;

	public String getPublicId() {
		return publicId;
	}
	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSobrenome() {
		return sobrenome;
	}
	public void setSobrenome(String sobrenome) {
		this.sobrenome = sobrenome;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}

	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public EmpresaResponseModel getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaResponseModel empresa) {
		this.empresa = empresa;
	}

}
