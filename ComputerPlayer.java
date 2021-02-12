import java.util.ArrayList;

public class ComputerPlayer {
	
	public static void computerTurn(Board b, Player computer) {
				
				ArrayList<int[]>legalMoves = new ArrayList<>();
				Cell[][] currentState = b.getBoard();
		
		        for(int xSelect = 0; xSelect < 8; xSelect++) {
				for(int ySelect = 0; ySelect < 8; ySelect++) {	
			  
					 if(currentState[xSelect][ySelect].getOccupied() && currentState[xSelect][ySelect].getColour().contentEquals(computer.getColour())) {
						 
			    	 for(int xMov = 0; xMov < 8; xMov++) {
			    	 for(int yMov = 0; yMov < 8; yMov++) {
			    	 MovementPath.setTest(true);
			    	 MovementPath.setSuccessful(false);
			    	 MovementPath.movementPath(b, computer,  xSelect, ySelect, xMov, yMov);
			    	
			    	 	if(MovementPath.isSuccessful()) {
			    	 		int[] coord = new int[4];
			    	 		coord[0] = xSelect;
			    	 		coord[1] = ySelect;
			    	 		coord[2] = xMov;
			    	 		coord[3] = yMov;
			    	 		legalMoves.add(coord);
			    	     }
			    		
			    	 }
			    	 }
			    	 }
			     }
			     }
		        
		        for(int i = 0; i < 5000000; i++) {
		        	System.out.print("");
		        	
		        }
		        System.out.println();
		        
		        System.out.println(legalMoves.size());
		        int rand = (int) (Math.random()*legalMoves.size());
		        int[] move = legalMoves.get(rand);
		        MovementPath.setTest(false);
		        System.out.println("computer movement "+ move[0] + " " + move[1] + " " + move[2] + " " + move[3]);
		        MovementPath.movementPath(b, computer,  move[0], move[1], move[2], move[3]);
		        
	}


}
