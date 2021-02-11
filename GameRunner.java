
public class GameRunner {
		 
	  private static boolean gameComplete;
	  
	  public static void main(String[] args) {
		  
		  setUp();
		  
	  }
	  
	  public static void setUp() {
		  boolean finished = false;	
	  
	      Board b = new Board();
	      Player one = new Player("White");
	      Player two = new Player("Black");
	      one.setName("One");
	      two.setName("Two");
	      b.startBoard(b);
	      
	 	  Gui gameInterface = new Gui(b); 
	 	  gameInterface.setUpBoard();
	 	  gameInterface.setState();
	 	  
		  gameLoop(b,one,two,gameInterface);
		  
		  // tring to get new game to work
		  System.out.println("game two");
		  
		  Board c = new Board();
		  c.startBoard(c);
		  gameInterface.setBoard(c);
		  gameInterface.setSideText("Welcome to Game 2 \n To start Game player 1 drag and drop a white piece\"");
		  gameInterface.setState();
		  gameLoop(c,one,two,gameInterface);
		  
	  }
 	 
	  private static boolean gameLoop(Board b, Player one, Player two, Gui gameInterface) {
		  
	  	  gameComplete = false;
		  boolean turnOneSuccess = false;
		  boolean turnTwoSuccess = false;
		  
		  while(!gameComplete) {
			  do {
			    turnOneSuccess = playerTurn(b,one,two,gameInterface);  
			  }while(!turnOneSuccess);
			  
			  if(gameComplete) {
				  break;
			  }
				 
			  do {
		        turnTwoSuccess = playerTurn(b,two,one,gameInterface);
			  }while(!turnTwoSuccess);
		  }
		  System.out.println("game complete");
		  return gameComplete;
	  }
	  
	  private static boolean playerTurn(Board b, Player primary,Player opponent, Gui gameInterface) {
		  
		  	  boolean successfullTurn;
		  	  String checkMessage = "";
		      MovementPath.playerTurn(b, primary, gameInterface);
		      successfullTurn = MovementPath.isSuccessful();
		         
		      //Checking if the player has moved themselves into check
		      if(GameLogic.checkCheck(opponent, b)) {
		    	  MovementPath.undoLastMove(b);
		    	  successfullTurn = MovementPath.isSuccessful();
		      } 
		      
		      //Check for Prawn promotion aswell as print state or error message
		      if(successfullTurn) {
		    	 GameLogic.promotion(b, primary);
		         gameInterface.setState();
		        
		      } else {
		    	  //System.out.println("Unsuccessfull turn please try again");
		    	  gameInterface.setSideText("Unsuccessfull turn please try again \n \n" + primary.getColour());
		    	 
		      }
		      
		      // Checking if they have checked the opponant
		      if(GameLogic.checkCheck(primary, b)) {
		    	  checkMessage = "you are in check";
		    	  System.out.println(opponent.getColour() + " is in check");
		    	  
		    	  if(GameLogic.checkMate(b, primary)) {
		    		  gameInterface.setSideText("Check mate you lose!!! \n \n" + opponent.getColour());
		    		  Gui.checkMateDialog(primary);
		    		  gameComplete = true;
	        		  return true;
		    	  }
		 	 }
		      
		      if(successfullTurn) {
		    	  gameInterface.setSideText("Success!! \n \n" + checkMessage + " "+ opponent.getColour() + " turn");	 
	          }
		      
		     return successfullTurn;
	  }

}

