
public class GameRunner {
		 
	  private static boolean gameComplete;
	  private static boolean newGameReady;
	  private static boolean loop = true;
	  private static int gameType;
	  public static void main(String[] args) {
		  
		  start();
		  
	  }
	  
	  public static void start() {
		  gameType = 0;
		  
		  Gui gameInterface = new Gui();
		  boolean finished = false;
		  GameManager local = null;
		  gameInterface.setUpBoard();
		  
		  int choice = gameInterface.chooseGameType();
		  if(choice == 0) {
		   local = new singlePlayerGame();
		  }
		  if(choice == 1) {
			  local = new LocalMultiplayerGame();
		  }
		  local.setUpAndPlay(gameInterface);
		  
		  gameInterface.setSideText("The game is finished \nPlease Select a new Game from settings");
		  System.out.println("hopefully im here at end");
		  newGame(gameInterface);
	 }
	  
	  public static void newGame(Gui gameInterface) {
		  System.out.println("in de new game");
		  String wasteTime = "";
		  while(loop) {
			  //using this to waste time will try to implement a more elegant solution at a later stage
			  System.out.print("");
			  
			  if(gameType == 0) {
				  gameInterface.setSideText("The game is finished \nPlease Select a new Game from settings");
			  }
			  
			  if(gameType == 1) {
				 System.out.println();
				GameManager single = new singlePlayerGame();
				gameInterface.setSideText(" New SinglePlayer Game \n Whites to move a piece to start");
				single.setUpAndPlay(gameInterface);
			  }
			  
			  if(gameType == 2) {
				  System.out.println();
				 GameManager local = new LocalMultiplayerGame();
				 gameInterface.setSideText(" New multiplayer Game \\n Whites to move a piece to start");
				 local.setUpAndPlay(gameInterface);
			  }
		  }
	  }
	  public static void setGameType(int gameChoice) {
		   gameType = gameChoice;
	  }

}

