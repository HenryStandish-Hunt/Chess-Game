
public class GameRunner {
	
	  public static void main(String[] args) {
		  
		  run();
		  
	  }
	  
	  public static boolean run() {
		  
	  boolean successfullTurn = false;
	  boolean finished = false;	
      System.out.println("welcome to chess by Henry Standish-Hunt");
      System.out.println();
      Board b = new Board();
      Player one = new Player("White");
      Player two = new Player("Black");
      one.setName("One");
      two.setName("Two");
      b.startBoard("Black", b);
       
     
     //System.out.println(b.getCell(0, 1).getOccupied());
 	 //System.out.println( b.getCell(4,0).getPiece().getName());
   
       
 	  Graphics g = new Graphics(b);
 	  Gui gameInterface = new Gui(b);
 	  g.printState();
 	  gameInterface.setUpBoard();
 	  gameInterface.setState();
 	  System.out.println();
      /*for(int i = 0; i < 8; i++) {
    	// System.out.println( b.getCell(i,0).getOccupied());
    	 System.out.println( b.getCell(i,0).getPiece().getName());
      }
      //MovementPath.movementPath(b, one, 1, 0, -2, 2);
       */
 	
 	
 	  while(!finished) {
 	  // player 1s turn	 
 	  do {
      MovementPath.playerTurn(b, one, gameInterface);
      successfullTurn = MovementPath.isSuccessful();
         
      //Checking if the player has moved themselves into check
      if(GameLogic.checkCheck(two, b)) {
    	  MovementPath.undoLastMove(b);
    	  successfullTurn = MovementPath.isSuccessful();
      } 
      //Check for Prawn promotion aswell as print state or error message
      if(successfullTurn) {
    	 GameLogic.promotion(b, one);
         g.printState();
         gameInterface.setState();
        
      } else {
    	  System.out.println("Unsuccessfull turn please try again");
    	  System.out.println();
      }
      
      // Checking if they have checked the opponant
      if(GameLogic.checkCheck(one, b)) {
    	  System.out.println(two.getColour() + " is in check");
    	  if(GameLogic.checkMate(b, one)) {
    		  finished = true;
    		  return true;
    	  }
 	  }
 	  } while(!successfullTurn);
 	  
 	  System.out.println();
      System.out.println("Successful turn player 1");
      System.out.println();
      
      //Player twos turn
          do {
          MovementPath.playerTurn(b, two, gameInterface);
          successfullTurn = MovementPath.isSuccessful();         
          //Checking if the player has moved themselves into check and undoing if so else printing the new state
          if(GameLogic.checkCheck(one, b)) {
        	  MovementPath.undoLastMove(b);
        	  successfullTurn = MovementPath.isSuccessful();
          }
          
          if(successfullTurn) {
        	  GameLogic.promotion(b, two);
              g.printState();
              gameInterface.setState();
              
           } else {
         	  System.out.println("Unsuccessfull turn please try again");
         	 System.out.println();
           }
           
          if(GameLogic.checkCheck(two, b)) {
        	  System.out.println(one.getColour() + " is in check");
        	  if(GameLogic.checkMate(b, two)) {
        		  finished = true;
        		  return true;
        	  }
          }
         
     	  } while(!successfullTurn);
          System.out.println();
          System.out.println("Successful turn player 2");
          System.out.println();
      }
	return finished;
 	 }
 	 
	

}

