package com.chessgame;

public abstract class GamePiece {
	
	protected String name;
	protected  String colour;
	//Grapic Object;
	
	protected int xPos;
	protected int yPos;
	protected int spaceCount;
	protected boolean active;
	protected int numMoves;
	
	GamePiece(String name , String colour, int xStart, int yStart ) {
		
		this.name = name;
		this.colour = colour;
		this.xPos = xStart;
		this.yPos = yStart;
		active = true;
		numMoves = 0;
	}
	
	public abstract void setX(int x);
	public abstract void setY(int y);
	public abstract int getxPos();
	public abstract int getyPos();
	public abstract void move();
	public abstract boolean getActive();
	public abstract void setActive(boolean state);
	public abstract String getColour();
	public abstract void setColour(String colour);
	public abstract String getName();
	public abstract void setName(String name);
	public abstract int getNumMoves();
	public abstract void addNumMoves();
	public abstract void minusNumMoves();
	
}
