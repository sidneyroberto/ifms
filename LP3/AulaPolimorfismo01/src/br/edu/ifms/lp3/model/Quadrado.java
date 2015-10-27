package br.edu.ifms.lp3.model;

public class Quadrado implements FormaGeometrica {

	private double lado;

	public double getLado() {
		return lado;
	}

	public void setLado(double lado) {
		this.lado = lado;
	}

	@Override
	public double calculaArea() {
		return Math.pow(lado, 2);
	}

}
