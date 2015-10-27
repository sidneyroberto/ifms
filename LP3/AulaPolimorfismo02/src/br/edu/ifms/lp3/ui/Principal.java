package br.edu.ifms.lp3.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Principal extends Application {

	@Override
	public void start(Stage estagioPrimario) {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("Formas.fxml"));
            AnchorPane layoutPrincipal = (AnchorPane) loader.load();
            Scene scene = new Scene(layoutPrincipal);
            estagioPrimario.setTitle("Formas geométricas");
            estagioPrimario.setResizable(false);
            estagioPrimario.setScene(scene);
            estagioPrimario.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
	}

	public static void main(String[] args) {
		launch(args);
	}
}
