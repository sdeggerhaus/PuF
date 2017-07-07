package solitaireModel;

public class PlayRound {
	
	private Position origin;
	private Position target;
	
	public PlayRound(Position origin, Position target) {
		super();
		this.origin = origin;
		this.target = target;
	}
		
	public Position getOrigin() {
		return origin;
	}
	public Position getTarget() {
		return target;
	}
	
	public boolean valid(Position toDel){
		boolean ret = false;
		System.out.println(origin.getHor() + " " + target.getHor() + " " + toDel.getHor());
		System.out.println(origin.getVert() + " " + target.getVert() + " " + toDel.getVert());
		if(!origin.isEmpty() && target.isEmpty()){
			if(origin.getHor() != target.getHor()){
				if(origin.getVert() == target.getVert()){
					if(Math.abs(origin.getHor()-target.getHor()) == 2){
						if(!toDel.isEmpty()){
							return true;
						}
					}
				}
			}
			if(origin.getVert() != target.getVert()){
				if(origin.getHor() == target.getHor()){
					if(Math.abs(origin.getVert()-target.getVert()) == 2){
						if(!toDel.isEmpty()){
							return true;
						}
					}
				}
			}
		}
		return ret;
		
	}

}
