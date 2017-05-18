package solitaireController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class FXMLcontroller {

	@FXML private GridPane gridPaneImage;
    
    @FXML protected void handleButtonAction(ActionEvent event) {
        for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				boolean check = !(i < 2 && j < 2) && !(i > 4 && j > 4) && !(i < 2 && j > 4) && !(i > 4 && j < 2) && !(i == 3 && j == 3);
				if(check){
					FileInputStream imageStream = null;
					try {
						File f = new File("media/figur.png");
						System.out.println(f.getAbsolutePath());
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
