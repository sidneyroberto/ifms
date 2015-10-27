package br.edu.ifms.lp3.model;

import javafx.scene.shape.Rectangle;

public class Quadrado extends FormaGeometrica {

	public Quadrado(Rectangle quadrado) {
		super(quadrado);
	}

	@Override
	public void alteraTamanho(double percentual) {
		Rectangle quadrado = (Rectangle) forma;
		double tamanhoLado = quadrado.getWidth();
		tamanhoLado *= percentual;
		if(tamanhoLado >= TAMANHO_MINIMO && tamanhoLado <= TAMANHO_MAXIMO) {
			quadrado.setWidth(tamanhoLado * percentual);
			quadrado.setHeight(tamanhoLado * percentual);
		}
	}

}
