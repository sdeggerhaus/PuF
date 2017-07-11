package solitaireController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import com.sun.javafx.geom.Rectangle;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import solitaireModel.Database;
import solitaireModel.PlayRound;
import solitaireModel.Position;
import solitaireModel.Solitaire;

public class FXMLcontroller {

	private Solitaire game;
	private AnimationTimer ta;
	private Timeline tLine;
	
	private Image image;
	
	private double dragOriginX;
	private double dragOriginY;
	private Rectangle actWaH;
	
	private Future<Boolean> hasRounds; 
	
	@FXML private Pane vb1;
	@FXML private Label lblTimer;
	@FXML private Label lblback;
	
	@FXML private void initialize() {
		Database.currIndex = -1;
		game = new Solitaire();
		FileInputStream imageStream = null;
		try {
			File f = new File("media/figur.png");
			//System.out.println(f.getAbsolutePath());
			imageStream = new FileInputStream(f);
			image = new Image(imageStream);
			imageStream.close();
		} catch (FileNotFoundException e) {
			System.out.println("fak");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		vb1.heightProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				regroupStones();				
			}
			
		});
		
		vb1.widthProperty().addListener(new ChangeListener<Number>() {

			@Override
			public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
				regroupStones();				
			}
			
		});
		
		game.getSTimer().start();
    	setTask();
    	game.addScore(game.getScore().getValue());
    	game.start();
    	Position[] posarr = new Position[33];
    	int poscounter = 0;
        for (int i = 0; i < 7; i++) {
			for (int j = 0; j < 7; j++) {
				boolean check = !(i < 2 && j < 2) && !(i > 4 && j > 4) && !(i < 2 && j > 4) && !(i > 4 && j < 2) && !(i == 3 && j == 3);
				if(check){
					Position pos = new Position(i, j, false);
					posarr[poscounter] = pos;
					poscounter++;
				}
				if(i == 3 && j == 3){
					Position pos = new Position(i, j, true);
					posarr[poscounter] = pos;
					poscounter++;
				}
				
			}
		}
        game.setPosis(posarr);
        startRoundTask();
        regroupStones();
		
    }
	
	private void startRoundTask(){
		ExecutorService exe = Executors.newFixedThreadPool(1);
		Callable<Boolean> hasRoundsTask = () -> {
			Position[] posarr = game.getPosis();
			for (int i = 0; i < posarr.length; i++) {
				Position pos1 = game.getPositionAt(posarr[i].getHor()+1, posarr[i].getVert());
				Position pos2 = game.getPositionAt(posarr[i].getHor()+2, posarr[i].getVert());
				if(pos1 != null && pos2 != null){
					if(!posarr[i].isEmpty() && !pos1.isEmpty() &&pos2.isEmpty()){
						//System.out.println("hi na 1: " + pos1.getHor() + " " + pos1.getVert() + " " + pos1.isEmpty());
						//System.out.println("hi na 1: " + pos2.getHor() + " " + pos2.getVert() + " " + pos2.isEmpty());
						return true;
					}
				}
				pos1 = game.getPositionAt(posarr[i].getHor()-1, posarr[i].getVert());
				pos2 = game.getPositionAt(posarr[i].getHor()-2, posarr[i].getVert());
				if(pos1 != null && pos2 != null){
					if(!posarr[i].isEmpty() && !pos1.isEmpty() &&pos2.isEmpty()){
						//System.out.println("hi na 2: " + pos1.getHor() + " " + pos1.getVert() + " " + pos1.isEmpty());
						//System.out.println("hi na 2: " + pos2.getHor() + " " + pos2.getVert() + " " + pos2.isEmpty());
						return true;
					}
				}
				pos1 = game.getPositionAt(posarr[i].getHor(), posarr[i].getVert()+1);
				pos2 = game.getPositionAt(posarr[i].getHor(), posarr[i].getVert()+2);
				if(pos1 != null && pos2 != null){
					if(!posarr[i].isEmpty() && !pos1.isEmpty() &&pos2.isEmpty()){
						//System.out.println("hi na 3: " + pos1.getHor() + " " + pos1.getVert() + " " + pos1.isEmpty());
						//System.out.println("hi na 3: " + pos2.getHor() + " " + pos2.getVert() + " " + pos2.isEmpty());
						return true;
					}
				}
				pos1 = game.getPositionAt(posarr[i].getHor(), posarr[i].getVert()-1);
				pos2 = game.getPositionAt(posarr[i].getHor(), posarr[i].getVert()-2);
				if(pos1 != null && pos2 != null){
					if(!posarr[i].isEmpty() && !pos1.isEmpty() &&pos2.isEmpty()){
						//System.out.println("hi na 4: " + pos1.getHor() + " " + pos1.getVert() + " " + pos1.isEmpty());
						//System.out.println("hi na 4: " + pos2.getHor() + " " + pos2.getVert() + " " + pos2.isEmpty());
						return true;
					}
				}
				
			}
			return false;
		
		};
		hasRounds = exe.submit(hasRoundsTask);
		
	}
	
	private void setTask(){
		tLine = new Timeline();
		tLine.setCycleCount(Timeline.INDEFINITE);
		ta = new AnimationTimer() {
			
			@Override
			public void handle(long now) {
				game.getScore().setTime(game.getSTimer().getTime());
				String text = game.getSTimer().getTimeS() + "\t" +  game.getScore().getValue();
				lblTimer.setText(text);				
			}
		};
		tLine.play();
		ta.start();		
	}
    
    @FXML protected void handleMouseClick(MouseEvent event) {
    	regroupStones();
    }
    
    @FXML protected void handleMouseRel(MouseEvent event) {
    	double diffX = event.getSceneX() - dragOriginX;
		double diffY = event.getSceneY() - dragOriginY;
		
		double ratio = vb1.getWidth()/vb1.getHeight();
		
		int posOriI = (int) Math.round((dragOriginX - (vb1.getWidth()*0.3157))/(vb1.getWidth()*0.063));
		int posOriJ = (int) Math.round((dragOriginY - (vb1.getHeight()*0.215))/(vb1.getHeight()*0.08));
		
		int posnewI = posOriI + (int) Math.round(diffX/actWaH.width);
		int posnewJ = posOriJ + (int) Math.round(diffY/actWaH.height);
		
		Position pOri = game.getPositionAt(posOriI, posOriJ);
		Position pNew =  game.getPositionAt(posnewI, posnewJ);
		if(pOri != null && pNew != null){
			Position toDel;
			if(posnewI - posOriI > 0){
				toDel = game.getPositionAt(posOriI+1, posOriJ);
			}else{
				if(posnewI - posOriI < 0){
					toDel = game.getPositionAt(posOriI-1, posOriJ);
				}else{
					if(posnewJ - posOriJ < 0){
						toDel = game.getPositionAt(posOriI, posOriJ-1);
					}else{
						toDel = game.getPositionAt(posOriI, posOriJ+1);
					}
				}
			}
						
			PlayRound round = new PlayRound(pOri, pNew);
			boolean err = round.valid(toDel);
			if(err){
				pNew.setEmpty(false);
				pOri.setEmpty(true);
				toDel.setEmpty(true);
				startRoundTask();
				game.addRond(round);
				game.manageNewRound();
				regroupStones();
				if(hasRounds.isDone()){
					try {
						if(!hasRounds.get()){
							calcLefties();
							changeToName();
						}
					} catch (InterruptedException e) {
						System.out.println("Interrupt");
						e.printStackTrace();
					} catch (ExecutionException e) {
						System.out.println("kp was das ist");
						e.printStackTrace();
					}
				}
				//System.out.println("PosOri " + posOriI + "/" + posOriJ + " PosNew " + posnewI + "/" + posnewJ);
			}
		}
    }
    
    public void calcLefties(){
    	Position[] posarr = game.getPosis();
    	int counter = 0;
    	for (int i = 0; i < posarr.length; i++) {
			if(!posarr[i].isEmpty()){
				counter++;
			}
		}
    	game.setLefties(counter);
    }
    
    public void changeToName(){
    	try {
			Parent root = FXMLLoader.load(getClass().getResource("../fxml_opt.fxml"));
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
    
    public void handleDrag(MouseEvent event){
    	Dragboard db = vb1.startDragAndDrop(TransferMode.MOVE);
    	ClipboardContent cbc = new ClipboardContent();
    	cbc.putImage(null);
    	db.setContent(cbc);
    	dragOriginX = event.getSceneX();
    	dragOriginY = event.getSceneY();
    	//System.out.println(dragOriginX + " " + dragOriginY);
    	event.consume();
    }
    
    public void regroupStones(){
    	vb1.getChildren().clear();
    	Position[] posarr = game.getPosis();
    	double paneWidth = vb1.getWidth();
    	double paneHeight = vb1.getHeight();
    	if(posarr[0] != null){
    		for (int i = 0; i < posarr.length; i++) {
    			if(!posarr[i].isEmpty()){
    				ImageView iv = new ImageView(image);
    				iv.setFitHeight(paneWidth/20);
    				iv.setFitWidth(paneWidth/20);
    				double multihor = (4 - (8-(posarr[i].getVert()+1)))*(4 - (8-(posarr[i].getHor()+1)));
    				//System.out.println(multihor + " " + posarr[i].getVert());
	    			double xProp = (((paneWidth*0.3157) + (posarr[i].getHor()*paneWidth*0.063))-(iv.getFitWidth()/2.0))+(multihor*paneWidth/400);
	    			double yProp = (((paneHeight*0.215) + (posarr[i].getVert()*paneHeight*0.08))-(iv.getFitHeight()/2.0));
	    			iv.setX(xProp);
	    			iv.setY(yProp);
	    				    			
	    			iv.setOnDragDetected(new EventHandler<MouseEvent>() {

						@Override
						public void handle(MouseEvent event) {
							handleDrag(event);
						}
					});
	    			
					vb1.getChildren().add(iv);
    			}
			}
    	
    	
    		double xtemp = (posarr[3].getHor()*paneWidth*0.063) - (posarr[0].getHor()*paneWidth*0.063);
    		double ytemp = (posarr[1].getVert()*paneHeight*0.085) - (posarr[0].getVert()*paneHeight*0.085);
    		actWaH = new Rectangle((int) xtemp, (int) ytemp);
    	}
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
