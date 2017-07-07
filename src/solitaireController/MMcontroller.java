package solitaireController;

import java.io.IOException;

import javafx.beans.binding.Bindings;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class MMcontroller {
	
	@FXML private HBox mm_main;
	
	private DoubleProperty fontSize = new SimpleDoubleProperty(20);
	
	
	@FXML private void initialize() {		
		fontSize.bind(mm_main.widthProperty().add(mm_main.heightProperty()).divide(50));
		for (int i = 0; i < mm_main.getChildren().size(); i++) {
			mm_main.getChildren().get(i).styleProperty().bind(Bindings.concat("-fx-font-size: ", fontSize.asString(), ";"));
		}	
	}
	
	@FXML private void startAction() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxml_solitaire.fxml"));
			Stage st = (Stage) mm_main.getScene().getWindow();
			Scene sc = new Scene(root, 720, 405);
			sc.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());
			st.minWidthProperty().bind(sc.heightProperty().multiply(1.778));
			st.minHeightProperty().bind(sc.widthProperty().divide(1.778));			
			st.setScene(sc);
		} catch (IOException e) {
			System.out.println("no xml file");
			e.printStackTrace();
		}		
	}
	
	@FXML private void hscAction() {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxml_highscore.fxml"));
			Stage st = (Stage) mm_main.getScene().getWindow();
			Scene sc = new Scene(root, 720, 405);
			sc.getStylesheets().add(getClass().getResource("../style.css").toExternalForm());
			st.minWidthProperty().bind(sc.heightProperty().multiply(1.778));
			st.minHeightProperty().bind(sc.widthProperty().divide(1.778));			
			st.setScene(sc);
		} catch (IOException e) {
			System.out.println("no xml file");
			e.printStackTrace();
		}
	}
	
	@FXML private void optAction() {
		
	}
	
	@FXML private void exitAction() {
		Stage st = (Stage) mm_main.getScene().getWindow();
		st.close();
	}

}
