package br.edu.ifms.lp4.model;

public class TipoArquivo {

	private String extensao;

	private Integer quantidadeEncontrada;

	private Double tamanhoEmMB;

	public String getExtensao() {
		return extensao;
	}

	public void setExtensao(String extensao) {
		this.extensao = extensao;
	}

	public Integer getQuantidadeEncontrada() {
		return quantidadeEncontrada;
	}

	public void setQuantidadeEncontrada(Integer quantidadeEncontrada) {
		this.quantidadeEncontrada = quantidadeEncontrada;
	}

	public Double getTamanhoEmMB() {
		return tamanhoEmMB;
	}

	public void setTamanhoEmMB(Double tamanhoEmMB) {
		this.tamanhoEmMB = tamanhoEmMB;
	}

}
