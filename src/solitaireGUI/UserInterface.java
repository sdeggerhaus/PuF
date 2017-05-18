package solitaireGUI;

import java.util.ArrayList;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import solitaireModel.Figure;

public class UserInterface extends Application{
	
	ArrayList<Figure> fi = new ArrayList<Figure>();

	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Solitaire");
		Parent root = FXMLLoader.load(getClass().getResource("../fxml_solitaire.fxml"));
		
		Scene sc = new Scene(root, 720, 405);
		sc.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());
		
		primaryStage.setScene(sc);
		primaryStage.show();
	}
}
