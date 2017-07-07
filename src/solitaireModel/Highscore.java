package solitaireModel;

import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Highscore {

	// Berechnung aus Anzahl der Züge und vergangener Zeit
	private int value;
	private String user;
	private Date date;
	private int rounds;
	private int lefties;
	private boolean finish;
	private Timer tim;
	private TimerTask tTask;
	private boolean running;
	private long timeinmillis;
	
	public Highscore(){
		tim = new Timer("Metronome", true);
		running = false;
		value = 0;
		timeinmillis = 0;
	}
	
	public Highscore(int value, String user, Date date, int rounds, int lefties){
		this.date = date;
		this.user = user;
		this.value = value;
		this.lefties = lefties;
		this.rounds = rounds;
		this.tim = new Timer("Metronome", true);
		this.running = false;
		this.timeinmillis = 0;
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
		value = (int) (timeinmillis/100);
	}
	
	public int getValue(){
		return value;
	}
	
	public void setTime(long time){
		this.timeinmillis = time;
	}

	public String getUser() {
		return user;
	}

	public Date getDate() {
		return date;
	}

	public int getRounds() {
		return rounds;
	}

	public int getLefties() {
		return lefties;
	}
	
	public void setLefties(int lefties) {
		this.lefties = lefties;
	}

	public boolean isFinish() {
		return finish;
	}
	
	
}
