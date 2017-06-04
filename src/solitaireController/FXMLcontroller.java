package solitaireController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Timer;
import java.util.TimerTask;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.util.Duration;
import solitaireModel.Solitaire;

public class FXMLcontroller {

	private Solitaire game;
	private AnimationTimer ta;
	private Timeline tLine;
	@FXML private GridPane gridPaneImage;
	@FXML private Label lblTimer;
	
	@FXML private void initialize() {
		game = new Solitaire();		
    }
	
	private void setTask(){
		tLine = new Timeline();
		tLine.setCycleCount(Timeline.INDEFINITE);
		ta = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				lblTimer.setText(game.getSTimer().getTimeS());				
			}
		};
		tLine.play();
		ta.start();		
	}
    
    @FXML protected void handleButtonAction(ActionEvent event) {
    	game.getSTimer().start();
    	setTask();
        for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				boolean check = !(i < 2 && j < 2) && !(i > 4 && j > 4) && !(i < 2 && j > 4) && !(i > 4 && j < 2) && !(i == 3 && j == 3);
				if(check){
					FileInputStream imageStream = null;
					try {
						File f = new File("media/figur.png");
						//System.out.println(f.getAbsolutePath());
						imageStream = new FileInputStream(f);
					} catch (FileNotFoundException e) {
						System.out.println("fak");
					}
					Image im = new Image(imageStream);
					ImageView iv = new ImageView(im);
					iv.setFitWidth(50);
					iv.setFitHeight(50);
					gridPaneImage.add(iv, i, j);
				}
			}
		}
    }
    
    @FXML protected void onResizeEvent(ActionEvent event) {
    	System.out.println("ajajajajja");
    	
    }
	
}
