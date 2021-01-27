
public class Graphics {
	
	Board b;
	
	Graphics(Board board){
	 this.b = board;
	}
	
	//this prints the state of the board 
	public void printState() {
		    
			//loops had to be different ways round to orientate the board how i want it in the console
	     	for(int y = 7; y >= 0; y--) {
	     		for(int x = 0; x < 8; x++)	{
	     			//this checks if the cell is occupied prints its initials if it is or just prints a blank space
	     			if(b.getCell(x,y).getOccupied() == true) {	
	     				System.out.print(b.getCell(x,y).getPiece().getName().substring(0,2) + b.getCell(x,y).getPiece().getColour().substring(0,1) + "| ");
	     			} else {
	     				 System.out.print("   | ");
	     			}
			
	     		} 
	     		// this adds the y key 
	     		System.out.print(" " + y + "| ");
	     		// this adds lin under with boarder
	     		System.out.println();
	     		for(int l = 0; l < 8; l++)	{
	     			System.out.print("___|_");
	     		}
	     		System.out.println();
			  }
	     	// this adds the x key 
	     	for(int l = 0; l < 8; l++)	{
     			System.out.print("_" + l + "_|_");
     		}
	     	System.out.println();
	   
	}

}
