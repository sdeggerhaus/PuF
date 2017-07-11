package solitaireController;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import solitaireModel.Database;

public class OPTcontroller {

	@FXML private Label lblback;
	@FXML private TextField tfname;
	private Database db;
	
	@FXML protected void handleForw(){
		db = new Database();
		if(tfname.getText().equals("")){
			tfname.setText("<Kein Name>");
		}
		db.updateName(tfname.getText());
		System.out.println(tfname.getText());
		
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxml_highscore.fxml"));
			Stage st = (Stage) lblback.getScene().getWindow();
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
	
}
