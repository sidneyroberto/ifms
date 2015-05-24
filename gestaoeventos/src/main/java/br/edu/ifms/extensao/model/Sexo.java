package br.edu.ifms.extensao.model;

public enum Sexo {

	M("M"), F("F");

	private final String descricao;

	private Sexo(String descricao) {
		this.descricao = descricao;
	}

	public String toString() {
		return descricao;
	}
}
