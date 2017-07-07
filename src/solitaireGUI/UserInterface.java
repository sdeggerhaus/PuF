package solitaireGUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class UserInterface extends Application{
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		primaryStage.setTitle("Solitaire");
		Parent root = FXMLLoader.load(getClass().getResource("../fxml_mainmenu.fxml"));
		
		Scene sc = new Scene(root, 720, 405);
		sc.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());
		
		primaryStage.setScene(sc);
		primaryStage.minWidthProperty().bind(sc.heightProperty().multiply(1.778));
	    primaryStage.minHeightProperty().bind(sc.widthProperty().divide(1.778));
		primaryStage.show();
	}
}
