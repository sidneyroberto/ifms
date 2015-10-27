package br.edu.ifms.lp3.model;

public class Retangulo implements FormaGeometrica {

	private double altura;

	private double largura;

	public double getAltura() {
		return altura;
	}

	public void setAltura(double altura) {
		this.altura = altura;
	}

	public double getLargura() {
		return largura;
	}

	public void setLargura(double largura) {
		this.largura = largura;
	}

	@Override
	public double calculaArea() {
		return largura * altura;
	}

}
