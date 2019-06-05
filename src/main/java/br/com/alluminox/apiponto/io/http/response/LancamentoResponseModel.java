package br.com.alluminox.apiponto.io.http.response;

import java.io.Serializable;
import java.util.Date;

import br.com.alluminox.apiponto.io.model.enums.TipoLancamento;

public class LancamentoResponseModel implements Serializable {
	private static final long serialVersionUID = 1L;

	private String publicId;
	private String descricao;
	private Date data;
	private TipoLancamento tipoLancamento;

	public String getPublicId() {
		return publicId;
	}

	public void setPublicId(String publicId) {
		this.publicId = publicId;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}

	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}

}
