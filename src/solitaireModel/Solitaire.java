package solitaireModel;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class Solitaire {

	private STimer stim;
	private Highscore score;
	private int place;
	private Database db;
	private Timer tim;
	private TimerTask tTask;
	private boolean running;
	private Position[] posis;
	private ArrayList<PlayRound> rounds;
	
	public Solitaire(){
		stim = new STimer();
		place = 0;
		score = new Highscore();
		score.start();
		
		posis = new Position[33];
		
		rounds = new ArrayList<PlayRound>();
		
		running = false;
		tim = new Timer("Metronome", true);
		
		db = new Database();
	}
	
	public Position getPositionAt(int hor, int vert){
		for (int i = 0; i < posis.length; i++) {
			if(posis[i].getHor() == hor && posis[i].getVert() == vert){
				return posis[i];
			}
		}
		return null;
	}
	
	public void addScore(int score){
		db.addScore(score);
	}
	
	public Highscore[] getTableView(){
		return db.getTableView();
	}
	
	public int getActPlace(){
		return db.getActPlace();
	}
	
	public void updateScore(int score, int rounds){
		db.updateScore(score, rounds);
	}
	
	public STimer getSTimer(){
		return stim;
	}
	
	public void start(){
		running = true;
		tTask = new TimerTask() {
			
			@Override
			public void run() {
				if(!running){
					tTask.cancel();
				}else{
					update();
				}
			}
		};
		tim.scheduleAtFixedRate(tTask, 2000, 2000);
	}
	
	public void stop(){
		running = false;
	}
	
	public void update(){
		db.updateScore(score.getValue(), score.getRounds());
	}
	
	public void updateLefties(){
		db.updateLefties(score.getLefties());
	}
		
	public void setLefties(int lefties){
		score.setLefties(lefties);
		updateLefties();
	}
	
	public Highscore getScore(){
		return score;
	}

	public Position[] getPosis() {
		return posis;
	}

	public void setPosis(Position[] posis) {
		this.posis = posis;
	}
	
	public void addRond(PlayRound round){
		rounds.add(round);
	}
	
	public void manageNewRound(){
		score.addRound(rounds.size());
		this.update();
	}
}
