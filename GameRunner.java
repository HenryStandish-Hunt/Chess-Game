
public class GameRunner {
	
	  public static void main(String[] args) {
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
 	  g.printState();
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
      MovementPath.playerTurn(b, one);
      successfullTurn = MovementPath.isSuccessful();
         
      //Checking if the player has moved themselves into check
      if(GameLogic.checkCheck(two, b)) {
    	  MovementPath.undoLastMove(b);
    	  successfullTurn = MovementPath.isSuccessful();
      } 
      if(successfullTurn) {
         g.printState();
      } else {
    	  System.out.println("Unsuccessfull turn please try again");
    	  System.out.println();
      }
      
      // Checking if they have checked the opponant
      if(GameLogic.checkCheck(one, b)) {
    	  if(GameLogic.checkMate(b, one)) {
    		  finished = true;
    		  return;
    	  }
 	  }
 	  } while(!successfullTurn);
 	  
 	  System.out.println();
      System.out.println("Successful turn player 1");
      System.out.println();
      
      //Player twos turn
          do {
          MovementPath.playerTurn(b, two);
          successfullTurn = MovementPath.isSuccessful();         
          //Checking if the player has moved themselves into check and undoing if so else printing the new state
          if(GameLogic.checkCheck(one, b)) {
        	  MovementPath.undoLastMove(b);
        	  successfullTurn = MovementPath.isSuccessful();
          }
          
          if(successfullTurn) {
              g.printState();
           } else {
         	  System.out.println("Unsuccessfull turn please try again");
         	 System.out.println();
           }
           
          if(GameLogic.checkCheck(two, b)) {
        	  if(GameLogic.checkMate(b, two)) {
        		  finished = true;
        		  return;
        	  }
          }
         
     	  } while(!successfullTurn);
          System.out.println();
          System.out.println("Successful turn player 2");
          System.out.println();
      }
 	 }
 	 
	

}