package br.edu.ifms.lp3.ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Principal extends Application {

	@Override
	public void start(Stage estagioPrimario) throws Exception {
		try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(Principal.class.getResource("CalculoArea.fxml"));
            AnchorPane layoutPrincipal = (AnchorPane) loader.load();
            Scene scene = new Scene(layoutPrincipal);
            estagioPrimario.setTitle("Cálculo de área");
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
