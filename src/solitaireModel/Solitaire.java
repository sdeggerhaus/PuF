package solitaireModel;

public class Solitaire {

	private STimer stim;
	private Highscore score;
	private int place;
	
	public Solitaire(){
		stim = new STimer();
		place = 0;
		score = new Highscore();
	}
	
	
	public STimer getSTimer(){
		return stim;
	}
}
