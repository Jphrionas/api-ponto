package br.com.alluminox.apiponto.io.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name="public_id", nullable = false)
	private String publicId;
	
	@Column(name="private_id", nullable = false)
	private String privateId;
	
	@Column(name = "razao_social", nullable = false)
	private String razaoSocial;
	
	@Column(name = "numero_inscricao", nullable = false)
	private String numeroInscricao;
	
	@Column(name = "cnpj", nullable = false)
	private String cnpj;
	
	@Column(name="data_criacao", nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataCriacao;
	
	@Column(name="data_atualizacao", nullable = false)
	@Temporal(TemporalType.TIMESTAMP)
	private Date dataAtualizacao;
	
	@OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "empresa", fetch = FetchType.LAZY)
	private List<Funcionario> funcionarios;
	
	public Empresa() {}
	
	public Empresa(Long id) {
		this.id = id;
	}

	@PrePersist
	public void prePersist() {
		Date date = new Date();
		if(dataCriacao == null) {
			this.dataCriacao = date;			
		}
		
		this.dataAtualizacao = date;
		this.setPublicId(UUID.randomUUID().toString());
		this.privateId = UUID.randomUUID().toString();
	}
	
	@PreUpdate
	public void preUpdate() {
		this.dataAtualizacao = new Date();
	}
	
	
	/*
	 * Getters and Setters
	 */
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
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

	public void setNumeroInscricao(String numeroInscricao) {
		this.numeroInscricao = numeroInscricao;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public Date getDataAtualizacao() {
		return dataAtualizacao;
	}

	public void setDataAtualizacao(Date dataAtualizacao) {
		this.dataAtualizacao = dataAtualizacao;
	}

	public List<Funcionario> getFuncionarios() {
		return funcionarios;
	}

	public void setFuncionarios(List<Funcionario> funcionarios) {
		this.funcionarios = funcionarios;
	}

	public String getPrivateId() {
		return privateId;
	}

	public void setPrivateId(String privateId) {
		this.privateId = privateId;
	}

	public static void main(String[] args) {
		System.out.println(UUID.randomUUID().toString());
	}

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}
}

