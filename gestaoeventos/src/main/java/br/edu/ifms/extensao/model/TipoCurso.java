package br.edu.ifms.extensao.model;

public enum TipoCurso {

	MEDIO("MÉDIO"), SUPERIOR("SUPERIOR"), ETEC("E-TEC"), PROEJA("PROEJA"), PRONATEC(
			"PRONATEC");

	private final String descricao;

	private TipoCurso(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return descricao;
	}
}
