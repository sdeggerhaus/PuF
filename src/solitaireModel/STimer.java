package solitaireModel;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class STimer{

	private long time;
	private Timer tim;
	private TimerTask tTask;
	private boolean running;
	private String timeS;
	private SimpleDateFormat dateformat;
	
	public STimer(){
		tim = new Timer("Metronome", true);
		running = false;
		time = 0L;
		dateformat = new SimpleDateFormat("mm:ss");
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
		tim.scheduleAtFixedRate(tTask, 10, 10);
	}
	
	public void stop(){
		running = false;
	}
	
	public void update(){
		time += 10;
		timeS = dateformat.format(new Date(time));
		//System.out.println(timeS);
	}
	
	public long getTime(){
		return time;
	}
	
	public String getTimeS(){
		return timeS;
	}
}
