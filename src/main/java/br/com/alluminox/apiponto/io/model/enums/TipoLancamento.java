package br.com.alluminox.apiponto.io.model.enums;

public enum TipoLancamento {
	INICIO_TRABALHO("INICIO_ALMOCO"),
	INICIO_ALMOCO("TERMINO_ALMOCO"),
	TERMINO_ALMOCO("TERMINO_TRABALHO"),
	TERMINO_TRABALHO("TERMINO_TRABALHO");
	
	private String next;

	private TipoLancamento(String next) {
		this.next = next;
	}

	public String getNext() {
		return next;
	}

}
