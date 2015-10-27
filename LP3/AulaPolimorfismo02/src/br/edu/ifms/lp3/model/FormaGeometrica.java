package br.edu.ifms.lp3.model;

import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

public abstract class FormaGeometrica {

	protected Shape forma;

	protected static final double TAMANHO_MINIMO = 50;

	protected static final double TAMANHO_MAXIMO = 350;

	protected FormaGeometrica(Shape forma) {
		this.forma = forma;
	}

	public void alteraCor(Color cor) {
		forma.setFill(cor);
	}

	public abstract void alteraTamanho(double percentual);


}
