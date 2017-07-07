package solitaireModel;

public class Position {
		
	private int hor;
	private int vert;
	private boolean empty;
	
	public Position(int hor, int vert, boolean empty){
		this.hor = hor;
		this.vert = vert;
		this.empty = empty;		
	}

	public int getHor() {
		return hor;
	}

	public int getVert() {
		return vert;
	}

	public boolean isEmpty() {
		return empty;
	}
	
	public void setEmpty(boolean empty){
		this.empty = empty;
	}
}
