
public class Cell {
	
	private Piece piece;
	
	private boolean occupied;
	
	private String colour;
	
	Cell(Piece p, boolean occupied){
		
		piece = p;
		this.occupied = occupied;
		
		if(occupied == true) {
			colour = p.getColour();
		}
		
	}
	
	public boolean getOccupied() {
		return occupied;
	}

	public void setOccupied(boolean occupied) {
		this.occupied = occupied;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}

	public Piece getPiece() {
		return piece;
	}
	// This sets the cell piece and the rest of the fields at once 
	public void setPiece(Piece piece) {
		if(piece == null) {
			this.piece = null;
			occupied = false;
			colour = null;
			
		} else {
		this.piece = piece;
		occupied = true;
		colour = piece.getColour();
	}
	}
	

}
