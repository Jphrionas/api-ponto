package br.com.alluminox.apiponto.io.http.request;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.alluminox.apiponto.io.model.enums.Perfil;

public class FuncionarioRequestModel implements RequestModel {
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "O campo nome é obrigatório")
	private String nome;
	
	@NotEmpty(message = "O campo sobrenome é obrigatório")
	private String sobrenome;
	
	@NotEmpty(message = "O campo e-mail é obrigatório")
	@Email(message = "Insira um e-mail válido")
	private String email;
	
	@NotEmpty(message = "O campo senha é obrigatório")
	@Length(min = 8, max = 16, message = "Insira uma senha entre 8 a 16 caracteres")
	private String senha;
	
	@NotEmpty(message = "O campo cpf é obrigatório")
	private String cpf;
	
	@NotEmpty(message = "O campo rg é obrigatório")
	private String rg;
	
	@NotNull(message = "O campo data de nascimento é obrigatório")
	private Date dataNascimento;
	
	@NotNull(message = "Escolha o perfil do usuario")
	private Perfil perfil;
	
	@NotNull(message = "Atribua a empresa do seu funcionario")
	private EmpresaRequestModel empresa;
	
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
	
	public String getSenha() {
		return senha;
	}
	public void setSenha(String senha) {
		this.senha = senha;
	}
	
	public String getCpf() {
		return cpf;
	}
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	public String getRg() {
		return rg;
	}
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public Date getDataNascimento() {
		return dataNascimento;
	}	
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}
	
	public EmpresaRequestModel getEmpresa() {
		return empresa;
	}
	public void setEmpresa(EmpresaRequestModel empresa) {
		this.empresa = empresa;
	}
	
}
