package br.edu.ifms.lp3.controller;

import java.net.URL;
import java.util.ResourceBundle;

import br.edu.ifms.lp3.model.FormaGeometrica;
import br.edu.ifms.lp3.model.Quadrado;
import br.edu.ifms.lp3.model.Retangulo;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;

public class CalculoAreaController implements Initializable {

	@FXML
	private Label labelAltura;

	@FXML
	private Label labelLado;

	@FXML
	private Label labelLargura;

	@FXML
	private RadioButton radioQuadrado;

	@FXML
	private RadioButton radioRetangulo;

	@FXML
	private TextField campoLado;

	@FXML
	private TextField campoLargura;

	@FXML
	private TextField campoAltura;

	@FXML
	private Button botaoCalcular;

	@Override
	public void initialize(URL url, ResourceBundle bundle) {
		botaoCalcular.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				FormaGeometrica forma;
				if(radioQuadrado.isSelected()) {
					double lado = 0;
					try {
						lado = Double.parseDouble(campoLado.getText().trim());
					} catch(NumberFormatException e) {
					}
					Quadrado quadrado = new Quadrado();
					quadrado.setLado(lado);
					forma = quadrado;
				} else {
					double altura = 0, largura = 0;
					try {
						altura = Double.parseDouble(campoAltura.getText().trim());
						largura = Double.parseDouble(campoLargura.getText().trim());
					} catch(NumberFormatException e) {
					}
					Retangulo retangulo = new Retangulo();
					retangulo.setAltura(altura);
					retangulo.setLargura(largura);
					forma = retangulo;
				}

				if(forma != null) {
					double area = forma.calculaArea();
					Alert alert = new Alert(AlertType.INFORMATION);
					alert.setTitle("Resultado");
					alert.setHeaderText("Valor da área:");
					alert.setContentText("" + area);
					alert.showAndWait();
				}
			}
		});

		radioQuadrado.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				labelLado.setDisable(false);
				labelLargura.setDisable(true);
				labelAltura.setDisable(true);
				campoLado.setEditable(true);
				campoLado.setDisable(false);
				campoLargura.setEditable(false);
				campoLargura.setDisable(true);
				campoAltura.setEditable(false);
				campoAltura.setDisable(true);
			}
		});

		radioRetangulo.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				labelLado.setDisable(true);
				labelLargura.setDisable(false);
				labelAltura.setDisable(false);
				campoLado.setEditable(false);
				campoLado.setDisable(true);
				campoLargura.setEditable(true);
				campoLargura.setDisable(false);
				campoAltura.setEditable(true);
				campoAltura.setDisable(false);
			}
		});
	}

}
