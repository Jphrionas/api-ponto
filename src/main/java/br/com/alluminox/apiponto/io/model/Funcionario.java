package br.com.alluminox.apiponto.io.model;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.alluminox.apiponto.io.model.enums.Perfil;

@Entity
@Table(name = "funcionario")
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "public_id", nullable = false)
	private String publicId;
	
	@Column(name="image", nullable = true)
	private String image = "https://alluminox.com.br/img/user.jpg";
	
	@Column(name="token", nullable = true)
	private String token;
	
	@Column(name="nome", nullable = false)
	private String nome;
	
	@Column(name="sobrenome", nullable = false)
	private String sobrenome;
	
	@Column(name="email", nullable = false, unique = true)
	private String email;
	
	@Column(name="senha", nullable = false)
	private String senha;
	
	@Column(name="cpf", nullable = false)
	private String cpf;
	
	@Column(name="rg", nullable = false)
	private String rg;
	
	@Column(name="valorHora", nullable = true)
	private BigDecimal valorHora;
	
	// Padrao são 9 horas, que sao de 7:30 a 17:30 - 1hr de almoco = 9horas
	@Column(name="qnt_horas_trabalho_dia", nullable = true)
	private Float quantidadeHorasTrabalhoDia;
	
	// Padrao sao 1 hora
	@Column(name="qnt_horas_almoco", nullable = true)
	private Float quantidadeHorasAlmoco;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_nascimento", nullable = true)
	private Date dataNascimento;

	@Temporal(TemporalType.DATE)
	@Column(name="data_criacao", nullable = true)
	private Date dataCriacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name="data_atualizacao", nullable = true)
	private Date dataAtualizacao;

	@Enumerated(EnumType.STRING)
	private Perfil perfil;
	
	@ManyToOne(fetch = FetchType.EAGER)
	private Empresa empresa;
	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "funcionario", orphanRemoval = true, fetch = FetchType.LAZY)
	private List<Lancamento> lancamentos;

	private Long expirationTime;
	
	public Funcionario() {}
	
	@PrePersist
	public void prePersist() {
		Date date = new Date();
		if(dataCriacao == null) 
			this.dataCriacao = date;			
		
		if(image == null || image.isEmpty())
			image = "img/foto-indisponivel.png";
		
		this.dataAtualizacao = date;
		this.publicId = UUID.randomUUID().toString();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.dataAtualizacao = new Date();
	}

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

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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

	public BigDecimal getValorHora() {
		return valorHora;
	}
	public void setValorHora(BigDecimal valorHora) {
		this.valorHora = valorHora;
	}

	public Float getQuantidadeHorasTrabalhoDia() {
		return quantidadeHorasTrabalhoDia;
	}
	public void setQuantidadeHorasTrabalhoDia(Float quantidadeHorasTrabalhoDia) {
		this.quantidadeHorasTrabalhoDia = quantidadeHorasTrabalhoDia;
	}

	public Float getQuantidadeHorasAlmoco() {
		return quantidadeHorasAlmoco;
	}
	public void setQuantidadeHorasAlmoco(Float quantidadeHorasAlmoco) {
		this.quantidadeHorasAlmoco = quantidadeHorasAlmoco;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}
	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
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

	public Perfil getPerfil() {
		return perfil;
	}
	public void setPerfil(Perfil perfil) {
		this.perfil = perfil;
	}

	public Empresa getEmpresa() {
		return empresa;
	}
	public void setEmpresa(Empresa empresa) {
		this.empresa = empresa;
	}

	public List<Lancamento> getLancamentos() {
		return lancamentos;
	}
	public void setLancamentos(List<Lancamento> lancamentos) {
		this.lancamentos = lancamentos;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public Long getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Long expirationTime) {
		this.expirationTime = expirationTime;
	}


}
