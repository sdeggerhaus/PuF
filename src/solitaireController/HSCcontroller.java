package solitaireController;

import java.awt.TextField;
import java.io.IOException;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.stage.Stage;
import solitaireModel.Database;
import solitaireModel.Highscore;

public class HSCcontroller {
	
	@FXML private TableView<Highscore> tV;
	private ObservableList<Highscore> data;
	private Database db;
	@FXML private Label lblback;
	
	@FXML private void initialize() {
		db = new Database();
		
		data = FXCollections.observableArrayList();
		
		Highscore[] dbdata = db.getTableView();
		for (int i = 0; i < dbdata.length; i++) {
			  
            data.add(dbdata[i]);
		}
		
		tV.setItems(data);
	}	
	
	@FXML protected void handleBack(){
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxml_mainmenu.fxml"));
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
