package br.edu.ifms.lp3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifms.lp3.model.Circulo;
import br.edu.ifms.lp3.model.Quadrado;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ColorPicker;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

public class FormasController implements Initializable {

	@FXML
	private Circle circulo;

	@FXML
	private Rectangle quadrado;

	@FXML
	private Button botaoAumentar;

	@FXML
	private Button botaoDiminuir;

	@FXML
	private ColorPicker seletorCores;

	private double incrementoPositivo = 1.05;

	private double incrementoNegativo = 0.95;


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		Circulo formaCirculo = new Circulo(circulo);
		Quadrado formaQuadrado = new Quadrado(quadrado);

		seletorCores.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				Color cor = seletorCores.getValue();
				formaCirculo.alteraCor(cor);
				formaQuadrado.alteraCor(cor);
			}
		});

		botaoAumentar.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				formaCirculo.alteraTamanho(incrementoPositivo);
				formaQuadrado.alteraTamanho(incrementoPositivo);
			}
		});

		botaoDiminuir.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				formaCirculo.alteraTamanho(incrementoNegativo);
				formaQuadrado.alteraTamanho(incrementoNegativo);
			}
		});
	}

}
