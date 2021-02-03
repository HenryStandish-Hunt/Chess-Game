import java.util.ArrayList;
import java.util.Scanner;

public class GameLogic {
	
	
	
	public static Boolean checkCheck(Player player, Board b) {
		
		//check every opposition piece and see if they can make a valid move to the players king
		
		Cell[][] currentState = b.getBoard();
		boolean check = false;
	
		int wKingxPos = 0;
		int wKingyPos = 0;
		ArrayList<Piece> piecesChecking = new ArrayList<>();
		int bKingxPos = 0;
		int bKingyPos = 0;
		int tempxPos = 0;
		int tempyPos = 0;
		String opColour;
		
		// Alternate code to easily traverse whole board could implement if have time 
		/*
		 for(Cell[] c : b.getBoard() ) {
		    	for(Cell cc : c) {
		    		  if(cc.getOccupied()) {
		    			 System.out.println( cc.getPiece().getName());
		    	      }
		    	}
		    	
		    }
		     */   	
		       // go through the board and find the kings 
		        for(int x = 0; x < 8; x++) {
				for(int y = 0; y < 8; y++)	{
			    if(currentState[x][y].getOccupied()) {
			    	if(currentState[x][y].getPiece().getName().equals("King")) {
			    		if(currentState[x][y].getPiece().getColour().equals("White")) {
			    		
			    			wKingxPos = currentState[x][y].getPiece().getxPos();
			    			wKingyPos = currentState[x][y].getPiece().getyPos();
			    		} else {
			    			bKingxPos = currentState[x][y].getPiece().getxPos();
			    			bKingyPos = currentState[x][y].getPiece().getyPos();
			    		}
			    	}
			    }
				}
				}
		       
		       
		        
		        if(player.getColour().equals("White")) {
		        	tempxPos = bKingxPos;
		        	tempyPos = bKingyPos;
		        	opColour = "Black";
		        } else {
		        	tempxPos = wKingxPos;
		        	tempyPos = wKingyPos;
		        	opColour = "White";
		        }
	
		// go through the board checking if the opponant is now in check
		    
		    for(int x = 0; x < 8; x++) {
			for(int y = 0; y < 8; y++)	{
				
		    if(currentState[x][y].getOccupied()) {
		    	if(currentState[x][y].getColour().equals(player.getColour())) {
		    		Piece p = currentState[x][y].getPiece();
		    		MovementPath.setTest(true);
		    		MovementPath.setSuccessful(false);
		    		MovementPath.movementPath(b, player,p.getxPos(),p.getyPos(),tempxPos,tempyPos);
		    		if(MovementPath.isSuccessful()) {
		    			check = true;
		    			piecesChecking.add(p);
		    			
		    		}
		    		MovementPath.setSuccessful(false);
		    			
		    	}
		    }
		     
			}
			}
		    if(check) {
		    //System.out.println(opColour + " is now in check " );
		    }
		    return check;
		
	}
	
	public static boolean checkMate(Board b,Player player) {
		
		 //create and set up a duplicate of the current board and produce a imitation opposition player
		  boolean stillInCheck = true;
		  String temp;
		  temp = player.getColour().equals("White") ? "Black": "White"; 
		  Player oppPlayer = new Player(temp);
		  temp = player.getName().equals("One") ? "Two": "One"; 
		  oppPlayer.setName(temp);
		  Board dup = b.duplicateBoard();
		  Cell[][]testState = dup.getBoard();
		  //Graphics test = new Graphics(dup);
	
		  
		  // go through the duplicate boards cells selecting all of them checking if they contain and opposition piece
		  // then trying to move them to a random cell on the board 
		  // if this succedes it then checks if the oppPlayer is still in check if their not it returns false
		  // if not the piece is moved back to its orginal position and the loop starts over moving it to all valid positions on the board
		  // working though all the cells once completed if no moves can be made to get out of check it is check mate
		  
		    for(int xSelect = 0; xSelect < 8; xSelect++) {
			for(int ySelect = 0; ySelect < 8; ySelect++) {	
		  
				 if(testState[xSelect][ySelect].getOccupied() && testState[xSelect][ySelect].getColour().contentEquals(oppPlayer.getColour())) {
					 
		    	 for(int xMov = 0; xMov < 8; xMov++) {
		    	 for(int yMov = 0; yMov < 8; yMov++) {
		    	 MovementPath.setTest(false);
		    	 MovementPath.setSuccessful(false);
		    	 MovementPath.movementPath(dup, oppPlayer,  xSelect, ySelect, xMov, yMov);
		    	
		    	 	if(MovementPath.isSuccessful()) {
		    	 		MovementPath.setSuccessful(false);
		    	 		//test.printState();
		    	 		if(!checkCheck(player,dup)) {
		    		    return stillInCheck = false;
		    	     }
		    		 MovementPath.manualMove(dup, xMov,yMov, xSelect , ySelect );
		    		// test.printState();
		    	 }
		    	 }
		    	 }
		    	 }
		     }
	         }
		  
		    if(stillInCheck) {
		    	System.out.println("Check Mate " + oppPlayer.getColour() + "  you Lose");
		    }
	        return stillInCheck;
     }
	
    public static void promotion(Board b, Player p ) {
    	String colour = p.getColour();
    	Cell[][] currentState = b.getBoard();
    	int yPosGoal;
    	
    	//checking the colour of the player and altering the yPosition checked for promotion
    	if(colour.equals("White")) {
    		yPosGoal = 7;
    		
    	} else {
    		yPosGoal = 0;
    	}
    	
    	    for(int i = 0; i < 8; i++) {
    		if(currentState[i][yPosGoal].getOccupied() && currentState[i][yPosGoal].getColour().equals(colour)) {
    			
    		   if(currentState[i][yPosGoal].getPiece().getName().contentEquals("Prawn")) {
    			   Scanner scan = new Scanner(System.in);
    			   Piece piece = currentState[i][yPosGoal].getPiece();
    			   int choice = 0;
    			   System.out.println(" Your Prawn can now be promoted ");
    			   
    			   do {
    				    System.out.println("Please enter number to select 1 Queen 2 Rook 3 Knight 4 Bishop");
    				   
    				    choice = scan.nextInt(); 
    			   
    			   
    			   
    		       }while(choice < 1 || choice > 4 );
    			   
    			   switch(choice) {
    				
    				case 1:
    				    piece.setName("Queen");
    					break;
    				case 2:
    					piece.setName("Rook");
    					break;
    				case 3:
    					piece.setName("Knight");
    					break;
    				case 4:
    					piece.setName("Bishop");
    					break;  
    		        }
    		      }
    	       }
               }
		
    }
}
