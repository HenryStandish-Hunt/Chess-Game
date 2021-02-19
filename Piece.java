
public class Piece extends GamePiece{
	
	Piece(String name,String colour, int xStart, int yStart) {
		super(name, colour, xStart, yStart);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void setX(int x) {
		xPos = x;
		
	}

	@Override
	public void setY(int y) {
		yPos = y;
		
	}

	@Override
	public int getxPos() {
		
		return xPos;
	}

	@Override
	public int getyPos() {
		
		return yPos;
	}

	@Override
	public void move() {
		
	}

	@Override
	public boolean getActive() {
		return active;
	}

	@Override
	public void setActive(boolean state) {
		active = state;
	
	}

	@Override
	public String getColour() {
		
		return colour;
	}

	@Override
	public void setColour(String colour) {
		this.colour = colour;
	}

	@Override
	public String getName() {
		return name;
		
	}

	@Override
	public void setName(String name) {
		this.name = name;
		
	}

	@Override
	public int getNumMoves() {
		// TODO Auto-generated method stub
		return numMoves;
	}
	public void setNumMoves(int num) {
		numMoves = num;
	}

	@Override
	public void addNumMoves() {
		numMoves++;
		
	}

	@Override
	public void minusNumMoves() {
		numMoves--;
		
	}

}
