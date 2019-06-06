package br.com.alluminox.apiponto.io.http.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.com.alluminox.apiponto.io.model.enums.TipoLancamento;

public class LancamentoRequestModel implements RequestModel {
	private static final long serialVersionUID = 1L;

	@NotEmpty(message = "O campo descrição é obrigatório")
	private String descricao;

	@NotEmpty(message = "O campo coords é obrigatório")
	private String coords; // x=1,y=2,z=3

	@NotNull(message = "O campo lancamento é obrigatório")
	private TipoLancamento tipoLancamento;

	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getCoords() {
		return coords;
	}
	public void setCoords(String coords) {
		this.coords = coords;
	}

	public TipoLancamento getTipoLancamento() {
		return tipoLancamento;
	}
	public void setTipoLancamento(TipoLancamento tipoLancamento) {
		this.tipoLancamento = tipoLancamento;
	}
}
