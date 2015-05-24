package br.edu.ifms.extensao.model;

public enum Periodo {

	MATUTINO("MATUTINO"), VESPERTINO("VESPERTINO"), NOTURNO("NOTURNO"), INTEGRAL(
			"INTEGRAL");

	private final String descricao;

	private Periodo(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return descricao;
	}
}
