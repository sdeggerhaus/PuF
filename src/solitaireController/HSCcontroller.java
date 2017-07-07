package solitaireController;

import java.awt.TextField;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import solitaireModel.Database;
import solitaireModel.Highscore;

public class HSCcontroller {
	
	@FXML private TableView<Highscore> tV;
	private ObservableList<Highscore> data;
	private Database db;
	
	@FXML private void initialize() {
		db = new Database();
		
		data = FXCollections.observableArrayList();
		
		Highscore[] dbdata = db.getTableView();
		for (int i = 0; i < dbdata.length; i++) {
			  
            data.add(dbdata[i]);
		}
		
		tV.setItems(data);
	}	

}
