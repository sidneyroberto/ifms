package br.edu.ifms.lp3.model;

import javafx.scene.shape.Circle;

public class Circulo extends FormaGeometrica {

	public Circulo(Circle circulo) {
		super(circulo);
	}

	@Override
	public void alteraTamanho(double percentual) {
		Circle circulo = (Circle) forma;
		double raioCirculo = circulo.getRadius();
		raioCirculo *= percentual;
		if(raioCirculo >= TAMANHO_MINIMO && raioCirculo <= TAMANHO_MAXIMO) {
			circulo.setRadius(raioCirculo * percentual);
		}
	}

}
