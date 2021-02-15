
public class LocalMultiplayerGame extends GameManager{
	
	private boolean gameComplete;
	private Board b;
	private Gui gameInterface;
	private Player one;
	private Player two;
	private int gameCount;
	private boolean quit;

	public void setUpAndPlay(Gui gameInterface) {
		  gameCount = 1;
		  quit = false;
		  this.gameInterface = gameInterface;
	      this.b = new Board();
	      this.one = new Player("White");
	      this.two = new Player("Black");
	      one.setName("One");
	      two.setName("Two");
	      b.startBoard(b); 
	 	  gameInterface.setGame(this);
	 	  gameInterface.setBoard(b);
	 	  //can set up board prior looks better
	 	  //gameInterface.setUpBoard();
	 	  gameInterface.setState();
	 	  gameLoop();
	 	  while(!quit) {
	 		  newGame(gameInterface);
	 	  }
	 	  
	}
	public void newGame(Gui gameInterface) {
		  gameCount++;
		  System.out.println("game " + gameCount);
		  b = new Board();
		  this.one = new Player("White");
	      this.two = new Player("Black");
	      one.setName("One");
	      two.setName("Two");
		  b.startBoard(b);
		  gameInterface.setBoard(b);
		  gameInterface.setSideText("Welcome to Game " + gameCount + "\n To start Game player 1 drag and drop a white piece\"");
		  gameInterface.setState();
		  gameLoop();
	}
	private boolean gameLoop() {
		      
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
		  
	
	private boolean playerTurn(Board b,Player primary,Player opponent,Gui gameInterface) {

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
	    		  gameComplete = true;
	    		  if(Gui.checkMateDialog(primary) == 1) {
	    			  quit = true;
	    		  }
	    		 
      		  return true;
	    	  }
	 	 }
	      
	      if(successfullTurn) {
	    	  gameInterface.setSideText("Success!! \n \n" + checkMessage + " "+ opponent.getColour() + " turn");	 
        }
	      
	     return successfullTurn;
}
	@Override
	public void resign() {
		gameComplete = true;
		quit = true;
		
	}


}
